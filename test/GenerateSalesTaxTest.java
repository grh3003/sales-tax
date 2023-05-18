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
		
		assertEquals(item1.getPriceWithTax(),12.49);
		assertEquals(item2.getPriceWithTax(),16.49);
		assertEquals(item3.getPriceWithTax(),0.85);
		
		/*
		 * 
		*/
	}

	@Test
	public void tesReceiptGeneration2() {
		
		/*
		 * Input 2: 
		 * > 1 imported box of chocolates at 10.00 
		 * > 1 imported bottle of perfume at 47.50
		 * 
		 * Output 2:
           > 1 imported box of chocolates: 10.50
           > 1 imported bottle of perfume: 54.65
		   > Sales Taxes: 7.65
		   > Total: 65.15
		 */
		
		Item item1 =  new Item("chocklates",10.00,ItemCategory.FOOD,true);
		Item item2 =  new Item("perfume",47.50,ItemCategory.OTHER,true);
		
		ShoppingBaskets sb =  new ShoppingBaskets();
		sb.addItem(item1);
		sb.addItem(item2);
		// testing
		assertTrue(sb.getItemList().size()==2);
		
		
		Billing.calculateTotalCost(sb);
		Billing.calculateTotalTax(sb);
		
		
		// test cost of each item after tax
		
		assertEquals(item1.getPriceWithTax(),10.50);
		assertEquals(item2.getPriceWithTax(),4.65);
	}
	
	@Test
	public void tesReceiptGeneration3() {
		
		/*
		 Input 3:
> 1 imported bottle of perfume at 27.99
> 1 bottle of perfume at 18.99
> 1 packet of headache pills at 9.75
> 1 box of imported chocolates at 11.25
		 * 
		 * Output 3:
> 1 imported bottle of perfume: 32.19
> 1 bottle of perfume: 20.89
> 1 packet of headache pills: 9.75
> 1 imported box of chocolates: 11.85
> Sales Taxes: 6.70
> Total: 74.68
		 */
		
		Item item1 =   new Item("perfume",27.99,ItemCategory.OTHER,true);
		Item item2 =  new Item("perfume",18.99,ItemCategory.OTHER,true);
		Item item3 =  new Item("headeach pills",9.75,ItemCategory.MEDICAL_PRODUCT,false);
		Item item4 =  new Item("chocklates",11.25,ItemCategory.OTHER,true);
		ShoppingBaskets sb =  new ShoppingBaskets();
		sb.addItem(item1);
		sb.addItem(item2);
		// testing
		assertTrue(sb.getItemList().size()==2);
		
		
		Billing.calculateTotalCost(sb);
		Billing.calculateTotalTax(sb);
		
		
		// test cost of each item after tax
		
		assertEquals(item1.getPriceWithTax(),32.19);
		assertEquals(item2.getPriceWithTax(),20.89);
		assertEquals(item3.getPriceWithTax(),9.75);
		assertEquals(item4.getPriceWithTax(),11.85);
	}
}
