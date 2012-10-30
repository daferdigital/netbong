// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrBancos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrBancos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrBancos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrBancos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrBancos csrbancos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrBancos getBancos()
    {
        CsrBancos csrbancos = (CsrBancos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "Select _id, descripcion from bancos ", null, null);
        csrbancos.moveToFirst();
        return csrbancos;
    }

    public static CsrBancos getBancos(String s)
    {
        CsrBancos csrbancos = (CsrBancos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), (new StringBuilder("Select _id, descripcion from bancos  WHERE like('%")).append(s).append("%', descripcion) ").toString(), null, null);
        csrbancos.moveToFirst();
        return csrbancos;
    }

    public static CsrBancos getBancosPrincipales()
    {
        CsrBancos csrbancos = (CsrBancos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "Select _id, descripcion from bancos where principal = 1 ", null, null);
        csrbancos.moveToFirst();
        return csrbancos;
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
