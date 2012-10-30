// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CrsPedidoItems extends SQLiteCursor {
    static enum Colums {
    	codref_producto,
    	precio,
    	cantidad,
    	iva,
    	total_excento,
    	total_gravament,
    	total_iva_gravament;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsPedidoItems(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsPedidoItems(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsPedidoItems(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsPedidoItems crspedidoitems)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsPedidoItems getPedidoItems(int i)
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f05001e);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsPedidoItems crspedidoitems = (CrsPedidoItems)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crspedidoitems.moveToFirst();
        return crspedidoitems;
    }

    public int getItemCantidad()
    {
        return getInt(getColumnIndexOrThrow(Colums.cantidad.toString()));
    }

    public String getItemCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.codref_producto.toString()));
    }

    public double getItemIva()
    {
        return getDouble(getColumnIndexOrThrow(Colums.iva.toString()));
    }

    public double getItemPrecio()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio.toString()));
    }

    public double getItemTotalExento()
    {
        return getDouble(getColumnIndexOrThrow(Colums.total_excento.toString()));
    }

    public double getItemTotalIvaGravament()
    {
        return getDouble(getColumnIndexOrThrow(Colums.total_iva_gravament.toString()));
    }

    public double getItemtotalGravament()
    {
        return getDouble(getColumnIndexOrThrow(Colums.total_gravament.toString()));
    }
}
