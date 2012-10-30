// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.util;

import android.os.Looper;
import com.zebra.android.comm.*;
import com.zebra.android.printer.*;

public class ZebraPrinterTest
    implements Runnable
{

    public ZebraPrinterTest(String s, byte abyte0[])
    {
        datos = abyte0;
        macAddressPrinter = s;
    }

    private void doConnectionTest()
    {
        printer = connect();
        if(printer != null)
            sendTestLabel();
        else
            disconnect();
    }

    private byte[] getConfigLabel()
    {
        printer.getPrinterControlLanguage();
        byte[] _tmp = (byte[])null;
        "! 0 200 200 406 1\r\nON-FEED IGNORE\r\nBOX 20 20 380 380 8\r\nT 0 6 137 177 TEST-CPCL\r\nPRINT\r\n".getBytes();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("! U1 SETLP 7 0 24").append("\n\r");
        stringbuilder.append("! U1 CENTER").append("\n\r");
        stringbuilder.append("CH Computacion").append("\n\r");
        stringbuilder.append("RIF: J00000000").append("\n\r");
        stringbuilder.append("Direccion/Zona").append("\n\r\n\r");
        stringbuilder.append("! U1 LEFT").append("\n\r").append("COD-PROD-1").append("! U1 RIGHT").append("\n\r").append("Bs. XXX.XX").append("\n\r");
        stringbuilder.append("! U1 LEFT").append("\n\r").append("CANT").append("! U1 RIGHT").append("\n\r").append("IVA. YY.YY").append("\n\r");
        stringbuilder.append("! U1 LEFT").append("EXCENTO").append("\n\r\n\r");
        stringbuilder.append("! U1 LEFT").append("\n\r").append("COD-PROD-2").append("! U1 RIGHT").append("\n\r").append("Bs. XXX.XX").append("\n\r");
        stringbuilder.append("! U1 LEFT").append("\n\r").append("CANT").append("! U1 RIGHT").append("\n\r").append("IVA. YY.YY").append("\n\r");
        stringbuilder.append("! U1 LEFT").append("GRAVAMENT").append("\n\r\n\r");
        return stringbuilder.toString().getBytes();
    }

    private String getMacAddressPrinter()
    {
        return macAddressPrinter;
    }

    private void sendTestLabel() {
    	
    	try {
    		zebraPrinterConnection.write(datos);
            Thread.sleep(1500L);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disconnect();
		}
    }

    public ZebraPrinter connect()
    {
        zebraPrinterConnection = null;
        zebraPrinterConnection = new BluetoothPrinterConnection(getMacAddressPrinter());
        ZebraPrinter zebraprinter;
        try
        {
            zebraPrinterConnection.open();
        }
        catch(ZebraPrinterConnectionException zebraprinterconnectionexception)
        {
            try
            {
                Thread.sleep(1000L);
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
            disconnect();
        }
        zebraprinter = null;
        if(zebraPrinterConnection.isConnected())
            try
            {
                zebraprinter = ZebraPrinterFactory.getInstance(zebraPrinterConnection);
                zebraprinter.getPrinterControlLanguage();
            }
            catch(ZebraPrinterConnectionException zebraprinterconnectionexception1)
            {
                zebraprinter = null;
                try
                {
                    Thread.sleep(1000L);
                }
                catch(InterruptedException interruptedexception2)
                {
                    interruptedexception2.printStackTrace();
                }
                disconnect();
            }
            catch(ZebraPrinterLanguageUnknownException zebraprinterlanguageunknownexception)
            {
                zebraprinter = null;
                try
                {
                    Thread.sleep(1000L);
                }
                catch(InterruptedException interruptedexception1)
                {
                    interruptedexception1.printStackTrace();
                }
                disconnect();
            }
        return zebraprinter;
    }

    public void disconnect() {
    	
    	try {
    		if(zebraPrinterConnection != null) {
            	zebraPrinterConnection.close();
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    public void run()
    {
        Looper.prepare();
        doConnectionTest();
        Looper.loop();
        Looper.myLooper().quit();
    }

    private byte datos[];
    private String macAddressPrinter;
    private ZebraPrinter printer;
    private ZebraPrinterConnection zebraPrinterConnection;
}
