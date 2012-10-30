// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorEventosPedido extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorEventosPedido(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorEventosPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorEventosPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorEventosPedido cursoreventospedido)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorEventosPedido getEventos(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("SELECT pe._id, pe.fecha, pe.evento FROM Pedidos_Eventos pe WHERE pe.id_pedido = %d ORDER BY pe._id ", aobj);
        CursorEventosPedido cursoreventospedido = (CursorEventosPedido)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursoreventospedido.moveToFirst();
        return cursoreventospedido;
    }

    public String getEvento()
    {
        return getString(getColumnIndexOrThrow("evento"));
    }

    public String getFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public static final String COLUMNA_EVENTO = "evento";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String QUERY = "SELECT pe._id, pe.fecha, pe.evento FROM Pedidos_Eventos pe WHERE pe.id_pedido = %d ORDER BY pe._id ";
}
