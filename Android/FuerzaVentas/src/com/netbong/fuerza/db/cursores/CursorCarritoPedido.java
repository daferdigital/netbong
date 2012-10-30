// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

public class CursorCarritoPedido extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorCarritoPedido(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorCarritoPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorCarritoPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorCarritoPedido cursorcarritopedido)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorCarritoPedido getArticulos(SQLiteDatabase sqlitedatabase)
    {
        CursorCarritoPedido cursorcarritopedido = (CursorCarritoPedido)sqlitedatabase.rawQueryWithFactory(new Factory(null), "select c._id, c.id_producto, c.precio,  c.cantidad,  c.iva,  0 as proviene_de_promocion,  p.id_profitplus,  p.imagen, p.nombre from  Carrito_items c,  Productos p  where  c.id_producto = p._id  ", null, null);
        cursorcarritopedido.moveToFirst();
        return cursorcarritopedido;
    }

    public int getCantidad()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    public int getEsPromocion()
    {
        return getInt(getColumnIndexOrThrow("proviene_de_promocion"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getIva()
    {
        return getDouble(getColumnIndexOrThrow("iva"));
    }

    public double getPrecio()
    {
        return getDouble(getColumnIndexOrThrow("precio"));
    }

    public int getProducto()
    {
        return getInt(getColumnIndexOrThrow("id_producto"));
    }

    public String getProductoCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public String getProductoImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public String getProductoNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public static final String COLUMNA_CANTIDAD = "cantidad";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_IVA = "iva";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_PRODUCTO_CODIGO = "id_profitplus";
    public static final String COLUMNA_PRODUCTO_ID = "id_producto";
    public static final String COLUMNA_PRODUCTO_IMAGEN = "imagen";
    public static final String COLUMNA_PRODUCTO_NOMBRE = "nombre";
    public static final String COLUMNA_PROVIENE_DE_PROMOCION = "proviene_de_promocion";
    private static final String QUERY = "select c._id, c.id_producto, c.precio,  c.cantidad,  c.iva,  0 as proviene_de_promocion,  p.id_profitplus,  p.imagen, p.nombre from  Carrito_items c,  Productos p  where  c.id_producto = p._id  ";
}
