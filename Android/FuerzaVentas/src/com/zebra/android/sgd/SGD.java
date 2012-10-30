// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.sgd;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.comm.internal.PrinterCommand;
import com.zebra.android.comm.internal.PrinterCommandImpl;
import com.zebra.android.util.internal.StringUtilities;

public class SGD
{

    private SGD()
    {
    }

    public static String DO(String s, String s1, ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        return DO(s, s1, zebraprinterconnection, zebraprinterconnection.getMaxTimeoutForRead(), zebraprinterconnection.getTimeToWaitForMoreData());
    }

    public static String DO(String s, String s1, ZebraPrinterConnection zebraprinterconnection, int i, int j)
        throws ZebraPrinterConnectionException
    {
        return StringUtilities.stripQuotes(new String((new PrinterCommandImpl((new StringBuilder()).append("! U1 do \"").append(s).append("\" \"").append(s1).append("\"").append("\r\n").toString())).sendAndWaitForResponse(zebraprinterconnection, i, j)));
    }

    public static String GET(String s, ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        return GET(s, zebraprinterconnection, zebraprinterconnection.getMaxTimeoutForRead(), zebraprinterconnection.getTimeToWaitForMoreData());
    }

    public static String GET(String s, ZebraPrinterConnection zebraprinterconnection, int i, int j)
        throws ZebraPrinterConnectionException
    {
        return StringUtilities.stripQuotes(new String((new PrinterCommandImpl((new StringBuilder()).append("! U1 getvar \"").append(s).append("\"").append("\r\n").toString())).sendAndWaitForResponse(zebraprinterconnection, i, j)));
    }

    public static void SET(String s, int i, ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        SET(s, String.valueOf(i), zebraprinterconnection);
    }

    public static void SET(String s, String s1, ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        zebraprinterconnection.write((new StringBuilder()).append("! U1 setvar \"").append(s).append("\" \"").append(s1).append("\"").append("\r\n").toString().getBytes());
    }
}
