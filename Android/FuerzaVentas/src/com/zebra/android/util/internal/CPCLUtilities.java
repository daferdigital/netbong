// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;


public class CPCLUtilities
{

    public CPCLUtilities()
    {
    }

    private static final int ASCII_FF = 12;
    private static final int ASCII_H = 104;
    private static final int ASCII_V = 86;
    private static final int CPCL_ESC = 27;
    public static final String PRINTER_CONFIG_LABEL;
    public static final String PRINTER_FORM_FEED;
    public static final String PRINTER_STATUS;
    public static final String VERSION_PREFIXES[];

    static 
    {
        String as[] = new String[3];
        as[0] = "SH";
        as[1] = "H8";
        as[2] = "C";
        VERSION_PREFIXES = as;
        byte abyte0[] = new byte[2];
        abyte0[0] = 27;
        abyte0[1] = 104;
        PRINTER_STATUS = new String(abyte0);
        byte abyte1[] = new byte[2];
        abyte1[0] = 27;
        abyte1[1] = 86;
        PRINTER_CONFIG_LABEL = new String(abyte1);
        byte abyte2[] = new byte[1];
        abyte2[0] = 12;
        PRINTER_FORM_FEED = new String(abyte2);
    }
}
