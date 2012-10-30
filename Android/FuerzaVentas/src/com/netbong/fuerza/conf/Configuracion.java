// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.conf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.sincronizar.HttpSync;

public class Configuracion extends Activity
{

    public Configuracion()
    {
        oclAdelantar = new _cls2();
        oclRegresar = new _cls3();
        oclSeleccVendedor = new _cls4();
        oclCancelar = new _cls5();
        oclAceptar = new _cls6();
    }

    private void actualizarParametros()
    {
        String tLogin = ((TextView)findViewById(0x7f060022)).getText().toString().trim();
        if(tLogin.length() == 0)
            tLogin = MainActivity.getDefaultLogin();
        MainActivity.settings.edit().putString("login", tLogin).commit();
        String s;
        final String cod_tablet;
        final String descrip_tablet;
        final String cod_printer;
        final String descrip_printer;
        TextView textview;
        TextView textview1;
        if(tLogin.equalsIgnoreCase(MainActivity.getDefaultLogin()))
            s = MainActivity.getDefaultPassword();
        else
            s = "";
        MainActivity.settings.edit().putString("password", s).commit();
        cod_tablet = ((TextView)findViewById(0x7f060023)).getText().toString();
        MainActivity.settings.edit().putString("tablet-cod", cod_tablet).commit();
        descrip_tablet = ((TextView)findViewById(0x7f060024)).getText().toString();
        MainActivity.settings.edit().putString("tablet-descrip", descrip_tablet).commit();
        cod_printer = ((TextView)findViewById(0x7f060025)).getText().toString();
        MainActivity.settings.edit().putString("printer-cod", cod_printer).commit();
        descrip_printer = ((TextView)findViewById(0x7f060026)).getText().toString();
        MainActivity.settings.edit().putString("printer-descrip", descrip_printer).commit();
        textview = (TextView)findViewById(0x7f060029);
        MainActivity.settings.edit().putString("sync-servidor", textview.getText().toString()).commit();
        textview1 = (TextView)findViewById(0x7f06002a);
        MainActivity.settings.edit().putString("sync-puerto", textview1.getText().toString()).commit();
        (new _cls7(tLogin, cod_tablet, descrip_tablet, cod_printer, descrip_printer)).start();
    }

    private void cargarParametrosActuales()
    {
        ((TextView)findViewById(0x7f060022)).setText(MainActivity.settings.getString("login", MainActivity.getDefaultLogin()));
        ((TextView)findViewById(0x7f060023)).setText(MainActivity.settings.getString("tablet-cod", ""));
        ((TextView)findViewById(0x7f060024)).setText(MainActivity.settings.getString("tablet-descrip", ""));
        ((TextView)findViewById(0x7f060025)).setText(MainActivity.settings.getString("printer-cod", ""));
        ((TextView)findViewById(0x7f060026)).setText(MainActivity.settings.getString("printer-descrip", ""));
        ((TextView)findViewById(0x7f060029)).setText(MainActivity.settings.getString("sync-servidor", ""));
        ((TextView)findViewById(0x7f06002a)).setText(MainActivity.settings.getString("sync-puerto", ""));
    }

    private void cargarVistaParametrosGenerales()
    {
        vistaParametrosServidor.setVisibility(8);
        vistavParametrosGenerales.setVisibility(0);
    }

    private void cargarVistaParametrosServidor()
    {
        vistaParametrosServidor.setVisibility(0);
        vistavParametrosGenerales.setVisibility(8);
    }

