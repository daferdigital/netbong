// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ConsultarPedidosPopupDialogMenu extends ListActivity
{

    public ConsultarPedidosPopupDialogMenu()
    {
        String as[] = new String[3];
        as[0] = "Imprimir";
        as[1] = "Modificar";
        as[2] = "Eliminar";
        opciones = as;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setListAdapter(new ArrayAdapter(this, 0x1090003, opciones));
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
            idFactura = bundle1.getInt("FACTURA");
    }

    public void onListItemClick(ListView listview, View view, int i, long l)
    {
        Toast.makeText(this, (new StringBuilder("Seleccionado ")).append(opciones[i]).toString(), 0).show();
        finish();
    }

    private int idFactura;
    private String opciones[];
}
