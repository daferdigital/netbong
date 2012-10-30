// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterClientesConPedidos;
import com.netbong.fuerza.adapters.AdapterPedidos;
import com.netbong.fuerza.db.cursores.CursorClientesConPedidos;
import com.netbong.fuerza.db.cursores.CursorPedidos;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class PedidosEfectuados extends Activity
{

    public PedidosEfectuados()
    {
        textWatcher = new _cls1();
        oclEstablecerParametrosFiltro = new _cls2();
        oclNuevaConsulta = new _cls3();
        oclFiltrarPedidos = new _cls4();
        oclSeleccionCliente = new _cls5();
        oclSeleccionarPedido = new _cls6();
        oclCancelar = new _cls7();
    }

    private static final String formatoDia(int i)
    {
        String s;
        if(i < 10)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(i);
            s = String.format("0%d", aobj1);
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            s = String.format("%d", aobj);
        }
        return s;
    }

    private static final String formatoMes(int i)
    {
        String s;
        if(i < 10)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(i);
            s = String.format("0%d", aobj1);
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            s = String.format("%d", aobj);
        }
        return s;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1 && i == 1)
        {
            String s = intent.getStringExtra("CRITERIOS");
            String s1 = intent.getStringExtra("CLIENTE");
            String s2 = intent.getStringExtra("GENERADO");
            String s3 = intent.getStringExtra("ANULADO");
            String s4 = intent.getStringExtra("FACTURADO");
            String s5 = intent.getStringExtra("SINCRONIZADO");
            String s6 = intent.getStringExtra("FECHA_DESDE");
            String s7 = intent.getStringExtra("FECHA_HASTA");
            if(s != null && s.length() > 0)
            {
                ((TextView)findViewById(0x7f06001b)).setText(s);
                if(crp != null && !crp.isClosed())
                    crp.close();
                crp = MainActivity.mDbHelper.getListadoPedidosDeCliente(s1, s2, s3, s4, s5, s6, s7);
                lv_pedidos.setAdapter(new AdapterPedidos(this, crp, oclSeleccionarPedido));
                descripcionFiltroActual = s;
                if(crp.getCount() == 0)
                    MainActivity.crearMensajeToast(this, "No se encontraron coincidencias.", true);
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.pedidos_efectuados_activity_layout);
        campoFiltroCliente = (EditText)findViewById(0x7f060015);
        campoFiltroCliente.addTextChangedListener(textWatcher);
        lv_pedidos = (ListView)findViewById(0x7f060052);
        lv_pedidos.setDivider(null);
        lv_pedidos.setCacheColorHint(0);
        findViewById(0x7f060017).setOnClickListener(oclNuevaConsulta);
        findViewById(0x7f0600b1).setOnClickListener(oclEstablecerParametrosFiltro);
        findViewById(0x7f060018).setOnClickListener(oclCancelar);
        oclNuevaConsulta.onClick(null);
    }

    private static final int INTENT_REQUEST_CONFIGURAR_CRITERIO_FILTRO = 1;
    EditText campoFiltroCliente;
    CursorPedidos crp;
    String descripcionFiltroActual;
    Intent filtrarPedidos;
    ListView lv_clientes;
    ListView lv_pedidos;
    android.view.View.OnClickListener oclCancelar;
    android.view.View.OnClickListener oclEstablecerParametrosFiltro;
    android.view.View.OnClickListener oclFiltrarPedidos;
    android.view.View.OnClickListener oclNuevaConsulta;
    android.view.View.OnClickListener oclSeleccionCliente;
    android.view.View.OnClickListener oclSeleccionarPedido;
    private TextWatcher textWatcher;



    private class _cls1
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            Editable editable1 = campoFiltroCliente.getText();
            if(editable1 != null) {
            	String s = editable1.toString();
                if(s != null)
                {
                    String s1 = s.trim();
                    if(s1 != null)
                    {
                        if(crp != null && !crp.isClosed())
                            crp.close();
                        crp = MainActivity.mDbHelper.getListadoPedidosDeCliente(s1);
                        lv_pedidos.setAdapter(new AdapterPedidos(PedidosEfectuados.this, crp, oclSeleccionarPedido));
                        descripcionFiltroActual = (new StringBuilder("Patron de nombre: ")).append(s1).toString();
                        ((TextView)findViewById(0x7f06001b)).setText(descripcionFiltroActual);
                    }
                }
            }
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        final PedidosEfectuados this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidosEfectuados.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(filtrarPedidos == null)
                filtrarPedidos = new Intent(PedidosEfectuados.this, PedidosEfectuadosFiltro.class);
            startActivityForResult(filtrarPedidos, 1);
        }

        final PedidosEfectuados this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidosEfectuados.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            campoFiltroCliente.setText(null);
            if(crp != null && !crp.isClosed())
                crp.close();
            crp = MainActivity.mDbHelper.getListadoPedidosDeCliente();
            lv_pedidos.setAdapter(new AdapterPedidos(PedidosEfectuados.this, crp, oclSeleccionarPedido));
        }

        final PedidosEfectuados this$0;

        _cls3()
        {
        	super();
        	this$0 = PedidosEfectuados.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            PedidosEfectuados pedidosefectuados = PedidosEfectuados.this;
            String s = ((EditText)findViewById(0x7f060015)).getText().toString();
            DatePicker datepicker = (DatePicker)findViewById(0x7f06007d);
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(datepicker.getYear());
            aobj[1] = PedidosEfectuados.formatoMes(1 + datepicker.getMonth());
            aobj[2] = PedidosEfectuados.formatoDia(datepicker.getDayOfMonth());
            String s1 = String.format("%d-%s-%s", aobj);
            DatePicker datepicker1 = (DatePicker)findViewById(0x7f06007e);
            Object aobj1[] = new Object[3];
            aobj1[0] = Integer.valueOf(datepicker1.getYear());
            aobj1[1] = PedidosEfectuados.formatoMes(1 + datepicker1.getMonth());
            aobj1[2] = PedidosEfectuados.formatoDia(datepicker1.getDayOfMonth());
            CursorClientesConPedidos cursorclientesconpedidos = CursorClientesConPedidos.getClientes(s, s1, String.format("%d-%s-%s", aobj1));
            if(cursorclientesconpedidos.getCount() == 0)
                MainActivity.crearMensajeToast(pedidosefectuados, "No se encontraron coincidencias.", true);
            lv_clientes.setAdapter(new AdapterClientesConPedidos(pedidosefectuados, cursorclientesconpedidos, oclSeleccionCliente));
        }

        final PedidosEfectuados this$0;

        _cls4()
        {
        	super();
        	this$0 = PedidosEfectuados.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(crp != null && !crp.isClosed())
                crp.close();
            int i = Integer.parseInt(view.getTag().toString());
            crp = MainActivity.mDbHelper.getListadoPedidosDeCliente(i);
            lv_pedidos.setAdapter(new AdapterPedidos(PedidosEfectuados.this, crp, oclSeleccionarPedido));
            LinearLayout linearlayout = (LinearLayout)view;
            ((TextView)findViewById(0x7f060051)).setText(((TextView)linearlayout.findViewById(0x7f060068)).getText());
        }

        final PedidosEfectuados this$0;

        _cls5()
        {
        	super();
        	this$0 = PedidosEfectuados.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(PedidosEfectuados.this, PedidoResumen.class);
            intent.putExtra("PEDIDO", Integer.parseInt(view.getTag().toString()));
            startActivity(intent);
        }

        final PedidosEfectuados this$0;

        _cls6()
        {
        	super();
        	this$0 = PedidosEfectuados.this;
        }
    }


    private class _cls7
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final PedidosEfectuados this$0;

        _cls7()
        {
        	super();
        	this$0 = PedidosEfectuados.this;
        }
    }

}
