// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.facturas.db.cursors.CsrPagosRegistrados;

public class AdpPagos extends BaseAdapter
{

    public AdpPagos(Context context1, CsrPagosRegistrados csrpagosregistrados, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crPagos = csrpagosregistrados;
        ocl = onclicklistener;
    }

    private void cargarInformacionView(View view, int i)
    {
        if(i >= crPagos.getCount())
        {
            view.setVisibility(8);
        } else
        {
            if(view.getVisibility() == 8)
                view.setVisibility(0);
            crPagos.moveToPosition(i);
            view.setOnClickListener(ocl);
            view.setTag(Integer.valueOf(crPagos.getPagoId()));
            ((TextView)view.findViewById(0x7f0600eb)).setText(crPagos.getPagoCliente());
            ((TextView)view.findViewById(0x7f0600ec)).setText(crPagos.getPagoFecha());
            ((TextView)view.findViewById(0x7f0600ed)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(crPagos.getPagoMonto())).toString());
        }
    }

    public int getCount()
    {
        return crPagos.getCount();
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
            linearlayout.addView(View.inflate(context, 0x7f030056, null), 0);
            linearlayout.addView(View.inflate(context, 0x7f030056, null), 1);
            linearlayout.addView(View.inflate(context, 0x7f030056, null), 2);
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
    private CsrPagosRegistrados crPagos;
    private android.view.View.OnClickListener ocl;
}
