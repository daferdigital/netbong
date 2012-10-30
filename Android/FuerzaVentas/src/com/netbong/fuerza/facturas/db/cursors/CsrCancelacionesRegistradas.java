// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CsrCancelacionesRegistradas extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CsrCancelacionesRegistradas(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CsrCancelacionesRegistradas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CsrCancelacionesRegistradas(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CsrCancelacionesRegistradas csrcancelacionesregistradas)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CsrCancelacionesRegistradas getCancelaciones(String s)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = (new StringBuilder("%")).append(s).append("%").toString();
        String s1 = String.format("select cc._id as cancel_id, cc.fecha_registro, sum(cf.monto_pago) as total_pagado, min(c.nombre) as clie_nombre from facturas f, clientes c, cancelaciones_facturas cf, cancelaciones cc where cc._id = cf.id_cancelacion and cf.id_factura = f._id and f.codref_cliente = c.id_profitplus and like('%s', c.nombre) group by cc._id, cc.fecha_registro ", aobj);
        CsrCancelacionesRegistradas csrcancelacionesregistradas = (CsrCancelacionesRegistradas)sqlitedatabase.rawQueryWithFactory(new Factory(null), s1, null, null);
        if(csrcancelacionesregistradas.getCount() > 0)
            csrcancelacionesregistradas.moveToFirst();
        return csrcancelacionesregistradas;
    }

    public static CsrCancelacionesRegistradas getFacturas()
    {
        CsrCancelacionesRegistradas csrcancelacionesregistradas = (CsrCancelacionesRegistradas)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "select cc._id as cancel_id, cc.fecha_registro, sum(cf.monto_pago) as total_pagado, min(c.nombre) as clie_nombre from facturas f, clientes c, cancelaciones_facturas cf, cancelaciones cc where cc._id = cf.id_cancelacion and cf.id_factura = f._id and f.codref_cliente = c.id_profitplus group by cc._id, cc.fecha_registro ", null, null);
        csrcancelacionesregistradas.moveToFirst();
        return csrcancelacionesregistradas;
    }

    public static CsrCancelacionesRegistradas getFacturas(String s, String s1, String s2, String s3, String s4, String s5)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        StringBuilder stringbuilder = new StringBuilder("select cc._id as cancel_id, cc.fecha_registro, sum(cf.monto_pago) as total_pagado, min(c.nombre) as clie_nombre from facturas f, clientes c, cancelaciones_facturas cf, cancelaciones cc where cc._id = cf.id_cancelacion and cf.id_factura = f._id and f.codref_cliente = c.id_profitplus group by cc._id, cc.fecha_registro ");
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
        CsrCancelacionesRegistradas csrcancelacionesregistradas = (CsrCancelacionesRegistradas)sqlitedatabase.rawQueryWithFactory(new Factory(null), s6, null, null);
        csrcancelacionesregistradas.moveToFirst();
        return csrcancelacionesregistradas;
    }

    public static final String COLUMNA_CANCELACION_FECHA = "fecha_registro";
    public static final String COLUMNA_CANCELACION_ID = "cancel_id";
    public static final String COLUMNA_CANCELACION_TOTAL_PAGADO = "total_pagado";
    public static final String COLUMNA_CLIENTE_NOMBRE = "clie_nombre";
    private static final String QUERY = "select cc._id as cancel_id, cc.fecha_registro, sum(cf.monto_pago) as total_pagado, min(c.nombre) as clie_nombre from facturas f, clientes c, cancelaciones_facturas cf, cancelaciones cc where cc._id = cf.id_cancelacion and cf.id_factura = f._id and f.codref_cliente = c.id_profitplus group by cc._id, cc.fecha_registro ";
    private static final String QUERY_PATRON_CLIENTE = "select cc._id as cancel_id, cc.fecha_registro, sum(cf.monto_pago) as total_pagado, min(c.nombre) as clie_nombre from facturas f, clientes c, cancelaciones_facturas cf, cancelaciones cc where cc._id = cf.id_cancelacion and cf.id_factura = f._id and f.codref_cliente = c.id_profitplus and like('%s', c.nombre) group by cc._id, cc.fecha_registro ";
}
