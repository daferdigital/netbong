// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.db.cursores.DroidSFDatabase;

public class RegistrarCancelaciones extends Activity
    implements android.view.View.OnClickListener
{

    public RegistrarCancelaciones()
    {
    }

    public void onClick(View view)
    {
        if(view == findViewById(0x7f060010))
        {
            byte byte0 = 0;
            byte byte1 = 0;
            if(((RadioButton)findViewById(0x7f060004)).isChecked())
                byte0 = 0;
            if(((RadioButton)findViewById(0x7f060005)).isChecked())
                byte0 = 1;
            if(((RadioButton)findViewById(0x7f060006)).isChecked())
                byte0 = 2;
            if(((RadioButton)findViewById(0x7f060008)).isChecked())
                byte1 = 0;
            if(((RadioButton)findViewById(0x7f060009)).isChecked())
                byte1 = 1;
            if(((RadioButton)findViewById(0x7f06000a)).isChecked())
                byte1 = 2;
            double d = Double.valueOf(((EditText)findViewById(0x7f06000c)).getText().toString()).doubleValue();
            String s = ((EditText)findViewById(0x7f06000e)).getText().toString();
            MainActivity.mDbHelper.crearFacturaAbonos(idFactura, byte0, d, byte1, s);
        }
        setResult(-1);
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.registro_cancelaciones_activity_layout);
        getWindow().setLayout(600, -2);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
            idFactura = bundle1.getInt("FACTURA");
        ((Button)findViewById(0x7f060010)).setOnClickListener(this);
        ((Button)findViewById(0x7f06000f)).setOnClickListener(this);
    }

    private int idFactura;
}
