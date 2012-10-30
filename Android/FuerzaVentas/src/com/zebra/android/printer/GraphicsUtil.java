// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import android.graphics.Bitmap;
import com.zebra.android.comm.ZebraPrinterConnectionException;

// Referenced classes of package com.zebra.android.printer:
//            ZebraIllegalArgumentException

public interface GraphicsUtil
{

    public abstract void printImage(Bitmap bitmap, int i, int j, int k, int l, boolean flag)
        throws ZebraPrinterConnectionException;

    public abstract void printImage(String s, int i, int j)
        throws ZebraPrinterConnectionException;

    public abstract void printImage(String s, int i, int j, int k, int l, boolean flag)
        throws ZebraPrinterConnectionException;

    public abstract void storeImage(String s, Bitmap bitmap, int i, int j)
        throws ZebraPrinterConnectionException, ZebraIllegalArgumentException;
}
