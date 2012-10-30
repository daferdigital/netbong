// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.sincronizar;

import android.util.Log;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class SyncNotificacionFacturas
{

    public SyncNotificacionFacturas()
    {
    }

    public static final boolean actualizar(String s)
    {
        String as[] = s.split("\r\n");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return true;
            String s1 = construitStatement(as[j]);
            String s2;
            if(s1 == null)
                s2 = "no implementado";
            else
                s2 = s1;
            Log.i("sync-sql", s2);
            if(s1 != null)
                MainActivity.mDbHelper.execSQL(s1);
            j++;
        } while(true);
    }

    private static final String construitStatement(String s)
    {
        String as[] = s.split(";");
        sb.delete(0, sb.length());
        sb.append("INSERT INTO Facturas_Eventos(id_factura, evento, fecha) values(");
        sb.append(as[0]).append(",");
        sb.append(as[1]).append(",");
        sb.append(as[2]).append(");");
        return sb.toString();
    }

    private static final int INDICE_CAMPO_FECHA = 2;
    private static final int INDICE_CAMPO_ID_FACTURA = 0;
    private static final int INDICE_CAMPO_OBSERVACION = 1;
    private static StringBuilder sb = new StringBuilder();

}
