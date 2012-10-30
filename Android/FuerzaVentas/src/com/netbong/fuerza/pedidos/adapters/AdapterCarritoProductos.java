// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.pedidos.db.cursors.CrsCarritoItemsProductos;

public class AdapterCarritoProductos extends BaseAdapter
{

    public AdapterCarritoProductos(Context context1, CrsCarritoItemsProductos crscarritoitemsproductos, android.view.View.OnClickListener onclicklistener, android.view.View.OnLongClickListener onlongclicklistener)
    {
        context = context1;
        crCarritoPedido = crscarritoitemsproductos;
        ocl = onclicklistener;
        olcl = onlongclicklistener;
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
        {
            view = View.inflate(context, 0x7f03005a, null);
            view.setOnClickListener(ocl);
            view.setOnLongClickListener(olcl);
        }
        crCarritoPedido.moveToPosition(i);
        view.setTag(Integer.valueOf(i));
        ((ImageView)view.findViewById(0x7f0600f3)).setImageDrawable(Drawable.createFromPath(crCarritoPedido.getItemProductoImagen()));
        ((TextView)view.findViewById(0x7f0600f4)).setText(crCarritoPedido.getItemProductoCodigo());
        TextView textview = (TextView)view.findViewById(0x7f0600f5);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(crCarritoPedido.getItemCantidad());
        textview.setText(String.format("Cant./Unid. %d", aobj));
        ((TextView)view.findViewById(0x7f0600f6)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(crCarritoPedido.getItemPrecioAsignado())).toString());
        if(crCarritoPedido.getItemIva() == 0.0D)
        {
            ((TextView)view.findViewById(0x7f0600f7)).setText("(E)");
        } else
        {
            TextView textview1 = (TextView)view.findViewById(0x7f0600f7);
            Object aobj1[] = new Object[1];
            aobj1[0] = MainActivity.formatVE(crCarritoPedido.getItemIva());
            textview1.setText(String.format("IVA %s", aobj1));
        }
        return view;
    }

    private Context context;
    private CrsCarritoItemsProductos crCarritoPedido;
    private android.view.View.OnClickListener ocl;
    private android.view.View.OnLongClickListener olcl;
}
