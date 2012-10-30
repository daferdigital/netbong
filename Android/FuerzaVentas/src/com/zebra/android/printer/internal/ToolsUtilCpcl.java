// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.ToolsUtil;
import com.zebra.android.sgd.SGD;
import com.zebra.android.util.internal.CPCLUtilities;

public class ToolsUtilCpcl
    implements ToolsUtil
{

    public ToolsUtilCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        connection = zebraprinterconnection;
    }

    public void calibrate()
        throws ZebraPrinterConnectionException
    {
        connection.write(CPCLUtilities.PRINTER_FORM_FEED.getBytes());
    }

    public void printConfigurationLabel()
        throws ZebraPrinterConnectionException
    {
        connection.write(CPCLUtilities.PRINTER_CONFIG_LABEL.getBytes());
    }

    public void reset()
        throws ZebraPrinterConnectionException
    {
        SGD.SET("device.reset", "", connection);
    }

    public void restoreDefaults()
        throws ZebraPrinterConnectionException
    {
        SGD.SET("device.restore_defaults", "display", connection);
        reset();
    }

    public void sendCommand(String s)
        throws ZebraPrinterConnectionException
    {
        if(s != null)
        {
            String s1 = (new StringBuilder()).append(s).append("\r\n").toString();
            connection.write(s1.getBytes());
        }
    }

    protected ZebraPrinterConnection connection;
}
