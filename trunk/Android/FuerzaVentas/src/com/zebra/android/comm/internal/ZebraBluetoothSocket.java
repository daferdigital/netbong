// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm.internal;

import android.bluetooth.BluetoothSocket;
import java.io.*;

// Referenced classes of package com.zebra.android.comm.internal:
//            ZebraSocket

public class ZebraBluetoothSocket
    implements ZebraSocket
{

    public ZebraBluetoothSocket(BluetoothSocket bluetoothsocket)
    {
        bSocket = bluetoothsocket;
    }

    public void close()
        throws IOException
    {
        bSocket.close();
    }

    public void connect()
        throws IOException
    {
        bSocket.connect();
    }

    public InputStream getInputStream()
        throws IOException
    {
        return bSocket.getInputStream();
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return bSocket.getOutputStream();
    }

    private BluetoothSocket bSocket;
}
