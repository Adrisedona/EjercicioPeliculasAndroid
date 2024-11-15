package com.example.gestionpeliculas;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.time.format.*;
import java.time.*;

public class MyAdapterCartelera extends RecyclerView.Adapter<MyAdapterCartelera.MyViewHolderCartelera>{
	ArrayList<Pelicula> peliculas;
	int selectedPos= RecyclerView.NO_POSITION;
	AppCompatActivity activity;

	public MyAdapterCartelera(ArrayList<Pelicula> peliculas, AppCompatActivity activity) {
		this.peliculas = peliculas;
		this.activity = activity;
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
		View elemento= LayoutInflater.from(parent.getContext()).inflate( R.layout.celda_completa,
				parent, false );
		MyAdapterCartelera.MyViewHolderCartelera mvh = new MyAdapterCartelera.MyViewHolderCartelera(elemento);
		return mvh;
	}

	@Override
	public void onBindViewHolder(@NonNull MyAdapterCartelera.MyViewHolderCartelera holder, int position) {
		Pelicula pelicula=this.peliculas.get(position);
		holder.getTxtTituloCompleto().setText(pelicula.getTitulo());
		holder.getTxtDirectorCompleta().setText(pelicula.getDirector());
		holder.getTxtDuracionCompleta().setText(pelicula.getDuracion() + "");
		holder.getTxtFechaCompleta().setText(new SimpleDateFormat("dd/MM/yyyy").format(pelicula.getFecha()));
		holder.getTxtSalaCompleta().setText(pelicula.getSala());
		holder.getImgPelicula().setImageResource(pelicula.getPortada());
		holder.getImgCalificacion().setImageResource(pelicula.getClasi());
		holder.getImgFavorita().setImageResource(pelicula.getFavorita() ? R.drawable.red_heart : R.drawable.empty);
	}

	@Override
	public int getItemCount() {
		return this.peliculas.size();
	}

	public class MyViewHolderCartelera extends RecyclerView.ViewHolder {
		private TextView txtTituloCompleto;
		private TextView txtDirectorCompleta;
		private TextView txtDuracionCompleta;
		private TextView txtSalaCompleta;
		private TextView txtFechaCompleta;
		private ImageView imgPelicula;
		private ImageView imgCalificacion;
		private ImageView imgFavorita;
		TextView lblDirector;

		public MyViewHolderCartelera(View viewElemento) {
			super(viewElemento);
			this.txtTituloCompleto = viewElemento.findViewById(R.id.txtTituloCompleta);
			this.txtDirectorCompleta = viewElemento.findViewById(R.id.txtDirectorCompleta);
			this.txtDuracionCompleta = viewElemento.findViewById(R.id.txtDuracionCompleta);
			this.txtSalaCompleta = viewElemento.findViewById(R.id.txtSalaCompleta);
			this.txtFechaCompleta = viewElemento.findViewById(R.id.txtFechaCompleta);
			this.imgPelicula = viewElemento.findViewById(R.id.imgPeliculaCompleta);
			this.imgCalificacion = viewElemento.findViewById(R.id.imgCalificacionCompleta);
			this.imgFavorita = viewElemento.findViewById(R.id.imgFavorita);
			this.lblDirector = viewElemento.findViewById(R.id.lblDirector);
			viewElemento.setOnClickListener(view -> {
				int posPulsada=getAdapterPosition();
				setSelectedPos(posPulsada);
				Intent intent = new Intent(activity, PeliculaConDescipcion.class);
				intent.putExtra("peli", Datos.getInstance().getPelis("peliculas").get(selectedPos));
				activity.startActivity(intent);
			});

		}

		public ImageView getImgCalificacion() {
			return imgCalificacion;
		}

		public ImageView getImgPelicula() {
			return imgPelicula;
		}

		public TextView getTxtDirectorCompleta() {
			return txtDirectorCompleta;
		}

		public TextView getTxtTituloCompleto() {
			return txtTituloCompleto;
		}

		public TextView getTxtDuracionCompleta() {
			return txtDuracionCompleta;
		}

		public TextView getTxtSalaCompleta() {
			return txtSalaCompleta;
		}

		public ImageView getImgFavorita() {
			return imgFavorita;
		}

		public TextView getTxtFechaCompleta() {
			return txtFechaCompleta;
		}
	}
}
