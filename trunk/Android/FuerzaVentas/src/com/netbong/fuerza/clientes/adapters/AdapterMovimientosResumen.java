// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.clientes.db.cursors.CrsMovimientosResumen;

public class AdapterMovimientosResumen extends BaseAdapter
{

    public AdapterMovimientosResumen(Context context1, CrsMovimientosResumen crsmovimientosresumen)
    {
        context = context1;
        crsMovimientosResumen = crsmovimientosresumen;
    }

    public int getCount()
    {
        return crsMovimientosResumen.getCount();
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
            view = View.inflate(context, 0x7f030052, null);
        crsMovimientosResumen.moveToPosition(i);
        view.setTag(Integer.valueOf(i));
        ((TextView)view.findViewById(0x7f0600de)).setText(crsMovimientosResumen.getMovimientoResumenConcepto());
        ((TextView)view.findViewById(0x7f0600df)).setText(crsMovimientosResumen.getMovimientoResumenComentario());
        ((TextView)view.findViewById(0x7f0600e0)).setText((new StringBuilder("Bs.: ")).append(MainActivity.formatVE(crsMovimientosResumen.getMovimientoResumenSaldo())).toString());
        return view;
    }

    private Context context;
    private CrsMovimientosResumen crsMovimientosResumen;
}
