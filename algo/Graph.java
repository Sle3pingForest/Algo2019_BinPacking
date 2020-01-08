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
public class Graph {

  public Sommet racine ; 
  public List<Sommet> sommets;
    Graph(Sommet sommet) {
     
    this.racine = sommet;
    }

    Graph() {
        sommets = new ArrayList<Sommet>();
    }
  

}
