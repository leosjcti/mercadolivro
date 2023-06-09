package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val custumerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            //return customers.filter { it.name.contains(name, true) }
            return custumerRepository.findByNameContaining(it)
        }
        return custumerRepository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel {
        return custumerRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML1101.message.format(id), Errors.ML1101.code) }
    }

    fun create(customer: CustomerModel) {
        custumerRepository.save(customer)
    }

    fun update(customer: CustomerModel) {
        if(!custumerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        custumerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        custumerRepository.save(customer)
//        if(!custumerRepository.existsById(id)) {
//            throw Exception()
//        }
    }
}