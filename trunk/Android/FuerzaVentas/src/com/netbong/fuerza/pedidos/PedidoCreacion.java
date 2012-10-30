// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.PedidoVerDetallePromocion;
import com.netbong.fuerza.clientes.FichaCliente;
import com.netbong.fuerza.clientes.SeleccionarCliente;
import com.netbong.fuerza.clientes.db.cursors.CrsClientes;
import com.netbong.fuerza.clientes.db.cursors.CrsMovimientosResumen;
import com.netbong.fuerza.pedidos.adapters.AdapterCarritoProductos;
import com.netbong.fuerza.pedidos.adapters.AdapterCatalogo;
import com.netbong.fuerza.pedidos.db.DBHandle;
import com.netbong.fuerza.pedidos.db.cursors.CrsCarrito;
import com.netbong.fuerza.pedidos.db.cursors.CrsCarritoItemsProductos;
import com.netbong.fuerza.pedidos.db.cursors.CrsPedidos;
import com.netbong.fuerza.pedidos.db.cursors.CrsProductos;

// Referenced classes of package com.ehp.droidsf.pedidos:
//            PedidoResumen

public class PedidoCreacion extends Activity
{

    public PedidoCreacion()
    {
        textWatcher = new _cls1();
        oclBlanquear = new _cls2();
        accionOkObservacion = new _cls3();
        accionCancelObservacion = new _cls4();
        accionOkObservacionProntoPago = new _cls5();
        accionCancelObservacionProntoPago = new _cls6();
        accionBarraPromocionesVer = new _cls7();
        accionToolbariIemCrearObservacion = new _cls8();
        accionToolbariIemSeleccionarLineaProducto = new _cls9();
        accionToolbariIemVerCarrito = new _cls10();
        accionToolbariIemVerFichaCliente = new _cls11();
        accionToolbariIemSeleccionarCliente = new _cls12();
        accionToolbariIemCerrarPedido = new _cls13();
        accionToolbariIemCodigoAutorizLimiteCredito = new _cls14();
        oclDetalleProducto = new _cls15();
        oclModificarItemCarrito = new _cls16();
        trasladarFocus = new _cls17();
        olclmodificarEliminarItemCarrito = new _cls18();
    }

    private ProgressDialog activarWhile()
    {
        return ProgressDialog.show(this, "", "Guardando informacion. Por favor espere...", false);
    }

    private void actualizarVistaResumidaCarrito()
    {
        ListView listview = (ListView)findViewById(0x7f0600f2);
        double d;
        if(crsCarritoItemsProductos == null)
            crsCarritoItemsProductos = CrsCarritoItemsProductos.getItemsProductos();
        else
            crsCarritoItemsProductos.requery();
        listview.setAdapter(new AdapterCarritoProductos(this, crsCarritoItemsProductos, oclModificarItemCarrito, olclmodificarEliminarItemCarrito));
        d = DBHandle.carritoDeterminarItemsTotalesBs();
        ((TextView)findViewById(0x7f0600f1)).setText((new StringBuilder("(Bs. ")).append(MainActivity.formatVE(d)).append(")").toString());
        findViewById(0x7f060112).setVisibility(8);
        if(limiteCreditoExcedido())
            startActivityForResult(MainActivity.getItentDialogueAccept("Se ha excedido el limite de credito actual otorgado al cliente."), 9);
    }

