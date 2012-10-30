// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CrsCarrito extends SQLiteCursor
{
    static enum Colums  {
    	_id,
    	id_cliente,
    	fecha,
    	observaciones,
    	observaciones_pronto_pago,
    	id_autorizacion_limite_credito,
    	id_pedido;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsCarrito(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsCarrito(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsCarrito(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsCarrito crscarrito)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsCarrito getCarrito()
    {
        String s = MainActivity.mainCtx.getString(0x7f050026);
        CrsCarrito crscarrito = (CrsCarrito)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), s, null, null);
        crscarrito.moveToFirst();
        return crscarrito;
    }

    public String getCarritoFecha()
    {
        return getString(getColumnIndexOrThrow(Colums.fecha.toString()));
    }

    public String getCarritoObservacion()
    {
        return getString(getColumnIndexOrThrow(Colums.observaciones.toString()));
    }

    public String getCarritoObservacionProntoPago()
    {
        return getString(getColumnIndexOrThrow(Colums.observaciones_pronto_pago.toString()));
    }

    public int getCarrtoAutorizacionLimiteCreditoId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_autorizacion_limite_credito.toString()));
    }

    public int getCarrtoClienteId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_cliente.toString()));
    }

    public int getCarrtoId()
    {
        return getInt(getColumnIndexOrThrow(Colums._id.toString()));
    }

    public int getCarrtoPedidoId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_pedido.toString()));
    }
}
