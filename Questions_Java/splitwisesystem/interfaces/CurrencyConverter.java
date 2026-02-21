package splitwisesystem.interfaces;
import splitwisesystem.enums.Currency;

public interface CurrencyConverter {
    double convert(double amount, Currency from, Currency to);
}