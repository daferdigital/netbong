// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.FormatUtil;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public abstract class FormatUtilA
    implements FormatUtil
{

    public FormatUtilA(ZebraPrinterConnection zebraprinterconnection)
    {
        printerConnection = zebraprinterconnection;
    }

    public void printStoredFormat(String s, String as[])
        throws ZebraPrinterConnectionException {
        
    	try {
			printStoredFormat(s, as, System.getProperty("file.encoding"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void printStoredFormat(String s, String as[], String s1)
        throws ZebraPrinterConnectionException, UnsupportedEncodingException
    {
        HashMap hashmap = new HashMap();
        for(int i = 0; i < as.length; i++)
            if(as[i] != null)
                hashmap.put(Integer.valueOf(i + 2), as[i]);

        printStoredFormat(s, ((Map) (hashmap)), s1);
    }

    protected ZebraPrinterConnection printerConnection;
}
