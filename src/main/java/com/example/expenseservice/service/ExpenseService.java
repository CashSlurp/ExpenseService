package com.example.expenseservice.service;

import com.example.expenseservice.entity.Expense;
import com.example.expenseservice.repository.ExpenseRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    public Expense addExpense(String username, String expense, Double amount, String category) {
        Expense userExpense = mongoTemplate.findById(username, Expense.class);

        if (userExpense == null) {
            userExpense = new Expense();
            userExpense.setUsername(username);
        }

        Expense.ExpenseEntry newExpense = new Expense.ExpenseEntry();
        newExpense.setExpense(expense);
        newExpense.setAmount(amount);
        newExpense.setDate(new Date());
        newExpense.setCategory(category);

        userExpense.getExpenses().add(newExpense);

        return mongoTemplate.save(userExpense);
    }

    public Optional<Expense> getExpensesByUsername(String username) {
        return Optional.ofNullable(mongoTemplate.findById(username, Expense.class));
    }

    @KafkaListener(topics = "user-creation", groupId = "expense-service-group")
    public void listen(String username) {
        System.out.printf(username);
        Expense userExpense = mongoTemplate.findById(username, Expense.class);
        if (userExpense == null) {
            userExpense = new Expense();
            userExpense.setUsername(username);
        }
        mongoTemplate.save(userExpense);
    }

}
