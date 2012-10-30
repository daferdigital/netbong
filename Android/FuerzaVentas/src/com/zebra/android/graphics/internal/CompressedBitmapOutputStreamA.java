// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.graphics.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.io.*;

public abstract class CompressedBitmapOutputStreamA extends OutputStream
{

    public CompressedBitmapOutputStreamA()
    {
    }

    protected void bufferAndWrite(char c)
        throws ZebraPrinterConnectionException
    {
        if(internalEncodedBuffer.size() < 1024)
            internalEncodedBuffer.write((byte)c);
        if(internalEncodedBuffer.size() == 1024)
        {
            printerConnection.write(internalEncodedBuffer.toByteArray());
            internalEncodedBuffer.reset();
        }
    }

    public void close()
        throws IOException
    {
        flush();
    }

    public void flush()
        throws IOException
    {
        if(internalEncodedBuffer.size() != 0)
        {
            try
            {
                printerConnection.write(internalEncodedBuffer.toByteArray());
            }
            catch(ZebraPrinterConnectionException zebraprinterconnectionexception)
            {
                throw new IOException(zebraprinterconnectionexception.getMessage());
            }
            internalEncodedBuffer.reset();
        }
    }

    public void write(int i)
        throws IOException
    {
        throw new IOException("This method is not implemented.");
    }

    private static final int INTERNAL_ENCODED_BUFFER_SIZE = 1024;
    protected ByteArrayOutputStream internalEncodedBuffer;
    protected ZebraPrinterConnection printerConnection;
}
