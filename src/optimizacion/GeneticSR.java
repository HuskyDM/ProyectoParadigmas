/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Jay
 */
public class GeneticSR {
    Random r = new Random();
    double crossoverRate;
    List<Solucion> poblacion;
    double mutationRate;
          
    double[] arrayMin = {2, 2, 2}; 
    double[] arrayMax = {20, 20, 21}; 
    
    public GeneticSR(double crossoverRate, List<Solucion> poblacion, double mutationRate){
        this.crossoverRate = crossoverRate;
        this.poblacion = poblacion;
        this.mutationRate=mutationRate;
    }
    
    /*
    Metodo mutate
    Selecciona un parametro y le añade un valor aleatorio seleccionado
    con distribucion normal, media 0 y una desviasion estandar del 10% del
    parametro elegido. El numero aleatorio tiene la capacidad de ser positivo
    o negativo asi que siempre puede mantenerse dentro del margen establecido.
    Si el parametro se sale del margen se repite la mutacion hasta que el parametro
    vuelva al margen ó hayan pasado 10 corridas.
    */
    
    public void mutate(Solucion individuo){      
        r = new Random();
        int param = r.nextInt(8)+1;
        
        //Se llama a mutate dependiendo del parametro.
        //Se remplaza el valor del parametro en orden
        switch(param){
            case 1:
               individuo.setValue("cell-density",mutateParameter(1, individuo.getValue("cell-density")));
               break;
            case 2:
                individuo.setValue("initial-infected-cell-percentage",mutateParameter(2, individuo.getValue("initial-infected-cell-percentage")));
                break;
            case 3:
                individuo.setValue("viral-reach",mutateParameter(3, individuo.getValue("viral-reach")));
                break;
            case 4:
                individuo.setValue("infection-rate",mutateParameter(4, individuo.getValue("infection-rate")));
                break;
            case 5:
                individuo.setValue("mNeptune-effectiveness",mutateParameter(5, individuo.getValue("mNeptune-effectiveness")));
                break;
            case 6:
                individuo.setValue("initial-probability-of-death",mutateParameter(6, individuo.getValue("initial-probability-of-death")));
                break;
            case 7:
                individuo.setValue("initial-probability-of-chromatin-condensation",mutateParameter(7, individuo.getValue("initial-probability-of-chromatin-condensation")));
                break;
            case 8:
                individuo.setValue("marker-detection-threashold",mutateParameter(8, individuo.getValue("marker-detection-threashold")));
                break;
        }
        
    };
   
    /*
        Metodo mutateParameter
        Recibe el id del parametro a cambiar y el parametro como tal.
        Se genera un numero aleatorio con distribucion normal, de media 0 y desviacion estandar
        igual a un 10% del parametro.
        Luego se revisa hasta unas 10 veces (segun Runnarson) si el parametro esta dentro del margen.
        Si el parametro esta dentro del margen se lleva a Break del for.
        Se retorna el parametro generado.
    */
    public double mutateParameter(int paramId, double parametro){
        double nuevoParametro = parametro;
        boolean dentroMargen=false;
        
        for(int i = 0; i<10;++i){
        this.r=new Random();
        double gaussian = r.nextGaussian()*(0.10*nuevoParametro);
        nuevoParametro=gaussian;
        
        switch(paramId){
            case 1:
                if(nuevoParametro >= arrayMin[1] && nuevoParametro <= arrayMax[1]){
                dentroMargen=true;
                }
                break;
            case 2:
                if(nuevoParametro >= arrayMin[2] && nuevoParametro <= arrayMax[2]){
                dentroMargen=true;
                }
                break;
            case 3:
                if(nuevoParametro >= arrayMin[3] && nuevoParametro <= arrayMax[3]){
                dentroMargen=true;
                }
                break;
            case 4:
                if(nuevoParametro >= arrayMin[4] && nuevoParametro <= arrayMax[4]){
                dentroMargen=true;
                }
                break;
            case 5:
                if(nuevoParametro >= arrayMin[5] && nuevoParametro <= arrayMax[5]){
                dentroMargen=true;
                }
                break;
            case 6:
                if(nuevoParametro >= arrayMin[6] && nuevoParametro <= arrayMax[6]){
                dentroMargen=true;
                }
                break;
            case 7:
                if(nuevoParametro >= arrayMin[7] && nuevoParametro <= arrayMax[7]){
                dentroMargen=true;
                }
                break;
            case 8:
                if(nuevoParametro >= arrayMin[8] && nuevoParametro <= arrayMax[8]){
                dentroMargen=true;
                }
                break;
        }
            if(dentroMargen==true){
                break;
            }        
        }
        return nuevoParametro;
    }
    
    /*
    Metodo crossover genera primero la cantidad de parejas a producir usando el crossoverRate
    dado por la terminal y la funcion floor((crossoverRate*populationSize)/100/2)
    */
    
