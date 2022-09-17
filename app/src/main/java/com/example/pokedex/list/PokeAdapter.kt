package com.example.pokedex.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.Results
import com.example.pokedex.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokeAdapter(var pokemons: ArrayList<Results?>) :
    RecyclerView.Adapter<PokeAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPokemonBinding.bind(view)

        fun bind(image: Results) {
            Picasso.get().load(image.url).into(binding.ivPokeImage)
            binding.ivPokeImage

            binding.tvPokeText.text = image.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = pokemons[position].name
        val pokemon = pokemons[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemons.size
    fun recibirPokemons(pokemons: ArrayList<Results?>) {
        this.pokemons = pokemons
        notifyDataSetChanged()

        Log.i("Listado de adapter", pokemons.toString())
    }
}

