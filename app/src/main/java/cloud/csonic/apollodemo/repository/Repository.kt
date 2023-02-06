package cloud.csonic.apollodemo.repository


import cloud.csonic.apolloclients.GetAccountsByIdcQuery
import cloud.csonic.apolloclients.GetCustomersByIdcQuery
import cloud.csonic.apolloclients.type.DocumentInput
import cloud.csonic.apolloclients.type.DocumentType
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse


class Repository(private val apolloClient: ApolloClient) {

    suspend fun getAccounts(numberDoc:String): ApolloResponse<GetAccountsByIdcQuery.Data> {


        var document = DocumentInput(DocumentType.DNI,numberDoc)

        return apolloClient.query(GetAccountsByIdcQuery(document))
            .execute();

    }

    suspend fun getCustomerByDocument(numberDoc:String): ApolloResponse<GetCustomersByIdcQuery.Data> {

        var document = DocumentInput(DocumentType.DNI,numberDoc)

        return apolloClient.query(GetCustomersByIdcQuery(document))
            .execute();

    }
}