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
import android.widget.EditText;
import android.widget.ListView;

import com.netbong.R;
import com.netbong.fuerza.facturas.adapters.AdpPagos;
import com.netbong.fuerza.facturas.db.DBHandle;
import com.netbong.fuerza.facturas.db.cursors.CsrPagosRegistrados;

public class PagosRegistrados extends Activity
{

    public PagosRegistrados()
    {
        textWatcher = new _cls1();
        toolBarOpcCancelar = new _cls2();
        toolBarOpcRegistrarPago = new _cls3();
        toolBarOpcNuevaConsulta = new _cls4();
        oclSeleccionarPago = new _cls5();
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1 && i == 3)
        {
            crp.requery();
            lv_cancelaciones.setAdapter(new AdpPagos(this, crp, oclSeleccionarPago));
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.pagos_registrados_activity_layout);
        campoFiltroCliente = (EditText)findViewById(0x7f060015);
        campoFiltroCliente.addTextChangedListener(textWatcher);
        lv_cancelaciones = (ListView)findViewById(0x7f060016);
        lv_cancelaciones.setDivider(null);
        lv_cancelaciones.setCacheColorHint(0);
        findViewById(0x7f060018).setOnClickListener(toolBarOpcCancelar);
        findViewById(0x7f060017).setOnClickListener(toolBarOpcNuevaConsulta);
        findViewById(0x7f0600a1).setOnClickListener(toolBarOpcRegistrarPago);
        toolBarOpcNuevaConsulta.onClick(null);
    }

    private static final int INTENT_REQUEST_CANCELACION_ANULAR = 1;
    private static final int INTENT_REQUEST_CANCELACION_RECUPERAR = 2;
    private static final int INTENT_REQUEST_PAGO_REGISTRAR_NUEVO = 3;
    EditText campoFiltroCliente;
    CsrPagosRegistrados crp;
    ListView lv_cancelaciones;
    private android.view.View.OnClickListener oclSeleccionarPago;
    Intent registroPago;
    private TextWatcher textWatcher;
    android.view.View.OnClickListener toolBarOpcCancelar;
    private android.view.View.OnClickListener toolBarOpcNuevaConsulta;
    private android.view.View.OnClickListener toolBarOpcRegistrarPago;


    private class _cls1
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            Editable editable1 = campoFiltroCliente.getText();
            if(editable1 != null) {
            	String s = editable1.toString();
                if(s != null)
                {
                    String s1 = s.trim();
                    if(s1 != null)
                    {
                        if(crp != null && !crp.isClosed())
                            crp.close();
                        crp = DBHandle.obtenerPagos(s1);
                        lv_cancelaciones.setAdapter(new AdpPagos(PagosRegistrados.this, crp, oclSeleccionarPago));
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

        final PagosRegistrados this$0;

        _cls1()
        {
        	super();
        	this$0 = PagosRegistrados.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(0);
            finish();
        }

        final PagosRegistrados this$0;

        _cls2()
        {
        	super();
        	this$0 = PagosRegistrados.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(registroPago == null)
                registroPago = new Intent(PagosRegistrados.this, PagosRegistrarNuevo.class);
            startActivityForResult(registroPago, 3);
        }

        final PagosRegistrados this$0;

        _cls3()
        {
        	super();
        	this$0 = PagosRegistrados.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            campoFiltroCliente.setText(null);
            if(crp != null && !crp.isClosed())
                crp.close();
            crp = DBHandle.obtenerPagos();
            lv_cancelaciones.setAdapter(new AdpPagos(PagosRegistrados.this, crp, oclSeleccionarPago));
        }

        final PagosRegistrados this$0;

        _cls4()
        {
        	super();
        	this$0 = PagosRegistrados.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            int i = Integer.parseInt(view.getTag().toString());
            Intent intent = new Intent(PagosRegistrados.this, PagosRegistrarNuevo.class);
            intent.putExtra("ID-PAGO", i);
            startActivity(intent);
        }

        final PagosRegistrados this$0;

        _cls5()
        {
        	super();
        	this$0 = PagosRegistrados.this;
        }
    }

}
