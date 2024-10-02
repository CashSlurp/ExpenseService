package com.example.expenseservice.service;

import com.example.expenseservice.entity.Expense;
import com.example.expenseservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findById(String id) {
        return expenseRepository.findById(id);
    }

    public Expense create(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void delete(String id) {
        expenseRepository.deleteById(id);
    }

}
