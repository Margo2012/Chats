package com.example.chats.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectme.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authorizationResult = MutableStateFlow(false)
    val authorizationResult: StateFlow<Boolean> = _authorizationResult

    fun sendPhoneNumberAndRequestCode(phoneNumber: String) {
        viewModelScope.launch {
            userRepository.sendAuthCode(phoneNumber)
        }
    }

    fun verifyCode(phoneNumber: String, code: String) {
        viewModelScope.launch {
            val isAuthorized = userRepository.authenticate(phoneNumber, code)
            _authorizationResult.value = true
        }
    }
}