// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.*;

// Referenced classes of package com.zebra.android.printer.internal:
//            ZebraPrinterA, PrinterStatusZpl, FileUtilZpl, FormatUtilZpl, 
//            GraphicsUtilZpl, ToolsUtilZpl

public class ZebraPrinterZpl extends ZebraPrinterA
{

    public ZebraPrinterZpl(ZebraPrinterConnection zebraprinterconnection)
    {
        super(zebraprinterconnection);
    }

    public PrinterStatus getCurrentStatus()
        throws ZebraPrinterConnectionException
    {
        return new PrinterStatusZpl(connection);
    }

    public FileUtil getFileUtil()
        throws ZebraPrinterConnectionException
    {
        if(fileUtil == null)
            fileUtil = new FileUtilZpl(connection);
        return fileUtil;
    }

    public FormatUtil getFormatUtil()
        throws ZebraPrinterConnectionException
    {
        if(formatUtil == null)
            formatUtil = new FormatUtilZpl(connection);
        return formatUtil;
    }

    public GraphicsUtil getGraphicsUtil()
        throws ZebraPrinterConnectionException
    {
        if(graphicsUtil == null)
            graphicsUtil = new GraphicsUtilZpl(connection);
        return graphicsUtil;
    }

    public MagCardReader getMagCardReader()
        throws ZebraPrinterConnectionException
    {
        return null;
    }

    public PrinterLanguage getPrinterControlLanguage()
    {
        return PrinterLanguage.ZPL;
    }

    public SmartcardReader getSmartcardReader()
        throws ZebraPrinterConnectionException
    {
        return null;
    }

    public ToolsUtil getToolsUtil()
        throws ZebraPrinterConnectionException
    {
        if(toolsUtil == null)
            toolsUtil = new ToolsUtilZpl(connection);
        return toolsUtil;
    }
}
