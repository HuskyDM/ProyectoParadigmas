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
public class ThreadCall {

    /**
     * @param args the command line arguments
     */
    
    List<Solucion> iniciales;
    
        public ThreadCall(){
            this.iniciales = new ArrayList<Solucion>();
        }
    
    public void runProject() {
       HashMap<String,Double> hmap = new HashMap<String,Double>();
       hmap.put("cell-density", 2.00);
       hmap.put("initial-infected-cell-percentage", 0.0);
       hmap.put("viral-reach", 1.5);
       hmap.put("infection-rate", 15.0);
       hmap.put("mNeptune-effectiveness", 100.0);
       hmap.put("initial-probability-of-death", 0.595);
       hmap.put("initial-probability-of-chromatin-condensation", 2.50);
       hmap.put("marker-detection-threashold",0.12);
      
       //dalist.add(hmap);
       //dalist.add(hmap);
      
       Solucion sol1 = new Solucion(0, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol2 = new Solucion(1, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       
       //List<HashMap<String,Double>> dalist = new ArrayList<HashMap<String,Double>>();
       ThreadCall tru = new ThreadCall();
       
       tru.iniciales.add(sol1);
       tru.iniciales.add(sol2);
        
       //System.out.println(sol1.getValue("mNeptune-effectiveness"));
       //HashMap<String,Double> hmap2 = new HashMap<String,Double>();
       //hmap2=sol1.getParameters();
       //System.out.println("hmap2="+sol1.getValue("cell-density"));
       ThreadSetup mid = new ThreadSetup(tru.iniciales);
       
      List<Solucion>results = mid.getResults();
      Printer printer = new Printer();  
      
      printer.printResults("threads",results);
      
      System.out.println("sol1 alive:"+tru.iniciales.get(0).getValue("alive5"));
      System.out.println("sol1 infected:"+tru.iniciales.get(0).getValue("infected5"));
      System.out.println("sol1 dead:"+tru.iniciales.get(0).getValue("dead5"));
      
      System.out.println("sol2 alive:"+tru.iniciales.get(1).getValue("alive5"));
      System.out.println("sol2 infected:"+tru.iniciales.get(1).getValue("infected5"));
      System.out.println("sol2 dead:"+tru.iniciales.get(1).getValue("dead5"));
      
    }
    
}
