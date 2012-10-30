// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import com.netbong.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class PedidoSeleccionarMontoOferta extends Activity
{

    public PedidoSeleccionarMontoOferta()
    {
        aceptar = new _cls1();
        cancelar = new _cls2();
        oclOfertaMonto = new _cls3();
        oclOfertaPorcent = new _cls4();
    }

    protected void onCreate(Bundle bundle)
    {
        int i = 1;
        boolean flag = true;
        super.onCreate(bundle);
        requestWindowFeature(i);
        setContentView(R.layout.pedido_seleccionar_monto_oferta_layout);
        findViewById(0x7f060011).setOnClickListener(cancelar);
        findViewById(0x7f060012).setOnClickListener(aceptar);
        Bundle bundle1 = getIntent().getExtras();
        
        if(bundle1 != null){
            precio = bundle1.getDouble("PRECIO_PRODUCTO");
            ofertaMonto = bundle1.getDouble("OFERTA_MONTO");
            ofertaPorcentaje = bundle1.getDouble("OFERTA_PORCENTAJE");
            CheckBox checkbox = (CheckBox)findViewById(0x7f06012f);
            Object aobj[] = new Object[i];
            aobj[0] = Double.valueOf(ofertaMonto);
            checkbox.setText(String.format("Oferta Monto (Bs. %.2f)", aobj));
            View view = findViewById(0x7f06012f);
            boolean flag1;
            View view1;
            boolean flag2;
            View view2;
            if(ofertaMonto > 0.0D)
                flag1 = true;
            else
                flag1 = false;
            view.setEnabled(flag1);
            ((EditText)findViewById(0x7f060131)).setText(Double.toString(ofertaPorcentaje));
            view1 = findViewById(0x7f060130);
            if(ofertaPorcentaje > 0.0D)
                flag2 = true;
            else
                flag2 = false;
            view1.setEnabled(flag2);
            view2 = findViewById(0x7f060131);
            if(ofertaPorcentaje <= 0.0D)
                flag = false;
            view2.setEnabled(flag);
            findViewById(0x7f06012f).setOnClickListener(oclOfertaMonto);
            findViewById(0x7f060130).setOnClickListener(oclOfertaPorcent);
        }
    }

    private android.view.View.OnClickListener aceptar;
    private android.view.View.OnClickListener cancelar;
    private double nuevoPrecio;
    private android.view.View.OnClickListener oclOfertaMonto;
    private android.view.View.OnClickListener oclOfertaPorcent;
    private double ofertaMonto;
    private double ofertaPorcentaje;
    private double precio;






    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent();
            intent.putExtra("AJUSTE_PRECIO", nuevoPrecio);
            setResult(-1, intent);
            finish();
        }

        final PedidoSeleccionarMontoOferta this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidoSeleccionarMontoOferta.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final PedidoSeleccionarMontoOferta this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidoSeleccionarMontoOferta.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(((CheckBox)view).isChecked())
            {
                ((CheckBox)findViewById(0x7f060130)).setChecked(false);
                nuevoPrecio = ofertaMonto;
            } else
            {
                nuevoPrecio = precio;
            }
        }

        final PedidoSeleccionarMontoOferta this$0;

        _cls3()
        {
        	super();
        	this$0 = PedidoSeleccionarMontoOferta.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(((CheckBox)view).isChecked())
            {
                ((CheckBox)findViewById(0x7f06012f)).setChecked(false);
                nuevoPrecio = (precio * ofertaPorcentaje) / 100D;
            } else
            {
                nuevoPrecio = precio;
            }
        }

        final PedidoSeleccionarMontoOferta this$0;

        _cls4()
        {
        	super();
        	this$0 = PedidoSeleccionarMontoOferta.this;
        }
    }

}
