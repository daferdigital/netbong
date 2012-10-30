// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;


public abstract class PrinterFileProperties
{

    public PrinterFileProperties()
    {
    }

    protected String getExt()
    {
        return extension;
    }

    public String getFullName()
    {
        return (new StringBuilder()).append(drivePrefix).append(fileName).append(".").append(extension).toString();
    }

    protected String drivePrefix;
    protected String extension;
    protected String fileName;
}
