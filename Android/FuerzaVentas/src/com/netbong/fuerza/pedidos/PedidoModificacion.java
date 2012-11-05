// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.*;
import com.netbong.fuerza.adapters.AdapterCatalogoProductos;
import com.netbong.fuerza.clientes.SeleccionarCliente;
import com.netbong.fuerza.db.cursores.*;
import com.netbong.fuerza.dialogos.DialogoSiNo;
import com.netbong.fuerza.pedidos.db.DBHandle;

// Referenced classes of package com.ehp.droidsf.pedidos:
//            PedidoSeleccionProducto

public class PedidoModificacion extends Activity
{

    public PedidoModificacion()
    {
        accionOkObservacion = new _cls1();
        accionCancelObservacion = new _cls2();
        accionBarraPromocionesVer = new _cls3();
        accionToolbariIemCrearObservacion = new _cls4();
        accionToolbariIemSeleccionarLineaProducto = new _cls5();
        accionToolbariIemVerCarrito = new _cls6();
        accionToolbariIemSeleccionarCliente = new _cls7();
        accionToolbariIemCerrarPedido = new _cls8();
        oclDetalleProducto = new _cls9();
        oclModificarItemCarrito = new _cls10();
        olclmodificarEliminarItemCarrito = new _cls11();
    }

    private void actualizarVistaResumidaCarrito()
    {
        ListView listview = (ListView)findViewById(0x7f0600f2);
        CursorCarritoPedidoTotales cursorcarritopedidototales;
        if(crCarritoPedido == null)
            crCarritoPedido = MainActivity.mDbHelper.getListadoArticulosCarritoPedido();
        else
            crCarritoPedido.requery();
        listview.setAdapter(new PedidoAdaptarArticulosCarrito(this, crCarritoPedido, oclModificarItemCarrito, olclmodificarEliminarItemCarrito));
        cursorcarritopedidototales = MainActivity.mDbHelper.getTotalesCarritoPedido();
        if(cursorcarritopedidototales.getCount() > 0)
        {
            double d = cursorcarritopedidototales.getTotalExcentoMasIVA();
            ((TextView)findViewById(0x7f0600f1)).setText((new StringBuilder("(Bs. ")).append(MainActivity.formatVE(d)).append(")").toString());
            findViewById(0x7f060112).setVisibility(8);
            if(clienteLimiteCredido > 0.0D && d > clienteLimiteCredido)
            {
                MainActivity.crearMensajeToast(this, "Se ha excedido el limite de credito actual otorgado al cliente.", true);
                findViewById(0x7f060112).setVisibility(0);
            }
        }
    }

    private void crearAccionesToolbar()
    {
        ((ImageView)findViewById(0x7f060134)).setOnClickListener(accionToolbariIemSeleccionarLineaProducto);
        ((ImageView)findViewById(0x7f060133)).setOnClickListener(accionToolbariIemSeleccionarCliente);
        ((ImageView)findViewById(0x7f06013b)).setOnClickListener(accionToolbariIemCerrarPedido);
        ((ImageView)findViewById(0x7f060136)).setOnClickListener(accionToolbariIemVerCarrito);
        ((ImageView)findViewById(0x7f060137)).setOnClickListener(accionToolbariIemCrearObservacion);
    }

    private void desactivarAccionesToolbar()
    {
        findViewById(0x7f060132).setVisibility(8);
    }

    private int guardarInformacionPedido()
    {
        int i = 1;
        if(clienteSeleccionadoID <= 0)
            Toast.makeText(this, "Debe seleccionar alg\372n cliente.", i).show();
        else
        if(crCarritoPedido == null || crCarritoPedido.getCount() == 0)
        {
            Toast.makeText(this, "Agregue por lo menos un producto al carrito.", i).show();
            i = 2;
        } else
        {
            DBHandle.actualizarItemsDetallePedidoModificado(idPedidoGenerado);
            DBHandle.actualizarObservacionPedidoModificado(idPedidoGenerado, descripcionObservacion);
            DBHandle.crearEventosDeModificacionPedidos(idPedidoGenerado);
            i = 0;
        }
        return i;
    }

    private void indicarDatosCliente()
    {
        onActivityResult(2, -1, getIntent());
    }

