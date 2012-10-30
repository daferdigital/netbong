// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.netbong.fuerza.MainActivity;

// Referenced classes of package com.ehp.droidsf.db:
//            DroidSFDatabase, CursorSyncDatosPedidoEventos

public class CursorSyncDatosPedido extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorSyncDatosPedido(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorSyncDatosPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorSyncDatosPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorSyncDatosPedido cursorsyncdatospedido)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    private String getCabecera()
    {
        Object aobj[] = new Object[9];
        aobj[0] = getLogin();
        aobj[1] = getDispositivo();
        aobj[2] = Integer.valueOf(getCodigoPedido());
        aobj[3] = getCodigoCliente();
        aobj[4] = getFecha();
        aobj[5] = getHora();
        aobj[6] = Double.valueOf(getTotalMontoPedido());
        aobj[7] = getObservacion();
        aobj[8] = Integer.valueOf(getClienteID());
        return String.format("%s;;%s;;%d;;%s;;%s;;%s;;%.4f;;%s;;%d", aobj);
    }

    private int getCantidad()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
    }

    private int getClienteID()
    {
        return getInt(getColumnIndexOrThrow("cliente_id"));
    }

    private String getCodigoCliente()
    {
        return getString(getColumnIndexOrThrow("cliente"));
    }

    private String getCodigoProducto()
    {
        return getString(getColumnIndexOrThrow("producto")).trim();
    }

    public static CursorSyncDatosPedido getDatosPedido(SQLiteDatabase sqlitedatabase, int i, String s, String s1)
    {
        Factory factory = new Factory(null);
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = s1;
        aobj[2] = Integer.valueOf(i);
        CursorSyncDatosPedido cursorsyncdatospedido = (CursorSyncDatosPedido)sqlitedatabase.rawQueryWithFactory(factory, String.format("select '%s' as login, '%s' as dispositivo, p._id as pedido, c.id_profitplus as cliente, date(p.fecha) as fecha, time(p.fecha) as hora, p.total_excento + p.total_gravament + p.total_iva_gravament as total, tt.id_profitplus as producto, pd.precio, pd.cantidad, pd.iva, pd.total_iva_gravament, p.observacion as observacion, c._id as cliente_id, c.nombre as clie_nombre from  Pedidos p, Pedidos_Detalle pd, Clientes c, Productos tt where p._id = %d and p._id = pd.id_pedido and p.id_cliente = c._id and pd.id_producto = tt._id ", aobj), null, null);
        cursorsyncdatospedido.moveToFirst();
        return cursorsyncdatospedido;
    }

    private String getDetalleItem()
    {
        Object aobj[] = new Object[5];
        aobj[0] = getCodigoProducto();
        aobj[1] = Double.valueOf(getPrecio());
        aobj[2] = Integer.valueOf(getCantidad());
        aobj[3] = Double.valueOf(getPorcentajeIva());
        aobj[4] = Double.valueOf(getMontoIva());
        return String.format("%s;;%.4f;;%d;;%.4f;;%.4f", aobj);
    }

    private String getDispositivo()
    {
        return getString(getColumnIndexOrThrow("dispositivo"));
    }

    private String getFecha()
    {
        String s = getString(getColumnIndexOrThrow("fecha")).substring(0, 10);
        Log.i("fecha registro pedido", s);
        String as[] = s.split("-");
        return (new StringBuilder(String.valueOf(as[2]))).append("-").append(as[1]).append("-").append(as[0]).toString();
    }

    private String getHora()
    {
        return getString(getColumnIndexOrThrow("hora"));
    }

    private String getLogin()
    {
        return getString(getColumnIndexOrThrow("login"));
    }

    private double getMontoIva()
    {
        return getDouble(getColumnIndexOrThrow("total_iva_gravament"));
    }

    private String getObservacion()
    {
        return getString(getColumnIndexOrThrow("observacion"));
    }

    public static CursorSyncDatosPedido getPedidosNoSynco()
    {
        CursorSyncDatosPedido cursorsyncdatospedido = (CursorSyncDatosPedido)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "select p._id as pedido from  Pedidos p where p.sync = 0", null, null);
        cursorsyncdatospedido.moveToFirst();
        return cursorsyncdatospedido;
    }

    private double getPorcentajeIva()
    {
        return getDouble(getColumnIndexOrThrow("iva"));
    }

    private double getPrecio()
    {
        return getDouble(getColumnIndexOrThrow("precio"));
    }

    private double getTotalMontoPedido()
    {
        return getDouble(getColumnIndexOrThrow("total"));
    }

    public StringBuilder construirTramaPedido()
    {
        StringBuilder stringbuilder;
        if(getCount() > 0)
        {
            stringbuilder = (new StringBuilder(getCabecera())).append("##").append(getDetalleItem());
            int i = getCodigoPedido();
            String s = CursorSyncDatosPedidoEventos.getDatosPedidoEvento(MainActivity.mDbHelper.getWritableDatabase(), i).construirTramaEvento();
            do
                stringbuilder.append("==").append(getDetalleItem());
            while(moveToNext());
            if(s != null)
                stringbuilder.append("##").append(s);
            Log.i("SYNC-TRAMA", stringbuilder.toString());
        } else
        {
            stringbuilder = null;
        }
        return stringbuilder;
    }

    public String getClienteNombre()
    {
        return getString(getColumnIndexOrThrow("clie_nombre"));
    }

    public int getCodigoPedido()
    {
        return getInt(getColumnIndexOrThrow("pedido"));
    }

    public String getFechaString()
    {
        return getString(getColumnIndexOrThrow("fecha"));
    }

    public static final String COLUMNA_CANTIDAD = "cantidad";
    public static final String COLUMNA_CLIENTE = "cliente";
    public static final String COLUMNA_CLIENTE_ID = "cliente_id";
    public static final String COLUMNA_CLIENTE_NOMBRE = "clie_nombre";
    public static final String COLUMNA_DISPOSITIVO = "dispositivo";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_HORA = "hora";
    public static final String COLUMNA_LOGIN = "login";
    public static final String COLUMNA_MONTO_IVA = "total_iva_gravament";
    public static final String COLUMNA_OBSERVACION = "observacion";
    public static final String COLUMNA_PEDIDO = "pedido";
    public static final String COLUMNA_PORCENTAJE_IVA = "iva";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_PRODUCTO = "producto";
    public static final String COLUMNA_TOTAL_PEDIDO = "total";
    private static final String QUERY = "select '%s' as login, '%s' as dispositivo, p._id as pedido, c.id_profitplus as cliente, date(p.fecha) as fecha, time(p.fecha) as hora, p.total_excento + p.total_gravament + p.total_iva_gravament as total, tt.id_profitplus as producto, pd.precio, pd.cantidad, pd.iva, pd.total_iva_gravament, p.observacion as observacion, c._id as cliente_id, c.nombre as clie_nombre from  Pedidos p, Pedidos_Detalle pd, Clientes c, Productos tt where p._id = %d and p._id = pd.id_pedido and p.id_cliente = c._id and pd.id_producto = tt._id ";
    private static final String QUERY_NO_SYNC = "select p._id as pedido from  Pedidos p where p.sync = 0";
}
