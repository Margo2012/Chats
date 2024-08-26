package com.example.connectme.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chats.data.model.User
import com.example.connectme.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userProfile: MutableStateFlow<User?> = MutableStateFlow(null)
    val userProfile: StateFlow<User?> = _userProfile

    init {
        viewModelScope.launch {
            userRepository.getUserProfile().collect { user ->
                _userProfile.value = user
            }
        }
    }
}