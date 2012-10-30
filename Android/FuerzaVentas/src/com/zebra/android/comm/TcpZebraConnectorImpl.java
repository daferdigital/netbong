// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm;

import com.zebra.android.comm.internal.ZebraConnector;
import com.zebra.android.comm.internal.ZebraNetworkSocket;
import com.zebra.android.comm.internal.ZebraSocket;
import java.io.IOException;

// Referenced classes of package com.zebra.android.comm:
//            ZebraPrinterConnectionException

class TcpZebraConnectorImpl
    implements ZebraConnector
{

    public TcpZebraConnectorImpl(String s, int i)
    {
        address = s;
        port = i;
    }

    String getAddress()
    {
        return address;
    }

    int getPort()
    {
        return port;
    }

    public ZebraSocket open()
        throws ZebraPrinterConnectionException
    {
        ZebraNetworkSocket zebranetworksocket;
        try
        {
            zebranetworksocket = new ZebraNetworkSocket(address, port);
            zebranetworksocket.connect();
        }
        catch(IOException ioexception)
        {
            throw new ZebraPrinterConnectionException(ioexception.getMessage());
        }
        return zebranetworksocket;
    }

    private String address;
    private int port;
}
