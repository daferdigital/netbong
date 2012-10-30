// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;


public class SGDUtilities
{

    public SGDUtilities()
    {
    }

    public static String decorateWithGetCommand(String s)
    {
        return (new StringBuilder()).append("! U1 getvar \"").append(s).append("\"").append("\r\n").toString();
    }

    public static final String APPL_NAME = "appl.name";
}
