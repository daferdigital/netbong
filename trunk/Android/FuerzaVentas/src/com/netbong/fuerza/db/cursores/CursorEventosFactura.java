// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

// Referenced classes of package com.ehp.droidsf.db:
//            DroidSFDatabase

public class CursorEventosFactura extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorEventosFactura(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorEventosFactura(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorEventosFactura(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorEventosFactura cursoreventosfactura)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorEventosFactura getEventos(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("SELECT pe._id, pe.fecha, pe.evento FROM Facturas_Eventos pe WHERE pe.id_factura = %d ORDER BY pe._id", aobj);
        CursorEventosFactura cursoreventosfactura = (CursorEventosFactura)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursoreventosfactura.moveToFirst();
        return cursoreventosfactura;
    }

    public String getEvento()
    {
        return getString(getColumnIndexOrThrow("evento"));
    }

    public String getFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public static final String COLUMNA_EVENTO = "evento";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_ID = "_id";
    public static final String QUERY = "SELECT pe._id, pe.fecha, pe.evento FROM Facturas_Eventos pe WHERE pe.id_factura = %d ORDER BY pe._id";
}
