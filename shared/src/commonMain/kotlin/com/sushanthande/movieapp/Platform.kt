package com.sushanthande.movieapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform