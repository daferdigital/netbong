// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CancelacionesFormas extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CancelacionesFormas(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CancelacionesFormas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CancelacionesFormas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CancelacionesFormas cancelacionesformas)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CancelacionesFormas getFormas(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        CancelacionesFormas cancelacionesformas = (CancelacionesFormas)sqlitedatabase.rawQueryWithFactory(factory, String.format("Select _id, id_cancelacion, banco, forma, referencia, monto_pago from Cancelaciones_Formas_Pagos where id_cancelacion = %d", aobj), null, null);
        cancelacionesformas.moveToFirst();
        return cancelacionesformas;
    }

    public String getBanco()
    {
        return getString(getColumnIndexOrThrow("banco"));
    }

    public int getCancelacionId()
    {
        return getInt(getColumnIndexOrThrow("id_cancelacion"));
    }

    public String getForma()
    {
        return getString(getColumnIndexOrThrow("forma"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getMontoPago()
    {
        return (double)getInt(getColumnIndexOrThrow("monto_pago"));
    }

    public String getReferencia()
    {
        return getString(getColumnIndexOrThrow("referencia"));
    }

    public String makeSyncTrama()
    {
        StringBuilder stringbuilder = new StringBuilder();
        Object aobj[] = new Object[4];
        aobj[0] = getForma();
        aobj[1] = getBanco();
        aobj[2] = getReferencia();
        aobj[3] = Double.valueOf(getMontoPago());
        stringbuilder.append(String.format("%s;;%s;;%s;;%f", aobj));
        do
        {
            if(!moveToNext())
                return stringbuilder.toString();
            stringbuilder.append("==");
            Object aobj1[] = new Object[4];
            aobj1[0] = getForma();
            aobj1[1] = getBanco();
            aobj1[2] = getReferencia();
            aobj1[3] = Double.valueOf(getMontoPago());
            stringbuilder.append(String.format("%s;;%s;;%s;;%f", aobj1));
        } while(true);
    }

    public static final String COLUMNA_BANCO = "banco";
    public static final String COLUMNA_CANCELACION_ID = "id_cancelacion";
    public static final String COLUMNA_FORMA = "forma";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_MONTO_PAGO = "monto_pago";
    public static final String COLUMNA_REFERENCIA = "referencia";
    private static final String QUERY = "Select _id, id_cancelacion, banco, forma, referencia, monto_pago from Cancelaciones_Formas_Pagos where id_cancelacion = %d";
}
