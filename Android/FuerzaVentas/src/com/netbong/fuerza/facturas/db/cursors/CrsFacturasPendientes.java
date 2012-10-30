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
import com.netbong.fuerza.facturas.db.DBHandle;

public class CrsFacturasPendientes extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsFacturasPendientes(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsFacturasPendientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsFacturasPendientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsFacturasPendientes crsfacturaspendientes)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsFacturasPendientes getFacturasPendientes(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        CrsFacturasPendientes crsfacturaspendientes = (CrsFacturasPendientes)sqlitedatabase.rawQueryWithFactory(factory, String.format("Select f._id, f.codref_factura, f.fecha, f.total as total_facturado, f.total as total_pendiente from Facturas f, Clientes c where f.estatus = 0 and f.codref_cliente = c.id_profitplus and c._id = %d ", aobj), null, null);
        crsfacturaspendientes.moveToFirst();
        return crsfacturaspendientes;
    }

    public static CrsFacturasPendientes getFacturasPendientesPago(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        CrsFacturasPendientes crsfacturaspendientes = (CrsFacturasPendientes)sqlitedatabase.rawQueryWithFactory(factory, String.format("Select f._id, f.codref_factura, f.fecha, f.total as total_facturado, f.total as total_pendiente, (select count(*) from Cancelaciones_Facturas cf where cf.id_cancelacion = cc._id and cf.id_factura = f._id) as en_pago,(select monto_pago from Cancelaciones_Facturas cf where cf.id_cancelacion = cc._id and cf.id_factura = f._id) as monto_en_pago from Facturas f, Cancelaciones cc where f.codref_cliente = cc.codref_cliente and cc._id = %d ", aobj), null, null);
        crsfacturaspendientes.moveToFirst();
        return crsfacturaspendientes;
    }

    public String getDocumento()
    {
        return getString(getColumnIndexOrThrow("codref_factura"));
    }

    public int getFacturaEnPago()
    {
        return getInt(getColumnIndexOrThrow("en_pago"));
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

    public double getMontoDescuetoProntoPago()
    {
        return 0.0D;
    }

    public double getMontoEnPago()
    {
        return getDouble(getColumnIndexOrThrow("monto_en_pago"));
    }

    public double getMontoFacturado()
    {
        return getDouble(getColumnIndexOrThrow("total_facturado"));
    }

    public double getMontoPagar()
    {
        return getMontoPendiente() - getMontoDescuetoProntoPago() - getMontoRetencionIVA();
    }

    public double getMontoPendiente()
    {
        return getDouble(getColumnIndexOrThrow("total_pendiente"));
    }

    public double getMontoRetencionIVA()
    {
        return 0.75D * DBHandle.comprobanteIvaDeterminacionBase(getDocumento());
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
        textview3.setText(String.format(" * Pendiente Bs.: %s", aobj2));
        ((TextView)linearlayout.findViewById(0x7f06015d)).setText((new StringBuilder(" * Ret IVA (75%) Bs.: ")).append(MainActivity.formatVE(getMontoRetencionIVA())).toString());
        ((EditText)linearlayout.findViewById(0x7f06015e)).setText(Double.toString(getMontoPagar()));
        return linearlayout;
    }

    public static final String COLUMNA_DOCUMENTO = "codref_factura";
    public static final String COLUMNA_FACTURA_EN_PAGO = "en_pago";
    public static final String COLUMNA_FACTURA_ID = "_id";
    public static final String COLUMNA_FACTURA_MONTO_EN_PAGO = "monto_en_pago";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_MONTO_FACTURADO = "total_facturado";
    public static final String COLUMNA_MONTO_PENDIENTE = "total_pendiente";
    private static final String QUERY = "Select f._id, f.codref_factura, f.fecha, f.total as total_facturado, f.total as total_pendiente from Facturas f, Clientes c where f.estatus = 0 and f.codref_cliente = c.id_profitplus and c._id = %d ";
    private static final String QUERY_PAGO = "Select f._id, f.codref_factura, f.fecha, f.total as total_facturado, f.total as total_pendiente, (select count(*) from Cancelaciones_Facturas cf where cf.id_cancelacion = cc._id and cf.id_factura = f._id) as en_pago,(select monto_pago from Cancelaciones_Facturas cf where cf.id_cancelacion = cc._id and cf.id_factura = f._id) as monto_en_pago from Facturas f, Cancelaciones cc where f.codref_cliente = cc.codref_cliente and cc._id = %d ";
}
