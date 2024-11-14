package com.example.gestionpeliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterCartelera extends RecyclerView.Adapter<MyAdapterCartelera.MyViewHolderCartelera>{
	ArrayList<Pelicula> peliculas;
	int selectedPos= RecyclerView.NO_POSITION;

	public MyAdapterCartelera(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public int getSelectedPos() {
		return selectedPos;
	}

	public void setSelectedPos(int nuevaPos) {
		if (nuevaPos == this.selectedPos){
			this.selectedPos=RecyclerView.NO_POSITION;
			notifyItemChanged(nuevaPos);
		} else {
			if (this.selectedPos >=0 ) {
				notifyItemChanged(this.selectedPos);
			}
			this.selectedPos = nuevaPos;
			notifyItemChanged(nuevaPos);
		}
	}

	@NonNull
	@Override
	public MyAdapterCartelera.MyViewHolderCartelera onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View elemento= LayoutInflater.from(parent.getContext()).inflate( R.layout.celda,
				parent, false );
		MyAdapterCartelera.MyViewHolderCartelera mvh = new MyAdapterCartelera.MyViewHolderCartelera(elemento);
		return mvh;
	}

	@Override
	public void onBindViewHolder(@NonNull MyAdapterCartelera.MyViewHolderCartelera holder, int position) {
		Pelicula pelicula=this.peliculas.get(position);

	}

	@Override
	public int getItemCount() {
		return this.peliculas.size();
	}

	public class MyViewHolderCartelera extends RecyclerView.ViewHolder {
		private TextView txtTituloCompleto;
		private TextView txtDirector;
		private ImageView imgPelicula;
		private ImageView imgCalificacion;
		private ImageView imgFavorita;

		public MyViewHolderCartelera(View viewElemento) {
			super(viewElemento);
			this.txtTituloCompleto = viewElemento.findViewById(R.id.txtTituloCompleta);
			this.txtDirector = viewElemento.findViewById(R.id.txtDirectorCompleta);
			this.imgPelicula = viewElemento.findViewById(R.id.imgPeliculaCompleta);
			this.imgCalificacion = viewElemento.findViewById(R.id.imgCalificacionCompleta);
			this.imgFavorita = viewElemento.findViewById(R.id.imgFavorita);
			viewElemento.setOnClickListener(view -> {
				int posPulsada=getAdapterPosition();
				setSelectedPos(posPulsada);

			});

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

		public TextView getTxtTituloCompleto() {
			return txtTituloCompleto;
		}
	}
}
