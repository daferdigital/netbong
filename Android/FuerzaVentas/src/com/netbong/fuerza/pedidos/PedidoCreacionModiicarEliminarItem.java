// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import com.netbong.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class PedidoCreacionModiicarEliminarItem extends Activity
{

    public PedidoCreacionModiicarEliminarItem()
    {
        oclEliminar = new _cls1();
        oclModificar = new _cls2();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pedido_creacion_modificar_eliminar);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            String s = bundle1.getString("IMG");
            item = bundle1.getInt("ITEM");
            ((ImageView)findViewById(0x7f060081)).setImageDrawable(Drawable.createFromPath(s));
        }
        findViewById(0x7f0600dc).setOnClickListener(oclEliminar);
        findViewById(0x7f0600dd).setOnClickListener(oclModificar);
    }

    private int item;
    private android.view.View.OnClickListener oclEliminar;
    private android.view.View.OnClickListener oclModificar;


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent();
            intent.putExtra("ITEM", item);
            intent.putExtra("TIPO", 1);
            setResult(-1, intent);
            finish();
        }

        final PedidoCreacionModiicarEliminarItem this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidoCreacionModiicarEliminarItem.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent();
            intent.putExtra("ITEM", item);
            intent.putExtra("TIPO", 2);
            setResult(-1, intent);
            finish();
        }

        final PedidoCreacionModiicarEliminarItem this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidoCreacionModiicarEliminarItem.this;
        }
    }

}
