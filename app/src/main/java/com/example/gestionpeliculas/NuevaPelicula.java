package com.example.gestionpeliculas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NuevaPelicula extends AppCompatActivity {

	Toolbar tlbNuevaPelicula;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_nueva_pelicula);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		tlbNuevaPelicula = findViewById(R.id.tlbNuevaPelicula);
		setSupportActionBar(tlbNuevaPelicula);
		ActionBar ctb = getSupportActionBar();
		ctb.setDisplayHomeAsUpEnabled(true);
	}
}