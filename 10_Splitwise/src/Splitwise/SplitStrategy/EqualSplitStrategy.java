package Splitwise.SplitStrategy;

import Splitwise.Split;
import Splitwise.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplits(Map<User, Double> splitData, double totalAmount) {
        int numberOfUser =  splitData.size();
        double equalAmount = totalAmount / numberOfUser;

        List<Split> splits = new ArrayList<>();
        for( User user : splitData.keySet() ) {
            splits.add(new Split(user, equalAmount));
        }
        return splits;
    }
}
