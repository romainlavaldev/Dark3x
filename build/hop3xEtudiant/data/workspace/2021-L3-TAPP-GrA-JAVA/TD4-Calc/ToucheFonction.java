/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 16:53:21 CET 2022
*
*/
public abstract class ToucheFonction extends Touche{
	protected UniteCentrale uc;
	protected String fonctionNom;

	public ToucheFonction(String label, UniteCentrale uc, String fonctionNom){
		super(label);
		this.uc = uc;
		this.fonctionNom = fonctionNom;
	}	
}