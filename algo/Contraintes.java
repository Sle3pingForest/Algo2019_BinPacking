/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author ileft
 */
public class Contraintes {

    private Clause clauseC2;

    public enum Color {
        R,
        B,
        V
    }
    private List<Clause> contrainte;
    private int nbvarDiff;   //nbr de var differentes
    private ArrayList<String> var = new ArrayList();
    private ArrayList<Clause> coloration = new ArrayList<Clause>();
    private String c1;
    private String c2;
    private String c3;

    public Contraintes() {
        this.contrainte = new ArrayList<Clause>();
        this.nbvarDiff = 0;

    }

    public Contraintes(List<Clause> contrainte) {
        this.contrainte = new ArrayList<Clause>();
        this.contrainte = contrainte;
        this.nbvarDiff = 0;
    }

    public List getContraintes() {
        return this.contrainte;
    }

    public List getColoration() {
        return this.coloration;
    }

    public void addClause(Clause c) {
        this.contrainte.add(c);

        /*  if (c.getUniare() == true) {
            if (this.verifExistance(c.getVar()) == false) {
                var.add(c.getVar());
                this.setNbVarDiff(this.getNbVarDiff() + 1);
            }

            List<Clause> con = this.getContraintes();
            String s1 = c.getVar();
            for (Clause cl : con) {
                String s2 = cl.getVar();
                if (s2.compareTo(s1) == 0) {
                    System.out.println(s1 + " equals " + s2);
                } else {

                    System.out.println(s1 + " diffrent from " + s2);

                }
            }
        }
        if (c.getUniare() == false) {
            if (this.verifExistance(c.getVar()) == false) {
                var.add(c.getVar());
                this.setNbVarDiff(this.getNbVarDiff() + 1);
            }
            if (this.verifExistance(c.getVar2()) == false) {
                var.add(c.getVar2());
                this.setNbVarDiff(this.getNbVarDiff() + 1);
            }

        }*/

    }

    public boolean verifExistance(String s) {
        if (var.contains(s)) {
            return true;
        } else {
            return false;
        }
    }

    public void displayContraintes() {
        ListIterator<Clause> c = contrainte.listIterator();
        while (c.hasNext()) {
            Clause clause = c.next();
            if (clause.getUniare()) {
                System.out.println("( " + clause.getVar() + " , " + clause.getColor() + " ) ");
            }
            if (!clause.getUniare()) {
                System.out.println("[( " + clause.getVar() + " , " + clause.getColor() + " ) , "
                        + "( " + clause.getVar2() + " , " + clause.getColor2() + " )] ");
            }
        }
        System.out.print("nb variable different :" + this.getNbVarDiff());
    }

    public void setNbVarDiff(int n) {
        this.nbvarDiff = n;
    }

    public int getNbVarDiff() {
        return this.nbvarDiff;
    }

    public boolean parcourirAlgo() {
        Boolean n ;
       n= this.parcourirCas1();
       if(n){
            this.parcourirCas2();
            this.parcourirCas3();
            if(this.ListecontrainteUn().isEmpty())
            {
                this.parcourirCas4();
           return  parcourirAlgo();
            }
       }
        
       return true  ;
    }

    public boolean cas1(Clause c) {

        ArrayList<Boolean> found = new ArrayList();
        found.add(Boolean.FALSE);
        found.add(Boolean.FALSE);
        found.add(Boolean.FALSE);
        ArrayList<Clause> cl = new ArrayList();
        ArrayList<String> color = new ArrayList();
        String s1 = c.getVar();
        cl.add(c);
        color.add(c.getColor());
        List<Clause> con = this.ListecontrainteUn();

        for (Clause clause : con) {
            String s2 = clause.getVar();
             System.out.println(" clause " + s2);
                if (s1.compareTo(s2) == 0) {
                    if (cl.contains(clause) == false) {
                        cl.add(clause);
                      
                        if (color.contains(clause.getColor()) == false) {
                            color.add(clause.getColor());
                        }
                    }
                
            }
        }
        if (color.contains("R") && color.contains("B") && color.contains("V")) {
            return false;
        }

        return true;
    }
    
    public boolean parcourirCas1() {
      
        List<Clause> con = this.getContraintes();
         for (Clause clause : con) {
            if (clause != null) {
                if (clause.getUniare()) {
                    if (cas1(clause)) {
                        System.out.println("Cas 1 : Safe \n");             
                    }
                    if (!cas1(clause)) {
                        System.out.println("L'algorithme répond FALSE. Echec dans 'cas 1' avec le var :" + clause.getVar());

                        return false;
                    }

                }
            }
        }
        return true;

    }
    public void parcourirCas2() {
        List<Clause> con = this.ListecontrainteUn();
        for (Clause clause : con) {

            if (this.Existe(clause)) {
                this.cas2(clause);             // 2 contraintes unaires
            }
        }
    }
    public void cas2(Clause c) { // a vedere
        List<Clause> list = ListecontrainteUn();
        for (Clause clause : list) {
            if (clause.getVar().equals(c.getVar())) {
                this.contrainte.remove(c);
            }
       
        }
        List<Clause> liste = ListecontrainteBin();
        for (Clause clause : liste) {
            if ((clause.getVar().equals(c.getVar()) ) && (clause.getColor().equals(c.getColor())  ||
                     clause.getColor().equals(this.clauseC2.getColor())) ) {
               
                this.contrainte.remove(clause);
            }
            else if(clause.getVar().equals(c.getVar()) )
            {
                Clause n= new Clause(clause.getVar2() , clause.getColor2());
                this.contrainte.add(n);
                n.setUnaire();
                this.contrainte.remove(clause);
            }
       
        }
     
    }

