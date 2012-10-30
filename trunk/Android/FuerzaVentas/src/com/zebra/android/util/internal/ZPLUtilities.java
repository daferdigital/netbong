// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;


public class ZPLUtilities
{

    public ZPLUtilities()
    {
    }

    public static String decorateWithCommandPrefix(String s)
    {
        String s1;
        if(s == null)
            s1 = null;
        else
        if(s.indexOf("~") != -1)
            s1 = s.replace('~', '\020');
        else
            s1 = (new StringBuilder()).append(ZPL_INTERNAL_COMMAND_PREFIX).append(s).toString();
        return s1;
    }

    public static String decorateWithFormatPrefix(String s)
    {
        String s1;
        if(s == null)
            s1 = null;
        else
        if(s.indexOf("^") != -1)
            s1 = s.replace('^', '\036');
        else
            s1 = (new StringBuilder()).append(ZPL_INTERNAL_FORMAT_PREFIX).append(s).toString();
        return s1;
    }

    public static final String PRINTER_CALIBRATE;
    public static final String PRINTER_CONFIG_LABEL;
    public static final String PRINTER_INFO;
    public static final String PRINTER_RESET;
    public static final String PRINTER_RESTORE_DEFAULTS;
    public static final String PRINTER_STATUS;
    public static final String ZPL_INTERNAL_COMMAND_PREFIX;
    public static final int ZPL_INTERNAL_COMMAND_PREFIX_CHAR = 16;
    public static final String ZPL_INTERNAL_DELIMITER;
    public static final int ZPL_INTERNAL_DELIMITER_CHAR = 31;
    public static final String ZPL_INTERNAL_FORMAT_PREFIX;
    public static final int ZPL_INTERNAL_FORMAT_PREFIX_CHAR = 30;

    static 
    {
        byte abyte0[] = new byte[1];
        abyte0[0] = 30;
        ZPL_INTERNAL_FORMAT_PREFIX = new String(abyte0);
        byte abyte1[] = new byte[1];
        abyte1[0] = 16;
        ZPL_INTERNAL_COMMAND_PREFIX = new String(abyte1);
        byte abyte2[] = new byte[1];
        abyte2[0] = 31;
        ZPL_INTERNAL_DELIMITER = new String(abyte2);
        byte abyte3[] = new byte[3];
        abyte3[0] = 16;
        abyte3[1] = 72;
        abyte3[2] = 73;
        PRINTER_INFO = new String(abyte3);
        byte abyte4[] = new byte[3];
        abyte4[0] = 16;
        abyte4[1] = 72;
        abyte4[2] = 83;
        PRINTER_STATUS = new String(abyte4);
        byte abyte5[] = new byte[3];
        abyte5[0] = 16;
        abyte5[1] = 87;
        abyte5[2] = 67;
        PRINTER_CONFIG_LABEL = new String(abyte5);
        byte abyte6[] = new byte[3];
        abyte6[0] = 16;
        abyte6[1] = 74;
        abyte6[2] = 67;
        PRINTER_CALIBRATE = new String(abyte6);
        byte abyte7[] = new byte[3];
        abyte7[0] = 16;
        abyte7[1] = 74;
        abyte7[2] = 82;
        PRINTER_RESET = new String(abyte7);
        byte abyte8[] = new byte[10];
        abyte8[0] = 30;
        abyte8[1] = 88;
        abyte8[2] = 65;
        abyte8[3] = 30;
        abyte8[4] = 74;
        abyte8[5] = 85;
        abyte8[6] = 70;
        abyte8[7] = 30;
        abyte8[8] = 88;
        abyte8[9] = 90;
        PRINTER_RESTORE_DEFAULTS = new String(abyte8);
    }
}
