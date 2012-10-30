// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import com.zebra.android.discovery.DiscoveryException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.zebra.android.discovery.internal:
//            BroadcastA, ZebraDiscoSocket

public class DirectedBroadcast extends BroadcastA
{

    public DirectedBroadcast(String s)
        throws DiscoveryException
    {
        this(s, 6000);
    }

    public DirectedBroadcast(String s, int i)
        throws DiscoveryException
    {
        super(i);
        try
        {
            broadcastIpAddresses = InetAddress.getAllByName(getDirectedBroadcastAddress(s));
            return;
        }
        catch(UnknownHostException unknownhostexception)
        {
            throw new DiscoveryException("Malformed directed broadcast address");
        }
    }

    private static String getDirectedBroadcastAddress(String s)
        throws DiscoveryException
    {
        if(s == null)
            throw new DiscoveryException("Malformed directed broadcast address");
        Matcher matcher = Pattern.compile("^([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\.?([0-9]{1,3})?$").matcher(s);
        if(matcher.find() && matcher.groupCount() > 1)
            return (new StringBuilder()).append(matcher.group(1)).append(".255").toString();
        else
            throw new DiscoveryException("Malformed directed broadcast address");
    }

    protected void setSocketOptions(ZebraDiscoSocket zebradiscosocket)
        throws DiscoveryException
    {
    }

    public String toString()
    {
        return broadcastIpAddresses[0].toString().substring(1);
    }
}
