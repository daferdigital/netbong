// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.catalogo.CatalogoProductos;
import com.netbong.fuerza.clientes.ConsultarClientes;
import com.netbong.fuerza.clientes.FichaCliente;
import com.netbong.fuerza.conf.Configuracion;
import com.netbong.fuerza.facturas.FacturasGeneradas;
import com.netbong.fuerza.facturas.PagosRegistrados;
import com.netbong.fuerza.facturas.db.DBHandle;
import com.netbong.fuerza.pedidos.PedidoCreacion;
import com.netbong.fuerza.pedidos.PedidosEfectuados;
import com.netbong.fuerza.sincronizar.SyncActivity;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity

public class OpcionesMenuPrincial extends Activity
    implements android.view.View.OnClickListener
{

    public OpcionesMenuPrincial()
    {
        oclConf = new _cls1();
        salir = new _cls2();
    }

    private View ViewSubOpcionsCliente()
    {
        if(subOpcionesCliente == null)
        {
            subOpcionesCliente = (LinearLayout)View.inflate(this, 0x7f030089, null);
            subOpcionesCliente.findViewById(0x7f060176).setOnClickListener(new _cls9());
            subOpcionesCliente.findViewById(0x7f060181).setOnClickListener(new _cls10());
        }
        return subOpcionesCliente;
    }

    private View ViewSubOpcionsFactura()
    {
        if(subOpcionesFactura == null)
        {
            subOpcionesFactura = (LinearLayout)View.inflate(this, 0x7f03008a, null);
            subOpcionesFactura.findViewById(0x7f060183).setOnClickListener(new _cls5());
            subOpcionesFactura.findViewById(0x7f060184).setOnClickListener(new _cls6());
            subOpcionesFactura.findViewById(0x7f060185).setOnClickListener(new _cls7());
        }
        return subOpcionesFactura;
    }

    private View ViewSubOpcionsPedido()
    {
        if(subOpcionesPedido == null)
        {
            subOpcionesPedido = (LinearLayout)View.inflate(this, 0x7f03008b, null);
            subOpcionesPedido.findViewById(0x7f060187).setOnClickListener(new _cls3());
            subOpcionesPedido.findViewById(0x7f060188).setOnClickListener(new _cls4());
        }
        return subOpcionesPedido;
    }

    private View ViewSubOpcionsProductos()
    {
        if(subOpcionesProductos == null)
        {
            subOpcionesProductos = (LinearLayout)View.inflate(this, 0x7f03008c, null);
            subOpcionesProductos.findViewById(0x7f060189).setOnClickListener(new _cls11());
        }
        return subOpcionesProductos;
    }

    private View ViewSubOpcionsSync()
    {
        if(subOpcionesSync == null)
        {
            subOpcionesSync = (LinearLayout)View.inflate(this, 0x7f03008d, null);
            subOpcionesSync.findViewById(0x7f06018b).setOnClickListener(new _cls8());
        }
        return subOpcionesSync;
    }

    private void mostrarOpcionComoNoSeleccionado(View view)
    {
        if(view != null)
        {
            view.setBackgroundResource(0x7f0200b8);
            ((TextView)view.findViewById(0x7f0600e5)).setTypeface(null, 0);
        }
    }

    private void mostrarOpcionComoSeleccionado(View view)
    {
        view.setBackgroundResource(0x7f0200b9);
        ((TextView)view.findViewById(0x7f0600e5)).setTypeface(null, 1);
    }

    public void onClick(View view) {
    	mostrarOpcionComoNoSeleccionado(this.ultimaOpcionSeleccionado);
        this.ultimaOpcionSeleccionado = view;
        mostrarOpcionComoSeleccionado(view);
        this.subOpciones.removeAllViews();
        if (view == findViewById(2131099876))
          this.subOpciones.addView(ViewSubOpcionsPedido());
        else {
          if (view == findViewById(2131099878))
            this.subOpciones.addView(ViewSubOpcionsFactura());
          else if (view == findViewById(2131099879))
            this.subOpciones.addView(ViewSubOpcionsSync());
          else if (view == findViewById(2131099880))
            this.subOpciones.addView(ViewSubOpcionsCliente());
          else if (view == findViewById(2131099881))
            startActivity(new Intent(MainActivity.mainCtx, CatalogoProductos.class));
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.opciones_menu_principal);
        findViewById(0x7f0600e4).setOnClickListener(this);
        findViewById(0x7f0600e6).setOnClickListener(this);
        findViewById(0x7f0600e7).setOnClickListener(this);
        findViewById(0x7f0600e8).setOnClickListener(this);
        findViewById(0x7f0600e9).setOnClickListener(this);
        findViewById(0x7f0600e1).setOnClickListener(oclConf);
        findViewById(0x7f0600e2).setOnClickListener(salir);
        subOpciones = (LinearLayout)findViewById(0x7f0600ea);
    }

    private android.view.View.OnClickListener oclConf;
    private android.view.View.OnClickListener salir;
    private LinearLayout subOpciones;
    private LinearLayout subOpcionesCliente;
    private LinearLayout subOpcionesFactura;
    private LinearLayout subOpcionesPedido;
    private LinearLayout subOpcionesProductos;
    private LinearLayout subOpcionesSync;
    private View ultimaOpcionSeleccionado;

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, Configuracion.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls1()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(-1);
            finish();
        }

        final OpcionesMenuPrincial this$0;

        _cls2()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls9
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, FichaCliente.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls9()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls10
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, ConsultarClientes.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls10()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, FacturasGeneradas.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls5()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, PagosRegistrados.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls6()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls7
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            DBHandle.ficticiasCrearDesdePedidosGenerados();
            MainActivity.crearMensajeToast(MainActivity.mainCtx, "Proceso ejecutado sin errores.", true);
        }

        final OpcionesMenuPrincial this$0;

        _cls7()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, PedidoCreacion.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls3()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, PedidosEfectuados.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls4()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls11
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, CatalogoProductos.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls11()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }


    private class _cls8
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.mainCtx, SyncActivity.class));
        }

        final OpcionesMenuPrincial this$0;

        _cls8()
        {
        	super();
        	this$0 = OpcionesMenuPrincial.this;
        }
    }

}
