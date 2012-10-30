// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterDevoluciones;
import com.netbong.fuerza.db.cursores.CursorDevolucionesArticulosNoDevueltos;

public class RegistrarDevoluciones extends Activity
{

    public RegistrarDevoluciones()
    {
        oclSeleccionarArticulo = new _cls1();
        oclRegistrarDevoluciones = new _cls2();
        oclCancelar = new _cls3();
    }

    private View getViewArticulo(int i, String s, String s1, int j)
    {
        LinearLayout linearlayout = (LinearLayout)View.inflate(this, 0x7f03002a, null);
        linearlayout.setTag(Integer.valueOf(i));
        ((ImageView)linearlayout.findViewById(0x7f06004c)).setImageDrawable(Drawable.createFromPath(s));
        ((TextView)linearlayout.findViewById(0x7f060078)).setText(s1);
        TextView textview = (TextView)linearlayout.findViewById(0x7f06007a);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(j);
        textview.setText(String.format("Unids/Cants: %d", aobj));
        linearlayout.findViewById(0x7f060079).setOnClickListener(oclSeleccionarArticulo);
        return linearlayout;
    }

    private void mostrarArticulos()
    {
        CursorDevolucionesArticulosNoDevueltos cursordevolucionesarticulosnodevueltos = MainActivity.mDbHelper.getArticulosNoDevuelto(idFactura);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f060027);
        do
        {
            if(!cursordevolucionesarticulosnodevueltos.moveToNext())
                return;
            linearlayout.addView(getViewArticulo(cursordevolucionesarticulosnodevueltos.getProductoID(), cursordevolucionesarticulosnodevueltos.getProductoImagen(), cursordevolucionesarticulosnodevueltos.getProductoCodigo(), cursordevolucionesarticulosnodevueltos.getUnidades()));
        } while(true);
    }

    private void registrarDevolucionesArticulosSeleccionados()
    {
        ViewGroup viewgroup = (ViewGroup)findViewById(0x7f060027);
        int i = viewgroup.getChildCount();
        int j = 0;
        do
        {
            if(j >= i)
                return;
            LinearLayout linearlayout = (LinearLayout)viewgroup.getChildAt(j);
            if(((CheckBox)linearlayout.findViewById(0x7f060079)).isChecked())
            {
                int k = Integer.parseInt(linearlayout.getTag().toString());
                String s = ((TextView)linearlayout.findViewById(0x7f060078)).getText().toString();
                int l = Integer.parseInt(((TextView)linearlayout.findViewById(0x7f06007b)).getText().toString());
                String s1 = ((TextView)linearlayout.findViewById(0x7f06007c)).getText().toString();
                MainActivity.mDbHelper.registrarDevolucion(idFactura, k, l, s1, s);
            }
            j++;
        } while(true);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.registro_devoluciones_activity_layout);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            idFactura = bundle1.getInt("FACTURA");
            documentoFactura = bundle1.getString("DOCUMENTO-FACTURA");
        }
        TextView textview = (TextView)findViewById(0x7f060000);
        Object aobj[] = new Object[1];
        aobj[0] = documentoFactura;
        textview.setText(String.format("Registro de Devoluciones - %s", aobj));
        ((ListView)findViewById(0x7f060172)).setAdapter(new AdapterDevoluciones(this, MainActivity.mDbHelper.getDevolucionesRegistradasPorFactura(idFactura)));
        mostrarArticulos();
        findViewById(0x7f060012).setOnClickListener(oclRegistrarDevoluciones);
        findViewById(0x7f060011).setOnClickListener(oclCancelar);
    }

    String documentoFactura;
    int idFactura;
    android.view.View.OnClickListener oclCancelar;
    android.view.View.OnClickListener oclRegistrarDevoluciones;
    android.view.View.OnClickListener oclSeleccionarArticulo;


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            LinearLayout linearlayout = (LinearLayout)view.getParent();
            CheckBox checkbox = (CheckBox)view;
            linearlayout.findViewById(0x7f06007b).setEnabled(checkbox.isChecked());
            linearlayout.findViewById(0x7f06007c).setEnabled(checkbox.isChecked());
        }

        final RegistrarDevoluciones this$0;

        _cls1()
        {
        	super();
        	this$0 = RegistrarDevoluciones.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            registrarDevolucionesArticulosSeleccionados();
            setResult(-1);
            finish();
        }

        final RegistrarDevoluciones this$0;

        _cls2()
        {
        	super();
        	this$0 = RegistrarDevoluciones.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(0);
            finish();
        }

        final RegistrarDevoluciones this$0;

        _cls3()
        {
        	super();
        	this$0 = RegistrarDevoluciones.this;
        }
    }

}
