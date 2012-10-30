// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;


// Referenced classes of package com.zebra.android.util.internal:
//            StringUtilities

public class ImageConverter
{

    public ImageConverter()
    {
    }

    public static String bitmapToAscii(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < abyte0.length; i++)
            stringbuffer.append(StringUtilities.padWithChar(Integer.toHexString(0xff & (byte)(-1 ^ abyte0[i])), '0', 2, true));

        return stringbuffer.toString().toUpperCase();
    }
}
