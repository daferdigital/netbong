// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

import com.netbong.fuerza.MainActivity;

public class CrsProductos extends SQLiteCursor
{
    static enum Colums {
    	stock,
    	_id,
    	id_profitplus,
    	nombre,
    	precio_base,
    	imagen,
    	iva,
    	activo,
    	marca,
    	imagen_marca,
    	precio1,
    	precio2,
    	precio3,
    	precio4,
    	precio5;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsProductos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsProductos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsProductos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsProductos crsproductos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsProductos getProductoPorId(int i)
    {
        String s = MainActivity.mainCtx.getString(0x7f05003c);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsProductos crsproductos = (CrsProductos)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crsproductos.moveToFirst();
        return crsproductos;
    }

    public static CrsProductos getProductos()
    {
        String s = MainActivity.mainCtx.getString(0x7f050038);
        CrsProductos crsproductos = (CrsProductos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), s, null, null);
        crsproductos.moveToFirst();
        return crsproductos;
    }

    public static CrsProductos getProductosPorLinea(String s)
    {
        String s1 = MainActivity.mainCtx.getString(0x7f050039).replace("?", (new StringBuilder("(")).append(s).append(")").toString());
        CrsProductos crsproductos = (CrsProductos)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), s1, null, null);
        crsproductos.moveToFirst();
        return crsproductos;
    }

    public static CrsProductos getProductosPorPatronCodigo(String s)
    {
        String s1 = MainActivity.mainCtx.getString(0x7f05003b);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = (new StringBuilder("%")).append(s).append("%").toString();
        CrsProductos crsproductos = (CrsProductos)sqlitedatabase.rawQueryWithFactory(factory, s1, as, null);
        crsproductos.moveToFirst();
        return crsproductos;
    }

    public static CrsProductos getProductosPorPatronNombre(String s)
    {
        String s1 = MainActivity.mainCtx.getString(0x7f05003a);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = (new StringBuilder("%")).append(s).append("%").toString();
        CrsProductos crsproductos = (CrsProductos)sqlitedatabase.rawQueryWithFactory(factory, s1, as, null);
        crsproductos.moveToFirst();
        return crsproductos;
    }

    public boolean getProductoActivo()
    {
        boolean flag = true;
        if(1 != getInt(getColumnIndexOrThrow(Colums.activo.toString())))
            flag = false;
        return flag;
    }

    public String getProductoCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.id_profitplus.toString()));
    }

    public int getProductoId()
    {
        return getInt(getColumnIndexOrThrow(Colums._id.toString()));
    }

    public String getProductoImagenPrincipal()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow(Colums.imagen.toString()))).toString();
    }

    public double getProductoIva()
    {
        return getDouble(getColumnIndexOrThrow(Colums.iva.toString()));
    }

    public String getProductoMarca()
    {
        return getString(getColumnIndexOrThrow(Colums.marca.toString()));
    }

    public String getProductoMarcaImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow(Colums.imagen_marca.toString()))).toString();
    }

    public String getProductoNombre()
    {
        return getString(getColumnIndexOrThrow(Colums.nombre.toString()));
    }

    public double getProductoPrecio1()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio1.toString()));
    }

    public double getProductoPrecio2()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio2.toString()));
    }

    public double getProductoPrecio3()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio3.toString()));
    }

    public double getProductoPrecio4()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio4.toString()));
    }

    public double getProductoPrecio5()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio5.toString()));
    }

    public double getProductoPrecioBase()
    {
        return getDouble(getColumnIndexOrThrow(Colums.precio_base.toString()));
    }

    public int getProductoStock()
    {
        return getInt(getColumnIndexOrThrow(Colums.stock.toString()));
    }
}
