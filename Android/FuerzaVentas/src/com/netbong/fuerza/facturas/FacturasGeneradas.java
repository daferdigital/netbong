// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.facturas.adapters.AdpFacturas;
import com.netbong.fuerza.facturas.db.DBHandle;
import com.netbong.fuerza.facturas.db.cursors.CsrFacturasGeneradas;

public class FacturasGeneradas extends Activity
{

    public FacturasGeneradas()
    {
        textWatcher = new _cls1();
        oclSeleccionarFactura = new _cls2();
        oclNuevaConsulta = new _cls3();
        oclCancelar = new _cls4();
        oclEstablecerParametrosFiltro = new _cls5();
    }

    private static final String formatoDia(int i)
    {
        String s;
        if(i < 10)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(i);
            s = String.format("0%d", aobj1);
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            s = String.format("%d", aobj);
        }
        return s;
    }

    private static final String formatoMes(int i)
    {
        String s;
        if(i < 10)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(i);
            s = String.format("0%d", aobj1);
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            s = String.format("%d", aobj);
        }
        return s;
    }

    private void mostrarOpcionComoNoSeleccionado(View view)
    {
        if(view != null)
            view.setBackgroundColor(0);
    }

    private void mostrarOpcionComoSeleccionado(View view)
    {
        view.setBackgroundColor(-1);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1 && i == 3)
        {
            String s = intent.getStringExtra("CRITERIOS");
            String s1 = intent.getStringExtra("CLIENTE");
            String s2 = intent.getStringExtra("PENDIENTE");
            String s3 = intent.getStringExtra("ANULADO");
            String s4 = intent.getStringExtra("PAGADO");
            String s5 = intent.getStringExtra("FECHA_DESDE");
            String s6 = intent.getStringExtra("FECHA_HASTA");
            if(s != null && s.length() > 0)
            {
                ((TextView)findViewById(0x7f06001b)).setText(s);
                if(crp != null && !crp.isClosed())
                    crp.close();
                crp = DBHandle.obtenerFacturas(s1, s2, s3, s4, s5, s6);
                lv_facturas.setAdapter(new AdpFacturas(this, crp, oclSeleccionarFactura));
                descripcionFiltroActual = s;
                if(crp.getCount() == 0)
                    MainActivity.crearMensajeToast(this, "No se encontraron coincidencias.", true);
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.facturas_generadas_activity_layout);
        campoFiltroCliente = (EditText)findViewById(0x7f060015);
        campoFiltroCliente.addTextChangedListener(textWatcher);
        lv_facturas = (ListView)findViewById(0x7f060052);
        lv_facturas.setDivider(null);
        lv_facturas.setCacheColorHint(0);
        findViewById(0x7f060017).setOnClickListener(oclNuevaConsulta);
        findViewById(0x7f060018).setOnClickListener(oclCancelar);
        findViewById(0x7f0600b1).setOnClickListener(oclEstablecerParametrosFiltro);
        oclNuevaConsulta.onClick(null);
    }

    private static final int INTENT_REQUEST_CONFIGURAR_CRITERIO_FILTRO = 3;
    private static final int INTENT_REQUEST_REGISTRAR_CANCELACION = 1;
    private static final int INTENT_REQUEST_REGISTRAR_DEVOLUCIONES = 2;
    EditText campoFiltroCliente;
    CsrFacturasGeneradas crp;
    String descripcionFiltroActual;
    Intent filtrarFacturas;
    ListView lv_facturas;
    android.view.View.OnClickListener oclCancelar;
    android.view.View.OnClickListener oclEstablecerParametrosFiltro;
    android.view.View.OnClickListener oclNuevaConsulta;
    android.view.View.OnClickListener oclSeleccionarFactura;
    private TextWatcher textWatcher;

    private class _cls1
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            Editable editable1 = campoFiltroCliente.getText();
            if(editable1 != null){
            	String s = editable1.toString();
                if(s != null)
                {
                    String s1 = s.trim();
                    if(s1 != null)
                    {
                        if(crp != null && !crp.isClosed())
                            crp.close();
                        crp = DBHandle.obtenerFacturas(s1);
                        lv_facturas.setAdapter(new AdpFacturas(FacturasGeneradas.this, crp, oclSeleccionarFactura));
                        descripcionFiltroActual = (new StringBuilder("Patron de nombre: ")).append(s1).toString();
                        ((TextView)findViewById(0x7f06001b)).setText(descripcionFiltroActual);
                    }
                }
            }
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        final FacturasGeneradas this$0;

        _cls1()
        {
        	super();
        	this$0 = FacturasGeneradas.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(FacturasGeneradas.this, FacturaResumen.class);
            intent.putExtra("FACTURA", Integer.parseInt(view.getTag().toString()));
            startActivity(intent);
        }

        final FacturasGeneradas this$0;

        _cls2()
        {
        	super();
        	this$0 = FacturasGeneradas.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(crp != null && !crp.isClosed())
                crp.close();
            campoFiltroCliente.setText(null);
        }

        final FacturasGeneradas this$0;

        _cls3()
        {
        	super();
        	this$0 = FacturasGeneradas.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final FacturasGeneradas this$0;

        _cls4()
        {
        	super();
        	this$0 = FacturasGeneradas.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(filtrarFacturas == null)
                filtrarFacturas = new Intent(FacturasGeneradas.this, FacturasGeneradasFiltro.class);
            startActivityForResult(filtrarFacturas, 3);
        }

        final FacturasGeneradas this$0;

        _cls5()
        {
        	super();
        	this$0 = FacturasGeneradas.this;
        }
    }

}
