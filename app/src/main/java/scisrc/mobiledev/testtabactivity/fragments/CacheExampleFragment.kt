package scisrc.mobiledev.testtabactivity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import scisrc.mobiledev.testtabactivity.adapters.CacheManager
import scisrc.mobiledev.testtabactivity.databinding.FragmentCacheExampleBinding
import java.util.Date

class CacheExampleFragment : Fragment() {

    private var _binding: FragmentCacheExampleBinding? = null
    private val binding get() = _binding!!

    private lateinit var cacheManager: CacheManager

    private lateinit var multilineText: EditText
    private lateinit var checkCacheButton: Button
    private lateinit var clearCacheButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCacheExampleBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cacheManager = CacheManager(requireActivity())

        bindingUI()

        // Example usage
        readDataFromCache()

        checkCacheButton.setOnClickListener() {
            saveDataToCache()
            checkCacheStatus()
        }

        clearCacheButton.setOnClickListener() {
            deleteCacheFile()
        }
    }

    fun bindingUI() {
        multilineText = binding.editTextTextMultiLine
        checkCacheButton = binding.button4
        clearCacheButton = binding.button5
    }

    private fun saveDataToCache() {
        // Save user data to cache
        val userData = """
            {
                "name": "John Doe",
                "age": 30,
                "email": "john@example.com"
            }
        """.trimIndent()

        cacheManager.writeToCache("user_data.json", userData)
    }

    private fun readDataFromCache() {
        // Read cached data
        val cachedData = cacheManager.readFromCache("user_data.json")
        cachedData?.let {
            multilineText.setText(cachedData)
            Log.d("Cache", "Cached data: $it")
        }
    }

    private fun checkCacheStatus() {
        val fileName = "user_data.json"

        // Check if cache exists
        if (cacheManager.cacheFileExists(fileName)) {
            // Get cache details
            val size = cacheManager.getCacheSize(fileName)
            val lastModified = cacheManager.getLastModified(fileName)

            Toast.makeText(requireActivity(), "Cache Size: $size bytes with ${Date(lastModified)}", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteCacheFile() {
        multilineText.setText("")
        cacheManager.deleteCacheFile("user_data.json")
    }

}