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

public class AdpBancos extends BaseAdapter
{

    public AdpBancos(Context context1, CsrBancos csrbancos, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crBancos = csrbancos;
        ocl = onclicklistener;
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
            view = View.inflate(context, 0x7f030073, null);
        crBancos.moveToPosition(i);
        TextView textview = (TextView)view.findViewById(0x7f060151);
        textview.setText(crBancos.getDescripcion());
        textview.setTag(Integer.valueOf(crBancos.getId()));
        textview.setOnClickListener(ocl);
        return view;
    }

    private Context context;
    private CsrBancos crBancos;
    private android.view.View.OnClickListener ocl;
}
