/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 17:12:20 CET 2022
*
*/
import java.lang.reflect.Method;
public class ToucheOpUnaire extends ToucheFonction{
	
	public ToucheOpUnaire(String label, UniteCentrale uc, String fonctionNom){
		super(label, uc, fonctionNom);
	}

	@Override
	public void appuyer(){
		Method fonc;
		try{
			fonc = uc.getClass().getMethod(fonctionNom, int.class);
		}catch(NoSuchMethodException e){
			System.out.println("Le bouton n'a pas de fonction correct !");
			return;
		}

		
		uc.traiterOpUnaire(fonc);
	}
}