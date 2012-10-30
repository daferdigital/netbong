// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.catalogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.adapters.AdapterCatalogoProductos;
import com.netbong.fuerza.adapters.AdapterLineasProductoConocerCatalogo;
import com.netbong.fuerza.db.cursores.*;

// Referenced classes of package com.ehp.droidsf.catalogo:
//            CatalogoProductosDetalle

public class CatalogoProductos extends Activity
{

    public CatalogoProductos()
    {
        oclLineas = new _ControlLineas();
        oclProductos = new _ControlProductos();
    }

    private void filtrarProductosPorLinea(int i, String s)
    {
        ((TextView)findViewById(0x7f06001d)).setText(s);
        ((ListView)findViewById(0x7f06001e)).setAdapter(getAdapterCatalogoProductos(i));
    }

    private AdapterCatalogoProductos getAdapterCatalogoProductos()
    {
        AdapterCatalogoProductos adaptercatalogoproductos = new AdapterCatalogoProductos(this, getCursorCatalogo(), oclProductos);
        adaptercatalogoproductos.setMostrarPrecio(false);
        return adaptercatalogoproductos;
    }

    private AdapterCatalogoProductos getAdapterCatalogoProductos(int i)
    {
        AdapterCatalogoProductos adaptercatalogoproductos = new AdapterCatalogoProductos(this, getCursorCatalogo(i), oclProductos);
        adaptercatalogoproductos.setMostrarPrecio(false);
        return adaptercatalogoproductos;
    }

    private CursorCatalogo getCursorCatalogo()
    {
        if(crCatalogo != null && !crCatalogo.isClosed())
        {
            crCatalogo.deactivate();
            crCatalogo.close();
        }
        crCatalogo = CursorCatalogo.getProductos(MainActivity.mDbHelper.getWritableDatabase(), com.netbong.fuerza.db.cursores.CursorCatalogo.SortBy.nombre);
        return crCatalogo;
    }

    private CursorCatalogo getCursorCatalogo(int i)
    {
        if(crCatalogo != null && !crCatalogo.isClosed())
        {
            crCatalogo.deactivate();
            crCatalogo.close();
        }
        android.database.sqlite.SQLiteDatabase sqlitedatabase = MainActivity.mDbHelper.getWritableDatabase();
        com.netbong.fuerza.db.cursores.CursorCatalogo.SortBy sortby = com.netbong.fuerza.db.cursores.CursorCatalogo.SortBy.nombre;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        crCatalogo = CursorCatalogo.getProductos(sqlitedatabase, sortby, String.format(" %d ", aobj));
        return crCatalogo;
    }

    private CursorLineasProducto getCursorLineas()
    {
        if(crLineas == null)
            crLineas = CursorLineasProducto.getLineasProducto(MainActivity.mDbHelper.getWritableDatabase());
        return crLineas;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.catalogo_productos_activity_layout);
        mostrarDetalle = new Intent(this, CatalogoProductosDetalle.class);
       
        //Crear list 
        ListView listview = (ListView)findViewById(R.id.lv_lineas);
        listview.setAdapter(new AdapterLineasProductoConocerCatalogo(this, getCursorLineas(), oclLineas));
        listview.setCacheColorHint(0);
        
        //Crear Catalogo de producto @id/lv_catalogo
        ListView listview1 = (ListView)findViewById(R.id.lv_catalogo);
        listview1.setAdapter(getAdapterCatalogoProductos());
        listview1.setCacheColorHint(0);
        listview1.setDivider(null);
    }

    private CursorCatalogo crCatalogo;
    private CursorLineasProducto crLineas;
    private Intent mostrarDetalle;
    private android.view.View.OnClickListener oclLineas;
    private android.view.View.OnClickListener oclProductos;





    private class _ControlLineas
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            crLineas.moveToPosition(view.getId());
            int i = crLineas.getId();
            String s = view.getTag().toString();
            filtrarProductosPorLinea(i, s);
        }

        final CatalogoProductos this$0;

        _ControlLineas()
        {
        	super();
        	this$0 = CatalogoProductos.this;
        }
    }


    private class _ControlProductos
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            int i = Integer.parseInt(((LinearLayout)view).findViewById(0x7f060115).getTag().toString());
            crCatalogo.moveToPosition(i);
            mostrarDetalle.putExtra("ID", crCatalogo.getId());
            startActivity(mostrarDetalle);
        }

        final CatalogoProductos this$0;

        _ControlProductos()
        {
        	super();
        	this$0 = CatalogoProductos.this;
        }
    }

}
