// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;

import java.util.ArrayList;

public class StringUtilities
{

    public StringUtilities()
    {
    }

    public static int countSubstringOccurences(String s, String s1)
    {
        int i = 0;
        int j = 0;
        do
        {
            if(i < 0)
                break;
            i = s.indexOf(s1, i);
            if(i >= 0)
            {
                j++;
                i += s1.length();
            }
        } while(true);
        return j;
    }

    public static boolean doesPrefixExistInArray(String as[], String s) {
    	boolean exists = false;
        if(s != null && as != null && s.length() > 0 && as.length > 0){
        	int i = 0;
        	
        	for (i = 0; i < as.length; i++) {
        		if ((as[i] != null) && (as[i].length() != 0) && (s.toUpperCase().startsWith(as[i].toUpperCase()))){
        			exists = true;
        			break;
        		}
        	}
        }
        
        return exists;
        	
    }

    public static int indexOf(String s, String as[], int i)
    {
        int j = s.length();
        for(int k = 0; k < as.length; k++)
        {
            int l = s.indexOf(as[k], i);
            if(l >= 0 && l < j)
                j = l;
        }

        if(j == s.length())
            j = -1;
        return j;
    }

    public static String padWithChar(String s, char c, int i, boolean flag)
    {
        StringBuffer stringbuffer = new StringBuffer(s);
        int j = i - s.length();
        int k = 0;
        while(k < j) 
        {
            if(flag)
                stringbuffer.insert(0, c);
            else
                stringbuffer.append(c);
            k++;
        }
        return stringbuffer.toString();
    }

    public static String[] split(String s, String s1)
    {
        ArrayList arraylist = new ArrayList();
        int i = 0;
        int j = 0;
        do
        {
            if(j < 0)
                break;
            j = s.indexOf(s1, i);
            if(j >= 0)
            {
                arraylist.add(s.substring(i, j));
                i = j + s1.length();
            } else
            if(i <= -1 + s.length())
                arraylist.add(s.substring(i));
        } while(true);
        return (String[])arraylist.toArray(new String[0]);
    }

    public static String stripQuotes(String s)
    {
        String s1;
        if(s == null)
        {
            s1 = null;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            int i = 0;
            while(i < s.length()) 
            {
                char c = s.charAt(i);
                boolean flag;
                if(c != '"')
                    flag = true;
                else
                    flag = false;
                if(flag)
                    stringbuffer.append(c);
                i++;
            }
            s1 = stringbuffer.toString();
        }
        return s1;
    }

    public static final String CRLF = "\r\n";
    public static final String LF = "\n";
}
