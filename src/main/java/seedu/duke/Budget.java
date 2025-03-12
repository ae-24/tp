package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a Budget that tracks expenses within a specific category.
 * The budget can have an optional spending limit.
 */
public class Budget {
    private final String category;
    private final double limit; //Optional
    private final ArrayList<Expense> expenses;

    /**
     * Constructs a Budget object with the given category and spending limit.
     *
     * @param category The name of the budget category (e.g., "Food", "Transport").
     * @param limit The spending limit for the budget.
     */
    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
        this.expenses = new ArrayList<>();
    }

    /**
     * Adds an expense to the list of expenses for this budget.
     *
     * @param expense The expense to add to this budget.
     */
    public void addExpense (Expense expense) {
        expenses.add(expense);
    }

    /**
     * Calculates the total amount of all expenses in this budget.
     *
     * @return The total expenses for the budget.
     */
    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(e -> e.amount).sum();
    }

}
