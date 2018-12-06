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

        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            //Se agrega al gen el random
            //double noise = r.nextGaussian() * Math.sqrt(variance) + mean;
            double random = r.nextGaussian() * 0.1 + 0;
            
            //individual.setInfection-rate(individual.getInfection-rate + random);
            //individual.setfluorescence(individual.getfluorescence + random);
            
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
    
    private int phi(double valor) {
        
        return 0;
    }
    
    private void swap(int valor1, int valor2) {
        
    }
}