    private void cargarCliente(int i)
    {
        crsCliente = CrsClientes.getCliente(i);
        if(crsCliente.moveToFirst())
        {
            String s = crsCliente.getClienteRif().trim();
            String s1 = crsCliente.getClienteNombre().trim();
            double d = crsCliente.getClienteLiminteCredito();
            TextView textview = (TextView)findViewById(0x7f060001);
            Object aobj[] = new Object[3];
            aobj[0] = s;
            aobj[1] = s1;
            aobj[2] = MainActivity.formatVE(d);
            textview.setText(String.format("Cliente: %s - %s - Limite Credito Bs.: %s", aobj));
            crsProductos = CrsProductos.getProductos();
            catalogoProductos.setAdapter(new AdapterCatalogo(this, crsProductos, oclDetalleProducto));
            if(crsCarrito == null)
            {
                DBHandle.carritoCrear(crsCliente.getClienteId());
                crsCarrito = CrsCarrito.getCarrito();
            } else
            {
                DBHandle.carritoCambiarCliente(crsCliente.getClienteId());
                crsCarrito.requery();
                crsCarrito.moveToFirst();
            }
            mostrarRestriccionesTomaPedido();
        }
    }

    private void crearAccionesToolbar()
    {
        ((ImageView)findViewById(0x7f060134)).setOnClickListener(accionToolbariIemSeleccionarLineaProducto);
        ((ImageView)findViewById(0x7f060133)).setOnClickListener(accionToolbariIemSeleccionarCliente);
        ((ImageView)findViewById(0x7f06013b)).setOnClickListener(accionToolbariIemCerrarPedido);
        ((ImageView)findViewById(0x7f060136)).setOnClickListener(accionToolbariIemVerCarrito);
        ((ImageView)findViewById(0x7f060137)).setOnClickListener(accionToolbariIemCrearObservacion);
        ((ImageView)findViewById(0x7f060138)).setOnClickListener(accionToolbariIemVerFichaCliente);
        ((ImageView)findViewById(0x7f06013a)).setOnClickListener(accionToolbariIemCodigoAutorizLimiteCredito);
    }

    private int guardarInformacionPedido()
    {
        boolean flag = true;
        int result = 0;
        
        if(limiteCreditoExcedido())
            startActivityForResult(MainActivity.getItentDialogueAccept("Se ha excedido el limite de credito actual otorgado al cliente. Se requiere codigo de autorizacion."), 9);
        else
        if(crsCarritoItemsProductos == null || crsCarritoItemsProductos.getCount() == 0)
            MainActivity.crearMensajeToast(this, "Agregue por lo menos un articulo al carrito.", flag);
        else
        if(crsPedidos != null && crsPedidos.moveToFirst())
        	result = pedidoModificar();
        else
        	result = pedidoCrear();
        
        return result;
    }

    private boolean limiteCreditoExcedido()
    {
        boolean flag;
        if(crsCarrito.getCarrtoAutorizacionLimiteCreditoId() > 0)
        {
            findViewById(0x7f060139).setVisibility(8);
            flag = false;
        } else
        {
            double d = DBHandle.carritoDeterminarItemsTotalesBs();
            double d1 = crsCliente.getClienteLiminteCredito();
            if(d1 != 0.0D && crsPedidos != null)
                d1 += crsPedidos.getPedidoTotalGeneral();
            if(d1 != 0.0D && d > d1)
                flag = true;
            else
                flag = false;
            if(flag)
                findViewById(0x7f060139).setVisibility(0);
            else
                findViewById(0x7f060139).setVisibility(8);
        }
        return flag;
    }

    private void mostrarRestriccionesTomaPedido()
    {
        CrsMovimientosResumen crsmovimientosresumen = CrsMovimientosResumen.getMovimientosResumenRestrictivos(crsCliente.getClienteCodigo());
        if(crsmovimientosresumen.moveToFirst())
        {
            StringBuilder stringbuilder = new StringBuilder("Los siguientes movimientos registrados restringen la toma de un nuevo pedido:\n\r\n\r");
            do
                stringbuilder.append(crsmovimientosresumen.getMovimientoResumenConcepto()).append(": ").append(crsmovimientosresumen.getMovimientoResumenComentario()).append("\n\r");
            while(crsmovimientosresumen.moveToNext());
            ((ImageView)findViewById(0x7f06013b)).setOnClickListener(null);
            startActivity(MainActivity.getItentDialogueAccept(stringbuilder.toString()));
        } else
        {
            ((ImageView)findViewById(0x7f06013b)).setOnClickListener(accionToolbariIemCerrarPedido);
        }
        crsmovimientosresumen.close();
    }

