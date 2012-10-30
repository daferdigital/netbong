// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm;

import com.zebra.android.comm.internal.ZebraConnector;
import com.zebra.android.comm.internal.ZebraSocket;
import com.zebra.android.util.internal.Sleeper;
import java.io.*;

// Referenced classes of package com.zebra.android.comm:
//            ZebraPrinterConnection, ZebraPrinterConnectionException

public abstract class ZebraPrinterConnectionA
    implements ZebraPrinterConnection
{

    protected ZebraPrinterConnectionA()
    {
        maxTimeoutForRead = 0;
        timeToWaitForMoreData = 0;
        isConnected = false;
    }

    public int bytesAvailable()
        throws ZebraPrinterConnectionException
    {
        int i;
        try
        {
            i = inputStream.available();
        }
        catch(IOException ioexception)
        {
            throw new ZebraPrinterConnectionException(ioexception.getMessage());
        }
        return i;
    }

    public void close()
        throws ZebraPrinterConnectionException
    {
        if(isConnected)
        {
            isConnected = false;
            try
            {
                outputStream.close();
                inputStream.close();
                commLink.close();
            }
            catch(IOException ioexception)
            {
                throw new ZebraPrinterConnectionException((new StringBuilder()).append("Could not disconnect from printer: ").append(ioexception.getMessage()).toString());
            }
        }
    }

    public int getMaxTimeoutForRead()
    {
        return maxTimeoutForRead;
    }

    public int getTimeToWaitForMoreData()
    {
        return timeToWaitForMoreData;
    }

    public boolean isConnected()
    {
        return isConnected;
    }

    public void open()
        throws ZebraPrinterConnectionException
    {
        if(!isConnected) {
        	try
            {
                commLink = zebraConnector.open();
                outputStream = commLink.getOutputStream();
                inputStream = commLink.getInputStream();
                isConnected = true;
            }
            catch(Exception exception)
            {
                isConnected = false;
                throw new ZebraPrinterConnectionException((new StringBuilder()).append("Could not connect to printer: ").append(exception.getMessage()).toString());
            }
        }
    }

    public byte[] read()
        throws ZebraPrinterConnectionException
    {
        int i = bytesAvailable();
        byte abyte0[];
        if(i > 0)
        {
            abyte0 = new byte[i];
            try
            {
                inputStream.read(abyte0);
            }
            catch(IOException ioexception)
            {
                throw new ZebraPrinterConnectionException(ioexception.getMessage());
            }
        } else
        {
            abyte0 = null;
        }
        return abyte0;
    }

    public void waitForData(int i)
        throws ZebraPrinterConnectionException
    {
        for(int j = 0; bytesAvailable() == 0 && j < i; j += 50)
            Sleeper.sleep(50L);

    }

    public void write(byte abyte0[])
        throws ZebraPrinterConnectionException
    {
        write(abyte0, 0, abyte0.length);
    }

    public void write(byte abyte0[], int i, int j)
        throws ZebraPrinterConnectionException
    {
        int k = j;
        int l = i;
        
        if(outputStream == null || !isConnected()) {
        	throw new ZebraPrinterConnectionException("The connection is not open");
        } else {
        	while(k > 0){
        		try {
        			int i1 = (k > MAX_DATA_TO_WRITE_TO_STREAM_AT_ONCE ? MAX_DATA_TO_WRITE_TO_STREAM_AT_ONCE : k);
            		
            		outputStream.write(abyte0, l, i1);
                    outputStream.flush();
                    Sleeper.sleep(10L);
                    l += i1;
                    k -= i1;
				} catch (Exception e) {
					// TODO: handle exception
					throw new ZebraPrinterConnectionException((new StringBuilder()).append("Error writing to connection: ").append(e.getMessage()).toString());
				}
        	}
        }
    }

    protected static final int DEFAULT_MAX_TIMEOUT_FOR_READ = 5000;
    protected static final int DEFAULT_TIME_TO_WAIT_FOR_MORE_DATA = 500;
    private static int MAX_DATA_TO_WRITE_TO_STREAM_AT_ONCE = 1024;
    protected ZebraSocket commLink;
    protected InputStream inputStream;
    protected boolean isConnected;
    protected int maxTimeoutForRead;
    protected OutputStream outputStream;
    protected int timeToWaitForMoreData;
    protected ZebraConnector zebraConnector;

}
