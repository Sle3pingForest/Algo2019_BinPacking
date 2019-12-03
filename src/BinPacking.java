import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class BinPacking {
	
	public ArrayList<Boite> listeBoites;
	
	public BinPacking() {
		this.listeBoites = new ArrayList<Boite>();
	}
	
	
	public int FractionalPacking(ArrayList<Objet> listobjets , int tailleBoite) {
		int tailleTotal = 0;
		for(Objet objet:listobjets) {
			tailleTotal += objet.getHight();
		}
		return (tailleTotal % tailleBoite > 0) ? (tailleTotal/ tailleBoite) + 1 : tailleTotal / tailleBoite;
	
	}
	
	
	/**
	 * DANS CET ALGO, ON CONSIDERE QU'ON PEUT TOUJOUR PLACE UN OBJET DANS UNE DES BOITE. IL N'Y A PAS OBJET FLOTTANT. 
	 */
	public String FirstFitDecreasingPacking(ArrayList<Objet> listObjets , int tailleBoite){
		if(listeBoites.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoites.add(b);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoites) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		for(int indiceObjet = 0 ; indiceObjet< listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoites) {//on prend une boite 
				if(!listObjets.get(indiceObjet).estPlace && listObjets.get(indiceObjet).getHight() <= boite.getHAUTEUR_BOITE()) {//on check s'il reste de la place dans la boite
					if(!boite.checkConflitInputObjet(listObjets.get(indiceObjet))) {// puis on verifie si l'objet LAMDA est en conflit avec les objet dans la boite
						boite.putObjetToBox(indiceObjet,listObjets.get(indiceObjet));
						listObjets.get(indiceObjet).setEstPlace(true);
						
					}
					else {
						System.out.println("Les objet sont en conflit");
					}
				}
				else {
					System.out.println("PAS ASSEZ DE PLACE , on regarde le prochaine boite");
				}

			}
			if(!listObjets.get(indiceObjet).estPlace) {// si il n'y a plus de boite dispo, on creat une nouvelle boite
				Boite b = new Boite(tailleBoite);
				b.putObjetToBox(indiceObjet, listObjets.get(indiceObjet));
				listObjets.get(indiceObjet).setEstPlace(true);
				listeBoites.add(b);
			}
			
			
		}

		System.out.println(listeBoites.size());
		return afficheLesboite(listeBoites, tailleBoite );
	}
	
	
	public String BestFitDecreasingPacking(ArrayList<Objet> listObjets, int tailleBoite) {
		if(listeBoites.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoites.add(b);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoites) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		
		for(int indiceObjet = 0 ; indiceObjet< listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoites) {//on prend une boite 
				if(!listObjets.get(indiceObjet).estPlace && listObjets.get(indiceObjet).getHight() <= boite.getHAUTEUR_BOITE()) {//on check s'il reste de la place dans la boite
					if(!boite.checkConflitInputObjet(listObjets.get(indiceObjet))) {// puis on verifie si l'objet LAMDA est en conflit avec les objet dans la boite
						boite.putObjetToBox(indiceObjet,listObjets.get(indiceObjet));
						listObjets.get(indiceObjet).setEstPlace(true);
						
					}
					else {
						System.out.println("Les objet sont en conflit");
					}
				}
				else {
					System.out.println("PAS ASSEZ DE PLACE , on regarde le prochaine boite");
				}

			}
			if(!listObjets.get(indiceObjet).estPlace) {// si il n'y a plus de boite dispo, on creat une nouvelle boite
				Boite b = new Boite(tailleBoite);
				b.putObjetToBox(indiceObjet, listObjets.get(indiceObjet));
				listObjets.get(indiceObjet).setEstPlace(true);
				listeBoites.add(b);
			}
			
			
		}

		System.out.println(listeBoites.size());
		
		
		
		return"";
	}
	
	
	

	
	public  String afficheLesboite(ArrayList<Boite> listBoites, int tailleInit){
		String s = "";
		int nb = 1;
		for(Boite boite : listBoites) {
			s += "Boite num "+ nb + " (Taille occupé: "+  (tailleInit - boite.getHAUTEUR_BOITE())  + "): " + boite.affichedetailleContenu() + "\n";
			nb++;
		}
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Objet o1 = new Objet(8);
		Objet o2 = new Objet(7);
		Objet o3 = new Objet(5);
		Objet o4 = new Objet(4);
		Objet o5 = new Objet(3);
		Objet o6 = new Objet(2);
		Objet o7 = new Objet(1);
		Objet o8 = new Objet(2);
		Objet o9 = new Objet(1);
		
		ArrayList<Objet> lo = new ArrayList<Objet>();
		lo.add(o1);
		lo.add(o2);
		lo.add(o3);
		lo.add(o4);
		lo.add(o5);
		lo.add(o6);
		lo.add(o7);
		//lo.add(o8);
		//lo.add(o9);
		
		BinPacking bp = new BinPacking();
		
		//System.out.println(bp.FractionalPacking(lo, 20));
		System.out.println(bp.FirstFitDecreasingPacking(lo, 10));

	}

}
