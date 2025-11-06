package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    private static final int AMOUNT = 100;
    public static final double TRANSACTION_FEE = 0.1;
    public static final double MANAGEMENT_FEE = 5;
    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(0.0,bankAccount.getBalance());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        int i = bankAccount.getTransactionsCount();
        double tot = bankAccount.getBalance();

        bankAccount.deposit(mRossi.getUserID(), AMOUNT);
        i++;
        tot += AMOUNT;
        assertEquals(tot, bankAccount.getBalance());
        assertEquals(i, bankAccount.getTransactionsCount());

        bankAccount.chargeManagementFees(mRossi.getUserID());
        tot -= (i * TRANSACTION_FEE) + MANAGEMENT_FEE;
        assertEquals(tot, bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        double tot = bankAccount.getBalance();
        int dim = -AMOUNT;
        try {
            bankAccount.withdraw(mRossi.getUserID(),dim);
            Assertions.fail("The withdrawal should had been blocked 'cause it's impossible to withdraw a negative amount");
        } catch (IllegalArgumentException e) {
            assertEquals(tot, bankAccount.getBalance());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        double tot = bankAccount.getBalance();
        double dim = tot + AMOUNT;
        try {
            bankAccount.withdraw(mRossi.getUserID(),dim);
            Assertions.fail("The withdrawal should had been blocked 'cause it's impossible to withdraw more than the account balance");
        } catch (IllegalArgumentException e) {
            assertEquals(tot, bankAccount.getBalance());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
        }
    }
}
