package com.hgh.na_o_man

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.work.Configuration
import com.hgh.na_o_man.di.util.remote.NetworkStatusChecker
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), DefaultLifecycleObserver, Configuration.Provider {

    @Inject
    lateinit var workFactory: HiltWorkerFactory

    override fun onCreate() {
        super<Application>.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_key))
        networkConnectionChecker = NetworkStatusChecker(applicationContext)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "image_channel",
                "사진 업로드/다운로드",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "image Upload Notification"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        networkConnectionChecker.unregister()
        super.onStop(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        networkConnectionChecker.register()
        super.onStart(owner)
    }

    fun getKeyHash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val packageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            for (signature in packageInfo.signingInfo.apkContentsSigners) {
                try {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d(
                        "getKeyHash",
                        "key hash: ${Base64.encodeToString(md.digest(), Base64.NO_WRAP)}"
                    )
                } catch (e: NoSuchAlgorithmException) {
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature=$signature", e)
                }
            }
        }
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workFactory)
            .build()


    companion object {
        private lateinit var networkConnectionChecker: NetworkStatusChecker
        fun isOnline() = networkConnectionChecker.isOnline()
    }
}