package lab6.baseClasses;

import lab6.ownExceptions.NotEnoughMoneyException;
import lab6.ownExceptions.TransactionsIsCompletedYet;

import java.util.Objects;

public class Transaction {

    private int ID;
    private Account fromID;
    private Account toID;
    private int amount;
    private boolean isCompleted;

    public Transaction(int ID, Account fromID, Account toID, int amount) {
        if (fromID == null || toID == null) {
            throw new IllegalArgumentException("Accounts cannot be be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.ID = ID;
        this.fromID = fromID;
        this.toID = toID;
        this.amount = amount;
        this.isCompleted = false;
    }

    public void executeTransaction() throws NotEnoughMoneyException, TransactionsIsCompletedYet {
        if (isCompleted) {
            throw new TransactionsIsCompletedYet("transaction is done");
        }
        if (isNotEnoughMoney()) {
            throw new NotEnoughMoneyException("transaction id=" + ID + " was decline because not enough money(" +
                (amount - fromID.getCash()) + ") from id=" + fromID.getID() + ", to id=" + toID.getID());
        }
        fromID.outcome(amount);
        toID.income(amount);
        isCompleted = true;
    }

    private boolean isNotEnoughMoney() {
        return (fromID.getCash() - amount) < 0;
    }

    public int getID() {
        return ID;
    }

    public Account getFromID() {
        return fromID;
    }

    public Account getToID() {
        return toID;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return ID == that.getID() &&
            amount == that.getAmount() &&
            fromID.equals(that.getFromID()) &&
            toID.equals(that.getToID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, fromID, toID);
    }
}