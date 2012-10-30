// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CrsEventos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsEventos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsEventos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsEventos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsEventos crseventos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsEventos getEventos(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("SELECT fe._id, fe.fecha, fe.evento FROM Facturas f, Facturas_Eventos fe WHERE f._id = %d and f.codref_factura = fe.codref_factura ORDER BY fe._id", aobj);
        CrsEventos crseventos = (CrsEventos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        crseventos.moveToFirst();
        return crseventos;
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
    public static final String COLUMNA_ID = "_id";
    public static final String QUERY = "SELECT fe._id, fe.fecha, fe.evento FROM Facturas f, Facturas_Eventos fe WHERE f._id = %d and f.codref_factura = fe.codref_factura ORDER BY fe._id";
}
