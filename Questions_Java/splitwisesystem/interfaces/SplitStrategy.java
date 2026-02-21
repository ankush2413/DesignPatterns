package splitwisesystem.interfaces;
import splitwisesystem.Split;
import java.util.List;

public interface SplitStrategy {
    boolean validateSplit(List<Split> splits, double totalAmount);
    void calculateSplits(List<Split> splits, double totalAmount);
}