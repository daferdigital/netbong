// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.sync;

import android.os.AsyncTask;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.CursorSyncDatosPedido;
import com.netbong.fuerza.pedidos.sync.exception.SyncPedidoException;
import com.netbong.fuerza.sincronizar.HttpSync;

import java.util.ArrayList;

// Referenced classes of package com.ehp.droidsf.pedidos.sync:
//            TramaPedido

public class AsyncTaskSyncPedido extends AsyncTask
{

    public AsyncTaskSyncPedido()
    {
    }

    protected  Object doInBackground(Object aobj[])
    {
        return doInBackground((Integer[])aobj);
    }

    protected String[] doInBackground(Integer ainteger[]) {
        String s = MainActivity.settings.getString("login", MainActivity.getDefaultLogin());
        String s1 = MainActivity.settings.getString("tablet-descrip", "NONE-DEVICE");
        ArrayList arraylist = new ArrayList();
        int i = ainteger.length;
        int j = 0;
        int k;
        CursorSyncDatosPedido cursorsyncdatospedido;
        String s2;
        TramaPedido tramapedido;
        Object aobj[];
        String s3;
        Object aobj1[];
        String as[];
        
        do
        {
            if(j >= i)
            {
                if(arraylist != null)
                    as = (String[])arraylist.toArray(new String[0]);
                else
                    as = null;
                return as;
            }
            k = ainteger[j].intValue();
            cursorsyncdatospedido = MainActivity.mDbHelper.getSyncDatosPedido(k, s, s1);
            s2 = cursorsyncdatospedido.construirTramaPedido().toString();
            cursorsyncdatospedido.moveToFirst();
            tramapedido = new TramaPedido(cursorsyncdatospedido.getClienteNombre(), cursorsyncdatospedido.getFechaString(), s2);
            try
            {
                s3 = HttpSync.enviarDatosPedidoGenerado(tramapedido);
                if(s3.equalsIgnoreCase("OK!\r\n"))
                    MainActivity.mDbHelper.marcarComoSyncPedido(k);
                aobj1 = new Object[3];
                aobj1[0] = s3.trim();
                aobj1[1] = tramapedido.getFecha();
                aobj1[2] = tramapedido.getClinete();
                arraylist.add(String.format("%s - Fecha: [%s]::Cliente: [%s]", aobj1));
            }
            // Misplaced declaration of an exception variable
            catch(SyncPedidoException syncpedidoexception)
            {
                aobj = new Object[3];
                aobj[0] = syncpedidoexception.getMessage();
                aobj[1] = tramapedido.getFecha();
                aobj[2] = tramapedido.getClinete();
                arraylist.add(String.format("%s - Fecha: [%s]::Cliente: [%s]", aobj));
            }
            cursorsyncdatospedido.deactivate();
            cursorsyncdatospedido.close();
            j++;
        } while(true);
    }

    private static final String formatoMensaje = "%s - Fecha: [%s]::Cliente: [%s]";
}
