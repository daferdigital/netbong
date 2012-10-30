// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;


public class PrinterFilePath
{

    public PrinterFilePath(String s, String s1)
    {
        drive = s;
        fileName = s1;
    }

    public String getDrive()
    {
        return drive;
    }

    public String getFileName()
    {
        return fileName;
    }

    private String drive;
    private String fileName;
}
