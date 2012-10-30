// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.comm.ZebraPrinterConnectionException;

public interface SmartcardReader
{

    public abstract void close()
        throws ZebraPrinterConnectionException;

    public abstract byte[] doCommand(String s)
        throws ZebraPrinterConnectionException;

    public abstract byte[] getATR()
        throws ZebraPrinterConnectionException;
}
