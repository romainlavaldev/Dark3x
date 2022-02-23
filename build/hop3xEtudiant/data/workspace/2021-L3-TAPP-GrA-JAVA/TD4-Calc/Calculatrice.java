/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 17:34:01 CET 2022
*
*/

import java.util.HashMap;
import java.util.Map;

public class Calculatrice {
	private Ecran e;
	private UniteCentrale uc;
	private Map<String, Touche> catalogueTouche;


	public Calculatrice(){
		e = new Ecran();
		uc = new UniteCentrale(e);
		e.setUniteCentrale(uc);
		
		populateCatalogue();
	}

	/**Rempli la Map des touches disponnibles sur la calculatrice
	 * 
	 */
	private void populateCatalogue(){
		
		catalogueTouche = new HashMap<>();
		
		//Ajout des touches numériques
		for(int i = 0; i < 10; i++){
			catalogueTouche.put(Integer.toString(i), new ToucheNumerique(Integer.toString(i), e));
		}

		catalogueTouche.put("+", new ToucheOpBinaire("+", uc, "add"));
		catalogueTouche.put("-", new ToucheOpBinaire("-", uc, "sub"));
		catalogueTouche.put("*", new ToucheOpBinaire("*", uc, "mul"));
		catalogueTouche.put("/", new ToucheOpBinaire("/", uc, "div"));

		
		catalogueTouche.put("^2", new ToucheOpUnaire("^2", uc, "auCarre"));

		
		catalogueTouche.put("C", new ToucheSpeciale("C", uc, "clear"));
		catalogueTouche.put("Enter", new ToucheSpeciale("Enter", uc, "valider"));
		
	}

	/**Affichage des clés de la Map contenants toutes les touches
	 * 
	 */
	
	public void afficherCatalogue(){
		System.out.println("\nEnsemble des touches disponnibles :");
		for (String k : catalogueTouche.keySet()){
			System.out.println(k);
		}
	}

	/**Permet de simuler l'appui sur une touche à partir d'un code
	 * 
	 * @param Un code correspondant à une touche
	 */
	public void appuyerTouche(String codeTouche){
		if (!codeTouche.equals("help")){
			Touche t = catalogueTouche.get(codeTouche);

			if(t != null){
				t.appuyer();
			}else{
				System.out.println("Touche incorrect. entrez \"help\" pour la liste des touches\n");
			}
		}else{
			afficherCatalogue();
		}
	}

	/**Affichage de la calculatrice dans la console
	 * 
	 */
	public void afficher(){
		System.out.println("*".repeat(17));
		System.out.println("* Casier TI 72+ *");
		//Ecran taille 15*5
		System.out.println("*"+"-".repeat(15)+"*");
		System.out.println("*"+"|" + " ".repeat(13) + "|"+"*");
		System.out.println("*"+"|" + " ".repeat(11-e.getBuffer().length()) + e.getBuffer() + "  " +"|"+"*");
		System.out.println("*"+"|" + " ".repeat(13) + "|"+"*");
		System.out.println("*"+"-".repeat(15)+"*");
		
		System.out.println("*" + " ".repeat(15) + "*");
		System.out.println("* C      ^2   / *");
		System.out.println("*" + " ".repeat(15) + "*");
		System.out.println("* 7   8   9   * *");
		System.out.println("*" + " ".repeat(15) + "*");
		System.out.println("* 4   5   6   - *");
		System.out.println("*" + " ".repeat(15) + "*");
		System.out.println("* 1   2   3   + *");
		System.out.println("*" + " ".repeat(15) + "*");
		System.out.println("*     0   Enter *");
		System.out.println("*" + " ".repeat(15) + "*");
		System.out.println("*".repeat(17));
	}
	
}