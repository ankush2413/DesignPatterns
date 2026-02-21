package splitwisesystem;
import splitwisesystem.interfaces.SplitStrategy;
import java.util.*;

class EqualSplitStrategy implements SplitStrategy {
    @Override
    public boolean validateSplit(List<Split> splits, double totalAmount) {
        return true; // Always valid, we calculate amount ourselves
    }

    @Override
    public void calculateSplits(List<Split> splits, double totalAmount) {
        double amountPerPerson = totalAmount / splits.size();
        for (Split split : splits) {
            split.setAmount(amountPerPerson);
        }
    }
}