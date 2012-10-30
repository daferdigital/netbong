// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

class CommandType {

    private CommandType(String s) {
        id = s;
    }

    protected static CommandType getCommand(String s)
    {
        CommandType commandtype = unknownCommand;
        if(!s.equalsIgnoreCase(fnCommand.getId())){
        	if(s.equalsIgnoreCase(ccCommand.getId())){
        		commandtype = ccCommand;
        	} else if(s.equalsIgnoreCase(dfCommand.getId())) {
                commandtype = dfCommand;
        	} else if(s.equalsIgnoreCase(xaCommand.getId())) {
                commandtype = xaCommand;
        	} else if(s.equalsIgnoreCase(xzCommand.getId())){
                commandtype = xzCommand;
        	}
        } else {
        	commandtype = fnCommand;
        }
        
        return commandtype;
    }

    protected String getId(){
        return id.toLowerCase();
    }

    public static final CommandType ccCommand = new CommandType("CC");
    public static final CommandType dfCommand = new CommandType("DF");
    public static final CommandType fnCommand = new CommandType("FN");
    public static final CommandType unknownCommand = new CommandType("unknown");
    public static final CommandType xaCommand = new CommandType("XA");
    public static final CommandType xzCommand = new CommandType("XZ");
    private String id;

}
