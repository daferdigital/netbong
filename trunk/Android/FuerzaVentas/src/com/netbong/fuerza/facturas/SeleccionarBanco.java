// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.facturas.adapters.BancosGenerales;
import com.netbong.fuerza.facturas.adapters.BancosPrincipales;
import com.netbong.fuerza.facturas.db.cursors.CsrBancos;

public class SeleccionarBanco extends Activity
{

    public SeleccionarBanco()
    {
        textWatcher = new _cls1();
        oclFiltrarPorCriterio = new _cls2();
        oclSeleccionarBanco = new _cls3();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.reg_pago_selecc_banco_actvity_layout);
        ListView listview = (ListView)findViewById(0x7f060153);
        listview.setAdapter(new BancosGenerales(this, CsrBancos.getBancos(), oclSeleccionarBanco));
        listview.setCacheColorHint(0);
        ListView listview1 = (ListView)findViewById(0x7f060164);
        listview1.setAdapter(new BancosPrincipales(this, CsrBancos.getBancosPrincipales(), oclSeleccionarBanco));
        listview1.setCacheColorHint(0);
        campoFiltro = (TextView)findViewById(0x7f060015);
        campoFiltro.addTextChangedListener(textWatcher);
        oclFiltrarPorCriterio.onClick(null);
    }

    private TextView campoFiltro;
    android.view.View.OnClickListener oclFiltrarPorCriterio;
    android.view.View.OnClickListener oclSeleccionarBanco;
    private TextWatcher textWatcher;


    private class _cls1
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            oclFiltrarPorCriterio.onClick(null);
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        final SeleccionarBanco this$0;

        _cls1()
        {
        	super();
        	this$0 = SeleccionarBanco.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            String s = campoFiltro.getText().toString().trim();
            if(s.length() == 0)
                s = "";
            ListView listview = (ListView)findViewById(0x7f060153);
            listview.setAdapter(new BancosGenerales(SeleccionarBanco.this, CsrBancos.getBancos(s), oclSeleccionarBanco));
            listview.setCacheColorHint(0);
        }

        final SeleccionarBanco this$0;

        _cls2()
        {
        	super();
        	this$0 = SeleccionarBanco.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            TextView textview = (TextView)view;
            int i = Integer.parseInt(textview.getTag().toString());
            String s = textview.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("ID", i);
            intent.putExtra("DESCRIPCION", s);
            setResult(-1, intent);
            finish();
        }

        final SeleccionarBanco this$0;

        _cls3()
        {
        	super();
        	this$0 = SeleccionarBanco.this;
        }
    }

}
