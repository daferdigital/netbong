// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class Bancos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new Bancos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private Bancos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    Bancos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, Bancos bancos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static Bancos getBancos()
    {
        Bancos bancos = (Bancos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "Select _id, descripcion from bancos ", null, null);
        bancos.moveToFirst();
        return bancos;
    }

    public static Bancos getBancos(String s)
    {
        Bancos bancos = (Bancos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), (new StringBuilder("Select _id, descripcion from bancos  WHERE like('%")).append(s).append("%', descripcion) ").toString(), null, null);
        bancos.moveToFirst();
        return bancos;
    }

    public static Bancos getBancosPrincipales()
    {
        Bancos bancos = (Bancos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "Select _id, descripcion from bancos where principal = 1 ", null, null);
        bancos.moveToFirst();
        return bancos;
    }

    public String getDescripcion()
    {
        return getString(getColumnIndexOrThrow("descripcion"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public static final String COLUMNA_DESCRIPCION = "descripcion";
    public static final String COLUMNA_ID = "_id";
    private static final String QUERY_PRINCIPALES = "Select _id, descripcion from bancos where principal = 1 ";
    private static final String QUERY_TODOS = "Select _id, descripcion from bancos ";
}
