// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.PedidoAdaptarImagenesProducto;
import com.netbong.fuerza.pedidos.db.DBHandle;
import com.netbong.fuerza.pedidos.db.cursors.CrsAutorizacionPrecio;
import com.netbong.fuerza.pedidos.db.cursors.CrsCarritoItemsProductos;
import com.netbong.fuerza.pedidos.db.cursors.CrsProductos;

public class PedidoSeleccionProducto extends Activity
{

    public PedidoSeleccionProducto()
    {
        tipoPrecioIndicado = "Precio Base";
        cambiarPrecioAutorizacion = new _cls3();
        cambiarPrecio1 = new _cls4();
        cambiarPrecio2 = new _cls5();
        cambiarPrecio3 = new _cls6();
        cambiarPrecio4 = new _cls7();
        cambiarPrecio5 = new _cls8();
        agregarProductoAlCarrito = new _cls9();
    }

    private void cargarInformacionProducto(Bundle bundle)
    {
        int i = bundle.getInt("ID", 0);
        cargarProducto(i, bundle.getInt("CLIETE-TIPO-PRECIO", 0));
        cargarProductoImagenes(i);
        cargarItemCarrito(i);
    }

    private void cargarItemCarrito(int i)
    {
        crsCarrito = CrsCarritoItemsProductos.getItemPorProductoId(i);
        if(crsCarrito.getCount() > 0)
        {
            ((TextView)findViewById(0x7f060124)).setText(Integer.toString(crsCarrito.getItemCantidad()));
            ((TextView)findViewById(0x7f060125)).setText(Double.toString(crsCarrito.getItemPrecioAsignado()));
            tipoPrecioIndicado = crsCarrito.getItemProductoTipoPrecioIndicado();
            idAutorizacionPrecio = crsCarrito.getItemCodigoAutorizacionId();
            crsAutorizacionPrecio = CrsAutorizacionPrecio.getAutorizacionPorId(idAutorizacionPrecio);
        }
    }

    private void cargarProducto(int i, int j)
    {
        crsProductos = CrsProductos.getProductoPorId(i);
        ((ImageView)findViewById(0x7f06011c)).setImageDrawable(Drawable.createFromPath(crsProductos.getProductoImagenPrincipal()));
        ((TextView)findViewById(0x7f060120)).setText(crsProductos.getProductoCodigo().trim());
        ((TextView)findViewById(0x7f060121)).setText(crsProductos.getProductoNombre().trim());
        ((TextView)findViewById(0x7f060122)).setText((new StringBuilder("Bs. ")).append(MainActivity.formatVE(crsProductos.getProductoPrecioBase())).toString());
        ((TextView)findViewById(0x7f060125)).setText(Double.toString(crsProductos.getProductoPrecioBase()));
        ((TextView)findViewById(0x7f060124)).setText(Integer.toString(1));
        ((TextView)findViewById(0x7f06011e)).setText(crsProductos.getProductoMarca());
        ((ImageView)findViewById(0x7f06011d)).setImageDrawable(Drawable.createFromPath(crsProductos.getProductoMarcaImagen()));
        ((ImageView)findViewById(0x7f06011f)).setImageDrawable(Drawable.createFromPath(crsProductos.getProductoMarcaImagen()));
        findViewById(0x7f06012c).setVisibility(8);
        findViewById(0x7f060126).setOnClickListener(cambiarPrecio1);
        View view = findViewById(0x7f060127);
        View view1;
        View view2;
        View view3;
        if(j < 2)
            view.setVisibility(8);
        else
            view.setOnClickListener(cambiarPrecio2);
        view1 = findViewById(0x7f060128);
        if(j < 3)
            view1.setVisibility(8);
        else
            view1.setOnClickListener(cambiarPrecio3);
        view2 = findViewById(0x7f060129);
        if(j < 4)
            view2.setVisibility(8);
        else
            view2.setOnClickListener(cambiarPrecio4);
        view3 = findViewById(0x7f06012a);
        if(j < 5)
            view3.setVisibility(8);
        else
            view3.setOnClickListener(cambiarPrecio5);
    }

    private void cargarProductoImagenes(int i)
    {
        Gallery gallery = (Gallery)findViewById(0x7f06010f);
        gallery.setAdapter(new PedidoAdaptarImagenesProducto(this, MainActivity.mDbHelper.getImagenesDeProductos(i)));
        gallery.setOnItemClickListener(new _cls10());
    }

