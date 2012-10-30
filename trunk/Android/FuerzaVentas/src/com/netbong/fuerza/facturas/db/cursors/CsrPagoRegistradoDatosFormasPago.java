// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrPagoRegistradoDatosFormasPago extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrPagoRegistradoDatosFormasPago(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrPagoRegistradoDatosFormasPago(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrPagoRegistradoDatosFormasPago(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrPagoRegistradoDatosFormasPago csrpagoregistradodatosformaspago)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrPagoRegistradoDatosFormasPago getFormasPago(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select banco, forma, referencia, monto_pago,fecha_movimiento from Cancelaciones_Formas_Pagos where id_cancelacion = %d ", aobj);
        CsrPagoRegistradoDatosFormasPago csrpagoregistradodatosformaspago = (CsrPagoRegistradoDatosFormasPago)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        csrpagoregistradodatosformaspago.moveToFirst();
        return csrpagoregistradodatosformaspago;
    }

    public String getBanco()
    {
        return getString(getColumnIndexOrThrow("banco"));
    }

    public String getFechaMovimiento()
    {
        return getString(getColumnIndexOrThrow("fecha_movimiento"));
    }

    public String getFormaPago()
    {
        return getString(getColumnIndexOrThrow("forma"));
    }

    public double getMonto()
    {
        return getDouble(getColumnIndexOrThrow("monto_pago"));
    }

    public String getReferencia()
    {
        return getString(getColumnIndexOrThrow("referencia"));
    }

    public static final String COLUMNA_BANCO = "banco";
    public static final String COLUMNA_FECHA_MOVIMIENTO = "fecha_movimiento";
    public static final String COLUMNA_FORMA = "forma";
    public static final String COLUMNA_MONTO = "monto_pago";
    public static final String COLUMNA_REFERENCIA = "referencia";
    private static final String QUERY = "select banco, forma, referencia, monto_pago,fecha_movimiento from Cancelaciones_Formas_Pagos where id_cancelacion = %d ";
}
