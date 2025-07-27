package Atm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Map<String, Account>  accounts = new ConcurrentHashMap<>();
    private final Map<String, Card>  cards = new ConcurrentHashMap<>();
    private final Map<Card, Account> cardAccounts = new ConcurrentHashMap<>();

    public BankService() {
        Account account1 = createAccount("1234567890", 1000.0);
        Card card1 = createCard("1234-5678-9012-3456", "1234");
        linkCardToAccount(card1, account1);

        Account account2 = createAccount("9876543210", 500.0);
        Card card2 = createCard("9876-5432-1098-7654", "4321");
        linkCardToAccount(card2, account2);
    }

    public Account createAccount(String accountNumber, double initialBalance) {
        Account account = new Account(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public Card createCard(String cardNumber, String pin) {
        Card card = new Card(cardNumber, pin);
        cards.put(cardNumber, card);
        return card;
    }

    public boolean authenticate(Card card, String pin) {
        return card.getPin().equals(pin);
    }

    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }

    public double getBalance(Card card) {
        return cardAccounts.get(card).getBalance();
    }

    public void withdrawMoney(Card card, double amount) {
        cardAccounts.get(card).withdraw(amount);
    }

    public void depositMoney(Card card, double amount) {
        cardAccounts.get(card).deposit(amount);
    }

    public void linkCardToAccount(Card card, Account account) {
        account.getCards().put(card.getCardNumber(), card);
        cardAccounts.put(card, account);
    }
}
