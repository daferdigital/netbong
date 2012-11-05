// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterArticulosCarritoVistaAmpliada;
import com.netbong.fuerza.db.cursores.*;
import com.netbong.fuerza.util.DeviceListActivity;
import com.netbong.fuerza.util.ZebraPrinterTest;

// Referenced classes of package com.ehp.droidsf.pedidos:
//            ModificarEliminarArticulo

public class DetalleCarrito extends Activity
{

    public DetalleCarrito()
    {
        imprimirPedido = new _cls1();
        modificarCarrito = new _cls2();
    }

    private void cabeceraDatosCliente(StringBuilder stringbuilder)
    {
        stringbuilder.append("! 0 200 200 210 1").append("\r\n");
        stringbuilder.append("CENTER").append("\r\n");
        stringbuilder.append("TEXT 7 0 0 48 Cliente: ").append(clienteNombre).append("\r\n");
        stringbuilder.append("TEXT 7 0 0 72 RIF: ").append(clienteRif).append("\r\n");
        stringbuilder.append("TEXT 7 0 0 96 Pedido Num: ").append(numeroPedido).append("\r\n");
        stringbuilder.append("TEXT 7 0 0 118 ").append(numeroPedido).append("\r\n");
        stringbuilder.append("PRINT").append("\r\n");
    }

    private void cabeceraDatosCliente_lineal(StringBuilder stringbuilder)
    {
        stringbuilder.append("! U1 SETBOLD 2").append("\r\n");
        stringbuilder.append("Cliente: ").append(clienteNombre).append("\r\n");
        stringbuilder.append("RIF: ").append(clienteRif).append("\r\n");
        stringbuilder.append("Pedido Num: ").append(numeroPedido).append("\r\n");
        stringbuilder.append("! U1 SETBOLD 0").append("\r\n").append("\r\n");
    }

    private void cabeceraDatosEmpresa(StringBuilder stringbuilder)
    {
        linea = 0;
        stringbuilder.append("! 0 200 200 210 1").append("\r\n");
        stringbuilder.append("CENTER").append("\r\n");
        stringbuilder.append("TEXT 7 0 0 24 CR Computacion").append("\r\n");
        stringbuilder.append("TEXT 7 0 0 48 RIF: J00000000").append("\r\n");
        stringbuilder.append("TEXT 7 0 0 72 Urb. Colinas de Bello Monte, Caracas").append("\r\n");
        stringbuilder.append("TEXT 7 0 0 96 Zona Postal 1050. Venezuela").append("\r\n");
        stringbuilder.append("PRINT").append("\r\n");
    }

    private void cabeceraDatosEmpresa_lineal(StringBuilder stringbuilder)
    {
        linea = 0;
        stringbuilder.append("! U1 SETLP 7 0 24").append("\r\n");
        stringbuilder.append("CR Computacion").append("\r\n");
        stringbuilder.append("RIF: J00000000").append("\r\n");
        stringbuilder.append("Urb. Colinas de Bello Monte, Caracas").append("\r\n");
        stringbuilder.append("Zona Postal 1050. Venezuela").append("\r\n").append("\r\n");
    }

    private void cargarInformacionCarrito()
    {
        ListView listview = (ListView)findViewById(0x7f0600f2);
        listview.setAdapter(new AdapterArticulosCarritoVistaAmpliada(this, articulosCarrito));
        listview.setOnItemClickListener(modificarCarrito);
        CursorCarritoPedidoTotales cursorcarritopedidototales = MainActivity.mDbHelper.getTotalesCarritoPedido();
        if(cursorcarritopedidototales.getCount() > 0)
        {
            double d = cursorcarritopedidototales.getTotalPrecioCantidad() + cursorcarritopedidototales.getTotalIVA();
            TextView textview = (TextView)findViewById(0x7f060191);
            Object aobj[] = new Object[1];
            aobj[0] = MainActivity.formatVE(cursorcarritopedidototales.getTotalPrecioCantidad());
            textview.setText(String.format("Total Bs.: %s", aobj));
            TextView textview1 = (TextView)findViewById(0x7f060061);
            Object aobj1[] = new Object[1];
            aobj1[0] = MainActivity.formatVE(cursorcarritopedidototales.getTotalIVA());
            textview1.setText(String.format("IVA Bs.: %s", aobj1));
            TextView textview2 = (TextView)findViewById(0x7f060192);
            Object aobj2[] = new Object[1];
            aobj2[0] = MainActivity.formatVE(d);
            textview2.setText(String.format("Total General Bs.: %s", aobj2));
            if(limiteCredito > 0.0D && limiteCredito < d)
                findViewById(0x7f060193).setVisibility(0);
        }
    }

