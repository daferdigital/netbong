// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import com.zebra.android.discovery.DiscoveryException;
import java.io.IOException;
import java.net.*;

// Referenced classes of package com.zebra.android.discovery.internal:
//            ZebraDiscoSocket

public class ZebraDiscoSocketImpl
    implements ZebraDiscoSocket
{

    public ZebraDiscoSocketImpl()
        throws DiscoveryException
    {
        try
        {
            mySocket = new MulticastSocket();
            return;
        }
        catch(IOException ioexception)
        {
            throw new DiscoveryException(ioexception.getMessage());
        }
    }

    public void close()
    {
        mySocket.close();
    }

    public void joinGroup(String s)
        throws UnknownHostException, IOException
    {
        mySocket.joinGroup(InetAddress.getByName(s));
    }

    public void receive(DatagramPacket datagrampacket)
        throws IOException
    {
        mySocket.receive(datagrampacket);
    }

    public void send(DatagramPacket datagrampacket)
        throws IOException
    {
        mySocket.send(datagrampacket);
    }

    public void setInterface(InetAddress inetaddress)
        throws SocketException
    {
        mySocket.setInterface(inetaddress);
    }

    public void setSoTimeout(int i)
        throws SocketException
    {
        mySocket.setSoTimeout(i);
    }

    public void setTimeToLive(int i)
        throws IOException
    {
        mySocket.setTimeToLive(i);
    }

    private MulticastSocket mySocket;
}
