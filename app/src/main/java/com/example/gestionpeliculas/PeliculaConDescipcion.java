package com.example.gestionpeliculas;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;

public class PeliculaConDescipcion extends AppCompatActivity {

	Toolbar tlbPelicula;
	Pelicula pelicula;
	ImageView imgCaratula;
	TextView txtDescripcion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_pelicula_con_descipcion);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		pelicula = (Pelicula) getIntent().getSerializableExtra("peli");

		tlbPelicula = findViewById(R.id.tlbPelicula);
		setSupportActionBar(tlbPelicula);
		ActionBar ctb = getSupportActionBar();
		ctb.setDisplayHomeAsUpEnabled(true);
		ctb.setTitle(pelicula.getTitulo());

		imgCaratula = findViewById(R.id.imgCaratula);
		imgCaratula.setImageResource(pelicula.getPortada());
		imgCaratula.setOnClickListener(view -> watchYoutubeVideo(pelicula.getIdYoutube()));

		txtDescripcion = findViewById(R.id.txtDescripcion);
		txtDescripcion.setText(pelicula.getSinopsis());


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

	public void watchYoutubeVideo(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}