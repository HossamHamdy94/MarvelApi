package com.example.marvelapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.marvelapi.R
import com.example.marvelapi.databinding.FragmentCharcterDetialsBinding
import com.example.marvelapi.databinding.FragmentFirstBinding
import com.example.marvelapi.models.ResultModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharcterDetialsFragment : Fragment() {

    lateinit var  charcter : ResultModel
    private var _binding: FragmentCharcterDetialsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharcterDetialsBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      var objectStr =   arguments?.getString("charcterObject","")
        charcter = Gson().fromJson(objectStr,ResultModel ::class.java)



        Glide.with(requireContext()).load("${charcter.thumbnail.path}.${charcter.thumbnail.extension}").into(binding.charterdetailsIV)
        binding.chNameTV.text = charcter.name
        binding.discTV.text = charcter.description
    }
}