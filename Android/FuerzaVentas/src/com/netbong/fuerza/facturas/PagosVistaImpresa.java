// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import com.netbong.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PagosVistaImpresa extends Activity
{

    public PagosVistaImpresa()
    {
        oclRegresar = new _cls1();
        oclImprimir = new _cls2();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.pagos_vista_impresa_activity_layout);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            String s = bundle1.getString("DATO");
            ((TextView)findViewById(0x7f060083)).setText(s);
        }
        findViewById(0x7f060018).setOnClickListener(oclRegresar);
        findViewById(0x7f0600ef).setOnClickListener(oclImprimir);
    }

    private android.view.View.OnClickListener oclImprimir;
    private android.view.View.OnClickListener oclRegresar;

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(0);
            finish();
        }

        final PagosVistaImpresa this$0;

        _cls1()
        {
        	super();
        	this$0 = PagosVistaImpresa.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
        }

        final PagosVistaImpresa this$0;

        _cls2()
        {
        	super();
        	this$0 = PagosVistaImpresa.this;
        }
    }

}
