// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.comm.internal.PrinterCommand;
import com.zebra.android.comm.internal.PrinterCommandImpl;
import com.zebra.android.printer.FieldDescriptionData;
import com.zebra.android.util.internal.StringUtilities;
import com.zebra.android.util.internal.ZPLUtilities;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package com.zebra.android.printer.internal:
//            FormatUtilA, CommandType, IndexAndCommandType, MalformedFormatException

public class FormatUtilZpl extends FormatUtilA
{

    public FormatUtilZpl(ZebraPrinterConnection zebraprinterconnection)
    {
        super(zebraprinterconnection);
    }

    private IndexAndCommandType findNext(String s, String s1, int i, String s2)
    {
        String s3 = (new StringBuilder()).append(s).append(CommandType.fnCommand.getId()).toString();
        String s4 = (new StringBuilder()).append(s).append(CommandType.ccCommand.getId()).toString();
        String s5 = (new StringBuilder()).append(s1).append(CommandType.ccCommand.getId()).toString();
        String as[] = new String[3];
        as[0] = s3;
        as[1] = s4;
        as[2] = s5;
        return findSpecifiedCommand(i, s2, as);
    }

    private IndexAndCommandType findNextFnCommand(String s, int i, String s1)
    {
        IndexAndCommandType indexandcommandtype = findNext(s, "~", i, s1);
        if(indexandcommandtype.getIndex() == -1)
            indexandcommandtype = findNext(ZPLUtilities.ZPL_INTERNAL_FORMAT_PREFIX, ZPLUtilities.ZPL_INTERNAL_COMMAND_PREFIX, i, s1);
        return indexandcommandtype;
    }

    private int findOccurance(List list, FieldDescriptionData fielddescriptiondata)
    {
        int i = -1;
        int j = 0;
        do
        {
label0:
            {
                if(j < list.size())
                {
                    if(((FieldDescriptionData)list.get(j)).fieldNumber != fielddescriptiondata.fieldNumber)
                        break label0;
                    i = j;
                }
                return i;
            }
            j++;
        } while(true);
    }

    private IndexAndCommandType findSpecifiedCommand(int i, String s, String as[])
    {
        int j = StringUtilities.indexOf(s.toLowerCase(), as, i);
        CommandType commandtype;
        if(j > -1)
            commandtype = CommandType.getCommand(s.substring(j + 1, j + 3));
        else
            commandtype = CommandType.unknownCommand;
        return new IndexAndCommandType(j, commandtype);
    }

    private FieldDescriptionData parseFnCommand(String s)
        throws MalformedFormatException {
    	
        FieldDescriptionData fielddescriptiondata = null;
        int i = s.indexOf('"', 0);
        
        if(i != -1) {
        	if(i + 1 < s.length()) {
        		String s1;
                int j;
                try {
                    s1 = StringUtilities.stripQuotes(s.substring(i).trim());
                    j = Integer.parseInt(s.substring(0, i).trim());
                    if(j < 1 || j > 9999)
                        throw new MalformedFormatException("'^FN' integer must be between 1 and 9999");
                } catch(NumberFormatException numberformatexception) {
                    throw new MalformedFormatException("'^FN' must be followed by an integer");
                }
                
                fielddescriptiondata = new FieldDescriptionData(j, s1);
        	}
        } else {
        	int k;
            try {
                k = Integer.parseInt(s.trim());
                if(k < 1 || k > 9999)
                    throw new MalformedFormatException("'^FN' integer must be between 1 and 9999");
            } catch(NumberFormatException numberformatexception1) {
                throw new MalformedFormatException("'^FN' must be followed by an integer");
            }
            
            fielddescriptiondata = new FieldDescriptionData(k, null);
        }
        
        return fielddescriptiondata;
    }

    public FieldDescriptionData[] getVariableFields(String s) {
        ArrayList arraylist = new ArrayList();
        String s1 = "^";
        int i = 0;
        
        if(i <= -1) {
        	
        } else {
        	
        }
        
        /*
        
_L4:
        if(i <= -1) goto _L2; else goto _L1
_L1:
        CommandType commandtype;
        int j;
        IndexAndCommandType indexandcommandtype = findNextFnCommand(s1, i, s);
        i = indexandcommandtype.getIndex();
        commandtype = indexandcommandtype.getCommand();
        boolean flag;
        if(i > -1)
            flag = true;
        else
            flag = false;
        if(!flag) goto _L4; else goto _L3
_L3:
        j = i + 3;
        if(commandtype != CommandType.ccCommand) goto _L6; else goto _L5
_L5:
        s1 = s.substring(j, j + 1);
_L8:
        i = j;
          goto _L4
_L6:
        if(commandtype != CommandType.fnCommand) goto _L8; else goto _L7
_L7:
        int k;
        String as[] = new String[2];
        as[0] = s1;
        as[1] = ZPLUtilities.ZPL_INTERNAL_FORMAT_PREFIX;
        k = StringUtilities.indexOf(s, as, j);
        if(k <= -1) goto _L8; else goto _L9
_L9:
        String s2 = s.substring(j, k);
        FieldDescriptionData fielddescriptiondata;
        int l;
        fielddescriptiondata = parseFnCommand(s2);
        l = findOccurance(arraylist, fielddescriptiondata);
        if(l == -1) goto _L11; else goto _L10
_L10:
        boolean flag1 = true;
_L14:
        if(!flag1) goto _L13; else goto _L12
_L12:
        boolean flag2;
        if(fielddescriptiondata.fieldName == null)
            break MISSING_BLOCK_LABEL_259;
        flag2 = true;
_L15:
        if(flag2)
        {
            arraylist.remove(l);
            arraylist.add(fielddescriptiondata);
        }
          goto _L8
_L13:
        arraylist.add(fielddescriptiondata);
          goto _L8
_L2:
        return (FieldDescriptionData[])arraylist.toArray(new FieldDescriptionData[0]);
        MalformedFormatException malformedformatexception;
        malformedformatexception;
          goto _L8
_L11:
        flag1 = false;
          goto _L14
        flag2 = false;
          goto _L15
          
          */
        
        return null;
    }

    public void printStoredFormat(String s, Map map)
        throws ZebraPrinterConnectionException {
    	
    	try {
    		printStoredFormat(s, map, System.getProperty("file.encoding"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    public void printStoredFormat(String s, Map map, String s1)
        throws ZebraPrinterConnectionException, UnsupportedEncodingException
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("^XA\r\n");
        stringbuffer.append("^XF");
        stringbuffer.append(s);
        stringbuffer.append("^FS\r\n");
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); stringbuffer.append("^FS\r\n"))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            stringbuffer.append("^FN");
            stringbuffer.append(entry.getKey());
            stringbuffer.append("^FD");
            stringbuffer.append((String)entry.getValue());
        }

        stringbuffer.append("^XZ");
        printerConnection.write(ZPLUtilities.decorateWithFormatPrefix(stringbuffer.toString()).getBytes(s1));
    }

    public byte[] retrieveFormatFromPrinter(String s)
        throws ZebraPrinterConnectionException
    {
        byte abyte0[];
        if(s == null || s.equals(""))
            abyte0 = null;
        else
            abyte0 = (new PrinterCommandImpl(ZPLUtilities.decorateWithFormatPrefix((new StringBuilder()).append("^XA^HF").append(s).append("^XZ").toString()))).sendAndWaitForResponse(printerConnection);
        return abyte0;
    }
}
