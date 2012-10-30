// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

// Referenced classes of package com.ehp.droidsf.db:
//            DroidSFDatabase

public class CursorFacturas extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorFacturas(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorFacturas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorFacturas cursorfacturas)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorFacturas getFacturas(SQLiteDatabase sqlitedatabase)
    {
        CursorFacturas cursorfacturas = (CursorFacturas)sqlitedatabase.rawQueryWithFactory(new Factory(null), "Select f._id, f.numero_factura, f.fecha, f.total, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Cancelada' when f.estatus = 2 then 'Anulada' else 'OTRO' end as status, c.rif, c.nombre from Facturas f, Clientes c where f.id_cliente = c._id ", null, null);
        cursorfacturas.moveToFirst();
        return cursorfacturas;
    }

    public static CursorFacturas getFacturas(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Object aobj[] = new Object[3];
        aobj[0] = (new StringBuilder("%")).append(s).append("%").toString();
        aobj[1] = s1;
        aobj[2] = s2;
        String s3 = String.format("Select f._id, f.numero_factura, f.fecha, f.total, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Cancelada' when f.estatus = 2 then 'Anulada' else 'OTRO' end as status, c.rif, c.nombre from Facturas f, Clientes c where like('%s', c.nombre) and f.fecha between '%s' and '%s 23:59:59' and f.id_cliente = c._id ", aobj);
        CursorFacturas cursorfacturas = (CursorFacturas)sqlitedatabase.rawQueryWithFactory(new Factory(null), s3, null, null);
        cursorfacturas.moveToFirst();
        return cursorfacturas;
    }

    public String getDocumento()
    {
        return getString(getColumnIndexOrThrow("numero_factura"));
    }

    public String getFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getMontoFacturado()
    {
        return getDouble(getColumnIndexOrThrow("total"));
    }

    public String getNombreCliente()
    {
        Object aobj[] = new Object[2];
        aobj[0] = getString(getColumnIndexOrThrow("rif"));
        aobj[1] = getString(getColumnIndexOrThrow("nombre"));
        return String.format("%s-%s", aobj);
    }

    public String getStatus()
    {
        return getString(getColumnIndexOrThrow("status"));
    }

    public static final String COLUMNA_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMNA_CLIENTE_RIF = "rif";
    public static final String COLUMNA_DOCUMENTO = "numero_factura";
    public static final String COLUMNA_FACTURA_ID = "_id";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_MONTO_FACTURADO = "total";
    public static final String COLUMNA_STATUS = "status";
    private static final String QUERY = "Select f._id, f.numero_factura, f.fecha, f.total, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Cancelada' when f.estatus = 2 then 'Anulada' else 'OTRO' end as status, c.rif, c.nombre from Facturas f, Clientes c where f.id_cliente = c._id ";
    private static final String QUERY_filtro = "Select f._id, f.numero_factura, f.fecha, f.total, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Cancelada' when f.estatus = 2 then 'Anulada' else 'OTRO' end as status, c.rif, c.nombre from Facturas f, Clientes c where like('%s', c.nombre) and f.fecha between '%s' and '%s 23:59:59' and f.id_cliente = c._id ";
}
