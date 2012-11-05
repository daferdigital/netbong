// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.dialogos;

import com.netbong.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DialogoAceptar extends Activity
{

    public DialogoAceptar()
    {
        ocl_aceptar = new _cls1();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialogo_aceptar);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            String s = bundle1.getString("MENSAJE");
            ((TextView)findViewById(0x7f060099)).setText(s);
        }
        findViewById(0x7f060012).setOnClickListener(ocl_aceptar);
    }

    private android.view.View.OnClickListener ocl_aceptar;

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(-1);
            finish();
        }

        final DialogoAceptar this$0;

        _cls1()
        {
        	super();
        	this$0 = DialogoAceptar.this;
        }
    }

}
