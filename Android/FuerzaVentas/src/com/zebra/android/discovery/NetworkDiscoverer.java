// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery;

import com.zebra.android.discovery.internal.BroadcastA;
import com.zebra.android.discovery.internal.DirectedBroadcast;
import com.zebra.android.discovery.internal.FindPrinters;
import com.zebra.android.discovery.internal.LocalBroadcast;
import com.zebra.android.discovery.internal.MulticastBroadcast;
import com.zebra.android.discovery.internal.SubnetSearch;

// Referenced classes of package com.zebra.android.discovery:
//            DiscoveryException, DiscoveryHandler

public class NetworkDiscoverer
{

    private NetworkDiscoverer()
    {
    }

    public static void directedBroadcast(DiscoveryHandler discoveryhandler, String s)
        throws DiscoveryException
    {
        (new DirectedBroadcast(s)).doBroadcast(discoveryhandler);
    }

    public static void directedBroadcast(DiscoveryHandler discoveryhandler, String s, int i)
        throws DiscoveryException
    {
        (new DirectedBroadcast(s, i)).doBroadcast(discoveryhandler);
    }

    public static void findPrinters(DiscoveryHandler discoveryhandler)
        throws DiscoveryException
    {
        (new FindPrinters()).doBroadcast(discoveryhandler);
    }

    public static void localBroadcast(DiscoveryHandler discoveryhandler)
        throws DiscoveryException
    {
        (new LocalBroadcast()).doBroadcast(discoveryhandler);
    }

    public static void localBroadcast(DiscoveryHandler discoveryhandler, int i)
        throws DiscoveryException
    {
        (new LocalBroadcast(i)).doBroadcast(discoveryhandler);
    }

    public static void _mthmulticast(DiscoveryHandler discoveryhandler, int i)
        throws DiscoveryException
    {
        (new MulticastBroadcast(i)).doBroadcast(discoveryhandler);
    }

    public static void _mthmulticast(DiscoveryHandler discoveryhandler, int i, int j)
        throws DiscoveryException
    {
        (new MulticastBroadcast(i, j)).doBroadcast(discoveryhandler);
    }

    public static void subnetSearch(DiscoveryHandler discoveryhandler, String s)
        throws DiscoveryException
    {
        (new SubnetSearch(s)).doBroadcast(discoveryhandler);
    }

    public static void subnetSearch(DiscoveryHandler discoveryhandler, String s, int i)
        throws DiscoveryException
    {
        (new SubnetSearch(s, i)).doBroadcast(discoveryhandler);
    }
}
