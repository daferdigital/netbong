// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;

// Referenced classes of package com.zebra.android.printer:
//            ZplPrintMode

public abstract class PrinterStatus
{

    public PrinterStatus(ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        statusHasBeenRetrievedFromPrinter = false;
        printerConnection = zebraprinterconnection;
        numberOfFormatsInReceiveBuffer = 0;
        labelsRemainingInBatch = 0;
        isPartialFormatInProgress = false;
        isHeadCold = false;
        printMode = ZplPrintMode.UNKNOWN;
        labelLengthInDots = 0;
        getStatusFromPrinter();
    }

    private void getStatusFromPrinter()
        throws ZebraPrinterConnectionException
    {
        boolean flag = true;
        if(!statusHasBeenRetrievedFromPrinter)
        {
            updateStatus();
            statusHasBeenRetrievedFromPrinter = flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            boolean flag6;
            boolean flag7;
            boolean flag8;
            boolean flag9;
            boolean flag10;
            if(!isPaperOut)
                flag1 = flag;
            else
                flag1 = false;
            isReadyToPrint = flag1;
            flag2 = isReadyToPrint;
            if(!isPaused)
                flag3 = flag;
            else
                flag3 = false;
            isReadyToPrint = flag3 & flag2;
            flag4 = isReadyToPrint;
            if(!isReceiveBufferFull)
                flag5 = flag;
            else
                flag5 = false;
            isReadyToPrint = flag5 & flag4;
            flag6 = isReadyToPrint;
            if(!isHeadTooHot)
                flag7 = flag;
            else
                flag7 = false;
            isReadyToPrint = flag7 & flag6;
            flag8 = isReadyToPrint;
            if(!isHeadOpen)
                flag9 = flag;
            else
                flag9 = false;
            isReadyToPrint = flag9 & flag8;
            flag10 = isReadyToPrint;
            if(isRibbonOut)
                flag = false;
            isReadyToPrint = flag10 & flag;
        }
    }

    protected abstract void updateStatus()
        throws ZebraPrinterConnectionException;

    public boolean isHeadCold;
    public boolean isHeadOpen;
    public boolean isHeadTooHot;
    public boolean isPaperOut;
    public boolean isPartialFormatInProgress;
    public boolean isPaused;
    public boolean isReadyToPrint;
    public boolean isReceiveBufferFull;
    public boolean isRibbonOut;
    public int labelLengthInDots;
    public int labelsRemainingInBatch;
    public int numberOfFormatsInReceiveBuffer;
    public ZplPrintMode printMode;
    protected ZebraPrinterConnection printerConnection;
    private boolean statusHasBeenRetrievedFromPrinter;
}
