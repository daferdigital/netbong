// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import com.netbong.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;

public class SeleccionarFecha extends Activity
{

    public SeleccionarFecha()
    {
        oclRegresar = new _cls1();
        oclAceptar = new _cls2();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.seleccionar_fecha_activity_layout);
        findViewById(0x7f060018).setOnClickListener(oclRegresar);
        findViewById(0x7f06017f).setOnClickListener(oclAceptar);
    }

    private android.view.View.OnClickListener oclAceptar;
    private android.view.View.OnClickListener oclRegresar;

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(0);
            finish();
        }

        final SeleccionarFecha this$0;

        _cls1()
        {
        	super();
        	this$0 = SeleccionarFecha.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            DatePicker datepicker = (DatePicker)findViewById(0x7f060173);
            String s = Integer.toString(datepicker.getYear());
            String s1 = (new StringBuilder("00")).append(Integer.toString(1 + datepicker.getMonth())).toString();
            String s2 = Integer.toString(datepicker.getDayOfMonth());
            String s3 = (new StringBuilder(String.valueOf(s))).append('-').append(s1.substring(1)).append('-').append(s2).toString();
            getIntent().putExtra("FECHA", s3);
            setResult(-1, getIntent());
            finish();
        }

        final SeleccionarFecha this$0;

        _cls2()
        {
        	super();
        	this$0 = SeleccionarFecha.this;
        }
    }

}
