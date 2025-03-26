package a;

public class Aims {

	public static void main(String[] args) {
		Cart anOrder = new Cart();
		DigitalVIdeoDisc dvd1=new DigitalVIdeoDisc("The Lion King ","Animation","Roger Allers",87, 19.95f);
		anOrder.addDigitalVideoDisc(dvd1);
		DigitalVIdeoDisc dvd2=new DigitalVIdeoDisc("Star wars","Science Fiction","George Lucas",87, 24.95f);
		anOrder.addDigitalVideoDisc(dvd2);
		DigitalVIdeoDisc dvd3=new DigitalVIdeoDisc("Aladin","Animation", 18.99f);
		anOrder.addDigitalVideoDisc(dvd3);
		System.out.println("Total cost is: ");
		System.out.println(anOrder.totalCost());
		anOrder.removeDigitalVIdeoDisc(dvd2);
		anOrder.displayCart();
	}

}
