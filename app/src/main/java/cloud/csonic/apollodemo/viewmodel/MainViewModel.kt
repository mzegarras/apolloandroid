package cloud.csonic.apollodemo.viewmodel

import android.accounts.Account
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.csonic.apolloclients.GetAccountsByIdcQuery
import cloud.csonic.apolloclients.type.Customer
import cloud.csonic.apollodemo.data.AccountModel
import cloud.csonic.apollodemo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val accountLiveData = MutableLiveData<List<GetAccountsByIdcQuery.Account?>?>()


    fun getAccounts() = accountLiveData

    fun loadAccounts(number:String) {
        viewModelScope.launch {
            val response = repository.getAccounts(number)
            if(!response.hasErrors()){
                accountLiveData.postValue(response.data?.getCustomersByIdc?.accounts)
            }
        }
    }

}