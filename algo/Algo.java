/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

/**
 *
 * @author ileft
 */
public class Algo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Clause x1 = new Clause("x","R");
        Clause x11 = new Clause("x","B");
        Clause x8 = new Clause("x","R");
        Clause x12 = new Clause("x","B","x2","R");
        Clause x2 = new Clause("x","R","x3","B");
        Clause x21 = new Clause("x","V","x3","V");
        Clause x3 = new Clause("x","B","x5","R");
        Clause x4 = new Clause("x","V","x5","B");
        Clause x5 = new Clause("x","B","x5","V");
        Clause x6 = new Clause("x3","V","x4","V");
        Clause x7 = new Clause("x3","B","x5","R");
        Clause x9 = new Clause("x2","V");
        Clause x10 = new Clause("x2","B");

        Contraintes c = new Contraintes();
        c.addClause(x2);
        c.addClause(x1);
        c.addClause(x11);
        c.addClause(x12);
        c.addClause(x21);
        c.addClause(x3);
        c.addClause(x4);
        c.addClause(x5);
        c.addClause(x6);
        c.addClause(x7);
       // c.addClause(x8);
        c.addClause(x9);
        c.addClause(x10);
        c.displayContraintes();
       
         c.parcourirAlgo();
      
          c.displayContraintes();
        
    }}

