// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.conf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;

public class EstablecerContrasena extends Activity
{

    public EstablecerContrasena()
    {
        oclCancelar = new _cls1();
        oclAceptar = new _cls2();
    }

    private void validarDatos()
    {
        String s = ((TextView)findViewById(0x7f06009d)).getText().toString();
        if(s.equals(((TextView)findViewById(0x7f06009e)).getText().toString()))
        {
            MainActivity.settings.edit().putString("password", s).commit();
            MainActivity.crearMensajeToast(this, "Contrase\361a establecida correctamente.", true);
            setResult(-1);
            oclCancelar.onClick(null);
        } else
        {
            MainActivity.crearMensajeToast(this, "La contrase\361a introducida no es valida.", true);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.establecer_contrasena_ly);
        findViewById(0x7f060012).setOnClickListener(oclAceptar);
        findViewById(0x7f060011).setOnClickListener(oclCancelar);
        String s = MainActivity.settings.getString("login", MainActivity.getDefaultLogin());
        ((TextView)findViewById(0x7f060022)).setText(s);
    }

    android.view.View.OnClickListener oclAceptar;
    android.view.View.OnClickListener oclCancelar;


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final EstablecerContrasena this$0;

        _cls1()
        {
        	super();
        	this$0 = EstablecerContrasena.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            validarDatos();
        }

        final EstablecerContrasena this$0;

        _cls2()
        {
        	super();
        	this$0 = EstablecerContrasena.this;
        }
    }

}
