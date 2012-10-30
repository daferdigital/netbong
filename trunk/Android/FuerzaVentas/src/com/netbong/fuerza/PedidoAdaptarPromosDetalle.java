// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorPromosDetalle;

public class PedidoAdaptarPromosDetalle extends BaseAdapter
{

    public PedidoAdaptarPromosDetalle(Context context1, CursorPromosDetalle cursorpromosdetalle)
    {
        context = context1;
        crPromosDetalle = cursorpromosdetalle;
    }

    public int getCount()
    {
        return crPromosDetalle.getCount();
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
            view = View.inflate(context, 0x7f030064, null);
        crPromosDetalle.moveToPosition(i);
        ((ImageView)view.findViewById(0x7f06004c)).setImageDrawable(Drawable.createFromPath(crPromosDetalle.getProductoImagen()));
        ((TextView)view.findViewById(0x7f06004d)).setText(crPromosDetalle.getProductoNombre());
        TextView textview = (TextView)view.findViewById(0x7f06004e);
        Object aobj[] = new Object[1];
        aobj[0] = Double.valueOf(crPromosDetalle.getProductoPrecio());
        textview.setText(String.format("Bs. %.2f", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f06011b);
        Object aobj1[] = new Object[1];
        aobj1[0] = Double.valueOf(crPromosDetalle.getProductoPrecioIva());
        textview1.setText(String.format("IVA. %.2f", aobj1));
        return view;
    }

    private Context context;
    private CursorPromosDetalle crPromosDetalle;
}
