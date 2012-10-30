// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorClientesConPedidos;

public class AdapterClientesConPedidos extends BaseAdapter
{

    public AdapterClientesConPedidos(Context context1, CursorClientesConPedidos cursorclientesconpedidos, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crClientes = cursorclientesconpedidos;
        ocl = onclicklistener;
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
            view = View.inflate(context, 0x7f030022, null);
            view.setOnClickListener(ocl);
        }
        crClientes.moveToPosition(i);
        LinearLayout linearlayout = (LinearLayout)view;
        linearlayout.setTag(Integer.valueOf(crClientes.getId()));
        ((TextView)linearlayout.findViewById(0x7f060068)).setText(crClientes.getNombre());
        TextView textview = (TextView)linearlayout.findViewById(0x7f060069);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(crClientes.getTotalGenerado());
        textview.setText(String.format("Generados(%d)", aobj));
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f06006a);
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(crClientes.getTotalAnulado());
        textview1.setText(String.format("Anulados(%d)", aobj1));
        TextView textview2 = (TextView)linearlayout.findViewById(0x7f06006b);
        Object aobj2[] = new Object[1];
        aobj2[0] = Integer.valueOf(crClientes.getTotalSync());
        textview2.setText(String.format("Sincronizados(%d)", aobj2));
        TextView textview3 = (TextView)linearlayout.findViewById(0x7f06006c);
        Object aobj3[] = new Object[1];
        aobj3[0] = Integer.valueOf(crClientes.getTotalFacturado());
        textview3.setText(String.format("Facturados(%d)", aobj3));
        return view;
    }

    private Context context;
    private CursorClientesConPedidos crClientes;
    private android.view.View.OnClickListener ocl;
}
