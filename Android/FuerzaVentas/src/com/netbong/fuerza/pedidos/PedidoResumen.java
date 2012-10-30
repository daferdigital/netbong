// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterArticulosPedido;
import com.netbong.fuerza.adapters.AdapterEventosPedido;
import com.netbong.fuerza.db.cursores.*;
import com.netbong.fuerza.dialogos.DialogoSiNo;
import com.netbong.fuerza.pedidos.db.DBHandle;
import com.netbong.fuerza.pedidos.sync.AsyncTaskSyncPedido;
import com.netbong.fuerza.util.DeviceListActivity;
import com.netbong.fuerza.util.ZebraPrinterTest;

public class PedidoResumen extends Activity
{

    public PedidoResumen()
    {
        INTENT_REQUEST_CONNECT_TO_DEVICE = 1;
        toolBarOpcRegresar = new _cls1();
        toolBarOpcAnular = new _cls2();
        toolBarOpcRecuperar = new _cls3();
        toolBarOpcImprimir = new _cls4();
        toolBarOpcModificar = new _cls5();
        oclSincronizar = new _cls6();
        oclResumen = new _cls7();
        oclArticulos = new _cls8();
        oclEventos = new _cls9();
        oclImpresion = new _cls10();
    }

    private View getViewArticulos()
    {
        articulos = (LinearLayout)getLayoutInflater().inflate(0x7f03002e, null);
        ListView listview = (ListView)articulos.findViewById(0x7f060080);
        listview.setAdapter(new AdapterArticulosPedido(this, MainActivity.mDbHelper.getListadoProductosEnPedido(pedido)));
        listview.setCacheColorHint(0);
        TextView textview = (TextView)articulos.findViewById(0x7f06007f);
        Object aobj[] = new Object[1];
        aobj[0] = MainActivity.formatVE(montoTotalPedido);
        textview.setText(String.format("Total General Bs.: %s", aobj));
        return articulos;
    }

    private View getViewEventos()
    {
        eventos = (ListView)getLayoutInflater().inflate(0x7f03002f, null);
        eventos.setAdapter(new AdapterEventosPedido(this, MainActivity.mDbHelper.getListadoEventosEnPedido(pedido)));
        eventos.setCacheColorHint(0);
        return eventos;
    }

    private View getViewImpresion()
    {
        String s = CursorPrintDatosPedido.getDatosPedido(MainActivity.mDbHelper.getWritableDatabase(), pedido).getPrintPreviewVersion();
        previewImpresion = (LinearLayout)getLayoutInflater().inflate(0x7f030031, null);
        ((EditText)previewImpresion.findViewById(0x7f060083)).setText(s);
        return previewImpresion;
    }

