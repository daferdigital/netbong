// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorClienteDocumentosDigitales extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorClienteDocumentosDigitales(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorClienteDocumentosDigitales(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorClienteDocumentosDigitales(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorClienteDocumentosDigitales cursorclientedocumentosdigitales)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorClienteDocumentosDigitales getDcumentosDigitales(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select _id, uri, tag from Documentos_Digitales_Cliente where id_cliente = %d order by _id ", aobj);
        CursorClienteDocumentosDigitales cursorclientedocumentosdigitales = (CursorClienteDocumentosDigitales)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorclientedocumentosdigitales.moveToFirst();
        return cursorclientedocumentosdigitales;
    }

    public int getID()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getTag()
    {
        return getString(getColumnIndexOrThrow("tag"));
    }

    public String getURI()
    {
        return getString(getColumnIndexOrThrow("uri"));
    }

    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_TAG = "tag";
    public static final String COLUMNA_URI = "uri";
    private static final String QUERY_POR_CLIENTE = "select _id, uri, tag from Documentos_Digitales_Cliente where id_cliente = %d order by _id ";
}