    private void detalleInformacionPedido(StringBuilder stringbuilder)
    {
        double d = 0.0D;
        linea = 24 + linea;
        articulosCarrito.moveToFirst();
        do
        {
            if(articulosCarrito.isAfterLast())
            {
                StringBuilder stringbuilder7 = stringbuilder.append("LEFT").append("\n\r");
                Object aobj7[] = new Object[1];
                int k = 24 + linea;
                linea = k;
                aobj7[0] = Integer.valueOf(k);
                StringBuilder stringbuilder8 = stringbuilder7.append(String.format("TEXT 7 0 0 %d ", aobj7));
                Object aobj8[] = new Object[1];
                aobj8[0] = Double.valueOf(d);
                stringbuilder8.append(String.format("TATAL Bs. %.2f", aobj8)).append("\n\r");
                stringbuilder.append("PRINT").append("\n\r");
                return;
            }
            double d1 = articulosCarrito.getPrecio() * (double)articulosCarrito.getCantidad();
            double d2 = d1 + (d1 * articulosCarrito.getIva()) / 100D;
            StringBuilder stringbuilder1 = stringbuilder.append("LEFT").append("\n\r");
            Object aobj[] = new Object[1];
            int i = 24 + linea;
            linea = i;
            aobj[0] = Integer.valueOf(i);
            stringbuilder1.append(String.format("TEXT 7 0 0 %d ", aobj)).append(articulosCarrito.getProductoCodigo()).append("\n\r");
            Object aobj1[] = new Object[1];
            int j = 24 + linea;
            linea = j;
            aobj1[0] = Integer.valueOf(j);
            StringBuilder stringbuilder2 = stringbuilder.append(String.format("TEXT 7 0 0 %d ", aobj1));
            Object aobj2[] = new Object[1];
            aobj2[0] = Double.valueOf(articulosCarrito.getPrecio());
            StringBuilder stringbuilder3 = stringbuilder2.append(String.format("Bs. %.2f", aobj2));
            Object aobj3[] = new Object[1];
            aobj3[0] = Integer.valueOf(articulosCarrito.getCantidad());
            StringBuilder stringbuilder4 = stringbuilder3.append(String.format("/ Cant. %d", aobj3));
            String s;
            StringBuilder stringbuilder5;
            Object aobj5[];
            StringBuilder stringbuilder6;
            Object aobj6[];
            if(articulosCarrito.getIva() > 0.0D)
            {
                s = "/ (E)";
            } else
            {
                Object aobj4[] = new Object[1];
                aobj4[0] = Double.valueOf(articulosCarrito.getIva());
                s = String.format("/ (G %.2f)", aobj4);
            }
            stringbuilder4.append(s).append("\n\r");
            stringbuilder5 = stringbuilder.append("RIGHT").append("\n\r");
            aobj5 = new Object[1];
            aobj5[0] = Integer.valueOf(linea);
            stringbuilder6 = stringbuilder5.append(String.format("TEXT 7 0 0 %d ", aobj5));
            aobj6 = new Object[1];
            aobj6[0] = Double.valueOf(d2);
            stringbuilder6.append(String.format("Bs. %.2f", aobj6)).append("\n\r");
            linea = 24 + linea;
            d += d2;
            articulosCarrito.moveToNext();
        } while(true);
    }