    private View getViewResumen()
    {
        CursorDatosResumenPedido cursordatosresumenpedido = MainActivity.mDbHelper.getDatosResumenPedido(pedido);
        resumen = (LinearLayout)getLayoutInflater().inflate(0x7f030032, null);
        ((TextView)resumen.findViewById(0x7f060084)).setText(cursordatosresumenpedido.getClienteNombre());
        TextView textview = (TextView)resumen.findViewById(0x7f060086);
        Object aobj[] = new Object[1];
        aobj[0] = cursordatosresumenpedido.getClienteDireccion();
        textview.setText(String.format("Direccion: %s", aobj));
        TextView textview1 = (TextView)resumen.findViewById(0x7f060085);
        Object aobj1[] = new Object[1];
        aobj1[0] = cursordatosresumenpedido.getClienteRIF();
        textview1.setText(String.format("RIF: %s", aobj1));
        TextView textview2 = (TextView)resumen.findViewById(0x7f060074);
        Object aobj2[] = new Object[1];
        aobj2[0] = cursordatosresumenpedido.getPedidoFecha();
        textview2.setText(String.format("Fecha: %s", aobj2));
        TextView textview3 = (TextView)resumen.findViewById(0x7f060087);
        Object aobj3[] = new Object[1];
        aobj3[0] = cursordatosresumenpedido.getPedidoEstatus();
        textview3.setText(String.format("Estatus: %s", aobj3));
        TextView textview4 = (TextView)resumen.findViewById(0x7f060088);
        Object aobj4[] = new Object[1];
        aobj4[0] = Double.valueOf(cursordatosresumenpedido.getPedidoTotal());
        textview4.setText(String.format("Monto Total: Bs. %.2f", aobj4));
        ((TextView)resumen.findViewById(0x7f06008a)).setText(cursordatosresumenpedido.getPedidoObservacion());
        montoTotalPedido = cursordatosresumenpedido.getPedidoTotal();
        clienteId = cursordatosresumenpedido.getClienteId();
        clienteRif = cursordatosresumenpedido.getClienteRIF();
        clienteNombre = cursordatosresumenpedido.getClienteNombre();
        clienteLimiteCredito = cursordatosresumenpedido.getClienteLimiteCredito();
        permitirModificar = cursordatosresumenpedido.permitirModificar();
        permitirImprimir = true;
        permitirSincronizar = cursordatosresumenpedido.permitirSincronizar();
        return resumen;
    }

    public void onActivityResult(int i, int j, Intent intent) {
    	
        if(j == -1 && i == INTENT_REQUEST_CONNECT_TO_DEVICE){
            String s = intent.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
            Log.i("IMPRESION", (new StringBuilder()).toString());
            (new Thread(new ZebraPrinterTest(s, CursorPrintDatosPedido.getDatosPedido(MainActivity.mDbHelper.getWritableDatabase(), pedido).getPrintPreviewVersionToMZ320Format().getBytes()))).start();
        }
        
        if(j == -1 && i == INTENT_REQUEST_PEDIDO_MODIFICAR){
            resumen = null;
            eventos = null;
            articulos = null;
            oclResumen.onClick(null);
        }
        
        if(j != -1 || i != INTENT_REQUEST_PEDIDO_ANULAR) {
        	if(j == -1 && i == INTENT_REQUEST_PEDIDO_RECUPERAR) {
                CursorDatosResumenPedido cursordatosresumenpedido = MainActivity.mDbHelper.getDatosResumenPedido(pedido);
                if(cursordatosresumenpedido.getCount() > 0)
                    if(cursordatosresumenpedido.getPedidoEstatusCodigo() != 1)
                    {
                        MainActivity.crearMensajeToast(this, "Imposible Recuperar. El pedido no se encuentra anulado.", true);
                    } else
                    {
                        DBHandle.recuperar(pedido);
                        cursordatosresumenpedido.requery();
                        findViewById(0x7f06016a).setVisibility(0);
                        findViewById(0x7f06016b).setVisibility(8);
                        resumen = null;
                        oclResumen.onClick(null);
                    }
            }
        } else {
        	CursorDatosResumenPedido cursordatosresumenpedido1 = MainActivity.mDbHelper.getDatosResumenPedido(pedido);
            if(cursordatosresumenpedido1.getCount() <= 0) {
            	if(j == -1 && i == INTENT_REQUEST_PEDIDO_RECUPERAR) {
                    CursorDatosResumenPedido cursordatosresumenpedido = MainActivity.mDbHelper.getDatosResumenPedido(pedido);
                    if(cursordatosresumenpedido.getCount() > 0){
                    	if(cursordatosresumenpedido.getPedidoEstatusCodigo() != 1) {
                            MainActivity.crearMensajeToast(this, "Imposible Recuperar. El pedido no se encuentra anulado.", true);
                        } else {
                            DBHandle.recuperar(pedido);
                            cursordatosresumenpedido.requery();
                            findViewById(0x7f06016a).setVisibility(0);
                            findViewById(0x7f06016b).setVisibility(8);
                            resumen = null;
                            oclResumen.onClick(null);
                        }
                    }
                }
            } else {
            	if(cursordatosresumenpedido1.getPedidoEstatusCodigo() == 0) {
            		DBHandle.anular(pedido);
                    cursordatosresumenpedido1.requery();
                    findViewById(0x7f06016a).setVisibility(8);
                    findViewById(0x7f06016b).setVisibility(0);
                    resumen = null;
                    oclResumen.onClick(null);
            	} else {
            		MainActivity.crearMensajeToast(this, "Imposible Anular. Pedido previamente anulado/sincronizado.", true);
            	}
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.ver_informacion_pedido_activity_layout);
        contenido = (LinearLayout)findViewById(0x7f060002);
        findViewById(0x7f06019d).setOnClickListener(oclSincronizar);
        findViewById(0x7f06019a).setOnClickListener(oclArticulos);
        findViewById(0x7f060199).setOnClickListener(oclResumen);
        findViewById(0x7f06019b).setOnClickListener(oclEventos);
        findViewById(0x7f06019c).setOnClickListener(oclImpresion);
        findViewById(0x7f060197).setOnClickListener(toolBarOpcImprimir);
        findViewById(0x7f060196).setOnClickListener(toolBarOpcModificar);
        findViewById(0x7f060194).setOnClickListener(toolBarOpcAnular);
        findViewById(0x7f060198).setOnClickListener(toolBarOpcRegresar);
        findViewById(0x7f060195).setOnClickListener(toolBarOpcRecuperar);
        findViewById(0x7f06016a).setVisibility(8);
        findViewById(0x7f06016b).setVisibility(8);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            pedido = bundle1.getInt("PEDIDO");
            oclResumen.onClick(null);
            if(bundle1.getInt("VISTA_IMPRESION") == 1)
                oclImpresion.onClick(null);
            if(DBHandle.pedidoPermitirAnular(pedido))
                findViewById(0x7f06016a).setVisibility(0);
            if(DBHandle.pedidoPermitirRecuperar(pedido))
                findViewById(0x7f06016b).setVisibility(0);
        }
    }