    private String mensajeTrazaModificar(int i)
    {
        String s;
        if(i > cantidadActualProducto)
        {
            Object aobj1[] = new Object[3];
            aobj1[0] = codigoProducto;
            aobj1[1] = descripcionProducto;
            aobj1[2] = Integer.valueOf(i - cantidadActualProducto);
            s = String.format("Articulo modificado: %s %s. Incluido %d mas al total.", aobj1);
        } else
        {
            Object aobj[] = new Object[3];
            aobj[0] = codigoProducto;
            aobj[1] = descripcionProducto;
            aobj[2] = Integer.valueOf(cantidadActualProducto - i);
            s = String.format("Articulo modificado: %s %s. Excluido %d del total.", aobj);
        }
        return s;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j != -1);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pedido_seleccion_producto_layout);
        imageView = (ImageView)findViewById(0x7f06011c);
        ((Button)findViewById(0x7f06012e)).setOnClickListener(agregarProductoAlCarrito);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
            cargarInformacionProducto(bundle1);
        findViewById(0x7f06012b).setOnClickListener(cambiarPrecioAutorizacion);
    }

    private static final int CANTIDAD = 1;
    private static final int INTENT_REQUEST_SELECCIONAR_MONTO_OFERTA = 1;
    private static final String tipo_precio_indicado_1 = "Precio 1";
    private static final String tipo_precio_indicado_2 = "Precio 2";
    private static final String tipo_precio_indicado_3 = "Precio 3";
    private static final String tipo_precio_indicado_4 = "Precio 4";
    private static final String tipo_precio_indicado_5 = "Precio 5";
    private static final String tipo_precio_indicado_autorizacion = "Precio Autorizado";
    private static final String tipo_precio_indicado_base = "Precio Base";
    private android.view.View.OnClickListener agregarProductoAlCarrito;
    private android.view.View.OnClickListener cambiarPrecio1;
    private android.view.View.OnClickListener cambiarPrecio2;
    private android.view.View.OnClickListener cambiarPrecio3;
    private android.view.View.OnClickListener cambiarPrecio4;
    private android.view.View.OnClickListener cambiarPrecio5;
    private android.view.View.OnClickListener cambiarPrecioAutorizacion;
    int cantidadActualProducto;
    String codigoProducto;
    private String codigoProductoAutorizacion;
    private CrsAutorizacionPrecio crsAutorizacionPrecio;
    private CrsCarritoItemsProductos crsCarrito;
    private CrsProductos crsProductos;
    String descripcionProducto;
    private ProgressDialog dialog;
    private int idAutorizacionPrecio;
    private ImageView imageView;
    final Handler mHandler = new Handler();
    final Runnable mUpdateResults2 = new _cls2();
    final Runnable mUpdateResults3 = new _cls1();
    private double precioAutorizacion;
    String presentacionProducto;
    private String tipoPrecioIndicado;









    private class _cls1
        implements Runnable
    {

        public void run()
        {
            dialog.cancel();
        }

        final PedidoSeleccionProducto this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls2
        implements Runnable
    {

        public void run()
        {
        }

        final PedidoSeleccionProducto this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            dialog = ProgressDialog.show(PedidoSeleccionProducto.this, "", "Buscando autorizaciones. Por favor espere...", false);
            class _cls1 extends Thread
            {

                public void run()
                {
                }

                final _cls3 this$1;

                _cls1()
                {
                	super();
                	this$1 = _cls3.this;
                }
            }

            (new _cls1()).start();
        }

        final PedidoSeleccionProducto this$0;

        _cls3()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((TextView)findViewById(0x7f060125)).setText(Double.toString(crsProductos.getProductoPrecio1()));
            tipoPrecioIndicado = "Precio 1";
        }

        final PedidoSeleccionProducto this$0;

        _cls4()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((TextView)findViewById(0x7f060125)).setText(Double.toString(crsProductos.getProductoPrecio2()));
            tipoPrecioIndicado = "Precio 2";
        }

        final PedidoSeleccionProducto this$0;

        _cls5()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((TextView)findViewById(0x7f060125)).setText(Double.toString(crsProductos.getProductoPrecio3()));
            tipoPrecioIndicado = "Precio 3";
        }

        final PedidoSeleccionProducto this$0;

        _cls6()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls7
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((TextView)findViewById(0x7f060125)).setText(Double.toString(crsProductos.getProductoPrecio4()));
            tipoPrecioIndicado = "Precio 4";
        }

        final PedidoSeleccionProducto this$0;

        _cls7()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls8
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((TextView)findViewById(0x7f060125)).setText(Double.toString(crsProductos.getProductoPrecio5()));
            tipoPrecioIndicado = "Precio 5";
        }

        final PedidoSeleccionProducto this$0;

        _cls8()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls9
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            double d = Double.parseDouble(((EditText)findViewById(0x7f060125)).getText().toString());
            if(d <= 0.0D)
            {
                MainActivity.crearMensajeToast(PedidoSeleccionProducto.this, "No esta permitido incluir productos con monto cero.", true);
            } else
            {
                EditText edittext = (EditText)findViewById(0x7f060124);
                if(edittext.getText() == null)
                    MainActivity.crearMensajeToast(PedidoSeleccionProducto.this, "Valor errado en campo Cantidad", true);
                else
                if(edittext.getText().toString().length() == 0)
                {
                    MainActivity.crearMensajeToast(PedidoSeleccionProducto.this, "Valor errado en campo Cantidad", true);
                } else
                {
                    int i = Integer.parseInt(edittext.getText().toString());
                    if(i <= 0)
                        MainActivity.crearMensajeToast(PedidoSeleccionProducto.this, "Valor errado en campo Cantidad", true);
                    else
                    if(i > crsProductos.getProductoStock())
                    {
                        MainActivity.crearMensajeToast(PedidoSeleccionProducto.this, "Valor errado en campo Cantidad.\n\rStock actual no cubre las unidades indicadas.", true);
                    } else
                    {
                        int j = crsProductos.getProductoId();
                        double d1 = crsProductos.getProductoIva();
                        double d2 = crsProductos.getProductoPrecioBase();
                        String s = tipoPrecioIndicado;
                        int k = idAutorizacionPrecio;
                        int l;
                        if(crsCarrito.getCount() == 0)
                            l = 0;
                        else
                            l = crsCarrito.getItemId();
                        DBHandle.carritoAgregarProducto(j, d, i, d1, d2, s, k, l);
                        setResult(-1);
                        finish();
                    }
                }
            }
        }

        final PedidoSeleccionProducto this$0;

        _cls9()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }


    private class _cls10
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            imageView.setImageDrawable(Drawable.createFromPath(view.getTag().toString()));
        }

        final PedidoSeleccionProducto this$0;

        _cls10()
        {
        	super();
        	this$0 = PedidoSeleccionProducto.this;
        }
    }

}
