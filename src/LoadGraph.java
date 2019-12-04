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
		for(int i = 1; i <= numSommet ; i ++) {
			Random rnd = new Random();
			int x = 10 + rnd.nextInt(40);
			this.listSommet.add(new Objet(""+ i, x));
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
				if(i > 13) {
					String[] arrOfStr = ligne.split(" ");
					//System.out.println(arrOfStr[1] + "****" + arrOfStr[2]) ;
					listSommet.get(Integer.parseInt(String.valueOf(arrOfStr[1])) -1).settingSommetRelie(listSommet.get(Integer.parseInt(String.valueOf(arrOfStr[2])) - 1));
				
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
