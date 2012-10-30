// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.printer.ZebraIllegalArgumentException;

// Referenced classes of package com.zebra.android.printer.internal:
//            PrinterFileProperties

public class PrinterFilePropertiesZpl extends PrinterFileProperties
{

    public PrinterFilePropertiesZpl(String s)
        throws ZebraIllegalArgumentException
    {
        int i = s.indexOf(":");
        int j = s.indexOf(".");
        int k = i - 1;
        try
        {
            drivePrefix = s.substring(k, i).trim();
            drivePrefix = (new StringBuilder()).append(drivePrefix).append(":").toString();
            fileName = s.substring(i + 1, j).trim();
            extension = trimExtension(s.substring(j + 1, j + 4));
            return;
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new ZebraIllegalArgumentException("Badly formed file directory entry");
        }
        catch(IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new ZebraIllegalArgumentException("Badly formed file directory entry");
        }
    }

    private boolean isEndOfExtensionChar(char c)
    {
        boolean flag;
        if(c == ' ' || c == '\n' || c == '\t' || c == '\r')
            flag = true;
        else
            flag = false;
        return flag;
    }

    private String trimExtension(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = 0;
        do
        {
            char c;
label0:
            {
                if(i < s.length())
                {
                    c = s.charAt(i);
                    if(!isEndOfExtensionChar(c))
                        break label0;
                }
                return stringbuilder.toString();
            }
            stringbuilder.append(c);
            i++;
        } while(true);
    }

    private static final int END_OF_EXTENSION_OFFSET = 4;
    private static final int START_OF_EXTENSION_OFFSET = 1;
    private static final int START_OF_NAME_OFFSET = 1;
}
