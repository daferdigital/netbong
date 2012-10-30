// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm;


// Referenced classes of package com.zebra.android.comm:
//            ZebraPrinterConnectionException

public interface ZebraPrinterConnection
{

    public abstract int bytesAvailable()
        throws ZebraPrinterConnectionException;

    public abstract void close()
        throws ZebraPrinterConnectionException;

    public abstract int getMaxTimeoutForRead();

    public abstract int getTimeToWaitForMoreData();

    public abstract boolean isConnected();

    public abstract void open()
        throws ZebraPrinterConnectionException;

    public abstract byte[] read()
        throws ZebraPrinterConnectionException;

    public abstract String toString();

    public abstract void waitForData(int i)
        throws ZebraPrinterConnectionException;

    public abstract void write(byte abyte0[])
        throws ZebraPrinterConnectionException;

    public abstract void write(byte abyte0[], int i, int j)
        throws ZebraPrinterConnectionException;
}
