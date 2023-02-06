package cloud.csonic.apollodemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.csonic.apolloclients.GetCustomersByIdcQuery

import cloud.csonic.apollodemo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val customerLiveData = MutableLiveData<GetCustomersByIdcQuery.GetCustomersByIdc?>()
    fun getCustomer() = customerLiveData

    fun loadCustomers(number:String) {
        viewModelScope.launch {
            val response = repository.getCustomerByDocument(number)

            //val launch = response.data?.getAccounts
            //if (launch == null || response.hasErrors()) {
            if(!response.hasErrors()){

                var data = response.data?.getCustomersByIdc

                data?.let {
                    customerLiveData.postValue(it)
                }

            }
        }
    }
}