// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CrsFactura extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsFactura(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsFactura(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsFactura(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsFactura crsfactura)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsFactura getDatos(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        CrsFactura crsfactura = (CrsFactura)sqlitedatabase.rawQueryWithFactory(factory, String.format("select c._id, c.rif, c.nombre as clie_nombre, c.id_profitplus as clie_codigo, substr(c.direccion_fiscal, 1, 100) as direccion_fiscal, f.codref_factura, f.fecha, f.total as fact_total , p.nombre as prod_nombre, p.imagen, fd.codref_producto, fd.cantidad, fd.iva, fd.precio, fd.total as detall_total, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Anulada' when f.estatus = 2 then 'Cancelada' else 'OTRO' end as estatus_dsc, f.estatus as estatus_cod from facturas f, clientes c, facturas_detalle fd, productos p where f._id = %d and f.codref_cliente = c.id_profitplus and f.codref_factura = fd.codref_factura and fd.codref_producto = p.id_profitplus ", aobj), null, null);
        crsfactura.moveToFirst();
        return crsfactura;
    }

    public String getDireccion()
    {
        return getString(getColumnIndexOrThrow("direccion_fiscal"));
    }

    public String getFacturaCodref()
    {
        return getString(getColumnIndexOrThrow("codref_factura"));
    }

    public int getFacturaEstatusCod()
    {
        return getInt(getColumnIndexOrThrow("estatus_cod"));
    }

    public String getFacturaEstatusDsc()
    {
        return getString(getColumnIndexOrThrow("estatus_dsc"));
    }

    public String getFacturaFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public double getFacturaTotal()
    {
        return getDouble(getColumnIndexOrThrow("fact_total"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public int getItemProductoCantidad()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    public String getItemProductoCodref()
    {
        return getString(getColumnIndexOrThrow("codref_producto"));
    }

    public String getItemProductoImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public double getItemProductoIva()
    {
        return getDouble(getColumnIndexOrThrow("iva"));
    }

    public String getItemProductoNombre()
    {
        return getString(getColumnIndexOrThrow("prod_nombre"));
    }

    public double getItemProductoPrecio()
    {
        return getDouble(getColumnIndexOrThrow("precio"));
    }

    public double getItemProductoTotal()
    {
        return getDouble(getColumnIndexOrThrow("detall_total"));
    }

    public String getNombre()
    {
        return getString(getColumnIndexOrThrow("clie_nombre"));
    }

    public String getRif()
    {
        return getString(getColumnIndexOrThrow("rif"));
    }

    public String getcodigo()
    {
        return getString(getColumnIndexOrThrow("clie_codigo"));
    }

    public static final String COLUMNA_CLIE_CODIGO = "clie_codigo";
    public static final String COLUMNA_CLIE_DIRECCION = "direccion_fiscal";
    public static final String COLUMNA_CLIE_ID = "_id";
    public static final String COLUMNA_CLIE_NOMBRE = "clie_nombre";
    public static final String COLUMNA_CLIE_RIF = "rif";
    public static final String COLUMNA_FACTURA_CODREF = "codref_factura";
    public static final String COLUMNA_FACTURA_ESTATUS_COD = "estatus_cod";
    public static final String COLUMNA_FACTURA_ESTATUS_DSC = "estatus_dsc";
    public static final String COLUMNA_FACTURA_FECHA = "fecha";
    public static final String COLUMNA_FACTURA_TOTAL = "fact_total";
    public static final String COLUMNA_FACT_DETALL_CANTIDAD = "cantidad";
    public static final String COLUMNA_FACT_DETALL_IVA = "iva";
    public static final String COLUMNA_FACT_DETALL_PRECIO = "precio";
    public static final String COLUMNA_FACT_DETALL_PROD_CODREF = "codref_producto";
    public static final String COLUMNA_FACT_DETALL_TOTAL = "detall_total";
    public static final String COLUMNA_PROD_IMG = "imagen";
    public static final String COLUMNA_PROD_NOMBRE = "prod_nombre";
    private static final String QUERY = "select c._id, c.rif, c.nombre as clie_nombre, c.id_profitplus as clie_codigo, substr(c.direccion_fiscal, 1, 100) as direccion_fiscal, f.codref_factura, f.fecha, f.total as fact_total , p.nombre as prod_nombre, p.imagen, fd.codref_producto, fd.cantidad, fd.iva, fd.precio, fd.total as detall_total, case when f.estatus = 0 then 'Pendiente' when f.estatus = 1 then 'Anulada' when f.estatus = 2 then 'Cancelada' else 'OTRO' end as estatus_dsc, f.estatus as estatus_cod from facturas f, clientes c, facturas_detalle fd, productos p where f._id = %d and f.codref_cliente = c.id_profitplus and f.codref_factura = fd.codref_factura and fd.codref_producto = p.id_profitplus ";
}
