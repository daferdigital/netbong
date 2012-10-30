// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import com.zebra.android.discovery.DiscoveryException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

// Referenced classes of package com.zebra.android.discovery.internal:
//            BroadcastA, ZebraDiscoSocket

public class MulticastBroadcast extends BroadcastA
{

    public MulticastBroadcast(int i)
        throws DiscoveryException
    {
        this(i, 6000);
    }

    public MulticastBroadcast(int i, int j)
        throws DiscoveryException
    {
        super(j);
        ESI_REGISTERED_MULTICAST_GROUP_ADDRESS = "224.0.1.55";
        hops = 0;
        if(i < 0)
            throw new DiscoveryException((new StringBuilder()).append(i).append(" is an invalid multicast hop argument").toString());
        hops = i;
        try
        {
            broadcastIpAddresses = InetAddress.getAllByName("224.0.1.55");
            return;
        }
        catch(UnknownHostException unknownhostexception)
        {
            throw new DiscoveryException(unknownhostexception.getMessage());
        }
    }

    protected void setSocketOptions(ZebraDiscoSocket zebradiscosocket)
        throws DiscoveryException
    {
        try
        {
            zebradiscosocket.joinGroup("224.0.1.55");
            zebradiscosocket.setTimeToLive(hops);
            return;
        }
        catch(IOException ioexception)
        {
            throw new DiscoveryException(ioexception.getMessage());
        }
    }

    private final String ESI_REGISTERED_MULTICAST_GROUP_ADDRESS;
    private int hops;
}