    private void detalleInformacionPedido_lineal(StringBuilder stringbuilder)
    {
        double d = 0.0D;
        stringbuilder.append("! U1 SETLP 7 0 24").append("\r\n");
        articulosCarrito.moveToFirst();
        do
        {
            if(articulosCarrito.isAfterLast())
            {
                stringbuilder.append("--------------------").append("\r\n");
                Object aobj4[] = new Object[1];
                aobj4[0] = Double.valueOf(d);
                stringbuilder.append(String.format("TATAL Bs. %.2f", aobj4)).append("\r\n");
                return;
            }
            double d1 = articulosCarrito.getPrecio() * (double)articulosCarrito.getCantidad();
            double d2 = d1 + (d1 * articulosCarrito.getIva()) / 100D;
            String s = articulosCarrito.getProductoCodigo();
            Object aobj[] = new Object[1];
            aobj[0] = Double.valueOf(d2);
            String s1 = String.format("Bs. %.2f", aobj);
            int i = s.length();
            int j = s1.length();
            stringbuilder.append(s);
            stringbuilder.append("                                        ".substring(i + j));
            stringbuilder.append(s1);
            stringbuilder.append("\r\n");
            Object aobj1[] = new Object[1];
            aobj1[0] = Double.valueOf(articulosCarrito.getPrecio());
            StringBuilder stringbuilder1 = stringbuilder.append(String.format("    Bs. %.2f", aobj1));
            Object aobj2[] = new Object[1];
            aobj2[0] = Integer.valueOf(articulosCarrito.getCantidad());
            StringBuilder stringbuilder2 = stringbuilder1.append(String.format("/ Cant. %d", aobj2));
            String s2;
            if(articulosCarrito.getIva() > 0.0D)
            {
                s2 = "/ (E)";
            } else
            {
                Object aobj3[] = new Object[1];
                aobj3[0] = Double.valueOf(articulosCarrito.getIva());
                s2 = String.format("/ (G %.2f)", aobj3);
            }
            stringbuilder2.append(s2).append("\n\r");
            d += d2;
            articulosCarrito.moveToNext();
        } while(true);
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1 && i == 1)
        {
            String s = intent.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
            StringBuilder stringbuilder = new StringBuilder();
            cabeceraDatosEmpresa(stringbuilder);
            cabeceraDatosCliente_lineal(stringbuilder);
            detalleInformacionPedido_lineal(stringbuilder);
            Log.i("IMPRESION", stringbuilder.toString());
            (new Thread(new ZebraPrinterTest(s, CursorPrintDatosPedido.getDatosPedido(MainActivity.mDbHelper.getWritableDatabase(), idPedido).getPrintVersion().getBytes()))).start();
        }
        if(j == -1 && i == 2)
        {
            Bundle bundle = intent.getExtras();
            int k = bundle.getInt("ELIMINAR_MODIFICAR");
            int l = bundle.getInt("ITEM_CARRITO");
            if(k == 1)
            {
                MainActivity.mDbHelper.eliminarArticuloCarrito(l);
                articulosCarrito.requery();
                cargarInformacionCarrito();
            }
            if(k == 2)
            {
                bundle.getInt("CANTIDAD_ITEM_CARRITO");
                int i1 = bundle.getInt("ID_PRODUCTO");
                Intent intent1 = new Intent();
                intent1.putExtra("ID-PRODUCTO", i1);
                setResult(-1, intent1);
                finish();
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ver_detalle_carrito_activity_layout);
        articulosCarrito = MainActivity.mDbHelper.getListadoArticulosCarritoPedido();
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            limiteCredito = bundle1.getDouble("LIMITE_CREDITO");
            if(bundle1.getInt("PERMITIR_IMPRESION") == 1)
            {
                View view = findViewById(0x7f06018f);
                view.setVisibility(0);
                view.setOnClickListener(imprimirPedido);
                clienteNombre = bundle1.getString("CLIENTE_NOMBRE");
                clienteRif = bundle1.getString("CLIENTE_RIF");
                numeroPedido = bundle1.getString("NUMERO_PEDIDO");
                idPedido = bundle1.getInt("ID_PEDIDO");
            }
        }
        cargarInformacionCarrito();
        modificarListaArticulos = new Intent(this, ModificarEliminarArticulo.class);
    }

    private static final int INTENT_REQUEST_CONNECT_TO_DEVICE = 1;
    private static final int INTENT_REQUEST_MODIFICAR_CARRITO = 2;
    private CursorCarritoPedido articulosCarrito;
    private String clienteNombre;
    private String clienteRif;
    private int idPedido;
    private android.view.View.OnClickListener imprimirPedido;
    double limiteCredito;
    private int linea;
    private android.widget.AdapterView.OnItemClickListener modificarCarrito;
    private Intent modificarListaArticulos;
    private String numeroPedido;



    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(DetalleCarrito.this, DeviceListActivity.class);
            startActivityForResult(intent, 1);
        }

        final DetalleCarrito this$0;

        _cls1()
        {
        	super();
        	this$0 = DetalleCarrito.this;
        }
    }


    private class _cls2
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            int j = Integer.parseInt(view.getTag().toString());
            articulosCarrito.moveToPosition(j);
            modificarListaArticulos.putExtra("ID", articulosCarrito.getId());
            modificarListaArticulos.putExtra("ID_PRODUCTO", articulosCarrito.getProducto());
            modificarListaArticulos.putExtra("CANTIDAD", articulosCarrito.getCantidad());
            modificarListaArticulos.putExtra("CODIGO_PRODUCTO", articulosCarrito.getProductoCodigo());
            modificarListaArticulos.putExtra("NOMBRE_PRODUCTO", articulosCarrito.getProductoNombre());
            modificarListaArticulos.putExtra("IMG_PRODUCTO", articulosCarrito.getProductoImagen());
            startActivityForResult(modificarListaArticulos, 2);
        }

        final DetalleCarrito this$0;

        _cls2()
        {
        	super();
        	this$0 = DetalleCarrito.this;
        }
    }

}
