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
    
    
    
        Phi phi = new Phi();
                UniformRandom uniformRandom = new UniformRandom();
                
                Double pf = 0.475; //This parameter is a numerical value in the interval (0, 1) 
                           //determining the chance that individuals either outside the parameter boundaries
                           //or violating the constraints are compared during the selection. The default is '0.475'.
            
		// Initialize new population
		Population newPopulation = new Population(this.populationSize);

                boolean wasSwapped = false;
                
		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
		    
                     Individual individual = population.getFittest(populationIndex);
                    
                    
                    // within bounds or random chance 
                    if( (phi.phi(populationIndex) == 0 && phi.phi(populationIndex+1) == 0) || uniformRandom.uniform(0, 1) < pf ){
                    
                        
        //Aqui va la comparación de Value de geneticSR
                        
                   

			// Loop over individual's genes
			for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
				// Skip mutation if this is an elite individual
				if (populationIndex > this.elitismCount) {
					// Does this gene need mutation?
					if (this.mutationRate > Math.random()) {
						// Get new gene
						int newGene = 1;
						if (individual.getGene(geneIndex) == 1) {
							newGene = 0;
						}
						// Mutate gene
						individual.setGene(geneIndex, newGene);
					}
				}
			}

                    }
			// Add individual to population
			newPopulation.setIndividual(populationIndex, individual);
		}

		// Return mutated population
		return newPopulation;
	}
        
    
    
    
}
