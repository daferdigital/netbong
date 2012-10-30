// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.CursorClientes;

public class AdapterClientes extends BaseAdapter
{

    public AdapterClientes(Context context1, CursorClientes cursorclientes, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crClientes = cursorclientes;
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
            view.setTag(Integer.valueOf(i));
            crClientes.moveToPosition(i);
            ((TextView)view.findViewById(0x7f060063)).setText(crClientes.getCodigo());
            ((TextView)view.findViewById(0x7f060064)).setText(crClientes.getNombre());
            ((TextView)view.findViewById(0x7f060067)).setText(crClientes.getDireccion());
            ((TextView)view.findViewById(0x7f060066)).setText(crClientes.getContacto());
            ((TextView)view.findViewById(0x7f060065)).setText((new StringBuilder("Limite Credito Bs.: ")).append(MainActivity.formatVE(crClientes.getLimiteCredito())).toString());
        }
    }

    public int getCount()
    {
        return crClientes.getCount();
    }

    public Object getItem(int i)
    {
        crClientes.moveToPosition(i);
        return crClientes.getNombre();
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
            linearlayout.addView(View.inflate(context, 0x7f030021, null), 0);
            linearlayout.addView(View.inflate(context, 0x7f030021, null), 1);
            linearlayout.addView(View.inflate(context, 0x7f030021, null), 2);
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
    private CursorClientes crClientes;
    private android.view.View.OnClickListener ocl;
}
