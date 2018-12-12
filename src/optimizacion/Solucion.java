/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizacion;

import java.util.HashMap;

/**
 *
 * @author Jay
 */
public class Solucion {
    
    int id;
    HashMap<String,Double> hmap;
    
    public Solucion(int id, double cell, double infected, double viral, double infection, double mNeptune, double probDeath, double probCond, double marker){
        this.hmap=new HashMap<String,Double>();
        this.id=id;
        
        this.hmap.put("id",(double)id);
        this.hmap.put("cell-density", cell);
        this.hmap.put("initial-infected-cell-percentage", infected);
        this.hmap.put("viral-reach", viral);
        this.hmap.put("infection-rate", infection);
        this.hmap.put("mNeptune-effectiveness", mNeptune);
        this.hmap.put("initial-probability-of-death", probDeath);
        this.hmap.put("initial-probability-of-chromatin-condensation", probCond);
        this.hmap.put("marker-detection-threashold", marker);
        
        this.hmap.put("alive1",0.0);
        this.hmap.put("dead1",0.0);
        this.hmap.put("infected1",0.0);
        
        this.hmap.put("alive2",0.0);
        this.hmap.put("dead2",0.0);
        this.hmap.put("infected2",0.0);
        
        this.hmap.put("alive3",0.0);
        this.hmap.put("dead3",0.0);
        this.hmap.put("infected3",0.0);
        
        this.hmap.put("alive4",0.0);
        this.hmap.put("dead4",0.0);
        this.hmap.put("infected4",0.0);
        
        this.hmap.put("alive5",0.0);
        this.hmap.put("dead5",0.0);
        this.hmap.put("infected5",0.0);
    
        this.hmap.put("Fitness", 0.0);
        this.hmap.put("Cell-Total",0.0);
    }
    
    public double getValue(String key){
        return this.hmap.get(key);
    }
    
    public HashMap<String, Double> getAllMap(){
        return this.hmap;
    }
    
    public HashMap<String,Double> getParameters(){
        HashMap<String,Double> parameters = new HashMap<String,Double>();
        
        parameters.put("cell-density", this.hmap.get("cell-density"));
        parameters.put("initial-infected-cell-percentage", this.hmap.get("initial-infected-cell-percentage"));
        parameters.put("viral-reach",  this.hmap.get("viral-reach"));
        parameters.put("infection-rate",  this.hmap.get("infection-rate"));
        parameters.put("mNeptune-effectiveness",  this.hmap.get("mNeptune-effectiveness"));
        parameters.put("initial-probability-of-death",  this.hmap.get("initial-probability-of-death"));
        parameters.put("initial-probability-of-chromatin-condensation",  this.hmap.get("initial-probability-of-chromatin-condensation"));
        parameters.put("marker-detection-threashold",  this.hmap.get("marker-detection-threashold"));
        
        return parameters;
    }
    
    public void setValue(String key, double value){
        this.hmap.replace(key, value);
    }
    
    public HashMap<String,Double> getLastResults(){
        HashMap<String,Double> results = new HashMap<String,Double>();
        results.put("alive5",this.hmap.get("alive5"));
        results.put("dead5",this.hmap.get("dead5"));
        results.put("infected5",this.hmap.get("infected5"));
        return results;
    }
    
    public void setCellTotal(){
        this.hmap.replace("Cell-Total",this.hmap.get("alive5")+this.hmap.get("dead5")+this.hmap.get("infected5"));
    }
    
    public double getCellTotal(){
        //double total = this.hmap.get("alive5")+this.hmap.get("dead5")+this.hmap.get("infected5");
        //return total;
        double total = this.hmap.get("Cell-Total");
        return total;
    }
    
    public void setFitness(){
    
        double fitness=0;
        
        double labAlive = 20.7832829381;
        double labDead = 68.1157972467;
        double labInfected = 11.1009198152;
        
        double total = this.hmap.get("Cell-Total");
        double promAlive = (this.hmap.get("alive5")*100)/total;
        double promDead = (this.hmap.get("dead5")*100)/total;
        double promInfected =  (this.hmap.get("infected5")*100)/total;
        
       // double sum = promAlive+promDead+promInfected;
        
        double downAlive=promAlive-labAlive;
        double downDead=promDead-labDead;
        double downInfected=promInfected-labInfected;
        
        double squareAlive=Math.pow(downAlive, 2);
        double squareDead=Math.pow(downDead, 2);
        double squareInfected=Math.pow(downInfected, 2);
        
        
        double sumOfAll = squareAlive+squareDead+squareInfected;        
        double newValue = sumOfAll/3;        
        fitness = Math.sqrt(newValue);
        this.hmap.replace("Fitness", fitness);
    }
    
    public double getFitness(){  
        return this.hmap.get("Fitness");
    }
    
    public int getId(){
        return this.id;
    }
    
}
