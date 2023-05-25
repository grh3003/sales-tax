import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

class ItemLabelProvider extends LabelProvider implements ITableLabelProvider {

	    @Override
	    public String getColumnText(Object element, int columnIndex) {
	        Item item = (Item) element;
	        switch (columnIndex) {
	            case 0:
	                return item.getName();
	            case 1:
	                return String.valueOf(item.getPrice());
	            case 2:
	                return item.getCategory().name();
	            case 3: 
	            	return item.isImported()?"True":"False";
	            default:
	                return "";
	        }
	    }

	    @Override
	    public Image getColumnImage(Object element, int columnIndex) {
	        return null;
	    }
	}
