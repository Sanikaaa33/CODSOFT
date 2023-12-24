package codsoft.task.pkg1.CodSoft_Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false; 
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return bankAccount.withdraw(amount);
    }

    public double checkBalance() {
        return bankAccount.getBalance();
    }
}

class ATMGUI extends JFrame {
    private ATM atm;

    public ATMGUI(ATM atm) {
        this.atm = atm;

      
        setTitle("ATM Machine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");

        
        setLayout(new GridLayout(3, 1));
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);

       
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter deposit amount:");
                try {
                    double amount = Double.parseDouble(input);
                    atm.deposit(amount);
                    displayMessage("Deposit successful. New balance: $" + atm.checkBalance());
                } catch (NumberFormatException ex) {
                    displayMessage("Invalid input. Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter withdrawal amount:");
                try {
                    double amount = Double.parseDouble(input);
                    if (atm.withdraw(amount)) {
                        displayMessage("Withdrawal successful. New balance: $" + atm.checkBalance());
                    } else {
                        displayMessage("Insufficient funds.");
                    }
                } catch (NumberFormatException ex) {
                    displayMessage("Invalid input. Please enter a valid number.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayMessage("Current balance: $" + atm.checkBalance());
            }
        });
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

public class CodSoft2 {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMGUI(atm).setVisible(true);
            }
   });
}
}
