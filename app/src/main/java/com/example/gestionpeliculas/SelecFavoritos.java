package com.example.gestionpeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SelecFavoritos extends AppCompatActivity {

	Toolbar tlbSelecFavoritos;
	private ArrayList<Pelicula> listaPeliculas;
	private ArrayAdapter<Pelicula> adapterPeliculas;
	private ListView lstFavoritos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_selec_favoritos);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		tlbSelecFavoritos = findViewById(R.id.tlbSelecFavoritos);
		setSupportActionBar(tlbSelecFavoritos);
		ActionBar ctb = getSupportActionBar();
		ctb.setDisplayHomeAsUpEnabled(true);
		ctb.setTitle("Peliculas");

		listaPeliculas = Datos.getInstance().getPelis("peliculas");

		adapterPeliculas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, listaPeliculas);

		lstFavoritos = findViewById(R.id.lstFavoritos);
		lstFavoritos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lstFavoritos.setAdapter(adapterPeliculas);
		lstFavoritos.setOnItemClickListener((adapterView, view, i, l) -> { listaPeliculas.get(i).setFavorita(lstFavoritos.isItemChecked(i)); adapterPeliculas.notifyDataSetChanged();});

		for (int i = 0; i < lstFavoritos.getCount(); i++) {
			lstFavoritos.setItemChecked(i, listaPeliculas.get(i).getFavorita());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater=getMenuInflater();
		menuInflater.inflate(R.menu.menu_con_guardado,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		int id=item.getItemId();
		if (id == R.id.mntGuardar) {
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}