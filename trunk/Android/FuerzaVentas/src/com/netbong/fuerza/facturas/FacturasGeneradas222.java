// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterEventosFactura;
import com.netbong.fuerza.adapters.AdapterFacturas;
import com.netbong.fuerza.db.cursores.*;

public class FacturasGeneradas222 extends Activity
{

    public FacturasGeneradas222()
    {
        oclFiltrarFacturas = new _cls1();
        oclSeleccionFactura = new _cls2();
        oclRegistrarCancelacion = new _cls3();
        oclRegistrarDevoluciones = new _cls4();
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
        if(j == -1)
        {
            crEventos.requery();
            lv_eventos.setAdapter(new AdapterEventosFactura(this, crEventos));
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.consulta_facturas_generadas_activity_layout);
        findViewById(0x7f06003b).setOnClickListener(oclRegistrarCancelacion);
        findViewById(0x7f06003a).setOnClickListener(oclRegistrarDevoluciones);
        lv_eventos = (ListView)findViewById(0x7f06003c);
        lv_eventos.setDivider(null);
        lv_eventos.setCacheColorHint(0);
        lv_facturas = (ListView)findViewById(0x7f060038);
        lv_facturas.setCacheColorHint(0);
        crFacturas = MainActivity.mDbHelper.getListadoFacturasGeneradas();
        lv_facturas.setAdapter(new AdapterFacturas(this, crFacturas, oclSeleccionFactura));
        findViewById(0x7f060014).setOnClickListener(oclFiltrarFacturas);
    }

    private static final int INTENT_REQUEST_REGISTRAR_CANCELACION = 1;
    private static final int INTENT_REQUEST_REGISTRAR_DEVOLUCIONES = 2;
    CursorEventosFactura crEventos;
    CursorFacturas crFacturas;
    String documento;
    int factura;
    ListView lv_eventos;
    ListView lv_facturas;
    android.view.View.OnClickListener oclFiltrarFacturas;
    android.view.View.OnClickListener oclRegistrarCancelacion;
    android.view.View.OnClickListener oclRegistrarDevoluciones;
    android.view.View.OnClickListener oclSeleccionFactura;
    Intent regCancelacion;
    Intent regDevoluciones;



    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            FacturasGeneradas222 facturasgeneradas222 = FacturasGeneradas222.this;
            String s = ((EditText)findViewById(0x7f060015)).getText().toString();
            DatePicker datepicker = (DatePicker)findViewById(0x7f06007d);
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(datepicker.getYear());
            aobj[1] = FacturasGeneradas222.formatoMes(1 + datepicker.getMonth());
            aobj[2] = FacturasGeneradas222.formatoDia(datepicker.getDayOfMonth());
            String s1 = String.format("%d-%s-%s", aobj);
            DatePicker datepicker1 = (DatePicker)findViewById(0x7f06007e);
            Object aobj1[] = new Object[3];
            aobj1[0] = Integer.valueOf(datepicker1.getYear());
            aobj1[1] = FacturasGeneradas222.formatoMes(1 + datepicker1.getMonth());
            aobj1[2] = FacturasGeneradas222.formatoDia(datepicker1.getDayOfMonth());
            CursorFacturas cursorfacturas = CursorFacturas.getFacturas(s, s1, String.format("%d-%s-%s", aobj1));
            if(cursorfacturas.getCount() == 0)
                MainActivity.crearMensajeToast(facturasgeneradas222, "No se encontraron coincidencias.", true);
            lv_facturas.setAdapter(new AdapterFacturas(facturasgeneradas222, cursorfacturas, oclSeleccionFactura));
        }

        final FacturasGeneradas222 this$0;

        _cls1()
        {
        	super();
        	this$0 = FacturasGeneradas222.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            factura = view.getId();
            documento = view.getTag().toString();
            if(crEventos != null && !crEventos.isClosed())
                crEventos.close();
            lv_eventos.setAdapter(new AdapterEventosFactura(FacturasGeneradas222.this, crEventos));
            ((TextView)findViewById(0x7f060039)).setText(documento);
        }

        private View lastViewClicked;
        final FacturasGeneradas222 this$0;

        _cls2()
        {
        	super();
        	this$0 = FacturasGeneradas222.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(factura != 0)
            {
                if(regCancelacion == null)
                    regCancelacion = new Intent(FacturasGeneradas222.this, PagosRegistrarNuevo.class);
                regCancelacion.putExtra("FACTURA", factura);
                startActivityForResult(regCancelacion, 1);
            }
        }

        final FacturasGeneradas222 this$0;

        _cls3()
        {
        	super();
        	this$0 = FacturasGeneradas222.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(factura != 0)
            {
                if(regDevoluciones == null)
                    regDevoluciones = new Intent(FacturasGeneradas222.this, RegistrarDevoluciones.class);
                regDevoluciones.putExtra("FACTURA", factura);
                regDevoluciones.putExtra("DOCUMENTO-FACTURA", documento);
                startActivityForResult(regDevoluciones, 2);
            }
        }

        final FacturasGeneradas222 this$0;

        _cls4()
        {
        	super();
        	this$0 = FacturasGeneradas222.this;
        }
    }

}
