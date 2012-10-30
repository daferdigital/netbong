// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterClienteDocumentosDigitales;
import com.netbong.fuerza.clientes.db.DBHandle;
import com.netbong.fuerza.clientes.db.cursors.CrsMovimientosResumen;
import com.netbong.fuerza.db.cursores.*;

import java.io.File;

public class FichaCliente extends Activity
{

    public FichaCliente()
    {
        oclAgregarDocumentoDig = new _cls1();
        oclGuardarFichaDatos = new _cls2();
        oclGuardarFichaSoporteFisico = new _cls3();
        oclVerFichaDatos = new _cls4();
        oclVerFichaSoporteFisico = new _cls5();
        oclVerFichaDocumentoDigital = new _cls6();
        oclVerFichaMovimientosResumen = new _cls7();
        oclEliminarDocumentoDig = new _cls8();
    }

    private View ViewFichaDatos()
    {
        if(fichaDatos == null)
        {
            if(!esClienteProfit)
                fichaDatos = (LinearLayout)View.inflate(this, 0x7f030048, null);
            if(esClienteProfit)
                fichaDatos = (LinearLayout)View.inflate(this, 0x7f030049, null);
            if(idCliente > 0)
                cargarFichaDatos();
        }
        return fichaDatos;
    }

    private View ViewFichaDocumentoDigital()
    {
        if(fichaDocumentoDigital == null)
        {
            fichaDocumentoDigital = (LinearLayout)View.inflate(this, 0x7f03004a, null);
            fichaDocumentoDigital.findViewById(0x7f0600cc).setOnClickListener(oclAgregarDocumentoDig);
            if(idCliente > 0)
                cargarFichaDocumentoDigital();
        }
        return fichaDocumentoDigital;
    }

    private View ViewFichaMovimientosResumen()
    {
        if(fichaMovimientosResumen == null)
        {
            fichaMovimientosResumen = (LinearLayout)View.inflate(this, 0x7f03004d, null);
            if(idCliente > 0)
                cargarFichaMovimientosResumen();
        }
        return fichaMovimientosResumen;
    }

    private View ViewFichaSoporteFisico()
    {
        if(fichaSoporteFisico == null)
        {
            if(!esClienteProfit)
                fichaSoporteFisico = (LinearLayout)View.inflate(this, 0x7f03004b, null);
            if(esClienteProfit)
                fichaSoporteFisico = (LinearLayout)View.inflate(this, 0x7f03004c, null);
            if(idCliente > 0)
                cargarFichaSoporteFisisco();
        }
        return fichaSoporteFisico;
    }

    private int actualizarClienteFichaDatos()
    {
        if(!esClienteProfit)
            actualizarFichaDatosNoProfit();
        if(esClienteProfit)
            actualizarFichaDatosProfit();
        return 0;
    }

    private int actualizarClienteFichaSoporteFisico()
    {
        if(!esClienteProfit)
            actualizarFichaSoporteFisicoNoProfit();
        if(esClienteProfit)
            actualizarFichaSoporteFisicoProfit();
        return 0;
    }

