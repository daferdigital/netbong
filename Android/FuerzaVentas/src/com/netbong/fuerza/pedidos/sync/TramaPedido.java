// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.pedidos.sync;


public class TramaPedido
{

    public TramaPedido(String s, String s1, String s2)
    {
        clinete = s;
        fecha = s1;
        trama = s2;
    }

    public String getClinete()
    {
        return clinete;
    }

    public String getFecha()
    {
        return fecha;
    }

    public String getTrama()
    {
        return trama;
    }

    private String clinete;
    private String fecha;
    private String trama;
}
