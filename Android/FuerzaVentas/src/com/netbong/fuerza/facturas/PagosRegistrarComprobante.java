// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.facturas.db.DBHandle;
import com.netbong.fuerza.facturas.db.cursors.CsrComprobantesIva;

public class PagosRegistrarComprobante extends Activity
{

    public PagosRegistrarComprobante()
    {
        oclGuardarComprobante = new _cls1();
        oclRegresar = new _cls2();
    }

    private void cargarComprobantes()
    {
        CsrComprobantesIva csrcomprobantesiva = DBHandle.obtenerComprobantes();
        if(csrcomprobantesiva.getCount() > 0)
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06016d);
            do
            {
                LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f03007c, null);
                ((TextView)linearlayout1.findViewById(0x7f0600ac)).setText(csrcomprobantesiva.getFactura());
                ((TextView)linearlayout1.findViewById(0x7f06016f)).setText(csrcomprobantesiva.getComprobante());
                ((TextView)linearlayout1.findViewById(0x7f060170)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(csrcomprobantesiva.getMontoBase())).toString());
                ((TextView)linearlayout1.findViewById(0x7f060171)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(csrcomprobantesiva.getMontoRetenido())).toString());
                linearlayout.addView(linearlayout1);
            } while(csrcomprobantesiva.moveToNext());
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.registrar_pagos_comprobante_activity_layout);
        findViewById(0x7f06016e).setOnClickListener(oclGuardarComprobante);
        findViewById(0x7f060018).setOnClickListener(oclRegresar);
        cargarComprobantes();
    }

    private android.view.View.OnClickListener oclGuardarComprobante;
    private android.view.View.OnClickListener oclRegresar;

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06016d);
            int i = linearlayout.getChildCount();
            int j = 0;
            do
            {
                if(j >= i)
                {
                    setResult(-1);
                    finish();
                    return;
                }
                View view1 = linearlayout.getChildAt(j);
                String s = ((TextView)view1.findViewById(0x7f06016f)).getText().toString();
                DBHandle.comprobanteIvaActualizarDatos(((TextView)view1.findViewById(0x7f0600ac)).getText().toString(), s);
                j++;
            } while(true);
        }

        final PagosRegistrarComprobante this$0;

        _cls1()
        {
        	super();
        	this$0 = PagosRegistrarComprobante.this;
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

        final PagosRegistrarComprobante this$0;

        _cls2()
        {
        	super();
        	this$0 = PagosRegistrarComprobante.this;
        }
    }

}
