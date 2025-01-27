package scisrc.mobiledev.testtabactivity.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val allUsers: Flow<List<User>>

    init {
        val database = AppDatabase.getDatabase(application)
        val userDao = database.userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        repository.update(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.delete(user)
    }

    fun getUserById(id: Int) = viewModelScope.launch {
        repository.getUserById(id)
    }

    fun deleteAllUsers() = viewModelScope.launch {
        repository.deleteAllUsers()
    }
}