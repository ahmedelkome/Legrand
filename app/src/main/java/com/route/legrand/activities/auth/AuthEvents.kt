package com.route.legrand.activities.auth

sealed class AuthEvents  {
    data object navigateToMain: AuthEvents()
}