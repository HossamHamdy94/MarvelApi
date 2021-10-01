package com.example.marvelapi.models

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultModel>,
    val total: Int
)