    private void llenarCarritoItemsPedido()
    {
        MainActivity.mDbHelper.crearCarrito();
        DBHandle.llenarCarritoItemsPedido(idPedidoGenerado);
        onActivityResult(1, -1, null);
    }

    private void recuperarObservacionPedido()
    {
        String s = DBHandle.obtenerObservacionPedido(idPedidoGenerado);
        ((EditText)findViewById(0x7f06007c)).setText(s);
    }

    protected void onActivityResult(int i, int j, Intent intent) {
    	Bundle bundle1;
    	Bundle bundle;
        int k, l, i1;
        
        TextView textview;
        Object aobj[];
        
        if(j == -1 && i == 6 && guardarInformacionPedido() == 0){
            setResult(-1);
            finish();
            Toast.makeText(this, "Pedido modificado exitosamente", 1).show();
        }
        
        if(j == -1 && i == 5)
            actualizarVistaResumidaCarrito();
        if(j == -1 && i == 3){
            String s = intent.getExtras().getString("LINEAS_SELECCIONADAS");
            if(s.equalsIgnoreCase("todas")){
                cc = MainActivity.mDbHelper.getListadoProductos(com.netbong.fuerza.db.cursores.CursorCatalogo.SortBy.nombre);
                catalogoProductos.setAdapter(new AdapterCatalogoProductos(this, cc, oclDetalleProducto));
            } else {
                cc = MainActivity.mDbHelper.getListadoProductos(com.netbong.fuerza.db.cursores.CursorCatalogo.SortBy.nombre, s);
                catalogoProductos.setAdapter(new AdapterCatalogoProductos(this, cc, oclDetalleProducto));
            }
        }
        if(j == -1 && i == 1)
            actualizarVistaResumidaCarrito();
        if(j == -1 && i == 2)
        {
            bundle1 = intent.getExtras();
            clienteSeleccionadoID = bundle1.getInt("ID");
            clienteSeleccionadoRIF = bundle1.getString("RIF");
            clienteSeleccionadoNombre = bundle1.getString("CLIENTE");
            clienteLimiteCredido = bundle1.getDouble("LIMITE_CREDITO");
            textview = (TextView)findViewById(0x7f060001);
            aobj = new Object[3];
            aobj[0] = clienteSeleccionadoRIF;
            aobj[1] = clienteSeleccionadoNombre;
            aobj[2] = MainActivity.formatVE(clienteLimiteCredido);
            textview.setText(String.format("Cliente: %s - %s - Limite Credito Bs.: %s", aobj));
            cc = MainActivity.mDbHelper.getListadoProductos(com.netbong.fuerza.db.cursores.CursorCatalogo.SortBy.nombre);
            catalogoProductos.setAdapter(new AdapterCatalogoProductos(this, cc, oclDetalleProducto));
        }
        
        if(j != -1 && i == 2 && clienteSeleccionadoID == 0){
            Toast.makeText(this, "Cliente NO indicado.", 1).show();
            finish();
        }
        
        if(i == 4){
            if(j == -1){
                i1 = intent.getExtras().getInt("ITEM_CARRITO");
                if(agregarProductoAlPedido == null)
                    agregarProductoAlPedido = new Intent(this, PedidoSeleccionProducto.class);
                agregarProductoAlPedido.putExtra("ITEM_CARRITO", i1);
                startActivityForResult(agregarProductoAlPedido, 1);
            }
            if(j != -1)
                actualizarVistaResumidaCarrito();
        }
        if(j == -1 && i == 7){
            bundle = intent.getExtras();
            k = bundle.getInt("ITEM");
            l = bundle.getInt("TIPO");
            if(l == 1)
            {
                MainActivity.mDbHelper.eliminarArticuloCarrito(k);
                actualizarVistaResumidaCarrito();
            }
            if(l == 2)
            {
                crCarritoPedido.getEsPromocion();
                if(crCarritoPedido.getEsPromocion() == 0)
                {
                    if(agregarProductoAlPedido == null)
                        agregarProductoAlPedido = new Intent(this, PedidoSeleccionProducto.class);
                    agregarProductoAlPedido.putExtra("ITEM_CARRITO", crCarritoPedido.getId());
                    agregarProductoAlPedido.putExtra("ES_PROMO", 0);
                    agregarProductoAlPedido.putExtra("ID-CLIETE", clienteSeleccionadoID);
                    startActivityForResult(agregarProductoAlPedido, 1);
                }
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pedido_layout);
        crearAccionesToolbar();
        desactivarAccionesToolbar();
        catalogoProductos = (ListView)findViewById(0x7f06001e);
        catalogoProductos.setCacheColorHint(0);
        catalogoProductos.setDivider(null);
        areaObservaciones = (LinearLayout)findViewById(0x7f060113);
        areaObservaciones.findViewById(0x7f060011).setOnClickListener(accionCancelObservacion);
        areaObservaciones.findViewById(0x7f060012).setOnClickListener(accionOkObservacion);
        Gallery gallery = (Gallery)findViewById(0x7f060111);
        gallery.setAdapter(new PedidoAdaptarPromos(this, MainActivity.mDbHelper.getListadoPromociones()));
        gallery.setOnItemClickListener(accionBarraPromocionesVer);
        if(bundle != null)
        {
            Intent intent1 = getIntent();
            intent1.putExtras(bundle);
            onActivityResult(2, -1, intent1);
            actualizarVistaResumidaCarrito();
        } else
        {
            Intent intent = getIntent();
            idPedidoGenerado = intent.getIntExtra("ID-PEDIDO", 0);
            clienteSeleccionadoID = intent.getIntExtra("ID", 0);
            indicarDatosCliente();
            llenarCarritoItemsPedido();
            recuperarObservacionPedido();
        }
    }

    private static final int INTENT_REQUEST_AGREGAR_PRODUCTO = 1;
    private static final int INTENT_REQUEST_CERRAR_PEDIDO = 6;
    private static final int INTENT_REQUEST_ELIMINAR_MODIFICAR_ITEM_CARRITO = 7;
    private static final int INTENT_REQUEST_FILTRAR_LINEA_PRODUCTO = 3;
    private static final int INTENT_REQUEST_SELECCIONAR_CLIENTE = 2;
    private static final int INTENT_REQUEST_VER_DETALLE_CARRITO = 4;
    private static final int INTENT_REQUEST_VER_DETALLE_PROMO = 5;
    private android.widget.AdapterView.OnItemClickListener accionBarraPromocionesVer;
    private android.view.View.OnClickListener accionCancelObservacion;
    private android.view.View.OnClickListener accionOkObservacion;
    private android.view.View.OnClickListener accionToolbariIemCerrarPedido;
    private android.view.View.OnClickListener accionToolbariIemCrearObservacion;
    private android.view.View.OnClickListener accionToolbariIemSeleccionarCliente;
    private android.view.View.OnClickListener accionToolbariIemSeleccionarLineaProducto;
    private android.view.View.OnClickListener accionToolbariIemVerCarrito;
    private Intent agregarProductoAlPedido;
    private LinearLayout areaObservaciones;
    private ListView catalogoProductos;
    private CursorCatalogo cc;
    private double clienteLimiteCredido;
    private int clienteSeleccionadoID;
    private String clienteSeleccionadoNombre;
    private String clienteSeleccionadoRIF;
    private CursorCarritoPedido crCarritoPedido;
    private String descripcionObservacion;
    private Intent dialogoSiNo;
    private Intent filtrarLineaProducto;
    private int idPedidoGenerado;
    private android.view.View.OnClickListener oclDetalleProducto;
    private android.view.View.OnClickListener oclModificarItemCarrito;
    private android.view.View.OnLongClickListener olclmodificarEliminarItemCarrito;
    private Intent opcEliminarModificar;
    private Intent seleccionarCliente;
    private Intent verDetalleCarrito;
    private Intent verDetallePromo;
    private Intent verPedidoResumen;
























    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            EditText edittext = (EditText)areaObservaciones.findViewById(0x7f06007c);
            descripcionObservacion = edittext.getText().toString();
            accionCancelObservacion.onClick(null);
        }

