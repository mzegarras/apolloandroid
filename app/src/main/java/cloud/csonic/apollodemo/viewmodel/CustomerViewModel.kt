package cloud.csonic.apollodemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.csonic.apollodemo.data.CustomerModel

import cloud.csonic.apollodemo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val customerLiveData = MutableLiveData<List<CustomerModel>?>()
    fun getCustomer() = customerLiveData

    fun loadCustomers(name:String) {
        viewModelScope.launch {
            val response = repository.getCustomers(name)

            //val launch = response.data?.getAccounts
            //if (launch == null || response.hasErrors()) {
            if(!response.hasErrors()){

                var customersList = listOf<CustomerModel>()

                response.data?.getCustomers?.forEach {
                    customersList = customersList + CustomerModel(it.name,it.cic);
                }

                customerLiveData.postValue(customersList)
            }
        }
    }
}