package Atm;

import Atm.Dispenser.DispenseChain;
import Atm.Dispenser.NoteDispenser100;
import Atm.Dispenser.NoteDispenser20;
import Atm.Dispenser.NoteDispenser50;
import Atm.State.ATMState;
import Atm.State.IdleState;

import java.util.concurrent.atomic.AtomicLong;

public class ATM {
    private static ATM INSTANCE;
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    private static final AtomicLong transactionCounter = new AtomicLong(0);
    private ATMState currentState;
    private Card currentCard;

    private ATM(){
        this.currentState = new IdleState();
        this.bankService = new BankService();

        DispenseChain c1 = new NoteDispenser100(10);
        DispenseChain c2 = new NoteDispenser50(20);
        DispenseChain c3 = new NoteDispenser20(30);

        c1.setNextChain(c2);
        c2.setNextChain(c3);

        this.cashDispenser = new CashDispenser(c1);
    }

    public static ATM getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ATM();
        }
        return INSTANCE;
    }

    public void changeState(ATMState newState){
        this.currentState = newState;
    }

    public void setCurrentCard(Card card){
        this.currentCard = card;
    }

    public void insertCard(String cardNumber){
        currentState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin){
        currentState.enterPin(this,pin);
    }

    public void selectOperation(OperationType op, int... args){
        currentState.selectOperation(this, op, args);
    }

    public Card getCard(String cardNumber){
        return bankService.getCard(cardNumber);
    }

    public boolean authenticate(String pin){
        return bankService.authenticate( currentCard, pin);
    }

    public void checkBalance(){
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your current account balance is: $%.2f%n", balance);
    }

    public void withdrawCash(int amount){
        if(!cashDispenser.canDispenseCash(amount)){
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        bankService.withdrawMoney(currentCard, amount);

        try {
            cashDispenser.dispenseCash(amount);
        } catch (Exception e) {
            bankService.depositMoney(currentCard, amount); // Deposit back if dispensing fails
        }
    }

    public void depositCash(int amount){
        bankService.depositMoney(currentCard, amount);
    }

    public Card getCurrentCard(){
        return currentCard;
    }

    public BankService getBankService() {
        return bankService;
    }


}










