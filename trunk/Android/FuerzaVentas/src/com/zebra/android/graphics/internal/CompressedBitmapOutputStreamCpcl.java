// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.graphics.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package com.zebra.android.graphics.internal:
//            CompressedBitmapOutputStreamA

public class CompressedBitmapOutputStreamCpcl extends CompressedBitmapOutputStreamA
{

    public CompressedBitmapOutputStreamCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        printerConnection = zebraprinterconnection;
        internalEncodedBuffer = new ByteArrayOutputStream();
    }

    public void write(byte abyte0[])
        throws IOException
    {
        int i = 0;
        while(i < abyte0.length) 
        {
            try
            {
                bufferAndWrite((char)(-1 ^ abyte0[i]));
            }
            catch(ZebraPrinterConnectionException zebraprinterconnectionexception)
            {
                throw new IOException(zebraprinterconnectionexception.getMessage());
            }
            i++;
        }
    }
}
