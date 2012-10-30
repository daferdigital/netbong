// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.CursorProductosEnPedido;

public class DBHandle
{

    public DBHandle()
    {
    }

    public static int actualizarItemsDetallePedidoModificado(int i)
    {
        MainActivity.mDbHelper.getWritableDatabase().execSQL((new StringBuilder("delete from Pedidos_Detalle where id_pedido = ")).append(Integer.toString(i)).toString());
        MainActivity.mDbHelper.llenarDetallePedido(i);
        return 0;
    }

    public static int actualizarObservacionPedidoModificado(int i, String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update Pedidos set observacion = ? where _id = ?");
        sqlitestatement.bindString(1, s);
        sqlitestatement.bindLong(2, i);
        sqlitestatement.executeInsert();
        return 0;
    }

    public static void anular(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement("update pedidos set status = 1 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
        SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement("insert into Pedidos_Eventos (id_pedido, fecha, evento) values(?, datetime('now'), 'Pedido Anulado.')");
        sqlitestatement1.bindLong(1, i);
        sqlitestatement1.executeInsert();
        SQLiteStatement sqlitestatement2 = sqlitedatabase.compileStatement("update productos set stock = stock + ( select sum(cantidad) from Pedidos_Detalle  pd where pd.id_pedido = ? and pd.id_producto = productos._id) ");
        sqlitestatement2.bindLong(1, i);
        sqlitestatement2.executeInsert();
    }

    public static int autorizacionLimiteCreditoId(String s, String s1, String s2)
    {
        String s3 = MainActivity.mainCtx.getResources().getString(0x7f050001);
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(s3);
        sqlitestatement.bindString(1, s);
        sqlitestatement.bindString(2, s1);
        sqlitestatement.bindString(3, s2);
        return (int)sqlitestatement.simpleQueryForLong();
    }

    public static boolean autorizacionLimiteCreditoValidar(String s, String s1, String s2)
    {
        boolean flag = true;
        String s3 = MainActivity.mainCtx.getResources().getString(0x7f050000);
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(s3);
        sqlitestatement.bindString(1, s);
        sqlitestatement.bindString(2, s1);
        sqlitestatement.bindString(3, s2);
        if(sqlitestatement.simpleQueryForLong() <= 0L)
            flag = false;
        return flag;
    }

    public static void autorizacionPrecioMarcarComoUtilizada(int i, int j)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050004));
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindLong(2, i);
        sqlitestatement.executeInsert();
    }

    public static boolean carritoAbierto()
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f050020);
        boolean flag;
        if(MainActivity.mDbHelper.getWritableDatabase().compileStatement(s).simpleQueryForLong() > 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void carritoAgregarProducto(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05001b));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static void carritoAgregarProducto(int i, double d, int j, double d1, double d2, 
            String s, int k, int l)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        if(l == 0)
        {
            SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050014));
            sqlitestatement1.bindLong(1, i);
            sqlitestatement1.bindDouble(2, d);
            sqlitestatement1.bindLong(3, j);
            sqlitestatement1.bindDouble(4, d1);
            sqlitestatement1.bindDouble(5, d2);
            sqlitestatement1.bindString(6, s);
            sqlitestatement1.bindLong(7, k);
            sqlitestatement1.executeInsert();
        } else
        {
            SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050031));
            sqlitestatement.bindDouble(1, d);
            sqlitestatement.bindLong(2, j);
            sqlitestatement.bindDouble(3, d1);
            sqlitestatement.bindString(4, s);
            sqlitestatement.bindLong(5, k);
            sqlitestatement.bindLong(6, l);
            sqlitestatement.executeInsert();
        }
    }

    public static void carritoAgregarPromocion()
    {
    }

    public static void carritoAsignarAutorizacionLimiteCredito(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050037));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static void carritoCambiarCliente(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050033));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static void carritoCambiarObservacion(String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050034));
        sqlitestatement.bindString(1, s);
        sqlitestatement.executeInsert();
    }

    public static void carritoCambiarObservacionProntoPago(String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050035));
        sqlitestatement.bindString(1, s);
        sqlitestatement.executeInsert();
    }

    public static void carritoCerrar()
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050011)).executeInsert();
        sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050012)).executeInsert();
    }

    public static void carritoCrear(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050015));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static void carritoCrear(int i, int j, String s, String s1, String s2)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05001a));
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindString(2, s2);
        sqlitestatement.bindString(3, s);
        sqlitestatement.bindString(4, s1);
        sqlitestatement.bindLong(5, j);
        sqlitestatement.bindString(6, s);
        sqlitestatement.bindString(7, s1);
        sqlitestatement.bindLong(8, i);
        sqlitestatement.bindLong(9, i);
        sqlitestatement.executeInsert();
    }

    public static double carritoDeterminarItemsTotalesBs()
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f050025);
        return Double.parseDouble(MainActivity.mDbHelper.getWritableDatabase().compileStatement(s).simpleQueryForString());
    }

    public static void carritoEliminarItem(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050032));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static double carritoTotalGeneralAnterior()
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f050030);
        return Double.parseDouble(MainActivity.mDbHelper.getWritableDatabase().compileStatement(s).simpleQueryForString());
    }

    public static void clienteBajarLimiteCredito(int i, double d)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050009));
        sqlitestatement.bindDouble(1, d);
        sqlitestatement.bindLong(2, i);
        sqlitestatement.executeInsert();
    }

    public static void clienteSubirLimiteCredito(int i, double d)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05000a));
        sqlitestatement.bindDouble(1, d);
        sqlitestatement.bindLong(2, i);
        sqlitestatement.executeInsert();
    }

    public static int crearEventosDeModificacionPedidos(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("insert into Pedidos_Eventos (id_pedido, fecha, evento) select %d, datetime('now'), modificacion from Pedidos_Modificaciones; ", aobj);
        MainActivity.mDbHelper.getWritableDatabase().execSQL(s);
        return 0;
    }

    public static void llenarCarritoItemsPedido(int i)
    {
        CursorProductosEnPedido cursorproductosenpedido = MainActivity.mDbHelper.getListadoProductosEnPedido(i);
        Log.v("prods en pedid", Integer.toString(cursorproductosenpedido.getCount()));
        do
            MainActivity.mDbHelper.agregarProductoCarrito(cursorproductosenpedido.getId(), cursorproductosenpedido.getPrecio(), cursorproductosenpedido.getCantidad(), cursorproductosenpedido.getIva());
        while(cursorproductosenpedido.moveToNext());
    }

    public static String obtenerObservacionPedido(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select observacion from Pedidos where _id = ? ");
        sqlitestatement.bindLong(1, i);
        String s = sqlitestatement.simpleQueryForString();
        if(s == null)
            s = "";
        return s;
    }

    public static void pedidoCrearAgregarEvento(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050018));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static void pedidoCrearAgregarItems(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050017));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public static int pedidoCrearNuevo()
    {
        return (int)MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050016)).executeInsert();
    }

    private static void pedidoModificarAgregarEvento(int i, String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050019));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.bindString(2, s);
        sqlitestatement.executeInsert();
    }

    public static void pedidoModificarAgregarEventos(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        if(pedidoModificarClienteCambio())
        {
            String s4 = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050028)).simpleQueryForString().trim();
            String s5 = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050027)).simpleQueryForString().trim();
            Object aobj2[] = new Object[2];
            aobj2[0] = s5;
            aobj2[1] = s4;
            pedidoModificarAgregarEvento(i, String.format("El cliente fue cambiado. Actual:%s. Anterior:%s", aobj2));
        }
        if(pedidoModificarObservacionCambio())
        {
            String s2 = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05002d)).simpleQueryForString().trim();
            String s3 = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05002c)).simpleQueryForString().trim();
            Object aobj1[] = new Object[2];
            aobj1[0] = s3;
            aobj1[1] = s2;
            pedidoModificarAgregarEvento(i, String.format("La observacion fue modificada. Actual:%s. Anterior:%s", aobj1));
        }
        if(pedidoModificarObservacionProntoPagoCambio())
        {
            String s = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05002f)).simpleQueryForString().trim();
            String s1 = sqlitedatabase.compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05002e)).simpleQueryForString().trim();
            Object aobj[] = new Object[2];
            aobj[0] = s1;
            aobj[1] = s;
            pedidoModificarAgregarEvento(i, String.format("La observacion Pronto Pago fue modificada. Actual:%s. Anterior:%s", aobj));
        }
    }

    public static void pedidoModificarCabecera(int i, int j, String s, String s1)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050036));
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindString(2, s);
        sqlitestatement.bindString(3, s1);
        sqlitestatement.bindLong(4, i);
        sqlitestatement.executeInsert();
    }

    private static boolean pedidoModificarClienteCambio()
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f050029);
        boolean flag;
        if(MainActivity.mDbHelper.getWritableDatabase().compileStatement(s).simpleQueryForLong() > 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void pedidoModificarEliminarDetalle(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f050013));
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    private static boolean pedidoModificarObservacionCambio()
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f05002a);
        boolean flag;
        if(MainActivity.mDbHelper.getWritableDatabase().compileStatement(s).simpleQueryForLong() > 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean pedidoModificarObservacionProntoPagoCambio()
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f05002b);
        boolean flag;
        if(MainActivity.mDbHelper.getWritableDatabase().compileStatement(s).simpleQueryForLong() > 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean pedidoPermitirAnular(int i)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from Pedidos where sync = 0 and status = 0 and _id = ?;");
        sqlitestatement.bindLong(1, i);
        if(sqlitestatement.simpleQueryForLong() != 1L)
            flag = false;
        return flag;
    }

    public static boolean pedidoPermitirRecuperar(int i)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from Pedidos where status = 1 and _id = ?;");
        sqlitestatement.bindLong(1, i);
        if(sqlitestatement.simpleQueryForLong() != 1L)
            flag = false;
        return flag;
    }

    public static boolean pedidopagoPermitirRecuperar(int i)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select count(*) from Pedidos where sync = 0 and status = 1 and _id = ?;");
        sqlitestatement.bindLong(1, i);
        if(sqlitestatement.simpleQueryForLong() != 1L)
            flag = false;
        return flag;
    }

    public static void productoBajarStock(int i, int j)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05003e));
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindLong(2, i);
        sqlitestatement.executeInsert();
    }

    public static void productoSubirStock(int i, int j)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(MainActivity.mainCtx.getResources().getString(0x7f05003f));
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindLong(2, i);
        sqlitestatement.executeInsert();
    }

    public static void recuperar(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement("update pedidos set status = 0 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
        SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement("insert into Pedidos_Eventos (id_pedido, fecha, evento) values(?, datetime('now'), 'Pedido Recuperado.')");
        sqlitestatement1.bindLong(1, i);
        sqlitestatement1.executeInsert();
    }
}
