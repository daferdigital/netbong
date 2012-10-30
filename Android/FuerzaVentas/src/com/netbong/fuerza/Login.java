// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import com.netbong.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Referenced classes of package com.ehp.droidsf:
//            OpcionesMenuPrincial

public class Login extends Activity
{

    public Login()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.login_layout);
        i = new Intent(this, OpcionesMenuPrincial.class);
        ((Button)findViewById(0x7f0600d9)).setOnClickListener(new _cls1());
    }

    Intent i;

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(i);
        }

        final Login this$0;

        _cls1()
        {
        	super();
        	this$0 = Login.this;
        }
    }

}
