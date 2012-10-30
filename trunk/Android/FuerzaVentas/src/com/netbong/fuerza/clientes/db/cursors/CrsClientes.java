// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CrsClientes extends SQLiteCursor
{
    static enum Colums
    {
    	id("id", 0),
    	id_profitplus("id_profitplus", 1),
    	nombre("nombre", 2),
    	rif("rif", 3),
    	limite_credito("limite_credito", 4),
    	id_tipo_cliente("id_tipo_cliente", 5),
    	descripcion_tipo_cliente("descripcion_tipo_cliente", 6),
    	tipo_precio("tipo_precio", 7);
        
        private Colums(String s, int i)
        {
            this.i = i;
        }
        private int i;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsClientes(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsClientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsClientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsClientes crsclientes)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsClientes getCliente(int i)
    {
        String s = MainActivity.mainCtx.getString(0x7f050005);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = Integer.toString(i);
        CrsClientes crsclientes = (CrsClientes)sqlitedatabase.rawQueryWithFactory(factory, s, as, null);
        crsclientes.moveToFirst();
        return crsclientes;
    }

    public String getClienteCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.id_profitplus.toString()));
    }

    public int getClienteId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id.toString()));
    }

    public double getClienteLiminteCredito()
    {
        return getDouble(getColumnIndexOrThrow(Colums.limite_credito.toString()));
    }

    public String getClienteNombre()
    {
        return getString(getColumnIndexOrThrow(Colums.nombre.toString()));
    }

    public String getClienteRif()
    {
        return getString(getColumnIndexOrThrow(Colums.rif.toString()));
    }

    public String getClienteTipoDescripcion()
    {
        return getString(getColumnIndexOrThrow(Colums.descripcion_tipo_cliente.toString()));
    }

    public int getClienteTipoId()
    {
        return getInt(getColumnIndexOrThrow(Colums.id_tipo_cliente.toString()));
    }

    public int getClienteTipoPrecio()
    {
        return getInt(getColumnIndexOrThrow(Colums.tipo_precio.toString()));
    }
}
