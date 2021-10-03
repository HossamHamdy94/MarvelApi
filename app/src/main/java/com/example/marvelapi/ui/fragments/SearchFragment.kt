package com.example.marvelapi.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapi.adapters.CharcterListAdapter
import com.example.marvelapi.adapters.SearchAdapter
import com.example.marvelapi.databinding.FragmentSecondBinding
import com.example.marvelapi.models.ResultModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var chList = ArrayList<ResultModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle=  Bundle ()

        var charString = arguments?.getString("CH", null)
        if (!charString.isNullOrEmpty()){

            val myType = object : TypeToken<ArrayList<ResultModel>>() {}.type


            chList = Gson().fromJson(charString,myType)

        }

        setListenrs()
        initRv()






    }


    fun initRv () {
        binding.searchRV.layoutManager =  LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false)

        binding.searchRV.adapter = SearchAdapter (requireContext())


    }


    fun setListenrs () {
        binding.serachEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                publishSearchResult(binding.serachEt.text.toString())

            }
        })





    }

    fun publishSearchResult (serch:String) {
        if (binding.serachEt.text.toString().isNullOrEmpty()){

            (binding.searchRV.adapter as SearchAdapter).submitList(ArrayList<ResultModel>())
        }

        else
        (binding.searchRV.adapter as SearchAdapter).submitList(chList.filter { it.name.contains(serch)  })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}