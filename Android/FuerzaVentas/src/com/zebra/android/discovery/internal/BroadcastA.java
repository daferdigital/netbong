// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import com.zebra.android.discovery.*;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.zebra.android.discovery.internal:
//            ZebraDiscoSocket, DiscoveryPacketDecodeException, DiscoveryPacketDecoder, ZebraDiscoSocketImpl

public abstract class BroadcastA
{

    protected BroadcastA(int i)
    {
        waitForResponsesTimeout = 6000;
        waitForResponsesTimeout = i;
        discoveredPrinters = new HashMap();
    }

    private ZebraDiscoSocket createDiscoverySocket()
        throws DiscoveryException, SocketException, UnknownHostException
    {
        ZebraDiscoSocket zebradiscosocket = createDiscoSocket();
        setSocketOptions(zebradiscosocket);
        zebradiscosocket.setSoTimeout(waitForResponsesTimeout);
        return zebradiscosocket;
    }

    private void getDiscoveryResponses(ZebraDiscoSocket zebradiscosocket, DatagramPacket datagrampacket)
        throws DiscoveryPacketDecodeException, IOException
    {
        do
            try
            {
                int i;
                String s;
                do
                {
                    zebradiscosocket.receive(datagrampacket);
                    i = getPortBasedOnProductName(datagrampacket.getData());
                    s = getIpAddressFromDiscoveryPacket(datagrampacket.getData());
                } while(discoveredPrinters.containsKey(s));
                DiscoveredPrinterNetwork discoveredprinternetwork = new DiscoveredPrinterNetwork(s, i);
                discoveredprinternetwork.dnsName = getDnsNameFromDiscoveryPacket(datagrampacket.getData());
                discoveryHandler.foundPrinter(discoveredprinternetwork);
                discoveredPrinters.put(s, discoveredprinternetwork.dnsName);
            }
            catch(IOException ioexception)
            {
                return;
            }
        while(true);
    }

    protected static String getDnsNameFromDiscoveryPacket(byte abyte0[])
        throws DiscoveryPacketDecodeException
    {
        return (new DiscoveryPacketDecoder(abyte0)).getDnsName();
    }

    protected static String getIpAddressFromDiscoveryPacket(byte abyte0[])
        throws DiscoveryPacketDecodeException
    {
        return (new DiscoveryPacketDecoder(abyte0)).getIpAddress();
    }

    protected static int getPortBasedOnProductName(byte abyte0[])
        throws DiscoveryPacketDecodeException
    {
        return (new DiscoveryPacketDecoder(abyte0)).getPrinterPort();
    }

    private void sendDiscoveryRequest(ZebraDiscoSocket zebradiscosocket, DatagramPacket datagrampacket)
        throws IOException
    {
        for(int i = 0; i < broadcastIpAddresses.length; i++)
        {
            datagrampacket.setSocketAddress(new InetSocketAddress(broadcastIpAddresses[i], 4201));
            zebradiscosocket.send(datagrampacket);
        }

    }

    private void startDiscoveryInBackground()
    {
        (new Thread(new _cls1())).start();
    }

    protected ZebraDiscoSocket createDiscoSocket()
        throws DiscoveryException
    {
        return new ZebraDiscoSocketImpl();
    }

    public void doBroadcast(DiscoveryHandler discoveryhandler)
        throws DiscoveryException
    {
        if(discoveryhandler == null)
        {
            throw new DiscoveryException("A DiscoveryHandler must be supplied");
        } else
        {
            discoveryHandler = discoveryhandler;
            startDiscoveryInBackground();
            return;
        }
    }

    protected boolean doDiscovery() {
    	boolean discover = false;
    	ZebraDiscoSocket zebradiscosocket = null;
    	
    	try {
    		zebradiscosocket = createDiscoverySocket();
            DatagramPacket datagrampacket = new DatagramPacket(DISCOVERY_REQUEST_PACKET, DISCOVERY_REQUEST_PACKET.length);
            byte abyte0[] = new byte[600];
            DatagramPacket datagrampacket1 = new DatagramPacket(abyte0, abyte0.length);
            sendDiscoveryRequest(zebradiscosocket, datagrampacket);
            getDiscoveryResponses(zebradiscosocket, datagrampacket1);
            discover = true;
    	} catch (IOException e) {
    		discoveryHandler.discoveryError(e.getMessage());
    	} catch (DiscoveryPacketDecodeException e) {
			// TODO Auto-generated catch block
			discoveryHandler.discoveryError(e.getMessage());
		} catch (DiscoveryException e) {
			// TODO Auto-generated catch block
			discoveryHandler.discoveryError(e.getMessage());
		} finally {
			try {
				zebradiscosocket.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
        
    	return discover;
    }

    protected abstract void setSocketOptions(ZebraDiscoSocket zebradiscosocket)
        throws DiscoveryException;

    protected static final int DEFAULT_LATE_ARRIVAL_DELAY = 6000;
    private static byte DISCOVERY_REQUEST_PACKET[];
    public static final int MAX_DATAGRAM_SIZE = 600;
    private final int DISCOVERY_PORT = 4201;
    protected InetAddress broadcastIpAddresses[];
    private Map discoveredPrinters;
    protected DiscoveryHandler discoveryHandler;
    private int waitForResponsesTimeout;

    static 
    {
        byte abyte0[] = new byte[6];
        abyte0[0] = 46;
        abyte0[1] = 44;
        abyte0[2] = 58;
        abyte0[3] = 1;
        abyte0[4] = 0;
        abyte0[5] = 0;
        DISCOVERY_REQUEST_PACKET = abyte0;
    }

    private class _cls1
        implements Runnable
    {

        public void run()
        {
            if(doDiscovery())
                discoveryHandler.discoveryFinished();
        }

        final BroadcastA this$0;

        _cls1() {
        	super();
        	this$0 = BroadcastA.this;
        }
    }

}
