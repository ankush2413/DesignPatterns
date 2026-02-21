package splitwisesystem;
import splitwisesystem.interfaces.CurrencyConverter;
import java.util.*;
import splitwisesystem.enums.Currency;

public class SimpleCurrencyConverter implements CurrencyConverter {
    // Mock exchange rates (Base = USD)
    private static final Map<Currency, Double> rates = new HashMap<>();
    static {
        rates.put(Currency.USD, 1.0);
        rates.put(Currency.INR, 83.0);
        rates.put(Currency.EUR, 0.92);
    }

    @Override
    public double convert(double amount, Currency from, Currency to) {
        double amountInUSD = amount / rates.get(from);
        return amountInUSD * rates.get(to);
    }
}