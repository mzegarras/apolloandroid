package cloud.csonic.apollodemo.repository

import cloud.csonic.apolloclients.CustomersDataQuery
import cloud.csonic.apolloclients.GetAccountsDataQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse


class Repository(private val apolloClient: ApolloClient) {

    suspend fun getAccounts(): ApolloResponse<GetAccountsDataQuery.Data> {

        return apolloClient.query(GetAccountsDataQuery())
            .execute();

    }

    suspend fun getCustomers(name:String): ApolloResponse<CustomersDataQuery.Data> {

        return apolloClient.query(CustomersDataQuery(name))
            .execute();

    }
}