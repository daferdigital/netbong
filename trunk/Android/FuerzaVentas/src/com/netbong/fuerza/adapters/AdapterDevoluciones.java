// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorDevoluciones;

public class AdapterDevoluciones extends BaseAdapter
{

    public AdapterDevoluciones(Context context1, CursorDevoluciones cursordevoluciones)
    {
        context = context1;
        crDevoluciones = cursordevoluciones;
    }

    public int getCount()
    {
        return crDevoluciones.getCount();
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
            view = View.inflate(context, 0x7f030024, null);
        crDevoluciones.moveToPosition(i);
        LinearLayout linearlayout = (LinearLayout)view;
        TextView textview = (TextView)linearlayout.findViewById(0x7f060070);
        Object aobj[] = new Object[2];
        aobj[0] = crDevoluciones.getFecha();
        aobj[1] = crDevoluciones.getProducto();
        textview.setText(String.format("[%s] %s", aobj));
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f060071);
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(crDevoluciones.getUnidades());
        textview1.setText(String.format("%d unids.", aobj1));
        ((TextView)linearlayout.findViewById(0x7f060072)).setText(crDevoluciones.getObservacion());
        return view;
    }

    private Context context;
    private CursorDevoluciones crDevoluciones;
}
