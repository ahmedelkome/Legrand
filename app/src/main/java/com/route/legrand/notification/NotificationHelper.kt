package com.route.legrand.notification


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.route.legrand.R
import java.io.File
import java.io.FileOutputStream


class NotificationHelper(private val context: Context) {
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "DownloadChannel"
            val descriptionText = "Channel for download notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("DOWNLOAD_CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.e("TAG", "createNotificationChannel: ${notificationManager.createNotificationChannel(channel)}", )
        }
    }

    fun showDownloadNotification(newFile: File) {
        Log.e("NotificationHelper", "Showing notification for file: ${newFile.absolutePath}")
        createNotificationChannel()
        // Create an Intent to open the file
        val fileUri = FileProvider.getUriForFile(context, "com.route.legrand.fileprovider", newFile)
        val mimeType = if (newFile.extension == "pdf") "application/pdf" else "application/vnd.ms-excel"
        val fileIntent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(fileUri, mimeType)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }

        // Create a PendingIntent to wrap the fileIntent
        val pendingIntent =
            PendingIntent.getActivity(context, 0, fileIntent, PendingIntent.FLAG_IMMUTABLE)

        // Create the notification
        val notificationBuilder = NotificationCompat.Builder(context, "DOWNLOAD_CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_file_download) // Replace with your icon
            .setContentTitle("File Downloaded")
            .setContentText("Your file has been downloaded and is ready to view.")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // Dismiss the notification when clicked
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        // Notify
        val notificationId = 1
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(notificationId)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}
