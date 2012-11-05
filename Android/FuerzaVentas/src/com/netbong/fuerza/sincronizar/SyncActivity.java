// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.sincronizar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.*;
import com.netbong.fuerza.db.cursores.otros.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

// Referenced classes of package com.ehp.droidsf.sync:
//            HttpSync, SyncActualizacionMaestros, SyncImagenes, SyncNotificacionFacturas, 
//            SyncNotificacionPedidos

public class SyncActivity extends Activity
{

    public SyncActivity()
    {
        oclCancelar = new _cls3();
        oclAceptar = new _cls4();
    }

    private void agregarView(View view)
    {
        if(view != null)
            mly.addView(view);
    }

    private View crearView(String s)
    {
        String as[] = s.split(";");
        LinearLayout linearlayout = (LinearLayout)View.inflate(this, 0x7f03008f, null);
        CheckBox checkbox = (CheckBox)linearlayout.findViewById(0x7f060000);
        checkbox.setText(as[1]);
        checkbox.setTag(as[0]);
        TextView textview = (TextView)linearlayout.findViewById(0x7f06018e);
        Object aobj[] = new Object[1];
        aobj[0] = as[2];
        textview.setText(String.format("(%s elementos pendientes por sincronizar.)", aobj));
        return linearlayout;
    }

    private View crearView(String s, long l)
    {
        LinearLayout linearlayout = (LinearLayout)View.inflate(this, 0x7f03008f, null);
        ((CheckBox)linearlayout.findViewById(0x7f060000)).setText(s);
        TextView textview = (TextView)linearlayout.findViewById(0x7f06018e);
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        textview.setText(String.format("(%s elementos pendientes por sincronizar.)", aobj));
        return linearlayout;
    }

    private void listarElementos() {
    	if(trama == null || trama.length() <= 0) {
    		
    	} else {
    		String[] as = trama.split("\r\n");;
            
    		for (int i = 0; i < as.length; i++) {
    			agregarView(crearView(as[i]));
			}
    	}
    }

