// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.db.cursores.CursorClientesConFacturas;
import com.netbong.fuerza.db.cursores.CursorEventosFactura;
import com.netbong.fuerza.db.cursores.CursorFacturas;
import com.netbong.fuerza.db.cursores.CursorProductosEnFactura;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity

public class ConsultaFactsGeneradas extends Activity
{

    public ConsultaFactsGeneradas()
    {
    }

    private void cargarClientes()
    {
        CursorClientesConFacturas cursorclientesconfacturas = MainActivity.mDbHelper.getListadoClientesConFacturas();
        if(cursorclientesconfacturas.getCount() != 0)
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f060040);
            View view = crearViewClienteConFactura(cursorclientesconfacturas.getId(), cursorclientesconfacturas.getNombre(), cursorclientesconfacturas.getTotalFacturasPendientes(), cursorclientesconfacturas.getTotalFacturasAnuladas(), cursorclientesconfacturas.getTotalFacturasCanceladas());
            view.setOnClickListener(oclViewClientesFacturas);
            linearlayout.addView(view);
            while(cursorclientesconfacturas.moveToNext()) 
            {
                View view1 = crearViewClienteConFactura(cursorclientesconfacturas.getId(), cursorclientesconfacturas.getNombre(), cursorclientesconfacturas.getTotalFacturasPendientes(), cursorclientesconfacturas.getTotalFacturasAnuladas(), cursorclientesconfacturas.getTotalFacturasCanceladas());
                view1.setOnClickListener(oclViewClientesFacturas);
                linearlayout.addView(view1);
            }
        }
    }

    private void cargarEventosEnFactura(int i, LinearLayout linearlayout) {
    	CursorFacturas localCursorFacturas = MainActivity.mDbHelper.getListadoFacturasGeneradas();
    	
        if(localCursorFacturas.getCount() != 0) {
            linearlayout.removeAllViews();
            TextView textview = new TextView(this);
            textview.setText((new StringBuilder("[")).append(localCursorFacturas.getFecha()).append("] ").append("localCursorFacturas.getEvento()").toString());
            textview.setTextColor(getResources().getColor(0x7f040001));
            linearlayout.addView(textview);
            
            while(localCursorFacturas.moveToNext()) {
                TextView textview1 = new TextView(this);
                textview1.setText((new StringBuilder("[")).append(localCursorFacturas.getFecha()).append("] ").append("localCursorFacturas.getEvento()").toString());
                textview1.setTextColor(getResources().getColor(0x7f040001));
                linearlayout.addView(textview1);
            }
        }
    }

    private void cargarFacturasEfectuadosPorCliente(int i, LinearLayout linearlayout)
    {
        CursorFacturas cursorfacturas = MainActivity.mDbHelper.getListadoFacturasGeneradas();
        if(cursorfacturas.getCount() != 0)
        {
            LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f03000e, null);
            linearlayout.addView(linearlayout1);
            View view = crearViewFacturasEfectuadosPorCliente(cursorfacturas.getId(), cursorfacturas.getDocumento(), cursorfacturas.getStatus(), cursorfacturas.getFecha(), cursorfacturas.getMontoFacturado());
            view.setOnClickListener(oclViewFacturas);
            view.setOnLongClickListener(olclViewFacturas);
            ((LinearLayout)linearlayout1.findViewById(0x7f06002f)).addView(view);
            while(cursorfacturas.moveToNext()) 
            {
                if(cursorfacturas.getPosition() % 2 == 0)
                {
                    linearlayout1 = (LinearLayout)View.inflate(this, 0x7f03000e, null);
                    linearlayout.addView(linearlayout1);
                }
                View view1 = crearViewFacturasEfectuadosPorCliente(cursorfacturas.getId(), cursorfacturas.getDocumento(), cursorfacturas.getStatus(), cursorfacturas.getFecha(), cursorfacturas.getMontoFacturado());
                view1.setOnClickListener(oclViewFacturas);
                view1.setOnLongClickListener(olclViewFacturas);
                view1.setOnLongClickListener(olclViewFacturas);
                ((LinearLayout)linearlayout1.findViewById(0x7f06002f)).addView(view1);
            }
        }
    }

    private void cargarProductosEnFactura(int i, LinearLayout linearlayout)
    {
        CursorProductosEnFactura cursorproductosenfactura = MainActivity.mDbHelper.getListadoProductosEnFactura(i);
        if(cursorproductosenfactura.getCount() != 0)
        {
            linearlayout.removeAllViews();
            linearlayout.addView(crearViewProductosEnFactura(cursorproductosenfactura.getNombre(), cursorproductosenfactura.getPrecio(), cursorproductosenfactura.getCantidad(), cursorproductosenfactura.getSubTotal(), cursorproductosenfactura.getImagen()));
            while(cursorproductosenfactura.moveToNext()) 
                linearlayout.addView(crearViewProductosEnFactura(cursorproductosenfactura.getNombre(), cursorproductosenfactura.getPrecio(), cursorproductosenfactura.getCantidad(), cursorproductosenfactura.getSubTotal(), cursorproductosenfactura.getImagen()));
        }
    }

    private View crearViewClienteConFactura(int i, String s, int j, int k, int l)
    {
        View view = View.inflate(this, 0x7f030012, null);
        view.setId(i);
        view.setTag(Integer.valueOf(i));
        ((TextView)view.findViewById(0x7f060041)).setText(s);
        TextView textview = (TextView)view.findViewById(0x7f060042);
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(j);
        aobj[1] = Integer.valueOf(k);
        aobj[2] = Integer.valueOf(l);
        textview.setText(String.format("Pendientes(%d), Anulados(%d), Cancelado(%d)", aobj));
        return view;
    }

    private View crearViewFacturasEfectuadosPorCliente(int i, String s, String s1, String s2, double d)
    {
        View view = View.inflate(this, 0x7f03000d, null);
        view.setId(i);
        view.setTag(Integer.valueOf(i));
        TextView textview = (TextView)view.findViewById(0x7f06002c);
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = s1;
        textview.setText(String.format("Factura %s [%s]", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f06002d);
        Object aobj1[] = new Object[1];
        aobj1[0] = s2;
        textview1.setText(String.format("Emitida en: %s", aobj1));
        TextView textview2 = (TextView)view.findViewById(0x7f06002e);
        Object aobj2[] = new Object[1];
        aobj2[0] = Double.valueOf(d);
        textview2.setText(String.format("Por el monto de: Bs. %1$.2f", aobj2));
        return view;
    }

    private View crearViewProductosEnFactura(String s, double d, int i, double d1, String s1)
    {
        View view = View.inflate(this, 0x7f03000f, null);
        ((ImageView)view.findViewById(0x7f060033)).setImageDrawable(Drawable.createFromPath(s1));
        ((TextView)view.findViewById(0x7f060034)).setText(s);
        TextView textview = (TextView)view.findViewById(0x7f060035);
        Object aobj[] = new Object[1];
        aobj[0] = Double.valueOf(d);
        textview.setText(String.format("Precio Bs. %1$.2f", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f060036);
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(i);
        textview1.setText(String.format("Cantidad %d", aobj1));
        TextView textview2 = (TextView)view.findViewById(0x7f060037);
        Object aobj2[] = new Object[1];
        aobj2[0] = Double.valueOf(d1);
        textview2.setText(String.format("Sub Total Bs. %1$.2f", aobj2));
        return view;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.consulta_facturas_generados_layout);
        oclViewClientesFacturas = new _cls1();
        oclViewFacturas = new _cls2();
        olclViewFacturas = new _cls3();
        cargarClientes();
    }

    private android.view.View.OnClickListener oclViewClientesFacturas;
    private android.view.View.OnClickListener oclViewFacturas;
    private android.view.View.OnLongClickListener olclViewFacturas;
    private View ultimaFacturaClicleado;
    private View ultimoResumenFacturaVisualizado;








    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f060043);
            if(linearlayout.getVisibility() == 0)
                linearlayout.setVisibility(8);
            else
                linearlayout.setVisibility(0);
            if(view.getId() <= 62000)
            {
                cargarFacturasEfectuadosPorCliente(view.getId(), linearlayout);
                view.setId(62000 + view.getId());
            }
        }

        final ConsultaFactsGeneradas this$0;

        _cls1()
        {
            super();
            this$0 = ConsultaFactsGeneradas.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            LinearLayout linearlayout = (LinearLayout)((LinearLayout)view.getParent().getParent()).findViewById(0x7f060030);
            if(ultimaFacturaClicleado != null)
            {
                LinearLayout linearlayout2 = (LinearLayout)ultimaFacturaClicleado.findViewById(0x7f06002b);
                linearlayout2.setBackgroundResource(0x7f0200b4);
                ((TextView)linearlayout2.findViewById(0x7f06002c)).setTextColor(getResources().getColor(0x7f040000));
                ((TextView)linearlayout2.findViewById(0x7f06002d)).setTextColor(getResources().getColor(0x7f040001));
                ((TextView)linearlayout2.findViewById(0x7f06002e)).setTextColor(getResources().getColor(0x7f040001));
            }
            if(ultimoResumenFacturaVisualizado != null)
                ultimoResumenFacturaVisualizado.setVisibility(8);
            LinearLayout linearlayout1 = (LinearLayout)((LinearLayout)view).findViewById(0x7f06002b);
            linearlayout1.setBackgroundResource(0x7f0200b2);
            ((TextView)linearlayout1.findViewById(0x7f06002c)).setTextColor(getResources().getColor(0x7f040002));
            ((TextView)linearlayout1.findViewById(0x7f06002d)).setTextColor(getResources().getColor(0x7f040002));
            ((TextView)linearlayout1.findViewById(0x7f06002e)).setTextColor(getResources().getColor(0x7f040002));
            linearlayout.setVisibility(0);
            cargarProductosEnFactura(view.getId(), (LinearLayout)linearlayout.findViewById(0x7f060031));
            cargarEventosEnFactura(view.getId(), (LinearLayout)linearlayout.findViewById(0x7f060032));
            ultimaFacturaClicleado = view;
            ultimoResumenFacturaVisualizado = linearlayout;
        }

        final ConsultaFactsGeneradas this$0;

        _cls2()
        {
        	super();
        	this$0 = ConsultaFactsGeneradas.this;
        }
    }


    private class _cls3
        implements android.view.View.OnLongClickListener
    {

        public boolean onLongClick(View view)
        {
            Intent intent = new Intent(MainActivity.mainCtx, ConsultarFacturasPopupDialogMenu.class);
            intent.putExtra("FACTURA", view.getId());
            startActivity(intent);
            return false;
        }

        final ConsultaFactsGeneradas this$0;

        _cls3() {
            super();
            this$0 = ConsultaFactsGeneradas.this;
        }
    }

}
