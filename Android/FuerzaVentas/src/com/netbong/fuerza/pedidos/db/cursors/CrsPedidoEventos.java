// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CrsPedidoEventos extends SQLiteCursor
{
    static enum Colums {
    	fecha,
    	hora,
    	evento;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsPedidoEventos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsPedidoEventos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsPedidoEventos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsPedidoEventos crspedidoeventos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsPedidoEventos getPedidoEventos(int i)
    {
        String s = MainActivity.mainCtx.getString(0x7f05001f);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsPedidoEventos crspedidoeventos = (CrsPedidoEventos)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crspedidoeventos.moveToFirst();
        return crspedidoeventos;
    }

    public String getEventoDescripcion()
    {
        return getString(getColumnIndexOrThrow(Colums.evento.toString()));
    }

    public String getEventoFecha()
    {
        return getString(getColumnIndexOrThrow(Colums.fecha.toString()));
    }

    public String getEventoHora()
    {
        return getString(getColumnIndexOrThrow(Colums.hora.toString()));
    }
}
