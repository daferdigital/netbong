// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.comm.internal.PrinterCommandImpl;
import com.zebra.android.printer.PrinterStatus;
import com.zebra.android.printer.ZplPrintMode;
import com.zebra.android.util.internal.StringUtilities;
import com.zebra.android.util.internal.ZPLUtilities;

public class PrinterStatusZpl extends PrinterStatus
{

    public PrinterStatusZpl(ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        super(zebraprinterconnection);
    }

    private static ZplPrintMode getPrintModeFromHs(char c) {
        String.valueOf(c).toUpperCase().charAt(0);
        ZplPrintMode zplprintmode = ZplPrintMode.UNKNOWN;
        
        switch (String.valueOf(c).toUpperCase().charAt(0)) {
        default:
        	zplprintmode = ZplPrintMode.UNKNOWN;
        	break;
        case '0':
        	zplprintmode = ZplPrintMode.REWIND;
        	break;
        case '1':
        	zplprintmode = ZplPrintMode.PEEL_OFF;
        	break;
        case '2':
        	zplprintmode = ZplPrintMode.TEAR_OFF;
        	break;
        case '3':
        	zplprintmode = ZplPrintMode.CUTTER;
        	break;
        case '4':
        	zplprintmode = ZplPrintMode.APPLICATOR;
        	break;
        case '5':
        	zplprintmode = ZplPrintMode.DELAYED_CUT;
        	break;
        case '6':
        	zplprintmode = ZplPrintMode.LINERLESS_PEEL;
        	break;
        case '7':
        	zplprintmode = ZplPrintMode.LINERLESS_REWIND;
        	break;
        case '8':
        	zplprintmode = ZplPrintMode.PARTIAL_CUTTER;
        	break;
        case '9':
        	zplprintmode = ZplPrintMode.RFID;
        	break;
        case 'K':
        	zplprintmode = ZplPrintMode.KIOSK;
        	break;
        }
        
        return zplprintmode;
    }

    private String[] getPrinterStatus()
        throws ZebraPrinterConnectionException
    {
        byte abyte0[] = (new PrinterCommandImpl(ZPLUtilities.PRINTER_STATUS)).sendAndWaitForResponse(printerConnection);
        StringBuffer stringbuffer = new StringBuffer();
        String as[] = new String[0];
        if(abyte0 != null)
        {
            int i;
            for(i = 0; i < abyte0.length && abyte0[i] != 2; i++);
            if(i == abyte0.length)
                throw new ZebraPrinterConnectionException("Malformed status response - unable to determine printer status");
            for(int j = 0; j < abyte0.length; j++)
            {
                if(abyte0[j] == 3)
                    abyte0[j] = 44;
                if(abyte0[j] > 31 && abyte0[j] < 127)
                    stringbuffer.append(String.valueOf((char)abyte0[j]));
            }

        }
        if(stringbuffer.length() >= 1)
        {
            as = StringUtilities.split(stringbuffer.toString(), ",");
            if(as.length < 25)
                throw new ZebraPrinterConnectionException("Malformed status response - unable to determine printer status");
        }
        return as;
    }

    protected void updateStatus()
        throws ZebraPrinterConnectionException
    {
        String as[] = getPrinterStatus();
        labelsRemainingInBatch = Integer.parseInt(as[20]);
        numberOfFormatsInReceiveBuffer = Integer.parseInt(as[4]);
        isPartialFormatInProgress = as[7].equals("1");
        isHeadCold = as[10].equals("1");
        isHeadOpen = as[14].equals("1");
        isHeadTooHot = as[11].equals("1");
        isPaperOut = as[1].equals("1");
        isRibbonOut = as[15].equals("1");
        isReceiveBufferFull = as[5].equals("1");
        isPaused = as[2].equals("1");
        labelLengthInDots = Integer.parseInt(as[3]);
        printMode = getPrintModeFromHs(as[17].charAt(0));
    }
}
