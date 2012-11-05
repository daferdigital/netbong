// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.facturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.clientes.SeleccionarCliente;
import com.netbong.fuerza.dialogos.DialogoSiNo;
import com.netbong.fuerza.facturas.adapters.AdpBancos;
import com.netbong.fuerza.facturas.db.DBHandle;
import com.netbong.fuerza.facturas.db.cursors.CrsFacturasPendientes;
import com.netbong.fuerza.facturas.db.cursors.CsrBancos;
import com.netbong.fuerza.facturas.db.cursors.CsrPagoRegistradoDatosCliente;
import com.netbong.fuerza.facturas.db.cursors.CsrPagoRegistradoDatosFacturas;
import com.netbong.fuerza.facturas.db.cursors.CsrPagoRegistradoDatosFormasPago;

public class PagosRegistrarNuevo extends Activity
{

    public PagosRegistrarNuevo()
    {
        oclEditarFormaPago = new _cls1();
        oclEliminarFormaPago = new _cls2();
        oclRegistrarCancelacion = new _cls3();
        oclSeleccionarCliente = new _cls4();
        oclSeleccionarBanco = new _cls5();
        oclRegresar = new _cls6();
        oclComprobantes = new _cls7();
        oclAgregaarFormaPago = new _cls8();
        oclSeleccFactura = new _cls9();
        oclSeleccBanco = new _cls10();
        oclInficarFechaMovimiento = new _cls11();
        textWatcher = new _cls12();
        oclanular = new _cls13();
        oclRecuperar = new _cls14();
        onFocusChangeListener = new _cls15();
        oclImprimirRecibo = new _cls16();
    }

    private void actualizarCancelacion() {
    	DBHandle.pagoActualizarDatos(this.idPago, 
    			null, 
    			null, 
    			this.idCliente, 
    			this.totalMontoPagarFormasPago);
        DBHandle.pagoQuitarFacturas(this.idPago);
        LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131099978);
        
        int i = localLinearLayout1.getChildCount();
        int j = 0, m = 0, n = 0;
        LinearLayout localLinearLayout3 = null;
        
        if (j >= i) {
        	DBHandle.pagoQuitarFormasPago(this.idPago);
        	localLinearLayout3 = (LinearLayout)findViewById(2131099980);
        	m = localLinearLayout3.getChildCount();
        }
        
        for (n = 0; ; n++){
        	if (n >= m) {
        		MainActivity.crearMensajeToast(this, "Registro de Pago Actualizado.", true);
        		setResult(-1);
        		finish();
        		return;
        	}
            
        	LinearLayout localLinearLayout2 = (LinearLayout)localLinearLayout1.getChildAt(j);
            if (((CheckBox)localLinearLayout2.findViewById(2131099995)).isChecked()){
            	int k = Integer.parseInt(((TextView)localLinearLayout2.findViewById(2131099820)).getTag().toString());
            	double d = Double.parseDouble(((TextView)localLinearLayout2.findViewById(2131099998)).getText().toString());
            	DBHandle.pagoAgregarFactura(this.idPago, k, d);
            	DBHandle.pagoAgregarFacturaEvento(this.idPago, k, d, "Modificado registro de pago. Ahora Bs. ");
            }
            j++;
            break;
        }
       
