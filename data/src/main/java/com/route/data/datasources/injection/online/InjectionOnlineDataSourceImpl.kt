package com.route.data.datasources.injection.online

import com.google.firebase.firestore.FirebaseFirestore
import com.route.data.contract.injection.online.InjectionOnlineDataSource
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.injection.InjectionData
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InjectionOnlineDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : InjectionOnlineDataSource {
    var listOfOnInjectionData = mutableListOf<InjectionData>()
    override suspend fun getInjectionData(): List<InjectionData> {
        return safeData {
            val query = firestore.collection(Constants.INJECTION_COLLECTION).get().await()
            for (docs in query.documents) {
                val myData = docs.toObject(InjectionData::class.java)
                myData?.let {
                    listOfOnInjectionData.add(it)
                }
            }
            listOfOnInjectionData
        }
    }
}