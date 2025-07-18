package Observable;

import Observer.NotificationAlertObserver;


import java.util.ArrayList;
import java.util.List;

public class IPhoneObservableImpl implements StockObservable {
    public List<NotificationAlertObserver> observerList = new ArrayList<>();
    public int stockCount = 0;


    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for(NotificationAlertObserver observer:observerList){
            observer.update();
        }
    }

    @Override
    public void setStockCount(int newStockCount) {
        if(this.stockCount == 0){
            notifySubscribers();
        }
        this.stockCount  += newStockCount;
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}
