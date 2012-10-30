// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

public class CursorDatosResumenPedido extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorDatosResumenPedido(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorDatosResumenPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorDatosResumenPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorDatosResumenPedido cursordatosresumenpedido)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorDatosResumenPedido getDatosResumenPedido(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("SELECT c._id, c.nombre as nombre, c.rif, c.direccion_fiscal, c.limite_credito, p.fecha, case when p.status = 0 then 'Generado' when p.status = 1 then 'Anulado' when p.status = 2 then 'Sincronizado' when p.status = 3 then 'Facturado' end as estatus, p.total_excento + p.total_gravament + p.total_iva_gravament as total, p.observacion as observacion, p.status as status_codigo FROM Pedidos p, Clientes c WHERE p._id = %d and p.id_cliente = c._id ", aobj);
        CursorDatosResumenPedido cursordatosresumenpedido = (CursorDatosResumenPedido)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursordatosresumenpedido.moveToFirst();
        return cursordatosresumenpedido;
    }

    public String getClienteDireccion()
    {
        return getString(getColumnIndexOrThrow("direccion_fiscal"));
    }

    public int getClienteId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getClienteLimiteCredito()
    {
        return getDouble(getColumnIndexOrThrow("limite_credito"));
    }

    public String getClienteNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public String getClienteRIF()
    {
        return getString(getColumnIndexOrThrow("rif"));
    }

    public String getPedidoEstatus()
    {
        return getString(getColumnIndexOrThrow("estatus"));
    }

    public int getPedidoEstatusCodigo()
    {
        return getInt(getColumnIndexOrThrow("status_codigo"));
    }

    public String getPedidoFecha()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public String getPedidoObservacion()
    {
        return getString(getColumnIndexOrThrow("observacion"));
    }

    public double getPedidoTotal()
    {
        return getDouble(getColumnIndexOrThrow("total"));
    }

    public boolean permitirImprimir()
    {
        boolean flag;
        if(2 == getInt(getColumnIndexOrThrow("status_codigo")))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean permitirModificar()
    {
        boolean flag;
        if(getInt(getColumnIndexOrThrow("status_codigo")) == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean permitirSincronizar()
    {
        boolean flag;
        if(getInt(getColumnIndexOrThrow("status_codigo")) == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final String COLUMNA_CLIENTE_DIRECCION = "direccion_fiscal";
    public static final String COLUMNA_CLIENTE_ID = "_id";
    public static final String COLUMNA_CLIENTE_LIMITE_CREDITO = "limite_credito";
    public static final String COLUMNA_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMNA_CLIENTE_RIF = "rif";
    public static final String COLUMNA_PEDIDO_ESTATUS = "estatus";
    public static final String COLUMNA_PEDIDO_ESTATUS_CODIGO = "status_codigo";
    public static final String COLUMNA_PEDIDO_FECHA = "fecha";
    public static final String COLUMNA_PEDIDO_OBSERVACON = "observacion";
    public static final String COLUMNA_PEDIDO_TOTAL = "total";
    private static final String QUERY = "SELECT c._id, c.nombre as nombre, c.rif, c.direccion_fiscal, c.limite_credito, p.fecha, case when p.status = 0 then 'Generado' when p.status = 1 then 'Anulado' when p.status = 2 then 'Sincronizado' when p.status = 3 then 'Facturado' end as estatus, p.total_excento + p.total_gravament + p.total_iva_gravament as total, p.observacion as observacion, p.status as status_codigo FROM Pedidos p, Clientes c WHERE p._id = %d and p.id_cliente = c._id ";
}
