// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CursorClientesConFacturas extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorClientesConFacturas(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorClientesConFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorClientesConFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorClientesConFacturas cursorclientesconfacturas)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorClientesConFacturas getClientes(SQLiteDatabase sqlitedatabase)
    {
        String s = MainActivity.mainCtx.getString(0x7f050049);
        CursorClientesConFacturas cursorclientesconfacturas = (CursorClientesConFacturas)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorclientesconfacturas.moveToFirst();
        return cursorclientesconfacturas;
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("id"));
    }

    public String getNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public int getTotalFacturasAnuladas()
    {
        return getInt(getColumnIndexOrThrow("anulada"));
    }

    public int getTotalFacturasCanceladas()
    {
        return getInt(getColumnIndexOrThrow("cancelada"));
    }

    public int getTotalFacturasPendientes()
    {
        return getInt(getColumnIndexOrThrow("pendiente"));
    }

    public static final String COLUMNA_CLIENTE_ID = "id";
    public static final String COLUMNA_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMNA_FACTURA_ANULADA = "anulada";
    public static final String COLUMNA_FACTURA_CANCELADA = "cancelada";
    public static final String COLUMNA_FACTURA_PENDIENTE = "pendiente";
}
