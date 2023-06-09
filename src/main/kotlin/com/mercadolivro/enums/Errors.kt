package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML000("ML-000", "Unauthorized"),
    ML001("ML-001", "Invalid Request"),
    ML1001("ML-1001", "Book [%s] not exists"),
    ML1002("ML-1002", "Cannot update book with status [%s]"),
    ML1101("ML-1101", "Customer [%s] not exists")
}