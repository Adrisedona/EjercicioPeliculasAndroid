package com.example.gestionpeliculas;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

	ArrayList<Pelicula> peliculas;

	public MyAdapter(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		private TextView txtTitulo;
		private TextView txtDirector;
		private ImageView imgPelicula;
		private ImageView imgCalificacion;

		public MyViewHolder(View viewElemento) {
			super(viewElemento);
			this.txtTitulo = viewElemento.findViewById(R.id.txtTitulo);
			this.txtDirector = viewElemento.findViewById(R.id.txtDirector);
			this.imgPelicula = viewElemento.findViewById(R.id.imgPelicula);
			this.imgCalificacion = viewElemento.findViewById(R.id.imgCalificacion);

		}

		public ImageView getImgCalificacion() {
			return imgCalificacion;
		}

		public ImageView getImgPelicula() {
			return imgPelicula;
		}

		public TextView getTxtDirector() {
			return txtDirector;
		}

		public TextView getTxtTitulo() {
			return txtTitulo;
		}
	}
}
