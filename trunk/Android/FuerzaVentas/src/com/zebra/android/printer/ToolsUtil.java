// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.comm.ZebraPrinterConnectionException;

public interface ToolsUtil
{

    public abstract void calibrate()
        throws ZebraPrinterConnectionException;

    public abstract void printConfigurationLabel()
        throws ZebraPrinterConnectionException;

    public abstract void reset()
        throws ZebraPrinterConnectionException;

    public abstract void restoreDefaults()
        throws ZebraPrinterConnectionException;

    public abstract void sendCommand(String s)
        throws ZebraPrinterConnectionException;
}
