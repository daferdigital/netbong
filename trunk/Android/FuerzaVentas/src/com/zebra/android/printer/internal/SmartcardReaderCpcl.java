// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.comm.internal.PrinterCommand;
import com.zebra.android.comm.internal.PrinterCommandImpl;
import com.zebra.android.printer.SmartcardReader;

public class SmartcardReaderCpcl
    implements SmartcardReader
{

    public SmartcardReaderCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        printerConnection = zebraprinterconnection;
    }

    public void close()
        throws ZebraPrinterConnectionException
    {
        printerConnection.write("! U1 S-CARD CT_CLOSE\r\n".getBytes());
    }

    public byte[] doCommand(String s)
        throws ZebraPrinterConnectionException
    {
        return (new PrinterCommandImpl((new StringBuilder()).append("! U1 S-CARD CT_DATA ").append(s.length()).append(" ").append(s).append("\r\n").toString())).sendAndWaitForResponse(printerConnection);
    }

    public byte[] getATR()
        throws ZebraPrinterConnectionException
    {
        return (new PrinterCommandImpl("! U1 S-CARD CT_ATR\r\n")).sendAndWaitForResponse(printerConnection);
    }

    protected ZebraPrinterConnection printerConnection;
}