    public int crossover(){
        
    double crossoverCount;
    double multi = this.crossoverRate*this.poblacion.size();
    double div = (multi/100)/2;
    crossoverCount = Math.floor(div);
    
    return (int)crossoverCount;
    }
    
    /*
    Metodo phi es llamado durante el swap
    Revisa cada parametro y genera un resultado que usa la siguiente funcion
    (paramMin - param)^2 + (paramMax - param)^2
    
    */
     private double phi(Solucion individuo) {
        
        double resultado = 0;
        
           if ((individuo.getValue("Cell-Density") < arrayMin[1]) ||(individuo.getValue("Cell-Density") > arrayMax[1]) ) {
                double total= Math.pow((arrayMin[1]-individuo.getValue("Cell-Density")),2)*Math.pow((arrayMin[1]-individuo.getValue("Cell-Density")),2);
                resultado+=total;
            }
        
        return resultado;
    }
    
    
     public void swap(){
     
         //Parametro indicado por el algoritmo
         
         Double pf = 0.475;    
     
     //Cantidad minima de veces que se debe revisar la poblacion es el tamaño de la poblacion
     //Si en una pasada entera del segundo for no hubo cambio entra Break
     //this.poblacion.size-1 porque existe la posibilidad de llegar al poblador 99 y causar
     //un nullpointerexception
     for (int populationIndex = 0; populationIndex < this.poblacion.size()-1; populationIndex++) {    
           
            //boolean wasSwapped es false por defecto
            //Se vuelve true si hubo un swap dentro del segundo for
            //Si se mantiene false por una pasada entera del segundo for
            //causa un break
            boolean wasSwapped = false;        
            
            for(int j = 0; j<this.poblacion.size()-1;++j){
            
            //phi(arrayParamNuevo, arrayMin, arrayMax );            
            
            //se hace phi del parametro actual y el parametro siguiente. 
            //Se revisa Value (en nuestro caso fitness) si ambos phis son 0 o si el UniformRandom retorna un valor
            //menor pf (0.475)
            if ((phi(this.poblacion.get(j)) == 0 && phi(this.poblacion.get(j+1)) == 0) || UniformRandom.uniform(0, 1) < pf) {
                //Comparación de Value de geneticSR                
                //if (Value(j) > Value(j + 1))
                //Si el fitness del poblador j es mejor que el del poblador j+1 se cambian
                //Esto acerca a los pobladores de mejor fitness hacia la derecha.
                
                if (this.poblacion.get(j).getFitness()> this.poblacion.get(j+1).getFitness()) {
                    //swap(populationIndex, populationIndex + 1);
                    Collections.swap(this.poblacion, j, j+1);
                    wasSwapped = true;
                }
                } else 
                //phi!=0 o el UniformRandom retorno un valor
            
                {
                if (phi(this.poblacion.get(j)) > phi(this.poblacion.get(j+1))) {
                    //swap(populationIndex, populationIndex + 1);
                    Collections.swap(this.poblacion, j, j+1);
                    wasSwapped = true;
                    }
                } 
            }   
            //Si no ocurre ningun swap en una corrida del segundo for se detiene
             if (wasSwapped == false) break;
        }     
     }
    
     public Solucion cruceSexual(Solucion padre, Solucion madre, int id){
        //Por el algoritmo se seleccionan un numero aleatorio de parametros entre 0
        //y la mitad de los parametros.
        //Para los parametros no seleccionados se escoge generan al azar un nuevo
        //parametro dentro de los margenes dados
        //Si obtiene 0 del generador entonces se genera un hijo sin caracteristicas de los
        //padres
        double cell=0.0, initial=0.0, viral=0.0, infection=0.0, mNeptune=0.0, probDead=0.0, probCond=0.0, marker=0.0;
              
        r=new Random();
        int cantidad = r.nextInt(4)+0;
        
        if(cantidad!=0){
            for(int i = 0; i<cantidad;++i){
            r=new Random();
            int aCambiar = r.nextInt(8)+1;
            
            switch(aCambiar){
                case 1: 
                    
                    break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 8: break;
                }
            
            }
            if(cell==0.0){}
            if(initial==0.0){}
            if(viral==0.0){}
            if(infection==0.0){}
            if(mNeptune==0.0){}
            if(probDead==0.0){}
            if(probCond==0.0){}
            if(marker==0.0){}       
        }
        else{
        
            }
        Solucion hijo =  new Solucion(id, cell, initial, viral, infection, mNeptune, probDead, probCond,marker);
        return hijo;
     }
     