    private int pedidoCrear()
    {
        int i = DBHandle.pedidoCrearNuevo();
        DBHandle.pedidoCrearAgregarItems(i);
        DBHandle.pedidoCrearAgregarEvento(i);
        crsCarritoItemsProductos.moveToFirst();
        do
        {
            DBHandle.productoBajarStock(crsCarritoItemsProductos.getItemProductoId(), crsCarritoItemsProductos.getItemCantidad());
            int j = crsCarritoItemsProductos.getItemCodigoAutorizacionId();
            if(j > 0)
                DBHandle.autorizacionPrecioMarcarComoUtilizada(j, i);
        } while(crsCarritoItemsProductos.moveToNext());
        crsPedidos = CrsPedidos.getPedido(i);
        DBHandle.clienteBajarLimiteCredito(crsCliente.getClienteId(), crsPedidos.getPedidoTotalGeneral());
        DBHandle.carritoCerrar();
        return 0;
    }

    private int pedidoModificar()
    {
        DBHandle.pedidoModificarCabecera(crsPedidos.getPedidoId(), crsCarrito.getCarrtoClienteId(), crsCarrito.getCarritoObservacion(), crsCarrito.getCarritoObservacionProntoPago());
        DBHandle.pedidoModificarEliminarDetalle(crsPedidos.getPedidoId());
        DBHandle.pedidoCrearAgregarItems(crsPedidos.getPedidoId());
        DBHandle.pedidoModificarAgregarEventos(crsPedidos.getPedidoId());
        crsCarritoItemsProductos.moveToFirst();
        do
        {
            if(crsCarritoItemsProductos.getItemCantidadAnt() > 0)
                DBHandle.productoSubirStock(crsCarritoItemsProductos.getItemProductoId(), crsCarritoItemsProductos.getItemCantidadAnt());
            DBHandle.productoBajarStock(crsCarritoItemsProductos.getItemProductoId(), crsCarritoItemsProductos.getItemCantidad());
            int i = crsCarritoItemsProductos.getItemCodigoAutorizacionId();
            if(i > 0)
                DBHandle.autorizacionPrecioMarcarComoUtilizada(i, crsPedidos.getPedidoId());
        } while(crsCarritoItemsProductos.moveToNext());
        DBHandle.clienteSubirLimiteCredito(crsCliente.getClienteId(), DBHandle.carritoTotalGeneralAnterior());
        crsPedidos = CrsPedidos.getPedido(crsPedidos.getPedidoId());
        DBHandle.clienteBajarLimiteCredito(crsCliente.getClienteId(), crsPedidos.getPedidoTotalGeneral());
        DBHandle.carritoCerrar();
        return 0;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
    	int l, k, i1;
        
        ProgressDialog progressdialog;
        
        if(j == -1 && i == 10)
        {
            String s1 = intent.getExtras().getString("INPUT");
            String s2 = MainActivity.settings.getString("login", MainActivity.getDefaultLogin());
            if(DBHandle.autorizacionLimiteCreditoValidar(s1, s2, crsCliente.getClienteCodigo()))
            {
                DBHandle.carritoAsignarAutorizacionLimiteCredito(DBHandle.autorizacionLimiteCreditoId(s1, s2, crsCliente.getClienteCodigo()));
                crsCarrito.requery();
                crsCarrito.moveToFirst();
                findViewById(0x7f060139).setVisibility(8);
            } else
            {
                MainActivity.crearMensajeToast(this, (new StringBuilder("Codigo de Autorizacion Invalido: ")).append(s1).toString(), true);
            }
        }
        if(j == -1 && i == 8)
        {
            DBHandle.carritoCerrar();
            setResult(1);
            finish();
        }
        if(j == -1 && i == 6)
        {
            progressdialog = activarWhile();
            i1 = guardarInformacionPedido();
            progressdialog.cancel();
            if(i1 == 0)
            {
                Toast.makeText(this, "Los dato del pedido han sido guardados exitosamente", 1).show();
                int j1;
                if(getIntent() == null)
                    j1 = 0;
                else
                if(getIntent().getExtras() == null)
                    j1 = 0;
                else
                    j1 = getIntent().getExtras().getInt("PEDIDO", 0);
                if(j1 > 0)
                {
                    setResult(-1);
                    finish();
                } else
                {
                    if(verPedidoResumen == null)
                        verPedidoResumen = new Intent(this, PedidoResumen.class);
                    verPedidoResumen.putExtra("PEDIDO", crsPedidos.getPedidoId());
                    verPedidoResumen.putExtra("VISTA_IMPRESION", 1);
                    finish();
                    startActivity(verPedidoResumen);
                }
            }
        }
        if(j == -1 && i == 5)
            actualizarVistaResumidaCarrito();
        if(j == -1 && i == 3)
        {
            String s = intent.getExtras().getString("LINEAS_SELECCIONADAS");
            if(s.equalsIgnoreCase("todas"))
            {
                crsProductos = CrsProductos.getProductos();
                catalogoProductos.setAdapter(new AdapterCatalogo(this, crsProductos, oclDetalleProducto));
            } else
            {
                crsProductos = CrsProductos.getProductosPorLinea(s);
                catalogoProductos.setAdapter(new AdapterCatalogo(this, crsProductos, oclDetalleProducto));
            }
        }
        if(j == -1 && i == 1)
            actualizarVistaResumidaCarrito();
        if(j == -1 && i == 2)
            cargarCliente(intent.getExtras().getInt("ID"));
        if(j != -1 && i == 2 && (crsCliente == null || !crsCliente.moveToFirst()))
        {
            Toast.makeText(this, "Cliente NO indicado.", 1).show();
            finish();
        }
        if(i == 4)
        {
            if(j == -1)
            {
                l = intent.getExtras().getInt("ID-PRODUCTO");
                agregarProductoAlPedido.putExtra("ID", l);
                agregarProductoAlPedido.putExtra("CLIETE-TIPO-PRECIO", crsCliente.getClienteTipoPrecio());
                startActivityForResult(agregarProductoAlPedido, 1);
            }
            if(j != -1)
                actualizarVistaResumidaCarrito();
        }
        if(j == -1 && i == 7)
        {
            k = intent.getExtras().getInt("TIPO");
            if(k == 1)
            {
                DBHandle.carritoEliminarItem(crsCarritoItemsProductos.getItemId());
                actualizarVistaResumidaCarrito();
            }
            if(k == 2 && !crsCarritoItemsProductos.getItemEsPromocion())
            {
                agregarProductoAlPedido.putExtra("ID", crsCarritoItemsProductos.getItemProductoId());
                agregarProductoAlPedido.putExtra("CLIETE-TIPO-PRECIO", crsCliente.getClienteTipoPrecio());
                startActivityForResult(agregarProductoAlPedido, 1);
            }
        }
        if(i == 9)
            crsCarritoItemsProductos.moveToLast();
    }

