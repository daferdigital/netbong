// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.util;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;

import java.util.Iterator;
import java.util.Set;

public class DeviceListActivity extends Activity
{

    public DeviceListActivity()
    {
        mDeviceClickListener = new _cls1();
    }

    private void doDiscovery()
    {
        Log.d("DeviceListActivity", "doDiscovery()");
        setProgressBarIndeterminateVisibility(true);
        setTitle("Buscando dispositivos cercanos...");
        findViewById(0x7f06008d).setVisibility(0);
        if(mBtAdapter.isDiscovering())
            mBtAdapter.cancelDiscovery();
        mBtAdapter.startDiscovery();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(5);
        setContentView(R.layout.device_list);
        setResult(0);
        ((Button)findViewById(0x7f06008f)).setOnClickListener(new _cls3());
        mPairedDevicesArrayAdapter = new ArrayAdapter(this, 0x7f030034);
        mNewDevicesArrayAdapter = new ArrayAdapter(this, 0x7f030034);
        ListView listview = (ListView)findViewById(0x7f06008c);
        listview.setAdapter(mPairedDevicesArrayAdapter);
        listview.setOnItemClickListener(mDeviceClickListener);
        ListView listview1 = (ListView)findViewById(0x7f06008e);
        listview1.setAdapter(mNewDevicesArrayAdapter);
        listview1.setOnItemClickListener(mDeviceClickListener);
        IntentFilter intentfilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        registerReceiver(mReceiver, intentfilter);
        IntentFilter intentfilter1 = new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        registerReceiver(mReceiver, intentfilter1);
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter == null)
        {
            MainActivity.crearMensajeToast(this, "El dispositivo no dispone de interfaz BlueTooth", true);
        } else
        {
            Set set = mBtAdapter.getBondedDevices();
            if(set.size() > 0)
            {
                findViewById(0x7f06008b).setVisibility(0);
                Iterator iterator = set.iterator();
                while(iterator.hasNext()) 
                {
                    BluetoothDevice bluetoothdevice = (BluetoothDevice)iterator.next();
                    mPairedDevicesArrayAdapter.add((new StringBuilder(String.valueOf(bluetoothdevice.getName()))).append("\n").append(bluetoothdevice.getAddress()).toString());
                }
            } else
            {
                mPairedDevicesArrayAdapter.add("NINGUNO");
            }
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(mBtAdapter != null)
            mBtAdapter.cancelDiscovery();
        unregisterReceiver(mReceiver);
    }

    private static final boolean D = true;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private static final String TAG = "DeviceListActivity";
    private BluetoothAdapter mBtAdapter;
    private android.widget.AdapterView.OnItemClickListener mDeviceClickListener;
    private ArrayAdapter mNewDevicesArrayAdapter;
    private ArrayAdapter mPairedDevicesArrayAdapter;
    private final BroadcastReceiver mReceiver = new _cls2();





    private class _cls1
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            mBtAdapter.cancelDiscovery();
            String s = ((TextView)view).getText().toString();
            String s1 = s.substring(-17 + s.length());
            Intent intent = new Intent();
            intent.putExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS, s1);
            setResult(-1, intent);
            finish();
        }

        final DeviceListActivity this$0;

        _cls1() {
        	super();
        	this$0 = DeviceListActivity.this;
        }
    }


    private class _cls2 extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            if("android.bluetooth.device.action.FOUND".equals(s)){
            	BluetoothDevice bluetoothdevice = (BluetoothDevice) intent.getParcelableExtra(
            			"android.bluetooth.device.extra.DEVICE");
            	if(bluetoothdevice.getBondState() != 12){
            		mNewDevicesArrayAdapter.add((new StringBuilder(String.valueOf(bluetoothdevice.getName()))).append("\n").append(bluetoothdevice.getAddress()).toString());
            	}
            } else {
            	if("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(s)){
            		setProgressBarIndeterminateVisibility(false);
                    setTitle("Seleccione un dispositivo para establecer conexion");
                    if(mNewDevicesArrayAdapter.getCount() == 0){
                    	mNewDevicesArrayAdapter.add("NINGUNO");
                    }
            	}
            }
        }

        final DeviceListActivity this$0;

        _cls2() {
        	super();
        	this$0 = DeviceListActivity.this;
            
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            doDiscovery();
            view.setVisibility(8);
        }

        final DeviceListActivity this$0;

        _cls3() {
        	super();
        	this$0 = DeviceListActivity.this;
            
        }
    }
}
