/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Jan 17 14:25:49 CET 2022
*
*/
public class Voiture extends Vehicule{
	public Voiture(){
		nbRoues = 4;
	}

	public String categorie(){
		return "je suis une voiture";
	}

	public void klaxoner(){
		System.out.println("Pouet Pouet");
	}
}