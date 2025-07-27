package Splitwise.SplitStrategy;

import Splitwise.Split;
import Splitwise.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> calculateSplits(Map<User, Double> splitData, double totalAmount);
}
