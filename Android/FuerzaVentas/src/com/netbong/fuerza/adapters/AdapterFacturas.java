// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorFacturas;

public class AdapterFacturas extends BaseAdapter
{

    public AdapterFacturas(Context context1, CursorFacturas cursorfacturas, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crFacturas = cursorfacturas;
        ocl = onclicklistener;
    }

    public int getCount()
    {
        return crFacturas.getCount();
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
            view = View.inflate(context, 0x7f030026, null);
            view.setOnClickListener(ocl);
        }
        crFacturas.moveToPosition(i);
        LinearLayout linearlayout = (LinearLayout)view;
        linearlayout.setId(crFacturas.getId());
        linearlayout.setTag(crFacturas.getDocumento());
        TextView textview = (TextView)linearlayout.findViewById(0x7f060075);
        Object aobj[] = new Object[1];
        aobj[0] = crFacturas.getDocumento();
        textview.setText(String.format("Factura: %s", aobj));
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f060068);
        Object aobj1[] = new Object[1];
        aobj1[0] = crFacturas.getNombreCliente();
        textview1.setText(String.format("Cliente: %s", aobj1));
        ((TextView)linearlayout.findViewById(0x7f060074)).setText(crFacturas.getFecha());
        TextView textview2 = (TextView)linearlayout.findViewById(0x7f060076);
        Object aobj2[] = new Object[1];
        aobj2[0] = Double.valueOf(crFacturas.getMontoFacturado());
        textview2.setText(String.format("Total Facturado: %.2f", aobj2));
        return view;
    }

    private Context context;
    private CursorFacturas crFacturas;
    private android.view.View.OnClickListener ocl;
}
