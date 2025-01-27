package scisrc.mobiledev.testtabactivity.adapters

import android.content.Context
import android.util.Log
import java.io.File

class CacheManager(private val context: Context) {
    // Get cache directory
    private val cacheDir: File = context.cacheDir

    // Write to cache
    fun writeToCache(fileName: String, data: String) {
        try {
            val file = File(cacheDir, fileName)
            file.writeText(data)
        } catch (e: Exception) {
            Log.e("CacheManager", "Error writing to cache: ${e.message}")
        }
    }

    // Read from cache
    fun readFromCache(fileName: String): String? {
        return try {
            val file = File(cacheDir, fileName)
            if (file.exists()) {
                file.readText()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("CacheManager", "Error reading from cache: ${e.message}")
            null
        }
    }

    // Delete specific cache file
    fun deleteCacheFile(fileName: String): Boolean {
        return try {
            val file = File(cacheDir, fileName)
            file.delete()
        } catch (e: Exception) {
            Log.e("CacheManager", "Error deleting cache file: ${e.message}")
            false
        }
    }

    // Clear all cache
    fun clearCache() {
        try {
            cacheDir.listFiles()?.forEach { file ->
                file.delete()
            }
        } catch (e: Exception) {
            Log.e("CacheManager", "Error clearing cache: ${e.message}")
        }
    }

    // Get cache file size
    fun getCacheSize(fileName: String): Long {
        val file = File(cacheDir, fileName)
        return if (file.exists()) file.length() else 0
    }

    // Check if cache file exists
    fun cacheFileExists(fileName: String): Boolean {
        return File(cacheDir, fileName).exists()
    }

    // Get cache file last modified time
    fun getLastModified(fileName: String): Long {
        val file = File(cacheDir, fileName)
        return if (file.exists()) file.lastModified() else 0
    }
}