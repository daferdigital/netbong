// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorPromos;

public class PedidoAdaptarPromos extends BaseAdapter
{

    public PedidoAdaptarPromos(Context context1, CursorPromos cursorpromos)
    {
        context = context1;
        crPromos = cursorpromos;
    }

    public int getCount()
    {
        return crPromos.getCount();
    }

    public Object getItem(int i)
    {
        return null;
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
            view = View.inflate(context, 0x7f030062, null);
        crPromos.moveToPosition(i);
        view.setTag(Integer.valueOf(crPromos.getId()));
        ImageView imageview = (ImageView)view.findViewById(0x7f060118);
        imageview.setImageDrawable(Drawable.createFromPath(crPromos.getImagen()));
        imageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        ((TextView)view.findViewById(0x7f060119)).setText(crPromos.getDescripcion());
        return view;
    }

    private Context context;
    private CursorPromos crPromos;
}
