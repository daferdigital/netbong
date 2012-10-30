// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;

import com.zebra.android.printer.ZebraIllegalArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.zebra.android.util.internal:
//            PrinterFilePath

public class FileUtilities
{

    public FileUtilities()
    {
    }

    public static PrinterFilePath parseDriveAndExtension(String s)
        throws ZebraIllegalArgumentException
    {
        Pattern pattern = Pattern.compile("^(([A-Za-z]+):)?([^.:]+)(\\.[^.]{0,3})?$");
        if(s == null || s.length() <= 0)
            throw new ZebraIllegalArgumentException("Incorrect file name : ");
        Matcher matcher = pattern.matcher(s);
        if(matcher.matches())
            return new PrinterFilePath(matcher.group(2), matcher.group(3));
        else
            throw new ZebraIllegalArgumentException((new StringBuilder()).append("Incorrect file name : ").append(s).toString());
    }
}
