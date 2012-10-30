// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery;


// Referenced classes of package com.zebra.android.discovery:
//            DiscoveredPrinter

public class DiscoveredPrinterBluetooth extends DiscoveredPrinter
{

    public DiscoveredPrinterBluetooth(String s, String s1)
    {
        super(s);
        friendlyName = s1;
    }

    public final String friendlyName;
}
