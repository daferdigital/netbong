// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterLineasProducto;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class SeleccionarLineaProducto extends Activity
{

    public SeleccionarLineaProducto()
    {
        textWatcher = new _cls1();
        limpiar = new _cls2();
        filtrar = new _cls3();
        aceptar = new _cls4();
        todas = new _cls5();
        cancelar = new _cls6();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.seleccion_linea_producto_activity_layout);
        findViewById(0x7f060018).setOnClickListener(cancelar);
        findViewById(0x7f06017e).setOnClickListener(aceptar);
        findViewById(0x7f060017).setOnClickListener(limpiar);
        findViewById(0x7f06017d).setOnClickListener(todas);
        campoFiltro = (EditText)findViewById(0x7f060015);
        campoFiltro.addTextChangedListener(textWatcher);
        lv = (ListView)findViewById(0x7f06017c);
        padapt = new AdapterLineasProducto(this, MainActivity.mDbHelper.getListadoLineasProducto());
        lv.setAdapter(padapt);
        lv.setCacheColorHint(0);
    }

    private android.view.View.OnClickListener aceptar;
    private EditText campoFiltro;
    private android.view.View.OnClickListener cancelar;
    private android.view.View.OnClickListener filtrar;
    private android.view.View.OnClickListener limpiar;
    private ListView lv;
    private AdapterLineasProducto padapt;
    private TextWatcher textWatcher;
    private android.view.View.OnClickListener todas;

    private class _cls1
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            Editable editable1 = campoFiltro.getText();
            if(editable1 != null) {
            	String s = editable1.toString();
                if(s != null)
                {
                    String s1 = s.trim();
                    if(s1 != null)
                    {
                        padapt = new AdapterLineasProducto(SeleccionarLineaProducto.this, MainActivity.mDbHelper.getListadoLineasProducto(s1));
                        lv.setAdapter(padapt);
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

        final SeleccionarLineaProducto this$0;

        _cls1()
        {
        	super();
        	this$0 = SeleccionarLineaProducto.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            padapt = new AdapterLineasProducto(SeleccionarLineaProducto.this, MainActivity.mDbHelper.getListadoLineasProducto(""));
            lv.setAdapter(padapt);
            campoFiltro.setText("");
        }

        final SeleccionarLineaProducto this$0;

        _cls2()
        {
        	super();
        	this$0 = SeleccionarLineaProducto.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            String s = ((TextView)findViewById(0x7f060015)).getText().toString();
            if(s.length() == 0)
                s = "";
            padapt = new AdapterLineasProducto(SeleccionarLineaProducto.this, MainActivity.mDbHelper.getListadoLineasProducto(s));
            lv.setAdapter(padapt);
        }

        final SeleccionarLineaProducto this$0;

        _cls3()
        {
        	super();
        	this$0 = SeleccionarLineaProducto.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            String s = padapt.getSeleccion();
            if(s != null && s.length() > 0)
            {
                Intent intent = new Intent();
                intent.putExtra("LINEAS_SELECCIONADAS", padapt.getSeleccion());
                setResult(-1, intent);
            }
            finish();
        }

        final SeleccionarLineaProducto this$0;

        _cls4()
        {
        	super();
        	this$0 = SeleccionarLineaProducto.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent();
            intent.putExtra("LINEAS_SELECCIONADAS", "TODAS");
            setResult(-1, intent);
            finish();
        }

        final SeleccionarLineaProducto this$0;

        _cls5()
        {
        	super();
        	this$0 = SeleccionarLineaProducto.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final SeleccionarLineaProducto this$0;

        _cls6()
        {
        	super();
        	this$0 = SeleccionarLineaProducto.this;
        }
    }

}
