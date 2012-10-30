// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.internal.ZebraPrinterCpcl;
import com.zebra.android.printer.internal.ZebraPrinterZpl;
import com.zebra.android.sgd.SGD;
import com.zebra.android.util.internal.CPCLUtilities;
import com.zebra.android.util.internal.StringUtilities;

// Referenced classes of package com.zebra.android.printer:
//            ZebraPrinterLanguageUnknownException, PrinterLanguage, ZebraPrinter

public class ZebraPrinterFactory
{

    private ZebraPrinterFactory()
    {
    }

    public static ZebraPrinter getInstance(ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException, ZebraPrinterLanguageUnknownException
    {
        return getInstance(lookForPrinter(zebraprinterconnection, CPCLUtilities.VERSION_PREFIXES), zebraprinterconnection);
    }

    public static ZebraPrinter getInstance(PrinterLanguage printerlanguage, ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        Object obj;
        if(printerlanguage == PrinterLanguage.CPCL)
            obj = new ZebraPrinterCpcl(zebraprinterconnection);
        else
            obj = new ZebraPrinterZpl(zebraprinterconnection);
        return ((ZebraPrinter) (obj));
    }

    public static ZebraPrinter getInstance(String as[], ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException, ZebraPrinterLanguageUnknownException
    {
        return getInstance(lookForPrinter(zebraprinterconnection, as), zebraprinterconnection);
    }

    private static PrinterLanguage lookForPrinter(ZebraPrinterConnection zebraprinterconnection, String as[])
        throws ZebraPrinterConnectionException, ZebraPrinterLanguageUnknownException
    {
        if(!zebraprinterconnection.isConnected())
            throw new ZebraPrinterConnectionException("Connection is not open.");
        PrinterLanguage printerlanguage = PrinterLanguage.ZPL;
        String s = SGD.GET("appl.name", zebraprinterconnection);
        if(s.length() == 0)
            throw new ZebraPrinterLanguageUnknownException("Unable to determine printer language");
        if(StringUtilities.doesPrefixExistInArray(as, s))
            printerlanguage = PrinterLanguage.CPCL;
        return printerlanguage;
    }
}
