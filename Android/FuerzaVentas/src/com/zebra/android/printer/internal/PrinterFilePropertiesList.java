// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.zebra.android.printer.internal:
//            PrinterFileProperties

public class PrinterFilePropertiesList
{

    public PrinterFilePropertiesList()
    {
        fileRecords = new ArrayList();
    }

    public void add(PrinterFileProperties printerfileproperties)
    {
        fileRecords.add(printerfileproperties);
    }

    public PrinterFilePropertiesList filterByExtension(String as[])
    {
        PrinterFilePropertiesList printerfilepropertieslist = new PrinterFilePropertiesList();
        int i = 0;
label0:
        do
        {
            if(i < fileRecords.size())
            {
                PrinterFileProperties printerfileproperties = (PrinterFileProperties)fileRecords.get(i);
                boolean flag = false;
                int j = 0;
                do
                {
label1:
                    {
                        if(j < as.length)
                        {
                            if(!printerfileproperties.getExt().toUpperCase().equals(as[j].toUpperCase()))
                                break label1;
                            flag = true;
                        }
                        if(flag)
                            printerfilepropertieslist.add(printerfileproperties);
                        i++;
                        continue label0;
                    }
                    j++;
                } while(true);
            }
            return printerfilepropertieslist;
        } while(true);
    }

    public PrinterFileProperties get(int i)
    {
        return (PrinterFileProperties)fileRecords.get(i);
    }

    public String[] getFileNamesFromProperties()
    {
        String as[] = new String[fileRecords.size()];
        for(int i = 0; i < fileRecords.size(); i++)
            as[i] = ((PrinterFileProperties)fileRecords.get(i)).getFullName();

        return as;
    }

    public int size()
    {
        return fileRecords.size();
    }

    List fileRecords;
}
