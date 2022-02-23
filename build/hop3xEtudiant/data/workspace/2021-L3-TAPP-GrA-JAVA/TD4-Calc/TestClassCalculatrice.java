/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Wed Jan 26 17:50:36 CET 2022
*
*/

import java.util.Scanner;
public class TestClassCalculatrice {
	public static void main(String[] args){
		Calculatrice calcu = new Calculatrice();

		
		Scanner sc = new Scanner(System.in);
		String input;
		
		System.out.print("\n".repeat(100));

		do{
			calcu.afficher();
			System.out.print("Touche (\"help\" en cas de besoin): ");
			input = sc.nextLine();
			System.out.print("\n".repeat(100));
			calcu.appuyerTouche(input);
			
		}while(!input.equals("quit"));
		
		
	}
}