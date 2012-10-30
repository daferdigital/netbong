// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

public class CursorDevolucionesArticulosNoDevueltos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorDevolucionesArticulosNoDevueltos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorDevolucionesArticulosNoDevueltos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorDevolucionesArticulosNoDevueltos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorDevolucionesArticulosNoDevueltos cursordevolucionesarticulosnodevueltos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorDevolucionesArticulosNoDevueltos getArtivulosNoDevueltos(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select p._id, p.id_profitplus, p.imagen, sum(fd.cantidad) as cantidad from facturas_detalle fd, productos p where fd.id_factura = %d and fd.id_producto = p._id group by p._id, p.id_profitplus, p.imagen ", aobj);
        CursorDevolucionesArticulosNoDevueltos cursordevolucionesarticulosnodevueltos = (CursorDevolucionesArticulosNoDevueltos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursordevolucionesarticulosnodevueltos.moveToFirst();
        return cursordevolucionesarticulosnodevueltos;
    }

    public String getProductoCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public int getProductoID()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getProductoImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public int getUnidades()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    public static final String COLUMNA_IMAGEN = "imagen";
    public static final String COLUMNA_PRODUCTO_CODIGO = "id_profitplus";
    public static final String COLUMNA_PRODUCTO_ID = "_id";
    public static final String COLUMNA_UNIDADES = "cantidad";
    private static final String QUERY = "select p._id, p.id_profitplus, sum(fd.cantidad) - min((select sum(d.cantidad) from devoluciones d where d.id_factura = %d and d.id_producto = p._id)) as cantidad from facturas_detalle fd, productos p where fd.id_factura = %d and fd.id_producto = p._id group by p._id, p.id_profitplus ";
    private static final String QUERY_alternativo = "select p._id, p.id_profitplus, p.imagen, sum(fd.cantidad) as cantidad from facturas_detalle fd, productos p where fd.id_factura = %d and fd.id_producto = p._id group by p._id, p.id_profitplus, p.imagen ";
}
