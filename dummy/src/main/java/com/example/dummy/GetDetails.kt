package com.example.dummy

interface GetDetails {
    fun onSuccess(data: String)
    fun onFailure(message: String)
}