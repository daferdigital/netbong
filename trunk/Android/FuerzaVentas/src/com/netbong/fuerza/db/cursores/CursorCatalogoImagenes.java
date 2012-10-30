// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

public class CursorCatalogoImagenes extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorCatalogoImagenes(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorCatalogoImagenes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorCatalogoImagenes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorCatalogoImagenes cursorcatalogoimagenes)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorCatalogoImagenes getImagenes(SQLiteDatabase sqlitedatabase, int i)
    {
        String s = (new StringBuilder("SELECT imagen, principal FROM Imagenes_Producto WHERE id_producto = ")).append(Integer.toString(i)).toString();
        CursorCatalogoImagenes cursorcatalogoimagenes = (CursorCatalogoImagenes)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorcatalogoimagenes.moveToFirst();
        return cursorcatalogoimagenes;
    }

    public boolean getEsImagenPrincipal()
    {
        boolean flag = true;
        if(getInt(getColumnIndexOrThrow("principal")) != 1)
            flag = false;
        return flag;
    }

    public String getImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public static final String COLUMNA_IMAGEN = "imagen";
    public static final String COLUMNA_PRINCIPAL = "principal";
    public static final String QUERY = "SELECT imagen, principal FROM Imagenes_Producto WHERE id_producto = ";
}
