/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 16:43:24 CET 2022
*
*/
public abstract class Touche {
	protected String label;

	public Touche(String label){
		this.label = label;
	}

	/**Comportement d'appui sur une touche
	 * 
	 */
	public abstract void appuyer();
}