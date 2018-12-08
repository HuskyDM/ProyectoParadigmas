/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizacion;

import java.util.Random;

/**
 *
 * @author Jay
 */
public class GeneticSR {
    Random r = new Random();
    
    public void mutate(){};
    
    public Population mutatePopulationSR(Population population) {
        Double pf = 0.475;  //This parameter is a numerical value in the interval (0, 1)
                            //determining the chance that individuals either outside the parameter boundaries
                            //or violating the constraints are compared during the selection. The default is '0.475'.             
                            
        Population newPopulation = new Population(population.size());
        boolean wasSwapped = false;


          //Parámetros  
           double[] arrayParamViejo = {1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5 };
          
           double[] arrayParamNuevo = {1, 2, 3, 4, 5, 6, 7, 8 };
           
          //Constraints
           double[] arrayConstraints = {10.5 , 11.5 , 12.5 , 13.5}; 
         
 
        
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            
                //Se agrega al gen el random
            //double noise = r.nextGaussian() * Math.sqrt(variance) + mean;
            double random = r.nextGaussian() * 0.1 + 0;
            
            //individual.setInfection-rate(individual.getInfection-rate + random);
            //individual.setfluorescence(individual.getfluorescence + random);
            
            
              //Revisar si el parámetro sobrepasa su constraint
           if( individual.getInfection-rate() < arrayConstraints){
           
               phi(arrayParamViejo, arrayParamNuevo, arrayConstraints );
              
           }
            
            
            // within bounds or random chance
            if ((phi(populationIndex) == 0 && phi(populationIndex + 1) == 0) || UniformRandom.uniform(0, 1) < pf) {
                //Comparación de Value de geneticSR
                
                //if (Value(j) > Value(j + 1))
                if (population.getIndividual(populationIndex).getFitness()
                        > population.getIndividual(populationIndex + 1).getFitness()) {
                    swap(populationIndex, populationIndex + 1);
                    wasSwapped = true;
                }
            } else {
                if (phi(populationIndex) > phi(populationIndex + 1)) {
                    swap(populationIndex, populationIndex + 1);
                    wasSwapped = true;
                }
            }
            
            // Add individual to population
            //newPopulation.setIndividual(populationIndex, individual);
        }
        
        if (wasSwapped == false) {
            return population;
        }
        
        return newPopulation;
    }
    
    
    
    
    private double phi(double[] arrayParamViejo, double[] arrayParamNuevo, double[] arrayConstraints) {
        
        int cantidadParams = 4;
                
        double resultado = 0;
        
        
        for(int i = 0; i < cantidadParams; i++ ){
        
            if(arrayParamNuevo[i] < arrayParamNuevo[i]){
            
                //(Parametro viejo - parametro nuevo)'2
               resultado += (arrayParamViejo[i]-arrayParamNuevo[i])*(arrayParamViejo[i]-arrayParamNuevo[i]);
                
            }
            
        }
        
        
        return resultado;
    }
     
    
    
    
    private void swap(int valor1, int valor2) {
        
        
        
        
    }
}
