// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.comm.ZebraPrinterConnectionException;

// Referenced classes of package com.zebra.android.printer:
//            PrinterStatus, FileUtil, FormatUtil, GraphicsUtil, 
//            MagCardReader, PrinterLanguage, SmartcardReader, ToolsUtil

public interface ZebraPrinter
{

    public abstract PrinterStatus getCurrentStatus()
        throws ZebraPrinterConnectionException;

    public abstract FileUtil getFileUtil()
        throws ZebraPrinterConnectionException;

    public abstract FormatUtil getFormatUtil()
        throws ZebraPrinterConnectionException;

    public abstract GraphicsUtil getGraphicsUtil()
        throws ZebraPrinterConnectionException;

    public abstract MagCardReader getMagCardReader()
        throws ZebraPrinterConnectionException;

    public abstract PrinterLanguage getPrinterControlLanguage();

    public abstract SmartcardReader getSmartcardReader()
        throws ZebraPrinterConnectionException;

    public abstract ToolsUtil getToolsUtil()
        throws ZebraPrinterConnectionException;
}
