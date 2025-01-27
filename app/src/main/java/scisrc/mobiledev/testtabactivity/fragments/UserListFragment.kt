package scisrc.mobiledev.testtabactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import scisrc.mobiledev.testtabactivity.R
import scisrc.mobiledev.testtabactivity.adapters.UserAdapter
import scisrc.mobiledev.testtabactivity.data.User
import scisrc.mobiledev.testtabactivity.data.UserViewModel
import scisrc.mobiledev.testtabactivity.data.UserViewModelFactory
import scisrc.mobiledev.testtabactivity.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    // private val userViewModel: UserViewModel by viewModels()
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeUsers()
        setupClickListeners()
    }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(requireActivity().application)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@UserListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeUsers() {
        lifecycleScope.launch {
            userViewModel.allUsers.collect { users ->
                adapter.submitList(users)
                binding.textEmpty.visibility = if (users.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupClickListeners() {
        binding.buttonAdd.setOnClickListener {
            // Show dialog or navigate to add user screen
            showAddUserDialog()
        }
    }

    private fun showAddUserDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_user, null)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add New User")
            .setView(dialogView)
            .setPositiveButton("Add", null) // Set to null initially
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val firstName = dialogView.findViewById<TextInputEditText>(R.id.editFirstName)
                    .text.toString().trim()
                val lastName = dialogView.findViewById<TextInputEditText>(R.id.editLastName)
                    .text.toString().trim()
                val email = dialogView.findViewById<TextInputEditText>(R.id.editEmail)
                    .text.toString().trim()

                if (validateInput(firstName, lastName, email)) {
                    val user = User(
                        firstName = firstName,
                        lastName = lastName,
                        email = email
                    )
                    userViewModel.insertUser(user)
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }

    private fun validateInput(firstName: String, lastName: String, email: String): Boolean {
        if (firstName.isEmpty()) {
            showError("Please enter first name")
            return false
        }
        if (lastName.isEmpty()) {
            showError("Please enter last name")
            return false
        }
        if (email.isEmpty()) {
            showError("Please enter email")
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Please enter valid email")
            return false
        }
        return true
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}