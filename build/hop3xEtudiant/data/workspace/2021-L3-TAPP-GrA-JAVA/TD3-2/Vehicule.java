/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Jan 17 14:23:43 CET 2022
*
*/
public class Vehicule implements Avertir{
	protected int nbRoues;

	public void demarrer(){
		
	}

	public void rouler(){
		
	}

	public void arreter(){
		
	}

	public String categorie(){
		return "Je suis un vehicule";
	}

	public void description(){
		System.out.println(categorie() + " a " + nbRoues + " roues");
	}

	public void klaxoner(){
		
	}
}