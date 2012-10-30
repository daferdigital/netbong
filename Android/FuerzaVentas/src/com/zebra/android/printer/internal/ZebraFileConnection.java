// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.io.IOException;
import java.io.InputStream;

public interface ZebraFileConnection
{

    public abstract void close()
        throws IOException;

    public abstract int fileSize()
        throws ZebraPrinterConnectionException;

    public abstract InputStream openInputStream()
        throws IOException;
}
