// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

public class CursorSyncDatosPedidoEventos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorSyncDatosPedidoEventos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorSyncDatosPedidoEventos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorSyncDatosPedidoEventos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorSyncDatosPedidoEventos cursorsyncdatospedidoeventos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorSyncDatosPedidoEventos getDatosPedidoEvento(SQLiteDatabase sqlitedatabase, int i)
    {
        Factory factory = new Factory(null);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        CursorSyncDatosPedidoEventos cursorsyncdatospedidoeventos = (CursorSyncDatosPedidoEventos)sqlitedatabase.rawQueryWithFactory(factory, String.format("select date(fecha) as fecha, time(fecha) as hora, evento from  Pedidos_Eventos where id_pedido = %d ", aobj), null, null);
        cursorsyncdatospedidoeventos.moveToFirst();
        return cursorsyncdatospedidoeventos;
    }

    private String getDescripcionEvento()
    {
        return getString(getColumnIndexOrThrow("evento"));
    }

    private String getEvento()
    {
        Object aobj[] = new Object[2];
        aobj[0] = getFecha();
        aobj[1] = getDescripcionEvento();
        return String.format("%s;;%s", aobj);
    }

    private String getFecha()
    {
        String s = getString(getColumnIndexOrThrow("fecha")).substring(0, 10);
        Log.i("fecha registro pedido", s);
        String as[] = s.split("-");
        return (new StringBuilder(String.valueOf(as[2]))).append("-").append(as[1]).append("-").append(as[0]).toString();
    }

    private String getHora()
    {
        return getString(getColumnIndexOrThrow("hora"));
    }

    public String construirTramaEvento()
    {
        StringBuilder stringbuilder = null;
        String s = null;
        
        if(getCount() > 0){
        	stringbuilder = new StringBuilder(getEvento());
        
        	while(moveToNext()){
        		stringbuilder.append("==").append(getEvento());
        	}
        	
        	Log.i("SYNC-TRAMA-EVENTO", stringbuilder.toString());
            s = stringbuilder.toString();
        }
        
        return s;
    }

    public static final String COLUMNA_DESCRIPCION_EVENTO = "evento";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_HORA = "hora";
    private static final String QUERY = "select date(fecha) as fecha, time(fecha) as hora, evento from  Pedidos_Eventos where id_pedido = %d ";
}
