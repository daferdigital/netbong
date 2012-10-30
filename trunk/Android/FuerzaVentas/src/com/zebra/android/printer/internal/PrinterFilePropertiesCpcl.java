// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.printer.ZebraIllegalArgumentException;

// Referenced classes of package com.zebra.android.printer.internal:
//            PrinterFileProperties

public class PrinterFilePropertiesCpcl extends PrinterFileProperties
{

    public PrinterFilePropertiesCpcl(String s)
        throws ZebraIllegalArgumentException
    {
        int i = s.indexOf(".");
        try
        {
            drivePrefix = "";
            fileName = s.substring(0, i).trim();
            extension = s.substring(i + 1, i + 4);
            return;
        }
        catch(IndexOutOfBoundsException indexoutofboundsexception)
        {
            String s1 = (new StringBuilder()).append(" index is ").append(i).toString();
            throw new ZebraIllegalArgumentException((new StringBuilder()).append("Badly formed file directory entry").append(s1).toString());
        }
    }
}
