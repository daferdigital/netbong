// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

// Referenced classes of package com.zebra.android.printer:
//            FieldDescriptionData

public interface FormatUtil
{

    public abstract FieldDescriptionData[] getVariableFields(String s);

    public abstract void printStoredFormat(String s, Map map)
        throws ZebraPrinterConnectionException;

    public abstract void printStoredFormat(String s, Map map, String s1)
        throws ZebraPrinterConnectionException, UnsupportedEncodingException;

    public abstract void printStoredFormat(String s, String as[])
        throws ZebraPrinterConnectionException;

    public abstract void printStoredFormat(String s, String as[], String s1)
        throws ZebraPrinterConnectionException, UnsupportedEncodingException;

    public abstract byte[] retrieveFormatFromPrinter(String s)
        throws ZebraPrinterConnectionException;
}
