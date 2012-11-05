// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.androidhive.dashboard.AndroidDashboardDesignActivity;
import com.netbong.R;
import com.netbong.fuerza.conf.EstablecerContrasena;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity, OpcionesMenuPrincial

public class AutenticacionCuenta extends Activity
    implements android.view.View.OnClickListener
{

    public AutenticacionCuenta()
    {
    }

    private boolean cuentaValida(String s, String s1)
    {
        return s1.equals(MainActivity.settings.getString("password", "SETTING_USUARIO_CONTRASE\321A"));
    }

    private boolean passwordEstablecido()
    {
        boolean flag;
        if(MainActivity.settings.getString("password", "").length() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onClick(View view)
    {
        if(cuentaValida(((TextView)findViewById(0x7f0600d6)).getText().toString(), ((TextView)findViewById(0x7f0600d8)).getText().toString()))
        {
            //startActivity(new Intent(this, OpcionesMenuPrincial.class));
           
        	startActivity(new Intent(this,AndroidDashboardDesignActivity.class));
        	
        	
            setResult(-1);
            finish();
        } else
        {
            MainActivity.crearMensajeToast(this, "Combinaci\363n usuario/contrase\361a errada!!", true);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
      //Quitamos barra de titulo de la aplicacion
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       // requestWindowFeature(1);
        setContentView(R.layout.login_layout);
        if(!passwordEstablecido())
            startActivity(new Intent(this, EstablecerContrasena.class));
        findViewById(0x7f0600d9).setOnClickListener(this);
        String s = MainActivity.settings.getString("login", MainActivity.getDefaultLogin());
        ((TextView)findViewById(0x7f0600d6)).setText(s);
    }
}
