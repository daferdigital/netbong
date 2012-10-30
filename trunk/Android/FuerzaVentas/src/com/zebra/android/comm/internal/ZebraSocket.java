// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.comm.internal;

import java.io.*;

public interface ZebraSocket
{

    public abstract void close()
        throws IOException;

    public abstract void connect()
        throws IOException;

    public abstract InputStream getInputStream()
        throws IOException;

    public abstract OutputStream getOutputStream()
        throws IOException;
}
