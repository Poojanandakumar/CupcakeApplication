package com.codelab.cupcakeapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
private val price_each=5
class CupcakeViewmodel:ViewModel() {

    //quantity of cupcakes
    private val _quantity=MutableLiveData<Int>()
    val quantity:LiveData<Int> = _quantity

    //flavor of cupcakes
    private val _flavor=MutableLiveData<String>()
    val flavor:LiveData<String> = _flavor

    //date of order
    private val _date = MutableLiveData<String>()
    val date:LiveData<String> = _date

    //price of order
    private val _price = MutableLiveData<Int>()
    val price:LiveData<Int> = _price

    init {
        resetOrder()
    }

     fun setQuantity(numberCupCakes:Int){
        _quantity.value=numberCupCakes
        updatePrice()
    }

     fun updatePrice() {
        _price.value=(quantity.value)!! * price_each
    }

     fun setFlavor(desiredFlavor:String){
        _flavor.value=desiredFlavor
    }

     fun setDate(desiredDate:String){
        _date.value=desiredDate
    }

    fun resetOrder(){
        _quantity.value=0
        _flavor.value=""
        _date.value=""
        _price.value=0

    }
    fun hasNoFlavor():Boolean{
        return _flavor.value.isNullOrEmpty()
    }
    fun hasNoDate():Boolean{
        return _date.value.isNullOrEmpty()
    }




}