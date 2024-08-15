package com.route.data.datasources.oee.online

import com.google.firebase.firestore.FirebaseFirestore
import com.route.data.contract.oee.OeePostOnlineDataSource
import com.route.data.utils.Constants
import com.route.data.utils.safePostData
import com.route.domain.models.oee.OEE
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OeePostOnlineDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : OeePostOnlineDataSource {
    override suspend fun postData(oee: OEE) {
        safePostData {
            firestore.collection(Constants.OEE_COLLECTION).add(oee).await()
        }
    }
}