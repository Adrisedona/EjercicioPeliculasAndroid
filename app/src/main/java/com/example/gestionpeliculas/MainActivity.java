package com.example.gestionpeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	TextView txtTituloMain;
	private boolean tglColumnas;
	private boolean tglFavoritos;
	private ArrayList<Pelicula> peliculas;
	RecyclerView rv;
	MyAdapterMain myAdapter;
	RecyclerView.LayoutManager myLayoutManagerOneColumn;
	RecyclerView.LayoutManager myLayoutManagerTwoColumn;
	ActivityResultLauncher<Intent> launcher;

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
		tglFavoritos = true;
		txtTituloMain = findViewById(R.id.txtTitulo);

		Toolbar barraDeHerramientas = findViewById(R.id.tlbMain);
		setSupportActionBar(barraDeHerramientas);

		peliculas = Datos.rellenaPeliculas();

		myAdapter = new MyAdapterMain(peliculas, txtTituloMain);

		rv = findViewById(R.id.rcv);

		myLayoutManagerOneColumn = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		myLayoutManagerTwoColumn = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

		rv.setAdapter(myAdapter);
		rv.setLayoutManager(myLayoutManagerOneColumn);
		rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

		getSupportActionBar().setTitle("Peliculas");
		getSupportActionBar().setSubtitle(peliculas.size() + "");

		 launcher = registerForActivityResult(new
				ActivityResultContracts.StartActivityForResult(), result -> {
					if (result.getResultCode()==RESULT_OK) {
						peliculas = (ArrayList<Pelicula>) result.getData().getSerializableExtra("peliculas2");
					}
				});

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
				rv.setAdapter(myAdapter);
			} else {
				ArrayList<Pelicula> peliculasFavoritas = new ArrayList<>();
				for (Pelicula peli : this.peliculas) {
					if (peli.getFavorita()) {
						peliculasFavoritas.add(peli);
					}
				}
				rv.setAdapter(new MyAdapterMain(peliculasFavoritas, txtTituloMain));
			}
			return true;
		}else if (id==R.id.itemSelecFavoritos){
			Intent intent = new Intent(MainActivity.this, SelecFavoritos.class);
			intent.putExtra("peliculas", peliculas);
			launcher.launch(intent);
			return true;
		}else if (id==R.id.itemColumnas){
			if (tglColumnas = !tglColumnas) {
				item.setIcon(R.drawable.number_square_one);
				rv.setLayoutManager(myLayoutManagerTwoColumn);
			} else {
				item.setIcon(R.drawable.number_square_two);
				rv.setLayoutManager(myLayoutManagerOneColumn);
			}
			return true;
		}
		else if (id==R.id.itemNuevaPelicula){
			Intent intent = new Intent(MainActivity.this, NuevaPelicula.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}