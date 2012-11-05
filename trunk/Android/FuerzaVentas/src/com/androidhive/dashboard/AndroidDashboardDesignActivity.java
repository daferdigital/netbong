package com.androidhive.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.netbong.R;
import com.netbong.fuerza.MainActivity;
import com.netbong.fuerza.catalogo.CatalogoProductos;
import com.netbong.fuerza.clientes.ConsultarClientes;
import com.netbong.fuerza.conf.Configuracion;
import com.netbong.fuerza.facturas.FacturasGeneradas;
import com.netbong.fuerza.pedidos.PedidosEfectuados;
import com.netbong.fuerza.sincronizar.SyncActivity;

public class AndroidDashboardDesignActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dashboard_layout);
        
        
        /**
         * Creating all buttons instances
         * */
        // Dashboard News feed button
        Button btn_newsfeed = (Button) findViewById(R.id.btn_news_feed);
        
        // Dashboard Friends button
        Button btn_friends = (Button) findViewById(R.id.btn_friends);
        
        // Dashboard Messages button
        Button btn_messages = (Button) findViewById(R.id.btn_messages);
        
        // Dashboard Places button
        Button btn_places = (Button) findViewById(R.id.btn_places);
        
        // Dashboard Events button
        Button btn_events = (Button) findViewById(R.id.btn_events);
        
        // Dashboard Photos button
        Button btn_photos = (Button) findViewById(R.id.btn_photos);
        
        /**
         * Handling all button click events
         * */
        
        // Listening to News Feed button click
        btn_newsfeed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
//				Intent i = new Intent(getApplicationContext(), NewsFeedActivity.class);
//				startActivity(i);
				startActivity(new Intent(MainActivity.mainCtx, PedidosEfectuados.class)); 
			}
		});
        
       // Listening Friends button click
        btn_friends.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
/*				Intent i = new Intent(getApplicationContext(), FriendsActivity.class);
				startActivity(i);*/
				startActivity(new Intent(MainActivity.mainCtx, ConsultarClientes.class));
				
				
			}
		});
        
        // Listening Messages button click
        btn_messages.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				//Intent i = new Intent(getApplicationContext(), MessagesActivity.class);
				//startActivity(i);
				startActivity(new Intent(MainActivity.mainCtx, FacturasGeneradas.class));
				
				
				
				
			}
		});
        
        // Listening to Places button click
        btn_places.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				//Intent i = new Intent(getApplicationContext(), PlacesActivity.class);
				//startActivity(i);
				 startActivity(new Intent(MainActivity.mainCtx, CatalogoProductos.class));
				
				
			}
		});
        
        // Listening to Events button click
        btn_events.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
/*				Intent i = new Intent(getApplicationContext(), EventsActivity.class);
				startActivity(i);*/
				startActivity(new Intent(MainActivity.mainCtx, SyncActivity.class));
				
			}
		});
        
        // Listening to Photos button click
        btn_photos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
/*				Intent i = new Intent(getApplicationContext(), PhotosActivity.class);
				startActivity(i);*/
				startActivity(new Intent(MainActivity.mainCtx, Configuracion.class));
				
				
				
			}
		});
    }
}