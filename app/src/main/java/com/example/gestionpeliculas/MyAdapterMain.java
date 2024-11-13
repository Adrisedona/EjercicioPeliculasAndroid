package com.example.gestionpeliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterMain extends RecyclerView.Adapter<MyAdapterMain.MyViewHolderMain> {

	ArrayList<Pelicula> peliculas;
	TextView txtTituloMain;
	int selectedPos=RecyclerView.NO_POSITION;

	public MyAdapterMain(ArrayList<Pelicula> peliculas, TextView txtTituloMain) {
		this.peliculas = peliculas;
		this.txtTituloMain = txtTituloMain;
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
	public MyViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View elemento= LayoutInflater.from(parent.getContext()).inflate( R.layout.celda,
				parent, false );
		MyViewHolderMain mvh = new MyViewHolderMain(elemento);
		return mvh ;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolderMain holder, int position) {
		Pelicula pelicula=this.peliculas.get(position);
		holder.getTxtDirector().setText(pelicula.getDirector());
		holder.getTxtTitulo().setText(pelicula.getTitulo());
		holder.getImgPelicula().setImageResource(pelicula.getPortada());
		holder.getImgCalificacion().setImageResource(pelicula.getClasi());
		if (selectedPos == position)
			holder.itemView.setBackgroundResource(R.color.seleccionado);
		else holder.itemView.setBackgroundResource(R.color.colorcelda);
	}

	@Override
	public int getItemCount() {
		return this.peliculas.size();
	}

	public class MyViewHolderMain extends RecyclerView.ViewHolder {
		private TextView txtTitulo;
		private TextView txtDirector;
		private ImageView imgPelicula;
		private ImageView imgCalificacion;

		public MyViewHolderMain(View viewElemento) {
			super(viewElemento);
			this.txtTitulo = viewElemento.findViewById(R.id.txtTitulo);
			this.txtDirector = viewElemento.findViewById(R.id.txtDirector);
			this.imgPelicula = viewElemento.findViewById(R.id.imgPelicula);
			this.imgCalificacion = viewElemento.findViewById(R.id.imgCalificacion);
			viewElemento.setOnClickListener(view -> {
				int posPulsada=getAdapterPosition();
				setSelectedPos(posPulsada);
				if (selectedPos>RecyclerView.NO_POSITION) {
					txtTituloMain.setText(peliculas.get(selectedPos).getTitulo());
				} else {
					txtTituloMain.setText("");
				}
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

		public TextView getTxtTitulo() {
			return txtTitulo;
		}
	}
}
