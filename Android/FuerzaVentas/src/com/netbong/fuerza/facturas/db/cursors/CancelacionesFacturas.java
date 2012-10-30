// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CancelacionesFacturas extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CancelacionesFacturas(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CancelacionesFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CancelacionesFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CancelacionesFacturas cancelacionesfacturas)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CancelacionesFacturas getFacturas(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        CancelacionesFacturas cancelacionesfacturas = (CancelacionesFacturas)sqlitedatabase.rawQueryWithFactory(factory, String.format("Select _id, id_cancelacion, id_factura, monto_pago from Cancelaciones_Facturas where id_cancelacion = %d", aobj), null, null);
        cancelacionesfacturas.moveToFirst();
        return cancelacionesfacturas;
    }

    public int getCancelacionId()
    {
        return getInt(getColumnIndexOrThrow("id_cancelacion"));
    }

    public int getFacturaId()
    {
        return getInt(getColumnIndexOrThrow("id_factura"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getMontoPago()
    {
        return (double)getInt(getColumnIndexOrThrow("monto_pago"));
    }

    public String makeSyncTrama()
    {
        StringBuilder stringbuilder = new StringBuilder();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(getFacturaId());
        aobj[1] = Double.valueOf(getMontoPago());
        stringbuilder.append(String.format("%d;;%f", aobj));
        do
        {
            if(!moveToNext())
                return stringbuilder.toString();
            stringbuilder.append("==");
            Object aobj1[] = new Object[2];
            aobj1[0] = Integer.valueOf(getFacturaId());
            aobj1[1] = Double.valueOf(getMontoPago());
            stringbuilder.append(String.format("%d;;%f", aobj1));
        } while(true);
    }

    public static final String COLUMNA_CANCELACION_ID = "id_cancelacion";
    public static final String COLUMNA_FACTURA_ID = "id_factura";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_MONTO_PAGO = "monto_pago";
    private static final String QUERY = "Select _id, id_cancelacion, id_factura, monto_pago from Cancelaciones_Facturas where id_cancelacion = %d";
}
