
/**
 * @author ganesh
 * its model class for Items
 */
public class Item {

	private String name;
	private double price;
	
	private boolean isImported;
	private ItemCategory category;

	public Item(String name,double price,ItemCategory category, boolean isExempted) {

	this.name = name;
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
	public double getPrice() {
		return price;
	}

	/**
	 * @return the isExempted
	 */
	public boolean isExempted() {
		return isImported;
	}
	public double getPriceWithTax() {

		return price+calculateTax();
	}
	public double calculateTax() {
		double finalTax = 0.0;
		
		if(!category.isSalesTaxExempted()) {
			finalTax = finalTax+(price*0.10);
		}
		if(isImported) {
			//add extra 5% with no exemption
			finalTax = finalTax+(price*0.05);
		}
		
		
		return finalTax;
	}

}
