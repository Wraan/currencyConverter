package com.example.currencyconverter

data class RatesInfoDto(
    val base: String,
    val date: String,
    val rates: RatesDto
){
    constructor() : this("", "", RatesDto())
}