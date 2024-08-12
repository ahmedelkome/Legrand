package com.route.legrand.fragments.auth

sealed class AuthEvents  {
    data object navigateToHome: AuthEvents()
}