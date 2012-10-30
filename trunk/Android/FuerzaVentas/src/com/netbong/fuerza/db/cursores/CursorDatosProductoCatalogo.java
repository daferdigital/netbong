// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Environment;

import com.netbong.fuerza.MainActivity;

public class CursorDatosProductoCatalogo extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorDatosProductoCatalogo(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorDatosProductoCatalogo(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorDatosProductoCatalogo(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorDatosProductoCatalogo cursordatosproductocatalogo)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorDatosProductoCatalogo getDatosProductoPorID(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        String s = String.format("SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, coalesce((select sum(cantidad) from Carrito where proviene_de_promocion = 0 and id_producto = p._id), 1) as cantidad, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.precio1, case when tc.id_tipo_precio > 1 then p.precio2 else 0 end as precio2, case when tc.id_tipo_precio > 2 then p.precio3 else 0 end as precio3, case when tc.id_tipo_precio > 3 then p.precio4 else 0 end as precio4, case when tc.id_tipo_precio > 4 then p.precio5 else 0 end as precio5, 0 as id_autorizacion_precio FROM Productos p, Marcas_Producto mp, clientes cli, tipos_clientes tc WHERE p._id = %d and p.id_marca_producto = mp._id and cli._id = %d and cli.id_tipo_cliente = tc._id ", aobj);
        CursorDatosProductoCatalogo cursordatosproductocatalogo = (CursorDatosProductoCatalogo)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursordatosproductocatalogo.moveToFirst();
        return cursordatosproductocatalogo;
    }

    public static CursorDatosProductoCatalogo getDatosProductoPorItemCarrito(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        String s = String.format("SELECT p.stock, p._id, p.id_profitplus, p.nombre, c.precio as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, c.cantidad, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.precio1, case when tc.id_tipo_precio > 1 then p.precio2 else 0 end as precio2, case when tc.id_tipo_precio > 2 then p.precio3 else 0 end as precio3, case when tc.id_tipo_precio > 3 then p.precio4 else 0 end as precio4, case when tc.id_tipo_precio > 4 then p.precio5 else 0 end as precio5, c.id_autorizacion_precio as id_autorizacion_precio FROM Productos p, Marcas_Producto mp, Carrito_Productos c, clientes cli, tipos_clientes tc WHERE c._id = %d and c.id_producto = p._id and p.id_marca_producto = mp._id and cli._id = %d and cli.id_tipo_cliente = tc._id ", aobj);
        CursorDatosProductoCatalogo cursordatosproductocatalogo = (CursorDatosProductoCatalogo)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursordatosproductocatalogo.moveToFirst();
        return cursordatosproductocatalogo;
    }

    public int getCantidad()
    {
        return getInt(getColumnIndexOrThrow("cantidad"));
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

    public int getIdAutorizacionPrecio()
    {
        return getInt(getColumnIndexOrThrow("id_autorizacion_precio"));
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

    public double getPrecio1()
    {
        return getDouble(getColumnIndexOrThrow("precio1"));
    }

    public double getPrecio2()
    {
        return getDouble(getColumnIndexOrThrow("precio2"));
    }

    public double getPrecio3()
    {
        return getDouble(getColumnIndexOrThrow("precio3"));
    }

    public double getPrecio4()
    {
        return getDouble(getColumnIndexOrThrow("precio4"));
    }

    public double getPrecio5()
    {
        return getDouble(getColumnIndexOrThrow("precio5"));
    }

    public String getPrecioComoString()
    {
        return (new StringBuilder("Bs. ")).append(MainActivity.formatVE(getDouble(getColumnIndexOrThrow("precio")))).toString();
    }

    public int getStock()
    {
        return getInt(getColumnIndexOrThrow("stock"));
    }

    public static final String COLUMNA_AUTORIZACION_PRECIO = "id_autorizacion_precio";
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
    public static final String COLUMNA_PRECIO1 = "precio1";
    public static final String COLUMNA_PRECIO2 = "precio2";
    public static final String COLUMNA_PRECIO3 = "precio3";
    public static final String COLUMNA_PRECIO4 = "precio4";
    public static final String COLUMNA_PRECIO5 = "precio5";
    public static final String COLUMNA_STOCK = "stock";
    public static final String COLUMNA_UNIDADES = "cantidad";
    private static final String QUERY_ID_PRODUCTO = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, p.precio1 as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, coalesce((select sum(cantidad) from Carrito where proviene_de_promocion = 0 and id_producto = p._id), 1) as cantidad, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.precio1, case when tc.id_tipo_precio > 1 then p.precio2 else 0 end as precio2, case when tc.id_tipo_precio > 2 then p.precio3 else 0 end as precio3, case when tc.id_tipo_precio > 3 then p.precio4 else 0 end as precio4, case when tc.id_tipo_precio > 4 then p.precio5 else 0 end as precio5, 0 as id_autorizacion_precio FROM Productos p, Marcas_Producto mp, clientes cli, tipos_clientes tc WHERE p._id = %d and p.id_marca_producto = mp._id and cli._id = %d and cli.id_tipo_cliente = tc._id ";
    private static final String QUERY_ITEM_CARRITO = "SELECT p.stock, p._id, p.id_profitplus, p.nombre, c.precio as precio, p.imagen, p.iva, mp.marca, mp.imagen as imagen_marca, c.cantidad, case when oferta_monto > 0 and julianday(oferta_monto_vigencia) - julianday('now') > 0 then oferta_monto else 0 end as tiene_oferta_monto, case when oferta_porcentaje > 0 and julianday(oferta_porcentaje_vigencia) - julianday('now') > 0 then oferta_porcentaje else 0 end as tiene_oferta_porcentaje, p.precio1, case when tc.id_tipo_precio > 1 then p.precio2 else 0 end as precio2, case when tc.id_tipo_precio > 2 then p.precio3 else 0 end as precio3, case when tc.id_tipo_precio > 3 then p.precio4 else 0 end as precio4, case when tc.id_tipo_precio > 4 then p.precio5 else 0 end as precio5, c.id_autorizacion_precio as id_autorizacion_precio FROM Productos p, Marcas_Producto mp, Carrito_Productos c, clientes cli, tipos_clientes tc WHERE c._id = %d and c.id_producto = p._id and p.id_marca_producto = mp._id and cli._id = %d and cli.id_tipo_cliente = tc._id ";
}
