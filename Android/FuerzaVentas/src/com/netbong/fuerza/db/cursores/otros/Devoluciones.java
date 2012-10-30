// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores.otros;

import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class Devoluciones extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new Devoluciones(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private Devoluciones(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    Devoluciones(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, Devoluciones devoluciones)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static Devoluciones getDevolucionesNoSync()
    {
        Devoluciones devoluciones = (Devoluciones)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "Select _id, id_factura, id_producto, fecha, cantidad, observacion from Devoluciones where sync = 0;", null, null);
        devoluciones.moveToFirst();
        return devoluciones;
    }

    public int getCantidad()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    public String getFecha()
    {
        String s = getString(getColumnIndexOrThrow("fecha")).substring(0, 10);
        Log.i("fecha registro devoluc", s);
        String as[] = s.split("-");
        return (new StringBuilder(String.valueOf(as[2]))).append("-").append(as[1]).append("-").append(as[0]).toString();
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public int getIdFactura()
    {
        return getInt(getColumnIndexOrThrow("id_factura"));
    }

    public int getIdProducto()
    {
        return getInt(getColumnIndexOrThrow("id_producto"));
    }

    public String getObservacion()
    {
        return getString(getColumnIndexOrThrow("observacion"));
    }

    public static final String COLUMNA_CANTIDAD = "cantidad";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_ID_FACTURA = "id_factura";
    public static final String COLUMNA_ID_PRODUCTO = "id_producto";
    public static final String COLUMNA_OBSERVACION = "observacion";
    private static final String QUERY_NO_SYNC = "Select _id, id_factura, id_producto, fecha, cantidad, observacion from Devoluciones where sync = 0;";
}
