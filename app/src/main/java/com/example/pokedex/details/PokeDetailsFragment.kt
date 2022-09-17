package com.example.pokedex.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex.databinding.FragmentPokeDetailsBinding
import com.squareup.picasso.Picasso


class PokeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPokeDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataDetails = PokeDetailsFragmentArgs.fromBundle(requireArguments())

        var numberPok = dataDetails.pokeUrl?.drop(34)
        numberPok = numberPok?.dropLast(1)
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numberPok.png")
            .into(binding.ivPokeImageDetail)

        binding.tvPokeTextDetail.text = dataDetails.pokeName

    }
}