    private void listarVendedores()
    {
        if(trama == null || trama.length() == 0)
            MainActivity.crearMensajeToast(this, "No se encontraron vendedores.", true);
        String as[] = trama.split("\r\n");
        RadioGroup radiogroup = (RadioGroup)findViewById(0x7f060021);
        radiogroup.removeAllViews();
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return;
            String as1[] = as[j].split(";");
            RadioButton radiobutton = (RadioButton)View.inflate(this, 0x7f03000c, null);
            radiobutton.setText(as1[1]);
            radiobutton.setTag(as1[0]);
            radiobutton.setOnClickListener(oclSeleccVendedor);
            radiogroup.addView(radiobutton);
            j++;
        } while(true);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.configuracion_ly);
        findViewById(0x7f060012).setOnClickListener(oclAceptar);
        findViewById(0x7f060011).setOnClickListener(oclCancelar);
        findViewById(0x7f060056).setOnClickListener(null);
        findViewById(0x7f060057).setOnClickListener(oclAdelantar);
        vistaParametrosServidor = (LinearLayout)View.inflate(this, 0x7f03000a, null);
        vistavParametrosGenerales = (LinearLayout)View.inflate(this, 0x7f030008, null);
        vistavParametrosGenerales.setVisibility(8);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f060027);
        linearlayout.addView(vistaParametrosServidor);
        linearlayout.addView(vistavParametrosGenerales);
        cargarParametrosActuales();
    }

    final Handler mHandler = new Handler();
    final Runnable mUpdateResults = new _cls1();
    android.view.View.OnClickListener oclAceptar;
    android.view.View.OnClickListener oclAdelantar;
    android.view.View.OnClickListener oclCancelar;
    android.view.View.OnClickListener oclRegresar;
    android.view.View.OnClickListener oclSeleccVendedor;
    String trama;
    LinearLayout vistaParametrosServidor;
    LinearLayout vistavParametrosGenerales;





    private class _cls1
        implements Runnable
    {

        public void run()
        {
            listarVendedores();
        }

        final Configuracion this$0;

        _cls1()
        {
        	super();
        	this$0 = Configuracion.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            cargarVistaParametrosGenerales();
            findViewById(0x7f060056).setOnClickListener(oclRegresar);
            findViewById(0x7f060057).setOnClickListener(null);
            TextView textview = (TextView)findViewById(0x7f060029);
            MainActivity.settings.edit().putString("sync-servidor", textview.getText().toString()).commit();
            TextView textview1 = (TextView)findViewById(0x7f06002a);
            MainActivity.settings.edit().putString("sync-puerto", textview1.getText().toString()).commit();
            class _cls1 extends Thread
            {

                public void run()
                {
                    String s;
                    try
                    {
                        trama = "";
                        trama = HttpSync.vendedoresActivos();
                        mHandler.post(mUpdateResults);
                    }
                    catch(UnsupportedEncodingException unsupportedencodingexception) { }
                    catch(IOException ioexception) { }
                    if(trama == null)
                        s = "no-trama";
                    else
                        s = trama;
                    Log.i("TRAMA", s);
                }

                final _cls2 this$1;

                _cls1()
                {
                	super();
                	this$1 = _cls2.this;
                }
            }

            (new _cls1()).start();
        }

        final Configuracion this$0;


        _cls2()
        {
        	super();
        	this$0 = Configuracion.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            cargarVistaParametrosServidor();
            findViewById(0x7f060056).setOnClickListener(null);
            findViewById(0x7f060057).setOnClickListener(oclAdelantar);
        }

        final Configuracion this$0;

        _cls3()
        {
        	super();
        	this$0 = Configuracion.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            String s = view.getTag().toString();
            ((EditText)findViewById(0x7f060022)).setText(s);
        }

        final Configuracion this$0;

        _cls4()
        {
        	super();
        	this$0 = Configuracion.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final Configuracion this$0;

        _cls5()
        {
        	super();
        	this$0 = Configuracion.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            actualizarParametros();
            MainActivity.crearMensajeToast(Configuracion.this, "Parametros de Configuracion Actualizados.", false);
            setResult(-1);
            oclCancelar.onClick(null);
        }

        final Configuracion this$0;

        _cls6()
        {
        	super();
        	this$0 = Configuracion.this;
        }
    }


    private class _cls7 extends Thread {
    	
        public void run(){
            HttpSync.asignarDispositivoTablet(tLogin, cod_tablet, descrip_tablet);
            HttpSync.asignarDispositivoPrinter(tLogin, cod_printer, descrip_printer);
        }

        final Configuracion this$0;
        private final String cod_printer;
        private final String cod_tablet;
        private final String descrip_printer;
        private final String descrip_tablet;
        private final String tLogin;

        _cls7(String tLogin, String cod_tablet, String descrip_tablet,
        		String cod_printer, String descrip_printer)
        {
        	super();
        	this$0 = Configuracion.this;
            this.tLogin = tLogin;
            this.cod_tablet = cod_tablet;
            this.descrip_tablet = descrip_tablet;
            this.cod_printer = cod_printer;
            this.descrip_printer = descrip_printer;
        }
    }

}
