// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.widget.Toast;
import com.netbong.fuerza.conf.Configuracion;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;
import com.netbong.fuerza.dialogos.DialogoAceptar;
import com.netbong.fuerza.dialogos.DialogoCapturaTexto;
import com.netbong.fuerza.dialogos.DialogoSiNo;
import com.netbong.fuerza.util.UnPackResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

// Referenced classes of package com.ehp.droidsf:
//            AutenticacionCuenta

public class MainActivity extends Activity
{

    public MainActivity()
    {
    	Log.i("MainActivity Constructor", "INICIANDO");
    }

    private void autenticarUsusrio()
    {
        startActivity(new Intent(this, AutenticacionCuenta.class));
        finish();
    }

    private void configurarEntornoEjecucion()
    {
        settings.edit().putString("login", getDefaultLogin()).commit();
        settings.edit().putString("password", getDefaultPassword()).commit();
        settings.edit().putString("tablet-cod", getCodigoTablet()).commit();
        settings.edit().putString("tablet-descrip", getDescripcionTablet()).commit();
        settings.edit().putString("printer-cod", getCodigoPrinter()).commit();
        settings.edit().putString("printer-descrip", getDescripcionPrinter()).commit();
        settings.edit().putString("sync-servidor", getIPServidor()).commit();
        settings.edit().putString("sync-puerto", getPuertoServidor()).commit();
        crearBaseDatos();
        crearCatalogo();
        startActivityForResult(new Intent(this, Configuracion.class), 1);
    }

    private int crearBaseDatos()
    {
        try
        {
            UnPackResource.unpackDatabase(mainCtx, "db.zip");
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Log.e("crear database", filenotfoundexception.getMessage());
        }
        catch(IOException ioexception)
        {
            Log.i("crear database", ioexception.getMessage());
        }
        return 0;
    }

    private int crearCatalogo()
    {
        try
        {
            UnPackResource.unpackCatalogo(mainCtx, "catalogo.zip");
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Log.i("crear catalogo", filenotfoundexception.getMessage());
        }
        catch(IOException ioexception)
        {
            Log.i("crear catalogo", ioexception.getMessage());
        }
        return 0;
    }

    public static void crearMensajeToast(Context context, String s, boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        Toast.makeText(context, s, i).show();
    }

    public static final String formatVE(double d)
    {
        return nf.format(d);
    }

    private String getCodigoPrinter()
    {
        return "MZ-320-01";
    }

    private String getCodigoTablet()
    {
        return "XOOM-01";
    }

    public static String getDefaultLogin()
    {
        return "netbong";
    }

    public static String getDefaultPassword()
    {
        return "123456";
    }

    private String getDescripcionPrinter()
    {
        return "Zebra Printer MZ-320";
    }

    private String getDescripcionTablet()
    {
        return "Motorola Xoom";
    }

    private String getIPServidor()
    {
        return "201.210.241.37";
    }

    public static Intent getItentDialogueAccept(String s)
    {
        if(DIALOGO_ACEPTAR == null)
            DIALOGO_ACEPTAR = new Intent(mainCtx, DialogoAceptar.class);
        DIALOGO_ACEPTAR.putExtra("MENSAJE", s);
        return DIALOGO_ACEPTAR;
    }

    public static Intent getItentDialogueInput(String s)
    {
        if(DIALOGO_CAPTURA_TEXTO == null)
            DIALOGO_CAPTURA_TEXTO = new Intent(mainCtx, DialogoCapturaTexto.class);
        DIALOGO_CAPTURA_TEXTO.putExtra("MENSAJE", s);
        return DIALOGO_CAPTURA_TEXTO;
    }

    public static Intent getItentDialogueYesNoQuestion(String s)
    {
        if(DIALOGO_SI_NO == null)
            DIALOGO_SI_NO = new Intent(mainCtx, DialogoSiNo.class);
        DIALOGO_SI_NO.putExtra("MENSAJE", s);
        return DIALOGO_SI_NO;
    }

    private String getPuertoServidor()
    {
        return "8080";
    }

    @SuppressLint("NewApi")
	public static final boolean isValidEmail(CharSequence charSequence) {
    	boolean bool1;
        if ((charSequence == null) || (charSequence.length() == 0)){
        	bool1 = true;
        }else{
        	try {
              boolean bool2 = Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
              bool1 = bool2;
            } catch (NullPointerException localNullPointerException) {
              bool1 = false;
            }
        }
          
        return bool1;
    }

    @SuppressLint("NewApi")
	public static final boolean isValidPhone(CharSequence charSequence)
    {
    	boolean bool1;
        if ((charSequence == null) || (charSequence.length() == 0)){
        	bool1 = true;
        }else{
        	try {
              boolean bool2 = Patterns.PHONE.matcher(charSequence).matches();
              bool1 = bool2;
            } catch (NullPointerException localNullPointerException) {
              bool1 = false;
            }
        }
          
        return bool1;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j != -1)
            crearMensajeToast(this, "Establecido Valores Prederterminados en la Configuracion.", true);
        if(j == -1 && i == 1)
            settings.edit().putBoolean("entornoConfiguado", true).commit();
        autenticarUsusrio();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mainCtx = this;
        settings = getPreferences(0);
        if(!settings.getBoolean("entornoConfiguado", false))
            configurarEntornoEjecucion();
        else
            autenticarUsusrio();
        mDbHelper = DroidSFDatabase.getDroidSFDatabase(mainCtx);
    }

    private static Intent DIALOGO_ACEPTAR;
    private static Intent DIALOGO_CAPTURA_TEXTO;
    private static Intent DIALOGO_SI_NO;
    private static final int INTENT_REQUEST_ESTABLECER_PARAMETROS_CONFIGURACION = 1;
    public static final String SETTING_CODIGO_DISPOSITIVO_PRINTER = "printer-cod";
    public static final String SETTING_CODIGO_DISPOSITIVO_TABLET = "tablet-cod";
    public static final String SETTING_DATABASE_FILE_PATH = "db-path";
    public static final String SETTING_DESCRIPC_DISPOSITIVO_PRINTER = "printer-descrip";
    public static final String SETTING_DESCRIPC_DISPOSITIVO_TABLET = "tablet-descrip";
    public static final String SETTING_ESTADO_CONFIGURACION = "entornoConfiguado";
    public static final String SETTING_IMAGENES_DIRECTORY_NAME = "droidsf";
    public static final String SETTING_SYNC_SERVIDOR_PORT = "sync-puerto";
    public static final String SETTING_SYNC_SERVIDOR_URI = "sync-servidor";
    public static final String SETTING_SYNC_URL_EVENTOS_PENDIENTES_CONFIRMAR_APLICACION = "/sync-eventos-pendientes-confirmacion";
    public static final String SETTING_SYNC_URL_EVENTOS_PENDIENTES_CONSULTAR = "/sync-eventos-pendientes";
    public static final String SETTING_SYNC_URL_EVENTOS_PENDIENTES_DESCARGAR = "/sync-eventos-pendientes-descargar";
    public static final String SETTING_SYNC_URL_PEDIDOS_ENVIAR = "/sync-pedidos";
    public static final String SETTING_USUARIO_CONTRASED1A = "password";
    public static final String SETTING_USUARIO_LOGIN = "login";
    public static DroidSFDatabase mDbHelper;
    public static Context mainCtx;
    private static final NumberFormat nf;
    public static SharedPreferences settings;

    static 
    {
        nf = NumberFormat.getInstance(new Locale("es", "VE"));
        nf.setMinimumFractionDigits(2);
    }
}
