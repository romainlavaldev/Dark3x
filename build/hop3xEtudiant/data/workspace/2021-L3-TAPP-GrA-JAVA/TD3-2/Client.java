/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Jan 17 14:28:42 CET 2022
*
*/
public class Client {
	public static void main(String args[]){
	Vehicule v;
	v = new Vehicule();
	v.description();
	v = new Voiture();
	v.description();
	v.klaxoner();
	v = new Bus();
	v.description();
	v.klaxoner();
	v = new Fiat();
	v.description();
	v.klaxoner();
	}
}