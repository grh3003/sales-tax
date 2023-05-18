import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GenerateSalesTaxTest {

	@Test
	void testItemList() {
		Item item1 = new Item("Item1", 10, ItemCategory.OTHER,false);
		ShoppingBaskets sb = new ShoppingBaskets();
		sb.addItem(item1);

		assertTrue(sb.getItemList().size() == 1);

	}

	@Test
	public void testReceiptGeneration() {

		/**
		 * 
		 * Input 1: > 1 book at 12.49 > 1 music CD at 14.99 > 1 chocolate bar at 0.85
		 * 
		 * Output 1: > 1 book: 12.49 > 1 music CD: 16.49 > 1 chocolate bar: 0.85 > Sales
		 * Taxes: 1.50 > Total: 29.83
		 */

		Item item1 =  new Item("book",12.49,ItemCategory.BOOK,false);
		Item item2 =  new Item("music CD",14.99,ItemCategory.OTHER,false);
		Item item3 =  new Item("chocolate bar",0.85,ItemCategory.OTHER,false);
		
		ShoppingBaskets sb =  new ShoppingBaskets();
		sb.addItem(item1);
		sb.addItem(item2);
		sb.addItem(item3);
		// testing
		assertTrue(sb.getItemList().size()==3);
		
		
		Billing.calculateTotalCost(sb);
		Billing.calculateTotalTax(sb);
		
		
		// test cost of each item after tax
		
		assertTrue(item1.getPriceWithTax()==12.49);
		
	}

}
