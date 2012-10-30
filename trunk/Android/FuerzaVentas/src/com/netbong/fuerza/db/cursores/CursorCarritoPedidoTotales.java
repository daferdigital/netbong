// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorCarritoPedidoTotales extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorCarritoPedidoTotales(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorCarritoPedidoTotales(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorCarritoPedidoTotales(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorCarritoPedidoTotales cursorcarritopedidototales)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorCarritoPedidoTotales getTotales(SQLiteDatabase sqlitedatabase)
    {
        CursorCarritoPedidoTotales cursorcarritopedidototales = (CursorCarritoPedidoTotales)sqlitedatabase.rawQueryWithFactory(new Factory(null), "select sum(c.precio * c.cantidad) as total_precio_x_cantidad, sum(case when c.iva = 0 then c.precio * c.cantidad else 0 end) as total_exento, sum(case when c.iva > 0 then c.precio * c.cantidad * c.iva / 100 else 0 end) as total_iva from Carrito_items c ", null, null);
        cursorcarritopedidototales.moveToFirst();
        return cursorcarritopedidototales;
    }

    public double getTotalExcentoMasIVA()
    {
        return getTotalPrecioCantidad() + getTotalIVA();
    }

    public double getTotalExento()
    {
        return getDouble(getColumnIndexOrThrow("total_exento"));
    }

    public double getTotalIVA()
    {
        return getDouble(getColumnIndexOrThrow("total_iva"));
    }

    public double getTotalPrecioCantidad()
    {
        return getDouble(getColumnIndexOrThrow("total_precio_x_cantidad"));
    }

    public static final String COLUMNA_TOTAL_EXENTO = "total_exento";
    public static final String COLUMNA_TOTAL_IVA = "total_iva";
    public static final String COLUMNA_TOTAL_PRECIO_X_CANTIDAD = "total_precio_x_cantidad";
    private static final String QUERY = "select sum(c.precio * c.cantidad) as total_precio_x_cantidad, sum(case when c.iva = 0 then c.precio * c.cantidad else 0 end) as total_exento, sum(case when c.iva > 0 then c.precio * c.cantidad * c.iva / 100 else 0 end) as total_iva from Carrito_items c ";
}
