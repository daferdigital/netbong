// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores.otros;

import android.database.Cursor;
import android.database.sqlite.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class DevolucionesImagenes extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new DevolucionesImagenes(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private DevolucionesImagenes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    DevolucionesImagenes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, DevolucionesImagenes devolucionesimagenes)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static DevolucionesImagenes getImagenes(int i)
    {
        DevolucionesImagenes devolucionesimagenes = (DevolucionesImagenes)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), (new StringBuilder("Select id_devolucion, img from Devoluciones_Imagenes where id_devolucion = ")).append(Integer.toString(i)).toString(), null, null);
        devolucionesimagenes.moveToFirst();
        return devolucionesimagenes;
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("id_devolucion"));
    }

    public String getImagen()
    {
        return getString(getColumnIndexOrThrow("img"));
    }

    public static final String COLUMNA_ID = "id_devolucion";
    public static final String COLUMNA_IMG = "img";
    private static final String QUERY = "Select id_devolucion, img from Devoluciones_Imagenes where id_devolucion = ";
}
