// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.io.*;

// Referenced classes of package com.zebra.android.printer.internal:
//            ZebraFileConnection

public class ZebraFileConnectionImpl
    implements ZebraFileConnection
{

    public ZebraFileConnectionImpl(String s)
        throws ZebraPrinterConnectionException
    {
        decoratedFileConnection = null;
        decoratedFileConnection = new File(s);
    }

    public void close()
        throws IOException
    {
    }

    public int fileSize()
        throws ZebraPrinterConnectionException
    {
        return (int)decoratedFileConnection.length();
    }

    public InputStream openInputStream()
        throws IOException
    {
        FileInputStream fileinputstream;
        try
        {
            fileinputstream = new FileInputStream(decoratedFileConnection);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            throw new IOException(filenotfoundexception.getMessage());
        }
        return fileinputstream;
    }

    private File decoratedFileConnection;
}
