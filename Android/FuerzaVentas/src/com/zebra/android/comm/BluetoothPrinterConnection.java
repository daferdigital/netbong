// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.zebra.android.comm.internal.ZebraConnector;
import com.zebra.android.util.internal.Sleeper;

// Referenced classes of package com.zebra.android.comm:
//            ZebraPrinterConnectionA, BluetoothZebraConnectorImpl, ZebraPrinterConnectionException

public class BluetoothPrinterConnection extends ZebraPrinterConnectionA
{

    protected BluetoothPrinterConnection(ZebraConnector zebraconnector, String s, int i, int j)
    {
        friendlyName = "";
        zebraConnector = zebraconnector;
        macAddress = s;
        maxTimeoutForRead = i;
        timeToWaitForMoreData = j;
    }

    public BluetoothPrinterConnection(String s)
    {
        this(s, 5000, 500);
    }

    public BluetoothPrinterConnection(String s, int i, int j)
    {
        this(((ZebraConnector) (new BluetoothZebraConnectorImpl(formatMacAddress(s)))), formatMacAddress(s), i, j);
    }

    private static String formatMacAddress(String s)
    {
        String s1;
        if(s == null)
        {
            s1 = null;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            if(s.length() == 12)
            {
                stringbuilder.append(s.substring(0, 2));
                stringbuilder.append(":");
                stringbuilder.append(s.substring(2, 4));
                stringbuilder.append(":");
                stringbuilder.append(s.substring(4, 6));
                stringbuilder.append(":");
                stringbuilder.append(s.substring(6, 8));
                stringbuilder.append(":");
                stringbuilder.append(s.substring(8, 10));
                stringbuilder.append(":");
                stringbuilder.append(s.substring(10, 12));
            } else
            {
                stringbuilder.append(s);
            }
            s1 = stringbuilder.toString().toUpperCase();
        }
        return s1;
    }

    private String getFriendlyNameFromDevice()
        throws ZebraPrinterConnectionException
    {
        String s;
        try
        {
            s = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(macAddress).getName();
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ZebraPrinterConnectionException((new StringBuilder()).append("Error reading from connection: ").append(illegalargumentexception.getMessage()).toString());
        }
        return s;
    }

    public void close()
        throws ZebraPrinterConnectionException
    {
        Sleeper.sleep(5000L);
        friendlyName = "";
        super.close();
    }

    public String getFriendlyName()
    {
        return friendlyName;
    }

    public String getMACAddress()
    {
        return macAddress;
    }

    public void open()
        throws ZebraPrinterConnectionException
    {
        super.open();
        friendlyName = getFriendlyNameFromDevice();
    }

    public String toString()
    {
        return (new StringBuilder()).append("Bluetooth:").append(getMACAddress()).append(":").append(getFriendlyName()).toString();
    }

    protected String friendlyName;
    protected String macAddress;
}
