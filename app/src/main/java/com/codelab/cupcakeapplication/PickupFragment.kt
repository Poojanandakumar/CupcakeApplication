package com.codelab.cupcakeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.codelab.cupcakeapplication.databinding.FragmentPickupBinding

class PickupFragment:Fragment() {
    private var binding:FragmentPickupBinding?=null
    private val sharedviewmodel:CupcakeViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentPickupBinding=FragmentPickupBinding.inflate(inflater,container,false)
        binding=fragmentPickupBinding
        return fragmentPickupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner=viewLifecycleOwner
            viewModel=sharedviewmodel
            pickupFragment=this@PickupFragment
        }
    }
    fun cancelOrder(){
        sharedviewmodel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)

    }
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_orderFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}