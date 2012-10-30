// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.netbong.fuerza.db.cursores.CursorCatalogoImagenes;

public class PedidoAdaptarImagenesProducto extends BaseAdapter
{

    public PedidoAdaptarImagenesProducto(Context context1, CursorCatalogoImagenes cursorcatalogoimagenes)
    {
        lp = new android.widget.Gallery.LayoutParams(90, 70);
        context = context1;
        crImagenesProducto = cursorcatalogoimagenes;
    }

    public int getCount()
    {
        return crImagenesProducto.getCount();
    }

    public Object getItem(int i)
    {
        crImagenesProducto.moveToPosition(i);
        return crImagenesProducto.getImagen();
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
            view = View.inflate(context, 0x7f030030, null);
        crImagenesProducto.moveToPosition(i);
        ImageView imageview = (ImageView)view.findViewById(0x7f060081);
        imageview.setImageDrawable(Drawable.createFromPath(crImagenesProducto.getImagen()));
        view.setTag(crImagenesProducto.getImagen());
        imageview.setTag(crImagenesProducto.getImagen());
        return view;
    }

    private Context context;
    private CursorCatalogoImagenes crImagenesProducto;
    private android.view.ViewGroup.LayoutParams lp;
}
