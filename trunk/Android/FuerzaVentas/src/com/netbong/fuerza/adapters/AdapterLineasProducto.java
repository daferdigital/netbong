// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorLineasProducto;

import java.util.ArrayList;

public class AdapterLineasProducto extends BaseAdapter
    implements android.view.View.OnClickListener
{

    public AdapterLineasProducto(Context context1, CursorLineasProducto cursorlineasproducto)
    {
        lineasSeleccionadas = new ArrayList();
        context = context1;
        crLineasProducto = cursorlineasproducto;
    }

    private void cargarInformacionView(View view, int i)
    {
        if(i >= crLineasProducto.getCount())
        {
            view.setVisibility(8);
        } else
        {
            if(view.getVisibility() == 8)
                view.setVisibility(0);
            crLineasProducto.moveToPosition(i);
            CheckBox checkbox = (CheckBox)view.findViewById(0x7f060077);
            checkbox.setOnClickListener(this);
            checkbox.setTag(Integer.valueOf(i));
            checkbox.setText(crLineasProducto.getNombre());
        }
    }

    public int getCount()
    {
        return crLineasProducto.getCount();
    }

    public Object getItem(int i)
    {
        crLineasProducto.moveToPosition(i);
        return crLineasProducto.getNombre();
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public String getSeleccion()
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder();
        Integer ainteger[] = (Integer[])lineasSeleccionadas.toArray(new Integer[0]);
        int j = ainteger.length;
        do
        {
            if(i >= j)
            {
                if(stringbuilder.length() > 0)
                    stringbuilder.delete(-1 + stringbuilder.length(), stringbuilder.length());
                return stringbuilder.toString();
            }
            stringbuilder.append(ainteger[i]);
            stringbuilder.append(',');
            i++;
        } while(true);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            LinearLayout linearlayout = new LinearLayout(context);
            linearlayout.addView(View.inflate(context, 0x7f030027, null), 0);
            linearlayout.addView(View.inflate(context, 0x7f030027, null), 1);
            linearlayout.addView(View.inflate(context, 0x7f030027, null), 2);
            view = linearlayout;
        }
        int j = i * 3;
        LinearLayout linearlayout1 = (LinearLayout)view;
        cargarInformacionView(linearlayout1.getChildAt(0), j);
        cargarInformacionView(linearlayout1.getChildAt(1), j + 1);
        cargarInformacionView(linearlayout1.getChildAt(2), j + 2);
        return view;
    }

    public void onClick(View view)
    {
        crLineasProducto.moveToPosition(Integer.parseInt(view.getTag().toString()));
        Integer integer = Integer.valueOf(crLineasProducto.getId());
        if(!lineasSeleccionadas.remove(integer))
            lineasSeleccionadas.add(Integer.valueOf(crLineasProducto.getId()));
    }

    private Context context;
    private CursorLineasProducto crLineasProducto;
    private ArrayList lineasSeleccionadas;
}
