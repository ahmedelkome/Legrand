package com.route.data.datasources.injection.online

import android.content.Context
import android.os.Environment
import androidx.core.net.toFile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.route.data.contract.injection.online.InjectionOnlineDataSource
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.injection.InjectionData
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class InjectionOnlineDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val context: Context
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

    override suspend fun getParameterSheet(partNumber: String): File? {
        return safeData {
            val basePath = "parameterSheet/"
            val xlsPath = "${basePath}$partNumber.xls"
            val pdfPath = "${basePath}$partNumber.pdf"

            val xlsRef =
                firebaseStorage.getReferenceFromUrl("gs://legrand-9180d.appspot.com/$xlsPath")
            val pdfRef =
                firebaseStorage.getReferenceFromUrl("gs://legrand-9180d.appspot.com/$pdfPath")

            val downloadDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            var localFile: File? = null

            try {
                localFile = File(downloadDir, "$partNumber.xls")
                xlsRef.getFile(localFile).await()
            } catch (e: Exception) {
                try {
                    localFile = File(downloadDir, "$partNumber.pdf")
                    pdfRef.getFile(localFile).await()
                } catch (e: Exception) {
                    throw e
                }

            }
            localFile
        }
    }

    private fun getFileExtension(fileName: String): String {
        return if (fileName.endsWith(".pdf")) ".pdf" else ".xls"
    }

//    private suspend fun getFileReference(path: String): StorageReference? {
//        return safeData {
//            val ref = firebaseStorage.reference.child(path)
//            ref.metadata.await()
//            ref
//        }
//    }
//
//    private suspend fun downloadFileAsByteArray(ref: StorageReference): ByteArray? {
//        return safeData {
//            val byte = ref.getBytes(Long.MAX_VALUE).await()
//            byte
//        }
//    }
}