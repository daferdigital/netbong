// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import com.zebra.android.discovery.DiscoveryException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.zebra.android.discovery.internal:
//            BroadcastA, ZebraDiscoSocket

public class SubnetSearch extends BroadcastA
{

    public SubnetSearch(String s)
        throws DiscoveryException
    {
        this(s, 6000);
    }

    public SubnetSearch(String s, int i)
        throws DiscoveryException
    {
        super(i);
        broadcastIpAddresses = getAddressesToSearch(s);
    }

    private static InetAddress[] createSearchList(Matcher matcher)
        throws DiscoveryException
    {
        String s = matcher.group(2);
        if(s == null)
            throw new DiscoveryException("Malformed subnet search address");
        int i = 1;
        int j = 254;
        if(!s.equals("*"))
        {
            i = setLowValue(matcher.group(4));
            j = setHighValue(matcher.group(6), i);
            if(!isRangeValid(i, j))
                throw new DiscoveryException("Malformed subnet search address");
        }
        ArrayList arraylist = new ArrayList();
        int k = i;
        while(k <= j) 
        {
            try
            {
                arraylist.add(InetAddress.getByName((new StringBuilder()).append(matcher.group(1)).append(".").append(k).toString()));
            }
            catch(UnknownHostException unknownhostexception)
            {
                throw new DiscoveryException("Malformed subnet search address");
            }
            k++;
        }
        return (InetAddress[])arraylist.toArray(new InetAddress[0]);
    }

    private static InetAddress[] getAddressesToSearch(String s)
        throws DiscoveryException
    {
        if(s == null)
            throw new DiscoveryException("Malformed subnet search address");
        Matcher matcher = Pattern.compile("^([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\.?((([0-9]{1,3})(\\-([0-9]{1,3}|\\*))?)|\\*)?$").matcher(s);
        if(matcher.find())
            return createSearchList(matcher);
        else
            throw new DiscoveryException("Malformed subnet search address");
    }

    private static boolean isHighValueIsPresent(String s)
    {
        boolean flag;
        if(s != null && s.length() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isRangeValid(int i, int j) {
        boolean flag = true;
        final int x = 1;
        
        if(i < x || i > 254 || j < x || j > 254 || i > j) {
        	flag = false;
        }
        
        return flag;
    }

    private static int setHighValue(String s, int i) {
        int j;
        if(isHighValueIsPresent(s))
            j = setHighValueWhichIsPresent(s);
        else
            j = i;
        return j;
    }

    private static int setHighValueWhichIsPresent(String s)
    {
        int i;
        if(s.equals("*"))
            i = 254;
        else
            i = Integer.valueOf(s).intValue();
        return i;
    }

    private static int setLowValue(String s)
        throws DiscoveryException
    {
        if(s == null)
            throw new DiscoveryException("Malformed subnet search address");
        else
            return Integer.valueOf(s).intValue();
    }

    protected void setSocketOptions(ZebraDiscoSocket zebradiscosocket)
        throws DiscoveryException
    {
    }

    private static final String EXCEPTION_STRING = "Malformed subnet search address";
    private static final int IP_SEARCH_RANGE_HIGH = 254;
    private static final int IP_SEARCH_RANGE_LOW = 1;
}
