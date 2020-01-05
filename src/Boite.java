
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Boite {
	protected int HAUTEUR_BOITE ;
	protected int tailleOccupé;
	protected ArrayList<Objet> listObjetDansLaboite;
	protected Map<String,Integer> listeDescouple;
	
	
	public Boite(int h) {
		this.HAUTEUR_BOITE = h;
		this.listeDescouple = new HashMap<String, Integer>();
		listObjetDansLaboite = new ArrayList<Objet>();
		
	}

	
	
	public boolean checkConflitInputObjet(Objet objet) {
		for( Objet o: listObjetDansLaboite) {
			if(o.checkConflit(objet)) { // on verifie pour chaque objet dans la boite si il est en conflit avec l'objet entrant
				System.out.println("L'objet: "+ objet.getNumSommet() + " est en conflit avec:  " + o.getNumSommet());
				return true; // vrai si ya un conflit avec l'objet entrant
			}
		}
		return false;// faux si il y a pas de conflit
	}
	
	public void putObjetToBox(String i , Objet objet) {
		this.listeDescouple.put(i, objet.getHight()); // [indice - taile de la boite]
		this.listObjetDansLaboite.add(objet);
		this.setTailleOccupé(this.getTailleOccupé() + objet.getHight());
	}
	
	public String affichedetailleContenu() {
		String s = "[";
		for (Map.Entry<String,Integer> e : listeDescouple.entrySet()){
		    s += e.getKey()+ "-" + e.getValue()+  "," ;
		}
		s+="]";
		return s;
	}
	
	public int getHAUTEUR_BOITE() {
		return HAUTEUR_BOITE;
	}

	public void setHAUTEUR_BOITE(int hAUTEUR_BOITE) {
		HAUTEUR_BOITE = hAUTEUR_BOITE;
	}

	public int getTailleOccupé() {
		return tailleOccupé;
	}

	public void setTailleOccupé(int tailleOccupé) {
		this.tailleOccupé = tailleOccupé;
	}


	
	
}
