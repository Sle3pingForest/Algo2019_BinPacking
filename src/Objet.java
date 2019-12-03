import java.util.ArrayList;

public class Objet {
	
	protected int hight;
	protected int tailleRestant;
	protected String arrete;
	protected ArrayList<Objet> sommetRelie;
	protected boolean estPlace;
	
	public Objet(int h) {
		hight = h;
		this.estPlace = false;
		this.sommetRelie = new ArrayList<Objet>();
	}
	
	public Objet(int h, ArrayList<Objet> sommetRelie) {
		hight = h;
		this.estPlace = false;
		this.sommetRelie = sommetRelie;
	}


	public boolean isEstPlace() {
		return estPlace;
	}

	public void setEstPlace(boolean estPlace) {
		this.estPlace = estPlace;
	}

	public void settingSommetRelie(Objet o) {
		this.sommetRelie.add(o);
		o.getSommetRelie().add(this);
	}
	
	public boolean checkConflit(Objet o) {
		return this.sommetRelie.contains(o);
	}

	public ArrayList<Objet> getSommetRelie() {
		return sommetRelie;
	}

	public void setSommetRelie(ArrayList<Objet> sommetRelie) {
		this.sommetRelie = sommetRelie;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public int getTailleRestant() {
		return tailleRestant;
	}

	public void setTailleRestant(int tailleRestant) {
		this.tailleRestant = tailleRestant;
	}

	public String getArrete() {
		return arrete;
	}

	public void setArrete(String arrete) {
		this.arrete = arrete;
	}
	
	
	
}
