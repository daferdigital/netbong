// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.netbong.fuerza.MainActivity;

// Referenced classes of package com.ehp.droidsf.db:
//            DroidSFDatabase

public class CursorClientesConPedidos extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorClientesConPedidos(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorClientesConPedidos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorClientesConPedidos(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorClientesConPedidos cursorclientesconpedidos)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorClientesConPedidos getClientes(SQLiteDatabase sqlitedatabase)
    {
        String s = MainActivity.mainCtx.getString(0x7f050047);
        CursorClientesConPedidos cursorclientesconpedidos = (CursorClientesConPedidos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorclientesconpedidos.moveToFirst();
        return cursorclientesconpedidos;
    }

    public static CursorClientesConPedidos getClientes(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Log.i("fehas desde:", s1);
        Log.i("fehas hasta:", s2);
        Object aobj[] = new Object[3];
        aobj[0] = (new StringBuilder("%")).append(s).append("%").toString();
        aobj[1] = s1;
        aobj[2] = s2;
        String s3 = String.format("SELECT c._id as id, c.nombre as nombre, sum(case when p.status = 0 then 1 else 0 end) as generado, sum(case when p.status = 1 then 1 else 0 end) as anulado,  sum(case when p.status = 2 then 1 else 0 end) as sincronizado, sum(case when p.status = 3 then 1 else 0 end) as facturado  FROM  Pedidos p, Clientes c WHERE like('%s', c.nombre) and p.fecha between '%s' and '%s 23:59:59' and p.id_cliente = c._id GROUP  BY c.nombre, c._id ", aobj);
        CursorClientesConPedidos cursorclientesconpedidos = (CursorClientesConPedidos)sqlitedatabase.rawQueryWithFactory(new Factory(null), s3, null, null);
        cursorclientesconpedidos.moveToFirst();
        return cursorclientesconpedidos;
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("id"));
    }

    public String getNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public int getTotalAnulado()
    {
        return getInt(getColumnIndexOrThrow("anulado"));
    }

    public int getTotalFacturado()
    {
        return getInt(getColumnIndexOrThrow("facturado"));
    }

    public int getTotalGenerado()
    {
        return getInt(getColumnIndexOrThrow("generado"));
    }

    public int getTotalSync()
    {
        return getInt(getColumnIndexOrThrow("sincronizado"));
    }

    public static final String COLUMNA_CLIENTE_ID = "id";
    public static final String COLUMNA_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMNA_PEDIDO_ANULADO = "anulado";
    public static final String COLUMNA_PEDIDO_FACT = "facturado";
    public static final String COLUMNA_PEDIDO_GENERADO = "generado";
    public static final String COLUMNA_PEDIDO_SYNC = "sincronizado";
    private static final String queryFiltro = "SELECT c._id as id, c.nombre as nombre, sum(case when p.status = 0 then 1 else 0 end) as generado, sum(case when p.status = 1 then 1 else 0 end) as anulado,  sum(case when p.status = 2 then 1 else 0 end) as sincronizado, sum(case when p.status = 3 then 1 else 0 end) as facturado  FROM  Pedidos p, Clientes c WHERE like('%s', c.nombre) and p.fecha between '%s' and '%s 23:59:59' and p.id_cliente = c._id GROUP  BY c.nombre, c._id ";
}
