// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.comm.internal.PrinterCommand;
import com.zebra.android.comm.internal.PrinterCommandImpl;
import com.zebra.android.printer.MagCardReader;

public class MagCardReaderCpcl
    implements MagCardReader
{

    public MagCardReaderCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        printerConnection = zebraprinterconnection;
    }

    public String[] read(int i)
        throws ZebraPrinterConnectionException
    {
        String as[] = new String[3];
        as[0] = "";
        as[1] = "";
        as[2] = "";
        if(i <= 0)
            i = 1000;
        int j = (i * 8) / 1000;
        String s = new String((new PrinterCommandImpl((new StringBuilder()).append("! U1 MCR ").append(j).append(" T1 T2 T3").append("\r\n").toString())).sendAndWaitForResponse(printerConnection, i, printerConnection.getTimeToWaitForMoreData()));
        int k = s.indexOf("T1:");
        int l = -1;
        if(k != -1)
            l = s.indexOf("\r\n", k);
        if(k != -1 && l != -1)
            as[0] = s.substring(k + "T1:".length(), l);
        int i1 = s.indexOf("T2:");
        int j1 = -1;
        if(i1 != -1)
            j1 = s.indexOf("\r\n", i1);
        if(i1 != -1 && j1 != -1)
            as[1] = s.substring(i1 + "T2:".length(), j1);
        int k1 = s.indexOf("T3:");
        int l1 = -1;
        if(k1 != -1)
            l1 = s.indexOf("\r\n", k1);
        if(k1 != -1 && l1 != -1)
            as[2] = s.substring(k1 + "T3:".length(), l1);
        return as;
    }

    protected ZebraPrinterConnection printerConnection;
}
