import java.math.BigDecimal;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class SalesTaxCalculator {

	private  Text receiptText;
	private  Shell shell;
	private  ShoppingBaskets sb;
	private  TableViewer tableViewer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SalesTaxCalculator calc =  new SalesTaxCalculator();
		calc.open();
		
	}

	private  void open() {
		sb = new ShoppingBaskets();
		Display display = new Display();
		shell = new Shell(display);
		shell.setText("Billing App");
		shell.setLayout(new GridLayout());
		shell.setLayoutData(GridDataFactory.fillDefaults());

		Composite cmp = new Composite(shell, SWT.BORDER);
		cmp.setLayout(new GridLayout());
		cmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createItemsTable(cmp);

		Composite buttonComposite = new Composite(shell, SWT.NONE);
		buttonComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button addItemButton = new Button(buttonComposite, SWT.PUSH);
		addItemButton.setText("Add Item");
		addItemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showAddItemDialog();
			}
		});

		Button generateReceiptButton = new Button(buttonComposite, SWT.PUSH);
		generateReceiptButton.setText("Generate Receipt");
		generateReceiptButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generateReceipt();
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				shell.getDisplay().sleep();
			}
		}
		if(!shell.isDisposed())
		shell.getDisplay().dispose();
	}

	/**
	 * @param cmp
	 */
	private void createItemsTable(Composite cmp) {
		tableViewer = new TableViewer(cmp, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLayout(new GridLayout());
		tableViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableViewer.getTable().setLinesVisible(true);
		// Define the columns
		String[] columnNames = { "Name", "Price", "category", "isImported" };
		int[] columnWidths = { 200, 100, 200, 100 };

		for (int i = 0; i < columnNames.length; i++) {
			TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
			column.getColumn().setText(columnNames[i]);
			column.getColumn().setWidth(columnWidths[i]);
			column.getColumn().setResizable(true);
		}

		// Set the content provider
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());

		Item[] items = {};
		tableViewer.setInput(items);

		tableViewer.setLabelProvider(new ItemLabelProvider());
	}

	private  void showAddItemDialog() {
		Shell dialogShell = new Shell(shell);
		dialogShell.setText("Add Item");
		dialogShell.setLayout(new GridLayout(2, false));

		Label nameLabel = new Label(dialogShell, SWT.NONE);
		nameLabel.setText("Item Name:");
		Text nameText = new Text(dialogShell, SWT.BORDER);

		Label priceLabel = new Label(dialogShell, SWT.NONE);
		priceLabel.setText("Price:");
		Text priceText = new Text(dialogShell, SWT.BORDER);

		Label categoryLabel = new Label(dialogShell, SWT.NONE);
		categoryLabel.setText("category:");
		
		Combo categoryCombo = new Combo(dialogShell, SWT.DROP_DOWN);
		categoryCombo.setData(ItemCategory.BOOK.name(), ItemCategory.BOOK);
		categoryCombo.setData(ItemCategory.FOOD.name(), ItemCategory.FOOD);
		categoryCombo.setData(ItemCategory.MEDICAL_PRODUCT.name(), ItemCategory.MEDICAL_PRODUCT);
		categoryCombo.setData(ItemCategory.OTHER.name(), ItemCategory.OTHER);

		categoryCombo.add(ItemCategory.BOOK.name());
		categoryCombo.add(ItemCategory.FOOD.name());
		categoryCombo.add(ItemCategory.MEDICAL_PRODUCT.name());
		categoryCombo.add(ItemCategory.OTHER.name());
		//default OTHER category is selected
		categoryCombo.select(3);
		Label isImportedLabel = new Label(dialogShell, SWT.NONE);
		isImportedLabel.setText("isImported");
		Button isImportedBtn = new Button(dialogShell, SWT.CHECK);

		Button addButton = new Button(dialogShell, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = nameText.getText();
				BigDecimal price = new BigDecimal(priceText.getText());
				String catagoty = categoryCombo.getItem(categoryCombo.getSelectionIndex());

				Item item = new Item(name, price, (ItemCategory) categoryCombo.getData(catagoty),
						isImportedBtn.getSelection());
				sb.addItem(item);
				TableItem tblItem = new TableItem(tableViewer.getTable(), SWT.NONE);

				tblItem.setText(new String[] { item.getName(), String.valueOf(item.getPrice()),
						item.getCategory().name(), item.isImported() ? "True" : "False" });
				dialogShell.close();
			}
		});

		dialogShell.pack();
		dialogShell.open();

	}

	private  void generateReceipt() {
		Shell dialogShell = new Shell(shell);
		dialogShell.setText("Receipt");
		dialogShell.setLayout(new GridLayout());
		dialogShell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		receiptText = new Text(dialogShell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.READ_ONLY);
		receiptText.setText("");
		receiptText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		StringBuilder receiptBuilder = new StringBuilder();

		for (Item item : sb.getItemList()) {
			BigDecimal costforItem = Billing.calculateCostforItem(item);
			receiptBuilder.append(item.getName() + " : " + costforItem.doubleValue());
			receiptBuilder.append("\n-------------------------------------\n");

		}
		receiptBuilder.append("\n----------------------------------------------------\n");
		receiptBuilder.append("Sales Taxes: ").append(Billing.calculateTotalTax(sb)).append("\n");
		receiptBuilder.append("\n----------------------------------------------------\n");
		receiptBuilder.append("Total: ").append(Billing.calculateTotalCost(sb));

		receiptText.setText(receiptBuilder.toString());
		dialogShell.pack();
		dialogShell.open();
	}
	
	

}