        final PedidoModificacion this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((EditText)areaObservaciones.findViewById(0x7f06007c)).setText(descripcionObservacion);
            areaObservaciones.setVisibility(8);
            catalogoProductos.setVisibility(0);
        }

        final PedidoModificacion this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls3
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            int j = Integer.parseInt(view.getTag().toString());
            if(verDetallePromo == null)
                verDetallePromo = new Intent(PedidoModificacion.this, PedidoVerDetallePromocion.class);
            verDetallePromo.putExtra("PROMO_ID", j);
            startActivityForResult(verDetallePromo, 5);
        }

        final PedidoModificacion this$0;

        _cls3()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            catalogoProductos.setVisibility(8);
            areaObservaciones.setVisibility(0);
        }

        final PedidoModificacion this$0;

        _cls4()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(filtrarLineaProducto == null)
                filtrarLineaProducto = new Intent(PedidoModificacion.this, SeleccionarLineaProducto.class);
            startActivityForResult(filtrarLineaProducto, 3);
        }

        final PedidoModificacion this$0;

        _cls5()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(verDetalleCarrito == null)
                verDetalleCarrito = new Intent(PedidoModificacion.this, DetalleCarrito.class);
            verDetalleCarrito.putExtra("LIMITE_CREDITO", clienteLimiteCredido);
            startActivityForResult(verDetalleCarrito, 4);
        }

        final PedidoModificacion this$0;

        _cls6()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls7
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(seleccionarCliente == null)
                seleccionarCliente = new Intent(PedidoModificacion.this, SeleccionarCliente.class);
            startActivityForResult(seleccionarCliente, 2);
        }

        final PedidoModificacion this$0;

        _cls7()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls8
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(dialogoSiNo == null)
            {
                dialogoSiNo = new Intent(PedidoModificacion.this, DialogoSiNo.class);
                dialogoSiNo.putExtra("MENSAJE", "La informacion del pedido original sera modificada. \277Desea continuar?");
            }
            startActivityForResult(dialogoSiNo, 6);
        }

        final PedidoModificacion this$0;

        _cls8()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls9
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(agregarProductoAlPedido == null)
                agregarProductoAlPedido = new Intent(PedidoModificacion.this, PedidoSeleccionProducto.class);
            View view1 = ((LinearLayout)view).findViewById(0x7f060115);
            cc.moveToPosition(Integer.parseInt(view1.getTag().toString()));
            agregarProductoAlPedido.removeExtra("ITEM_CARRITO");
            agregarProductoAlPedido.putExtra("ID", cc.getId());
            agregarProductoAlPedido.putExtra("ID-CLIETE", clienteSeleccionadoID);
            startActivityForResult(agregarProductoAlPedido, 1);
        }

        final PedidoModificacion this$0;

        _cls9()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls10
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            int i = Integer.parseInt(view.getTag().toString());
            crCarritoPedido.moveToPosition(i);
            crCarritoPedido.getEsPromocion();
            if(crCarritoPedido.getEsPromocion() == 0)
            {
                if(agregarProductoAlPedido == null)
                    agregarProductoAlPedido = new Intent(PedidoModificacion.this, PedidoSeleccionProducto.class);
                agregarProductoAlPedido.putExtra("ITEM_CARRITO", crCarritoPedido.getId());
                agregarProductoAlPedido.putExtra("ES_PROMO", 0);
                agregarProductoAlPedido.putExtra("ID-CLIETE", clienteSeleccionadoID);
                startActivityForResult(agregarProductoAlPedido, 1);
            }
        }

        final PedidoModificacion this$0;

        _cls10()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }


    private class _cls11
        implements android.view.View.OnLongClickListener
    {

        public boolean onLongClick(View view)
        {
            int i = Integer.parseInt(view.getTag().toString());
            crCarritoPedido.moveToPosition(i);
            if(opcEliminarModificar == null)
                opcEliminarModificar = new Intent(PedidoModificacion.this, PedidoCreacionModiicarEliminarItem.class);
            opcEliminarModificar.putExtra("IMG", crCarritoPedido.getProductoImagen());
            opcEliminarModificar.putExtra("ITEM", crCarritoPedido.getId());
            startActivityForResult(opcEliminarModificar, 7);
            return false;
        }

        final PedidoModificacion this$0;

        _cls11()
        {
        	super();
        	this$0 = PedidoModificacion.this;
        }
    }

}
