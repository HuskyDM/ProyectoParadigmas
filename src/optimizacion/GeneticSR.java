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
    
    public Population mutatePopulationSR(Population population, int crossoverRate) {
        Double pf = 0.475;  //This parameter is a numerical value in the interval (0, 1)
                            //determining the chance that individuals either outside the parameter boundaries
                            //or violating the constraints are compared during the selection. The default is '0.475'.             
                            
        Population newPopulation = new Population(population.size());
        boolean wasSwapped = false;

        //Parámetros  
        double[] arrayParamViejo = {14, 7, 19 };          
        double[] arrayParamNuevo = {15, 1, 22 };
          
        double[] arrayMin = {2, 2, 2}; 
        double[] arrayMax = {20, 20, 21}; 
        
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {    
            //Se agrega al gen el random
            double random = r.nextGaussian() * 0.1 + 0;
            
            phi(arrayParamNuevo, arrayMin, arrayMax );
            
            
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
        }
        
        /*if (wasSwapped == false) {
            return population;
        }*/
        
        int crossoverCount = r.nextInt(5); //Se generan numeros aleatorios entre 0 y 4
        double cantidadCruces = Math.floor(crossoverRate * population.size() / 100 / 2);
        int newPopulationIndex = 0;
        
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
                    case 0:
                        newPopulation.setIndividual(newPopulationIndex, /*Padre1*/ population.getIndividual(0));
                        break;
                    case 1:
                        newPopulation.setIndividual(newPopulationIndex, /*Padre2*/ population.getIndividual(1));
                        break;
                    case 2:
                        newPopulation.setIndividual(newPopulationIndex, /*Hijo1*/ i1);
                        break;
                    case 3:
                        newPopulation.setIndividual(newPopulationIndex, /*Hijo2*/ i2);
                        break;
                    case 4:
                        newPopulation.setIndividual(newPopulationIndex, /*Hijo3*/ i3);
                        break;
                    case 5:
                        newPopulation.setIndividual(newPopulationIndex, /*Hijo4*/ i4);
                        break;    
                }
                counter++;
                newPopulationIndex++;
            }
        }
        
        return newPopulation;
    }
    
    private double phi(double[] arrayParamNuevo, double[] arrayMin, double[] arrayMax) {
        int cantidadParams = 4;
                
        double resultado = 0;
        
        for (int i = 0; i < cantidadParams; i++ ) {
            if (arrayParamNuevo[i] < arrayMin[i]) {
                resultado += (arrayMin[i]-arrayParamNuevo[i])*(arrayMin[i]-arrayParamNuevo[i]);
            }
            
            if (arrayParamNuevo[i] > arrayMax[i]) {
                //(Parametro viejo - parametro nuevo)'2
                resultado += (arrayMax[i] - arrayParamNuevo[i]) * (arrayMin[i] - arrayParamNuevo[i]);   
            }   
        }
        return resultado;
    }
    
    private void swap(int valor1, int valor2) {}
}
