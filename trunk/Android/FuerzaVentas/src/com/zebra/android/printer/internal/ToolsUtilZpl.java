// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.ToolsUtil;
import com.zebra.android.util.internal.ZPLUtilities;

public class ToolsUtilZpl
    implements ToolsUtil
{

    public ToolsUtilZpl(ZebraPrinterConnection zebraprinterconnection)
    {
        connection = zebraprinterconnection;
    }

    public void calibrate()
        throws ZebraPrinterConnectionException
    {
        connection.write(ZPLUtilities.PRINTER_CALIBRATE.getBytes());
    }

    public void printConfigurationLabel()
        throws ZebraPrinterConnectionException
    {
        connection.write(ZPLUtilities.PRINTER_CONFIG_LABEL.getBytes());
    }

    public void reset()
        throws ZebraPrinterConnectionException
    {
        connection.write(ZPLUtilities.PRINTER_RESET.getBytes());
    }

    public void restoreDefaults()
        throws ZebraPrinterConnectionException
    {
        connection.write(ZPLUtilities.PRINTER_RESTORE_DEFAULTS.getBytes());
    }

    public void sendCommand(String s)
        throws ZebraPrinterConnectionException
    {
        if(s != null)
            connection.write(s.getBytes());
    }

    protected ZebraPrinterConnection connection;
}
