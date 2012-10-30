// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package com.zebra.android.comm.internal:
//            PrinterCommand

public class PrinterCommandImpl
    implements PrinterCommand
{

    public PrinterCommandImpl(String s)
    {
        command = "";
        command = s;
    }

    public byte[] sendAndWaitForResponse(ZebraPrinterConnection zebraprinterconnection)
        throws ZebraPrinterConnectionException
    {
        return sendAndWaitForResponse(zebraprinterconnection, zebraprinterconnection.getMaxTimeoutForRead(), zebraprinterconnection.getTimeToWaitForMoreData());
    }

    public byte[] sendAndWaitForResponse(ZebraPrinterConnection zebraprinterconnection, int i, int j)
        throws ZebraPrinterConnectionException
    {
        if(!zebraprinterconnection.isConnected())
            throw new ZebraPrinterConnectionException("No Printer Connection");
        zebraprinterconnection.write(command.getBytes());
        zebraprinterconnection.waitForData(i);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        while(zebraprinterconnection.bytesAvailable() > 0) 
        {
            byte abyte0[] = zebraprinterconnection.read();
            try
            {
                bytearrayoutputstream.write(abyte0);
            }
            catch(IOException ioexception)
            {
                throw new ZebraPrinterConnectionException(ioexception.getMessage());
            }
            zebraprinterconnection.waitForData(j);
        }
        return bytearrayoutputstream.toByteArray();
    }

    private String command;
}
