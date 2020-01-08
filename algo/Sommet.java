/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ileft
 */
public class Sommet {
    String name ;
    public String Couleur ; 
    public List<Sommet> voisins = new ArrayList<Sommet>();
    public Sommet(String name , String Couleur)
    {
        this.name=name;
        this.Couleur=Couleur;
    }
    
}