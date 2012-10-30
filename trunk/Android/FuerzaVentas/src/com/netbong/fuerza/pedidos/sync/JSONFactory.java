// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.sync;

import com.netbong.fuerza.pedidos.db.cursors.*;

import org.json.*;

public class JSONFactory
{

    public JSONFactory()
    {
    }

    private static JSONArray JSONPedidoEventos(int i)
    {
        JSONArray jsonarray = new JSONArray();
        CrsPedidoEventos crspedidoeventos = CrsPedidoEventos.getPedidoEventos(i);
        if(crspedidoeventos.moveToFirst())
        {
            JSONObject jsonobject = new JSONObject();
            do
                try
                {
                    jsonobject.put("fecha", crspedidoeventos.getEventoFecha());
                    jsonobject.put("hora", crspedidoeventos.getEventoHora());
                    jsonobject.put("evento", crspedidoeventos.getEventoDescripcion());
                    jsonarray.put(jsonobject);
                }
                catch(JSONException jsonexception) { }
            while(crspedidoeventos.moveToNext());
        }
        crspedidoeventos.deactivate();
        crspedidoeventos.close();
        return jsonarray;
    }

    private static JSONArray JSONPedidoItems(int i)
    {
        JSONArray jsonarray = new JSONArray();
        CrsPedidoItems crspedidoitems = CrsPedidoItems.getPedidoItems(i);
        if(crspedidoitems.moveToFirst())
        {
            JSONObject jsonobject = new JSONObject();
            do
                try
                {
                    jsonobject.put("codref_producto", crspedidoitems.getItemCodigo());
                    jsonobject.put("precio", crspedidoitems.getItemPrecio());
                    jsonobject.put("cantidad", crspedidoitems.getItemCantidad());
                    jsonobject.put("iva", crspedidoitems.getItemIva());
                    jsonobject.put("total_excento", crspedidoitems.getItemTotalExento());
                    jsonobject.put("total_gravament", crspedidoitems.getItemtotalGravament());
                    jsonobject.put("total_iva_gravament", crspedidoitems.getItemTotalIvaGravament());
                    jsonarray.put(jsonobject);
                }
                catch(JSONException jsonexception) { }
            while(crspedidoitems.moveToNext());
        }
        crspedidoitems.deactivate();
        crspedidoitems.close();
        return jsonarray;
    }

    public static JSONArray JSONPedidos(CrsPedidos crspedidos)
    {
        JSONArray jsonarray = new JSONArray();
        if(crspedidos.moveToFirst())
            do
            {
                JSONObject jsonobject = new JSONObject();
                try
                {
                    jsonobject.put("cliente", crspedidos.getClienteCodRef());
                    jsonobject.put("clie_nombre", crspedidos.getClienteNombre());
                    jsonobject.put("pedido", crspedidos.getPedidoId());
                    jsonobject.put("fecha", crspedidos.getPedidoFecha());
                    jsonobject.put("hora", crspedidos.getPedidoHora());
                    jsonobject.put("total_excento", crspedidos.getPedidoTotalExento());
                    jsonobject.put("total_gravament", crspedidos.getPedidoTotalGravament());
                    jsonobject.put("total_iva_gravament", crspedidos.getPedidoTotalIvaGravament());
                    jsonobject.put("total_general", crspedidos.getPedidoTotalGeneral());
                    jsonobject.put("observacion", crspedidos.getPedidoObservacion());
                    jsonobject.put("items", JSONPedidoItems(crspedidos.getPedidoId()));
                    jsonobject.put("eventos", JSONPedidoEventos(crspedidos.getPedidoId()));
                    jsonarray.put(jsonobject);
                }
                catch(JSONException jsonexception) { }
            } while(crspedidos.moveToNext());
        return jsonarray;
    }
}
