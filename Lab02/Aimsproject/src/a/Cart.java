package a;

public class Cart {
	public static final int MAX_NUMBERS_ORDERED=20;
	private DigitalVIdeoDisc[] itemsOrdered=new DigitalVIdeoDisc[MAX_NUMBERS_ORDERED];	
	private int qtyOrdered=0;
		///them dvd
	public void addDigitalVideoDisc(DigitalVIdeoDisc disc)
	{
		if(qtyOrdered<MAX_NUMBERS_ORDERED)
		{
			itemsOrdered[qtyOrdered]=disc;
			qtyOrdered++;
			System.out.println("The disc has been added.");
		}
		else
		{
			System.out.println("The cart is almost full.");
		}
	}
	    ///xoa dvd
	public void removeDigitalVIdeoDisc(DigitalVIdeoDisc disc)
	{
		boolean found=false;
		for(int i=0;i<qtyOrdered;i++)
		{
			if(itemsOrdered[i]==disc)
			{
				found=true;
				for(int j=i;j<qtyOrdered-1;j++)
				itemsOrdered[j]=itemsOrdered[j+1];
				itemsOrdered[qtyOrdered-1]=null;
				qtyOrdered--;
				System.out.println("The disc has been removed.");
				break;
			}
		}
		if(found==false)
			System.out.println("The disc was not found in the cart.");
	}
		///tinh tong chi phi
	public float totalCost()
	{
		float total=0.0f;
		for(int i=0;i<qtyOrdered;i++)
		total+=itemsOrdered[i].getCost();
		return total;
	}
		///hien thi gio hang
		public void displayCart()
		{
			 System.out.println("\n----- Cart Details -----");
			 for(int i=0;i<qtyOrdered;i++)
			 {
				 System.out.println((i+1) + ". Title: " + itemsOrdered[i].getTitle() + ", Cost: " + itemsOrdered[i].getCost());
			 }
			 System.out.println("Total cost: " + totalCost() + "$\n");
		}
		
}


