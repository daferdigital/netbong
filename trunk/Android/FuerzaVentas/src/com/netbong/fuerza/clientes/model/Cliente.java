// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.clientes.model;


public class Cliente
{

    public Cliente(int i, String s, String s1, double d)
    {
        cliente = i;
        rif = s;
        nombre = s1;
        limiteCredito = d;
    }

    public int getCliente()
    {
        return cliente;
    }

    public double getLimiteCredito()
    {
        return limiteCredito;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getRif()
    {
        return rif;
    }

    public void setCliente(int i)
    {
        cliente = i;
    }

    public void setLimiteCredito(double d)
    {
        limiteCredito = d;
    }

    public void setNombre(String s)
    {
        nombre = s;
    }

    public void setRif(String s)
    {
        rif = s;
    }

    private int cliente;
    private double limiteCredito;
    private String nombre;
    private String rif;
}
