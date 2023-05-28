package core;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

public class Billing {

	public static double calculateTotalCost(ShoppingBaskets sb) {
		return sb.getItemList().stream().map(item->calculateCostforItem(item)).collect(Collectors.toList()).stream().reduce(BigDecimal.ZERO,BigDecimal::add).doubleValue();
		//return roundingUp(sb.getItemList().stream().mapToDouble(item ->calculateCostforItem(item)).sum());
		
	}
	public static double calculateTotalTax(ShoppingBaskets sb) {
	return sb.getItemList().stream().map(item->calculateTaxforItem(item)).collect(Collectors.toList()).stream().reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
	 
	}
	public static BigDecimal calculateCostforItem(Item item) {
		return (item.getPrice().add(calculateTaxforItem(item)));
	}
public static BigDecimal calculateTaxforItem(Item item) {
		
	BigDecimal salesTaxRate = new BigDecimal(getSalesTaxRate(item));
	BigDecimal salesTax = item.getPrice().multiply(salesTaxRate.divide(BigDecimal.valueOf(100)));
        return roundingUp(salesTax);
	}
public static BigDecimal roundingUp(BigDecimal cost) {
	
	BigDecimal multiply = cost.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(0.05));
	BigDecimal roundedTax = multiply.setScale(2,RoundingMode.HALF_UP);
	 return roundedTax;
	

}
public static double getSalesTaxRate(Item item) {
    double basicSalesTaxRate = 10.0;
    double importDutyRate = 5.0;

    if (item.getCategory() == ItemCategory.BOOK ||item.getCategory() == ItemCategory.FOOD ||
    		item.getCategory()== ItemCategory.MEDICAL_PRODUCT) {
        basicSalesTaxRate = 0;
    }

    if (item.isImported()) {
        return basicSalesTaxRate + importDutyRate;
    } else {
        return basicSalesTaxRate;
    }
}
}
