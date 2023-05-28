package core;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class GenerateSalesTaxTest {

	@Test
	void testItemList() {
		Item item1 = new Item("tesr",BigDecimal.valueOf(12.49),ItemCategory.BOOK,false);
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

		Item item1 =  new Item("book",BigDecimal.valueOf(12.49),ItemCategory.BOOK,false);
		Item item2 =  new Item("music CD",BigDecimal.valueOf(14.99),ItemCategory.OTHER,false);
		Item item3 =  new Item("chocolate bar",BigDecimal.valueOf(0.85),ItemCategory.FOOD,false);
		
		ShoppingBaskets sb =  new ShoppingBaskets();
		sb.addItem(item1);
		sb.addItem(item2);
		sb.addItem(item3);
		// testing
		assertTrue(sb.getItemList().size()==3);
		
		assertEquals(12.49,Billing.calculateCostforItem(item1).doubleValue());
		assertEquals(16.49,Billing.calculateCostforItem(item2).doubleValue());
		assertEquals(0.85,Billing.calculateCostforItem(item3).doubleValue());
		
		assertEquals(29.83,Billing.calculateTotalCost(sb));
		assertEquals(1.50,Billing.calculateTotalTax(sb));
		
		
		
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
		
		Item item1 =  new Item("chocklates",BigDecimal.valueOf(10.00),ItemCategory.FOOD,true);
		Item item2 =  new Item("perfume",BigDecimal.valueOf(47.50),ItemCategory.OTHER,true);
		
		ShoppingBaskets sb =  new ShoppingBaskets();
		sb.addItem(item1);
		sb.addItem(item2);
		// testing
		assertTrue(sb.getItemList().size()==2);
		
		assertEquals(10.50,Billing.calculateCostforItem(item1).doubleValue());
		assertEquals(54.65,Billing.calculateCostforItem(item2).doubleValue());
		
		assertEquals(65.15,Billing.calculateTotalCost(sb));
		assertEquals(7.65,Billing.calculateTotalTax(sb));
		
		
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
		
		Item item1 =   new Item("perfume",BigDecimal.valueOf(27.99),ItemCategory.OTHER,true);
		Item item2 =  new Item("perfume",BigDecimal.valueOf(18.99),ItemCategory.OTHER,false);
		Item item3 =  new Item("headeach pills",BigDecimal.valueOf(9.75),ItemCategory.MEDICAL_PRODUCT,false);
		Item item4 =  new Item("chocklates",BigDecimal.valueOf(11.25),ItemCategory.FOOD,true);
		ShoppingBaskets sb =  new ShoppingBaskets();
		sb.addItem(item1);
		sb.addItem(item2);
		sb.addItem(item3);
		sb.addItem(item4);
		
		// testing
		assertTrue(sb.getItemList().size()==4);
		
		
		assertEquals(32.19,Billing.calculateCostforItem(item1).doubleValue());
		assertEquals(20.89,Billing.calculateCostforItem(item2).doubleValue());
		assertEquals(9.75,Billing.calculateCostforItem(item3).doubleValue());
		assertEquals(11.85,Billing.calculateCostforItem(item4).doubleValue());
		
		assertEquals(6.70,Billing.calculateTotalTax(sb));
		assertEquals(74.68,Billing.calculateTotalCost(sb));
	}
	
}
