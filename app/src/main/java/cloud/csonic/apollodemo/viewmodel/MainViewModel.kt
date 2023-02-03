package cloud.csonic.apollodemo.viewmodel

import android.accounts.Account
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.csonic.apolloclients.type.Customer
import cloud.csonic.apollodemo.data.AccountModel
import cloud.csonic.apollodemo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val accountLiveData = MutableLiveData<List<AccountModel>?>()


    fun getAccounts() = accountLiveData

    fun loadAccounts() {
        viewModelScope.launch {
            val response = repository.getAccounts();

            //val launch = response.data?.getAccounts
            //if (launch == null || response.hasErrors()) {
            if(!response.hasErrors()){
                var accountList = listOf<AccountModel>()
                response.data?.getAccounts?.forEach {


                    accountList = accountList + AccountModel(it.id, it.number)
                }
                accountLiveData.postValue(accountList)
            }
        }
    }

}