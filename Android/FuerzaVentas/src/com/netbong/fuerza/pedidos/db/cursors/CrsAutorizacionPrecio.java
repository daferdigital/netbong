// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CrsAutorizacionPrecio extends SQLiteCursor
{
    static enum Colums {
    	_id,
    	codref_autorizacion_precio,
    	codref_producto,
    	codref_cliente,
    	precio,
    	id_pedido;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsAutorizacionPrecio(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsAutorizacionPrecio(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsAutorizacionPrecio(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsAutorizacionPrecio crsautorizacionprecio)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsAutorizacionPrecio getAutorizacionPorId(int i)
    {
        String s = MainActivity.mainCtx.getString(0x7f050002);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsAutorizacionPrecio crsautorizacionprecio = (CrsAutorizacionPrecio)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crsautorizacionprecio.moveToFirst();
        return crsautorizacionprecio;
    }

    public static CrsAutorizacionPrecio getAutorizacionPorProductoCliente(String s, String s1)
    {
        String s2 = MainActivity.mainCtx.getString(0x7f050003);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[2];
        as[0] = s;
        as[1] = s1;
        CrsAutorizacionPrecio crsautorizacionprecio = (CrsAutorizacionPrecio)sqlitedatabase.rawQueryWithFactory(factory, s2, as, null);
        crsautorizacionprecio.moveToFirst();
        return crsautorizacionprecio;
    }

    public int getAturizacionId()
    {
        return getInt(getColumnIndexOrThrow(Colums._id.toString()));
    }

    public int getAturizacionPedidoId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_pedido.toString()));
    }

    public double getAturizacionPrecio()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio.toString()));
    }

    public String getAutorizacionClienteCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.codref_cliente.toString()));
    }

    public String getAutorizacionCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.codref_autorizacion_precio.toString()));
    }

    public String getAutorizacionProductoCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.codref_producto.toString()));
    }
}
