package com.example.expenseservice.controller;

import com.example.expenseservice.entity.Expense;
import com.example.expenseservice.service.ExpenseService;
import org.apache.kafka.common.protocol.types.Field;
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

    @PostMapping("/{username}")
    public Expense addExpense(
            @PathVariable String username,
            @RequestParam String expense,
            @RequestParam Double amount,
            @RequestParam String category) {
        return expenseService.addExpense(username, expense, amount, category);
    }

    @GetMapping("/{username}/all")
    public Optional<Expense> getExpensesByUsername(@PathVariable String username) {
        return expenseService.getExpensesByUsername(username);
    }

}
