import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Dsatur {
	
	public ArrayList<Objet> U;
	public ArrayList<Objet> C;
	public ArrayList<Objet> V;
	public int nbSommet;
	public Dsatur( ArrayList<Objet> listSommet) {
		this.nbSommet = listSommet.size();
		this.V = new ArrayList<Objet>();
		for(Objet objet: listSommet) {
			objet.setEstPlace(false);
			this.V.add(objet);
		}

		this.C = new ArrayList<Objet>();
		this.U = new ArrayList<Objet>();
	}

	
	public void Initilisation() {
		//ordonner les sommets par ordre decroissantes de degres.
		int d = 0;
		int indice_d_max = 0;
		while (this.V.size() != 0) {
			for(int j = 0 ; j  < this.V.size(); j++ ) {
				if(d < this.V.get(j).getDegreSommet()) {
					d = this.V.get(j).getDegreSommet();
					indice_d_max = j;
				}
			}
			this.U.add(this.V.get(indice_d_max));
			this.V.remove(indice_d_max);
			d = 0;
		}
		
		//identifier le sommet de degre maximal de U et affecter la couleur 1 
		this.U.get(0).setCouleur(1);
		this.U.get(0).setColored(true);
		this.C.add(this.U.get(0));
		//this.U.remove(0);
	}
	
	public int DSAT(int i) {
		Objet o = this.U.get(i);
		boolean voisinColored = false;

		HashSet<Integer> a = new HashSet<Integer>();
		for(Objet ob : o.getSommetRelie()) {
			if(ob.isColored) {
				voisinColored = true;
				a.add(ob.getCouleur());
			}
		}
		if(voisinColored) {
			return a.size();
		}
		else {
			return o.getDegreSommet();
		}
	}
	
	
	public int ColoredSommetDegreMax(int i ) {
		int DSATmax = DSAT(i);
		for(int j = 0 ; j < this.C.size(); j++) {
			if(!this.C.get(j).isColored) {
				if(DSATmax <= DSAT(j)) {
					i = j;
					DSATmax = DSAT(j);
				}
			}
		}
		int dgMax = this.U.get(i).getDegreSommet();
		for(int j = 0 ; j < this.C.size(); j++ ) {
			if(!this.C.get(j).isColored) {
				if(DSATmax == DSAT(j) && dgMax < this.U.get(j).getDegreSommet()) {
					i = j;
					dgMax = this.U.get(j).getDegreSommet();
				}
			}
		}
		return i;
	}

	public void ColorationSommetAvecPlusPetitColor(int i) {
		ArrayList<Integer> tabColor = new ArrayList<Integer>();
		for(Objet o : this.U.get(i).getSommetRelie()) {
			tabColor.add(o.getCouleur());
		}
		int k = 1;
		while(tabColor.contains(k)) {
			k++;
		}
		this.U.get(i).setCouleur(k);
		this.U.get(i).setColored(true);
		this.C.add(this.U.get(i));
		
		//this.U.remove(i);
	}

	public void AlgoDSatur() {
		Initilisation();
		for(int i = 0 ; i < this.U.size(); i ++) {
			int s  = ColoredSommetDegreMax(i);
			ColorationSommetAvecPlusPetitColor(s);
		}
	}
	
	public ArrayList<Objet> getV() {
		
		return V;
	}


	public void setV(ArrayList<Objet> v) {
		V = v;
	}




	public ArrayList<Objet> getU() {
		return U;
	}


	public void setU(ArrayList<Objet> u) {
		U = u;
	}


	public ArrayList<Objet> getC() {
		return C;
	}


	public void setC(ArrayList<Objet> c) {
		C = c;
	}
	
	
	
}
