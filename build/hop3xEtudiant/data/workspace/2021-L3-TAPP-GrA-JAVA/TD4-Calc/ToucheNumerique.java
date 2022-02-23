/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 16:45:48 CET 2022
*
*/
public class ToucheNumerique extends Touche{
	private Ecran ecran;

	public ToucheNumerique(String label, Ecran e){
		super(label);
		ecran = e;
	}

	public void appuyer(){
		ecran.traiter(label);
	}

}