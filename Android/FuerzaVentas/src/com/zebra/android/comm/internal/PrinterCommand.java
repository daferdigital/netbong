// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;

public interface PrinterCommand
{

    public abstract byte[] sendAndWaitForResponse(ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException;

    public abstract byte[] sendAndWaitForResponse(ZebraPrinterConnection zebraprinterconnection, int i, int j)
        throws ZebraPrinterConnectionException;
}
