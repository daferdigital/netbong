// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.netbong.fuerza.facturas.RegistrarCancelaciones;

// Referenced classes of package com.ehp.droidsf:
//            MainActivity, RegistroDevoluciones

public class ConsultarFacturasPopupDialogMenu extends ListActivity
{

    public ConsultarFacturasPopupDialogMenu()
    {
        String as[] = new String[2];
        as[0] = "Abonar a Factura";
        as[1] = "Registar Devoluciones";
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
        if(i == 0)
        {
            Intent intent = new Intent(MainActivity.mainCtx, RegistrarCancelaciones.class);
            intent.putExtra("FACTURA", idFactura);
            startActivity(intent);
        }
        if(i == 1)
        {
            Intent intent1 = new Intent(MainActivity.mainCtx, RegistroDevoluciones.class);
            intent1.putExtra("FACTURA", idFactura);
            startActivity(intent1);
        }
        finish();
    }

    private int idFactura;
    private String opciones[];
}
