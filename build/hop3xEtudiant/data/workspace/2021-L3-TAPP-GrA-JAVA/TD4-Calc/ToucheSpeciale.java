/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 16:55:04 CET 2022
*
*/
import java.lang.reflect.Method;
public class ToucheSpeciale extends ToucheFonction{

	public ToucheSpeciale(String label, UniteCentrale uc, String fonctionNom){
		super(label, uc, fonctionNom);
	}

	@Override
	public void appuyer(){
		Method fonc;
		try{
			fonc = uc.getClass().getMethod(fonctionNom, null);
		}catch(NoSuchMethodException e){
			System.out.println("Le bouton n'a pas de fonction correct !");
			return;
		}

		
		uc.traiterSpecial(fonc);
	}
}