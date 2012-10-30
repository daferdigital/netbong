// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.fuerza.db.cursores.CursorCarritoPedido;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity

public class PedidoAdaptarArticulosCarrito extends BaseAdapter
{

    public PedidoAdaptarArticulosCarrito(Context context1, CursorCarritoPedido cursorcarritopedido, android.view.View.OnClickListener onclicklistener, android.view.View.OnLongClickListener onlongclicklistener)
    {
        context = context1;
        crCarritoPedido = cursorcarritopedido;
        ocl = onclicklistener;
        olcl = onlongclicklistener;
    }

    public int getCount()
    {
        return crCarritoPedido.getCount();
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
            view = View.inflate(context, 0x7f03005a, null);
            view.setOnClickListener(ocl);
            view.setOnLongClickListener(olcl);
        }
        crCarritoPedido.moveToPosition(i);
        view.setTag(Integer.valueOf(i));
        ((ImageView)view.findViewById(0x7f0600f3)).setImageDrawable(Drawable.createFromPath(crCarritoPedido.getProductoImagen()));
        ((TextView)view.findViewById(0x7f0600f4)).setText(crCarritoPedido.getProductoCodigo());
        TextView textview = (TextView)view.findViewById(0x7f0600f5);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(crCarritoPedido.getCantidad());
        textview.setText(String.format("Cant./Unid. %d", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f0600f6);
        Object aobj1[] = new Object[1];
        aobj1[0] = MainActivity.formatVE(crCarritoPedido.getPrecio());
        textview1.setText(String.format("Bs. %s", aobj1));
        if(crCarritoPedido.getIva() == 0.0D)
        {
            ((TextView)view.findViewById(0x7f0600f7)).setText("(E)");
        } else
        {
            TextView textview2 = (TextView)view.findViewById(0x7f0600f7);
            Object aobj2[] = new Object[1];
            aobj2[0] = MainActivity.formatVE(crCarritoPedido.getIva());
            textview2.setText(String.format("IVA %s", aobj2));
        }
        return view;
    }

    private Context context;
    private CursorCarritoPedido crCarritoPedido;
    private android.view.View.OnClickListener ocl;
    private android.view.View.OnLongClickListener olcl;
}
