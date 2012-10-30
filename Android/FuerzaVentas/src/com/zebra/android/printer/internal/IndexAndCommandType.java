// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;


// Referenced classes of package com.zebra.android.printer.internal:
//            CommandType

class IndexAndCommandType
{

    protected IndexAndCommandType(int i, CommandType commandtype)
    {
        index = i;
        command = commandtype;
    }

    protected CommandType getCommand()
    {
        return command;
    }

    protected int getIndex()
    {
        return index;
    }

    private CommandType command;
    private int index;
}