    protected void onCreate(Bundle bundle)
    {
        int i;
        i = 0;
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.pedido_layout);
        crearAccionesToolbar();
        rbTipoFiltroCodigo = (RadioButton)findViewById(0x7f06013d);
        rbTipoFiltroCodigo.setOnClickListener(oclBlanquear);
        findViewById(0x7f06013e).setOnClickListener(oclBlanquear);
        catalogoProductos = (ListView)findViewById(0x7f06001e);
        catalogoProductos.setCacheColorHint(0);
        catalogoProductos.setDivider(null);
        campoFiltroProducto = (EditText)findViewById(0x7f06013c);
        campoFiltroProducto.addTextChangedListener(textWatcher);
        areaObservaciones = (LinearLayout)findViewById(0x7f060113);
        areaObservaciones.findViewById(0x7f060011).setOnClickListener(accionCancelObservacion);
        areaObservaciones.findViewById(0x7f060012).setOnClickListener(accionOkObservacion);
        findViewById(0x7f060114).setOnClickListener(trasladarFocus);
        
        if(getIntent() != null && getIntent().getExtras() != null)
            i = getIntent().getExtras().getInt("PEDIDO", 0);
        if(i > 0)
        {
            DBHandle.carritoCerrar();
            crsPedidos = CrsPedidos.getPedido(i);
            DBHandle.carritoCrear(i, crsPedidos.getClienteId(), crsPedidos.getPedidoObservacion(), crsPedidos.getPedidoObservacionProntoPago(), crsPedidos.getPedidoFecha());
            crsCarrito = CrsCarrito.getCarrito();
            cargarCliente(crsPedidos.getClienteId());
            DBHandle.carritoAgregarProducto(i);
            actualizarVistaResumidaCarrito();
        } else
        if(DBHandle.carritoAbierto())
        {
            startActivity(MainActivity.getItentDialogueAccept("Se encontro informacion de un pedido previo que no habia sido cerrado. El mismo sera cargado."));
            crsCarrito = CrsCarrito.getCarrito();
            if(crsCarrito.getCarrtoPedidoId() > 0)
                crsPedidos = CrsPedidos.getPedido(crsCarrito.getCarrtoPedidoId());
            cargarCliente(crsCarrito.getCarrtoClienteId());
            actualizarVistaResumidaCarrito();
        } else
        {
            DBHandle.carritoCerrar();
            accionToolbariIemSeleccionarCliente.onClick(null);
        }
        return;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            startActivityForResult(MainActivity.getItentDialogueYesNoQuestion("Esto cancelara la toma del pedido. \277Desea continuar?"), 8);
            flag = true;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    private static final int INTENT_REQUEST_AGREGAR_PRODUCTO = 1;
    private static final int INTENT_REQUEST_CERRAR_PEDIDO = 6;
    private static final int INTENT_REQUEST_ELIMINAR_MODIFICAR_ITEM_CARRITO = 7;
    private static final int INTENT_REQUEST_FILTRAR_LINEA_PRODUCTO = 3;
    private static final int INTENT_REQUEST_KEYCODE_BACK_PRESS = 8;
    private static final int INTENT_REQUEST_LIMITE_CREDITO_AUTORIZACION = 10;
    private static final int INTENT_REQUEST_LIMITE_CREDITO_EXCEDIDO = 9;
    private static final int INTENT_REQUEST_SELECCIONAR_CLIENTE = 2;
    private static final int INTENT_REQUEST_VER_DETALLE_CARRITO = 4;
    private static final int INTENT_REQUEST_VER_DETALLE_PROMO = 5;
    private android.widget.AdapterView.OnItemClickListener accionBarraPromocionesVer;
    private android.view.View.OnClickListener accionCancelObservacion;
    private android.view.View.OnClickListener accionCancelObservacionProntoPago;
    private android.view.View.OnClickListener accionOkObservacion;
    private android.view.View.OnClickListener accionOkObservacionProntoPago;
    private android.view.View.OnClickListener accionToolbariIemCerrarPedido;
    private android.view.View.OnClickListener accionToolbariIemCodigoAutorizLimiteCredito;
    private android.view.View.OnClickListener accionToolbariIemCrearObservacion;
    private android.view.View.OnClickListener accionToolbariIemSeleccionarCliente;
    private android.view.View.OnClickListener accionToolbariIemSeleccionarLineaProducto;
    private android.view.View.OnClickListener accionToolbariIemVerCarrito;
    android.view.View.OnClickListener accionToolbariIemVerFichaCliente;
    private Intent agregarProductoAlPedido;
    private LinearLayout areaObservaciones;
    private EditText campoFiltroProducto;
    private ListView catalogoProductos;
    private CrsCarrito crsCarrito;
    private CrsCarritoItemsProductos crsCarritoItemsProductos;
    private CrsClientes crsCliente;
    private CrsPedidos crsPedidos;
    private CrsProductos crsProductos;
    private Intent filtrarLineaProducto;
    private android.view.View.OnClickListener oclBlanquear;
    private android.view.View.OnClickListener oclDetalleProducto;
    private android.view.View.OnClickListener oclModificarItemCarrito;
    private android.view.View.OnLongClickListener olclmodificarEliminarItemCarrito;
    private Intent opcEliminarModificar;
    private RadioButton rbTipoFiltroCodigo;
    private Intent seleccionarCliente;
    private TextWatcher textWatcher;
    private android.view.View.OnClickListener trasladarFocus;
    private Intent verDetalleCarrito;
    private Intent verDetallePromo;
    private Intent verFichaCliente;
    private Intent verPedidoResumen;



