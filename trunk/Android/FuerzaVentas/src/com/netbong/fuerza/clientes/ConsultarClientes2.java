// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterClientes;
import com.netbong.fuerza.db.cursores.CursorClientes;

// Referenced classes of package com.ehp.droidsf.clientes:
//            FichaCliente

public class ConsultarClientes2 extends Activity
{

    public ConsultarClientes2()
    {
        oclFiltrarPorCriterio = new _cls1();
        oclVerFichaClientes = new _cls2();
    }

    private Intent getIntentFichaCliente()
    {
        if(fichaCliente == null)
            fichaCliente = new Intent(MainActivity.mainCtx, FichaCliente.class);
        return fichaCliente;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.consultar_clientes_activity_layout);
        lv = (ListView)findViewById(0x7f060050);
        campoFiltro = (TextView)findViewById(0x7f060015);
        findViewById(0x7f060014).setOnClickListener(oclFiltrarPorCriterio);
        oclFiltrarPorCriterio.onClick(null);
    }

    private static final int INTENT_REQUEST_AGREGAR_NUEVO_CLIENTE = 1;
    private TextView campoFiltro;
    private CursorClientes cc;
    private Intent fichaCliente;
    private ListView lv;
    android.view.View.OnClickListener oclFiltrarPorCriterio;
    android.view.View.OnClickListener oclVerFichaClientes;
    private AdapterClientes padaptc;








    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            String s = campoFiltro.getText().toString();
            if(s.length() == 0)
                s = "";
            cc = MainActivity.mDbHelper.getListadoClientes(s, com.netbong.fuerza.db.cursores.CursorClientes.SortBy.nombre);
            padaptc = new AdapterClientes(ConsultarClientes2.this, cc, oclVerFichaClientes);
            lv.setAdapter(padaptc);
        }

        final ConsultarClientes2 this$0;

        _cls1()
        {
        	super();
        	this$0 = ConsultarClientes2.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            int i = Integer.parseInt(view.getTag().toString());
            cc.moveToPosition(i);
            Intent intent = getIntentFichaCliente();
            intent.putExtra("ID_CLIENTE", cc.getId());
            boolean flag;
            if(cc.getCodigo().equalsIgnoreCase("NONE"))
                flag = false;
            else
                flag = true;
            intent.putExtra("PROFIT", flag);
            startActivity(intent);
        }

        final ConsultarClientes2 this$0;

        _cls2()
        {
        	super();
        	this$0 = ConsultarClientes2.this;
        }
    }

}
