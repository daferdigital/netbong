// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrPagoRegistradoDatosFacturas extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrPagoRegistradoDatosFacturas(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrPagoRegistradoDatosFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrPagoRegistradoDatosFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrPagoRegistradoDatosFacturas csrpagoregistradodatosfacturas)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrPagoRegistradoDatosFacturas getFacturas(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select f._id as f_id, f.codref_factura as codref_factura, cf.monto_pago as monto_pago, (select monto_retenido from facturas_comprobantes fcompro where fcompro.id_cancelacion = cf.id_cancelacion and fcompro.codref_factura = f.codref_factura) as monto_retenido from Cancelaciones_Facturas cf,facturas f where cf.id_factura = f._id and cf.id_cancelacion = %d ", aobj);
        CsrPagoRegistradoDatosFacturas csrpagoregistradodatosfacturas = (CsrPagoRegistradoDatosFacturas)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        csrpagoregistradodatosfacturas.moveToFirst();
        return csrpagoregistradodatosfacturas;
    }

    public String getFacturaCodigo()
    {
        return getString(getColumnIndexOrThrow("codref_factura"));
    }

    public int getFacturaId()
    {
        return getInt(getColumnIndexOrThrow("f_id"));
    }

    public double getFacturaMontoRetenido()
    {
        return getDouble(getColumnIndexOrThrow("monto_retenido"));
    }

    public double getPagoMonto()
    {
        return getDouble(getColumnIndexOrThrow("monto_pago"));
    }

    public static final String COLUMNA_FACTURA_CODREF = "codref_factura";
    public static final String COLUMNA_FACTURA_ID = "f_id";
    public static final String COLUMNA_FACTURA_MONTO_RETENIDO = "monto_retenido";
    public static final String COLUMNA_PAGO_MONTO = "monto_pago";
    private static final String QUERY = "select f._id as f_id, f.codref_factura as codref_factura, cf.monto_pago as monto_pago, (select monto_retenido from facturas_comprobantes fcompro where fcompro.id_cancelacion = cf.id_cancelacion and fcompro.codref_factura = f.codref_factura) as monto_retenido from Cancelaciones_Facturas cf,facturas f where cf.id_factura = f._id and cf.id_cancelacion = %d ";
}
