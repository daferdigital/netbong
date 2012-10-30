// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import com.zebra.android.discovery.DiscoveryException;
import java.net.InetAddress;
import java.net.UnknownHostException;

// Referenced classes of package com.zebra.android.discovery.internal:
//            BroadcastA, ZebraDiscoSocket

public class LocalBroadcast extends BroadcastA
{

    public LocalBroadcast()
        throws DiscoveryException
    {
        this(6000);
    }

    public LocalBroadcast(int i)
        throws DiscoveryException
    {
        super(i);
        LOCAL_BROADCAST_ADDRESS = "255.255.255.255";
        try
        {
            broadcastIpAddresses = InetAddress.getAllByName("255.255.255.255");
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
    }

    private final String LOCAL_BROADCAST_ADDRESS;
}
