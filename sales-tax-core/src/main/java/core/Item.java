package core;
import java.math.BigDecimal;

/**
 * @author ganesh
 * its model class for Items
 */
public class Item {

	private String name;
	private BigDecimal price;
	
	private boolean isImported;
	private ItemCategory category;

	public Item(String name,BigDecimal price,ItemCategory category, boolean isExempted) throws InvalidPriceException {

	this.name = name;
	if(price==null ||price.doubleValue()<0) {
		 throw new InvalidPriceException("price can not be negative!!");
	}
	this.price = price;
	this.isImported = isExempted;
	this.category = category ;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @return the isExempted
	 */
	public boolean isImported() {
		return isImported;
	}
public ItemCategory getCategory() {
	return this.category;
}

}
