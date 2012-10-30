// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrComprobantesIva extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrComprobantesIva(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrComprobantesIva(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrComprobantesIva(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrComprobantesIva csrcomprobantesiva)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrComprobantesIva getComprobantes()
    {
        CsrComprobantesIva csrcomprobantesiva = (CsrComprobantesIva)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "select codref_factura, fecha, comprobante, monto_base, monto_retenido, id_cancelacion from facturas_comprobantes where id_cancelacion = 0; ", null, null);
        csrcomprobantesiva.moveToFirst();
        return csrcomprobantesiva;
    }

    public int getCancelacion()
    {
        return getInt(getColumnIndexOrThrow("monto_retenido"));
    }

    public String getComprobante()
    {
        return getString(getColumnIndexOrThrow("comprobante"));
    }

    public String getDescripcion()
    {
        return getString(getColumnIndexOrThrow("id_cancelacion"));
    }

    public String getFactura()
    {
        return getString(getColumnIndexOrThrow("codref_factura"));
    }

    public String getFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public double getMontoBase()
    {
        return getDouble(getColumnIndexOrThrow("monto_base"));
    }

    public double getMontoRetenido()
    {
        return getDouble(getColumnIndexOrThrow("monto_retenido"));
    }

    public static final String COLUMNA_CANCELACION = "id_cancelacion";
    public static final String COLUMNA_COMPROBANTE = "comprobante";
    public static final String COLUMNA_FACTURA = "codref_factura";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_MONTO_BASE = "monto_base";
    public static final String COLUMNA_MONTO_RETENIDO = "monto_retenido";
    private static final String QUERY = "select codref_factura, fecha, comprobante, monto_base, monto_retenido, id_cancelacion from facturas_comprobantes where id_cancelacion = 0; ";
}
