// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CrsPedidos extends SQLiteCursor
{
    static enum Colums {
    	cliente,
    	clie_nombre,
    	pedido,
    	fecha,
    	hora,
    	total_excento,
    	total_gravament,
    	total_iva_gravament,
    	total_general,
    	observacion,
    	observacion_pronto_pago,
    	clienteId;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsPedidos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsPedidos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsPedidos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsPedidos crspedidos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsPedidos getPedido(int i)
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f05001d);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsPedidos crspedidos = (CrsPedidos)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crspedidos.moveToFirst();
        return crspedidos;
    }

    public static CrsPedidos getPedidosPendientesPorSincronizar()
    {
        String s = MainActivity.mainCtx.getResources().getString(0x7f05001c);
        CrsPedidos crspedidos = (CrsPedidos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), s, null, null);
        crspedidos.moveToFirst();
        return crspedidos;
    }

    public String getClienteCodRef()
    {
        return getString(getColumnIndexOrThrow(Colums.cliente.toString()));
    }

    public int getClienteId()
    {
        return getInt(getColumnIndexOrThrow(Colums.clienteId.toString()));
    }

    public String getClienteNombre()
    {
        return getString(getColumnIndexOrThrow(Colums.clie_nombre.toString()));
    }

    public String getPedidoFecha()
    {
        return getString(getColumnIndexOrThrow(Colums.fecha.toString()));
    }

    public String getPedidoHora()
    {
        return getString(getColumnIndexOrThrow(Colums.hora.toString()));
    }

    public int getPedidoId()
    {
        return getInt(getColumnIndexOrThrow(Colums.pedido.toString()));
    }

    public String getPedidoObservacion()
    {
        return getString(getColumnIndexOrThrow(Colums.observacion.toString()));
    }

    public String getPedidoObservacionProntoPago()
    {
        return getString(getColumnIndexOrThrow(Colums.observacion_pronto_pago.toString()));
    }

    public double getPedidoTotalExento()
    {
        return getDouble(getColumnIndexOrThrow(Colums.total_excento.toString()));
    }

    public double getPedidoTotalGeneral()
    {
        return getDouble(getColumnIndexOrThrow(Colums.total_general.toString()));
    }

    public double getPedidoTotalGravament()
    {
        return getDouble(getColumnIndexOrThrow(Colums.total_gravament.toString()));
    }

    public double getPedidoTotalIvaGravament()
    {
        return getDouble(getColumnIndexOrThrow(Colums.total_iva_gravament.toString()));
    }
}
