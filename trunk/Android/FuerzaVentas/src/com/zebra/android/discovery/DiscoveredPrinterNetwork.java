// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery;


// Referenced classes of package com.zebra.android.discovery:
//            DiscoveredPrinter

public class DiscoveredPrinterNetwork extends DiscoveredPrinter
{

    public DiscoveredPrinterNetwork(String s, int i)
    {
        super(s);
        port = i;
        dnsName = s;
    }

    public String dnsName;
    public final int port;
}
