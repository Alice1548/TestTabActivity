package scisrc.mobiledev.testtabactivity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import scisrc.mobiledev.testtabactivity.databinding.FragmentTab1Binding

class Tab1 : Fragment() {

    private var _binding: FragmentTab1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ONLY inflate layout here
        _binding = FragmentTab1Binding.inflate(inflater, container, false)
        return binding.root
    }

    // function to do the action in this fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListener()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setListener() {
        binding.button.setOnClickListener() {
            Snackbar.make(binding.root, "The Button is Clicked", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), TabViewActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener() {
            Snackbar.make(binding.root, "The Button is Clicked", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), BottomViewActivity::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener() {
            Snackbar.make(binding.root, "The Button is Clicked", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), LeftMenuActivity::class.java)
            startActivity(intent)
        }
    }

}