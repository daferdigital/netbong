// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.*;

// Referenced classes of package com.zebra.android.printer.internal:
//            ZebraPrinterA, PrinterStatusCpcl, FileUtilCpcl, FormatUtilCpcl, 
//            GraphicsUtilCpcl, MagCardReaderCpcl, SmartcardReaderCpcl, ToolsUtilCpcl

public class ZebraPrinterCpcl extends ZebraPrinterA
{

    public ZebraPrinterCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        super(zebraprinterconnection);
    }

    public PrinterStatus getCurrentStatus()
        throws ZebraPrinterConnectionException
    {
        return new PrinterStatusCpcl(connection);
    }

    public FileUtil getFileUtil()
        throws ZebraPrinterConnectionException
    {
        if(fileUtil == null)
            fileUtil = new FileUtilCpcl(connection);
        return fileUtil;
    }

    public FormatUtil getFormatUtil()
        throws ZebraPrinterConnectionException
    {
        if(formatUtil == null)
            formatUtil = new FormatUtilCpcl(connection);
        return formatUtil;
    }

    public GraphicsUtil getGraphicsUtil()
        throws ZebraPrinterConnectionException
    {
        if(graphicsUtil == null)
            graphicsUtil = new GraphicsUtilCpcl(connection);
        return graphicsUtil;
    }

    public MagCardReader getMagCardReader()
        throws ZebraPrinterConnectionException
    {
        if(magCardReader == null)
            magCardReader = new MagCardReaderCpcl(connection);
        return magCardReader;
    }

    public PrinterLanguage getPrinterControlLanguage()
    {
        return PrinterLanguage.CPCL;
    }

    public SmartcardReader getSmartcardReader()
        throws ZebraPrinterConnectionException
    {
        if(smartcardReader == null)
            smartcardReader = new SmartcardReaderCpcl(connection);
        return smartcardReader;
    }

    public ToolsUtil getToolsUtil()
        throws ZebraPrinterConnectionException
    {
        if(toolsUtil == null)
            toolsUtil = new ToolsUtilCpcl(connection);
        return toolsUtil;
    }
}
