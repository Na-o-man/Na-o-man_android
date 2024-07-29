package com.hgh.na_o_man.di.util.s3

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject

class S3Util @Inject constructor() {
    suspend fun uploadImageWithPreSignedUrl(file: File, signedUrl: String) = withContext(Dispatchers.IO) {
        val requestBody = file.asRequestBody("image/png".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(signedUrl)
            .put(requestBody)
            .build()

        try {
            val response = OkHttpClient().newCall(request).execute()
            if (response.isSuccessful) {
                Log.d("S3LOG", "File(Image) uploaded successfully: ${file.name}")
            } else {
                val errorBody = response.body?.string() ?: "No error details"
                throw IOException("Failed to upload file(Image): ${file.name}, Response: $errorBody")
            }
        }catch (e:Exception){
            Log.e("S3LOG", "File upload failed(Image): ${file.name}", e)
        }
    }
}

