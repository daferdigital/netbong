// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.printer.*;

public abstract class ZebraPrinterA
    implements ZebraPrinter
{

    protected ZebraPrinterA(ZebraPrinterConnection zebraprinterconnection)
    {
        connection = null;
        fileUtil = null;
        formatUtil = null;
        graphicsUtil = null;
        toolsUtil = null;
        magCardReader = null;
        smartcardReader = null;
        connection = zebraprinterconnection;
    }

    protected ZebraPrinterConnection connection;
    protected FileUtil fileUtil;
    protected FormatUtil formatUtil;
    protected GraphicsUtil graphicsUtil;
    protected MagCardReader magCardReader;
    protected SmartcardReader smartcardReader;
    protected ToolsUtil toolsUtil;
}
