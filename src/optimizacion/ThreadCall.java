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
    
    List<Solucion> iniciales;
    String filename;
    double crossover;
    
        public ThreadCall(String filename, double crossover,List<Solucion> iniciales){
            
            this.iniciales=iniciales;
            this.filename = filename;
            this.crossover = crossover;
        }
    
    public void runProject() {
       int gen=1;     
        Printer printer = new Printer();
        ThreadSetup mid = new ThreadSetup(iniciales);
        List<Solucion>results = mid.getResults();
        
        while(true){                   
              
        printer.printResults(filename,results,gen);   
        gen++;
        GeneticSR genetic = new GeneticSR(crossover,results,0.01);
        List<Solucion>nuevaGen = genetic.createPopulationSR();
        System.out.println("Tamaño de la nueva gen: "+nuevaGen.size());
        ThreadSetup newMid = new ThreadSetup(nuevaGen);
        results=newMid.getResults();
        printer.printResults("newPop", nuevaGen, gen);
        }
    }
    
}

        //this.iniciales = new ArrayList<Solucion>();

       /*HashMap<String,Double> hmap = new HashMap<String,Double>();
       hmap.put("cell-density", 2.00);
       hmap.put("initial-infected-cell-percentage", 0.0);
       hmap.put("viral-reach", 1.5);
       hmap.put("infection-rate", 15.0);
       hmap.put("mNeptune-effectiveness", 100.0);
       hmap.put("initial-probability-of-death", 0.595);
       hmap.put("initial-probability-of-chromatin-condensation", 2.50);
       hmap.put("marker-detection-threashold",0.12);*/

/*System.out.println("sol1 alive:"+tru.iniciales.get(0).getValue("alive5"));
      System.out.println("sol1 infected:"+tru.iniciales.get(0).getValue("infected5"));
      System.out.println("sol1 dead:"+tru.iniciales.get(0).getValue("dead5"));
      
      System.out.println("sol2 alive:"+tru.iniciales.get(1).getValue("alive5"));
      System.out.println("sol2 infected:"+tru.iniciales.get(1).getValue("infected5"));
      System.out.println("sol2 dead:"+tru.iniciales.get(1).getValue("dead5"));*/


              
       //List<HashMap<String,Double>> dalist = new ArrayList<HashMap<String,Double>>();
       //ThreadCall tru = new ThreadCall();
       
        // tru.iniciales.add(sol1);
        //tru.iniciales.add(sol2);
        
       //System.out.println(sol1.getValue("mNeptune-effectiveness"));
       //HashMap<String,Double> hmap2 = new HashMap<String,Double>();
       //hmap2=sol1.getParameters();
       //System.out.println("hmap2="+sol1.getValue("cell-density"));