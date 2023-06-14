package com.example.adv160420142week4.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.adv160420142week4.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
            }
        })
}

@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoURL(view: ImageView, url: String, pb:ProgressBar){
    view.loadImage(url,pb)
}


fun createNotificationChannel(context: Context, importance: Int,
                              showBadge: Boolean, name: String, description: String) {
    val channelId = "${context.packageName}-$name"
    val channel = NotificationChannel(channelId, name, importance)
    channel.description = description
    channel.setShowBadge(showBadge)

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.createNotificationChannel(channel)
}
