// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.CursorCatalogo;

public class AdapterCatalogoProductos extends BaseAdapter
{

    public AdapterCatalogoProductos(Context context1, CursorCatalogo cursorcatalogo, android.view.View.OnClickListener onclicklistener)
    {
        verPrecio = true;
        context = context1;
        crCatalogo = cursorcatalogo;
        ocl = onclicklistener;
    }

    private void cargarInfoViewProducto(View view, int i)
    {
        if(i >= crCatalogo.getCount())
        {
            view.setVisibility(8);
        } else
        {
            if(view.getVisibility() == 8)
                view.setVisibility(0);
            crCatalogo.moveToPosition(i);
            ((TextView)view.findViewById(0x7f060084)).setText(crCatalogo.getNombre());
            TextView textview = (TextView)view.findViewById(0x7f060116);
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(crCatalogo.getStock());
            textview.setText(String.format("Stock: %d unds.", aobj));
            ((TextView)view.findViewById(0x7f0600db)).setText(crCatalogo.getCodigo());
            ((TextView)view.findViewById(0x7f06005a)).setText(crCatalogo.getPrecioComoString());
            TextView textview1 = (TextView)view.findViewById(0x7f060117);
            ImageView imageview;
            android.view.View.OnClickListener onclicklistener;
            if(crCatalogo.getPrecioIva() == 0.0D)
            {
                textview1.setText("(E)");
            } else
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = MainActivity.formatVE(crCatalogo.getPrecio() + crCatalogo.getPrecioIva());
                textview1.setText(String.format("Bs. %s (con IVA)", aobj1));
            }
            if(!verPrecio)
                textview1.setVisibility(8);
            imageview = (ImageView)view.findViewById(0x7f060115);
            imageview.setImageDrawable(Drawable.createFromPath(crCatalogo.getImagen()));
            imageview.setTag(Integer.valueOf(i));
            if(crCatalogo.getStock() == 0)
                onclicklistener = null;
            else
                onclicklistener = ocl;
            view.setOnClickListener(onclicklistener);
        }
    }

    public int getCount()
    {
        return crCatalogo.getCount();
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
            LinearLayout linearlayout = new LinearLayout(context);
            linearlayout.addView(View.inflate(context, 0x7f030061, null), 0);
            linearlayout.addView(View.inflate(context, 0x7f030061, null), 1);
            linearlayout.addView(View.inflate(context, 0x7f030061, null), 2);
            linearlayout.addView(View.inflate(context, 0x7f030061, null), 3);
            linearlayout.addView(View.inflate(context, 0x7f030061, null), 4);
            view = linearlayout;
        }
        int j = i * 5;
        LinearLayout linearlayout1 = (LinearLayout)view;
        cargarInfoViewProducto(linearlayout1.getChildAt(0), j);
        cargarInfoViewProducto(linearlayout1.getChildAt(1), j + 1);
        cargarInfoViewProducto(linearlayout1.getChildAt(2), j + 2);
        cargarInfoViewProducto(linearlayout1.getChildAt(3), j + 3);
        cargarInfoViewProducto(linearlayout1.getChildAt(4), j + 4);
        return view;
    }

    public void setMostrarPrecio(boolean flag)
    {
        verPrecio = flag;
    }

    private Context context;
    private CursorCatalogo crCatalogo;
    private android.view.View.OnClickListener ocl;
    private boolean verPrecio;
}
