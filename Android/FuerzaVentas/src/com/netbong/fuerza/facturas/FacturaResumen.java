// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.facturas.db.DBHandle;
import com.netbong.fuerza.facturas.db.cursors.CrsEventos;
import com.netbong.fuerza.facturas.db.cursors.CrsFactura;

public class FacturaResumen extends Activity
{

    public FacturaResumen()
    {
        oclRegistrarPago = new _cls1();
        oclRegresar = new _cls2();
    }

    private void cargarClienteDatos(LinearLayout linearlayout, CrsFactura crsfactura)
    {
        LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f03003f, null);
        ((TextView)linearlayout1.findViewById(0x7f0600a4)).setText(crsfactura.getRif());
        ((TextView)linearlayout1.findViewById(0x7f0600a5)).setText(crsfactura.getNombre());
        ((TextView)linearlayout1.findViewById(0x7f0600a7)).setText(crsfactura.getDireccion());
        idCliente = crsfactura.getId();
        cliente = crsfactura.getNombre();
        rif = crsfactura.getRif();
        linearlayout.addView(linearlayout1);
    }

    private void cargarEventos(LinearLayout linearlayout, CrsEventos crseventos)
    {
        if(crseventos.getCount() != 0)
        {
            linearlayout.removeAllViews();
            do
            {
                LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f030040, null);
                ((TextView)linearlayout1.findViewById(0x7f060073)).setText(crseventos.getEvento());
                ((TextView)linearlayout1.findViewById(0x7f060074)).setText(crseventos.getFecha());
                linearlayout.addView(linearlayout1);
            } while(crseventos.moveToNext());
        }
    }

    private void cargarFacturaDatos(LinearLayout linearlayout, CrsFactura crsfactura)
    {
        LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f030041, null);
        ((TextView)linearlayout1.findViewById(0x7f0600ac)).setText(crsfactura.getFacturaCodref());
        ((TextView)linearlayout1.findViewById(0x7f0600ad)).setText(crsfactura.getFacturaFecha());
        ((TextView)linearlayout1.findViewById(0x7f0600ae)).setText(crsfactura.getFacturaEstatusDsc());
        ((TextView)linearlayout1.findViewById(0x7f0600b0)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(crsfactura.getFacturaTotal())).toString());
        if(crsfactura.getFacturaEstatusCod() == 2)
            findViewById(0x7f0600a1).setVisibility(8);
        linearlayout1.findViewById(0x7f0600a9).setVisibility(8);
        linearlayout1.findViewById(0x7f0600aa).setVisibility(8);
        linearlayout.addView(linearlayout1);
    }

    private void cargarFacturaProductos(LinearLayout linearlayout, CrsFactura crsfactura)
    {
        do
        {
            LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f03003e, null);
            ((TextView)linearlayout1.findViewById(0x7f0600a3)).setText(crsfactura.getItemProductoNombre());
            ((TextView)linearlayout1.findViewById(0x7f060078)).setText(crsfactura.getItemProductoCodref());
            ((TextView)linearlayout1.findViewById(0x7f060060)).setText((new StringBuilder("Cant. ")).append(crsfactura.getItemProductoCantidad()).toString());
            ((TextView)linearlayout1.findViewById(0x7f060061)).setText((new StringBuilder("IVA ")).append(crsfactura.getItemProductoIva()).toString());
            ((TextView)linearlayout1.findViewById(0x7f060062)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(crsfactura.getItemProductoTotal())).toString());
            ((ImageView)linearlayout1.findViewById(0x7f060058)).setImageDrawable(Drawable.createFromPath(crsfactura.getItemProductoImagen()));
            linearlayout.addView(linearlayout1);
        } while(crsfactura.moveToNext());
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1 && i == 1)
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0600a0);
            eventos.requery();
            eventos.moveToFirst();
            cargarEventos(linearlayout, eventos);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.factura_resumen_activity_layout);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
            factura = bundle1.getInt("FACTURA");
        if(bundle1 == null)
            factura = 1;
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0600a0);
        eventos = DBHandle.obtenerEventosFactura(factura);
        cargarEventos(linearlayout, eventos);
        LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f06009f);
        datosFactura = DBHandle.obtenerDatosFactura(factura);
        if(datosFactura.getCount() != 0)
        {
            findViewById(0x7f0600a1).setOnClickListener(oclRegistrarPago);
            findViewById(0x7f060018).setOnClickListener(oclRegresar);
            cargarClienteDatos(linearlayout1, datosFactura);
            cargarFacturaDatos(linearlayout1, datosFactura);
            cargarFacturaProductos(linearlayout1, datosFactura);
        }
    }

    private static final int INTENT_REQUEST_REGISTRAR_PAGO = 1;
    private String cliente;
    CrsFactura datosFactura;
    CrsEventos eventos;
    private int factura;
    private int idCliente;
    private android.view.View.OnClickListener oclRegistrarPago;
    private android.view.View.OnClickListener oclRegresar;
    private Intent registrarPago;
    private String rif;







    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(registrarPago == null)
                registrarPago = new Intent(FacturaResumen.this, PagosRegistrarNuevo.class);
            registrarPago.putExtra("ID", idCliente);
            registrarPago.putExtra("RIF", rif);
            registrarPago.putExtra("CLIENTE", cliente);
            registrarPago.putExtra("ID-FACTURA", factura);
            startActivityForResult(registrarPago, 1);
        }

        final FacturaResumen this$0;

        _cls1()
        {
        	super();
        	this$0 = FacturaResumen.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(0);
            finish();
        }

        final FacturaResumen this$0;

        _cls2()
        {
        	super();
        	this$0 = FacturaResumen.this;
        }
    }

}
