package com.route.data.datasources.oee.online

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.route.data.contract.oee.OeePostOnlineDataSource
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.data.utils.safePostData
import com.route.domain.models.oee.OEE
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OeePostOnlineDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : OeePostOnlineDataSource {
    var listOfPartNUmber = mutableListOf<String>()
    var resultOfExcel = mutableListOf<Map<String, Any>>()
    override suspend fun postData(oee: OEE): String {
        return safeData {
            firestore.collection(Constants.OEE_COLLECTION).add(oee).await()
            "Added Successfully"
        }
    }

    override suspend fun getPartNumber(): List<String> {
        return safeData {
            val query = firestore.collection(Constants.INJECTION_COLLECTION).get().await()
            for (docs in query.documents) {
                docs.getString(Constants.PARTNUMBER)?.let {
                    listOfPartNUmber.add(it)
                }
            }
            listOfPartNUmber
        }
    }

    override suspend fun exportDataToExcel(): List<Map<String, Any>> {
        return safeData {
            val docs = firestore.collection(Constants.OEE_COLLECTION).get().await()
            for (list in docs) {
                resultOfExcel.add(list.data)
            }
            resultOfExcel
        }
    }
}