    public void remove(ArrayList<Clause> clauses) {

        for (Clause clause : clauses) {
            if (!clause.getUniare()) {
                this.getContraintes().add(new Clause(clause.getVar2(), clause.getColor2()));
            }
            this.contrainte.remove(clause);
        }
    }

    public void cas3(Clause c) {
        List<Clause> con = this.ListecontrainteBin();
        ArrayList<Clause> CoupleContrainte = new ArrayList();
        String s1 = c.getVar();
        String c1 = c.getColor();
        int compt = 0;
        for (Clause clause : con) {
            String s2 = clause.getVar();
            String c2 = clause.getColor();
           
                if (s1.compareTo(s2) == 0 && c1.compareTo(c2) == 0) {
                    this.contrainte.remove(clause);
                } else if (s1.compareTo(s2) == 0 && c1.compareTo(c2) != 0) {
                    compt++;
                    CoupleContrainte.add(clause);
                    this.contrainte.remove(clause);
                }

            
        }
        CreatContrainte(CoupleContrainte, compt);
    }
    public void parcourirCas3()
    {
         List<Clause> con = this.ListecontrainteUn();
         for (Clause clause : con) { 
                this.cas3(clause);
                 }
    }
    public void CreatContrainte(ArrayList<Clause> c, int compt) {
       

        for (int i = 0; i <compt-1 ; i++) {
            this.contrainte.add(new Clause(c.get(i).getVar2(), c.get(i).getColor2(), c.get(i + 1).getVar2(), c.get(i + 1).getColor2()));
            String r = c.get(i).getVar2();
            System.out.println("**********this :  "+r );
        }

    }

    public List<Clause> ListecontrainteUn() {
        List<Clause> con = new ArrayList();
        List<Clause> list = this.getContraintes();
        for (Clause clause : list) {
            if (clause.getUniare()) {
                con.add(clause);
            }
        }

        return con;
    }

    public List<Clause> ListecontrainteBin() {
        List<Clause> con = new ArrayList();
        List<Clause> list = this.getContraintes();
        for (Clause clause : list) {
            if (!clause.getUniare()) {
                con.add(clause);
            }
        }

        return con;
    }
    public boolean Existe(Clause c) {
        List<Clause> list = ListecontrainteUn();
        String s1 = c.getVar();
        String c1 =c.getColor();
        for (Clause clause : list) {
         
                System.out.println(" unaire" + clause.getColor());
                String s2 = clause.getVar();
                String c2 = clause.getColor();
                System.out.println("compare " + s1 + " to : " + s2);
                if (s1.compareTo(s2) == 0 && c1.compareTo(c2) != 0) {
                    this.setClauseCas2(clause);
                    return true; //oui il existe

                }
            }

        
        return false; //non elle est unique
    }
    public void setClauseCas2(Clause c){
        this.clauseC2 = c ;
    }
    public void Cas4(Clause c) {
        double i = (Math.random() * (4 - 1));
        this.detColor(c);
        switch ((int) i) {
            case 1:
                this.contrainte.add(new Clause(c.getVar(), c.getColor()));
                this.detColor(new Clause(c.getVar2(), c.getColor2()));
                this.contrainte.add(new Clause(c.getVar2(), c2));
                break;
            case 2:
                this.contrainte.add(new Clause(c.getVar(), c.getColor()));
                this.detColor(new Clause(c.getVar2(), c.getColor2()));
                this.contrainte.add(new Clause(c.getVar2(), c3));
                break;
            case 3:
                this.detColor(c);
                this.contrainte.add(new Clause(c.getVar(), c2));
                this.contrainte.add(new Clause(c.getVar2(), c.getColor2()));
                break;
            case 4:
                this.detColor(c);
                this.contrainte.add(new Clause(c.getVar(), c3));
                this.contrainte.add(new Clause(c.getVar2(), c.getColor2()));
                break;
        }
        this.contrainte.remove(c);
    }

    public void detColor(Clause c) {
        c1 = c.getColor();
        switch (c1) {
            case "R":
                c2 = "B";
                c3 = "V";
                break;
            case "B":
                c2 = "R";
                c3 = "V";
                break;
            case "V":
                c2 = "R";
                c3 = "B";
                break;
        }

    }
    public void parcourirCas4(){
       
               List<Clause> list = this.ListecontrainteBin();
           
                for (Clause clause : list) {
                    this.Cas4(clause);
                }
        
    }

}
