// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores.otros;

import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;
import com.netbong.fuerza.facturas.db.cursors.CancelacionesFacturas;
import com.netbong.fuerza.facturas.db.cursors.CancelacionesFormas;

public class Cancelaciones extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new Cancelaciones(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private Cancelaciones(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    Cancelaciones(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, Cancelaciones cancelaciones)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static Cancelaciones getCancelacionesNoSync()
    {
        Cancelaciones cancelaciones = (Cancelaciones)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "Select _id, fecha_registro, fecha_pago, observacion from Cancelaciones where sync = 0;", null, null);
        cancelaciones.moveToFirst();
        return cancelaciones;
    }

    public String getFechaAplicacionPago()
    {
        return getString(getColumnIndexOrThrow("fecha_pago"));
    }

    public String getFechaRegistro()
    {
        String s = getString(getColumnIndexOrThrow("fecha_registro")).substring(0, 10);
        Log.i("fecha registro", s);
        String as[] = s.split("-");
        return (new StringBuilder(String.valueOf(as[2]))).append("-").append(as[1]).append("-").append(as[0]).toString();
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getObservacion()
    {
        return getString(getColumnIndexOrThrow("observacion"));
    }

    public String getTramaSync()
    {
        Object aobj[] = new Object[3];
        aobj[0] = getFechaRegistro();
        aobj[1] = getFechaAplicacionPago();
        aobj[2] = getObservacion();
        StringBuilder stringbuilder = new StringBuilder(String.format("%s;;%s;;%s", aobj));
        CancelacionesFacturas cancelacionesfacturas = CancelacionesFacturas.getFacturas(getId());
        stringbuilder.append("##").append(cancelacionesfacturas.makeSyncTrama());
        CancelacionesFormas cancelacionesformas = CancelacionesFormas.getFormas(getId());
        stringbuilder.append("##").append(cancelacionesformas.makeSyncTrama());
        return stringbuilder.toString();
    }

    public static final String COLUMNA_DESCRIPCION = "descripcion";
    public static final String COLUMNA_FECHA_PAGO = "fecha_pago";
    public static final String COLUMNA_FECHA_REGISTRO = "fecha_registro";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_OBSERVACION = "observacion";
    private static final String QUERY_NO_SYNC = "Select _id, fecha_registro, fecha_pago, observacion from Cancelaciones where sync = 0;";
}
