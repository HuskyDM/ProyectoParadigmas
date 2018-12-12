/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jay
 */
public class ThreadSetup {
    
    private HashMap<String,Double>[] results;
    private List<Solucion> soluciones;
    
    
    ThreadSetup(List<Solucion> dalist){
    
    this.soluciones=new ArrayList<Solucion>();    
    results = new HashMap[5];
    Thread t1 = new Thread(new ThreadRun(0, results, dalist.get(0).getParameters()));
    Thread t2 = new Thread(new ThreadRun(1, results, dalist.get(1).getParameters()));
    Thread t3 = new Thread(new ThreadRun(2, results, dalist.get(2).getParameters()));
    Thread t4 = new Thread(new ThreadRun(3, results, dalist.get(3).getParameters()));
    Thread t5 = new Thread(new ThreadRun(4, results, dalist.get(4).getParameters()));
    
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    
    try{
    t1.join();
    t2.join();
    t3.join();
    t4.join();
    t5.join();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    for(int i=0;i<results.length;++i){
            
            dalist.get(i).setValue("alive1",results[i].get("alive1"));
            dalist.get(i).setValue("dead1",results[i].get("dead1"));
            dalist.get(i).setValue("infected1",results[i].get("infected1"));
           
            dalist.get(i).setValue("alive2",results[i].get("alive2"));
            dalist.get(i).setValue("dead2",results[i].get("dead2"));
            dalist.get(i).setValue("infected2",results[i].get("infected2"));
            
            
            dalist.get(i).setValue("alive3",results[i].get("alive3"));
            dalist.get(i).setValue("dead3",results[i].get("dead3"));
            dalist.get(i).setValue("infected3",results[i].get("infected3"));
           
            dalist.get(i).setValue("alive4",results[i].get("alive4"));
            dalist.get(i).setValue("dead4",results[i].get("dead4"));
            dalist.get(i).setValue("infected4",results[i].get("infected4"));
           
            dalist.get(i).setValue("alive5",results[i].get("alive5"));
            dalist.get(i).setValue("dead5",results[i].get("dead5"));
            dalist.get(i).setValue("infected5",results[i].get("infected5"));
           
            dalist.get(i).setCellTotal();
            dalist.get(i).setFitness();
            
            soluciones.add(dalist.get(i));
    }
        
    
    }      
    
    public void setValues(List<Solucion> dalist, HashMap<String,Double>[]results){
        for(int i=0;i<results.length;++i){
            dalist.get(i).setValue("alive1",results[i].get("alive"));
            dalist.get(i).setValue("dead1",results[i].get("dead1"));
            dalist.get(i).setValue("infected1",results[i].get("infected1"));
           
        }
        
    }
    
    public List<Solucion>getResults(){
        return this.soluciones;
        }
}
