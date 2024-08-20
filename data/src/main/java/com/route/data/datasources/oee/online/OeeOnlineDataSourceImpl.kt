package com.route.data.datasources.oee.online

import com.google.firebase.firestore.FirebaseFirestore
import com.route.data.contract.oee.OeeOnlineDataSource
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.oee.OEE
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OeeOnlineDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : OeeOnlineDataSource {
    var listOfPartNUmber = mutableListOf<String>()
    var resultOfExcel = mutableListOf<Map<String, Any>>()
    override suspend fun postData(oee: OEE): String {
        return safeData {
            if (oee.Shift.isNullOrEmpty() && oee.Machine.isNullOrEmpty()
                && oee.PartNumber.isNullOrEmpty() && oee
                    .Date.isNullOrEmpty() && oee.Time.isNullOrEmpty()
            ) {
                "Please Enter Data"
            } else {
                firestore.collection(Constants.OEE_COLLECTION).add(oee).await()
                "Added Successfully"
            }
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
                if (!resultOfExcel.contains(list.data)) {
                    resultOfExcel.add(list.data)
                }
            }
            resultOfExcel
        }
    }

    override suspend fun editData(oee: OEE): String {
        return safeData {
            if (oee.Shift.isNullOrEmpty() && oee.Machine.isNullOrEmpty()
                && oee.PartNumber.isNullOrEmpty() && oee
                    .Date.isNullOrEmpty() && oee.Time.isNullOrEmpty()
            ) {
                "Please Enter Data"
            } else {
                val query = firestore.collection(Constants.OEE_COLLECTION)
                    .whereEqualTo("partNumber", oee.PartNumber)
                    .whereEqualTo("shift", oee.Shift)
                    .whereEqualTo("cavityNumber", oee.cavityNumber)
                    .whereEqualTo("date", oee.Date)
                    .whereEqualTo("machine", oee.Machine)
                    .get().await()
                for (document in query.documents) {
                    firestore.collection(Constants.OEE_COLLECTION)
                        .document(document.id)
                        .set(oee)
                        .await()
                }
                "Edit Successfully"
            }
        }
    }
}