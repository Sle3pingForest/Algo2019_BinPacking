/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ileft
 */
public class LireGraphe {

    private int nbVar =0;
    private int nbligne=0;
    public final static String path = FileSystems.getDefault().getPath(".").toAbsolutePath().toString() + "/src/algo/graphes/Graph3.txt";
    private int[][] graphe;
    private String c1 = "R";
    private String c2 = "V";
    private String c3 = "B";
    public Graph g ;
    Contraintes contraintes;
    public void LireGraphes() throws IOException {

        BufferedReader helpReader = new BufferedReader(new FileReader(path));
        String ligneGraphe;
        nbVar = this.nbVar();
       
        nbligne = this.nbLigne();
        this.graphe = new int[nbVar][nbligne];

        int l = 0;
        int c = 0;
        while ((ligneGraphe = helpReader.readLine()) != null) {
            char[] vars = ligneGraphe.toCharArray();

            for (c = 0; c < vars.length; c++) {
                this.graphe[l][c] = Integer.valueOf(String.valueOf(vars[c]));
            }
            l++;}
        helpReader.close();
    }
    
    public int nbLigne() throws IOException{
        BufferedReader helpReader = new BufferedReader(new FileReader(path));
        String ligneGraphe;
          while ((ligneGraphe = helpReader.readLine()) != null) {
              this.nbligne++;
          }
          return this.nbligne;
    }
    public int nbVar() throws IOException{
        BufferedReader helpReader = new BufferedReader(new FileReader(path));
       String ligneGraphe = helpReader.readLine();
        nbVar = (ligneGraphe.split(" ")[0]).length();
          return this.nbVar;
    }
    
    public void creerGraph() {
       g = new Graph();
      
        for (int j = 0; j < nbVar; j++) {
            Sommet S = new Sommet("x"+j , "");
            g.sommets.add(S);
        }

        for (int i = 0; i < nbligne; i++) {
            for (int j = 0; j < nbVar; j++) {
                Sommet act;
                if (graphe[i][j] != 0) {
                    act = g.sommets.get(i);
                   

                    if(!act.voisins.contains(g.sommets.get(j).name)){
                      act.voisins.add(g.sommets.get(j));
                     
                       
                    }
                }

            }

        }
    }

    public void CreerContrainte() {
        this.contraintes =new Contraintes();
        for (int i = 0; i < nbligne; i++) {
            for (int j = 0; j < nbVar; j++) {             
                Sommet act =g.sommets.get(i);
                if (graphe[i][j] != 0) {      
                     this.voisinCommun(act,g.sommets.get(j) );
                    
                     this.contraintes.addClause(new Clause(g.sommets.get(i).name, "R",g.sommets.get(j).name, "R"));
                  //  c = new Clause(g.sommets.get(j).name, "R");
                    this.contraintes.addClause(new Clause(g.sommets.get(j).name, "R"));

                }
            }

        }

    }
//indice 
    public void voisinCommun(Sommet s , Sommet c){
        for (int i = 0; i < s.voisins.size(); i++) {
            for (int j = 0; j < c.voisins.size(); j++) {
                   if(s.voisins.get(i).name.equals(c.voisins.get(j).name)){
                          this.contraintes.addClause(new Clause(s.name, "R" , c.name , "R"));
                          this.contraintes.addClause(new Clause(c.name, "B" , s.voisins.get(i).name , "B"));
                          this.contraintes.Cas4(new Clause(s.voisins.get(i).name, "V"));
                   }
                   
            }

        }
      
    }

    public static void main(String[] args) {
        LireGraphe g = new LireGraphe();
        try {
            g.LireGraphes();
            /*for (int i = 0; i < g.nbligne; i++) {
                  for (int j = 0; j < g.nbVar; j++) {
                      System.out.print(" "+ g.graphe[i][j]);
            }
               System.out.print("\n");
            }*/
            g.creerGraph();
            //System.out.print(g.g.sommets.get(0).name);
            Sommet s1 =g.g.sommets.get(0);
             for (Sommet s : s1.voisins) {
                 System.out.print( " "+s.name);
                 
             }
             g.CreerContrainte();
             g.contraintes.parcourirAlgo();
        } catch (IOException ex) {
            Logger.getLogger(LireGraphe.class.getName()).log(Level.SEVERE, null, ex);
        
    }

    }}
