/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Jan 17 14:27:58 CET 2022
*
*/
public class Bus extends Vehicule{
	public Bus(){
		nbRoues = 6;
	}
	
	public String categorie(){
		return "je suis un bus";
	}
	
	public void klaxoner(){
		System.out.println("Tut Tut");
	}
}