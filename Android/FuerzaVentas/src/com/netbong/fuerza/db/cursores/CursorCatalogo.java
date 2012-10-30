// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;
import android.util.Log;

import com.netbong.fuerza.MainActivity;

public class CursorCatalogo extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorCatalogo(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }

    public enum SortBy {
        nombre(0);

        private SortBy(int i){
            this.i = i;
        }
        
        final int i;
    }


    private CursorCatalogo(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorCatalogo(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorCatalogo cursorcatalogo)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorCatalogo getProductos(SQLiteDatabase sqlitedatabase, int i, String s, int j)
    {
        String s1;
        Object aobj[];
        String s2;
        CursorCatalogo cursorcatalogo;
        if(j == 0)
            s1 = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl, vendedor_x_linea vxl  WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea = vxl.id_linea and like('%s', p.nombre) ORDER BY ";
        else
            s1 = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl, vendedor_x_linea vxl  WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea = vxl.id_linea and like('%s', p.id_profitplus) ORDER BY ";
        aobj = new Object[2];
        aobj[0] = (new StringBuilder(String.valueOf(s))).append("%").toString();
        aobj[1] = Integer.valueOf(i);
        s2 = String.format(s1, aobj);
        cursorcatalogo = (CursorCatalogo)sqlitedatabase.rawQueryWithFactory(new Factory(null), (new StringBuilder(String.valueOf(s2))).append("nombre").toString(), null, null);
        cursorcatalogo.moveToFirst();
        return cursorcatalogo;
    }

    public static CursorCatalogo getProductos(SQLiteDatabase sqlitedatabase, SortBy sortby)
    {
        String s = (new StringBuilder("SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl, vendedor_x_linea vxl  WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea = vxl.id_linea ORDER BY ")).append(sortby.toString()).toString();
        CursorCatalogo cursorcatalogo = (CursorCatalogo)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorcatalogo.moveToFirst();
        return cursorcatalogo;
    }

    public static CursorCatalogo getProductos(SQLiteDatabase sqlitedatabase, SortBy sortby, String s)
    {
        String s1 = (new StringBuilder("SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea in (")).append(s).append(")").toString();
        Log.i("SQL", s1);
        Log.i("Linea", s);
        CursorCatalogo cursorcatalogo = (CursorCatalogo)sqlitedatabase.rawQueryWithFactory(new Factory(null), s1, null, null);
        cursorcatalogo.moveToFirst();
        return cursorcatalogo;
    }

    public String getCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public String getDescripcion()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public String getImagen()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen"))).toString();
    }

    public String getImagenMarca()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/").append("droidsf").append("/").append(getString(getColumnIndexOrThrow("imagen_marca"))).toString();
    }

    public String getMarca()
    {
        return getString(getColumnIndexOrThrow("marca"));
    }

    public String getNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public double getOfertaMonto()
    {
        return getDouble(getColumnIndexOrThrow("tiene_oferta_monto"));
    }

    public double getOfertaPorcentaje()
    {
        return getDouble(getColumnIndexOrThrow("tiene_oferta_porcentaje"));
    }

    public double getPorcentajeIva()
    {
        return getDouble(getColumnIndexOrThrow("iva"));
    }

    public double getPrecio()
    {
        return getDouble(getColumnIndexOrThrow("precio"));
    }

    public String getPrecioComoString()
    {
        return (new StringBuilder("Bs. ")).append(MainActivity.formatVE(getDouble(getColumnIndexOrThrow("precio")))).toString();
    }

    public double getPrecioIva()
    {
        return (getPrecio() * getPorcentajeIva()) / 100D;
    }

    public int getStock()
    {
        return getInt(getColumnIndexOrThrow("stock"));
    }

    public boolean productoActivo()
    {
        boolean flag = true;
        if(1 != getInt(getColumnIndexOrThrow("activo")))
            flag = false;
        return flag;
    }

    public static final String COLUMNA_ACTIVO = "activo";
    public static final String COLUMNA_CODIGO = "id_profitplus";
    public static final String COLUMNA_DESCRIPCION = "nombre";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_IMAGEN = "imagen";
    public static final String COLUMNA_IMAGEN_MARCA = "imagen_marca";
    public static final String COLUMNA_MARCA = "marca";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_OFERTA_MONTO = "tiene_oferta_monto";
    public static final String COLUMNA_OFERTA_PORCENTAJE = "tiene_oferta_porcentaje";
    public static final String COLUMNA_PORCENTAJE_IVA = "iva";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_STOCK = "stock";
    private static final String QUERY = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl, vendedor_x_linea vxl  WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea = vxl.id_linea ORDER BY ";
    private static final String QUERY_PATRON_CODIGO_PRODUCTO = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl, vendedor_x_linea vxl  WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea = vxl.id_linea and like('%s', p.id_profitplus) ORDER BY ";
    private static final String QUERY_PATRON_NOMBRE_PRODUCTO = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl, vendedor_x_linea vxl  WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea = vxl.id_linea and like('%s', p.nombre) ORDER BY ";
    private static final String QUERY_SIMPLE = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.activo FROM Productos p, Marcas_Producto mp, Productos_x_Lineas pxl WHERE p.id_marca_producto = mp._id and p._id = pxl.id_producto and pxl.id_linea in ";
}
