/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 16:35:58 CET 2022
*
*/
import java.util.Stack;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class UniteCentrale {
	private Ecran ecran;
	private Stack<Integer> memoire;

	public UniteCentrale(Ecran e){
		ecran = e;
		memoire = new Stack<Integer>();
	}

	/**
	 * Empile la valeur passé en parametre dans la memoire de l'unite centrale
	 * 
	 * @param La valeur à empiler
	 */
	public void sauvegarde(String s){
		memoire.add(Integer.parseInt(s));
	}

	/**Affiche la pile mémoire
	 * 
	 */
	public void afficherMemoire(){
		System.out.println("Memoire de l'uc");
		for (Integer i : memoire){
			System.out.println(i);
		}
	}


	//Fonctions de traitement
	/**Traite une fonction recu par une touche spéciale
	 * @param La fonction à executer
	 * 
	 */
	public void traiterSpecial(Method fonc){
		try{
			fonc.invoke(this, null);
		}catch(IllegalAccessException e){
			System.out.println("Fatal error : IllegalAccessException from traiterSpecial call");
		}catch(InvocationTargetException e){
			System.out.println("Fatal error : IllegalAccessException from InvocationTargetException call");
		}
	}
	
	/**Traite une fonction recu par une touche unaire
	 * @param La fonction à executer
	 * 
	 */
	public void traiterOpUnaire(Method fonc){
		try{
			int res = (Integer)fonc.invoke(this, Integer.parseInt(ecran.getBuffer()));
			ecran.setBuffer(Integer.toString(res));
			ecran.setRecouvrement(true);
			
		}catch(IllegalAccessException e){
			System.out.println("Fatal error : IllegalAccessException from traiterSpecial call");
		}catch(InvocationTargetException e){
			System.out.println("Fatal error : IllegalAccessException from InvocationTargetException call");
		}
	}

	/**Traite une fonction recu par une touche binaire
	 * @param La fonction à executer
	 * 
	 */
	public void traiterOpBinaire(Method fonc){
		try{
			int res = (Integer)fonc.invoke(this, memoire.pop(), Integer.parseInt(ecran.getBuffer()));
			ecran.setBuffer(Integer.toString(res));
			ecran.setRecouvrement(true);
			
		}catch(IllegalAccessException e){
			System.out.println("Fatal error : IllegalAccessException from traiterSpecial call");
		}catch(InvocationTargetException e){
			System.out.println("Fatal error : IllegalAccessException from InvocationTargetException call");
		}
	}


	//Fonctions de touches
	//Touches Speciales
	/**Raz de la calculatrice
	 * 
	 * 
	 */
	public void clear(){
		ecran.setBuffer("0");
		ecran.setRecouvrement(true);
		memoire = new Stack<Integer>();
	}

	/**Valide le nombre actuel
	 * 
	 * 
	 */
	public void valider(){
		ecran.setRecouvrement(true);
	}

	//Touches unaires
	public int auCarre(int x){
		return x*x;
	}

	//Touches binaires
	public int add(int a, int b){
		return a+b;
	}

	public int sub(int a, int b){
		return a-b;
	}

	public int mul(int a, int b){
		return a*b;
	}

	public int div(int a, int b){
		return a/b;
	}
	
}