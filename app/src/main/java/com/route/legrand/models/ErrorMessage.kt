package com.route.legrand.models

data class ErrorMessage(
    val title:String?=null,
    val message:String?=null,
    val posTitle:String?=null,
    val negTitle:String?=null,
    val posClick : (()->Unit)?=null,
    val negClick : (()->Unit)?=null,
) {
}