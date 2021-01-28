package com.codelab.cupcakeapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.codelab.cupcakeapplication.databinding.FragmentOrderBinding

class OrderFragment:Fragment() {
    private var binding:FragmentOrderBinding?=null
    private val sharedViewmodel:CupcakeViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding= FragmentOrderBinding.inflate(inflater,container,false)
        binding= fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner=viewLifecycleOwner
            viewModel=sharedViewmodel
            orderFragment=this@OrderFragment
        }
    }

    fun sendOrder(){
        val numberOfCupcakes=sharedViewmodel.quantity.value?:0
        val orderSummary= getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            sharedViewmodel.flavor.value.toString(),
            sharedViewmodel.date.value.toString(),
            sharedViewmodel.price.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
                .putExtra(Intent.EXTRA_TEXT, orderSummary)

        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }
    fun cancelOrder(){
        sharedViewmodel.resetOrder()
        findNavController().navigate(R.id.action_orderFragment_to_startFragment2)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

}