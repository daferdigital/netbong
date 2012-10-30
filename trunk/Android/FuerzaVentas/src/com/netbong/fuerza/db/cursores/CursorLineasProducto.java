// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorLineasProducto extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorLineasProducto(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorLineasProducto(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorLineasProducto(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorLineasProducto cursorlineasproducto)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorLineasProducto getLineasProducto(SQLiteDatabase sqlitedatabase)
    {
        CursorLineasProducto cursorlineasproducto = (CursorLineasProducto)sqlitedatabase.rawQueryWithFactory(new Factory(null), "SELECT _id, nombre, id_profitplus FROM Lineas_Producto lp, vendedor_x_linea vl where lp._id = vl.id_linea  ORDER BY nombre ", null, null);
        cursorlineasproducto.moveToFirst();
        return cursorlineasproducto;
    }

    public static CursorLineasProducto getLineasProducto(SQLiteDatabase sqlitedatabase, String s)
    {
        String s1 = (new StringBuilder("SELECT _id, nombre, id_profitplus FROM Lineas_Producto lp, vendedor_x_linea vl where lp._id = vl.id_linea  and like('%")).append(s).append("%', nombre)").append(" ORDER BY nombre ").toString();
        CursorLineasProducto cursorlineasproducto = (CursorLineasProducto)sqlitedatabase.rawQueryWithFactory(new Factory(null), s1, null, null);
        cursorlineasproducto.moveToFirst();
        return cursorlineasproducto;
    }

    public String getCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public static final String COLUMNA_CODIGO = "id_profitplus";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_NOMBRE = "nombre";
    private static final String QUERY = "SELECT _id, nombre, id_profitplus FROM Lineas_Producto lp, vendedor_x_linea vl where lp._id = vl.id_linea ";
    private static final String QUERY_ORDER = " ORDER BY nombre ";
}
