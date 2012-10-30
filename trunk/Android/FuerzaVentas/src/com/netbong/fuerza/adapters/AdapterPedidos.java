// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorPedidos;

public class AdapterPedidos extends BaseAdapter
{

    public AdapterPedidos(Context context1, CursorPedidos cursorpedidos, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crClientes = cursorpedidos;
        ocl = onclicklistener;
    }

    private void cargarInformacionView(View view, int i)
    {
        if(i >= crClientes.getCount())
        {
            view.setVisibility(8);
        } else
        {
            if(view.getVisibility() == 8)
                view.setVisibility(0);
            view.setOnClickListener(ocl);
            crClientes.moveToPosition(i);
            view.setTag(Integer.valueOf(crClientes.getId()));
            ((TextView)view.findViewById(0x7f060051)).setText(crClientes.getCliente());
            TextView textview = (TextView)view.findViewById(0x7f060049);
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(crClientes.getId());
            aobj[1] = crClientes.getStatus();
            textview.setText(String.format("Pedido 00%d [%s]", aobj));
            TextView textview1 = (TextView)view.findViewById(0x7f06004a);
            Object aobj1[] = new Object[1];
            aobj1[0] = crClientes.getFecha();
            textview1.setText(String.format("Generado en %s", aobj1));
            TextView textview2 = (TextView)view.findViewById(0x7f06004b);
            Object aobj2[] = new Object[1];
            aobj2[0] = Double.valueOf(crClientes.getMontoTotal());
            textview2.setText(String.format("Por el monto de Bs. %.2f", aobj2));
        }
    }

    public int getCount()
    {
        return crClientes.getCount();
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
            LinearLayout linearlayout = new LinearLayout(context);
            linearlayout.addView(View.inflate(context, 0x7f030029, null), 0);
            linearlayout.addView(View.inflate(context, 0x7f030029, null), 1);
            linearlayout.addView(View.inflate(context, 0x7f030029, null), 2);
            view = linearlayout;
        }
        int j = i * 3;
        LinearLayout linearlayout1 = (LinearLayout)view;
        cargarInformacionView(linearlayout1.getChildAt(0), j);
        cargarInformacionView(linearlayout1.getChildAt(1), j + 1);
        cargarInformacionView(linearlayout1.getChildAt(2), j + 2);
        return view;
    }

    private Context context;
    private CursorPedidos crClientes;
    private android.view.View.OnClickListener ocl;
}
