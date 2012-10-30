// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorClienteDocumentosFisicos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorClienteDocumentosFisicos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorClienteDocumentosFisicos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorClienteDocumentosFisicos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorClienteDocumentosFisicos cursorclientedocumentosfisicos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorClienteDocumentosFisicos getDcumentosFisicos(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select registro_mercantil, rif, copia_cedula, referencias_comerciales, referencias_bancarias, observaciones from Documentos_Fisicos_Cliente where id_cliente = %d ", aobj);
        CursorClienteDocumentosFisicos cursorclientedocumentosfisicos = (CursorClienteDocumentosFisicos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorclientedocumentosfisicos.moveToFirst();
        return cursorclientedocumentosfisicos;
    }

    public int getCedula()
    {
        return getInt(getColumnIndexOrThrow("copia_cedula"));
    }

    public String getObservaciones()
    {
        return getString(getColumnIndexOrThrow("observaciones"));
    }

    public int getReferenciasBancarias()
    {
        return getInt(getColumnIndexOrThrow("referencias_bancarias"));
    }

    public int getReferenciasComerciales()
    {
        return getInt(getColumnIndexOrThrow("referencias_comerciales"));
    }

    public int getRegistroMercantil()
    {
        return getInt(getColumnIndexOrThrow("registro_mercantil"));
    }

    public int getRif()
    {
        return getInt(getColumnIndexOrThrow("rif"));
    }

    public static final String COLUMNA_CEDULA = "copia_cedula";
    public static final String COLUMNA_OBSERVACIONES = "observaciones";
    public static final String COLUMNA_REF_BANCARIA = "referencias_bancarias";
    public static final String COLUMNA_REF_COMERCIALES = "referencias_comerciales";
    public static final String COLUMNA_REGISTRO_MERCANTIL = "registro_mercantil";
    public static final String COLUMNA_RIF = "rif";
    private static final String QUERY_POR_CLIENTE = "select registro_mercantil, rif, copia_cedula, referencias_comerciales, referencias_bancarias, observaciones from Documentos_Fisicos_Cliente where id_cliente = %d ";
}
