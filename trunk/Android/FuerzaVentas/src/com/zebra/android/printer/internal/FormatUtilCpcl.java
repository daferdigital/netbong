// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.FieldDescriptionData;
import com.zebra.android.sgd.SGD;
import com.zebra.android.util.internal.StringUtilities;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package com.zebra.android.printer.internal:
//            FormatUtilA

public class FormatUtilCpcl extends FormatUtilA
{

    public FormatUtilCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        super(zebraprinterconnection);
    }

    protected int countVariableFields(String s)
    {
        return StringUtilities.countSubstringOccurences(s, "\\\\");
    }

    public FieldDescriptionData[] getVariableFields(String s)
    {
        FieldDescriptionData afielddescriptiondata[] = new FieldDescriptionData[countVariableFields(s)];
        for(int i = 0; i < afielddescriptiondata.length; i++)
            afielddescriptiondata[i] = new FieldDescriptionData(i + 1, null);

        return afielddescriptiondata;
    }

    public void printStoredFormat(String s, Map map)
        throws ZebraPrinterConnectionException
    {
        try {
        	printStoredFormat(s, map, System.getProperty("file.encoding"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    public void printStoredFormat(String s, Map map, String s1)
        throws ZebraPrinterConnectionException, UnsupportedEncodingException
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("! UF ");
        stringbuffer.append(s);
        stringbuffer.append("\r\n");
        for(Iterator iterator = map.keySet().iterator(); iterator.hasNext(); stringbuffer.append("\r\n"))
            stringbuffer.append((String)map.get(iterator.next()));

        printerConnection.write(stringbuffer.toString().getBytes(s1));
    }

    public byte[] retrieveFormatFromPrinter(String s)
        throws ZebraPrinterConnectionException
    {
        return SGD.DO("file.type", s, printerConnection).getBytes();
    }
}
