// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterClientes;
import com.netbong.fuerza.db.cursores.CursorClientes;

// Referenced classes of package com.ehp.droidsf.clientes:
//            FichaCliente

public class SeleccionarCliente extends Activity
{

    public SeleccionarCliente()
    {
        textWatcher = new _cls1();
        oclFiltrarPorCriterio = new _cls2();
        oclCrearNuevo = new _cls3();
        oclRegresar = new _cls4();
        oclSeleccionarCliente = new _cls5();
    }

    private Intent getIntentFichaCliente()
    {
        if(fichaCliente == null)
        {
            fichaCliente = new Intent(MainActivity.mainCtx, FichaCliente.class);
            fichaCliente.putExtra("FUENTE", 1);
        }
        return fichaCliente;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1 && i == 1)
        {
            setResult(j, intent);
            finish();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.seleccion_cliente_actvity_layout);
        lv = (ListView)findViewById(0x7f060050);
        cc = MainActivity.mDbHelper.getListadoClientes(com.netbong.fuerza.db.cursores.CursorClientes.SortBy.nombre);
        padaptc = new AdapterClientes(this, cc, oclSeleccionarCliente);
        lv.setAdapter(padaptc);
        findViewById(0x7f060177).setOnClickListener(oclCrearNuevo);
        findViewById(0x7f060018).setOnClickListener(oclRegresar);
        campoFiltro = (TextView)findViewById(0x7f060015);
        campoFiltro.addTextChangedListener(textWatcher);
        oclFiltrarPorCriterio.onClick(null);
    }

    private static final int INTENT_REQUEST_AGREGAR_NUEVO_CLIENTE = 1;
    private TextView campoFiltro;
    private CursorClientes cc;
    private Intent fichaCliente;
    private ListView lv;
    android.view.View.OnClickListener oclCrearNuevo;
    android.view.View.OnClickListener oclFiltrarPorCriterio;
    android.view.View.OnClickListener oclRegresar;
    android.view.View.OnClickListener oclSeleccionarCliente;
    private AdapterClientes padaptc;
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

        final SeleccionarCliente this$0;

        _cls1()
        {
        	super();
        	this$0 = SeleccionarCliente.this;
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
            cc = MainActivity.mDbHelper.getListadoClientes(s, com.netbong.fuerza.db.cursores.CursorClientes.SortBy.nombre);
            padaptc = new AdapterClientes(SeleccionarCliente.this, cc, oclSeleccionarCliente);
            lv.setAdapter(padaptc);
            lv.setCacheColorHint(0);
        }

        final SeleccionarCliente this$0;

        _cls2()
        {
        	super();
        	this$0 = SeleccionarCliente.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            startActivityForResult(getIntentFichaCliente(), 1);
        }

        final SeleccionarCliente this$0;

        _cls3()
        {
        	super();
        	this$0 = SeleccionarCliente.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(0);
            finish();
        }

        final SeleccionarCliente this$0;

        _cls4()
        {
        	super();
        	this$0 = SeleccionarCliente.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            int i = Integer.parseInt(view.getTag().toString());
            cc.moveToPosition(i);
            Intent intent = new Intent();
            intent.putExtra("ID", cc.getId());
            intent.putExtra("RIF", "");
            intent.putExtra("CLIENTE", cc.getNombre());
            intent.putExtra("LIMITE_CREDITO", cc.getLimiteCredito());
            setResult(-1, intent);
            finish();
        }

        final SeleccionarCliente this$0;

        _cls5()
        {
        	super();
        	this$0 = SeleccionarCliente.this;
        }
    }

}
