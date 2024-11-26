package com.example.gestionpeliculas;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.HashMap;

public class NuevaPelicula extends AppCompatActivity {

	Toolbar tlbNuevaPelicula;
	String[] salas;
	Spinner spinner;
	boolean muestra;
	Pelicula nuevaPelicula;
	EditText txtTitulo;
	EditText txtDirector;
	EditText txtDuracion;
	RadioGroup rdg;
	CalendarView cld;

	HashMap<Integer,Integer> clasis;

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

		nuevaPelicula = new Pelicula();

		tlbNuevaPelicula = findViewById(R.id.tlbNuevaPelicula);
		setSupportActionBar(tlbNuevaPelicula);
		ActionBar ctb = getSupportActionBar();
		assert ctb != null;
		ctb.setDisplayHomeAsUpEnabled(true);
		ctb.setTitle("Peliculas");

		txtTitulo = findViewById(R.id.dtxtTitulo);
		txtDirector = findViewById(R.id.dtxtDirector);
		txtDuracion = findViewById(R.id.dtxtDuracion);
		rdg = findViewById(R.id.rdg);
		cld = findViewById(R.id.cld);

		muestra = true;

		salas = getResources().getStringArray(R.array.salas);
		spinner = findViewById(R.id.spinner);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,salas);
		spinner.setAdapter(arrayAdapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				if (!muestra) muestra = true;
				else {
					nuevaPelicula.setSala(adapterView.getItemAtPosition(i).toString());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		clasis = new HashMap<>();
		clasis.put(R.id.rdbG, R.drawable.g);
		clasis.put(R.id.rdbPG, R.drawable.pg);
		clasis.put(R.id.rdbR, R.drawable.r);
		clasis.put(R.id.rdbPG13, R.drawable.pg13);
		clasis.put(R.id.rdbNC17, R.drawable.nc17);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater=getMenuInflater();
		menuInflater.inflate(R.menu.menu_con_guardado,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.mntGuardar) {
			if (!txtTitulo.getText().toString().isEmpty() && !txtDirector.getText().toString().isEmpty() && !txtDuracion.getText().toString().isEmpty()) {
				nuevaPelicula.setTitulo(txtTitulo.getText().toString());
				nuevaPelicula.setDirector(txtDirector.getText().toString());
				nuevaPelicula.setDuracion(Integer.parseInt(txtDuracion.getText().toString()));
				nuevaPelicula.setClasi(clasis.get(rdg.getCheckedRadioButtonId()));
				nuevaPelicula.setFecha(new Date(cld.getDate()));
				Datos.getInstance().getPelis("peliculas").add(nuevaPelicula);
				finish();
			} else {
				Toast.makeText(this, "Introduce todos los datos", Toast.LENGTH_LONG).show();
			}


		}
		return super.onOptionsItemSelected(item);
	}
}