// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrPagoRegistradoDatosCliente extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrPagoRegistradoDatosCliente(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrPagoRegistradoDatosCliente(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrPagoRegistradoDatosCliente(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrPagoRegistradoDatosCliente csrpagoregistradodatoscliente)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrPagoRegistradoDatosCliente getDatosCliente(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select c._id, c.rif, c.nombre, c.id_profitplus, cf.monto_total from Cancelaciones cf,clientes c where cf.codref_cliente = c.id_profitplus and cf._id = %d ", aobj);
        CsrPagoRegistradoDatosCliente csrpagoregistradodatoscliente = (CsrPagoRegistradoDatosCliente)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        csrpagoregistradodatoscliente.moveToFirst();
        return csrpagoregistradodatoscliente;
    }

    public String getClienteCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public int getClienteId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getClienteNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public String getClienteRif()
    {
        return getString(getColumnIndexOrThrow("rif"));
    }

    public double getMontoTotalPago()
    {
        return getDouble(getColumnIndexOrThrow("monto_total"));
    }

    public static final String COLUMNA_CLIENTE_CODREF = "id_profitplus";
    public static final String COLUMNA_CLIENTE_ID = "_id";
    public static final String COLUMNA_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMNA_CLIENTE_RIF = "rif";
    public static final String COLUMNA_TOTAL_PAGO = "monto_total";
    private static final String QUERY = "select c._id, c.rif, c.nombre, c.id_profitplus, cf.monto_total from Cancelaciones cf,clientes c where cf.codref_cliente = c.id_profitplus and cf._id = %d ";
}
