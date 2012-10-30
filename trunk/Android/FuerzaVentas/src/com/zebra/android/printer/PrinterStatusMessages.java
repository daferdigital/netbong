// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.util.internal.StringUtilities;

// Referenced classes of package com.zebra.android.printer:
//            PrinterStatus

public class PrinterStatusMessages
{

    public PrinterStatusMessages(PrinterStatus printerstatus)
    {
        printerStatus = printerstatus;
    }

    public String[] getStatusMessage()
    {
        StringBuffer stringbuffer = new StringBuffer();
        String as[];
        if(printerStatus == null)
        {
            stringbuffer.append("INVALID STATUS");
            stringbuffer.append(";");
            as = new String[1];
            as[0] = stringbuffer.toString();
        } else
        {
            if(printerStatus.isHeadOpen)
            {
                stringbuffer.append("HEAD OPEN");
                stringbuffer.append(";");
            }
            if(printerStatus.isHeadTooHot)
            {
                stringbuffer.append("HEAD TOO HOT");
                stringbuffer.append(";");
            }
            if(printerStatus.isPaperOut)
            {
                stringbuffer.append("PAPER OUT");
                stringbuffer.append(";");
            }
            if(printerStatus.isRibbonOut)
            {
                stringbuffer.append("RIBBON OUT");
                stringbuffer.append(";");
            }
            if(printerStatus.isReceiveBufferFull)
            {
                stringbuffer.append("RECEIVE BUFFER FULL");
                stringbuffer.append(";");
            }
            if(printerStatus.isPaused)
            {
                stringbuffer.append("PAUSE");
                stringbuffer.append(";");
            }
            if(stringbuffer.length() > 0 && stringbuffer.charAt(-1 + stringbuffer.length()) == ';')
                stringbuffer.deleteCharAt(-1 + stringbuffer.length());
            as = StringUtilities.split(stringbuffer.toString(), ";");
        }
        return as;
    }

    public static final String HEAD_OPEN_MSG = "HEAD OPEN";
    public static final String HEAD_TOO_HOT_MSG = "HEAD TOO HOT";
    public static final String NULL_MSG = "INVALID STATUS";
    public static final String PAPER_OUT_MSG = "PAPER OUT";
    public static final String PAUSE_MSG = "PAUSE";
    public static final String RECEIVE_BUFFER_FULL_MSG = "RECEIVE BUFFER FULL";
    public static final String RIBBON_OUT_MSG = "RIBBON OUT";
    private PrinterStatus printerStatus;
}
