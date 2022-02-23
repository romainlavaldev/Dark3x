/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 16:27:54 CET 2022
*
*/
public class Ecran {
	private String buffer;
	private boolean recouvrement;
	private UniteCentrale uc;


	public Ecran(){
		buffer = "0";
		recouvrement = true;
	}

	public void setUniteCentrale(UniteCentrale uc){
		this.uc = uc;
	}

	public String getBuffer(){
		return buffer;
	}

	public void setBuffer(String s){
		buffer = s;
	}

	public void setRecouvrement(boolean b){
		recouvrement = b;
	}


	/**Recouvre où concatene une valeur reçus dans le buffer
	 * 
	 * @param La valeur recue
	 */
	public void traiter(String s){
		if (recouvrement){
			uc.sauvegarde(buffer);
			buffer = s;
			recouvrement = false;
		}else{
			buffer += s;
		}
	}

	@Override
	public String toString(){
		return buffer;
	}
}