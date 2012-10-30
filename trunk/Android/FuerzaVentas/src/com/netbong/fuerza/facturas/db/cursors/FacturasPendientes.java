// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db.cursors;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.view.View;
import android.widget.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class FacturasPendientes extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new FacturasPendientes(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private FacturasPendientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    FacturasPendientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, FacturasPendientes facturaspendientes)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static FacturasPendientes getFacturasPendientes(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        FacturasPendientes facturaspendientes = (FacturasPendientes)sqlitedatabase.rawQueryWithFactory(factory, String.format("Select f._id, f.codref_factura, f.fecha, f.total as total_facturado, f.total as total_pendiente from Facturas f, Clientes c where f.codref_cliente = c.id_profitplus and c._id = %d ", aobj), null, null);
        facturaspendientes.moveToFirst();
        return facturaspendientes;
    }

    public String getDocumento()
    {
        return getString(getColumnIndexOrThrow("codref_factura"));
    }

    public String getFecha()
    {
        String as[] = getString(getColumnIndexOrThrow("fecha")).substring(0, 10).split("-");
        return (new StringBuilder(String.valueOf(as[2]))).append("-").append(as[1]).append("-").append(as[0]).toString();
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public double getMontoFacturado()
    {
        return getDouble(getColumnIndexOrThrow("total_facturado"));
    }

    public double getMontoPendiente()
    {
        return getDouble(getColumnIndexOrThrow("total_pendiente"));
    }

    public View getRowAsView(Context context)
    {
        LinearLayout linearlayout = (LinearLayout)View.inflate(context, 0x7f030075, null);
        TextView textview = (TextView)linearlayout.findViewById(0x7f0600ac);
        textview.setText(getDocumento());
        textview.setTag(Integer.valueOf(getId()));
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f06004a);
        Object aobj[] = new Object[1];
        aobj[0] = getFecha();
        textview1.setText(String.format("Fecha: %s", aobj));
        TextView textview2 = (TextView)linearlayout.findViewById(0x7f0600b0);
        Object aobj1[] = new Object[1];
        aobj1[0] = MainActivity.formatVE(getMontoFacturado());
        textview2.setText(String.format("Facturado Bs.: %s", aobj1));
        TextView textview3 = (TextView)linearlayout.findViewById(0x7f06015c);
        Object aobj2[] = new Object[1];
        aobj2[0] = MainActivity.formatVE(getMontoPendiente());
        textview3.setText(String.format("Pendiente Bs.: %s", aobj2));
        ((EditText)linearlayout.findViewById(0x7f06015e)).setText(Double.toString(getMontoPendiente()));
        return linearlayout;
    }

    public static final String COLUMNA_DOCUMENTO = "codref_factura";
    public static final String COLUMNA_FACTURA_ID = "_id";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_MONTO_FACTURADO = "total_facturado";
    public static final String COLUMNA_MONTO_PENDIENTE = "total_pendiente";
    private static final String QUERY = "Select f._id, f.codref_factura, f.fecha, f.total as total_facturado, f.total as total_pendiente from Facturas f, Clientes c where f.codref_cliente = c.id_profitplus and c._id = %d ";
}
