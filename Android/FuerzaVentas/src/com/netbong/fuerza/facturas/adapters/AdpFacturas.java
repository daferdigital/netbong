// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.facturas.db.cursors.CsrFacturasGeneradas;

public class AdpFacturas extends BaseAdapter
{

    public AdpFacturas(Context context1, CsrFacturasGeneradas csrfacturasgeneradas, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crFacturas = csrfacturasgeneradas;
        ocl = onclicklistener;
    }

    private void cargarInformacionView(View view, int i)
    {
        if(i >= crFacturas.getCount())
        {
            view.setVisibility(8);
        } else
        {
            if(view.getVisibility() == 8)
                view.setVisibility(0);
            crFacturas.moveToPosition(i);
            view.setOnClickListener(ocl);
            view.setTag(Integer.valueOf(crFacturas.getFacturaId()));
            ((TextView)view.findViewById(0x7f0600a9)).setText(crFacturas.getFacturaClienteCodigo());
            ((TextView)view.findViewById(0x7f0600aa)).setText(crFacturas.getFacturaCliente());
            ((TextView)view.findViewById(0x7f0600ac)).setText(crFacturas.getFactura());
            ((TextView)view.findViewById(0x7f0600ae)).setText(crFacturas.getFacturaEstatus());
            ((TextView)view.findViewById(0x7f0600ad)).setText(crFacturas.getFacturaFecha());
            ((TextView)view.findViewById(0x7f0600b0)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(crFacturas.getFacturaMonto())).toString());
        }
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
            LinearLayout linearlayout = new LinearLayout(context);
            linearlayout.addView(View.inflate(context, 0x7f030041, null), 0);
            linearlayout.addView(View.inflate(context, 0x7f030041, null), 1);
            linearlayout.addView(View.inflate(context, 0x7f030041, null), 2);
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
    private CsrFacturasGeneradas crFacturas;
    private android.view.View.OnClickListener ocl;
}
