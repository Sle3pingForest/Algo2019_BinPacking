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
public class Clause {
    private  boolean unaire;  //true uniare false binaire
    private final String var ;
    private final String color;
    
    private final String var2; 
    private final String color2; 
    public Clause(String v , String c ){
        this.setUnaire();
        this.var=v;
        this.color=c;
        this.var2= "";
        this.color2="";
      
    }
    public Clause(String v , String c ,String v2 ,String c2 ){
        this.setBinaire();
        this.var=v;
        this.color=c;
        this.var2=v2;
        this.color2=c2;
        
       
    }
    
    public String getVar(){
        return this.var;
    }
    public String getColor(){
        return this.color;
    }
    public void setBinaire()
    {
        
        this.unaire=false;
    }
     public void setUnaire()
    {
        
        this.unaire=true;
    }
    public boolean getUniare()
    {
      return this.unaire;
    }

    public String getColor2() {
        return color2;
    }
    
     public String getVar2(){
        return this.var2;
    }
  
 
   
}