        LinearLayout localLinearLayout4 = (LinearLayout)localLinearLayout3.getChildAt(n);
        String str1 = ((TextView)localLinearLayout4.findViewById(2131099985)).getText().toString();
        String str2 = ((TextView)localLinearLayout4.findViewById(2131099999)).getText().toString();
        String str3 = ((TextView)localLinearLayout4.findViewById(2131100000)).getText().toString();
        Double localDouble = Double.valueOf(Double.parseDouble(((TextView)localLinearLayout4.findViewById(2131100001)).getTag().toString()));
        String str4 = ((TextView)localLinearLayout4.findViewById(2131099992)).getText().toString();
        DBHandle.pagoAgregarFormaPago(this.idPago, str1, str2, str3, localDouble.doubleValue(), str4);
    }

    private void cargarFacturasPendientes(int i)
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06014a);
        linearlayout.removeAllViews();
        CrsFacturasPendientes crsfacturaspendientes = CrsFacturasPendientes.getFacturasPendientes(idCliente);
        if(crsfacturaspendientes.getCount() > 0)
            do
            {
                View view = crsfacturaspendientes.getRowAsView(this);
                linearlayout.addView(view);
                view.findViewById(0x7f06015b).setOnClickListener(oclSeleccFactura);
                view.findViewById(0x7f06015e).setOnFocusChangeListener(onFocusChangeListener);
                if(i == Integer.parseInt(((TextView)view.findViewById(0x7f0600ac)).getTag().toString()))
                {
                    CheckBox checkbox = (CheckBox)view.findViewById(0x7f06015b);
                    checkbox.setChecked(true);
                    oclSeleccFactura.onClick(checkbox);
                }
            } while(crsfacturaspendientes.moveToNext());
    }

    private void cargarFacturasPendientesPago(int i)
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06014a);
        linearlayout.removeAllViews();
        CrsFacturasPendientes crsfacturaspendientes = CrsFacturasPendientes.getFacturasPendientesPago(i);
        if(crsfacturaspendientes.getCount() > 0)
            do
            {
                View view = crsfacturaspendientes.getRowAsView(this);
                linearlayout.addView(view);
                view.findViewById(0x7f06015b).setOnClickListener(oclSeleccFactura);
                view.findViewById(0x7f06015e).setOnFocusChangeListener(onFocusChangeListener);
                if(crsfacturaspendientes.getFacturaEnPago() != 0)
                {
                    CheckBox checkbox = (CheckBox)view.findViewById(0x7f06015b);
                    checkbox.setChecked(true);
                    ((TextView)view.findViewById(0x7f06015e)).setText(Double.toString(crsfacturaspendientes.getMontoEnPago()));
                    oclSeleccFactura.onClick(checkbox);
                }
            } while(crsfacturaspendientes.moveToNext());
    }

    private void cargarFormasPago(int i)
    {
        CsrPagoRegistradoDatosFormasPago csrpagoregistradodatosformaspago = DBHandle.obtenerDatosFormasPago(i);
        if(csrpagoregistradodatosformaspago.getCount() > 0)
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06014c);
            do
            {
                LinearLayout linearlayout1 = (LinearLayout)View.inflate(this, 0x7f030076, null);
                linearlayout.addView(linearlayout1);
                TextView textview = (TextView)linearlayout1.findViewById(0x7f060151);
                textview.setText(csrpagoregistradodatosformaspago.getBanco());
                textview.setTag(Integer.valueOf(0x1869f));
                ((TextView)linearlayout1.findViewById(0x7f06015f)).setText(csrpagoregistradodatosformaspago.getFormaPago());
                ((TextView)linearlayout1.findViewById(0x7f060160)).setText(csrpagoregistradodatosformaspago.getReferencia());
                TextView textview1 = (TextView)linearlayout1.findViewById(0x7f060161);
                textview1.setTag(Double.toString(csrpagoregistradodatosformaspago.getMonto()));
                textview1.setText(MainActivity.formatVE(csrpagoregistradodatosformaspago.getMonto()));
                ((TextView)linearlayout1.findViewById(0x7f060158)).setText(csrpagoregistradodatosformaspago.getFechaMovimiento());
                incrementarMontoPagarFormasPago(csrpagoregistradodatosformaspago.getMonto());
                ImageButton imagebutton = (ImageButton)linearlayout1.findViewById(0x7f060162);
                imagebutton.setTag(linearlayout1);
                imagebutton.setOnClickListener(oclEditarFormaPago);
                ImageButton imagebutton1 = (ImageButton)linearlayout1.findViewById(0x7f060163);
                imagebutton1.setTag(linearlayout1);
                imagebutton1.setOnClickListener(oclEliminarFormaPago);
            } while(csrpagoregistradodatosformaspago.moveToNext());
        }
    }

    private boolean clientePoseeFacturasPendientes(int i)
    {
        boolean flag;
        if(DBHandle.totalFacturasPendientesPorCliente(i) > 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void comprobanteIvaQuitar(String s)
    {
        if(!DBHandle.comprobanteIvaYaRegistrado(s))
        {
            incrementarMontoRetencion(0.75D * -DBHandle.comprobanteIvaDeterminacionBase(s));
            DBHandle.comprobanteIvaEliminar(s);
        }
    }

    private void coprobanteIvaAgregar(String s)
    {
        if(!DBHandle.comprobanteIvaYaRegistrado(s))
        {
            double d = DBHandle.comprobanteIvaDeterminacionBase(s);
            if(d > 0.0D)
            {
                incrementarMontoRetencion(0.75D * d);
                DBHandle.comprobanteIvaRegistrar(s, d);
            }
        }
    }

    private String fechaActual()
    {
        DatePicker datepicker = new DatePicker(this);
        String s = Integer.toString(datepicker.getYear());
        String s1 = (new StringBuilder("00")).append(Integer.toString(1 + datepicker.getMonth())).toString();
        String s2 = Integer.toString(datepicker.getDayOfMonth());
        return (new StringBuilder(String.valueOf(s))).append('-').append(s1.substring(1)).append('-').append(s2).toString();
    }

    private void incrementarMontoPagar(double d)
    {
        totalMontoPagar = d + totalMontoPagar;
        ((TextView)findViewById(0x7f060167)).setText((new StringBuilder("Total a pagar Bs.: ")).append(MainActivity.formatVE(totalMontoPagar)).toString());
    }

    private void incrementarMontoPagarFormasPago(double d)
    {
        totalMontoPagarFormasPago = d + totalMontoPagarFormasPago;
    }

    private void incrementarMontoRetencion(double d)
    {
        totalMontoRetencion = d + totalMontoRetencion;
        ((TextView)findViewById(0x7f060166)).setText((new StringBuilder("Retencion IVA (75%) Bs.: ")).append(MainActivity.formatVE(totalMontoRetencion)).toString());
    }

    private String lineaCentrada(String s, int i)
    {
        String s2;
        if(s.length() > i)
        {
            s2 = s.substring(0, i);
        } else
        {
            String s1 = "                                                     ".substring(0, (i - s.length()) / 2);
            s2 = (new StringBuilder(String.valueOf(s1))).append(s).append(s1).toString();
        }
        return s2;
    }

    private String lineaDerecha(String s, int i)
    {
        String s1;
        if(s.length() > i)
            s1 = s.substring(0, i);
        else
            s1 = (new StringBuilder(String.valueOf("                                                     ".substring(0, i - s.length())))).append(s).toString();
        return s1;
    }

    private String lineaExtremos(String s, String s1, int i)
    {
        int j = s.length() + s1.length();
        String s3;
        if(j > i)
        {
            s3 = "";
        } else
        {
            String s2 = "                                                     ".substring(0, i - j);
            s3 = (new StringBuilder(String.valueOf(s))).append(s2).append(s1).toString();
        }
        return s3;
    }

    private String lineaIzquierda(String s, int i)
    {
        String s1;
        if(s.length() > i)
            s1 = s.substring(0, i);
        else
            s1 = (new StringBuilder(String.valueOf(s))).append("                                                     ".substring(0, i - s.length())).toString();
        return s1;
    }

    private void registrarCancelacion() {
    	int i = DBHandle.pagoRegistrarNueva(null, null, this.idCliente, this.totalMontoPagar);
        LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131099978);
        LinearLayout localLinearLayout3 = null;
        
        int j = localLinearLayout1.getChildCount();
        int k = 0, n = 0, i1 = 0;
        
        if (k >= j){
        	localLinearLayout3 = (LinearLayout)findViewById(2131099980);
        	n = localLinearLayout3.getChildCount();
        }
        
        for (i1 = 0; ; i1++){
        	if (i1 >= n) {
        		MainActivity.crearMensajeToast(this, "Registro de Pago exitoso.", true);
        		setResult(-1);
        		finish();
        		break;
        	}
        	
            LinearLayout localLinearLayout2 = (LinearLayout)localLinearLayout1.getChildAt(k);
            if (((CheckBox)localLinearLayout2.findViewById(2131099995)).isChecked()) {
            	TextView localTextView = (TextView)localLinearLayout2.findViewById(2131099820);
            	int m = Integer.parseInt(localTextView.getTag().toString());
            	String str1 = localTextView.getText().toString();
            	double d = Double.parseDouble(((TextView)localLinearLayout2.findViewById(2131099998)).getText().toString());
            	DBHandle.pagoAgregarFactura(i, m, d);
            	DBHandle.pagoAgregarFacturaEvento(i, m, d, "Nuevo registro de pago efectuado por Bs. ");
            	DBHandle.comprobanteIvaActualizarDatos(str1, i);
            	DBHandle.actualizarSaldoPendienteFactura(str1, d);
            	DBHandle.actualizarEstatusFacturaSegunSaldoActual(str1);
            }
            
            k++;
          }
        
          LinearLayout localLinearLayout4 = (LinearLayout)localLinearLayout3.getChildAt(i1);
          String str2 = ((TextView)localLinearLayout4.findViewById(2131099985)).getText().toString();
          String str3 = ((TextView)localLinearLayout4.findViewById(2131099999)).getText().toString();
          String str4 = ((TextView)localLinearLayout4.findViewById(2131100000)).getText().toString();
          Double localDouble = Double.valueOf(Double.parseDouble(((TextView)localLinearLayout4.findViewById(2131100001)).getTag().toString()));
          String str5 = ((TextView)localLinearLayout4.findViewById(2131099992)).getText().toString();
          DBHandle.pagoAgregarFormaPago(i, str2, str3, str4, localDouble.doubleValue(), str5);
    }

    private boolean validarCamposFormaPago()
    {
        boolean flag1;
        if(idBanco <= 0)
        {
            MainActivity.crearMensajeToast(this, "Seleccione un Banco.", true);
            flag1 = false;
        } else
        {
            boolean flag;
            if(!((RadioButton)findViewById(0x7f060154)).isChecked() && !((RadioButton)findViewById(0x7f060155)).isChecked() && !((RadioButton)findViewById(0x7f060156)).isChecked())
                flag = false;
            else
                flag = true;
            if(!flag)
            {
                MainActivity.crearMensajeToast(this, "Indique la forma de pago.", true);
                flag1 = false;
            } else
            {
                String s = ((TextView)findViewById(0x7f06000e)).getText().toString();
                if(s == null || s.length() == 0)
                {
                    MainActivity.crearMensajeToast(this, "Indique la referencia.", true);
                    flag1 = false;
                } else
                {
                    String s1 = ((TextView)findViewById(0x7f060159)).getText().toString();
                    if(s1 == null || s1.length() == 0)
                    {
                        MainActivity.crearMensajeToast(this, "Indique el monto.", true);
                        flag1 = false;
                    } else
                    {
                        flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    private boolean validarRequeridos() {
        boolean flag = false;
        if(idCliente > 0) {
        	if(((LinearLayout)findViewById(0x7f06014c)).getChildCount() > 0) {
        		LinearLayout linearlayout = (LinearLayout)findViewById(0x7f06014a);
        		int i = linearlayout.getChildCount();
                boolean flag1 = true;
                int j = 0;
                
                for (j = 0;  j < i; j++) {
                	LinearLayout linearlayout1 = (LinearLayout)linearlayout.getChildAt(j);
                    if(!((CheckBox)linearlayout1.findViewById(0x7f06015b)).isChecked()) {
                    	break;
                    }
                    
                    flag1 = false;
                    String s = ((TextView)linearlayout1.findViewById(0x7f06015e)).getText().toString();
                    if(s == null || s.length() == 0) {
                        MainActivity.crearMensajeToast(this, "Indique monto a cancelar en factura seleccionada.", true);
                    } else {
                        if(Double.parseDouble(s) >= 1.0D) {
                        	MainActivity.crearMensajeToast(this, "Monto errado en en factura seleccionada.", true);
                        	break;
                        }
                    }
				}
                
                if(flag1) {
                	MainActivity.crearMensajeToast(this, "Seleccione una factura.", true);
                } else if(DBHandle.totalComprobantesIvaNoConfigurado() > 0) {
                	MainActivity.crearMensajeToast(this, "Comprobante de Iva no indicado.", true);
                } else if(!MainActivity.formatVE(totalMontoPagar).equalsIgnoreCase(MainActivity.formatVE(totalMontoPagarFormasPago))) {
                	MainActivity.crearMensajeToast(this, (new StringBuilder("Montos Total A Pagar no coinciden: ")).append(MainActivity.formatVE(totalMontoPagar)).append(" vs ").append(MainActivity.formatVE(totalMontoPagarFormasPago)).toString(), true);
                } else {
                	flag = true;
                }
        	} else {
        		MainActivity.crearMensajeToast(this, "Indique la forma de pago.", true);
        	}
        } else {
        	MainActivity.crearMensajeToast(this, "Seleccione algun cliente.", true);
        }
        
        return flag;
    }

    private String versionImpresa()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(lineaCentrada("FRIGIMAR C.A.", 45)).append("\r\n");
        stringbuilder.append(lineaCentrada("NetBong.", 45)).append("\r\n").append("\r\n");
        stringbuilder.append(lineaIzquierda("HEMOS RECIBIDO DE:", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda("----------------------------------------------------", 45)).append("\r\n");
        CsrPagoRegistradoDatosCliente csrpagoregistradodatoscliente = CsrPagoRegistradoDatosCliente.getDatosCliente(idPago);
        stringbuilder.append(lineaIzquierda((new StringBuilder("CLIENTE: ")).append(csrpagoregistradodatoscliente.getClienteCodigo()).append(" / ").append("RIF: ").append(csrpagoregistradodatoscliente.getClienteRif()).toString(), 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda(csrpagoregistradodatoscliente.getClienteNombre().trim(), 45)).append("\r\n").append("\r\n");
        stringbuilder.append(lineaIzquierda((new StringBuilder("COBRANZAS N\260: ")).append(Integer.toString(idPago)).toString(), 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda("FECHA: ", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda("=====================================================", 45)).append("\r\n");
        CsrPagoRegistradoDatosFormasPago csrpagoregistradodatosformaspago = CsrPagoRegistradoDatosFormasPago.getFormasPago(idPago);
        stringbuilder.append(lineaIzquierda("DATOS DEL PAGO:", 45)).append("\r\n");
        do
        {
            stringbuilder.append(lineaExtremos((new StringBuilder("  T/P: ")).append(csrpagoregistradodatosformaspago.getFormaPago()).toString(), "Fecha: ", 45)).append("\r\n");
            stringbuilder.append(lineaExtremos((new StringBuilder("  Nro: ")).append(csrpagoregistradodatosformaspago.getReferencia()).toString(), (new StringBuilder("Monto: ")).append(MainActivity.formatVE(csrpagoregistradodatosformaspago.getMonto())).toString(), 45)).append("\r\n");
            stringbuilder.append(lineaIzquierda((new StringBuilder("Banco: ")).append(csrpagoregistradodatosformaspago.getBanco()).toString(), 45)).append("\r\n");
        } while(csrpagoregistradodatosformaspago.moveToNext());
        stringbuilder.append(lineaIzquierda("----------------------------------------------------", 45)).append("\r\n");
        CsrPagoRegistradoDatosFacturas csrpagoregistradodatosfacturas = CsrPagoRegistradoDatosFacturas.getFacturas(idPago);
        stringbuilder.append(lineaIzquierda("DOCUMENTOS:", 45)).append("\r\n");
        do
        {
            stringbuilder.append(lineaExtremos((new StringBuilder("  Nro: ")).append(csrpagoregistradodatosfacturas.getFacturaCodigo()).toString(), "Fecha: ", 45)).append("\r\n");
            stringbuilder.append(lineaExtremos("Retn: (75)% ", (new StringBuilder("Retn: ")).append(MainActivity.formatVE(csrpagoregistradodatosfacturas.getFacturaMontoRetenido())).toString(), 45)).append("\r\n");
            stringbuilder.append(lineaDerecha((new StringBuilder("Total Monto: ")).append(MainActivity.formatVE(csrpagoregistradodatosfacturas.getPagoMonto())).toString(), 45)).append("\r\n").append("\r\n");
        } while(csrpagoregistradodatosfacturas.moveToNext());
        stringbuilder.append(lineaIzquierda("=====================================================", 45)).append("\r\n");
        stringbuilder.append(lineaDerecha((new StringBuilder("Total Pago: ")).append(MainActivity.formatVE(csrpagoregistradodatoscliente.getMontoTotalPago())).toString(), 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda("=====================================================", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda("Nota: No se consider\341 pago si el cheque se", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda("      realiza a nombre de persona distinta a", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierda("      FRIGIMAR, C.A.:", 45)).append("\r\n");
        return stringbuilder.toString();
    }

    protected void onActivityResult(int i, int j, Intent intent) {
    	Bundle bundle;
    	String s;
        
        if(j == -1 && i == 6){
            String s2 = intent.getExtras().getString("FECHA");
            ((TextView)findViewById(0x7f060158)).setText(s2);
        }
        
        if(j == -1 && i == 1){
            Bundle bundle1 = intent.getExtras();
            int k = bundle1.getInt("ID");
            bundle1.getString("RIF");
            String s1 = bundle1.getString("CLIENTE");
            
            if(clientePoseeFacturasPendientes(k)){
                ((TextView)findViewById(0x7f060051)).setText(s1);
                idCliente = k;
                cargarFacturasPendientes(0);
            } else{
                MainActivity.crearMensajeToast(this, "No existen facturas pendientes para el cliente indicado.", true);
            }
        }
        
        if(j == -1 && i == 2){
            bundle = intent.getExtras();
            idBanco = bundle.getInt("ID");
            s = bundle.getString("DESCRIPCION");
            ((TextView)findViewById(0x7f060151)).setText(s);
        }
        
        if(j != -1 || i != 3) {
        	if(j == -1 && i == 4) {
        		if(!DBHandle.pagoPermitirRecuperar(idPago)) {
                    MainActivity.crearMensajeToast(this, "Imposible Recuperar. Pedido previamente recuperado/sincronizado.", true);
                } else {
                    DBHandle.pagoRecuperar(idPago);
                    setResult(-1);
                    finish();
                    MainActivity.crearMensajeToast(this, "Pago recuperado exitosamente.", true);
                }
        	}
        } else {
        	if(DBHandle.pagoPermitirAnular(idPago)) {
        		DBHandle.pagoAnular(idPago);
                setResult(-1);
                finish();
                MainActivity.crearMensajeToast(this, "Pago anulado exitosamente.", true);
        	} else {
        		MainActivity.crearMensajeToast(this, "Imposible Anular. Pedido previamente anulado/sincronizado.", true);
        	}
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.registrar_pagos_activity_layout);
        DBHandle.comprobanteIvaEliminar();
        findViewById(0x7f060168).setOnClickListener(oclSeleccionarCliente);
        findViewById(0x7f060018).setOnClickListener(oclRegresar);
        findViewById(0x7f06016c).setOnClickListener(oclRegistrarCancelacion);
        findViewById(0x7f060019).setOnClickListener(oclanular);
        findViewById(0x7f06001a).setOnClickListener(oclRecuperar);
        findViewById(0x7f060169).setOnClickListener(oclComprobantes);
        campoFiltroBanco = (EditText)findViewById(0x7f060152);
        campoFiltroBanco.addTextChangedListener(textWatcher);
        ListView listview = (ListView)findViewById(0x7f060153);
        listview.setDivider(null);
        listview.setCacheColorHint(0);
        findViewById(0x7f06015a).setOnClickListener(oclAgregaarFormaPago);
        findViewById(0x7f060157).setOnClickListener(oclInficarFechaMovimiento);
        findViewById(0x7f06016a).setVisibility(8);
        findViewById(0x7f06016b).setVisibility(8);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            idPago = bundle1.getInt("ID-PAGO");
            if(idPago > 0)
            {
                CsrPagoRegistradoDatosCliente csrpagoregistradodatoscliente = DBHandle.obtenerDatosCliente(idPago);
                idCliente = csrpagoregistradodatoscliente.getClienteId();
                ((TextView)findViewById(0x7f060051)).setText(csrpagoregistradodatoscliente.getClienteNombre());
                cargarFacturasPendientesPago(idPago);
                cargarFormasPago(idPago);
                if(DBHandle.pagoPermitirAnular(idPago))
                    findViewById(0x7f06016a).setVisibility(0);
                if(DBHandle.pagoPermitirRecuperar(idPago))
                    findViewById(0x7f06016b).setVisibility(0);
                findViewById(0x7f0600ee).setVisibility(0);
                findViewById(0x7f0600ef).setOnClickListener(oclImprimirRecibo);
            } else
            {
                idCliente = bundle1.getInt("ID");
                String s = bundle1.getString("CLIENTE");
                int i = bundle1.getInt("ID-FACTURA");
                ((TextView)findViewById(0x7f060051)).setText(s);
                ((TextView)findViewById(0x7f060158)).setText(fechaActual());
                cargarFacturasPendientes(i);
            }
        }
    }

    private static final int INTENT_REQUEST_ANULAR = 3;
    private static final int INTENT_REQUEST_COMOPROBANTE = 5;
    private static final int INTENT_REQUEST_INDICAR_FECHA_MOVIMIENTO = 6;
    private static final int INTENT_REQUEST_RECUPERAR = 4;
    private static final int INTENT_REQUEST_SELECCIONAR_BANCO = 2;
    private static final int INTENT_REQUEST_SELECCIONAR_CLIENTE = 1;
    private static final String SALTO_LINEA = "\r\n";
    private static final String blancos = "                                                     ";
    private static final String seperador1 = "----------------------------------------------------";
    private static final String seperador2 = "=====================================================";
    private EditText campoFiltroBanco;
    private CsrBancos crb;
    private int idBanco;
    private int idCliente;
    private int idPago;
    private Intent imprimirRecibo;
    private Intent indicarFechaMovimiento;
    private LinearLayout lyFormaPagoSeleccinado;
    private android.view.View.OnClickListener oclAgregaarFormaPago;
    private android.view.View.OnClickListener oclComprobantes;
    private android.view.View.OnClickListener oclEditarFormaPago;
    private android.view.View.OnClickListener oclEliminarFormaPago;
    private android.view.View.OnClickListener oclImprimirRecibo;
    private android.view.View.OnClickListener oclInficarFechaMovimiento;
    private android.view.View.OnClickListener oclRecuperar;
    private android.view.View.OnClickListener oclRegistrarCancelacion;
    private android.view.View.OnClickListener oclRegresar;
    private android.view.View.OnClickListener oclSeleccBanco;
    private android.view.View.OnClickListener oclSeleccFactura;
    private android.view.View.OnClickListener oclSeleccionarBanco;
    private android.view.View.OnClickListener oclSeleccionarCliente;
    private android.view.View.OnClickListener oclanular;
    private android.view.View.OnFocusChangeListener onFocusChangeListener;
    private Intent registrarComprobantes;
    private Intent seleccionarBanco;
    private Intent seleccionarCliente;
    private String startValue;
    private TextWatcher textWatcher;
    private double totalMontoPagar;
    private double totalMontoPagarFormasPago;
    private double totalMontoRetencion;
    private View vReference;



































    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            lyFormaPagoSeleccinado = (LinearLayout)view.getTag();
            TextView textview = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f060158);
            ((TextView)findViewById(0x7f060158)).setText(textview.getText());
            TextView textview1 = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f060151);
            ((TextView)findViewById(0x7f060151)).setText(textview1.getText());
            idBanco = Integer.parseInt(textview1.getTag().toString());
            TextView textview2 = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f06015f);
            Log.v("forma pago", textview2.getText().toString());
            RadioButton radiobutton = (RadioButton)findViewById(0x7f060154);
            if(textview2.getText().toString().equalsIgnoreCase(radiobutton.getText().toString()))
                radiobutton.setChecked(true);
            RadioButton radiobutton1 = (RadioButton)findViewById(0x7f060155);
            if(textview2.getText().toString().equalsIgnoreCase(radiobutton1.getText().toString()))
                radiobutton1.setChecked(true);
            RadioButton radiobutton2 = (RadioButton)findViewById(0x7f060156);
            if(textview2.getText().toString().equalsIgnoreCase(radiobutton2.getText().toString()))
                radiobutton2.setChecked(true);
            TextView textview3 = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f060160);
            ((TextView)findViewById(0x7f06000e)).setText(textview3.getText());
            TextView textview4 = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f060161);
            ((TextView)findViewById(0x7f060159)).setText(textview4.getTag().toString());
            String s = textview4.getTag().toString();
            incrementarMontoPagarFormasPago(-Double.parseDouble(s));
            ((LinearLayout)findViewById(0x7f06014c)).removeView(lyFormaPagoSeleccinado);
            lyFormaPagoSeleccinado = null;
        }

        final PagosRegistrarNuevo this$0;

        _cls1()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            View view1 = (View)view.getTag();
            String s = ((TextView)view1.findViewById(0x7f060161)).getTag().toString();
            incrementarMontoPagarFormasPago(-Double.parseDouble(s));
            ((LinearLayout)findViewById(0x7f06014c)).removeView(view1);
        }

        final PagosRegistrarNuevo this$0;

        _cls2()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(vReference != null)
                vReference.clearFocus();
            if(validarRequeridos())
                if(idPago > 0)
                    actualizarCancelacion();
                else
                    registrarCancelacion();
        }

        final PagosRegistrarNuevo this$0;

        _cls3()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(seleccionarCliente == null)
                seleccionarCliente = new Intent(PagosRegistrarNuevo.this, SeleccionarCliente.class);
            startActivityForResult(seleccionarCliente, 1);
        }

        final PagosRegistrarNuevo this$0;

        _cls4()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(seleccionarBanco == null)
                seleccionarBanco = new Intent(PagosRegistrarNuevo.this, SeleccionarBanco.class);
            startActivityForResult(seleccionarBanco, 2);
        }

        final PagosRegistrarNuevo this$0;

        _cls5()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setResult(0);
            finish();
        }

        final PagosRegistrarNuevo this$0;

        _cls6()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls7
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(registrarComprobantes == null)
                registrarComprobantes = new Intent(PagosRegistrarNuevo.this, PagosRegistrarComprobante.class);
            startActivityForResult(registrarComprobantes, 5);
        }

        final PagosRegistrarNuevo this$0;

        _cls7()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls8
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(validarCamposFormaPago())
            {
                if(lyFormaPagoSeleccinado == null)
                {
                    lyFormaPagoSeleccinado = (LinearLayout)View.inflate(PagosRegistrarNuevo.this, 0x7f030076, null);
                    ((LinearLayout)findViewById(0x7f06014c)).addView(lyFormaPagoSeleccinado);
                }
                ((TextView)lyFormaPagoSeleccinado.findViewById(0x7f060158)).setText(((TextView)findViewById(0x7f060158)).getText());
                TextView textview = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f060151);
                textview.setText(((TextView)findViewById(0x7f060151)).getText());
                textview.setTag(Integer.valueOf(idBanco));
                ((TextView)findViewById(0x7f060151)).setText(null);
                idBanco = 0;
                TextView textview1 = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f06015f);
                RadioButton radiobutton = (RadioButton)findViewById(0x7f060154);
                if(radiobutton.isChecked())
                    textview1.setText(radiobutton.getText());
                RadioButton radiobutton1 = (RadioButton)findViewById(0x7f060155);
                if(radiobutton1.isChecked())
                    textview1.setText(radiobutton1.getText());
                RadioButton radiobutton2 = (RadioButton)findViewById(0x7f060156);
                if(radiobutton2.isChecked())
                    textview1.setText(radiobutton2.getText());
                ((RadioGroup)findViewById(0x7f060101)).clearCheck();
                ((TextView)lyFormaPagoSeleccinado.findViewById(0x7f060160)).setText(((TextView)findViewById(0x7f06000e)).getText());
                ((TextView)findViewById(0x7f06000e)).setText(null);
                TextView textview2 = (TextView)lyFormaPagoSeleccinado.findViewById(0x7f060161);
                String s = ((TextView)findViewById(0x7f060159)).getText().toString();
                textview2.setTag(s);
                textview2.setText(MainActivity.formatVE(Double.parseDouble(s)));
                ((TextView)findViewById(0x7f060159)).setText(null);
                incrementarMontoPagarFormasPago(Double.parseDouble(s));
                ImageButton imagebutton = (ImageButton)lyFormaPagoSeleccinado.findViewById(0x7f060162);
                imagebutton.setTag(lyFormaPagoSeleccinado);
                imagebutton.setOnClickListener(oclEditarFormaPago);
                ImageButton imagebutton1 = (ImageButton)lyFormaPagoSeleccinado.findViewById(0x7f060163);
                imagebutton1.setTag(lyFormaPagoSeleccinado);
                imagebutton1.setOnClickListener(oclEliminarFormaPago);
                lyFormaPagoSeleccinado = null;
            }
        }

        final PagosRegistrarNuevo this$0;

        _cls8()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls9
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            LinearLayout linearlayout = (LinearLayout)view.getParent();
            String s = ((TextView)linearlayout.findViewById(0x7f0600ac)).getText().toString();
            TextView textview = (TextView)linearlayout.findViewById(0x7f06015e);
            double d = Double.parseDouble(textview.getText().toString());
            CheckBox checkbox = (CheckBox)view;
            if(!checkbox.isChecked())
                d = -d;
            textview.setEnabled(checkbox.isChecked());
            incrementarMontoPagar(d);
            if(!checkbox.isChecked())
                comprobanteIvaQuitar(s);
            else
                coprobanteIvaAgregar(s);
        }

        final PagosRegistrarNuevo this$0;

        _cls9()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls10
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            idBanco = Integer.parseInt(view.getTag().toString());
            String s = ((TextView)view).getText().toString();
            ((TextView)findViewById(0x7f060151)).setText(s);
        }

        final PagosRegistrarNuevo this$0;

        _cls10()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls11
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(indicarFechaMovimiento == null)
                indicarFechaMovimiento = new Intent(PagosRegistrarNuevo.this, SeleccionarFecha.class);
            startActivityForResult(indicarFechaMovimiento, 6);
        }

        final PagosRegistrarNuevo this$0;

        _cls11()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls12
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable){
            Editable editable1 = campoFiltroBanco.getText();
            if(editable1 != null) {
            	
	            String s = editable1.toString();
	            if(s != null) {
	                String s1 = s.trim();
	                if(s1 != null) {
	                    if(crb != null && !crb.isClosed())
	                        crb.close();
	                    crb = DBHandle.obtenerBancos(s1);
	                    ((ListView)findViewById(0x7f060153)).setAdapter(new AdpBancos(PagosRegistrarNuevo.this, crb, oclSeleccBanco));
	                }
	            }
            }
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        final PagosRegistrarNuevo this$0;

        _cls12()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls13
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(PagosRegistrarNuevo.this, DialogoSiNo.class);
            intent.putExtra("MENSAJE", "Esto anulara el pago actual. \277Desea continuar?");
            startActivityForResult(intent, 3);
        }

        final PagosRegistrarNuevo this$0;

        _cls13()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls14
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Intent intent = new Intent(PagosRegistrarNuevo.this, DialogoSiNo.class);
            intent.putExtra("MENSAJE", "Esto recuperara el pago actual. \277Desea continuar?");
            startActivityForResult(intent, 4);
        }

        final PagosRegistrarNuevo this$0;

        _cls14()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls15
        implements android.view.View.OnFocusChangeListener
    {

        public void onFocusChange(View view, boolean flag)
        {
            if(flag)
            {
                startValue = ((TextView)view).getText().toString();
                vReference = view;
            }
            if(!flag)
            {
                String s = ((TextView)view).getText().toString();
                double d = Double.parseDouble(startValue);
                double d1 = Double.parseDouble(s);
                incrementarMontoPagar(d1 - d);
            }
        }

        final PagosRegistrarNuevo this$0;

        _cls15()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }


    private class _cls16
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(imprimirRecibo == null)
            {
                imprimirRecibo = new Intent(PagosRegistrarNuevo.this, PagosVistaImpresa.class);
                imprimirRecibo.putExtra("DATO", versionImpresa());
            }
            startActivity(imprimirRecibo);
        }

        final PagosRegistrarNuevo this$0;

        _cls16()
        {
        	super();
        	this$0 = PagosRegistrarNuevo.this;
        }
    }

}
