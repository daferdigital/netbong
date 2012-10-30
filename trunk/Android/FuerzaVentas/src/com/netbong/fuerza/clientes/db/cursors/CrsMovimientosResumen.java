// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes.db.cursors;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CrsMovimientosResumen extends SQLiteCursor
{
    private enum Colums {
        codref_cliente(0),
        concepto(1),
        saldo(2),
        comentario(3);

        private Colums(int nativeInt){
            this.nativeInt = nativeInt;
        }
        
        final int nativeInt;
    }

    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CrsMovimientosResumen(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CrsMovimientosResumen(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CrsMovimientosResumen(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CrsMovimientosResumen crsmovimientosresumen)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CrsMovimientosResumen getMovimientosResumen(String s)
    {
        String s1 = MainActivity.mainCtx.getString(0x7f050007);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = s;
        CrsMovimientosResumen crsmovimientosresumen = (CrsMovimientosResumen)sqlitedatabase.rawQueryWithFactory(factory, s1, as, null);
        crsmovimientosresumen.moveToFirst();
        return crsmovimientosresumen;
    }

    public static CrsMovimientosResumen getMovimientosResumenRestrictivos(String s)
    {
        String s1 = MainActivity.mainCtx.getString(0x7f050008);
        SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        Factory factory = new Factory(null);
        String as[] = new String[1];
        as[0] = s;
        CrsMovimientosResumen crsmovimientosresumen = (CrsMovimientosResumen)sqlitedatabase.rawQueryWithFactory(factory, s1, as, null);
        crsmovimientosresumen.moveToFirst();
        return crsmovimientosresumen;
    }

    public String getClienteCodigo()
    {
        return getString(getColumnIndexOrThrow(Colums.codref_cliente.toString()));
    }

    public String getMovimientoResumenComentario()
    {
        return getString(getColumnIndexOrThrow(Colums.comentario.toString()));
    }

    public String getMovimientoResumenConcepto()
    {
        return getString(getColumnIndexOrThrow(Colums.concepto.toString()));
    }

    public double getMovimientoResumenSaldo()
    {
        return Double.parseDouble(getString(getColumnIndexOrThrow(Colums.saldo.toString())));
    }
}
