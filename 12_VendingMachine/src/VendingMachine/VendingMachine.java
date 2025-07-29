package VendingMachine;

import VendingMachine.States.IdleState;
import VendingMachine.States.State;

public class VendingMachine {
    private static VendingMachine INSTANCE;
    private final Inventory inventory = new Inventory();
    private State currentState;
    private int balance = 0;
    private String selectedItemCode;

    public VendingMachine(){
        currentState = new IdleState(this);
    }

    public static VendingMachine getInstance(){
        if(INSTANCE == null){
            INSTANCE = new VendingMachine();
        }
        return INSTANCE;
    }

    public void insertCoin(Coin coin){
        currentState.insertCoin(coin);
    }

    public Item addItem(String code, String name, int price, int quantity){
        Item item = new Item(code, name, price);
        inventory.addItem(code, item, quantity);
        return item;
    }

    public void selectItem(String code){
        currentState.selectItem(code);
    }

    public void dispense(){
        currentState.dispense();
    }

    public void dispenseItem(){
        Item item = inventory.getItem(selectedItemCode);
        if(balance >= item.getPrice()){
            inventory.reduceStock(selectedItemCode);
            balance -= item.getPrice();
            System.out.println("Dispensed: " + item.getName());
            if(balance > 0){
                System.out.println("Returning change: " + balance);
            }
        }

        reset();
        setState(new IdleState(this));
    }

    public void refundBalance(){
        System.out.println("Refunding: " + balance);
        balance = 0;
    }

    public void reset(){
        selectedItemCode = null;
        balance = 0;
    }

    public void addBalance(int amount){
        balance += amount;
    }

    public Item getSelectedItem(){
        return inventory.getItem(selectedItemCode);
    }

    public void setSelectedItemCode(String code){
        selectedItemCode = code;
    }

    public void setState(State state){
        this.currentState = state;
    }

    public Inventory getInventory() { return inventory; }
    public int getBalance() { return balance; }

}
