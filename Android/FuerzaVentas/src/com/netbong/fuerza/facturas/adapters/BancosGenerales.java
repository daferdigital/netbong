// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.netbong.fuerza.facturas.db.cursors.CsrBancos;

public class BancosGenerales extends BaseAdapter
{

    public BancosGenerales(Context context1, CsrBancos csrbancos, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crBancos = csrbancos;
        ocl = onclicklistener;
    }

    private void cargarInformacionView(View view, int i)
    {
        if(i >= crBancos.getCount())
        {
            view.setVisibility(8);
        } else
        {
            if(view.getVisibility() == 8)
                view.setVisibility(0);
            crBancos.moveToPosition(i);
            TextView textview = (TextView)view;
            textview.setText(crBancos.getDescripcion());
            textview.setTag(Integer.valueOf(crBancos.getId()));
            textview.setOnClickListener(ocl);
        }
    }

    public int getCount()
    {
        return crBancos.getCount();
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
            view = View.inflate(context, 0x7f030072, null);
        int j = i * 3;
        cargarInformacionView(view.findViewById(0x7f06014e), j);
        cargarInformacionView(view.findViewById(0x7f06014f), j + 1);
        cargarInformacionView(view.findViewById(0x7f060150), j + 2);
        return view;
    }

    private Context context;
    private CsrBancos crBancos;
    private android.view.View.OnClickListener ocl;
}