    private void mostrarEventosPendientesPorEnviar()
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06018c);
        linearlayout.removeAllViews();
        long l = MainActivity.mDbHelper.pedidosPendientesPorSync();
        if(l > 0L)
            linearlayout.addView(crearView("Pedidos Efectuados", l));
        long l1 = MainActivity.mDbHelper.pagosPendientesPorSync();
        if(l1 > 0L)
            linearlayout.addView(crearView("Registro de Pago", l1));
        long l2 = MainActivity.mDbHelper.devolucionesPendientesPorSync();
        if(l2 > 0L)
            linearlayout.addView(crearView("Registro de Devoluciones", l2));
        long l3 = MainActivity.mDbHelper.clientesPendientesPorSync();
        if(l3 > 0L)
            linearlayout.addView(crearView("Registro de Nuevos Clientes", l3));
    }

    private void syncEnviarCancelaciones()
    {
        Cancelaciones cancelaciones = Cancelaciones.getCancelacionesNoSync();
        if(cancelaciones.getCount() > 0)
            do
                try
                {
                    if(HttpSync.enviarCancelaciones(cancelaciones.getTramaSync()))
                        MainActivity.mDbHelper.marcarComoSyncCancelacion(cancelaciones.getId());
                }
                catch(UnsupportedEncodingException unsupportedencodingexception) { }
                catch(IOException ioexception) { }
            while(cancelaciones.moveToNext());
    }

    private void syncEnviarClientes()
    {
        CursorClientes cursorclientes = CursorClientes.getClientesNoSync();
        if(cursorclientes.getCount() > 0)
            do
                try
                {
                    if(HttpSync.enviarDatosNuevoCliente(cursorclientes.getTramaClientesNoSync(cursorclientes.getPosition())))
                        MainActivity.mDbHelper.marcarComoSyncCliente(cursorclientes.getId());
                }
                catch(UnsupportedEncodingException unsupportedencodingexception) { }
                catch(IOException ioexception)
                {
                    Log.i("fallo", ioexception.getMessage());
                }
            while(cursorclientes.moveToNext());
    }

    private void syncEnviarDevoluciones()
    {
        Devoluciones devoluciones = Devoluciones.getDevolucionesNoSync();
        if(devoluciones.getCount() > 0)
            do
                try
                {
                    Object aobj[] = new Object[2];
                    aobj[0] = Integer.valueOf(devoluciones.getIdFactura());
                    aobj[1] = Integer.valueOf(devoluciones.getId());
                    String s = String.format("%d-%d", aobj);
                    boolean flag = HttpSync.enviarDevoluciones(devoluciones.getIdFactura(), devoluciones.getIdProducto(), devoluciones.getFecha(), devoluciones.getCantidad(), devoluciones.getObservacion(), s);
                    DevolucionesImagenes devolucionesimagenes = DevolucionesImagenes.getImagenes(devoluciones.getId());
                    if(devolucionesimagenes.getCount() > 0)
                        do
                            HttpSync.enviarDevolucionArchivos(devolucionesimagenes.getImagen(), devoluciones.getId(), s);
                        while(devolucionesimagenes.moveToNext());
                    if(flag)
                        MainActivity.mDbHelper.marcarComoSyncDevolucion(devoluciones.getId());
                }
                catch(UnsupportedEncodingException unsupportedencodingexception) { }
                catch(IOException ioexception) { }
            while(devoluciones.moveToNext());
    }

    private void syncEnviarPedidos()
    {
        String s = MainActivity.settings.getString("login", MainActivity.getDefaultLogin());
        String s1 = MainActivity.settings.getString("tablet-descrip", "NONE-DEVICE");
        CursorSyncDatosPedido cursorsyncdatospedido = CursorSyncDatosPedido.getPedidosNoSynco();
        if(cursorsyncdatospedido.getCount() > 0)
            do
                try
                {
                    int i = cursorsyncdatospedido.getCodigoPedido();
                    if(HttpSync.enviarDatosPedidoGenerado(MainActivity.mDbHelper.getSyncDatosPedido(i, s, s1).construirTramaPedido().toString()))
                        MainActivity.mDbHelper.marcarComoSyncPedido(i);
                }
                catch(UnsupportedEncodingException unsupportedencodingexception) { }
                catch(IOException ioexception) { }
            while(cursorsyncdatospedido.moveToNext());
    }

    private void syncRecibirEventos()
    {
        (new _cls5()).start();
    }

    private void syncRecirEventosActualizacionMaestros() {
        try {
        	String s = MainActivity.settings.getString("login", "NONE");
            trama = HttpSync.eventosPendientes(s, "1");
            SyncActualizacionMaestros.actualizar(trama);
            HttpSync.eventosPendientesConfirmacion(s, "1");
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    private void syncRecirEventosImagenes() {
        try {
        	String s = MainActivity.settings.getString("login", "NONE");
            trama = HttpSync.eventosPendientes(s, "4");
            SyncImagenes.descargar(trama);
            HttpSync.eventosPendientesConfirmacion(s, "4");
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("Img-Exc", e.getMessage());
		}
    }

    private void syncRecirEventosNotificacionesFacturas()
    {
        try {
        	String s = MainActivity.settings.getString("login", "NONE");
            trama = HttpSync.eventosPendientes(s, "3");
            SyncNotificacionFacturas.actualizar(trama);
            HttpSync.eventosPendientesConfirmacion(s, "3");
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    private void syncRecirEventosNotificacionesPedidos() {
        try {
        	String s = MainActivity.settings.getString("login", "NONE");
            trama = HttpSync.eventosPendientes(s, "2");
            SyncNotificacionPedidos.actualizar(trama);
            HttpSync.eventosPendientesConfirmacion(s, "2");
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sync_activity_ly);
        mly = (LinearLayout)findViewById(0x7f06018d);
        findViewById(0x7f060011).setOnClickListener(oclCancelar);
        syncRecibirEventos();
        mostrarEventosPendientesPorEnviar();
    }

    ProgressDialog dialog;
    final Handler mHandler = new Handler();
    final Runnable mUpdateResults = new _cls1();
    final Runnable mUpdateResults2 = new _cls2();
    LinearLayout mly;
    android.view.View.OnClickListener oclAceptar;
    android.view.View.OnClickListener oclCancelar;
    String trama;










    private class _cls1
        implements Runnable
    {

        public void run()
        {
            listarElementos();
            findViewById(0x7f060012).setOnClickListener(oclAceptar);
        }

        final SyncActivity this$0;

        _cls1(){
        	super();
        	this$0 = SyncActivity.this;
        }
    }


    private class _cls2
        implements Runnable
    {

        public void run()
        {
            dialog.cancel();
            MainActivity.crearMensajeToast(SyncActivity.this, "Operaciones efectuadas exitosamente", true);
            finish();
        }

        final SyncActivity this$0;

        _cls2() {
        	super();
        	this$0 = SyncActivity.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final SyncActivity this$0;

        _cls3(){
        	super();
        	this$0 = SyncActivity.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            dialog = ProgressDialog.show(SyncActivity.this, "", "Realizando tareas de sincronizacion. Por favor espere...", false);
            class _cls1 extends Thread {

                public void run() {
                    LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06018c);
                    int i = linearlayout.getChildCount();
                    int j = 0;
                    
                    for (j = 0; j < i; j++) {
                    	CheckBox checkbox = (CheckBox)((LinearLayout)linearlayout.getChildAt(j)).findViewById(0x7f060000);
                        if(checkbox.isChecked() && checkbox.getText().toString().equalsIgnoreCase("Pedidos Efectuados"))
                            syncEnviarPedidos();
                        else
                        if(checkbox.isChecked() && checkbox.getText().toString().equalsIgnoreCase("Registro de Pago"))
                            syncEnviarCancelaciones();
                        else
                        if(checkbox.isChecked() && checkbox.getText().toString().equalsIgnoreCase("Registro de Devoluciones"))
                            syncEnviarDevoluciones();
                        else
                        if(checkbox.isChecked() && checkbox.getText().toString().equalsIgnoreCase("Registro de Nuevos Clientes"))
                            syncEnviarClientes();
					}
                    
                    LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f06018d);
                    int k = linearlayout1.getChildCount();
                    int l = 0;
                    
                    for (l = 0; l < k; l++) {
                    	CheckBox checkbox1 = (CheckBox)((LinearLayout)linearlayout1.getChildAt(l)).findViewById(0x7f060000);
                        String s = checkbox1.getTag().toString();
                        if(checkbox1.isChecked() && s.equalsIgnoreCase("1"))
                            syncRecirEventosActualizacionMaestros();
                        else
                        if(checkbox1.isChecked() && s.equalsIgnoreCase("2"))
                            syncRecirEventosNotificacionesPedidos();
                        else
                        if(checkbox1.isChecked() && s.equalsIgnoreCase("3"))
                            syncRecirEventosNotificacionesFacturas();
                        else
                        if(checkbox1.isChecked() && s.equalsIgnoreCase("4"))
                            syncRecirEventosImagenes();
					}
                    
                    mHandler.post(mUpdateResults2);
                }
                
                final _cls4 this$1;

                _cls1() {
                	super();
                	this$1 = _cls4.this;
                }
            }

            (new _cls1()).start();
        }

        final SyncActivity this$0;

        _cls4() {
        	super();
        	this$0 = SyncActivity.this;
        }
    }


    private class _cls5 extends Thread {
        public void run(){
            try {
            	String s = MainActivity.settings.getString("login", "NONE");
                trama = HttpSync.eventosPendientes(s);
                mHandler.post(mUpdateResults);
			} catch (Exception e) {
				// TODO: handle exception
			}
        }

        final SyncActivity this$0;

        _cls5() {
        	super();
        	this$0 = SyncActivity.this;
        }
    }

}
