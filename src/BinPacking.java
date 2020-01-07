
import java.util.ArrayList;


public class BinPacking {
	
	public ArrayList<Boite> listeBoitesFFD;
	public ArrayList<Boite> listeBoitesBFD;
	public LoadGraph loadgrah;
	
	public BinPacking() {
		this.listeBoitesFFD = new ArrayList<Boite>();
		this.listeBoitesBFD = new ArrayList<Boite>();
		
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
	public String FirstFitDecreasingPacking(ArrayList<Objet> listObjets , int tailleBoite){

		if(listeBoitesFFD.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoitesFFD.add(b);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoitesFFD) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		for(int indiceObjet = 0 ; indiceObjet < listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoitesFFD) {//on prend une boite 
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
				listeBoitesFFD.add(b);
			}
		}

		return afficheLesboite(listeBoitesFFD, tailleBoite );
	}
	
	
	
	/**cette algo : on remplie dans dans la boite, apres le remplissage, l'hauteur de la boite est le PLUS grand parrapport a les autre boites
	 * 
	 */
	public String BestFitDecreasingPacking(ArrayList<Objet> listObjets, int tailleBoite) {
		if(listeBoitesBFD.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoitesBFD.add(b);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoitesBFD) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		int topHight = 0;
		for(int indiceObjet = 0 ; indiceObjet< listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoitesBFD) {//on prend une boite 
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
				listeBoitesBFD.add(b);
				topHight = b.tailleOccupé;
			}
		}
		
		return afficheLesboite(listeBoitesBFD, tailleBoite );
	}
	
	
	
	/**
	 * 
	 * @param listBoites
	 * @param tailleInit
	 * @return
	 */
	
	public String DsaturWithFFDpacking(ArrayList<Objet> listObjets, int tailleBoite) {
		listeBoitesFFD = new ArrayList<Boite>();
		if(listeBoitesFFD.size() == 0) {// vefirie si on a des boite dispo 
			Boite b = new Boite(tailleBoite); // creer une nouvelle boite si on a pas et on ajoute dans la liste des boite
			listeBoitesFFD.add(b);
			b.getListcolor().add(1);
		}
		else {//si non on modifie la taille des boites 
			for(Boite boite : listeBoitesFFD) { 
				boite.setHAUTEUR_BOITE(tailleBoite);
			}
		}
		for(int indiceObjet = 0 ; indiceObjet < listObjets.size(); indiceObjet++) {// on prend un objet LAMDA dans la list
			for(Boite boite : listeBoitesFFD) {//on prend une boite 
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
				listeBoitesFFD.add(b);
			}
		}
		return afficheLesboite(listeBoitesFFD,tailleBoite);
	}
	
	public  String afficheLesboite(ArrayList<Boite> listBoites, int tailleInit){
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
		
	
		System.out.println("######################");
		Dsatur ds = new Dsatur(125, "./graphes-conflits/125.txt");
		ds.AlgoDSatur();
		
		for(Objet o : ds.getU()) {
			//System.out.println("sommet num: " + o.getNumSommet() + ", degre: " +  o.getDegreSommet() + " couleur: " +  o.getCouleur() );
		}
		
		System.out.println("######################");
		System.out.println(bp.FirstFitDecreasingPacking(load.getListSommet(), 150));
		//System.out.println(bp.BestFitDecreasingPacking(load.getListSommet(), 150));
		System.out.println(bp.DsaturWithFFDpacking(ds.getU(), 150));
		
	}

}
