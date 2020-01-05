import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class LoadGraph {
	
	protected ArrayList<Objet> listSommet;
	
	public LoadGraph(int numSommet){
		this.listSommet = new ArrayList<Objet>();
		int x = 50;
		for(int i = 0; i < numSommet ; i++) {
			/*Random rnd = new Random();
			int x = 10 + rnd.nextInt(40);*/
			this.listSommet.add(new Objet(""+ (i + 1), x));// pour les hauteur d'objet , pour facilite le codage, je commence a cre une boite de taille 50 et tremine par taille 1 , comme ca les objet sont deja trie.
			x--; 

		}
	}

	public void loadFile(String path) {
		try{
			InputStream flux=new FileInputStream(path); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			int  i = 0 ;
			while ((ligne= buff.readLine())!=null){
				i++;
				if(i > 13) {// on commence a creer des sommet a la ligne 14 car le fichier nous donne les informations 
					String[] arrOfStr = ligne.split(" ");
					//System.out.println(arrOfStr[1] + "****" + arrOfStr[2] + "*****" + listSommet.get(Integer.parseInt(String.valueOf(arrOfStr[1]))-1).getNumSommet() + "****" + listSommet.get(Integer.parseInt(String.valueOf(arrOfStr[2]))-1).getNumSommet()) ;
					listSommet.get(Integer.parseInt(String.valueOf(arrOfStr[1]))-1).settingSommetRelie(listSommet.get(Integer.parseInt(String.valueOf(arrOfStr[2])) -1));
				
				}
			}
			buff.close(); 
			}		
			catch (Exception e){
				System.out.println(e.getMessage());
		}
	}

	public ArrayList<Objet> getListSommet() {
		return listSommet;
	}

	public void setListSommet(ArrayList<Objet> listSommet) {
		this.listSommet = listSommet;
	}
	
	
}
