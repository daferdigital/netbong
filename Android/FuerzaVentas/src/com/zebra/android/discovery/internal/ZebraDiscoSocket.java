// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery.internal;

import java.io.IOException;
import java.net.*;

public interface ZebraDiscoSocket
{

    public abstract void close();

    public abstract void joinGroup(String s)
        throws UnknownHostException, IOException;

    public abstract void receive(DatagramPacket datagrampacket)
        throws IOException;

    public abstract void send(DatagramPacket datagrampacket)
        throws IOException;

    public abstract void setInterface(InetAddress inetaddress)
        throws SocketException;

    public abstract void setSoTimeout(int i)
        throws SocketException;

    public abstract void setTimeToLive(int i)
        throws IOException;
}
