package com.example.polycareer.domain.usecase

import com.example.polycareer.domain.model.Result
import com.example.polycareer.domain.model.UserDetails
import com.example.polycareer.domain.repository.UserRepository
import com.example.polycareer.utils.isValidEmail
import com.example.polycareer.utils.isValidName

class AuthUseCase(
    private val repository: UserRepository
) : ValidateParam() {
    suspend fun saveUser(user: UserDetails): Result {
        val userId = repository.checkUserEmail(user.email)
            ?: repository.saveUserDetail(user)
            ?: return Result.Error("Failed to save data")

        repository.setCurrentUser(userId)
        return Result.DataCorrect
    }

    suspend fun validateName(name: String): Result =
        validate(name.isValidName())

    suspend fun validateEmail(email: String): Result =
        validate(email.isValidEmail())

    suspend fun validateConf(isChecked: Boolean): Result =
        validate(isChecked)
}