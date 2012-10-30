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

public class FindPrinters extends BroadcastA
{

    public FindPrinters()
        throws DiscoveryException
    {
        super(6000);
    }

    protected boolean doDiscovery() {
    	boolean discovery = false;
    	
    	try {
    		broadcastIpAddresses = InetAddress.getAllByName("255.255.255.255");
            if(super.doDiscovery()) {
            	broadcastIpAddresses = InetAddress.getAllByName("224.0.1.55");
            	discovery = super.doDiscovery();
            }
		} catch (UnknownHostException e) {
			// TODO: handle exception
			discoveryHandler.discoveryError("Unknown host address");
		}
        
        return discovery;
    }

    protected void setSocketOptions(ZebraDiscoSocket zebradiscosocket)
        throws DiscoveryException
    {
        try
        {
            zebradiscosocket.setTimeToLive(3);
            return;
        }
        catch(IOException ioexception)
        {
            throw new DiscoveryException(ioexception.getMessage());
        }
    }

    private final String ESI_REGISTERED_MULTICAST_GROUP_ADDRESS = "224.0.1.55";
    final String LOCAL_BROADCAST_ADDRESS = "255.255.255.255";
}
