package com.example.expenseservice.controller;

import com.example.expenseservice.entity.Expense;
import com.example.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> findAll() {
        return expenseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Expense> findById(@PathVariable String id) {
        return expenseService.findById(id);
    }

    @PostMapping
    public Expense create(@RequestBody Expense expense) {
        return expenseService.create(expense);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        expenseService.delete(id);
    }

}
