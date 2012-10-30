// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.sincronizar;

import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.pedidos.sync.TramaPedido;
import com.netbong.fuerza.pedidos.sync.exception.SyncPedidoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public abstract class HttpSync
{

    public HttpSync()
    {
    }

    private static final String asignarDispositivo(String s, Integer integer, String s1, String s2)
        throws UnsupportedEncodingException, IOException
    {
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-asignacion-dispositivo-vendedor"));
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("vendedor", s));
        arraylist.add(new BasicNameValuePair("tipo-disposi", integer.toString()));
        arraylist.add(new BasicNameValuePair("cod-disposi", s1));
        arraylist.add(new BasicNameValuePair("descrip-disposi", s2));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        String s3;
        if(httpentity != null)
            s3 = EntityUtils.toString(httpentity);
        else
            s3 = null;
        return s3;
    }

    public static final boolean asignarDispositivoPrinter(String s, String s1, String s2) {
    	boolean flag = false;
    	String s3 = null;
        
        try {
        	String s4 = asignarDispositivo(s, Integer.valueOf(2), s1, s2);
            s3 = s4;
            
            if(s3 == null)
                flag = false;
            else
                flag = s3.equalsIgnoreCase("OK!\r\n");
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        return flag;
    }

    public static final boolean asignarDispositivoTablet(String s, String s1, String s2) {
    	boolean flag = false;
        String s3 = null;
        
        try {
        	String s4 = asignarDispositivo(s, Integer.valueOf(1), s1, s2);
            s3 = s4;
            if(s3 == null)
                flag = false;
            else
                flag = s3.equalsIgnoreCase("OK!\r\n");
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        return flag;
    }

    public static final String autorizacionPrecio(String s, int i)
        throws UnsupportedEncodingException, IOException
    {
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-autorizaciones-precios"));
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("vendedor", s));
        arraylist.add(new BasicNameValuePair("id-producto", Integer.toString(i)));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        String s1;
        if(httpentity != null)
            s1 = EntityUtils.toString(httpentity);
        else
            s1 = null;
        return s1;
    }

    private static final StringEntity construirStringEntity(String s)
        throws UnsupportedEncodingException
    {
        StringEntity stringentity = new StringEntity(s);
        stringentity.setContentType("text/html;charset=UTF-8");
        return stringentity;
    }

    private static final String contruirSyncUri(String s)
    {
        String s1 = MainActivity.settings.getString("sync-servidor", "10.0.2.2");
        String s2 = MainActivity.settings.getString("sync-puerto", "8080");
        Object aobj[] = new Object[3];
        aobj[0] = s1;
        aobj[1] = s2;
        aobj[2] = s;
        return String.format("http://%s:%s/FuerzaVentas/%s", aobj);
    }

    public static final void descargarImagen(String s)
        throws UnsupportedEncodingException, IOException
    {
        String s1;
        HttpEntity httpentity;
        s1 = s.split(";")[2];
        Log.i("imagen-descar", s1);
        HttpPost httppost = new HttpPost(contruirSyncUri((new StringBuilder("imgs/")).append(s1).toString()));
        httpentity = httpClient.execute(httppost).getEntity();
        
        if(httpentity == null || !httpentity.isStreaming()) {
        	/**/
        } else {
        	BufferedOutputStream bufferedoutputstream = null;
            byte abyte0[] = null;
            BufferedInputStream bufferedinputstream = null;
            
        	try {
        		bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(new File(new File(Environment.getExternalStorageDirectory(), "droidsf"), s1.split("/")[1])), 8192);
                abyte0 = new byte[8192];
                bufferedinputstream = new BufferedInputStream(httpentity.getContent());
                int i = -1;
                while((i = bufferedinputstream.read(abyte0, 0, 8192)) != -1){
                	bufferedoutputstream.write(abyte0, 0, i);
                }
			} finally {
				// TODO: handle exception
				bufferedoutputstream.flush();
		        bufferedoutputstream.close();
		        bufferedinputstream.close();
			}
        }
    }

    public static final boolean enviarCancelaciones(String s)
        throws UnsupportedEncodingException, IOException
    {
        boolean flag = false;
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-upload-registro-pagos"));
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("sync-trama", s));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        if(httpentity != null)
        {
            String s1 = EntityUtils.toString(httpentity);
            if(s1 != null)
                flag = s1.equalsIgnoreCase("OK!\r\n");
        }
        return flag;
    }

    public static boolean enviarDatosNuevoCliente(String s)
        throws UnsupportedEncodingException, IOException
    {
        boolean flag = false;
        Log.i("HttpPost", s);
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-clientes"));
        httppost.setEntity(construirStringEntity(s));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        if(httpentity != null)
        {
            String s1 = EntityUtils.toString(httpentity);
            if(s1 != null)
                flag = s1.equalsIgnoreCase("OK!\r\n");
        }
        return flag;
    }

    public static String enviarDatosPedidoGenerado(TramaPedido tramapedido)
        throws SyncPedidoException
    {
        String s = tramapedido.getTrama();
        Log.i("HttpPost", s);
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-pedidos"));
        HttpResponse httpresponse;
        String s1;
        HttpEntity httpentity;
        try
        {
            httppost.setEntity(construirStringEntity(s));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new SyncPedidoException(unsupportedencodingexception.getLocalizedMessage());
        }
        try
        {
            httpresponse = httpClient.execute(httppost);
        }
        catch(ClientProtocolException clientprotocolexception)
        {
            throw new SyncPedidoException(clientprotocolexception.getLocalizedMessage());
        }
        catch(IOException ioexception)
        {
            throw new SyncPedidoException("Conexion interrumpida enviando datos hacia el servidor.");
        }
        s1 = null;
        httpentity = httpresponse.getEntity();
        if(httpentity != null)
        {
            String s2;
            try
            {
                s2 = EntityUtils.toString(httpentity);
            }
            catch(ParseException parseexception)
            {
                throw new SyncPedidoException(parseexception.getLocalizedMessage());
            }
            catch(IOException ioexception1)
            {
                throw new SyncPedidoException("Conexion interrumpida recibiendo datos desde el servidor.");
            }
            s1 = s2;
        }
        return s1;
    }

    public static boolean enviarDatosPedidoGenerado(String s)
        throws UnsupportedEncodingException, IOException
    {
        boolean flag = false;
        Log.i("HttpPost", s);
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-pedidos"));
        httppost.setEntity(construirStringEntity(s));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        if(httpentity != null)
        {
            String s1 = EntityUtils.toString(httpentity);
            if(s1 != null)
                flag = s1.equalsIgnoreCase("OK!\r\n");
        }
        return flag;
    }

    public static final void enviarDevolucionArchivos(String s, int i, String s1)
        throws UnsupportedEncodingException, IOException
    {
        Log.i("RUTA", s);
        DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-upload-devoluciones-imagenes"));
        FileBody filebody = new FileBody(new File(s));
        MultipartEntity multipartentity = new MultipartEntity();
        multipartentity.addPart(s1, filebody);
        httppost.setEntity(multipartentity);
        HttpEntity httpentity = defaulthttpclient.execute(httppost).getEntity();
        if(httpentity != null)
            EntityUtils.toString(httpentity);
    }

    public static final boolean enviarDevoluciones(int i, int j, String s, int k, String s1, String s2)
        throws UnsupportedEncodingException, IOException
    {
        boolean flag = false;
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-upload-registro-devoluciones"));
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("id-factura", Integer.toString(i)));
        arraylist.add(new BasicNameValuePair("id-producto", Integer.toString(j)));
        arraylist.add(new BasicNameValuePair("fecha", s));
        arraylist.add(new BasicNameValuePair("unidades", Integer.toString(k)));
        arraylist.add(new BasicNameValuePair("observacion", s1));
        arraylist.add(new BasicNameValuePair("referencia", s2));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        if(httpentity != null)
        {
            String s3 = EntityUtils.toString(httpentity);
            if(s3 != null)
                flag = s3.equalsIgnoreCase("OK!\r\n");
        }
        return flag;
    }

    public static final String eventosPendientes(String s)
        throws UnsupportedEncodingException, IOException
    {
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-eventos-pendientes"));
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("vendedor", s));
        arraylist.add(new BasicNameValuePair("fecha", "01/01/2011"));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        String s1;
        if(httpentity != null)
            s1 = EntityUtils.toString(httpentity);
        else
            s1 = null;
        return s1;
    }

    public static final String eventosPendientes(String s, String s1)
        throws UnsupportedEncodingException, IOException
    {
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-eventos-pendientes-descargar"));
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("vendedor", s));
        arraylist.add(new BasicNameValuePair("fecha", "01/01/2011"));
        arraylist.add(new BasicNameValuePair("clasificacion", s1));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        String s2;
        if(httpentity != null)
            s2 = EntityUtils.toString(httpentity);
        else
            s2 = null;
        return s2;
    }

    public static final void eventosPendientesConfirmacion(String s, String s1)
        throws UnsupportedEncodingException, IOException
    {
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-eventos-pendientes-confirmacion"));
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("vendedor", s));
        arraylist.add(new BasicNameValuePair("clasificacion", s1));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        if(httpentity != null)
            EntityUtils.toString(httpentity);
    }

    private DefaultHttpClient getHttpClient()
    {
        return httpClient;
    }

    public static final String vendedoresActivos()
        throws UnsupportedEncodingException, IOException
    {
        HttpPost httppost = new HttpPost(contruirSyncUri("sync-vendedores-activos"));
        HttpEntity httpentity = httpClient.execute(httppost).getEntity();
        String s;
        if(httpentity != null)
            s = EntityUtils.toString(httpentity);
        else
            s = null;
        return s;
    }

    private static final int NETWORK_CONNECTION_TIMEOUT = 30000;
    public static final String SYNC_STATUS_OK = "OK!\r\n";
    private static final String SYNC_URI_ASIGNAR_DISPISITIVO = "sync-asignacion-dispositivo-vendedor";
    private static final String SYNC_URI_AUTORIZACIONES_PRECIOS = "sync-autorizaciones-precios";
    private static final String SYNC_URI_BAJAR_IMAGEN_CATALOGO = "imgs/";
    private static final String SYNC_URI_ENVIAR_CANCELACION = "sync-upload-registro-pagos";
    private static final String SYNC_URI_ENVIAR_CLIENTE = "sync-clientes";
    private static final String SYNC_URI_ENVIAR_DEVOLUCION = "sync-upload-registro-devoluciones";
    private static final String SYNC_URI_ENVIAR_DEVOLUCION_IMAGEN = "sync-upload-devoluciones-imagenes";
    private static final String SYNC_URI_ENVIAR_PEDIDO = "sync-pedidos";
    private static final String SYNC_URI_EVENTOS_PENDIENTES = "sync-eventos-pendientes";
    private static final String SYNC_URI_EVENTOS_PENDIENTES_BAJAR = "sync-eventos-pendientes-descargar";
    private static final String SYNC_URI_EVENTOS_PENDIENTES_CONFIRMACION = "sync-eventos-pendientes-confirmacion";
    private static final String SYNC_URI_PATTERN = "http://%s:%s/FuerzaVentas/%s";
    private static final String SYNC_URI_VENDEDORES_ACTIVOS = "sync-vendedores-activos";
    private static DefaultHttpClient httpClient = new DefaultHttpClient();

    static 
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 30000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
        HttpConnectionParams.setStaleCheckingEnabled(basichttpparams, false);
    }
}
