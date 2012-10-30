// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.CursorProductosEnPedido;

public class AdapterArticulosPedido extends BaseAdapter
{

    public AdapterArticulosPedido(Context context1, CursorProductosEnPedido cursorproductosenpedido)
    {
        context = context1;
        crArticulosPedido = cursorproductosenpedido;
    }

    public int getCount()
    {
        return crArticulosPedido.getCount();
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
            view = View.inflate(context, 0x7f030020, null);
        crArticulosPedido.moveToPosition(i);
        view.setTag(Integer.valueOf(i));
        double d = crArticulosPedido.getCantidad() * crArticulosPedido.getPrecio();
        double d1 = (d * crArticulosPedido.getIva()) / 100D;
        ((ImageView)view.findViewById(0x7f060058)).setImageDrawable(Drawable.createFromPath(crArticulosPedido.getImagen()));
        ((TextView)view.findViewById(0x7f06005e)).setText(crArticulosPedido.getNombre());
        TextView textview = (TextView)view.findViewById(0x7f06005f);
        Object aobj[] = new Object[1];
        aobj[0] = Double.valueOf(crArticulosPedido.getPrecio());
        textview.setText(String.format("Bs.: %.2f", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f060061);
        Object aobj1[] = new Object[2];
        aobj1[0] = Double.valueOf(crArticulosPedido.getIva());
        aobj1[1] = MainActivity.formatVE(d1);
        textview1.setText(String.format("IVA(%.2f) Bs.: %s", aobj1));
        TextView textview2 = (TextView)view.findViewById(0x7f060060);
        Object aobj2[] = new Object[1];
        aobj2[0] = Integer.valueOf(crArticulosPedido.getCantidad());
        textview2.setText(String.format("Cant/ Unid %d", aobj2));
        TextView textview3 = (TextView)view.findViewById(0x7f060062);
        Object aobj3[] = new Object[1];
        aobj3[0] = MainActivity.formatVE(d + d1);
        textview3.setText(String.format("Sub Total Bs.: %s", aobj3));
        return view;
    }

    private Context context;
    private CursorProductosEnPedido crArticulosPedido;
}
