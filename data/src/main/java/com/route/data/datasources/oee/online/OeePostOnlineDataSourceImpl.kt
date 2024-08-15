package com.route.data.datasources.oee.online

import com.google.firebase.firestore.FirebaseFirestore
import com.route.data.contract.oee.OeePostOnlineDataSource
import com.route.data.utils.Constants
import com.route.data.utils.safeGetData
import com.route.data.utils.safePostData
import com.route.domain.models.oee.OEE
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OeePostOnlineDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : OeePostOnlineDataSource {
    var listOfPartNUmber = mutableListOf<String>()
    override suspend fun postData(oee: OEE) {
        safePostData {
            firestore.collection(Constants.OEE_COLLECTION).add(oee).await()
        }
    }

    override suspend fun getPartNumber(): List<String> {
        return safeGetData {
            val query = firestore.collection(Constants.INJECTION_COLLECTION).get().await()
            for (docs in query.documents){
                docs.getString(Constants.PARTNUMBER)?.let {
                    listOfPartNUmber.add(it)
                }
            }
            listOfPartNUmber
        }
    }
}