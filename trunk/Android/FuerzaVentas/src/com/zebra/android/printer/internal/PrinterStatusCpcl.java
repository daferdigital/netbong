// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.comm.internal.PrinterCommandImpl;
import com.zebra.android.printer.PrinterStatus;
import com.zebra.android.printer.ZplPrintMode;
import com.zebra.android.util.internal.CPCLUtilities;

public class PrinterStatusCpcl extends PrinterStatus
{

    public PrinterStatusCpcl(ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        super(zebraprinterconnection);
    }

    protected void updateStatus()
        throws ZebraPrinterConnectionException
    {
        boolean flag = true;
        byte abyte0[] = (new PrinterCommandImpl(CPCLUtilities.PRINTER_STATUS)).sendAndWaitForResponse(printerConnection);
        if(abyte0.length != 1)
            throw new ZebraPrinterConnectionException((new StringBuilder()).append("Malformed status response - unable to determine printer status (received ").append(abyte0.length).append(" bytes)").toString());
        labelsRemainingInBatch = 0;
        numberOfFormatsInReceiveBuffer = 0;
        isPartialFormatInProgress = false;
        isHeadCold = false;
        boolean flag1;
        if((4 & abyte0[0]) == 4)
            flag1 = flag;
        else
            flag1 = false;
        isHeadOpen = flag1;
        isHeadTooHot = false;
        if((2 & abyte0[0]) != 2)
            flag = false;
        isPaperOut = flag;
        isRibbonOut = false;
        isReceiveBufferFull = false;
        isPaused = false;
        labelLengthInDots = 0;
        printMode = ZplPrintMode.UNKNOWN;
    }
}
