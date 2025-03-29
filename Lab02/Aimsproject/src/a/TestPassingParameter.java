package a;

public class TestPassingParameter {

	public static void main(String[] args) {
		DigitalVIdeoDisc jungleDVD=new DigitalVIdeoDisc("Jungle");
		DigitalVIdeoDisc cinderellaDVD=new DigitalVIdeoDisc("Cinderella");
		swap(jungleDVD, cinderellaDVD);
		System.out.println("jungle dvd title: " + jungleDVD.getTitle());
		System.out.println("cinderella dvd tite: "+ cinderellaDVD.getTitle());
		changeTitle(jungleDVD,cinderellaDVD.getTitle());
		System.out.println("jungle dvd title: " + jungleDVD.getTitle());
		}
	public static void swap(Object o1,Object o2)
	{
		Object tmp=o1;
		o1=o2;
		o2=tmp;
	}
	public static void changeTitle(DigitalVIdeoDisc dvd, String title)
	{
		String oldTitle=dvd.getTitle();
		dvd.setTitle(title);
		dvd = new DigitalVIdeoDisc(oldTitle);
	}

}