    private static int INTENT_REQUEST_PEDIDO_ANULAR = 3;
    private static int INTENT_REQUEST_PEDIDO_MODIFICAR = 2;
    private static int INTENT_REQUEST_PEDIDO_RECUPERAR = 4;
    private int INTENT_REQUEST_CONNECT_TO_DEVICE;
    private LinearLayout articulos;
    private int clienteId;
    private double clienteLimiteCredito;
    private String clienteNombre;
    private String clienteRif;
    private LinearLayout contenido;
    private ListView eventos;
    private double montoTotalPedido;
    private android.view.View.OnClickListener oclArticulos;
    private android.view.View.OnClickListener oclEventos;
    private android.view.View.OnClickListener oclImpresion;
    private android.view.View.OnClickListener oclResumen;
    private android.view.View.OnClickListener oclSincronizar;
    private int pedido;
    private boolean permitirImprimir;
    private boolean permitirModificar;
    private boolean permitirSincronizar;
    private LinearLayout previewImpresion;
    private LinearLayout resumen;
    private android.view.View.OnClickListener toolBarOpcAnular;
    private android.view.View.OnClickListener toolBarOpcImprimir;
    private android.view.View.OnClickListener toolBarOpcModificar;
    private android.view.View.OnClickListener toolBarOpcRecuperar;
    private android.view.View.OnClickListener toolBarOpcRegresar;






















    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final PedidoResumen this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(PedidoResumen.this, DialogoSiNo.class);
            intent.putExtra("MENSAJE", "Esto anulara el pedido actual. \277Desea continuar?");
            startActivityForResult(intent, PedidoResumen.INTENT_REQUEST_PEDIDO_ANULAR);
        }

        final PedidoResumen this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(PedidoResumen.this, DialogoSiNo.class);
            intent.putExtra("MENSAJE", "\277Desea recuperar el pedidio Anulado?");
            startActivityForResult(intent, PedidoResumen.INTENT_REQUEST_PEDIDO_RECUPERAR);
        }

        final PedidoResumen this$0;

        _cls3()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(permitirImprimir)
            {
                Intent intent = new Intent(PedidoResumen.this, DeviceListActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_CONNECT_TO_DEVICE);
            } else
            {
                MainActivity.crearMensajeToast(PedidoResumen.this, "Imposible Imprimir. El pedido no ha sido sincronizado aun.", true);
            }
        }

        final PedidoResumen this$0;

        _cls4()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(permitirModificar)
            {
                Intent intent = new Intent(PedidoResumen.this, PedidoCreacion.class);
                intent.putExtra("PEDIDO", pedido);
                intent.putExtra("ID-PEDIDO", pedido);
                intent.putExtra("ID", clienteId);
                intent.putExtra("RIF", clienteRif);
                intent.putExtra("CLIENTE", clienteNombre);
                intent.putExtra("LIMITE_CREDITO", clienteLimiteCredito);
                startActivityForResult(intent, PedidoResumen.INTENT_REQUEST_PEDIDO_MODIFICAR);
            } else
            {
                MainActivity.crearMensajeToast(PedidoResumen.this, "Imposible modificar. El pedido se encuentra sincronizado/ anulado.", true);
            }
        }

        final PedidoResumen this$0;

        _cls5()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener {

        public void onClick(View view){
            if(!permitirSincronizar){
                MainActivity.crearMensajeToast(PedidoResumen.this, "Solo pedidos con estatus Generado pueden ser sincronizados.", true);
            } else {
            	class _cls1 extends AsyncTaskSyncPedido {

                    protected void onPostExecute(Object obj){
                        onPostExecute((String[])obj);
                    }

                    protected void onPostExecute(String as[]){
                        super.onPostExecute(as);
                        findViewById(0x7f06016a).setVisibility(8);
                        if(DBHandle.pedidoPermitirAnular(pedido))
                            findViewById(0x7f06016a).setVisibility(0);
                        findViewById(0x7f06016b).setVisibility(8);
                        if(DBHandle.pedidoPermitirRecuperar(pedido))
                            findViewById(0x7f06016b).setVisibility(0);
                        findViewById(0x7f06019d).setEnabled(true);
                        if(as.length != 0)
                            MainActivity.crearMensajeToast(PedidoResumen.this, as[0], true);
                        resumen = null;
                        previewImpresion = null;
                        oclResumen.onClick(null);
                    }

                    protected void onPreExecute(){
                        super.onPreExecute();
                        findViewById(0x7f06019d).setEnabled(false);
                    }

                    final _cls6 this$1;

                    _cls1() {
                    	super();
                    	this$1 = _cls6.this;
                    }
                }

                _cls1 _lcls1 = new _cls1();
                Integer ainteger[] = new Integer[1];
                ainteger[0] = new Integer(pedido);
                _lcls1.execute(ainteger);
            }
        }

        final PedidoResumen this$0;


        _cls6() {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls7
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            View view1 = getViewResumen();
            if(view1 != null)
                contenido.addView(view1);
        }

        final PedidoResumen this$0;

        _cls7()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls8
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            View view1 = getViewArticulos();
            if(view1 != null)
                contenido.addView(view1);
        }

        final PedidoResumen this$0;

        _cls8()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls9
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            View view1 = getViewEventos();
            if(view1 != null)
                contenido.addView(view1);
        }

        final PedidoResumen this$0;

        _cls9()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }


    private class _cls10
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            View view1 = getViewImpresion();
            if(view1 != null)
                contenido.addView(view1);
        }

        final PedidoResumen this$0;

        _cls10()
        {
        	super();
        	this$0 = PedidoResumen.this;
        }
    }

}
