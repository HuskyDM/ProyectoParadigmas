/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizacion;

/**
 *
 * @author Jay
 */
public class GeneticSR {
    int i;
    
    public Population mutatePopulationSR(Population population) {
        Phi phi = new Phi();
        
        UniformRandom uniformRandom = new UniformRandom();
        Double pf = 0.475;  //This parameter is a numerical value in the interval (0, 1)
                            //determining the chance that individuals either outside the parameter boundaries
                            //or violating the constraints are compared during the selection. The default is '0.475'.

        // Initialize new population
	Population newPopulation = new Population(this.populationSize);
        
        boolean wasSwapped = false;

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            // within bounds or random chance
            if( (phi.phi(populationIndex) == 0 && phi.phi(populationIndex+1) == 0) || uniformRandom.uniform(0, 1) < pf ){
                //Comparación de Value de geneticSR
                
                //if (Value(j) > Value(j + 1))
                if(population.getFittest(populationIndex) > population.getFittest(populationIndex+1)) {
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
            newPopulation.setIndividual(populationIndex, individual);
        }
        
        if (wasSwapped == false) break;
        
        // Return mutated population
        return newPopulation;
    }
}
