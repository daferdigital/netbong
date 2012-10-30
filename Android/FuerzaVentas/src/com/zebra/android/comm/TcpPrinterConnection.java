// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm;

import com.zebra.android.comm.internal.ZebraConnector;

// Referenced classes of package com.zebra.android.comm:
//            ZebraPrinterConnectionA, TcpZebraConnectorImpl

public class TcpPrinterConnection extends ZebraPrinterConnectionA
{

    protected TcpPrinterConnection(ZebraConnector zebraconnector, int i, int j)
    {
        zebraConnector = zebraconnector;
        maxTimeoutForRead = i;
        timeToWaitForMoreData = j;
    }

    public TcpPrinterConnection(String s, int i)
    {
        this(s, i, 5000, 500);
    }

    public TcpPrinterConnection(String s, int i, int j, int k)
    {
        this(((ZebraConnector) (new TcpZebraConnectorImpl(s, i))), j, k);
    }

    public String getAddress()
    {
        return ((TcpZebraConnectorImpl)zebraConnector).getAddress();
    }

    public String getPortNumber()
    {
        return String.valueOf(((TcpZebraConnectorImpl)zebraConnector).getPort());
    }

    public String toString()
    {
        return (new StringBuilder()).append("TCP:").append(getAddress()).append(":").append(getPortNumber()).toString();
    }

    public static final int DEFAULT_CPCL_TCP_PORT = 6101;
    public static final int DEFAULT_ZPL_TCP_PORT = 9100;
}
