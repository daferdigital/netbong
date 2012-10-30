// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.printer.ZebraIllegalArgumentException;
import com.zebra.android.util.internal.StringUtilities;

// Referenced classes of package com.zebra.android.printer.internal:
//            FileUtilA, PrinterFilePropertiesList, PrinterFilePropertiesZpl

public class FileUtilZpl extends FileUtilA
{

    public FileUtilZpl(ZebraPrinterConnection zebraprinterconnection)
    {
        super(zebraprinterconnection);
    }

    private boolean looksLineZPLFileLine(String s)
    {
        boolean flag = true;
        boolean flag1 = true;
        int i = s.indexOf(":");
        int j = s.indexOf(".");
        boolean flag2;
        if(i > 0)
            flag2 = flag;
        else
            flag2 = false;
        if(j <= i + 1)
            flag = false;
        if(flag2 && flag)
            flag1 = Character.isLetter(s.charAt(i - 1));
        return flag1;
    }

    public PrinterFilePropertiesList extractFilePropertiesFromDirResult(String s)
        throws ZebraIllegalArgumentException
    {
        PrinterFilePropertiesList printerfilepropertieslist = new PrinterFilePropertiesList();
        String as[] = StringUtilities.split(s, "\n");
        int i = 0;
        while(i < as.length) 
        {
            String s1 = as[i].trim();
            if(looksLineZPLFileLine(s1))
                try
                {
                    printerfilepropertieslist.add(new PrinterFilePropertiesZpl(s1));
                }
                catch(ZebraIllegalArgumentException zebraillegalargumentexception) { }
            i++;
        }
        return printerfilepropertieslist;
    }
}
