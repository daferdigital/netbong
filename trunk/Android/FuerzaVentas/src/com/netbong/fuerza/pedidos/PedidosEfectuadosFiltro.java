// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos;

import com.netbong.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

public class PedidosEfectuadosFiltro extends Activity
{

    public PedidosEfectuadosFiltro()
    {
        oclAceptar = new _cls1();
        oclCancelar = new _cls2();
    }

    private String obtenerFecha(DatePicker datepicker)
    {
        String s = Integer.toString(datepicker.getYear());
        String s1 = (new StringBuilder("00")).append(Integer.toString(1 + datepicker.getMonth())).toString();
        String s2 = Integer.toString(datepicker.getDayOfMonth());
        return (new StringBuilder(String.valueOf(s))).append('-').append(s1.substring(1)).append('-').append(s2).toString();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pedidos_efectuados_filtro_activity_layout);
        ((ImageButton)findViewById(0x7f0600b1)).setOnClickListener(oclAceptar);
        ((ImageButton)findViewById(0x7f060018)).setOnClickListener(oclCancelar);
    }

    android.view.View.OnClickListener oclAceptar;
    android.view.View.OnClickListener oclCancelar;


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            StringBuilder stringbuilder = new StringBuilder();
            Intent intent = new Intent();
            EditText edittext = (EditText)findViewById(0x7f060068);
            if(edittext.getText() != null && edittext.getText().toString().trim().length() > 0)
            {
                stringbuilder.append((new StringBuilder("Cliente con patron de nombre: ")).append(edittext.getText().toString()).toString());
                stringbuilder.append(" + ");
                intent.putExtra("CLIENTE", edittext.getText().toString());
            }
            if(((CheckBox)findViewById(0x7f0600b4)).isChecked())
            {
                stringbuilder.append("Con estatus Generado");
                stringbuilder.append(" + ");
                intent.putExtra("GENERADO", "1");
            }
            if(((CheckBox)findViewById(0x7f0600b5)).isChecked())
            {
                stringbuilder.append("Con estatus Anulado");
                stringbuilder.append(" + ");
                intent.putExtra("ANULADO", "1");
            }
            if(((CheckBox)findViewById(0x7f06013f)).isChecked())
            {
                stringbuilder.append("Con estatus Facturado");
                stringbuilder.append(" + ");
                intent.putExtra("FACTURADO", "1");
            }
            if(((CheckBox)findViewById(0x7f060140)).isChecked())
            {
                stringbuilder.append("Con estatus Sincronizado");
                stringbuilder.append(" + ");
                intent.putExtra("SINCRONIZADO", "1");
            }
            DatePicker datepicker = (DatePicker)findViewById(0x7f0600b2);
            String s = obtenerFecha(datepicker);
            stringbuilder.append((new StringBuilder("Con fecha de generacion desde: ")).append(s).toString());
            stringbuilder.append(" + ");
            intent.putExtra("FECHA_DESDE", s);
            DatePicker datepicker1 = (DatePicker)findViewById(0x7f0600b3);
            String s1 = obtenerFecha(datepicker1);
            stringbuilder.append((new StringBuilder("Con fecha de generacion hasta: ")).append(s1).toString());
            stringbuilder.append(" + ");
            intent.putExtra("FECHA_HASTA", s1);
            if(stringbuilder != null && stringbuilder.length() > 0)
            {
                intent.putExtra("CRITERIOS", stringbuilder.toString());
                setResult(-1, intent);
            } else
            {
                setResult(0);
            }
            finish();
        }

        final PedidosEfectuadosFiltro this$0;

        _cls1()
        {
        	super();
        	this$0 = PedidosEfectuadosFiltro.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            finish();
        }

        final PedidosEfectuadosFiltro this$0;

        _cls2()
        {
        	super();
        	this$0 = PedidosEfectuadosFiltro.this;
        }
    }

}
