// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorDevoluciones extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorDevoluciones(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorDevoluciones(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorDevoluciones(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorDevoluciones cursordevoluciones)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorDevoluciones getDevoluciones(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select d.fecha, p.id_profitplus, d.cantidad, d.observacion from devoluciones d, productos p where d.id_factura = %d and d.id_producto = p._id order by 1, 2 ", aobj);
        CursorDevoluciones cursordevoluciones = (CursorDevoluciones)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursordevoluciones.moveToFirst();
        return cursordevoluciones;
    }

    public String getFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public String getObservacion()
    {
        return getString(getColumnIndexOrThrow("observacion"));
    }

    public String getProducto()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public int getUnidades()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_OBSERVACIONES = "observacion";
    public static final String COLUMNA_PRODUCTO = "id_profitplus";
    public static final String COLUMNA_UNIDADES = "cantidad";
    private static final String QUERY = "select d.fecha, p.id_profitplus, d.cantidad, d.observacion from devoluciones d, productos p where d.id_factura = %d and d.id_producto = p._id order by 1, 2 ";
}
