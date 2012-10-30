// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;

import java.util.ArrayList;
import java.util.List;

public class ThreadExecutor
{

    private ThreadExecutor()
    {
    }

    public static void execute(Thread athread[], int i)
    {
        ArrayList arraylist = new ArrayList();
        for(int j = 0; j < athread.length || arraylist.size() > 0;)
        {
            if(arraylist.size() < i && j < athread.length)
            {
                Thread thread = athread[j];
                j++;
                arraylist.add(thread);
                thread.start();
            }
            int k = 0;
            while(k < arraylist.size()) 
            {
                if(!((Thread)arraylist.get(k)).isAlive())
                    arraylist.remove(k);
                k++;
            }
        }

    }
}
