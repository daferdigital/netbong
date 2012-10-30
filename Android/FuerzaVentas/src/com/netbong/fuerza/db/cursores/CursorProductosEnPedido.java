// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

import com.netbong.fuerza.MainActivity;

public class CursorProductosEnPedido extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorProductosEnPedido(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorProductosEnPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorProductosEnPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorProductosEnPedido cursorproductosenpedido)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorProductosEnPedido getProductos(SQLiteDatabase sqlitedatabase, int i)
    {
        String s = (new StringBuilder(String.valueOf(MainActivity.mainCtx.getString(0x7f05004f)))).append(Integer.toString(i)).toString();
        CursorProductosEnPedido cursorproductosenpedido = (CursorProductosEnPedido)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorproductosenpedido.moveToFirst();
        return cursorproductosenpedido;
    }

    public int getCantidad()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public double getIva()
    {
        return getDouble(getColumnIndexOrThrow("iva"));
    }

    public String getNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public int getPrecio()
    {
        return getInt(getColumnIndexOrThrow("precio"));
    }

    public double getSubTotal()
    {
        return getDouble(getColumnIndexOrThrow("sub_total"));
    }

    public static final String COLUMNA_CANTIDAD = "cantidad";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_IMAGEN = "imagen";
    public static final String COLUMNA_IVA = "iva";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_SUB_TOTAL = "sub_total";
}
