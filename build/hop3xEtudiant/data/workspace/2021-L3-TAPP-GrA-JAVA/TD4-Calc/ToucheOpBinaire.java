/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 17:22:50 CET 2022
*
*/
import java.lang.reflect.Method;
public class ToucheOpBinaire extends ToucheFonction{

	public ToucheOpBinaire(String label, UniteCentrale uc, String fonctionNom){
		super(label, uc, fonctionNom);
	}

	@Override
	public void appuyer(){
		Method fonc;
		try{
			fonc = uc.getClass().getMethod(fonctionNom, int.class, int.class);
		}catch(NoSuchMethodException e){
			System.out.println("Le bouton n'a pas de fonction correct !");
			return;
		}

		
		uc.traiterOpBinaire(fonc);
	}
	
}