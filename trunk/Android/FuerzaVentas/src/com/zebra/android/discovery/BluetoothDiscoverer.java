// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.discovery;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.*;
import android.os.Bundle;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.zebra.android.discovery:
//            DiscoveryHandler, DiscoveredPrinterBluetooth

public class BluetoothDiscoverer
{
    private class BtRadioMonitor extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) && intent.getExtras().getInt("android.bluetooth.adapter.extra.STATE") == 10)
            {
                mDiscoveryHandler.discoveryFinished();
                if(btReceiver != null)
                    context.unregisterReceiver(btReceiver);
                if(btMonitor != null)
                    context.unregisterReceiver(btMonitor);
            }
        }

        final BluetoothDiscoverer this$0;

        private BtRadioMonitor()
        {
        	super();
        	this$0 = BluetoothDiscoverer.this;
        }

    }

    private class BtReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getAction();
            if(!"android.bluetooth.device.action.FOUND".equals(s)){
            	if("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(s))
                {
                    mDiscoveryHandler.discoveryFinished();
                    if(btReceiver != null)
                        context.unregisterReceiver(btReceiver);
                    if(btMonitor != null)
                        context.unregisterReceiver(btMonitor);
                }
            } else {
            	BluetoothDevice bluetoothdevice = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if(!listOfFoundBtDevices.contains(bluetoothdevice.getAddress()))
                {
                    mDiscoveryHandler.foundPrinter(new DiscoveredPrinterBluetooth(bluetoothdevice.getAddress(), bluetoothdevice.getName()));
                    listOfFoundBtDevices.add(bluetoothdevice.getAddress());
                }
            }
        }

        private Set listOfFoundBtDevices;
        final BluetoothDiscoverer this$0;

        private BtReceiver()
        {
        	super();
            this$0 = BluetoothDiscoverer.this;
            listOfFoundBtDevices = new HashSet();
        }

    }


    private BluetoothDiscoverer(Context context, DiscoveryHandler discoveryhandler)
    {
        mContext = context;
        mDiscoveryHandler = discoveryhandler;
    }

    private void doBluetoothDisco()
    {
        btReceiver = new BtReceiver();
        btMonitor = new BtRadioMonitor();
        IntentFilter intentfilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        IntentFilter intentfilter1 = new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        IntentFilter intentfilter2 = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        mContext.registerReceiver(btReceiver, intentfilter);
        mContext.registerReceiver(btReceiver, intentfilter1);
        mContext.registerReceiver(btMonitor, intentfilter2);
        BluetoothAdapter.getDefaultAdapter().startDiscovery();
    }

    public static void findPrinters(Context context, DiscoveryHandler discoveryhandler)
        throws ZebraPrinterConnectionException, InterruptedException
    {
        BluetoothAdapter bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothadapter == null)
            discoveryhandler.discoveryError("No bluetooth radio found");
        else
        if(!bluetoothadapter.isEnabled())
        {
            discoveryhandler.discoveryError("Bluetooth radio is currently disabled");
        } else
        {
            if(bluetoothadapter.isDiscovering())
                bluetoothadapter.cancelDiscovery();
            (new BluetoothDiscoverer(context.getApplicationContext(), discoveryhandler)).doBluetoothDisco();
        }
    }

    BtRadioMonitor btMonitor;
    BtReceiver btReceiver;
    private Context mContext;
    private DiscoveryHandler mDiscoveryHandler;

}
