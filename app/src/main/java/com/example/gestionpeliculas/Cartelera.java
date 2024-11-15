package com.example.gestionpeliculas;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cartelera extends AppCompatActivity {

	Toolbar tlbCartelera;
	ArrayList<Pelicula> peliculas;
	RecyclerView rv;
	MyAdapterCartelera myAdapterCartelera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_cartelera);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		tlbCartelera = findViewById(R.id.tlbCartelera);
		setSupportActionBar(tlbCartelera);
		ActionBar ctb = getSupportActionBar();
		ctb.setDisplayHomeAsUpEnabled(true);
		ctb.setTitle("Peliculas");

		peliculas = Datos.getInstance().getPelis("peliculas");

		rv = findViewById(R.id.rcvCartelera);
		myAdapterCartelera = new MyAdapterCartelera(peliculas, this);
		rv.setAdapter(myAdapterCartelera);
		rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}