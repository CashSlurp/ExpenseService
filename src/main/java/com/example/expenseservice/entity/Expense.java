package com.example.expenseservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "expenses")
public class Expense {

    @Id
    private String username;
    private List<ExpenseEntry> expenses = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ExpenseEntry> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseEntry> expenses) {
        this.expenses = expenses;
    }

    public static class ExpenseEntry {

        private String expense;
        private Double amount;
        private Date date;
        private String category;

        public String getExpense() {
            return expense;
        }

        public void setExpense(String expense) {
            this.expense = expense;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

    }

}
