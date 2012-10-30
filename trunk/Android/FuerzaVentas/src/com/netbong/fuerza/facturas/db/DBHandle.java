// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.facturas.db.cursors.CrsEventos;
import com.netbong.fuerza.facturas.db.cursors.CrsFactura;
import com.netbong.fuerza.facturas.db.cursors.CsrBancos;
import com.netbong.fuerza.facturas.db.cursors.CsrComprobantesIva;
import com.netbong.fuerza.facturas.db.cursors.CsrFacturasGeneradas;
import com.netbong.fuerza.facturas.db.cursors.CsrPagoRegistradoDatosCliente;
import com.netbong.fuerza.facturas.db.cursors.CsrPagoRegistradoDatosFormasPago;
import com.netbong.fuerza.facturas.db.cursors.CsrPagosRegistrados;

public class DBHandle
{

    public DBHandle()
    {
    }

    public static void actualizarEstatusFacturaSegunSaldoActual(String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update facturas set estatus = case when (select total_pendiente from facturas_saldos_pendientes where codref_factura = ?) <= 0 then 2 else 0 end where codref_factura = ? and estatus <> 1 ");
        sqlitestatement.bindString(1, s);
        sqlitestatement.bindString(2, s);
        sqlitestatement.executeInsert();
    }

    public static void actualizarSaldoPendienteFactura(String s, double d)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update facturas_saldos_pendientes set total_pendiente = total_pendiente - ? where codref_factura = ? ");
        sqlitestatement.bindDouble(1, d);
        sqlitestatement.bindString(2, s);
        sqlitestatement.executeInsert();
    }

    public static void comprobanteIvaActualizarDatos(String s, int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update facturas_comprobantes set id_cancelacion = ? where codref_factura = ? and id_cancelacion = 0 ;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.bindString(2, s);
        sqlitestatement.executeInsert();
    }

    public static void comprobanteIvaActualizarDatos(String s, String s1)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update facturas_comprobantes set comprobante = ? where codref_factura = ? ;");
        sqlitestatement.bindString(1, s1);
        sqlitestatement.bindString(2, s);
        sqlitestatement.executeInsert();
    }

    public static double comprobanteIvaDeterminacionBase(String s) {
    	double d = 0.0D;
    	
        if(!comprobanteIvaYaRegistrado(s)){
        	SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select cast( sum(total_iva_gravament) as text) from facturas_detalle where codref_factura = ?;");
            sqlitestatement.bindString(1, s);
            String s1 = sqlitestatement.simpleQueryForString();
            if(s1 != null && s1.length() > 0)
                d = Double.parseDouble(s1);
        }
        
        return d;
    }

    public static void comprobanteIvaEliminar()
    {
        MainActivity.mDbHelper.getWritableDatabase().compileStatement("delete from facturas_comprobantes where id_cancelacion = 0 ").executeInsert();
    }

    public static void comprobanteIvaEliminar(String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("delete from facturas_comprobantes where codref_factura = ? and id_cancelacion = 0 ");
        sqlitestatement.bindString(1, s);
        sqlitestatement.executeInsert();
    }

    public static void comprobanteIvaRegistrar(String s, double d)
    {
        comprobanteIvaEliminar(s);
        double d1 = d * 0.75D;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("insert into facturas_comprobantes(codref_factura, fecha, comprobante, monto_base, monto_retenido, id_cancelacion) values(?, datetime('now'), '', ?, ?, 0);");
        sqlitestatement.bindString(1, s);
        sqlitestatement.bindDouble(2, d);
        sqlitestatement.bindDouble(3, d1);
        sqlitestatement.executeInsert();
    }

    public static boolean comprobanteIvaYaRegistrado(String s)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from facturas_comprobantes where codref_factura = ? and id_cancelacion > 0; ");
        sqlitestatement.bindString(1, s);
        if(sqlitestatement.simpleQueryForLong() != 1L)
            flag = false;
        return flag;
    }

    public static void ficticiasCrearDesdePedidosGenerados()
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05000b)).executeInsert();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05000c)).executeInsert();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05000d)).executeInsert();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05000e)).executeInsert();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050010)).executeInsert();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05000f)).executeInsert();
    }

    public static CsrBancos obtenerBancos(String s)
    {
        return CsrBancos.getBancos(s);
    }

    public static CsrComprobantesIva obtenerComprobantes()
    {
        return CsrComprobantesIva.getComprobantes();
    }

    public static CsrPagoRegistradoDatosCliente obtenerDatosCliente(int i)
    {
        return CsrPagoRegistradoDatosCliente.getDatosCliente(i);
    }

    public static CrsFactura obtenerDatosFactura(int i)
    {
        return CrsFactura.getDatos(i);
    }

    public static CsrPagoRegistradoDatosFormasPago obtenerDatosFormasPago(int i)
    {
        return CsrPagoRegistradoDatosFormasPago.getFormasPago(i);
    }

    public static CrsEventos obtenerEventosFactura(int i)
    {
        return CrsEventos.getEventos(i);
    }

    public static CsrFacturasGeneradas obtenerFacturas()
    {
        return CsrFacturasGeneradas.getFacturas();
    }

    public static CsrFacturasGeneradas obtenerFacturas(String s)
    {
        return CsrFacturasGeneradas.getFacturas(s);
    }

    public static CsrFacturasGeneradas obtenerFacturas(String s, String s1, String s2, String s3, String s4, String s5)
    {
        return CsrFacturasGeneradas.getFacturas(s, s2, s1, s3, s4, s5);
    }

    public static CsrPagosRegistrados obtenerPagos()
    {
        return CsrPagosRegistrados.getPagos();
    }

    public static CsrPagosRegistrados obtenerPagos(String s)
    {
        return CsrPagosRegistrados.getPagos(s);
    }

    public static void pagoActualizarDatos(int i, String s, String s1, int j, double d)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update cancelaciones set fecha_pago = ?, observacion = ?, codref_cliente = (select id_profitplus from clientes where _id = ?), monto_total = ? where _id = ?");
        if(s == null)
            s = "\t";
        if(s1 == null)
            s1 = "\t";
        sqlitestatement.bindString(1, s);
        sqlitestatement.bindString(2, s1);
        sqlitestatement.bindLong(3, j);
        sqlitestatement.bindDouble(4, d);
        sqlitestatement.executeInsert();
    }

    public static boolean pagoAgregarFactura(int i, int j, double d)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("insert into Cancelaciones_Facturas(id_cancelacion, id_factura, monto_pago) values(?, ?, ?) ");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.bindLong(2, j);
        sqlitestatement.bindDouble(3, d);
        long l = sqlitestatement.executeInsert();
        Log.i("ID CANCELACION FACTURA GENERADO", Long.toString(l));
        if(l <= 0L)
            flag = false;
        return flag;
    }

    public static void pagoAgregarFacturaEvento(int i, int j, double d, String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("insert into facturas_eventos(codref_factura, fecha, evento) values((select codref_factura from facturas where _id = ?), date('now'), ?) ");
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindString(2, (new StringBuilder(String.valueOf(s))).append(MainActivity.formatVE(d)).toString());
        sqlitestatement.executeInsert();
    }

    public static boolean pagoAgregarFormaPago(int i, String s, String s1, String s2, double d, String s3)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("insert into Cancelaciones_Formas_Pagos(id_cancelacion, banco, forma, referencia, monto_pago, fecha_movimiento) values (?, ? , ?, ?, ?, ?); ");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.bindString(2, s);
        sqlitestatement.bindString(3, s1);
        sqlitestatement.bindString(4, s2);
        sqlitestatement.bindDouble(5, d);
        sqlitestatement.bindString(6, s3);
        long l = sqlitestatement.executeInsert();
        Log.i("ID CANCELACION FORMA PAGO GENERADO", Long.toString(l));
        if(l <= 0L)
            flag = false;
        return flag;
    }

    public static void pagoAnular(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement("update cancelaciones set estatus = 1 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
        SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement("update facturas_saldos_pendientes set total_pendiente  = total_pendiente + coalesce( ( select monto_pago from cancelaciones_facturas cf, facturas f where  cf.id_cancelacion = ? and cf.id_factura = f._id and f.codref_factura = facturas_saldos_pendientes.codref_factura ), 0) where codref_factura in ( select f.codref_factura from cancelaciones_facturas cf, facturas f where cf.id_cancelacion = ? and cf.id_factura = f._id ) ");
        sqlitestatement1.bindLong(1, i);
        sqlitestatement1.bindLong(2, i);
        sqlitestatement1.executeInsert();
        SQLiteStatement sqlitestatement2 = sqlitedatabase.compileStatement("update facturas set estatus = 0 where _id in (select id_factura from cancelaciones_facturas where id_cancelacion = ?) and estatus <> 1 ");
        sqlitestatement2.bindLong(1, i);
        sqlitestatement2.executeInsert();
    }

    public static boolean pagoPermitirAnular(int i)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from cancelaciones where sync = 0 and estatus = 0 and _id = ?;");
        sqlitestatement.bindLong(1, i);
        if(sqlitestatement.simpleQueryForLong() != 1L)
            flag = false;
        return flag;
    }

    public static boolean pagoPermitirRecuperar(int i)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from cancelaciones where sync = 0 and estatus = 1 and _id = ?;");
        sqlitestatement.bindLong(1, i);
        if(sqlitestatement.simpleQueryForLong() != 1L)
            flag = false;
        return flag;
    }

    public static void pagoQuitarFacturas(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("delete from Cancelaciones_Facturas where id_cancelacion = ? ");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static void pagoQuitarFormasPago(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("delete from Cancelaciones_Formas_Pagos where id_cancelacion = ? ");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static void pagoRecuperar(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement("update cancelaciones set estatus = 0 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
        SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement("update facturas_saldos_pendientes set total_pendiente  = total_pendiente - coalesce( ( select monto_pago from cancelaciones_facturas cf, facturas f where  cf.id_cancelacion = ? and cf.id_factura = f._id and f.codref_factura = facturas_saldos_pendientes.codref_factura ), 0) where codref_factura in ( select f.codref_factura from cancelaciones_facturas cf, facturas f where cf.id_cancelacion = ? and cf.id_factura = f._id ) ");
        sqlitestatement1.bindLong(1, i);
        sqlitestatement1.bindLong(2, i);
        sqlitestatement1.executeInsert();
        SQLiteStatement sqlitestatement2 = sqlitedatabase.compileStatement("update facturas set estatus = 2 where _id in (select id_factura from cancelaciones_facturas where id_cancelacion = ?) and estatus <> 1 and coalesce((select total_pendiente from facturas_saldos_pendientes fsp where fsp.codref_factura = facturas.codref_factura), 1) <= 0 ");
        sqlitestatement2.bindLong(1, i);
        sqlitestatement2.executeInsert();
    }

    public static int pagoRegistrarNueva(String s, String s1, int i, double d)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("insert into Cancelaciones(fecha_pago, observacion, codref_cliente, monto_total) values(?, ?, (select id_profitplus from clientes where _id = ?), ?) ");
        if(s == null)
            s = "\t";
        if(s1 == null)
            s1 = "\t";
        sqlitestatement.bindString(1, s);
        sqlitestatement.bindString(2, s1);
        sqlitestatement.bindLong(3, i);
        sqlitestatement.bindDouble(4, d);
        long l = sqlitestatement.executeInsert();
        Log.i("ID CANCELACION GENERADO", Long.toString(l));
        return (int)l;
    }

    public static int totalComprobantesIvaNoConfigurado()
    {
        return (int)MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from facturas_comprobantes where id_cancelacion = 0 and length(comprobante) = 0; ").simpleQueryForLong();
    }

    public static long totalFacturasPendientesPorCliente(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from Facturas f, clientes c where f.estatus = 0 and f.codref_cliente = c.id_profitplus and c._id = ?;");
        sqlitestatement.bindLong(1, i);
        return sqlitestatement.simpleQueryForLong();
    }
}
