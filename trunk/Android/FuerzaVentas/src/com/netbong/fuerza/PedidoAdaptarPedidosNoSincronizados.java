// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.netbong.fuerza.db.cursores.CursorPedidosNoSincronizado;

public class PedidoAdaptarPedidosNoSincronizados extends BaseAdapter
{

    public PedidoAdaptarPedidosNoSincronizados(Context context1, CursorPedidosNoSincronizado cursorpedidosnosincronizado)
    {
        context = context1;
        cr = cursorpedidosnosincronizado;
    }

    public int getCount()
    {
        return cr.getCount();
    }

    public Object getItem(int i)
    {
        return Integer.valueOf(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return view;
    }

    private Context context;
    private CursorPedidosNoSincronizado cr;
}
