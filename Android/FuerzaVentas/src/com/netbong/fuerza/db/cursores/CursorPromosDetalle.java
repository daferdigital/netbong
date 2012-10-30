// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

import com.netbong.fuerza.MainActivity;

import java.io.File;

public class CursorPromosDetalle extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorPromosDetalle(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorPromosDetalle(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorPromosDetalle(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorPromosDetalle cursorpromosdetalle)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorPromosDetalle getDetallePromo(SQLiteDatabase sqlitedatabase, int i)
    {
        CursorPromosDetalle cursorpromosdetalle = (CursorPromosDetalle)sqlitedatabase.rawQueryWithFactory(new Factory(null), (new StringBuilder("select pm.descripcion, pm.vigencia, pm.imagen as pr_imagen, p._id, p.nombre, p.imagen, pmp.precio, pmp.iva, pmp.cantidad from promociones pm, promociones_productos pmp, productos p where pm._id = pmp.id_promocion and pmp.id_producto = p._id and pm._id = ")).append(Integer.toString(i)).toString(), null, null);
        cursorpromosdetalle.moveToFirst();
        return cursorpromosdetalle;
    }

    public int getProductoCantidad()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    public int getProductoID()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getProductoImagen()
    {
        return (new StringBuilder(String.valueOf(MainActivity.mainCtx.getFilesDir().getAbsolutePath()))).append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public String getProductoNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public double getProductoPrecio()
    {
        return getDouble(getColumnIndexOrThrow("precio"));
    }

    public double getProductoPrecioIva()
    {
        return getDouble(getColumnIndexOrThrow("iva"));
    }

    public String getPromocion()
    {
        return getString(getColumnIndexOrThrow("descripcion"));
    }

    public String getPromocionImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("pr_imagen"))).toString();
    }

    public String getPromocionVigencia()
    {
        return getString(getColumnIndexOrThrow("vigencia"));
    }

    public static final String COLUMNA_IVA = "iva";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_PRODUCTO = "nombre";
    public static final String COLUMNA_PRODUCTO_CANTIDAD = "cantidad";
    public static final String COLUMNA_PRODUCTO_ID = "_id";
    public static final String COLUMNA_PRODUCTO_IMAGEN = "imagen";
    public static final String COLUMNA_PROMO = "descripcion";
    public static final String COLUMNA_PROMO_IMAGEN = "pr_imagen";
    public static final String COLUMNA_PROMO_VIGENCIA = "vigencia";
    public static final String QUERY = "select pm.descripcion, pm.vigencia, pm.imagen as pr_imagen, p._id, p.nombre, p.imagen, pmp.precio, pmp.iva, pmp.cantidad from promociones pm, promociones_productos pmp, productos p where pm._id = pmp.id_promocion and pmp.id_producto = p._id and pm._id = ";
}
