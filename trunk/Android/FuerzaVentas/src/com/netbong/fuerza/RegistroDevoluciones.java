// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.db.cursores.CursorProductosEnFactura;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity

public class RegistroDevoluciones extends Activity
    implements android.view.View.OnClickListener
{

    public RegistroDevoluciones()
    {
    }

    private void listarProductos()
    {
        CursorProductosEnFactura cursorproductosenfactura = MainActivity.mDbHelper.getListadoProductosEnFactura(idFactura);
        if(cursorproductosenfactura.getCount() <= 0){
        	return;
        } else {
        	contenedor = (LinearLayout)findViewById(0x7f060095);
        	LinearLayout linearlayout = (LinearLayout)View.inflate(this, 0x7f030035, null);
        	((TextView)linearlayout.findViewById(0x7f060092)).setText(cursorproductosenfactura.getNombre());
        	((TextView)linearlayout.findViewById(0x7f06007a)).setText(Integer.toString(cursorproductosenfactura.getCantidad()));
        	((TextView)linearlayout.findViewById(0x7f06004e)).setText(Double.toString(cursorproductosenfactura.getPrecio()));
        	((EditText)linearlayout.findViewById(0x7f060093)).setText(Integer.toString(cursorproductosenfactura.getCantidad()));
        	((ImageView)linearlayout.findViewById(0x7f060091)).setImageDrawable(Drawable.createFromPath(cursorproductosenfactura.getImagen()));
        	linearlayout.findViewById(0x7f060090).setOnClickListener(this);
        	contenedor.addView(linearlayout);
        }
        
        while(cursorproductosenfactura.moveToNext()){
        	LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f030035, null);
            ((TextView)linearlayout1.findViewById(0x7f060092)).setText(cursorproductosenfactura.getNombre());
            ((TextView)linearlayout1.findViewById(0x7f06007a)).setText(Integer.toString(cursorproductosenfactura.getCantidad()));
            ((TextView)linearlayout1.findViewById(0x7f06004e)).setText(Double.toString(cursorproductosenfactura.getPrecio()));
            ((EditText)linearlayout1.findViewById(0x7f060093)).setText(Integer.toString(cursorproductosenfactura.getCantidad()));
            ((ImageView)linearlayout1.findViewById(0x7f060091)).setImageDrawable(Drawable.createFromPath(cursorproductosenfactura.getImagen()));
            linearlayout1.findViewById(0x7f060090).setOnClickListener(this);
            contenedor.addView(linearlayout1);
        }
    }

    public void onClick(View view)
    {
        LinearLayout linearlayout = (LinearLayout)view.getParent();
        CheckBox checkbox = (CheckBox)view;
        if(checkbox.isChecked())
            linearlayout.findViewById(0x7f060093).setEnabled(true);
        if(!checkbox.isChecked())
            linearlayout.findViewById(0x7f060093).setEnabled(false);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.opciones_menu_principal);
        setContentView(R.layout.devoluciones_layout);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
            idFactura = bundle1.getInt("FACTURA");
        listarProductos();
    }

    private LinearLayout contenedor;
    private int idFactura;
}
