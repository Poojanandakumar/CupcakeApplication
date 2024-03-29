package com.codelab.cupcakeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.codelab.cupcakeapplication.databinding.FragmentStartBinding

class StartFragment:Fragment() {
    private var binding: FragmentStartBinding?=null
    private val sharedcupcakeViewmodel: CupcakeViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment=this
    }

    fun orderCupCake(quantity:Int){
        sharedcupcakeViewmodel.setQuantity(quantity)

        if(sharedcupcakeViewmodel.hasNoFlavor())
        {
            sharedcupcakeViewmodel.setFlavor("vanilla")
        }
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}