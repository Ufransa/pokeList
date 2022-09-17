package com.example.pokedex.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.data.PokeApiService
import com.example.pokedex.data.Results
import com.example.pokedex.databinding.FragmentPokeListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private lateinit var binding: FragmentPokeListBinding
private lateinit var adapter: PokeAdapter
private var pokeImages = mutableListOf<Results?>()


class PokeListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        searchByName()

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(PokeApiService::class.java)
                .getPokemonList("pokemon/?offset=20&limit=5")
            val pokeResponse = call.body()?.results
            activity?.runOnUiThread {
                if (pokeResponse != null) {
                    pokeImages = pokeResponse
                    adapter.notifyDataSetChanged()
                    sendPokmemons(pokeImages)

                } else {
                    showError()

                }
            }
        }
    }


    private fun showError() {
        Toast.makeText(activity, "Something has happend", Toast.LENGTH_SHORT).show()
    }


    private fun initRecyclerView() {
        adapter = PokeAdapter(pokeImages, ::listener)
        binding.rvPokeList.layoutManager = LinearLayoutManager(activity)
        binding.rvPokeList.adapter = adapter
        Log.i("Listado de pokemons", pokeImages.toString())
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun sendPokmemons(pokemons: MutableList<Results?>) {
        adapter.getPokemons(pokemons)
    }


    private fun listener(pokemons: Results?) {
        val pokeName = pokemons?.name
        val pokeUrl = pokemons?.url
        findNavController().navigate(
            PokeListFragmentDirections.actionPokeListFragmentToPokeDetailsFragment(
                pokeName,
                pokeUrl
            )
        )
    }

}