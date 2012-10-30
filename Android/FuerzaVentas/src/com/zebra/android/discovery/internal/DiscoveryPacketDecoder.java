// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import java.util.HashMap;

// Referenced classes of package com.zebra.android.discovery.internal:
//            DiscoveryPacketDecodeException

public class DiscoveryPacketDecoder
{

    public DiscoveryPacketDecoder(byte abyte0[])
    {
        getDiscoveryStatus(abyte0);
    }

    private static int byte2int(byte byte0)
    {
        int i = byte0;
        if(i < 0)
            i += 256;
        return i;
    }

    private void getDiscoveryStatus(byte abyte0[])
    {
        discoveryEntries = new HashMap();
        if(abyte0 != null && abyte0.length >= 105)
        {
            discoveryEntries.put("Product-Number", parseGeneralString(abyte0, offsets[0], offsets[1] - offsets[0]));
            int i = 0 + 1;
            discoveryEntries.put("Product-Name", parseGeneralString(abyte0, offsets[i], offsets[2] - offsets[i]));
            int j = i + 1;
            discoveryEntries.put("Datecode", parseGeneralString(abyte0, offsets[j], offsets[3] - offsets[j]));
            int k = j + 1;
            discoveryEntries.put("F/W-Version", parseGeneralString(abyte0, offsets[k], offsets[4] - offsets[k]));
            int l = 1 + (k + 1);
            discoveryEntries.put("H/W-Address", parseGeneralByte(abyte0, offsets[l], offsets[6] - offsets[l]));
            int i1 = l + 1;
            discoveryEntries.put("Serial-Number", parseGeneralString(abyte0, offsets[i1], offsets[7] - offsets[i1]));
            int j1 = i1 + 1;
            discoveryEntries.put("Using-Net-Protocol", parseBoolean(abyte0, offsets[j1], offsets[8] - offsets[j1]));
            int k1 = j1 + 1;
            discoveryEntries.put("IP-Address", parseAddress(abyte0, offsets[k1], offsets[9] - offsets[k1]));
            int l1 = k1 + 1;
            discoveryEntries.put("Subnet-Mask", parseAddress(abyte0, offsets[l1], offsets[10] - offsets[l1]));
            int i2 = l1 + 1;
            discoveryEntries.put("Default-Gateway", parseAddress(abyte0, offsets[i2], offsets[11] - offsets[i2]));
            int j2 = i2 + 1;
            discoveryEntries.put("System-Name", parseGeneralString(abyte0, offsets[j2], offsets[12] - offsets[j2]));
            int k2 = 1 + (j2 + 1);
            discoveryEntries.put("Port-Status", parseStatus(abyte0, offsets[k2], 1));
            int l2 = k2 + 1;
            discoveryEntries.put("Port-Name", parseGeneralString(abyte0, offsets[l2], offsets[15] - offsets[l2]));
        }
    }

    private static String parseAddress(byte abyte0[], int i, int j)
    {
        StringBuffer stringbuffer = new StringBuffer("");
        for(int k = i; k < i + j; k++)
        {
            stringbuffer.append(Integer.toString(byte2int(abyte0[k])));
            if(k + 1 != i + j)
                stringbuffer.append(".");
        }

        return stringbuffer.toString();
    }

    private static String parseBoolean(byte abyte0[], int i, int j) {
        int k = i;
        String s = "FALSE";
        
        while(k < i + j){
        	if(abyte0[i] == 1){
        		s = "TRUE";
        		break;
        	}
        	
        	k++;
        }
        
        return s;
    }

    private static String parseGeneralByte(byte abyte0[], int i, int j)
    {
        StringBuffer stringbuffer = new StringBuffer("");
        for(int k = i; k < i + j; k++)
        {
            String s = Integer.toHexString(byte2int(abyte0[k])).toUpperCase();
            if(s.length() == 1)
                stringbuffer.append("0");
            stringbuffer.append(s);
        }

        return stringbuffer.toString();
    }

    private static String parseGeneralString(byte abyte0[], int i, int j)
    {
        StringBuffer stringbuffer = new StringBuffer("");
        int k = i;
        
        while(! (k >= i + j)){
        	if(abyte0[k] != 0){
        		char ac[] = new char[1];
                ac[0] = (char)abyte0[k];
                stringbuffer.append(new String(ac));
        	}
        	
        	k++;
        }
        
        return stringbuffer.toString();
    }

    private static String parseStatus(byte abyte0[], int i, int j)
    {
        String s;
        try
        {
            s = STATUS_ENUM[byte2int(abyte0[i])];
        }
        catch(Exception exception)
        {
            s = STATUS_ENUM[0];
        }
        return s;
    }

    public String getDiscoveryEntryValue(String s)
        throws DiscoveryPacketDecodeException
    {
        if(discoveryEntries.size() == 0)
            throw new DiscoveryPacketDecodeException("Unable to parse the supplied discovery packet likely due to an invalid discovery packet length");
        if(!discoveryEntries.containsKey(s))
            throw new DiscoveryPacketDecodeException((new StringBuilder()).append("Entry key ").append(s).append(" is not a valid discovery packet entry").toString());
        else
            return (String)discoveryEntries.get(s);
    }

    public String getDnsName()
        throws DiscoveryPacketDecodeException
    {
        return getDiscoveryEntryValue("System-Name");
    }

    public String getIpAddress()
        throws DiscoveryPacketDecodeException
    {
        return getDiscoveryEntryValue("IP-Address");
    }

    public int getPrinterPort()
        throws DiscoveryPacketDecodeException
    {
        String s = getDiscoveryEntryValue("Product-Name");
        char c = '\u238C';
        if(s.startsWith("QL") || s.startsWith("RW") || s.startsWith("MZ") || s.startsWith("P4T") || s.startsWith("MQ") || s.startsWith("MU"))
            c = '\u17D5';
        return c;
    }

    private static final int MIN_PACKET_SIZE = 105;
    private static final String STATUS_ENUM[];
    private static final int offsets[];
    private HashMap discoveryEntries;

    static 
    {
        int ai[] = new int[17];
        ai[0] = 4;
        ai[1] = 12;
        ai[2] = 32;
        ai[3] = 39;
        ai[4] = 49;
        ai[5] = 54;
        ai[6] = 60;
        ai[7] = 70;
        ai[8] = 72;
        ai[9] = 76;
        ai[10] = 80;
        ai[11] = 84;
        ai[12] = 109;
        ai[13] = 358;
        ai[14] = 359;
        ai[15] = 375;
        ai[16] = 0;
        offsets = ai;
        String as[] = new String[16];
        as[0] = "None";
        as[1] = "Online";
        as[2] = "Offline";
        as[3] = "Toner-Low";
        as[4] = "Paper-Out";
        as[5] = "Paper-Jammed";
        as[6] = "Door-Open";
        as[7] = "Unknown";
        as[8] = "Unknown";
        as[9] = "Unknown";
        as[10] = "Unknown";
        as[11] = "Unknown";
        as[12] = "Unknown";
        as[13] = "Unknown";
        as[14] = "Unknown";
        as[15] = "Printer-Error";
        STATUS_ENUM = as;
    }
}
