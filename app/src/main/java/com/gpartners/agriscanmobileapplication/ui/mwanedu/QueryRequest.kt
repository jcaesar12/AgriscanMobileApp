package com.gpartners.agriscanmobileapplication.ui.mwanedu

data class QueryRequest(
    val text: String,
    val top_k: Int = 5
)