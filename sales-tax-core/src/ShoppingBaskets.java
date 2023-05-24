import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author ganesh
 *
 */
public class ShoppingBaskets {
	
private List<Item>itemList;

public ShoppingBaskets() {
itemList =  new ArrayList<>();
}
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public List<Item> getItemList(){
		return itemList;
	}
}
