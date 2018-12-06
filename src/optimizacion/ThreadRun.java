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
public class ThreadRun implements Runnable{
    
    int id;
    HashMap<String,Double>[] resultados;
    HashMap<String,Double> hmap;
    
    public ThreadRun(int id, HashMap<String,Double>[] resultados, HashMap<String,Double> hmap){
        this.id=id;
        this.resultados=resultados;
        this.hmap=hmap;
    }
    
    @Override
    public void run(){
         System.out.println("Llegue a los threads soy id:"+id);
         NetLogoSimMod simulacion = new NetLogoSimMod("C:\\Users\\Jay\\Desktop\\simulacion\\zika.nlogo",id);
         simulacion.updateParams(hmap);
            try{
                resultados[id]=simulacion.runSimulation();
                }
            catch(Exception e)
                {
                e.printStackTrace();
                }
    }
}
