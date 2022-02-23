/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 16:30:59 CET 2022
*
*/
public class Test {
	public static void main(String[] args){
		
		Ecran e = new Ecran();
		UniteCentrale uc = new UniteCentrale(e);
		e.setUniteCentrale(uc);

		Touche t5 = new ToucheNumerique("5",e);
		Touche t3 = new ToucheNumerique("3",e);
		Touche t0 = new ToucheNumerique("0",e);
		Touche clr = new ToucheSpeciale("clr", uc, "clear");
		Touche auCarre = new ToucheOpUnaire("^2", uc, "auCarre");
		Touche add = new ToucheOpBinaire("+", uc, "add");
		Touche sub = new ToucheOpBinaire("-", uc, "sub");
		Touche valider = new ToucheSpeciale("Enter", uc, "valider");

		System.out.println(e);
		t3.appuyer();
		System.out.println(e);
		t0.appuyer();
		System.out.println(e);
		
		valider.appuyer();
		System.out.println(e);
		
		t5.appuyer();
		System.out.println(e);
		
		auCarre.appuyer();
		System.out.println(e);

		sub.appuyer();
		System.out.println(e);
		

	}
}