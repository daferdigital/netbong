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
import android.widget.TextView;

public class ModificarEliminarArticulo extends Activity
{

    public ModificarEliminarArticulo()
    {
        ocl_eliminar = new _cls1();
        ocl_modificar = new _cls2();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ver_detalle_carrito_activity_layout);
        setContentView(R.layout.modificar_eliminar_articulo_activity_layout);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            idItemCarrito = bundle1.getInt("ID");
            idProductoItem = bundle1.getInt("ID_PRODUCTO");
            cantdadItemCarrito = bundle1.getInt("CANTIDAD");
            String s = bundle1.getString("CODIGO_PRODUCTO");
            String s1 = bundle1.getString("NOMBRE_PRODUCTO");
            String s2 = bundle1.getString("IMG_PRODUCTO");
            ((TextView)findViewById(0x7f0600db)).setText(s);
            ((TextView)findViewById(0x7f060084)).setText(s1);
            ((ImageView)findViewById(0x7f060098)).setImageDrawable(Drawable.createFromPath(s2));
        }
        findViewById(0x7f0600dc).setOnClickListener(ocl_eliminar);
        findViewById(0x7f0600dd).setOnClickListener(ocl_modificar);
    }

    private static final int ELIMINAR = 1;
    private static final int MODIFICAR = 2;
    private int cantdadItemCarrito;
    private int idItemCarrito;
    private int idProductoItem;
    private android.view.View.OnClickListener ocl_eliminar;
    private android.view.View.OnClickListener ocl_modificar;




    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent();
            intent.putExtra("ITEM_CARRITO", idItemCarrito);
            intent.putExtra("ELIMINAR_MODIFICAR", 1);
            setResult(-1, intent);
            finish();
        }

        final ModificarEliminarArticulo this$0;

        _cls1()
        {
        	super();
        	this$0 = ModificarEliminarArticulo.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent();
            intent.putExtra("ITEM_CARRITO", idItemCarrito);
            intent.putExtra("ID_PRODUCTO", idProductoItem);
            intent.putExtra("CANTIDAD_ITEM_CARRITO", cantdadItemCarrito);
            intent.putExtra("ELIMINAR_MODIFICAR", 2);
            setResult(-1, intent);
            finish();
        }

        final ModificarEliminarArticulo this$0;

        _cls2()
        {
        	super();
        	this$0 = ModificarEliminarArticulo.this;
        }
    }

}
