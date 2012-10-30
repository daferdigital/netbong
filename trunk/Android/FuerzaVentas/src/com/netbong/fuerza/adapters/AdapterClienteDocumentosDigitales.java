// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorClienteDocumentosDigitales;

public class AdapterClienteDocumentosDigitales extends BaseAdapter
{

    public AdapterClienteDocumentosDigitales(Context context1, CursorClienteDocumentosDigitales cursorclientedocumentosdigitales, android.view.View.OnClickListener onclicklistener)
    {
        context = context1;
        crDocsDigitales = cursorclientedocumentosdigitales;
        ocl = onclicklistener;
    }

    public int getCount()
    {
        return crDocsDigitales.getCount();
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
            view = View.inflate(context, 0x7f030023, null);
        LinearLayout linearlayout = (LinearLayout)view;
        crDocsDigitales.moveToPosition(i);
        ((TextView)linearlayout.findViewById(0x7f06006e)).setText(crDocsDigitales.getTag());
        ((ImageView)linearlayout.findViewById(0x7f06006d)).setImageDrawable(Drawable.createFromPath(crDocsDigitales.getURI()));
        View view1 = linearlayout.findViewById(0x7f06006f);
        view1.setOnClickListener(ocl);
        view1.setTag(crDocsDigitales.getURI());
        view1.setId(crDocsDigitales.getID());
        linearlayout.setId(crDocsDigitales.getID());
        return view;
    }

    public void reQueryCursor()
    {
        crDocsDigitales.requery();
    }

    private Context context;
    private CursorClienteDocumentosDigitales crDocsDigitales;
    private android.view.View.OnClickListener ocl;
}