    private int actualizarFichaDatosNoProfit()
    {
        String s = ((TextView)contenido.findViewById(0x7f0600be)).getText().toString();
        String s1 = ((TextView)contenido.findViewById(0x7f0600bf)).getText().toString();
        String s2 = ((TextView)contenido.findViewById(0x7f0600c0)).getText().toString();
        String s3 = ((TextView)contenido.findViewById(0x7f0600c1)).getText().toString();
        String s4 = ((TextView)contenido.findViewById(0x7f0600c2)).getText().toString();
        TextView textview = (TextView)contenido.findViewById(0x7f0600c3);
        int i;
        if(!MainActivity.isValidPhone(textview.getText()))
        {
            Toast.makeText(this, "Formato de telefono no valido.", 1).show();
            i = 1;
        } else
        {
            String s5 = textview.getText().toString();
            TextView textview1 = (TextView)contenido.findViewById(0x7f0600c4);
            if(!MainActivity.isValidPhone(textview1.getText()))
            {
                Toast.makeText(this, "Formato de fax no valido.", 1).show();
                i = 1;
            } else
            {
                String s6 = textview1.getText().toString();
                TextView textview2 = (TextView)contenido.findViewById(0x7f0600c5);
                if(!MainActivity.isValidEmail(textview2.getText()))
                {
                    Toast.makeText(this, "Formato de correo no valido.", 1).show();
                    i = 1;
                } else
                {
                    String s7 = textview2.getText().toString();
                    TextView _tmp = (TextView)contenido.findViewById(0x7f0600be);
                    StringBuilder stringbuilder = new StringBuilder();
                    if(((CheckBox)contenido.findViewById(0x7f0600c6)).isChecked())
                        stringbuilder.append("Lunes;");
                    if(((CheckBox)contenido.findViewById(0x7f0600c7)).isChecked())
                        stringbuilder.append("Martes;");
                    if(((CheckBox)contenido.findViewById(0x7f0600c8)).isChecked())
                        stringbuilder.append("Miercoles;");
                    if(((CheckBox)contenido.findViewById(0x7f0600c9)).isChecked())
                        stringbuilder.append("Jueves;");
                    if(((CheckBox)contenido.findViewById(0x7f0600ca)).isChecked())
                        stringbuilder.append("Viernes;");
                    String s8 = stringbuilder.toString();
                    MainActivity.mDbHelper.clienteActualizarExistente(idCliente, 1, s, s1, s2, s3, s4, s5, s6, s7, s8);
                    Toast.makeText(this, "Datos Guardados Exitosamente.", 0).show();
                    i = 0;
                }
            }
        }
        return i;
    }

    private int actualizarFichaDatosProfit()
    {
        return 0;
    }