    private class _cls1
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            Editable editable1 = campoFiltroProducto.getText();
            if(editable1 != null) {
            	String s = editable1.toString();
            	
            	if(s != null) {
            		String s1 = s.trim();
            		
            		if(s1 != null) {
            			PedidoCreacion pedidocreacion = PedidoCreacion.this;
            			CrsProductos crsproductos;
            			if(rbTipoFiltroCodigo.isChecked()){
            				crsproductos = CrsProductos.getProductosPorPatronCodigo(s1);
            			} else {
            				crsproductos = CrsProductos.getProductosPorPatronNombre(s1);
            			}
            			
            			pedidocreacion.crsProductos = crsproductos;
            			catalogoProductos.setAdapter(new AdapterCatalogo(PedidoCreacion.this, crsProductos, oclDetalleProducto));
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

        final PedidoCreacion this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            campoFiltroProducto.setText(null);
        }

        final PedidoCreacion this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            DBHandle.carritoCambiarObservacion(((EditText)areaObservaciones.findViewById(0x7f06007c)).getText().toString());
            crsCarrito.requery();
            crsCarrito.moveToFirst();
            accionCancelObservacion.onClick(null);
        }

        final PedidoCreacion this$0;

        _cls3()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            areaObservaciones.setVisibility(8);
            catalogoProductos.setVisibility(0);
        }

