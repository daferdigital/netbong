// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorPedidosNoSincronizado extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorPedidosNoSincronizado(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorPedidosNoSincronizado(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorPedidosNoSincronizado(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorPedidosNoSincronizado cursorpedidosnosincronizado)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorPedidosNoSincronizado getPedidosNoSincronizados(SQLiteDatabase sqlitedatabase)
    {
        CursorPedidosNoSincronizado cursorpedidosnosincronizado = (CursorPedidosNoSincronizado)sqlitedatabase.rawQueryWithFactory(new Factory(null), "select c.nombre, c.rif, p.id, p.fecha, p.total_excento + p.total_gravament + p.total_iva_gravament as total from  Pedidos p, Clientes c where p.id_cliente = c.id", null, null);
        cursorpedidosnosincronizado.moveToFirst();
        return cursorpedidosnosincronizado;
    }

    public String getClienteNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public String getClienteRif()
    {
        return getString(getColumnIndexOrThrow("rif"));
    }

    public int getPedidoCodigo()
    {
        return getInt(getColumnIndexOrThrow("id"));
    }

    public String getPedidoFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public double getPedidoMontoTotal()
    {
        return getDouble(getColumnIndexOrThrow("total"));
    }

    public static final String COLUMNA_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMNA_CLIENTE_RIF = "rif";
    public static final String COLUMNA_PEDIDO_FECHA = "fecha";
    public static final String COLUMNA_PEDIDO_ID = "id";
    public static final String COLUMNA_PEDIDO_TOTAL = "total";
    private static final String query = "select c.nombre, c.rif, p.id, p.fecha, p.total_excento + p.total_gravament + p.total_iva_gravament as total from  Pedidos p, Clientes c where p.id_cliente = c.id";
}
