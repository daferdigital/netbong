// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.GraphicsUtil;

public abstract class GraphicsUtilA
    implements GraphicsUtil
{

    public GraphicsUtilA()
    {
    }

    public static Bitmap getImage(String s)
        throws ZebraPrinterConnectionException
    {
        return BitmapFactory.decodeFile(s);
    }

    public void printImage(String s, int i, int j)
        throws ZebraPrinterConnectionException
    {
        printImage(s, i, j, -1, -1, false);
    }

    public void printImage(String s, int i, int j, int k, int l, boolean flag)
        throws ZebraPrinterConnectionException
    {
        printImage(getImage(s), i, j, k, l, flag);
    }

    protected Bitmap scaleImage(int i, int j, Bitmap bitmap)
    {
        if(i > 0 && j > 0)
            bitmap = Bitmap.createScaledBitmap(bitmap, i, j, true);
        return bitmap;
    }
}