        final PedidoCreacion this$0;

        _cls4()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            DBHandle.carritoCambiarObservacion(((EditText)areaObservaciones.findViewById(0x7f06007c)).getText().toString());
            accionCancelObservacion.onClick(null);
        }

        final PedidoCreacion this$0;

        _cls5()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            areaObservaciones.setVisibility(8);
            catalogoProductos.setVisibility(0);
        }

        final PedidoCreacion this$0;

        _cls6()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls7
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            int j = Integer.parseInt(view.getTag().toString());
            if(verDetallePromo == null)
                verDetallePromo = new Intent(PedidoCreacion.this, PedidoVerDetallePromocion.class);
            verDetallePromo.putExtra("PROMO_ID", j);
            startActivityForResult(verDetallePromo, 5);
        }

        final PedidoCreacion this$0;

        _cls7()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls8
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            catalogoProductos.setVisibility(8);
            areaObservaciones.setVisibility(0);
            EditText edittext = (EditText)areaObservaciones.findViewById(0x7f06007c);
            edittext.setText(crsCarrito.getCarritoObservacion());
            ((InputMethodManager)getSystemService("input_method")).showSoftInput(edittext, 2);
        }

        final PedidoCreacion this$0;

        _cls8()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls9
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(filtrarLineaProducto == null)
                filtrarLineaProducto = new Intent(PedidoCreacion.this, SeleccionarLineaProducto.class);
            startActivityForResult(filtrarLineaProducto, 3);
        }

        final PedidoCreacion this$0;

        _cls9()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls10
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(verDetalleCarrito == null)
                verDetalleCarrito = new Intent(PedidoCreacion.this, DetalleCarrito.class);
            verDetalleCarrito.putExtra("LIMITE_CREDITO", crsCliente.getClienteLiminteCredito());
            startActivityForResult(verDetalleCarrito, 4);
        }

        final PedidoCreacion this$0;

        _cls10()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls11
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(verFichaCliente == null)
                verFichaCliente = new Intent(PedidoCreacion.this, FichaCliente.class);
            verFichaCliente.putExtra("ID_CLIENTE", crsCliente.getClienteId());
            Intent intent = verFichaCliente;
            boolean flag;
            if(crsCliente.getClienteCodigo().equalsIgnoreCase("NONE"))
                flag = false;
            else
                flag = true;
            intent.putExtra("PROFIT", flag);
            startActivity(verFichaCliente);
        }

        final PedidoCreacion this$0;

        _cls11()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls12
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(seleccionarCliente == null)
                seleccionarCliente = new Intent(PedidoCreacion.this, SeleccionarCliente.class);
            startActivityForResult(seleccionarCliente, 2);
        }

        final PedidoCreacion this$0;

        _cls12()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls13
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = MainActivity.getItentDialogueYesNoQuestion("Esto creara un nuevo Pedido para el cliente seleccionado. \277Desea continuar?");
            startActivityForResult(intent, 6);
        }

        final PedidoCreacion this$0;

        _cls13()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls14
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = MainActivity.getItentDialogueInput("Introduzca c\363digo de autorizaci\363n");
            startActivityForResult(intent, 10);
        }

        final PedidoCreacion this$0;

        _cls14()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls15
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(agregarProductoAlPedido == null)
                agregarProductoAlPedido = new Intent(PedidoCreacion.this, PedidoSeleccionProducto.class);
            View view1 = ((LinearLayout)view).findViewById(0x7f060115);
            crsProductos.moveToPosition(Integer.parseInt(view1.getTag().toString()));
            agregarProductoAlPedido.putExtra("ID", crsProductos.getProductoId());
            agregarProductoAlPedido.putExtra("CLIETE-TIPO-PRECIO", crsCliente.getClienteTipoPrecio());
            startActivityForResult(agregarProductoAlPedido, 1);
        }

        final PedidoCreacion this$0;

        _cls15()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls16
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            int i = Integer.parseInt(view.getTag().toString());
            crsCarritoItemsProductos.moveToPosition(i);
            if(!crsCarritoItemsProductos.getItemEsPromocion())
            {
                if(agregarProductoAlPedido == null)
                    agregarProductoAlPedido = new Intent(PedidoCreacion.this, PedidoSeleccionProducto.class);
                agregarProductoAlPedido.putExtra("ID", crsCarritoItemsProductos.getItemProductoId());
                agregarProductoAlPedido.putExtra("CLIETE-TIPO-PRECIO", crsCliente.getClienteTipoPrecio());
                startActivityForResult(agregarProductoAlPedido, 1);
            }
        }

        final PedidoCreacion this$0;

        _cls16()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls17
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            EditText edittext = (EditText)areaObservaciones.findViewById(0x7f06007c);
            edittext.setFocusable(true);
            ((InputMethodManager)getSystemService("input_method")).showSoftInput(edittext, 2);
        }

        final PedidoCreacion this$0;

        _cls17()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }


    private class _cls18
        implements android.view.View.OnLongClickListener
    {

        public boolean onLongClick(View view)
        {
            int i = Integer.parseInt(view.getTag().toString());
            crsCarritoItemsProductos.moveToPosition(i);
            if(opcEliminarModificar == null)
                opcEliminarModificar = new Intent(PedidoCreacion.this, PedidoCreacionModiicarEliminarItem.class);
            opcEliminarModificar.putExtra("IMG", crsCarritoItemsProductos.getItemProductoImagen());
            startActivityForResult(opcEliminarModificar, 7);
            return false;
        }

        final PedidoCreacion this$0;

        _cls18()
        {
        	super();
        	this$0 = PedidoCreacion.this;
        }
    }

}
