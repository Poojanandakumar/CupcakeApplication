package com.codelab.cupcakeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.codelab.cupcakeapplication.databinding.FragmentFlavorBinding

class FlavorFragment:Fragment() {
    private var binding:FragmentFlavorBinding?=null
    private val sharedviewModel:CupcakeViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding=FragmentFlavorBinding.inflate(inflater,container,false)
        binding=fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner=viewLifecycleOwner
            viewModel=sharedviewModel
            flavorFragment= this@FlavorFragment
        }
    }

    fun goToNextScreen(){
        if(sharedviewModel.hasNoDate())
        {
            sharedviewModel.setDate("Wed jan 27")
        }
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)

    }

    fun cancelOrder(){
        sharedviewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}