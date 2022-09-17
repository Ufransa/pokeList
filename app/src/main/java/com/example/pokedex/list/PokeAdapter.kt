package com.example.pokedex.list


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.Results
import com.example.pokedex.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokeAdapter(
    private var pokemons: MutableList<Results?>,
    private val pokeDetail: (Results?) -> Unit
) :
    RecyclerView.Adapter<PokeAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPokemonBinding.bind(view)

        fun bind(image: Results?) {

            binding.tvPokeText.text = image?.name

            var numberPok = image?.url?.drop(34)
            numberPok = numberPok?.dropLast(1)
            Picasso.get()
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numberPok.png")
                .into(binding.ivPokeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
        holder.itemView.setOnClickListener {
            pokeDetail(pokemon)

        }
    }

    override fun getItemCount(): Int = pokemons.size

    @SuppressLint("NotifyDataSetChanged")
    fun getPokemons(pokemons: MutableList<Results?>) {
        this.pokemons = pokemons
        notifyDataSetChanged()
    }
}


