// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorLineasProducto;

public class AdapterLineasProductoConocerCatalogo extends BaseAdapter
{

    public AdapterLineasProductoConocerCatalogo(Context context1, CursorLineasProducto cursorlineasproducto, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crLineasProducto = cursorlineasproducto;
        ocl = onclicklistener;
    }

    public int getCount()
    {
        return crLineasProducto.getCount();
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
        {
            view = View.inflate(context, 0x7f030028, null);
            view.setOnClickListener(ocl);
        }
        LinearLayout linearlayout = (LinearLayout)view;
        crLineasProducto.moveToPosition(i);
        ((TextView)linearlayout.findViewById(0x7f060078)).setText(crLineasProducto.getCodigo());
        ((TextView)linearlayout.findViewById(0x7f06004d)).setText(crLineasProducto.getNombre());
        view.setId(i);
        view.setTag(crLineasProducto.getNombre());
        return view;
    }

    private Context context;
    private CursorLineasProducto crLineasProducto;
    private android.view.View.OnClickListener ocl;
}
