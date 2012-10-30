// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import com.netbong.R;
import com.netbong.fuerza.dialogos.DialogoSiNo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class CancelacionesRegistradas extends Activity
{

    public CancelacionesRegistradas()
    {
        textWatcher = new _cls1();
        toolBarOpcCancelar = new _cls2();
        toolBarOpcAnular = new _cls3();
        toolBarOpcRecuperar = new _cls4();
        toolBarOpcNuevaConsulta = new _cls5();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.cancelaciones_registradas_activity_layout);
        campoFiltroCliente = (EditText)findViewById(0x7f060015);
        campoFiltroCliente.addTextChangedListener(textWatcher);
        lv_cancelaciones = (ListView)findViewById(0x7f060016);
        lv_cancelaciones.setDivider(null);
        lv_cancelaciones.setCacheColorHint(0);
        findViewById(0x7f060019).setOnClickListener(toolBarOpcAnular);
        findViewById(0x7f06001a).setOnClickListener(toolBarOpcRecuperar);
        findViewById(0x7f060018).setOnClickListener(toolBarOpcCancelar);
        findViewById(0x7f060017).setOnClickListener(toolBarOpcNuevaConsulta);
    }

    private static final int INTENT_REQUEST_CANCELACION_ANULAR = 1;
    private static final int INTENT_REQUEST_CANCELACION_RECUPERAR = 2;
    EditText campoFiltroCliente;
    ListView lv_cancelaciones;
    private TextWatcher textWatcher;
    private android.view.View.OnClickListener toolBarOpcAnular;
    android.view.View.OnClickListener toolBarOpcCancelar;
    private android.view.View.OnClickListener toolBarOpcNuevaConsulta;
    private android.view.View.OnClickListener toolBarOpcRecuperar;

    private class _cls1
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        final CancelacionesRegistradas this$0;

        _cls1()
        {
        	super();
        	this$0 = CancelacionesRegistradas.this;
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

        final CancelacionesRegistradas this$0;

        _cls2()
        {
        	super();
        	this$0 = CancelacionesRegistradas.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(CancelacionesRegistradas.this, DialogoSiNo.class);
            intent.putExtra("MENSAJE", "Esto anulara el pedido actual. \277Desea continuar?");
            startActivityForResult(intent, 1);
        }

        final CancelacionesRegistradas this$0;

        _cls3()
        {
        	super();
        	this$0 = CancelacionesRegistradas.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(CancelacionesRegistradas.this, DialogoSiNo.class);
            intent.putExtra("MENSAJE", "\277Desea recuperar el pedidio Anulado?");
            startActivityForResult(intent, 2);
        }

        final CancelacionesRegistradas this$0;

        _cls4()
        {
        	super();
        	this$0 = CancelacionesRegistradas.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
        }

        final CancelacionesRegistradas this$0;

        _cls5()
        {
        	super();
        	this$0 = CancelacionesRegistradas.this;
        }
    }

}
