// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CursorPedidos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorPedidos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorPedidos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorPedidos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorPedidos cursorpedidos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorPedidos getPedidos(SQLiteDatabase sqlitedatabase)
    {
        String s = MainActivity.mainCtx.getString(0x7f05004c);
        CursorPedidos cursorpedidos = (CursorPedidos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorpedidos.moveToFirst();
        return cursorpedidos;
    }

    public static CursorPedidos getPedidos(SQLiteDatabase sqlitedatabase, int i)
    {
        String s = (new StringBuilder(String.valueOf(MainActivity.mainCtx.getString(0x7f05004a)))).append(Integer.toString(i)).toString();
        CursorPedidos cursorpedidos = (CursorPedidos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorpedidos.moveToFirst();
        return cursorpedidos;
    }

    public static CursorPedidos getPedidos(SQLiteDatabase sqlitedatabase, String s)
    {
        String s1 = MainActivity.mainCtx.getString(0x7f05004b);
        Object aobj[] = new Object[1];
        aobj[0] = (new StringBuilder("'%")).append(s).append("%'").toString();
        String s2 = String.format(s1, aobj);
        CursorPedidos cursorpedidos = (CursorPedidos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s2, null, null);
        cursorpedidos.moveToFirst();
        return cursorpedidos;
    }

    public static CursorPedidos getPedidos(SQLiteDatabase sqlitedatabase, String s, String s1, String s2, String s3, String s4, String s5, String s6)
    {
        StringBuilder stringbuilder = new StringBuilder(MainActivity.mainCtx.getString(0x7f05004d));
        if(s != null && s.length() > 0)
            stringbuilder.append(" and like('%").append(s).append("%', c.nombre)");
        if(s1 != null && s1.length() > 0)
            stringbuilder.append(" and p.status = 0");
        if(s2 != null && s2.length() > 0)
            stringbuilder.append(" and p.status = 1");
        if(s3 != null && s3.length() > 0)
            stringbuilder.append(" and p.status = 3");
        if(s4 != null && s4.length() > 0)
            stringbuilder.append(" and p.status = 2");
        if(s5 != null && s5.length() > 0)
            stringbuilder.append(" and p.fecha > '").append(s5).append("'");
        if(s6 != null && s6.length() > 0)
            stringbuilder.append(" and p.fecha < '").append(s6).append(" 23:59:59'");
        String s7 = stringbuilder.toString();
        CursorPedidos cursorpedidos = (CursorPedidos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s7, null, null);
        cursorpedidos.moveToFirst();
        return cursorpedidos;
    }

    public String getCliente()
    {
        return getString(getColumnIndexOrThrow("cliente"));
    }

    public String getFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getMontoTotal()
    {
        return getDouble(getColumnIndexOrThrow("total"));
    }

    public String getStatus()
    {
        return getString(getColumnIndexOrThrow("status"));
    }

    public static final String COLUMNA_CLIENTE_NOMBRE = "cliente";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_MONTO_TOTAL = "total";
    public static final String COLUMNA_PEDIDO_ID = "_id";
    public static final String COLUMNA_STATUS = "status";
}
