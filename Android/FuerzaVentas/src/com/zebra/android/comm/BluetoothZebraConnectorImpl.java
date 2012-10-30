// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm;

import android.bluetooth.*;
import com.zebra.android.comm.internal.ZebraBluetoothSocket;
import com.zebra.android.comm.internal.ZebraConnector;
import com.zebra.android.comm.internal.ZebraSocket;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

// Referenced classes of package com.zebra.android.comm:
//            ZebraPrinterConnectionException

class BluetoothZebraConnectorImpl
    implements ZebraConnector
{

    public BluetoothZebraConnectorImpl(String s)
    {
        macAddress = s;
    }

    private ZebraSocket tryPrivateApiWay()
        throws Exception
    {
        BluetoothDevice bluetoothdevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(macAddress);
        Class class1 = bluetoothdevice.getClass();
        Class aclass[] = new Class[1];
        aclass[0] = Integer.TYPE;
        Method method = class1.getMethod("createRfcommSocket", aclass);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(1);
        ZebraBluetoothSocket zebrabluetoothsocket = new ZebraBluetoothSocket((BluetoothSocket)method.invoke(bluetoothdevice, aobj));
        zebrabluetoothsocket.connect();
        return zebrabluetoothsocket;
    }

    private ZebraSocket tryPublicApiWay()
        throws ZebraPrinterConnectionException
    {
        ZebraBluetoothSocket zebrabluetoothsocket;
        try
        {
            zebrabluetoothsocket = new ZebraBluetoothSocket(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(macAddress).createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")));
            zebrabluetoothsocket.connect();
        }
        catch(IOException ioexception)
        {
            throw new ZebraPrinterConnectionException(ioexception.getMessage());
        }
        return zebrabluetoothsocket;
    }

    public ZebraSocket open()
        throws ZebraPrinterConnectionException {
    	
    	ZebraSocket zebrasocket = null;
    	
    	try {
    		ZebraSocket zebrasocket1 = tryPrivateApiWay();
            zebrasocket = zebrasocket1;
		} catch (Exception e) {
			// TODO: handle exception
			zebrasocket = tryPublicApiWay();
		}
        
        return zebrasocket;
    }

    private static final String SPP_SERVICE_ID = "00001101-0000-1000-8000-00805F9B34FB";
    private String macAddress;
}
