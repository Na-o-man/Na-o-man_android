package com.hgh.na_o_man.di.util.s3

import android.content.Context
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class S3Util @Inject constructor(
    private val context: Context
) {
    suspend fun uploadImageWithPreSignedUrl(file: File, signedUrl: String) :Result<String> =
        withContext(Dispatchers.IO) {
            val requestBody = file.asRequestBody("image/png".toMediaTypeOrNull())

            val request = Request.Builder()
                .url(signedUrl)
                .put(requestBody)
                .build()

            try {
                val response = OkHttpClient().newCall(request).execute()
                if (response.isSuccessful) {
                    Log.d("S3LOG", "File(Image) uploaded successfully: ${file.name}")
                    return@withContext Result.success(signedUrl)
                } else {
                    val errorBody = response.body?.string() ?: "No error details"
                    throw IOException("Failed to upload file(Image): ${file.name}, Response: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("S3LOG", "File upload failed(Image): ${file.name}", e)
                return@withContext  Result.failure(e)
            }
        }

    fun convertUriToJpegFile( uri: Uri, targetFilename: String): File {
        Log.d("한건희","convertUriToJpegFile ON")
//
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val outputFile = File(context.cacheDir, "$targetFilename")

        inputStream?.use { input ->
            FileOutputStream(outputFile).use { output ->
                val buffer = ByteArray(4 * 1024) // 4KB buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }

        return if (outputFile.exists()) outputFile else outputFile
    }
}

