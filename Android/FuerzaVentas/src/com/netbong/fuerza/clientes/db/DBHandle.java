// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class DBHandle
{

    public DBHandle()
    {
    }

    public static double movimientosResumenTotal(String s)
    {
        String s1 = MainActivity.mainCtx.getResources().getString(0x7f050006);
        SQLiteStatement sqlitestatement = MainActivity.mDbHelper.getWritableDatabase().compileStatement(s1);
        sqlitestatement.bindString(1, s);
        return Double.parseDouble(sqlitestatement.simpleQueryForString());
    }
}
