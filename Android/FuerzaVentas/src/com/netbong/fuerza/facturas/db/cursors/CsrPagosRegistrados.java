// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrPagosRegistrados extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrPagosRegistrados(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrPagosRegistrados(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrPagosRegistrados(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrPagosRegistrados csrpagosregistrados)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrPagosRegistrados getPagos()
    {
        CsrPagosRegistrados csrpagosregistrados = (CsrPagosRegistrados)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "select cc._id as cancel_id, cc.fecha_registro, cc.monto_total as total_pagado, c.nombre as clie_nombre from cancelaciones cc, clientes c where cc.codref_cliente = c.id_profitplus ", null, null);
        csrpagosregistrados.moveToFirst();
        return csrpagosregistrados;
    }

    public static CsrPagosRegistrados getPagos(String s)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        String s1 = (new StringBuilder("select cc._id as cancel_id, cc.fecha_registro, cc.monto_total as total_pagado, c.nombre as clie_nombre from cancelaciones cc, clientes c where cc.codref_cliente = c.id_profitplus  and like('%")).append(s).append("%', c.nombre) ").toString();
        CsrPagosRegistrados csrpagosregistrados = (CsrPagosRegistrados)sqlitedatabase.rawQueryWithFactory(new Factory(null), s1, null, null);
        if(csrpagosregistrados.getCount() > 0)
            csrpagosregistrados.moveToFirst();
        return csrpagosregistrados;
    }

    public static CsrPagosRegistrados getPagos(String s, String s1, String s2, String s3, String s4, String s5)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        StringBuilder stringbuilder = new StringBuilder("select cc._id as cancel_id, cc.fecha_registro, cc.monto_total as total_pagado, c.nombre as clie_nombre from cancelaciones cc, clientes c where cc.codref_cliente = c.id_profitplus ");
        if(s != null && s.length() > 0)
            stringbuilder.append(" and like('%").append(s).append("%', c.nombre)");
        if(s1 != null && s1.length() > 0)
            stringbuilder.append(" and f.estatus = 1");
        if(s2 != null && s2.length() > 0)
            stringbuilder.append(" and f.estatus = 0");
        if(s3 != null && s3.length() > 0)
            stringbuilder.append(" and f.estatus = 2");
        if(s4 != null && s4.length() > 0)
            stringbuilder.append(" and f.fecha > '").append(s4).append("'");
        if(s5 != null && s5.length() > 0)
            stringbuilder.append(" and f.fecha < '").append(s5).append(" 23:59:59'");
        String s6 = stringbuilder.toString();
        CsrPagosRegistrados csrpagosregistrados = (CsrPagosRegistrados)sqlitedatabase.rawQueryWithFactory(new Factory(null), s6, null, null);
        csrpagosregistrados.moveToFirst();
        return csrpagosregistrados;
    }

    public String getPagoCliente()
    {
        return getString(getColumnIndexOrThrow("clie_nombre"));
    }

    public String getPagoFecha()
    {
        return getString(getColumnIndexOrThrow("fecha_registro")).substring(0, 10);
    }

    public int getPagoId()
    {
        return getInt(getColumnIndexOrThrow("cancel_id"));
    }

    public double getPagoMonto()
    {
        return getDouble(getColumnIndexOrThrow("total_pagado"));
    }

    public static final String COLUMNA_CANCELACION_FECHA = "fecha_registro";
    public static final String COLUMNA_CANCELACION_ID = "cancel_id";
    public static final String COLUMNA_CANCELACION_TOTAL_PAGADO = "total_pagado";
    public static final String COLUMNA_CLIENTE_NOMBRE = "clie_nombre";
    private static final String QUERY = "select cc._id as cancel_id, cc.fecha_registro, cc.monto_total as total_pagado, c.nombre as clie_nombre from cancelaciones cc, clientes c where cc.codref_cliente = c.id_profitplus ";
}
