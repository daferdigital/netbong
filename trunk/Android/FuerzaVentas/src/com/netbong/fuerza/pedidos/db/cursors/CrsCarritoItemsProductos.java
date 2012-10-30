// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

import com.netbong.fuerza.MainActivity;

public class CrsCarritoItemsProductos extends SQLiteCursor
{
    static enum Colums {
    	id_promocion,
    	id_producto,
    	tipo_precio_indicado,
    	precio,
    	cantidad,
    	iva,
    	precio_base,
    	id_autorizacion_precio,
    	_id,
    	art_cod,
    	art_nombre,
    	art_imagen,
    	precio_ant,
    	cantidad_ant,
    	iva_ant;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsCarritoItemsProductos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsCarritoItemsProductos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsCarritoItemsProductos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsCarritoItemsProductos crscarritoitemsproductos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsCarritoItemsProductos getItemPorProductoId(int i)
    {
        String s = MainActivity.mainCtx.getString(0x7f050021);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsCarritoItemsProductos crscarritoitemsproductos = (CrsCarritoItemsProductos)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crscarritoitemsproductos.moveToFirst();
        return crscarritoitemsproductos;
    }

    public static CrsCarritoItemsProductos getItemPorPromocionId(int i)
    {
        String s = MainActivity.mainCtx.getString(0x7f050022);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsCarritoItemsProductos crscarritoitemsproductos = (CrsCarritoItemsProductos)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crscarritoitemsproductos.moveToFirst();
        return crscarritoitemsproductos;
    }

    public static CrsCarritoItemsProductos getItemsProductos()
    {
        String s = MainActivity.mainCtx.getString(0x7f050023);
        CrsCarritoItemsProductos crscarritoitemsproductos = (CrsCarritoItemsProductos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), s, null, null);
        crscarritoitemsproductos.moveToFirst();
        return crscarritoitemsproductos;
    }

    public int getItemCantidad()
    {
        return getInt(getColumnIndexOrThrow(Colums.cantidad.toString()));
    }

    public int getItemCantidadAnt()
    {
        return getInt(getColumnIndexOrThrow(Colums.cantidad_ant.toString()));
    }

    public int getItemCodigoAutorizacionId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_autorizacion_precio.toString()));
    }

    public boolean getItemEsPromocion()
    {
        boolean flag;
        if(getItemPromocionId() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getItemId()
    {
        return getInt(getColumnIndexOrThrow(Colums._id.toString()));
    }

    public double getItemIva()
    {
        return getDouble(getColumnIndexOrThrow(Colums.iva.toString()));
    }

    public double getItemIvaAnt()
    {
        return getDouble(getColumnIndexOrThrow(Colums.iva_ant.toString()));
    }

    public double getItemPrecioAsignado()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio.toString()));
    }

    public double getItemPrecioAsignadoAnt()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio_ant.toString()));
    }

    public double getItemPrecioBase()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio_base.toString()));
    }

    public String getItemProductoCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.art_cod.toString()));
    }

    public int getItemProductoId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_producto.toString()));
    }

    public String getItemProductoImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow(Colums.art_imagen.toString()))).toString();
    }

    public String getItemProductoNombre()
    {
        return getString(getColumnIndexOrThrow(Colums.art_nombre.toString()));
    }

    public String getItemProductoTipoPrecioIndicado()
    {
        return getString(getColumnIndexOrThrow(Colums.tipo_precio_indicado.toString()));
    }

    public int getItemPromocionId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_promocion.toString()));
    }
}
