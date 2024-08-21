public class Account {
    private double balance;
    private final String accountHolder;

    public Account(){
        balance = 0;
        accountHolder = "Guest";
    }
    public Account(int balance){
        this.balance = balance;
        accountHolder = "Guest";
    }
    public Account(int balance, String name){
        this.balance = balance;
        accountHolder = name;
    }

    public double getBal(){
        return balance;
    }

    /**
     * Adds the specified amount to the current balance.
     *
     * @param amt Amount to deposit
     * @return The updated balance
     */
    public double deposit(int amt){
        this.balance += amt;
        return this.balance;
    }
    /**
     * Subtracts the specified amount to the current balance.
     *
     * @param amt Amount to withdraw
     * @return The updated balance
     */
    public double withdraw(int amt){
        this.balance -= amt;
        checkUnderdraw();
        return this.balance;
    }

    /**
     * Checks if the current balances is greater than or equal to $0
     *
     * @return True if <= 0, false otherwise
     */
    private boolean checkUnderdraw(){
        if(balance <= 0){
            System.out.printf("%s's gone in the red!\n", accountHolder);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Transfers the specifid amount to the target
     *
     * @param target The account to transfer too
     * @param amt The amount to transfer
     * @return The toString values of the involved accounts.
     */
    public String transfer(Account target, int amt){
        if(amt > balance){
            return "The transaction could not be completed.";
        } else {
            target.deposit(amt);
            balance -= amt;
            return toString() + "\n" + target.toString();
        }
    }
    public String toString(){
        return String.format("%s's Balance: %.2f", accountHolder, balance);
    }
}
