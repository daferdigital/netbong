// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

public class CursorPromos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorPromos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorPromos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorPromos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorPromos cursorpromos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorPromos getPromos(SQLiteDatabase sqlitedatabase)
    {
        CursorPromos cursorpromos = (CursorPromos)sqlitedatabase.rawQueryWithFactory(new Factory(null), "select _id, descripcion, vigencia, imagen from promociones where julianday(vigencia) - julianday('now') > 0", null, null);
        cursorpromos.moveToFirst();
        return cursorpromos;
    }

    public String getDescripcion()
    {
        return getString(getColumnIndexOrThrow("descripcion"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public String getVigencia()
    {
        return getString(getColumnIndexOrThrow("vigencia"));
    }

    public static final String COLUMNA_DESCRIPCION = "descripcion";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_IMAGEN = "imagen";
    public static final String COLUMNA_VIGENCIA = "vigencia";
    public static final String QUERY = "select _id, descripcion, vigencia, imagen from promociones where julianday(vigencia) - julianday('now') > 0";
}
