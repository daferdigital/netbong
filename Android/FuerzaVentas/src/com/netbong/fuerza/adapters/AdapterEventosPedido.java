// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorEventosPedido;

public class AdapterEventosPedido extends BaseAdapter
{

    public AdapterEventosPedido(Context context1, CursorEventosPedido cursoreventospedido)
    {
        context = context1;
        crEventos = cursoreventospedido;
    }

    public int getCount()
    {
        return crEventos.getCount();
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
        if(view == null)
            view = View.inflate(context, 0x7f030025, null);
        crEventos.moveToPosition(i);
        LinearLayout linearlayout = (LinearLayout)view;
        ((TextView)linearlayout.findViewById(0x7f060073)).setText(crEventos.getEvento());
        ((TextView)linearlayout.findViewById(0x7f060074)).setText(crEventos.getFecha());
        return view;
    }

    private Context context;
    private CursorEventosPedido crEventos;
}
