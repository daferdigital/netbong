// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.content.*;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;

import com.netbong.fuerza.MainActivity;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Referenced classes of package com.ehp.droidsf.db:
//            CursorDevolucionesArticulosNoDevueltos, CursorClientes, CursorDatosProductoCatalogo, CursorDatosResumenPedido, 
//            CursorDevoluciones, CursorClienteDocumentosFisicos, CursorCatalogoImagenes, CursorCarritoPedido, 
//            CursorClientesConFacturas, CursorClientesConPedidos, CursorPromosDetalle, CursorEventosPedido, 
//            CursorFacturas, CursorLineasProducto, CursorPedidos, CursorCatalogo, 
//            CursorProductosEnFactura, CursorProductosEnPedido, CursorPromos, CursorSyncDatosPedido, 
//            CursorCarritoPedidoTotales, CursorEventosFactura

public class DroidSFDatabase extends SQLiteOpenHelper
{

    private DroidSFDatabase(Context context, String s)
    {
        super(context, s, null, 1);
        mContext = context;
    }

    private void execMultipleSQL(SQLiteDatabase sqlitedatabase, String as[])
    {
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return;
            String s = as[j];
            if(s.trim().length() > 0)
                sqlitedatabase.execSQL(s);
            j++;
        } while(true);
    }

    private void execSQL(SQLiteDatabase sqlitedatabase, String s)
    {
        if(s.trim().length() > 0)
            sqlitedatabase.execSQL(s);
    }

    public static final DroidSFDatabase getDroidSFDatabase(Context context)
    {
        return new DroidSFDatabase(context, MainActivity.settings.getString("db-path", null));
    }

    public int actualizarCantidadArticulosItemCarritoPorIDItem(int i, int j, double d, int k)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(j);
        aobj[1] = Double.valueOf(d);
        aobj[2] = Integer.valueOf(k);
        aobj[3] = Integer.valueOf(i);
        execSQL(sqlitedatabase, String.format("update Carrito_Productos set cantidad = %d, precio = %f, id_autorizacion_precio = %d where _id = %d ", aobj));
        return 0;
    }

    public int actualizarCantidadArticulosItemCarritoPorProducto(int i, int j, double d, int k)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(j);
        aobj[1] = Double.valueOf(d);
        aobj[2] = Integer.valueOf(k);
        aobj[3] = Integer.valueOf(i);
        execSQL(sqlitedatabase, String.format("update Carrito_Productos set cantidad = %d, precio = %f, id_autorizacion_precio = %d where id_producto = %d and proviene_de_promocion = 0", aobj));
        return 0;
    }

    public int agregarAutorizacionPrecioProducto(int i, int j, String s, double d)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement("select count(*) from AutorizacionesPrecios where codigo_autorizacion = ? ");
        sqlitestatement.bindLong(1, i);
        int k;
        if(sqlitestatement.simpleQueryForLong() > 0L)
        {
            SQLiteStatement sqlitestatement2 = sqlitedatabase.compileStatement("select _id from AutorizacionesPrecios where codigo_autorizacion = ? ");
            sqlitestatement2.bindLong(1, i);
            k = (int)sqlitestatement2.simpleQueryForLong();
        } else
        {
            SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement("insert into AutorizacionesPrecios(codigo_autorizacion, id_profitplus_producto, precio)  values(?, ?,  ?) ");
            sqlitestatement1.bindLong(1, i);
            sqlitestatement1.bindString(2, s);
            sqlitestatement1.bindDouble(3, d);
            k = (int)sqlitestatement1.executeInsert();
        }
        return k;
    }

    public int agregarProductoCarrito(int i, double d, int j, double d1) {
    	int k = 0;
        
    	try {
    		ContentValues contentvalues;
            contentvalues = new ContentValues();
            contentvalues.put("id_producto", Integer.valueOf(i));
            contentvalues.put("precio", Double.valueOf(d));
            contentvalues.put("cantidad", Integer.valueOf(j));
            contentvalues.put("iva", Double.valueOf(d1));
            getWritableDatabase().insert("Carrito_Productos", null, contentvalues);
            Log.v("agregarProductoCarrito", Integer.toString(i));
            k = 1;
		} catch (SQLException e) {
			// TODO: handle exception
			Log.e("agregarProductoCarrito", "Error " + e.getLocalizedMessage(), e);
		}
        
        return k;
    
    }

    public int agregarProductoCarrito(int i, double d, int j, double d1, int k, 
            int l) {
    	int i1 = 0;
    	try {
    		ContentValues contentvalues;
            contentvalues = new ContentValues();
            contentvalues.put("id_producto", Integer.valueOf(i));
            contentvalues.put("precio", Double.valueOf(d));
            contentvalues.put("cantidad", Integer.valueOf(j));
            contentvalues.put("iva", Double.valueOf(d1));
            contentvalues.put("id_autorizacion_precio", Integer.valueOf(l));
            getWritableDatabase().insert("Carrito_Productos", null, contentvalues);
            i1 = 1;
		} catch (SQLException e) {
			// TODO: handle exception
			Log.e("Carrito_Productos", "Error " + e.getLocalizedMessage(), e);
		}
    	
    	return i1;
    }

    public boolean autorizacionPrecioProductoUtilizada(int i)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select utilizado from AutorizacionesPrecios where _id = ? ");
        sqlitestatement.bindLong(1, i);
        if(sqlitestatement.simpleQueryForLong() != 1L)
            flag = false;
        return flag;
    }

    public int clienteActualizarExistente(int i, int j, String s, String s1, String s2, String s3, String s4, 
            String s5, String s6, String s7, String s8)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update Clientes set id_zona_cliente = ?, id_tipo_cliente = ?, nombre = ?, rif = ?, direccion_fiscal = ?, direccion_envio_mercancia = ?, contacto_persona = ?, contacto_telefono = ?, contacto_fax = ?, contacto_correo = ?, dias_caja = ? where _id = ? ");
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindLong(2, 1);
        sqlitestatement.bindString(3, s);
        sqlitestatement.bindString(4, s1);
        sqlitestatement.bindString(5, s2);
        sqlitestatement.bindString(6, s3);
        sqlitestatement.bindString(7, s4);
        sqlitestatement.bindString(8, s5);
        sqlitestatement.bindString(9, s6);
        sqlitestatement.bindString(10, s7);
        sqlitestatement.bindString(11, s8);
        sqlitestatement.bindLong(12, i);
        sqlitestatement.executeInsert();
        return 0;
    }

    public int clienteActualizarSoporteFisico(int i, int j, int k, int l, int i1, int j1, String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update Documentos_Fisicos_Cliente set registro_mercantil = ?, rif = ?, copia_cedula = ?, referencias_comerciales = ?, referencias_bancarias = ?, observaciones = ? where id_cliente = ? ");
        sqlitestatement.bindLong(1, j);
        sqlitestatement.bindLong(2, k);
        sqlitestatement.bindLong(3, l);
        sqlitestatement.bindLong(4, i1);
        sqlitestatement.bindLong(5, j1);
        sqlitestatement.bindString(6, s);
        sqlitestatement.bindLong(7, i);
        sqlitestatement.executeInsert();
        return 0;
    }

    public int clienteCrearNuevo(int i, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, String s8)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement("insert into Clientes(id_profitplus, id_zona_cliente, id_tipo_cliente, nombre, rif, direccion_fiscal, direccion_envio_mercancia, contacto_persona, contacto_telefono, contacto_fax, contacto_correo, dias_caja, estatus)values('NONE', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 2)");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.bindLong(2, 1);
        sqlitestatement.bindString(3, s);
        sqlitestatement.bindString(4, s1);
        sqlitestatement.bindString(5, s2);
        sqlitestatement.bindString(6, s3);
        sqlitestatement.bindString(7, s4);
        sqlitestatement.bindString(8, s5);
        sqlitestatement.bindString(9, s6);
        sqlitestatement.bindString(10, s7);
        sqlitestatement.bindString(11, s8);
        long l = sqlitestatement.executeInsert();
        Log.i("ID CLIENTE GENERADO", Long.toString(l));
        SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement("insert into Documentos_Fisicos_Cliente(id_cliente) values(?)");
        sqlitestatement1.bindLong(1, l);
        sqlitestatement1.executeInsert();
        return (int)l;
    }

    public long clientesPendientesPorSync()
    {
        return getWritableDatabase().compileStatement("select count(*) from clientes where sync = 0;").simpleQueryForLong();
    }

    public void copyDataBase()
        throws IOException
    {
        FileInputStream fileinputstream = new FileInputStream((new StringBuilder(String.valueOf(MainActivity.mainCtx.getFilesDir().getAbsolutePath()))).append("/").append("sfdroid.sqlite").toString());
        FileOutputStream fileoutputstream = new FileOutputStream("sfdroid.sqlite");
        byte abyte0[] = new byte[1024];
        do
        {
            int i = fileinputstream.read(abyte0);
            if(i <= 0)
            {
                fileoutputstream.flush();
                fileoutputstream.close();
                fileinputstream.close();
                return;
            }
            fileoutputstream.write(abyte0, 0, i);
            Log.v("Copiando Datos en DB", "Copiando Datos en DB en progreso...");
        } while(true);
    }

    public int crearCarrito()
    {
        execSQL(getWritableDatabase(), "delete from Carrito_Productos;");
        return 0;
    }

    public boolean crearDocumentoDigitalCliente(int i, String s, String s1)
    {
        boolean flag = true;
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("insert into Documentos_Digitales_Cliente (id_cliente, tipo,  uri,  tag) values(?, 0, ?, ?)");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.bindString(2, s);
        sqlitestatement.bindString(3, s1);
        if(sqlitestatement.executeInsert() <= 0L)
            flag = false;
        return flag;
    }

    public void crearFactura()
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        sqlitedatabase.execSQL("insert into Facturas(id_cliente, numero_factura, fecha, total, estatus) select id_cliente, 'FACT-', datetime('now'), _id, 0 from Pedidos where status = 0 ");
        sqlitedatabase.execSQL("insert into Facturas_Detalle(id_factura, id_producto, cantidad, iva, total) select f._id, pd.id_producto, pd.cantidad, pd.iva, pd.precio from Facturas f, PedidosDetalle pd, Pedidos p where f.total = pd.id_pedido and f.total = p._id and p.status = 0 ");
        sqlitedatabase.execSQL("insert into Facturas_eventos(id_factura, fecha, evento) select f._id, datetime('now'), 'Nueva Factura Generada' from Facturas f, Pedidos p where f.total = p._id and p.status = 0 ");
        sqlitedatabase.execSQL("update Pedidos set status = 2 where status = 0");
    }

    public int crearFacturaAbonos(int i, int j, double d, int k, String s)
    {
    	int l = 0;
        
    	try {
    		ContentValues contentvalues;
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            contentvalues = new ContentValues();
            contentvalues.put("id_factura", Integer.valueOf(i));
            contentvalues.put("fecha", simpledateformat.format(new Date()));
            contentvalues.put("monto", Double.valueOf(d));
            contentvalues.put("forma", Integer.valueOf(k));
            contentvalues.put("banco", Integer.valueOf(j));
            contentvalues.put("referencia", s);
            getWritableDatabase().insert("Cancelaciones", null, contentvalues);
            Log.v("Creado Abono", "OK!!");
            Object aobj[] = new Object[1];
            aobj[l] = Double.valueOf(d);
            crearFacturaEvento(i, String.format("Registro de abono por Bs. %1$.2f", aobj));
            l = 1;
		} catch (SQLException e) {
			// TODO: handle exception
			Log.e("crearFacturaAbonos", "Error " + e.getLocalizedMessage(), e);
		}
        
        return l;
    }

    public int crearFacturaAbonos(int i, String s, double d, String s1, String s2, String s3, 
            String s4)
    {
    	int j = 0;
    	try {
    		ContentValues contentvalues;
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            contentvalues = new ContentValues();
            contentvalues.put("id_factura", Integer.valueOf(i));
            contentvalues.put("fecha_registro", simpledateformat.format(new Date()));
            contentvalues.put("monto", Double.valueOf(d));
            contentvalues.put("forma", s2);
            contentvalues.put("banco", s);
            contentvalues.put("referencia", s3);
            contentvalues.put("observacion", s4);
            contentvalues.put("fecha_pago", s1);
            getWritableDatabase().insert("Cancelaciones", null, contentvalues);
            Log.v("Creado Abono", "OK!!");
            Object aobj[] = new Object[4];
            aobj[0] = Double.valueOf(d);
            aobj[1] = s2;
            aobj[2] = s;
            aobj[3] = s3;
            crearFacturaEvento(i, String.format("Registro de pago por Bs. %.2f (Forma: %s/ Banco: %s/ Ref: %s)", aobj));
            j = 1;
		} catch (SQLException e) {
			// TODO: handle exception
			Log.e("crearFacturaAbonos", "Error " + e.getLocalizedMessage(), e);
		}
        
        return j;
    }

    public int crearFacturaEvento(int i, String s)
    {
    	int j = 0;
    	
    	try {
    		ContentValues contentvalues;
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            contentvalues = new ContentValues();
            contentvalues.put("id_factura", Integer.valueOf(i));
            contentvalues.put("fecha", simpledateformat.format(new Date()));
            contentvalues.put("evento", s);
            getWritableDatabase().insert("Facturas_Eventos", null, contentvalues);
            Log.v("Creado Factura Evento", "OK!!");
            j = 1;
		} catch (SQLException e) {
			// TODO: handle exception
			Log.e("crearFacturaEvento", "Error " + e.getLocalizedMessage(), e);
		}
        
    	return j;
    }

    public int crearPedido(int i, String s)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        SQLiteStatement sqlitestatement = sqlitedatabase.compileStatement("insert into Pedidos(id_cliente, total_excento, total_gravament, total_iva_gravament, observacion) values(?, coalesce((select sum(precio * cantidad) from Carrito_Productos where iva = 0), 0), coalesce((select sum(precio * cantidad) from Carrito_Productos where iva > 0), 0), coalesce((select sum(precio * cantidad * iva / 100) from Carrito_Productos where iva > 0), 0), ?)");
        if(s == null)
            s = "";
        sqlitestatement.bindLong(1, i);
        sqlitestatement.bindString(2, s);
        long l = sqlitestatement.executeInsert();
        Log.i("ID PEDIDO GENERADO", Long.toString(l));
        sqlitedatabase.execSQL("insert into PedidosDetalle( id_pedido, id_producto, iva, precio, cantidad, total_excento, total_gravament, total_iva_gravament) select (select max(_id) from Pedidos), id_producto, iva, precio, cantidad, case when iva = 0 then precio * cantidad else 0 end, case when iva > 0 then precio * cantidad else 0 end, case when iva > 0 then precio * cantidad * iva / 100 else 0 end from Carrito_Productos ");
        sqlitedatabase.execSQL((new StringBuilder(" update AutorizacionesPrecios  set utilizado = 1, id_pedido = ")).append(Long.toString(l)).append(" where ").append(" _id in (select id_autorizacion_precio from Carrito where id_autorizacion_precio > 0) ").toString());
        sqlitedatabase.execSQL("insert into PedidosEventos (id_pedido, fecha, evento) values((select max(_id) from Pedidos), datetime('now'), 'Nuevo Pedido generado.')");
        return (int)l;
    }

    public long devolucionesPendientesPorSync()
    {
        return getWritableDatabase().compileStatement("select count(*) from devoluciones where sync = 0;").simpleQueryForLong();
    }

    public int eliminarArticuloCarrito(int i)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        execSQL(sqlitedatabase, String.format("delete from Carrito_Productos where _id = %d;", aobj));
        return 0;
    }

    public int eliminarDocumentoDigital(int i)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        execSQL(sqlitedatabase, String.format("delete from Documentos_Digitales_Cliente where _id = %d;", aobj));
        return 0;
    }

    public void execSQL(String s)
    {
        if(s != null)
            execSQL(getWritableDatabase(), s);
    }

    public void execSQLFromFile(String s)
        throws IOException
    {
        SQLiteDatabase sqlitedatabase;
        Scanner scanner;
        sqlitedatabase = getWritableDatabase();
        scanner = new Scanner(new FileInputStream((new StringBuilder(String.valueOf(MainActivity.mainCtx.getFilesDir().getAbsolutePath()))).append("/").append(s).toString()), "UTF-8");
        
        while(scanner.hasNextLine()){
        	try {
        		execSQL(sqlitedatabase, scanner.nextLine());
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				scanner.close();
			}
        }
    }

    public String generarNombreDocumentosDigitalCliente(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select max(_id) from Documentos_Digitales_Cliente where id_cliente = ? ");
        sqlitestatement.bindLong(1, i);
        long l = sqlitestatement.simpleQueryForLong();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Long.valueOf(l);
        return String.format("ID_PROFIT_%d_%d.jpg", aobj);
    }

    public CursorDevolucionesArticulosNoDevueltos getArticulosNoDevuelto(int i)
    {
        return CursorDevolucionesArticulosNoDevueltos.getArtivulosNoDevueltos(getReadableDatabase(), i);
    }

    public CursorClientes getDatosCliente(int i)
    {
        return CursorClientes.getCliente(getReadableDatabase(), i);
    }

    public CursorDatosProductoCatalogo getDatosProductoCatalogoPorID(int i, int j)
    {
        return CursorDatosProductoCatalogo.getDatosProductoPorID(getReadableDatabase(), i, j);
    }

    public CursorDatosProductoCatalogo getDatosProductoCatalogoPorItemCarrito(int i, int j)
    {
        return CursorDatosProductoCatalogo.getDatosProductoPorItemCarrito(getReadableDatabase(), i, j);
    }

    public CursorDatosResumenPedido getDatosResumenPedido(int i)
    {
        return CursorDatosResumenPedido.getDatosResumenPedido(getReadableDatabase(), i);
    }

    public CursorDevoluciones getDevolucionesRegistradasPorFactura(int i)
    {
        return CursorDevoluciones.getDevoluciones(getReadableDatabase(), i);
    }

    public CursorClienteDocumentosFisicos getDocumentosFisicosCliente(int i)
    {
        return CursorClienteDocumentosFisicos.getDcumentosFisicos(getReadableDatabase(), i);
    }

    public CursorCatalogoImagenes getImagenesDeProductos(int i)
    {
        return CursorCatalogoImagenes.getImagenes(getReadableDatabase(), i);
    }

    public CursorCarritoPedido getListadoArticulosCarritoPedido()
    {
        return CursorCarritoPedido.getArticulos(getReadableDatabase());
    }

    public CursorClientes getListadoClientes(CursorClientes.SortBy sortby)
    {
        return CursorClientes.getClientes(getReadableDatabase(), sortby);
    }

    public CursorClientes getListadoClientes(String s, CursorClientes.SortBy sortby)
    {
        return CursorClientes.getClientes(getReadableDatabase(), s, sortby);
    }

    public CursorClientesConFacturas getListadoClientesConFacturas()
    {
        return CursorClientesConFacturas.getClientes(getReadableDatabase());
    }

    public CursorClientesConPedidos getListadoClientesConPedidos()
    {
        return CursorClientesConPedidos.getClientes(getReadableDatabase());
    }

    public CursorPromosDetalle getListadoDetallePromocion(int i)
    {
        return CursorPromosDetalle.getDetallePromo(getReadableDatabase(), i);
    }

    public CursorEventosFactura getListadoEventosEnFactura2(int i)
    {
        return null;
    }

    public CursorEventosPedido getListadoEventosEnPedido(int i)
    {
        return CursorEventosPedido.getEventos(getReadableDatabase(), i);
    }

    public CursorFacturas getListadoFacturasGeneradas()
    {
        return CursorFacturas.getFacturas(getReadableDatabase());
    }

    public CursorLineasProducto getListadoLineasProducto()
    {
        return CursorLineasProducto.getLineasProducto(getReadableDatabase());
    }

    public CursorLineasProducto getListadoLineasProducto(String s)
    {
        return CursorLineasProducto.getLineasProducto(getReadableDatabase(), s);
    }

    public CursorPedidos getListadoPedidosDeCliente()
    {
        return CursorPedidos.getPedidos(getReadableDatabase());
    }

    public CursorPedidos getListadoPedidosDeCliente(int i)
    {
        return CursorPedidos.getPedidos(getReadableDatabase(), i);
    }

    public CursorPedidos getListadoPedidosDeCliente(String s)
    {
        return CursorPedidos.getPedidos(getReadableDatabase(), s);
    }

    public CursorPedidos getListadoPedidosDeCliente(String s, String s1, String s2, String s3, String s4, String s5, String s6)
    {
        return CursorPedidos.getPedidos(getReadableDatabase(), s, s1, s2, s3, s4, s5, s6);
    }

    public CursorCatalogo getListadoProductos(int i, String s, int j)
    {
        return CursorCatalogo.getProductos(getReadableDatabase(), i, s, j);
    }

    public CursorCatalogo getListadoProductos(CursorCatalogo.SortBy sortby)
    {
        return CursorCatalogo.getProductos(getReadableDatabase(), sortby);
    }

    public CursorCatalogo getListadoProductos(CursorCatalogo.SortBy sortby, String s)
    {
        return CursorCatalogo.getProductos(getReadableDatabase(), sortby, s);
    }

    public CursorProductosEnFactura getListadoProductosEnFactura(int i)
    {
        return CursorProductosEnFactura.getProductos(getReadableDatabase(), i);
    }

    public CursorProductosEnPedido getListadoProductosEnPedido(int i)
    {
        return CursorProductosEnPedido.getProductos(getReadableDatabase(), i);
    }

    public CursorPromos getListadoPromociones()
    {
        return CursorPromos.getPromos(getReadableDatabase());
    }

    public double getPrecioAutorizacion(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("select cast(precio as text) from AutorizacionesPrecios where _id = ? ");
        sqlitestatement.bindLong(1, i);
        return Double.parseDouble(sqlitestatement.simpleQueryForString());
    }

    public CursorSyncDatosPedido getSyncDatosPedido(int i, String s, String s1)
    {
        return CursorSyncDatosPedido.getDatosPedido(getReadableDatabase(), i, s, s1);
    }

    public CursorCarritoPedidoTotales getTotalesCarritoPedido()
    {
        return CursorCarritoPedidoTotales.getTotales(getReadableDatabase());
    }

    public int llenarDetallePedido(int i)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        sqlitedatabase.execSQL((new StringBuilder("insert into PedidosDetalle( id_pedido, id_producto, iva, precio, cantidad, total_excento, total_gravament, total_iva_gravament, promo) select ")).append(Integer.toString(i)).append(", ").append("id_promo, ").append("iva, ").append("precio, ").append("cantidad, ").append("case when iva = 0 then precio * cantidad else 0 end, ").append("case when iva > 0 then precio * cantidad else 0 end, ").append("case when iva > 0 then precio * cantidad * iva / 100 else 0 end, 1 ").append("from ").append("Carrito_Promos ").toString());
        sqlitedatabase.execSQL((new StringBuilder("insert into PedidosDetalle( id_pedido, id_producto, iva, precio, cantidad, total_excento, total_gravament, total_iva_gravament) select ")).append(Integer.toString(i)).append(", ").append("id_producto, ").append("iva, ").append("precio, ").append("cantidad, ").append("case when iva = 0 then precio * cantidad else 0 end, ").append("case when iva > 0 then precio * cantidad else 0 end, ").append("case when iva > 0 then precio * cantidad * iva / 100 else 0 end ").append("from ").append("Carrito_Productos ").toString());
        sqlitedatabase.execSQL((new StringBuilder("update Pedidos set total_excento = coalesce((select sum(precio * cantidad) from Carrito_Productos where iva = 0), 0) + coalesce((select sum(precio * cantidad) from Carrito_Promos where iva = 0), 0), total_gravament = coalesce((select sum(precio * cantidad) from Carrito_Productos where iva > 0), 0) + coalesce((select sum(precio * cantidad) from Carrito_promos where iva > 0), 0), total_iva_gravament = coalesce((select sum(precio * cantidad * iva / 100) from Carrito_Productos where iva > 0), 0) + coalesce((select sum(precio * cantidad * iva / 100) from Carrito_Promos where iva > 0), 0) where _id = ")).append(Integer.toString(i)).toString());
        sqlitedatabase.execSQL((new StringBuilder("insert into PedidosEventos (id_pedido, fecha, evento) values(")).append(Integer.toString(i)).append(", datetime('now'), 'Datos del pedido modificados por el vendedor.')").toString());
        return 0;
    }

    public void marcarComoSyncCancelacion(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update cancelaciones set sync = 1 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public void marcarComoSyncCliente(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update clientes set sync = 1 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public void marcarComoSyncDevolucion(int i)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update devoluciones set sync = 1 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public void marcarComoSyncPedido(int i)
    {
        Log.i("Sync", "Marcando pedido como syncr");
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("update Pedidos set sync = 1, status = 2 where _id = ?;");
        sqlitestatement.bindLong(1, i);
        sqlitestatement.executeInsert();
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        onCreate(sqlitedatabase);
    }

    public long pagosPendientesPorSync()
    {
        return getWritableDatabase().compileStatement("select count(*) from cancelaciones where sync = 0;").simpleQueryForLong();
    }

    public int pedidoTrazaModificacion(String s)
    {
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement("insert into Pedidos_Modificaciones (modificacion) values(?); ");
        sqlitestatement.bindString(1, s);
        sqlitestatement.executeInsert();
        return 0;
    }

    public long pedidosPendientesPorSync()
    {
        return getWritableDatabase().compileStatement("select count(*) from pedidos where sync = 0;").simpleQueryForLong();
    }

    public boolean productoCargadoEnCarrito(int i)
    {
        boolean flag = true;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        if(sqlitedatabase.compileStatement(String.format("select count(*) from Carrito where id_producto = %d and proviene_de_promocion = 0", aobj)).simpleQueryForLong() <= 0L)
            flag = false;
        return flag;
    }

    public void registrarDevolucion(int i, int j, int k, String s, String s1)
    {
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        aobj[2] = Integer.valueOf(k);
        aobj[3] = s;
        String s2 = String.format("insert into Devoluciones(id_factura, id_producto, fecha, cantidad, observacion) values(%d, %d, datetime('now'), %d, '%s') ", aobj);
        MainActivity.mDbHelper.getWritableDatabase().execSQL(s2);
        Object aobj1[] = new Object[2];
        aobj1[0] = s1;
        aobj1[1] = Integer.valueOf(k);
        crearFacturaEvento(i, String.format("Registro de Devolucion. Articulo %s - Unidades %d", aobj1));
    }

    public static final String DATABASE_DIR = "DB_DIR";
    public static final String DATABASE_FILE_NAME = "sfdroid.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String QUERY_PRODUCTO_EN_CARRITO = "select count(*) from Carrito where id_producto = %d and proviene_de_promocion = 0";
    private static final String QUERY_ULTIMO_PEDIDO_GENERADO = "select count(*) from Pedidos";
    private static final String TABLA_ABONOS = "Cancelaciones";
    private static final String TABLA_CARRITO = "Carrito_Productos";
    private static final String TABLA_CARRITO_PRODUCTOS = "Carrito_Productos";
    private static final String TABLA_FACTURA_EVENTOS = "Facturas_Eventos";
    private static final String TABLA_PEDIDOS = "Pedidos";
    private static final String TABLA_PEDIDOS_DETALLE = "PedidosDetalle";
    private static final String TABLA_PEDIDOS_EVENTOS = "PedidosEventos";
    public static final String UPDATE_CANTIDAD_ITEM_CARRITO_POR_IDITEM = "update Carrito_Productos set cantidad = %d, precio = %f, id_autorizacion_precio = %d where _id = %d ";
    public static final String UPDATE_CANTIDAD_ITEM_CARRITO_POR_PRODUCTO = "update Carrito_Productos set cantidad = %d, precio = %f, id_autorizacion_precio = %d where id_producto = %d and proviene_de_promocion = 0";
    private final Context mContext;
}
