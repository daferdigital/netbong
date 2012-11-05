// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.db.cursores.CursorClientesConPedidos;
import com.netbong.fuerza.db.cursores.CursorEventosPedido;
import com.netbong.fuerza.db.cursores.CursorPedidos;
import com.netbong.fuerza.db.cursores.CursorProductosEnPedido;
import com.netbong.fuerza.pedidos.PedidoResumen;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity

public class ConsultaPedidosGenerados extends Activity
{

    public ConsultaPedidosGenerados()
    {
    }

    private void cargarClientes()
    {
        CursorClientesConPedidos cursorclientesconpedidos = MainActivity.mDbHelper.getListadoClientesConPedidos();
        if(cursorclientesconpedidos.getCount() != 0)
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f060053);
            View view = crearViewClienteConPedido(cursorclientesconpedidos.getNombre(), cursorclientesconpedidos.getTotalGenerado(), cursorclientesconpedidos.getTotalAnulado(), cursorclientesconpedidos.getTotalSync(), cursorclientesconpedidos.getTotalFacturado());
            view.setId(cursorclientesconpedidos.getId());
            view.setOnClickListener(oclViewClientes);
            linearlayout.addView(view);
            while(cursorclientesconpedidos.moveToNext()) 
            {
                View view1 = crearViewClienteConPedido(cursorclientesconpedidos.getNombre(), cursorclientesconpedidos.getTotalGenerado(), cursorclientesconpedidos.getTotalAnulado(), cursorclientesconpedidos.getTotalSync(), cursorclientesconpedidos.getTotalFacturado());
                view1.setId(cursorclientesconpedidos.getId());
                view1.setOnClickListener(oclViewClientes);
                linearlayout.addView(view1);
            }
        }
    }

    private void cargarEventosEnPedido(int i, LinearLayout linearlayout)
    {
        CursorEventosPedido cursoreventospedido = MainActivity.mDbHelper.getListadoEventosEnPedido(i);
        if(cursoreventospedido.getCount() != 0)
        {
            linearlayout.removeAllViews();
            TextView textview = new TextView(this);
            textview.setText((new StringBuilder("[")).append(cursoreventospedido.getFecha()).append("] ").append(cursoreventospedido.getEvento()).toString());
            textview.setTextColor(getResources().getColor(0x7f040001));
            linearlayout.addView(textview);
            while(cursoreventospedido.moveToNext()) 
            {
                TextView textview1 = new TextView(this);
                textview1.setText((new StringBuilder("[")).append(cursoreventospedido.getFecha()).append("] ").append(cursoreventospedido.getEvento()).toString());
                textview1.setTextColor(getResources().getColor(0x7f040001));
                linearlayout.addView(textview1);
            }
        }
    }

    private void cargarPedidosEfectuadosPorCliente(int i, LinearLayout linearlayout)
    {
        CursorPedidos cursorpedidos = MainActivity.mDbHelper.getListadoPedidosDeCliente(i);
        if(cursorpedidos.getCount() != 0)
        {
            LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f030015, null);
            linearlayout.addView(linearlayout1);
            View view = crearViewPedidosEfectuadosPorCliente(cursorpedidos.getId(), cursorpedidos.getStatus(), cursorpedidos.getFecha(), cursorpedidos.getMontoTotal());
            view.setOnClickListener(oclViewPedidos);
            ((LinearLayout)linearlayout1.findViewById(0x7f060044)).addView(view);
            while(cursorpedidos.moveToNext()) 
            {
                if(cursorpedidos.getPosition() % 2 == 0)
                {
                    linearlayout1 = (LinearLayout)View.inflate(this, 0x7f030015, null);
                    linearlayout.addView(linearlayout1);
                }
                View view1 = crearViewPedidosEfectuadosPorCliente(cursorpedidos.getId(), cursorpedidos.getStatus(), cursorpedidos.getFecha(), cursorpedidos.getMontoTotal());
                view1.setOnClickListener(oclViewPedidos);
                ((LinearLayout)linearlayout1.findViewById(0x7f060044)).addView(view1);
            }
        }
    }

    private void cargarProductosEnPedido(int i, LinearLayout linearlayout)
    {
        CursorProductosEnPedido cursorproductosenpedido = MainActivity.mDbHelper.getListadoProductosEnPedido(i);
        if(cursorproductosenpedido.getCount() != 0)
        {
            linearlayout.removeAllViews();
            linearlayout.addView(crearViewProductosEnPedido(cursorproductosenpedido.getNombre(), cursorproductosenpedido.getPrecio(), cursorproductosenpedido.getCantidad(), cursorproductosenpedido.getSubTotal(), cursorproductosenpedido.getImagen()));
            while(cursorproductosenpedido.moveToNext()) 
                linearlayout.addView(crearViewProductosEnPedido(cursorproductosenpedido.getNombre(), cursorproductosenpedido.getPrecio(), cursorproductosenpedido.getCantidad(), cursorproductosenpedido.getSubTotal(), cursorproductosenpedido.getImagen()));
        }
    }

    private View crearViewClienteConPedido(String s, int i, int j, int k, int l)
    {
        View view = View.inflate(this, 0x7f03001a, null);
        ((TextView)view.findViewById(0x7f060051)).setText(s);
        TextView textview = (TextView)view.findViewById(0x7f060054);
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        aobj[2] = Integer.valueOf(k);
        aobj[3] = Integer.valueOf(l);
        textview.setText(String.format("Generados(%d), Anulados(%d), Sincronizados(%d), Facturados(%d)", aobj));
        return view;
    }

    private View crearViewPedidosEfectuadosPorCliente(int i, String s, String s1, double d)
    {
        View view = View.inflate(this, 0x7f030016, null);
        view.setId(i);
        view.setTag(Integer.valueOf(i));
        TextView textview = (TextView)view.findViewById(0x7f060049);
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = s;
        textview.setText(String.format("Pedido 0000%d [%s]", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f06004a);
        Object aobj1[] = new Object[1];
        aobj1[0] = s1;
        textview1.setText(String.format("Generado en: %s", aobj1));
        TextView textview2 = (TextView)view.findViewById(0x7f06004b);
        Object aobj2[] = new Object[1];
        aobj2[0] = Double.valueOf(d);
        textview2.setText(String.format("Por el monto de: Bs. %1$.2f", aobj2));
        return view;
    }

    private View crearViewProductosEnPedido(String s, double d, int i, double d1, String s1)
    {
        View view = View.inflate(this, 0x7f030017, null);
        ((ImageView)view.findViewById(0x7f06004c)).setImageDrawable(Drawable.createFromPath(s1));
        ((TextView)view.findViewById(0x7f06004d)).setText(s);
        TextView textview = (TextView)view.findViewById(0x7f06004e);
        Object aobj[] = new Object[1];
        aobj[0] = Double.valueOf(d);
        textview.setText(String.format("Precio Bs. %1$.2f", aobj));
        TextView textview1 = (TextView)view.findViewById(0x7f06004f);
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(i);
        textview1.setText(String.format("Cantidad %d", aobj1));
        TextView textview2 = (TextView)view.findViewById(0x7f06004b);
        Object aobj2[] = new Object[1];
        aobj2[0] = Double.valueOf(d1);
        textview2.setText(String.format("Sub Total Bs. %1$.2f", aobj2));
        return view;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.consulta_pedidos_generados_layout);
        oclViewClientes = new _cls1();
        oclViewPedidos = new _cls2();
        olclViewPedidos = new _cls3();
        cargarClientes();
    }

    private android.view.View.OnClickListener oclViewClientes;
    private android.view.View.OnClickListener oclViewPedidos;
    private android.view.View.OnLongClickListener olclViewPedidos;
    private View ultimoPedidoClicleado;
    private View ultimoResumenPedidoVisualizado;


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f060055);
            if(linearlayout.getVisibility() == 0)
                linearlayout.setVisibility(8);
            else
                linearlayout.setVisibility(0);
            if(view.getId() <= 62000)
            {
                cargarPedidosEfectuadosPorCliente(view.getId(), linearlayout);
                view.setId(62000 + view.getId());
            }
        }

        final ConsultaPedidosGenerados this$0;

        _cls1()
        {
        	super();
        	this$0 = ConsultaPedidosGenerados.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(ConsultaPedidosGenerados.this, PedidoResumen.class);
            intent.putExtra("PEDIDO", view.getId());
            startActivity(intent);
        }

        final ConsultaPedidosGenerados this$0;

        _cls2()
        {
        	super();
        	this$0 = ConsultaPedidosGenerados.this;
        }
    }


    private class _cls3
        implements android.view.View.OnLongClickListener
    {

        public boolean onLongClick(View view)
        {
            Intent intent = new Intent(MainActivity.mainCtx, ConsultarPedidosPopupDialogMenu.class);
            intent.putExtra("FACTURA", view.getId());
            startActivity(intent);
            return false;
        }

        final ConsultaPedidosGenerados this$0;

        _cls3()
        {
        	super();
        	this$0 = ConsultaPedidosGenerados.this;
        }
    }

}
