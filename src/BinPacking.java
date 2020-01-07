
import java.util.ArrayList;


public class BinPacking {
	
	
	public BinPacking() {
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
	 * on met l'objet LAMDA dans le premiere boite qui lui convient
	 */
	public ArrayList<Boite> FirstFitDecreasingPacking(ArrayList<Objet> listObjets , int tailleBoite){
		ArrayList<Boite> listeBoites = new ArrayList<Boite>();
		if(listeBoites.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoites.add(b);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoites) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		for(int indiceObjet = 0 ; indiceObjet < listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoites) {//on prend une boite 
				int tailleIntermediaire = boite.getTailleOccupé() + listObjets.get(indiceObjet).getHight();
				if(!listObjets.get(indiceObjet).estPlace && tailleIntermediaire <= boite.getHAUTEUR_BOITE()) {//on check s'il reste de la place dans la boite
					if(!boite.checkConflitInputObjet(listObjets.get(indiceObjet))) {// puis on verifie si l'objet LAMDA est en conflit avec les objet dans la boite
						boite.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(),listObjets.get(indiceObjet));
						listObjets.get(indiceObjet).setEstPlace(true);
						
					}
				}
			}
			if(!listObjets.get(indiceObjet).estPlace) {// si il n'y a plus de boite dispo, on creat une nouvelle boite
				Boite b = new Boite(tailleBoite);
				b.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(), listObjets.get(indiceObjet));
				listObjets.get(indiceObjet).setEstPlace(true);
				listeBoites.add(b);
			}
		}

		return listeBoites;
	}
	
	
	
	/**cette algo : on remplie dans dans la boite, apres le remplissage, l'hauteur de la boite est le PLUS grand parrapport a les autre boites
	 * 
	 */
	public ArrayList<Boite> BestFitDecreasingPacking(ArrayList<Objet> listObjets, int tailleBoite) {
		ArrayList<Boite> listeBoites = new ArrayList<Boite>();
		if(listeBoites.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoites.add(b);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoites) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		int topHight = 0;
		for(int indiceObjet = 0 ; indiceObjet< listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoites) {//on prend une boite 
				int tailleIntermediaire = boite.getTailleOccupé() + listObjets.get(indiceObjet).getHight();// on cre une taille intermediare avec la taille d'objet courrant et la taille de la boite courrant pour savoir si est plus haut apres ajout
				if(!listObjets.get(indiceObjet).estPlace && topHight < tailleIntermediaire && tailleIntermediaire <= boite.getHAUTEUR_BOITE()) {//on check s'il reste de la place dans la boite
					if(!boite.checkConflitInputObjet(listObjets.get(indiceObjet))) {// puis on verifie si l'objet LAMDA est en conflit avec les objet dans la boite
						boite.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(),listObjets.get(indiceObjet));
						listObjets.get(indiceObjet).setEstPlace(true);
						topHight = tailleIntermediaire;
					}
				}
			}
			if(!listObjets.get(indiceObjet).estPlace) {// si il n'y a plus de boite dispo, on creat une nouvelle boite
				Boite b = new Boite(tailleBoite);
				b.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(), listObjets.get(indiceObjet));
				listObjets.get(indiceObjet).setEstPlace(true);
				listeBoites.add(b);
				topHight = b.tailleOccupé;
			}
		}
		
		return listeBoites;
	}
	
	
	
	/**
	 * 
	 * @param listBoites
	 * @param tailleInit
	 * @return
	 */
	
	public ArrayList<Boite> DsaturWithFFDpacking(ArrayList<Objet> listObjets, int tailleBoite) {
		ArrayList<Boite> listeBoiteDs = new ArrayList<Boite>();
		if(listeBoiteDs.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoiteDs.add(b);
			b.getListcolor().add(1);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoiteDs) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		for(int indiceObjet = 0 ; indiceObjet < listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoiteDs) {//on prend une boite 
				int tailleIntermediaire = boite.getTailleOccupé() + listObjets.get(indiceObjet).getHight();
				if(!listObjets.get(indiceObjet).estPlace && tailleIntermediaire <= boite.getHAUTEUR_BOITE()) {//on check s'il reste de la place dans la boite
					if(boite.checkColor(listObjets.get(indiceObjet)) || !boite.checkConflitInputObjet(listObjets.get(indiceObjet))) {// puis on verifie si l'objet LAMDA est en conflit avec les objet dans la boite CAD si ya une objet meme couleur que lui dans la boite
						boite.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(),listObjets.get(indiceObjet));
						listObjets.get(indiceObjet).setEstPlace(true);
						
					}
				}
			}
			if(!listObjets.get(indiceObjet).estPlace) {// si il n'y a plus de boite dispo, on creat une nouvelle boite
				Boite b = new Boite(tailleBoite);
				b.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(), listObjets.get(indiceObjet));
				listObjets.get(indiceObjet).setEstPlace(true);
				listeBoiteDs.add(b);
				
			}
			else {
			}
		}
		return listeBoiteDs;
	}
	

	public ArrayList<Boite> DsaturWithBFDpacking(ArrayList<Objet> listObjets, int tailleBoite) {
		ArrayList<Boite> listeBoites = new ArrayList<Boite>();
		if(listeBoites.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoites.add(b);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoites) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		int topHight = 0;
		for(int indiceObjet = 0 ; indiceObjet< listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoites) {//on prend une boite 
				int tailleIntermediaire = boite.getTailleOccupé() + listObjets.get(indiceObjet).getHight();// on cre une taille intermediare avec la taille d'objet courrant et la taille de la boite courrant pour savoir si est plus haut apres ajout
				if(!listObjets.get(indiceObjet).estPlace && topHight < tailleIntermediaire && tailleIntermediaire <= boite.getHAUTEUR_BOITE()) {//on check s'il reste de la place dans la boite
					if(boite.checkColor(listObjets.get(indiceObjet)) || !boite.checkConflitInputObjet(listObjets.get(indiceObjet))) {// puis on verifie si l'objet LAMDA est en conflit avec les objet dans la boite
						boite.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(),listObjets.get(indiceObjet));
						listObjets.get(indiceObjet).setEstPlace(true);
						topHight = tailleIntermediaire;
					}
				}
			}
			if(!listObjets.get(indiceObjet).estPlace) {// si il n'y a plus de boite dispo, on creat une nouvelle boite
				Boite b = new Boite(tailleBoite);
				b.putObjetToBox(listObjets.get(indiceObjet).getNumSommet(), listObjets.get(indiceObjet));
				listObjets.get(indiceObjet).setEstPlace(true);
				listeBoites.add(b);
				topHight = b.tailleOccupé;
			}
		}
		
		return listeBoites;
	}


	public String afficheLesboite(ArrayList<Boite> listBoites){
		String s = "";
		int nb = 1;
		for(Boite boite : listBoites) {
			s += "Boite num "+ nb + " (Taille occupé: "+  boite.tailleOccupé  + "): " + boite.affichedetailleContenu() + "\n";
			nb++;
		}
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinPacking bp = new BinPacking();
		
		
		LoadGraph load = new LoadGraph(125);
		load.loadFile("./graphes-conflits/125.txt");
		

		ArrayList<Objet> listeFFD = new ArrayList<Objet>();
		listeFFD = load.getListSommet();
		
		ArrayList<Objet> listeBFD = new ArrayList<Objet>();
		listeBFD = load.getListSommet2();
	
		
		
		System.out.println("###################### FFDP");
		ArrayList<Boite> boitesFFDP = bp.FirstFitDecreasingPacking(listeFFD, 150);
		System.out.println(bp.afficheLesboite(boitesFFDP));
		

		System.out.println("###################### BFDP");
		ArrayList<Boite> boitesBFDP = bp.BestFitDecreasingPacking(listeBFD, 150);
		System.out.println(bp.afficheLesboite(boitesBFDP));
		
		System.out.println("###################### DSFFDP");
		Dsatur ds = new Dsatur(load.getListSommet());
		ds.AlgoDSatur();
		ArrayList<Boite> boitesDSFFDP =  bp.DsaturWithFFDpacking(ds.getU(), 150);
		System.out.println(bp.afficheLesboite(boitesDSFFDP));
		

		System.out.println("###################### DSFFDP");
		Dsatur ds2 = new Dsatur(load.getListSommet());
		ds2.AlgoDSatur();
		ArrayList<Boite> boitesDSBFDP =  bp.DsaturWithBFDpacking(ds2.getU(), 150);
		System.out.println(bp.afficheLesboite(boitesDSBFDP));
	}

}
