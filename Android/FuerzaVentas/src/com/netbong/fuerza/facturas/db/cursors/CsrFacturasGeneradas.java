// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrFacturasGeneradas extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrFacturasGeneradas(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrFacturasGeneradas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrFacturasGeneradas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrFacturasGeneradas csrfacturasgeneradas)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrFacturasGeneradas getFacturas()
    {
        CsrFacturasGeneradas csrfacturasgeneradas = (CsrFacturasGeneradas)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "SELECT f._id, c.nombre, c.id_profitplus, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Anulada' when f.estatus = 2 then 'Cancelada' else 'OTRO' end as estatus, f.fecha, f.total, f.codref_factura FROM Facturas f, Clientes c WHERE f.codref_cliente = c.id_profitplus ", null, null);
        csrfacturasgeneradas.moveToFirst();
        return csrfacturasgeneradas;
    }

    public static CsrFacturasGeneradas getFacturas(String s)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        String s1 = (new StringBuilder("SELECT f._id, c.nombre, c.id_profitplus, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Anulada' when f.estatus = 2 then 'Cancelada' else 'OTRO' end as estatus, f.fecha, f.total, f.codref_factura FROM Facturas f, Clientes c WHERE f.codref_cliente = c.id_profitplus  and like('%")).append(s).append("%', f.codref_factura || c.nombre)").toString();
        CsrFacturasGeneradas csrfacturasgeneradas = (CsrFacturasGeneradas)sqlitedatabase.rawQueryWithFactory(new Factory(null), s1, null, null);
        if(csrfacturasgeneradas.getCount() > 0)
            csrfacturasgeneradas.moveToFirst();
        return csrfacturasgeneradas;
    }

    public static CsrFacturasGeneradas getFacturas(String s, String s1, String s2, String s3, String s4, String s5)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        StringBuilder stringbuilder = new StringBuilder("SELECT f._id, c.nombre, c.id_profitplus, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Anulada' when f.estatus = 2 then 'Cancelada' else 'OTRO' end as estatus, f.fecha, f.total, f.codref_factura FROM Facturas f, Clientes c WHERE f.codref_cliente = c.id_profitplus ");
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
        CsrFacturasGeneradas csrfacturasgeneradas = (CsrFacturasGeneradas)sqlitedatabase.rawQueryWithFactory(new Factory(null), s6, null, null);
        csrfacturasgeneradas.moveToFirst();
        return csrfacturasgeneradas;
    }

    public String getFactura()
    {
        return getString(getColumnIndexOrThrow("codref_factura"));
    }

    public String getFacturaCliente()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public String getFacturaClienteCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public String getFacturaEstatus()
    {
        return getString(getColumnIndexOrThrow("estatus"));
    }

    public String getFacturaFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public int getFacturaId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getFacturaMonto()
    {
        return getDouble(getColumnIndexOrThrow("total"));
    }

    public static final String COLUMNA_CLIENTE_CODIGO = "id_profitplus";
    public static final String COLUMNA_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMNA_FACTURA = "codref_factura";
    public static final String COLUMNA_FACTURA_FECHA = "fecha";
    public static final String COLUMNA_FACTURA_ID = "_id";
    public static final String COLUMNA_FACTURA_STATUS = "estatus";
    public static final String COLUMNA_FACTURA_TOTAL = "total";
    private static final String QUERY = "SELECT f._id, c.nombre, c.id_profitplus, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Anulada' when f.estatus = 2 then 'Cancelada' else 'OTRO' end as estatus, f.fecha, f.total, f.codref_factura FROM Facturas f, Clientes c WHERE f.codref_cliente = c.id_profitplus ";
}
