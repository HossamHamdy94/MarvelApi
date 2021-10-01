package com.example.marvelapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.downloadexample.viewModels.FragmentMoviesViewModel
import com.example.marvelapi.adapters.CharcterListAdapter
import com.example.marvelapi.R
import com.example.marvelapi.databinding.FragmentFirstBinding
import com.example.marvelapi.models.ResultModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class CharctersListFragment : Fragment() {

    val viewmodel :FragmentMoviesViewModel by viewModels()
    var charcterList = ArrayList<ResultModel> ()
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


callApi()
        setListeners()


    }

    fun callApi() {

        viewmodel.getChracters()
        binding.loading.visibility = View.VISIBLE
        viewmodel.getCharcterLiveData.observe(viewLifecycleOwner,{
            if (it!=null){
                if (it.code==200){
                    initRv()
                    charcterList = it.data.results as ArrayList<ResultModel>
                    (binding.charcterRV.adapter as CharcterListAdapter).submitList(it.data.results)
                    binding.loading.visibility = View.GONE


                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun initRv () {
      binding.charcterRV.layoutManager =  LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false)

        binding.charcterRV.adapter = CharcterListAdapter (requireContext())


    }

    fun setListeners () {
        binding.serachIcon.setOnClickListener {
            val json: String = Gson().toJson(charcterList)
            var bundle = Bundle()
            bundle.putString("CH",json)


            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        }
    }
}