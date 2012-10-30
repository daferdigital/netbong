// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm.internal;

import java.io.*;
import java.net.*;

// Referenced classes of package com.zebra.android.comm.internal:
//            ZebraSocket

public class ZebraNetworkSocket
    implements ZebraSocket
{

    public ZebraNetworkSocket(String s, int i)
        throws UnknownHostException
    {
        inetSocketAddress = new InetSocketAddress(InetAddress.getByName(s), i);
        socket = new Socket();
    }

    public void close()
        throws IOException
    {
        socket.close();
    }

    public void connect()
        throws IOException
    {
        if(socket.isConnected())
            socket.close();
        socket.connect(inetSocketAddress, 15000);
    }

    public InputStream getInputStream()
        throws IOException
    {
        return socket.getInputStream();
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return socket.getOutputStream();
    }

    private final int MAX_TIMEOUT = 15000;
    private InetSocketAddress inetSocketAddress;
    private Socket socket;
}