    public List<Solucion> createPopulationSR(List<Solucion> population, int crossoverRate) {
         
        //Parametro por defecto del algoritmo                   
        //Population newPopulation = new Population(population.size());
        
        //Parámetros  
        //Se obtiene int crossoverCount que contiene la cantidad de cruces a realizar
        int crossoverCount=crossover();
        List<Solucion> nuevaPoblacion = new ArrayList<Solucion>();
        int restantes = population.size();
        
       //int crossoverCount = r.nextInt(5); //Se generan numeros aleatorios entre 0 y 4
       // double cantidadCruces = Math.floor(crossoverRate * population.size() / 100 / 2);
       
        //Se llama al metodo swap para realizar el bubble sort sobre la lista de
        //Soluciones dada al algoritmo
        swap();
            //Se seleccionan los padres de mayor fitness y se cruzan con el anterior
            //Se va reduciendo de 2 en 2 para manejo de ids
            //Cada padre genera dos hijos
            //Si crossover count = 15 y la poblacion es 30 entonces 
            //los primeros padres generan id 30 y 29, los siguientes 28 y 27, asi sucesivamente
          int id=0;
          for(int i = population.size()-1; i>=(population.size()-1)-crossoverCount;i=i-2 ){
              nuevaPoblacion.add(cruceSexual(population.get(i),population.get(i-1),id));
              nuevaPoblacion.add(cruceSexual(population.get(i),population.get(i-1),id+1));
              id+=2;
              restantes-=2;
          }
          
          //La poblacion restante es generada aleatoriamente de clones de la poblacion vieja
       
          for(int i = 0; i<restantes;++i){
             r = new Random();
             int select = r.nextInt(population.size())+0;
             Solucion viejo = population.get(select);
             Solucion clon = new Solucion(i,viejo.getValue("cell-density"),viejo.getValue("initial-infected-cell-percentage")
                     ,viejo.getValue("viral-reach"),viejo.getValue("infection-rate"),viejo.getValue("mNeptune-effectiveness")
                     ,viejo.getValue("initial-probability-of-death"),viejo.getValue("initial-probability-of-chromatin-condensation")
                     ,viejo.getValue("marker-detection-threashold"));
             
          }
          //cada parametro pasa por una mutacion
          //El chance es un numero aleatorio entre 0 a 100, la posibilidad es dada
          //por el usuario
          for(int i=0; i<nuevaPoblacion.size();++i){
              r=new Random();
              double rate = r.nextDouble()*(100.0);
              if(rate<=mutationRate){
                  mutate(nuevaPoblacion.get(i));
              }
          }
        //Se retorna la nueva poblacion para hacer un nuevo ciclo
        return nuevaPoblacion;
    }
}

 /*
     Metodo cantidadErrores se usa en el bubble sort descrito por Runnarson, fuente Copasi.
     Este metodo revisa cada uno de los parametros del individuo y retorna la cantidad de veces que
     el parametro se sale de los margenes establecidos. Es una variacion del calculo de phi
     */
    
   /* public int cantidadErrores(Solucion individuo){
        int errores=0;
        if(individuo.getValue("Cell-Density")<2.0 || individuo.getValue("Cell-Density")>20.0){
            errores++;
        }
        return errores;
    }*/


/*
 for (int x = 0; x < cantidadCruces; x++) {
            //Se obtienen parametros de los padres
            int[] parametrosPadre1;
            int[] parametrosPadre2;
            
            //Se inicializan los parametros de los hijos
            int[] parametrosHijo1 = null;
            int[] parametrosHijo2 = null;
            int[] parametrosHijo3 = null;
            int[] parametrosHijo4 = null;
            
            //Se realiza el cruce
            for (int y = 0; y < crossoverCount; y++) {
                
            }
            
            //Se crean los nuevos individuos
            Individual i1 = new Individual(parametrosHijo1);
            Individual i2 = new Individual(parametrosHijo2);
            Individual i3 = new Individual(parametrosHijo3);
            Individual i4 = new Individual(parametrosHijo4);
            
            int counter = 0;
            while (counter < 6 && newPopulationIndex < population.size()) {
                switch(counter) {
                    case 0:*/
                        //newPopulation.setIndividual(newPopulationIndex, /*Padre1*/ population.getIndividual(0));
                        //break;
                    //case 1:
                      //  newPopulation.setIndividual(newPopulationIndex, /*Padre2*/ population.getIndividual(1));
                        //break;
                    //case 2:
                      //  newPopulation.setIndividual(newPopulationIndex, /*Hijo1*/ i1);
                        //break;
                    //case 3:
                      //  newPopulation.setIndividual(newPopulationIndex, /*Hijo2*/ i2);
                        //break;
                    //case 4:
                       // newPopulation.setIndividual(newPopulationIndex, /*Hijo3*/ i3);
                        //break;
                    //case 5:
                       // newPopulation.setIndividual(newPopulationIndex, /*Hijo4*/ i4);
                        //break;    
                //}
              //  counter++;
            //    newPopulationIndex++;
          //  }
        //}