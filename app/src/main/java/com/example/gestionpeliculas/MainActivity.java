package com.example.gestionpeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

	private boolean tglColumnas;
	private boolean tglFavoritos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_main);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		tglColumnas = false;
		tglFavoritos = false;

		Toolbar barraDeHerramientas = findViewById(R.id.tlbMain);
		setSupportActionBar(barraDeHerramientas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater=getMenuInflater();
		menuInflater.inflate(R.menu.main_menu,menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		int id=item.getItemId();
		if (id==R.id.itemCartelera){
			Intent intent = new Intent(MainActivity.this, Cartelera.class);
			startActivity(intent);
			return true;
		}else if (id==R.id.itemListaFavoritos){
			if (tglFavoritos = !tglFavoritos) {

			} else {

			}
			return true;
		}else if (id==R.id.itemSelecFavoritos){
			Intent intent = new Intent(MainActivity.this, SelecFavoritos.class);
			return true;
		}else if (id==R.id.itemColumnas){
			if (tglColumnas = !tglColumnas) {
				item.setIcon(R.drawable.number_square_one);
			} else {
				item.setIcon(R.drawable.number_square_two);
			}
			return true;
		}
		else if (id==R.id.itemNuevaPelicula){
			Intent intent = new Intent(MainActivity.this, NuevaPelicula.class);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}