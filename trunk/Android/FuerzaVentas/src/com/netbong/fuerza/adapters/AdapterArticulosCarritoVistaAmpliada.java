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
import com.netbong.fuerza.db.cursores.CursorCarritoPedido;

public class AdapterArticulosCarritoVistaAmpliada extends BaseAdapter
{

    public AdapterArticulosCarritoVistaAmpliada(Context context1, CursorCarritoPedido cursorcarritopedido)
    {
        context = context1;
        crCarritoPedido = cursorcarritopedido;
    }

    public int getCount()
    {
        return crCarritoPedido.getCount();
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
            view = View.inflate(context, 0x7f03001f, null);
        crCarritoPedido.moveToPosition(i);
        view.setTag(Integer.valueOf(i));
        ((ImageView)view.findViewById(0x7f060058)).setImageDrawable(Drawable.createFromPath(crCarritoPedido.getProductoImagen()));
        ((TextView)view.findViewById(0x7f060059)).setText(crCarritoPedido.getProductoCodigo());
        TextView textview = (TextView)view.findViewById(0x7f06004f);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(crCarritoPedido.getCantidad());
        textview.setText(String.format("Cant/ Unid %d", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f06005b);
        String s;
        TextView textview2;
        Object aobj2[];
        TextView textview3;
        Object aobj3[];
        TextView textview4;
        Object aobj4[];
        if(crCarritoPedido.getIva() == 0.0D)
        {
            s = "Exento";
        } else
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = Double.valueOf(crCarritoPedido.getIva());
            s = String.format("IVA (%.2f)", aobj1);
        }
        textview1.setText(s);
        textview2 = (TextView)view.findViewById(0x7f06005a);
        aobj2 = new Object[1];
        aobj2[0] = Double.valueOf(crCarritoPedido.getPrecio());
        textview2.setText(String.format("Bs. %.2f", aobj2));
        textview3 = (TextView)view.findViewById(0x7f06005c);
        aobj3 = new Object[1];
        aobj3[0] = MainActivity.formatVE((double)crCarritoPedido.getCantidad() * crCarritoPedido.getPrecio());
        textview3.setText(String.format("Sub Total Bs.: %s", aobj3));
        textview4 = (TextView)view.findViewById(0x7f06005d);
        aobj4 = new Object[1];
        aobj4[0] = MainActivity.formatVE(((double)crCarritoPedido.getCantidad() * crCarritoPedido.getPrecio() * crCarritoPedido.getIva()) / 100D);
        textview4.setText(String.format("IVA Bs.: %s", aobj4));
        ((TextView)view.findViewById(0x7f06005e)).setText(crCarritoPedido.getProductoNombre());
        return view;
    }

    private Context context;
    private CursorCarritoPedido crCarritoPedido;
}
