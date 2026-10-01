package com.prep.m03_encapsulation.exercises;

/**
 * Exercise 1 [I]: Encapsulation and Invariants
 *
 * Design a BankAccount class with encapsulation.
 * Invariant: balance >= 0.
 */
public class Ex1_BankAccount {

    private double balance;

    // TODO: Implement constructor (balance must be >= 0)
    // TODO: Implement getBalance() - return the balance
    // TODO: Implement deposit(amount) - add to balance (amount > 0)
    // TODO: Implement withdraw(amount) - subtract from balance (amount > 0, balance >= amount)

    public double getBalance() {
        throw new UnsupportedOperationException("TODO");
    }

    public void deposit(double amount) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean withdraw(double amount) {
        throw new UnsupportedOperationException("TODO: return true if successful, false otherwise");
    }
}
