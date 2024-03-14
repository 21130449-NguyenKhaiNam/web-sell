package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatCurrency {
    public static String vietNamCurrency(double amount){
        Locale vietnameseLocale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(vietnameseLocale);
        String formattedAmount = currencyFormat.format(amount);
        return formattedAmount;
    }
}
