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

public class DialogoSiNo extends Activity
{

    public DialogoSiNo()
    {
        ocl_si = new _cls1();
        ocl_no = new _cls2();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialogo_si_no);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            String s = bundle1.getString("MENSAJE");
            ((TextView)findViewById(0x7f060099)).setText(s);
        }
        findViewById(0x7f06009b).setOnClickListener(ocl_si);
        findViewById(0x7f06009c).setOnClickListener(ocl_no);
    }

    private android.view.View.OnClickListener ocl_no;
    private android.view.View.OnClickListener ocl_si;

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(-1);
            finish();
        }

        final DialogoSiNo this$0;

        _cls1()
        {
        	super();
        	this$0 = DialogoSiNo.this;
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

        final DialogoSiNo this$0;

        _cls2()
        {
        	super();
        	this$0 = DialogoSiNo.this;
        }
    }

}