    private int actualizarFichaSoporteFisicoNoProfit()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        String s;
        if(((CheckBox)contenido.findViewById(0x7f0600cd)).isChecked())
            i = 1;
        else
            i = 0;
        if(((CheckBox)contenido.findViewById(0x7f0600ce)).isChecked())
            j = 1;
        else
            j = 0;
        if(((CheckBox)contenido.findViewById(0x7f0600cf)).isChecked())
            k = 1;
        else
            k = 0;
        if(((CheckBox)contenido.findViewById(0x7f0600d0)).isChecked())
            l = 1;
        else
            l = 0;
        if(((CheckBox)contenido.findViewById(0x7f0600d1)).isChecked())
            i1 = 1;
        else
            i1 = 0;
        s = ((EditText)contenido.findViewById(0x7f0600d2)).getText().toString();
        return MainActivity.mDbHelper.clienteActualizarSoporteFisico(idCliente, i, j, k, l, i1, s);
    }

    private int actualizarFichaSoporteFisicoProfit()
    {
        return 0;
    }

    private android.view.View.OnClickListener callBackCerrar()
    {
        return new _cls9();
    }

    private android.view.View.OnClickListener callBackSeleccionarContinuar()
    {
        return new _cls10();
    }

    private void cargarFichaDatos()
    {
        CursorClientes cursorclientes = MainActivity.mDbHelper.getDatosCliente(idCliente);
        codigoCliente = cursorclientes.getCodigo();
        ((TextView)fichaDatos.findViewById(0x7f0600be)).setText(cursorclientes.getNombre());
        ((TextView)fichaDatos.findViewById(0x7f0600bf)).setText(cursorclientes.getRif());
        ((TextView)fichaDatos.findViewById(0x7f0600c0)).setText(cursorclientes.getDireccion());
        ((TextView)fichaDatos.findViewById(0x7f0600c1)).setText(cursorclientes.getDireccionMercancia());
        ((TextView)fichaDatos.findViewById(0x7f0600c2)).setText(cursorclientes.getContacto());
        ((TextView)fichaDatos.findViewById(0x7f0600c3)).setText(cursorclientes.getContactoTelefono());
        ((TextView)fichaDatos.findViewById(0x7f0600c4)).setText(cursorclientes.getContactoFax());
        ((TextView)fichaDatos.findViewById(0x7f0600c5)).setText(cursorclientes.getContactoCorreo());
        String as[] = cursorclientes.getDiasCaja().split(";");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                cursorclientes.close();
                return;
            }
            String s = as[j];
            Log.i("DIAS_CAJA", s);
            if(s.equalsIgnoreCase("Lunes"))
                ((CheckBox)fichaDatos.findViewById(0x7f0600c6)).setChecked(true);
            if(s.equalsIgnoreCase("Martes"))
                ((CheckBox)fichaDatos.findViewById(0x7f0600c7)).setChecked(true);
            if(s.equalsIgnoreCase("Miercoles"))
                ((CheckBox)fichaDatos.findViewById(0x7f0600c8)).setChecked(true);
            if(s.equalsIgnoreCase("Jueves"))
                ((CheckBox)fichaDatos.findViewById(0x7f0600c9)).setChecked(true);
            if(s.equalsIgnoreCase("Viernes"))
                ((CheckBox)fichaDatos.findViewById(0x7f0600ca)).setChecked(true);
            j++;
        } while(true);
    }

    private void cargarFichaDocumentoDigital()
    {
        adpDocDigitales = new AdapterClienteDocumentosDigitales(this, CursorClienteDocumentosDigitales.getDcumentosDigitales(MainActivity.mDbHelper.getWritableDatabase(), idCliente), oclEliminarDocumentoDig);
        ((Gallery)fichaDocumentoDigital.findViewById(0x7f0600cb)).setAdapter(adpDocDigitales);
    }

    private void cargarFichaMovimientosResumen()
    {
        double d = DBHandle.movimientosResumenTotal(codigoCliente);
        ((TextView)fichaMovimientosResumen.findViewById(0x7f0600d3)).setText((new StringBuilder("Bs.: ")).append(MainActivity.formatVE(d)).toString());
        CrsMovimientosResumen crsmovimientosresumen = CrsMovimientosResumen.getMovimientosResumen(codigoCliente);
        LinearLayout linearlayout = (LinearLayout)fichaMovimientosResumen.findViewById(0x7f0600d4);
        linearlayout.removeAllViews();
        if(crsmovimientosresumen.moveToFirst())
            do
            {
                View view = View.inflate(this, 0x7f030052, null);
                ((TextView)view.findViewById(0x7f0600de)).setText(crsmovimientosresumen.getMovimientoResumenConcepto());
                ((TextView)view.findViewById(0x7f0600df)).setText(crsmovimientosresumen.getMovimientoResumenComentario());
                ((TextView)view.findViewById(0x7f0600e0)).setText((new StringBuilder("Bs.: ")).append(MainActivity.formatVE(crsmovimientosresumen.getMovimientoResumenSaldo())).toString());
                linearlayout.addView(view);
            } while(crsmovimientosresumen.moveToNext());
        crsmovimientosresumen.close();
    }

    private void cargarFichaSoporteFisisco()
    {
        boolean flag = true;
        CursorClienteDocumentosFisicos cursorclientedocumentosfisicos = MainActivity.mDbHelper.getDocumentosFisicosCliente(idCliente);
        if(cursorclientedocumentosfisicos.getCount() != 0)
        {
            CheckBox checkbox = (CheckBox)fichaSoporteFisico.findViewById(0x7f0600cd);
            boolean flag1;
            CheckBox checkbox1;
            boolean flag2;
            CheckBox checkbox2;
            boolean flag3;
            CheckBox checkbox3;
            boolean flag4;
            CheckBox checkbox4;
            if(cursorclientedocumentosfisicos.getRegistroMercantil() == 1)
                flag1 = flag;
            else
                flag1 = false;
            checkbox.setChecked(flag1);
            checkbox1 = (CheckBox)fichaSoporteFisico.findViewById(0x7f0600ce);
            if(cursorclientedocumentosfisicos.getRif() == 1)
                flag2 = flag;
            else
                flag2 = false;
            checkbox1.setChecked(flag2);
            checkbox2 = (CheckBox)fichaSoporteFisico.findViewById(0x7f0600cf);
            if(cursorclientedocumentosfisicos.getCedula() == 1)
                flag3 = flag;
            else
                flag3 = false;
            checkbox2.setChecked(flag3);
            checkbox3 = (CheckBox)fichaSoporteFisico.findViewById(0x7f0600d0);
            if(cursorclientedocumentosfisicos.getReferenciasComerciales() == 1)
                flag4 = flag;
            else
                flag4 = false;
            checkbox3.setChecked(flag4);
            checkbox4 = (CheckBox)fichaSoporteFisico.findViewById(0x7f0600d1);
            if(cursorclientedocumentosfisicos.getReferenciasBancarias() != 1)
                flag = false;
            checkbox4.setChecked(flag);
            ((EditText)fichaSoporteFisico.findViewById(0x7f0600d2)).setText(cursorclientedocumentosfisicos.getObservaciones());
        }
    }

    private int crearNuevoCliente()
    {
        String s = ((TextView)contenido.findViewById(0x7f0600be)).getText().toString();
        String s1 = ((TextView)contenido.findViewById(0x7f0600bf)).getText().toString();
        String s2 = ((TextView)contenido.findViewById(0x7f0600c0)).getText().toString();
        String s3 = ((TextView)contenido.findViewById(0x7f0600c1)).getText().toString();
        String s4 = ((TextView)contenido.findViewById(0x7f0600c2)).getText().toString();
        TextView textview = (TextView)contenido.findViewById(0x7f0600c3);
        int i;
        if(!MainActivity.isValidPhone(textview.getText()))
        {
            Toast.makeText(this, "Formato de telefono no valido.", 1).show();
            i = 1;
        } else
        {
            String s5 = textview.getText().toString();
            TextView textview1 = (TextView)contenido.findViewById(0x7f0600c4);
            if(!MainActivity.isValidPhone(textview1.getText()))
            {
                Toast.makeText(this, "Formato de fax no valido.", 1).show();
                i = 1;
            } else
            {
                String s6 = textview1.getText().toString();
                TextView textview2 = (TextView)contenido.findViewById(0x7f0600c5);
                if(!MainActivity.isValidEmail(textview2.getText()))
                {
                    Toast.makeText(this, "Formato de correo no valido.", 1).show();
                    i = 1;
                } else
                {
                    String s7 = textview2.getText().toString();
                    TextView _tmp = (TextView)contenido.findViewById(0x7f0600be);
                    StringBuilder stringbuilder = new StringBuilder();
                    if(((CheckBox)contenido.findViewById(0x7f0600c6)).isChecked())
                        stringbuilder.append("Lunes, ");
                    if(((CheckBox)contenido.findViewById(0x7f0600c7)).isChecked())
                        stringbuilder.append("Martes, ");
                    if(((CheckBox)contenido.findViewById(0x7f0600c8)).isChecked())
                        stringbuilder.append("Miercoles, ");
                    if(((CheckBox)contenido.findViewById(0x7f0600c9)).isChecked())
                        stringbuilder.append("Jueves, ");
                    if(((CheckBox)contenido.findViewById(0x7f0600ca)).isChecked())
                        stringbuilder.append("Viernes, ");
                    String s8 = stringbuilder.toString();
                    idCliente = MainActivity.mDbHelper.clienteCrearNuevo(1, s, s1, s2, s3, s4, s5, s6, s7, s8);
                    Toast.makeText(this, "Datos Guardados Exitosamente.", 0).show();
                    if(idCliente > 0)
                        i = 0;
                    else
                        i = 1;
                }
            }
        }
        return i;
    }

    private void crearNuevoDocumentoDigital()
    {
        if(idCliente == 0)
        {
            MainActivity.crearMensajeToast(this, "Seleccione [Datos] y luego [Guardar Cambios] para poder crear documentos digitales.", true);
        } else
        {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            String s = MainActivity.mDbHelper.generarNombreDocumentosDigitalCliente(idCliente);
            file = new File(Environment.getExternalStorageDirectory(), s);
            intent.putExtra("output", Uri.fromFile(file));
            startActivityForResult(intent, 1);
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1 && i == 1)
        {
            String s = file.getAbsolutePath();
            if(MainActivity.mDbHelper.crearDocumentoDigitalCliente(idCliente, s, "NO TAG"))
            {
                Gallery gallery = (Gallery)fichaDocumentoDigital.findViewById(0x7f0600cb);
                gallery.setAdapter(null);
                adpDocDigitales.reQueryCursor();
                gallery.setAdapter(adpDocDigitales);
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.ficha_cliente_activity_layout);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            idCliente = bundle1.getInt("ID_CLIENTE", 0);
            esClienteProfit = bundle1.getBoolean("PROFIT");
            fuente = bundle1.getInt("FUENTE", 0);
        }
        contenido = (LinearLayout)findViewById(0x7f060002);
        guadar = (Button)findViewById(0x7f0600bb);
        if(esClienteProfit)
            guadar.setVisibility(8);
        findViewById(0x7f0600b8).setOnClickListener(oclVerFichaSoporteFisico);
        findViewById(0x7f0600b7).setOnClickListener(oclVerFichaDatos);
        findViewById(0x7f0600b9).setOnClickListener(oclVerFichaDocumentoDigital);
        findViewById(0x7f0600ba).setOnClickListener(oclVerFichaMovimientosResumen);
        View view;
        android.view.View.OnClickListener onclicklistener;
        if(fuente == 1)
            view = findViewById(0x7f0600bd);
        else
            view = findViewById(0x7f0600bc);
        if(fuente == 1)
            onclicklistener = callBackSeleccionarContinuar();
        else
            onclicklistener = callBackCerrar();
        view.setOnClickListener(onclicklistener);
        view.setVisibility(0);
        oclVerFichaDatos.onClick(findViewById(0x7f0600b7));
    }

    private static final int INTENT_REQUEST_TOMAR_FOTO = 1;
    private static File file = null;
    private AdapterClienteDocumentosDigitales adpDocDigitales;
    private String codigoCliente;
    LinearLayout contenido;
    private boolean esClienteProfit;
    LinearLayout fichaDatos;
    LinearLayout fichaDocumentoDigital;
    LinearLayout fichaMovimientosResumen;
    LinearLayout fichaSoporteFisico;
    int fuente;
    Button guadar;
    private int idCliente;
    android.view.View.OnClickListener oclAgregarDocumentoDig;
    android.view.View.OnClickListener oclEliminarDocumentoDig;
    android.view.View.OnClickListener oclGuardarFichaDatos;
    android.view.View.OnClickListener oclGuardarFichaSoporteFisico;
    android.view.View.OnClickListener oclVerFichaDatos;
    android.view.View.OnClickListener oclVerFichaDocumentoDigital;
    android.view.View.OnClickListener oclVerFichaMovimientosResumen;
    android.view.View.OnClickListener oclVerFichaSoporteFisico;












    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            crearNuevoDocumentoDigital();
        }

        final FichaCliente this$0;

        _cls1()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(idCliente == 0)
                crearNuevoCliente();
            if(idCliente > 0)
                actualizarClienteFichaDatos();
        }

        final FichaCliente this$0;

        _cls2()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            actualizarClienteFichaSoporteFisico();
            Toast.makeText(FichaCliente.this, "Datos Guardados Exitosamente.", 0).show();
        }

        final FichaCliente this$0;

        _cls3()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            contenido.addView(ViewFichaDatos());
            guadar.setOnClickListener(oclGuardarFichaDatos);
        }

        final FichaCliente this$0;

        _cls4()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            contenido.addView(ViewFichaSoporteFisico());
            guadar.setOnClickListener(oclGuardarFichaSoporteFisico);
        }

        final FichaCliente this$0;

        _cls5()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            contenido.addView(ViewFichaDocumentoDigital());
            guadar.setOnClickListener(null);
        }

        final FichaCliente this$0;

        _cls6()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls7
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            contenido.removeAllViews();
            contenido.addView(ViewFichaMovimientosResumen());
            guadar.setOnClickListener(null);
        }

        final FichaCliente this$0;

        _cls7()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls8
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            int i = view.getId();
            String s = view.getTag().toString();
            MainActivity.mDbHelper.eliminarDocumentoDigital(i);
            (new File(s)).delete();
            cargarFichaDocumentoDigital();
        }

        final FichaCliente this$0;

        _cls8()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls9
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final FichaCliente this$0;

        _cls9()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }


    private class _cls10
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            View view1 = ViewFichaDatos();
            String s = ((TextView)view1.findViewById(0x7f0600be)).getText().toString();
            String s1 = ((TextView)view1.findViewById(0x7f0600bf)).getText().toString();
            Intent intent = new Intent();
            intent.putExtra("ID", idCliente);
            intent.putExtra("RIF", s1);
            intent.putExtra("CLIENTE", s);
            setResult(-1, intent);
            finish();
        }

        final FichaCliente this$0;

        _cls10()
        {
        	super();
        	this$0 = FichaCliente.this;
        }
    }

}
