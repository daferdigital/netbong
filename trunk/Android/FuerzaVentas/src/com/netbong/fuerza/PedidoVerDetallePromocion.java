// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.db.cursores.CursorPromosDetalle;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity, PedidoAdaptarPromosDetalle

public class PedidoVerDetallePromocion extends Activity
{

    public PedidoVerDetallePromocion()
    {
        cancelar = new _cls1();
        cargarAlCarrito = new _cls2();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pedido_promociones_detalle);
        Bundle bundle1 = getIntent().getExtras();
        int i;
        if(bundle1 != null)
            i = bundle1.getInt("PROMO_ID");
        else
            i = 0;
        cpd = MainActivity.mDbHelper.getListadoDetallePromocion(i);
        ((ListView)findViewById(0x7f06011a)).setAdapter(new PedidoAdaptarPromosDetalle(this, cpd));
        ((TextView)findViewById(0x7f060119)).setText(cpd.getPromocion());
        ((ImageView)findViewById(0x7f060118)).setImageDrawable(Drawable.createFromPath(cpd.getPromocionImagen()));
        findViewById(0x7f060012).setOnClickListener(cargarAlCarrito);
        findViewById(0x7f060011).setOnClickListener(cancelar);
    }

    private android.view.View.OnClickListener cancelar;
    private android.view.View.OnClickListener cargarAlCarrito;
    private CursorPromosDetalle cpd;


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final PedidoVerDetallePromocion this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidoVerDetallePromocion.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(cpd.moveToFirst()) {
            	MainActivity.mDbHelper.agregarProductoCarrito(cpd.getProductoID(), 
            			cpd.getProductoPrecio(), cpd.getProductoCantidad(), cpd.getProductoPrecioIva(), 1, 0);
            }
            
            while(cpd.moveToNext()){
            	MainActivity.mDbHelper.agregarProductoCarrito(cpd.getProductoID(), cpd.getProductoPrecio(), cpd.getProductoCantidad(), cpd.getProductoPrecioIva(), 1, 0);
            }
            
            setResult(-1, null);
            finish();
        }

        final PedidoVerDetallePromocion this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidoVerDetallePromocion.this;
        }
    }

}
