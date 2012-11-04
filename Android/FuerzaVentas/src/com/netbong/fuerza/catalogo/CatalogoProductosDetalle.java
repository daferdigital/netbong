// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.catalogo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.PedidoAdaptarImagenesProducto;
import com.netbong.fuerza.db.cursores.CursorCatalogoImagenes;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class CatalogoProductosDetalle extends Activity
{

    public CatalogoProductosDetalle()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.catalogo_productos_detalle_activity_layout);
        getWindow().setLayout(650, 650);
        imageView = (ImageView)findViewById(0x7f06001f);
        int i = 0;
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
            i = bundle1.getInt("ID");
        CursorCatalogoImagenes cursorcatalogoimagenes = MainActivity.mDbHelper.getImagenesDeProductos(i);
        Gallery gallery = (Gallery)findViewById(0x7f060020);
        gallery.setAdapter(new PedidoAdaptarImagenesProducto(this, cursorcatalogoimagenes));
        gallery.setOnItemClickListener(new OnItemClickDetalle());
        if(cursorcatalogoimagenes.getCount() > 0)
            imageView.setImageDrawable(Drawable.createFromPath(cursorcatalogoimagenes.getImagen()));
    }

    private ImageView imageView;


    private class OnItemClickDetalle
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            imageView.setImageDrawable(Drawable.createFromPath(view.getTag().toString()));
        }

        final CatalogoProductosDetalle this$0;

        OnItemClickDetalle()
        {
        	super();
        	this$0 = CatalogoProductosDetalle.this;
        }
    }

}
