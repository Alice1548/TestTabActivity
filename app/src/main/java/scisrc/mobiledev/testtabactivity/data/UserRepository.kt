package scisrc.mobiledev.testtabactivity.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    suspend fun update(user: User) {
        userDao.updateUser(user)
    }

    suspend fun delete(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}