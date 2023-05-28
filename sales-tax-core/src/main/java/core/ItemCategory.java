package core;
/**
 * 
 */

/**
 * @author ganesh
 *
 */
public enum ItemCategory {

	BOOK(true),
	FOOD(true),
	MEDICAL_PRODUCT(true),
	OTHER(false);
	
	private boolean isSalesTaxExempted;

	private ItemCategory(boolean isSalesTaxExempted) {
this.isSalesTaxExempted = isSalesTaxExempted;
	}
	
	public boolean isSalesTaxExempted() {
		return this.isSalesTaxExempted;
	}
	
}
