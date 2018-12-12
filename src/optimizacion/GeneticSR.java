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
    //Los parametros siguen el mismo orden de aparicion que en la simulacion
    //de netlogo
    //El orden es:
    //"cell-density" "initial-infected-cell-percentage" "viral-reach" "infection-rate" "mNeptune-effectiveness" 
    //"initial-probability-of-death" "initial-probability-of-chromatin-condensation" "marker-detection-threashold"

    double[] arrayMin = {1.0, 0.00, 1.0, 5.0, 75.0, 0.50, 0.50, 0.5};
    double[] arrayMax = {5.0, 5.00, 2.5, 15.0, 200.0, 1.50, 2.50, 0.75};

    public GeneticSR(double crossoverRate, List<Solucion> poblacion, double mutationRate) {
        this.crossoverRate = crossoverRate;
        this.poblacion = poblacion;
        this.mutationRate = mutationRate;
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
    public void mutate(Solucion individuo) {
        r = new Random();
        int param = r.nextInt(8) + 1;

        //Se llama a mutate dependiendo del parametro.
        //Se remplaza el valor del parametro en orden
        switch (param) {
            case 1:
                individuo.setValue("cell-density", mutateParameter(1, individuo.getValue("cell-density")));
                break;
            case 2:
                individuo.setValue("initial-infected-cell-percentage", mutateParameter(2, individuo.getValue("initial-infected-cell-percentage")));
                break;
            case 3:
                individuo.setValue("viral-reach", mutateParameter(3, individuo.getValue("viral-reach")));
                break;
            case 4:
                individuo.setValue("infection-rate", mutateParameter(4, individuo.getValue("infection-rate")));
                break;
            case 5:
                individuo.setValue("mNeptune-effectiveness", mutateParameter(5, individuo.getValue("mNeptune-effectiveness")));
                break;
            case 6:
                individuo.setValue("initial-probability-of-death", mutateParameter(6, individuo.getValue("initial-probability-of-death")));
                break;
            case 7:
                individuo.setValue("initial-probability-of-chromatin-condensation", mutateParameter(7, individuo.getValue("initial-probability-of-chromatin-condensation")));
                break;
            case 8:
                individuo.setValue("marker-detection-threashold", mutateParameter(8, individuo.getValue("marker-detection-threashold")));
                break;
        }

    }

    ;
   
    /*
        Metodo mutateParameter
        Recibe el id del parametro a cambiar y el parametro como tal.
        Se genera un numero aleatorio con distribucion normal, de media 0 y desviacion estandar
        igual a un 10% del parametro.
        Luego se revisa hasta unas 10 veces (segun Runnarson) si el parametro esta dentro del margen.
        Si el parametro esta dentro del margen se lleva a Break del for.
        Se retorna el parametro generado.
    */
    public double mutateParameter(int paramId, double parametro) {
        double nuevoParametro = parametro;
        boolean dentroMargen = false;

        for (int i = 0; i < 10; ++i) {
            this.r = new Random();
            double gaussian = r.nextGaussian() * (0.10 * nuevoParametro);
            nuevoParametro = gaussian;

            switch (paramId) {
                case 1:
                    if (nuevoParametro >= arrayMin[1] && nuevoParametro <= arrayMax[1]) {
                        dentroMargen = true;
                    }
                    break;
                case 2:
                    if (nuevoParametro >= arrayMin[2] && nuevoParametro <= arrayMax[2]) {
                        dentroMargen = true;
                    }
                    break;
                case 3:
                    if (nuevoParametro >= arrayMin[3] && nuevoParametro <= arrayMax[3]) {
                        dentroMargen = true;
                    }
                    break;
                case 4:
                    if (nuevoParametro >= arrayMin[4] && nuevoParametro <= arrayMax[4]) {
                        dentroMargen = true;
                    }
                    break;
                case 5:
                    if (nuevoParametro >= arrayMin[5] && nuevoParametro <= arrayMax[5]) {
                        dentroMargen = true;
                    }
                    break;
                case 6:
                    if (nuevoParametro >= arrayMin[6] && nuevoParametro <= arrayMax[6]) {
                        dentroMargen = true;
                    }
                    break;
                case 7:
                    if (nuevoParametro >= arrayMin[7] && nuevoParametro <= arrayMax[7]) {
                        dentroMargen = true;
                    }
                    break;
                case 8:
                    if (nuevoParametro >= arrayMin[8] && nuevoParametro <= arrayMax[8]) {
                        dentroMargen = true;
                    }
                    break;
            }
            if (dentroMargen == true) {
                break;
            }
        }
        return nuevoParametro;
    }

    //Revisa que cada parametro este dentro del margen de netlogo
    //Principalmente revisa que el parametro no baje de 0
    //Si se sale del margen se le asigna un numero aleatorio dentro del margen
    //Dado por arrayMin y arrayMax
    public void check(Solucion individuo){
    
        //Random r = new Random();
        //double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        
        if(individuo.getValue("cell-density")<0.01 || individuo.getValue("cell-density")>20.00){
            r = new Random();
            double nuevoValor = arrayMin[0]+(arrayMax[0]-arrayMin[0])*r.nextDouble();
            individuo.setValue("cell-density", nuevoValor);
        }
        if(individuo.getValue("initial-infected-cell-percentage")<0.00 || individuo.getValue("initial-infected-cell-percentage")>100.00){
            r = new Random();
            double nuevoValor = arrayMin[1]+(arrayMax[1]-arrayMin[1])*r.nextDouble();
            individuo.setValue("initial-infected-cell-percentage", nuevoValor);
        }
        if(individuo.getValue("viral-reach")<1.00 || individuo.getValue("viral-reach")>3.00){
            r = new Random();
            double nuevoValor = arrayMin[2]+(arrayMax[2]-arrayMin[2])*r.nextDouble();
            individuo.setValue("viral-reach", nuevoValor);
        }
        if(individuo.getValue("infection-rate")<1.00 || individuo.getValue("infection-rate")>15.00){
            r = new Random();
            double nuevoValor = arrayMin[3]+(arrayMax[3]-arrayMin[3])*r.nextDouble();
            individuo.setValue("infection-rate", nuevoValor);
        }
        if(individuo.getValue("mNeptune-effectiveness")<0.01 || individuo.getValue("mNeptune-effectiveness")>400.00){
            r = new Random();
            double nuevoValor = arrayMin[4]+(arrayMax[4]-arrayMin[4])*r.nextDouble();
            individuo.setValue("mNeptune-effectiveness", nuevoValor);
        }
        if(individuo.getValue("initial-probability-of-death")<0.01 || individuo.getValue("initial-probability-of-death")>0.200){
            r = new Random();
            double nuevoValor = arrayMin[5]+(arrayMax[5]-arrayMin[5])*r.nextDouble();
            individuo.setValue("initial-probability-of-death", nuevoValor);
        }
        if(individuo.getValue("initial-probability-of-chromatin-condensation")<0.01 || individuo.getValue("initial-probability-of-chromatin-condensation")>2.500){
            r = new Random();
            double nuevoValor = arrayMin[6]+(arrayMax[6]-arrayMin[6])*r.nextDouble();
            individuo.setValue("initial-probability-of-chromatin-condensation", nuevoValor);
        }
        if(individuo.getValue("marker-detection-threashold")<0.01 || individuo.getValue("marker-detection-threashold")>1.00){
            r = new Random();
            double nuevoValor = arrayMin[7]+(arrayMax[7]-arrayMin[7])*r.nextDouble();
            individuo.setValue("marker-detection-threashold", nuevoValor);
        }       
    }
    
    /*
    Metodo crossover genera primero la cantidad de parejas a producir usando el crossoverRate
    dado por la terminal y la funcion floor((crossoverRate*populationSize)/100/2)
     */
    public int crossover() {

        double crossoverCount;
        double multi = this.crossoverRate * this.poblacion.size();
        double div = (multi / 5) / 2;
        crossoverCount = Math.floor(div);

        return (int) crossoverCount;
    }

    /*
    Metodo phi es llamado durante el swap
    Revisa cada parametro y genera un resultado que usa la siguiente funcion
    (paramMin - param)^2 + (paramMax - param)^2
    
     */
    private double phi(Solucion individuo) {

        double resultado = 0;

        if ((individuo.getValue("cell-density") < arrayMin[0]) || (individuo.getValue("cell-density") > arrayMax[0])) {
            double total = Math.pow((arrayMin[0] - individuo.getValue("cell-density")), 2) + Math.pow((individuo.getValue("cell-density"))-arrayMax[0], 2);
            resultado += total;
        }
        if ((individuo.getValue("initial-infected-cell-percentage") < arrayMin[1]) || (individuo.getValue("initial-infected-cell-percentage") > arrayMax[1])) {
            double total = Math.pow((arrayMin[1] - individuo.getValue("initial-infected-cell-percentage")), 2) + Math.pow((individuo.getValue("initial-infected-cell-percentage"))-arrayMax[1], 2);
            resultado += total;
        }
        if ((individuo.getValue("viral-reach") < arrayMin[2]) || (individuo.getValue("viral-reach") > arrayMax[2])) {
            double total = Math.pow((arrayMin[2] - individuo.getValue("viral-reach")), 2) + Math.pow((individuo.getValue("viral-reach"))-arrayMax[2], 2);
            resultado += total;
        }
        if ((individuo.getValue("infection-rate") < arrayMin[3]) || (individuo.getValue("infection-rate") > arrayMax[3])) {
            double total = Math.pow((arrayMin[3] - individuo.getValue("infection-rate")), 2) + Math.pow((individuo.getValue("infection-rate"))-arrayMax[3], 2);
            resultado += total;
        }
        if ((individuo.getValue("mNeptune-effectiveness") < arrayMin[4]) || (individuo.getValue("mNeptune-effectiveness") > arrayMax[4])) {
            double total = Math.pow((arrayMin[4] - individuo.getValue("mNeptune-effectiveness")), 2) + Math.pow((individuo.getValue("mNeptune-effectiveness"))-arrayMax[4], 2);
            resultado += total;
        }
        if ((individuo.getValue("initial-probability-of-death") < arrayMin[5]) || (individuo.getValue("initial-probability-of-death") > arrayMax[5])) {
            double total = Math.pow((arrayMin[5] - individuo.getValue("initial-probability-of-death")), 2) + Math.pow((individuo.getValue("initial-probability-of-death"))-arrayMax[5], 2);
            resultado += total;
        }
        if ((individuo.getValue("initial-probability-of-chromatin-condensation") < arrayMin[6]) || (individuo.getValue("initial-probability-of-chromatin-condensation") > arrayMax[6])) {
            double total = Math.pow((arrayMin[6] - individuo.getValue("initial-probability-of-chromatin-condensation")), 2) + Math.pow((individuo.getValue("initial-probability-of-chromatin-condensation"))-arrayMax[6], 2);
            resultado += total;
        }
        if ((individuo.getValue("marker-detection-threashold") < arrayMin[7]) || (individuo.getValue("marker-detection-threashold") > arrayMax[7])) {
            double total = Math.pow((arrayMin[7] - individuo.getValue("marker-detection-threashold")), 2) + Math.pow((individuo.getValue("marker-detection-threashold"))-arrayMax[7], 2);
            resultado += total;
        }
        
        return resultado;
    }

    public void swap() {

        //Parametro indicado por el algoritmo
        Double pf = 0.475;

        //Cantidad minima de veces que se debe revisar la poblacion es el tamaño de la poblacion
        //Si en una pasada entera del segundo for no hubo cambio entra Break
        //this.poblacion.size-1 porque existe la posibilidad de llegar al poblador 99 y causar
        //un nullpointerexception
        for (int populationIndex = 0; populationIndex < this.poblacion.size() - 1; populationIndex++) {

            //boolean wasSwapped es false por defecto
            //Se vuelve true si hubo un swap dentro del segundo for
            //Si se mantiene false por una pasada entera del segundo for
            //causa un break
            boolean wasSwapped = false;

            for (int j = 0; j < this.poblacion.size() - 1; ++j) {

                //phi(arrayParamNuevo, arrayMin, arrayMax );            
                //se hace phi del parametro actual y el parametro siguiente. 
                //Se revisa Value (en nuestro caso fitness) si ambos phis son 0 o si el UniformRandom retorna un valor
                //menor pf (0.475)
                if ((phi(this.poblacion.get(j)) == 0 && phi(this.poblacion.get(j + 1)) == 0) || UniformRandom.uniform(0, 1) < pf) {
                    //Comparación de Value de geneticSR                
                    //if (Value(j) > Value(j + 1))
                    //Si el fitness del poblador j es mejor que el del poblador j+1 se cambian
                    //Esto acerca a los pobladores de mejor fitness hacia la derecha.

                    if (this.poblacion.get(j).getFitness() > this.poblacion.get(j + 1).getFitness()) {
                        //swap(populationIndex, populationIndex + 1);
                        Collections.swap(this.poblacion, j, j + 1);
                        wasSwapped = true;
                    }
                } else //phi!=0 o el UniformRandom retorno un valor
                {
                    if (phi(this.poblacion.get(j)) > phi(this.poblacion.get(j + 1))) {
                        //swap(populationIndex, populationIndex + 1);
                        Collections.swap(this.poblacion, j, j + 1);
                        wasSwapped = true;
                    }
                }
            }
            //Si no ocurre ningun swap en una corrida del segundo for se detiene
            if (wasSwapped == false) {
                break;
            }
        }
    }

    public Solucion cruceSexual(Solucion padre, Solucion madre, int id) {
        //Por el algoritmo se seleccionan un numero aleatorio de parametros entre 0
        //y la mitad de los parametros.
        //Para los parametros no seleccionados se escoge generan al azar un nuevo
        //parametro dentro de los margenes dados
        //Si obtiene 0 del generador entonces se genera un hijo sin caracteristicas de los
        //padres
        double cell = 0.0, initial = 0.0, viral = 0.0, infection = 0.0, mNeptune = 0.0, probDead = 0.0, probCond = 0.0, marker = 0.0;

        r = new Random();
        int cantidad = r.nextInt(4) + 0;

        if (cantidad != 0) {
            for (int i = 0; i < cantidad; ++i) {
                r = new Random();
                int aCambiar = r.nextInt(8) + 1;

                switch (aCambiar) {
                    case 1:
                        cell = (padre.getValue("cell-density") + madre.getValue("cell-density")) / 2;
                        break;
                    case 2:
                        initial = (padre.getValue("initial-infected-cell-percentage") + madre.getValue("initial-infected-cell-percentage")) / 2;
                        break;
                    case 3:
                        viral = (padre.getValue("viral-reach") + madre.getValue("viral-reach")) / 2;
                        break;
                    case 4:
                        infection = (padre.getValue("infection-rate") + madre.getValue("infection-rate")) / 2;
                        break;
                    case 5:
                        mNeptune = (padre.getValue("mNeptune-effectiveness") + madre.getValue("mNeptune-effectiveness")) / 2;
                        break;
                    case 6:
                        probDead = (padre.getValue("initial-probability-of-death") + madre.getValue("initial-probability-of-death")) / 2;
                        break;
                    case 7:
                        probCond = (padre.getValue("initial-probability-of-chromatin-condensation") + madre.getValue("initial-probability-of-chromatin-condensation")) / 2;
                        break;
                    case 8:
                        marker = (padre.getValue("marker-detection-threashold") + madre.getValue("marker-detection-threashold")) / 2;
                        break;
                }

            }
        }
        if (cell == 0.0) {
            //rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            r = new Random();
            cell = arrayMin[0] + (arrayMax[0] - arrayMin[0]) * r.nextDouble();
        }
        if (initial == 0.0) {
            r = new Random();
            initial = arrayMin[1] + (arrayMax[1] - arrayMin[1]) * r.nextDouble();
        }
        if (viral == 0.0) {
            r = new Random();
            viral = arrayMin[2] + (arrayMax[2] - arrayMin[2]) * r.nextDouble();
        }
        if (infection == 0.0) {
            r = new Random();
            infection = arrayMin[3] + (arrayMax[3] - arrayMin[3]) * r.nextDouble();
        }
        if (mNeptune == 0.0) {
            r = new Random();
            mNeptune = arrayMin[4] + (arrayMax[4] - arrayMin[4]) * r.nextDouble();
        }
        if (probDead == 0.0) {
            r = new Random();
            probDead = arrayMin[5] + (arrayMax[5] - arrayMin[5]) * r.nextDouble();
        }
        if (probCond == 0.0) {
            r = new Random();
            probCond = arrayMin[6] + (arrayMax[6] - arrayMin[6]) * r.nextDouble();
        }
        if (marker == 0.0) {
            r = new Random();
            marker = arrayMin[7] + (arrayMax[7] - arrayMin[7]) * r.nextDouble();
        }
        Solucion hijo = new Solucion(id, cell, initial, viral, infection, mNeptune, probDead, probCond, marker);
        return hijo;
    }

    public List<Solucion> createPopulationSR() {

        System.out.println("Creando nueva gen");
        
        //Parametro por defecto del algoritmo                   
        //Population newPopulation = new Population(population.size());
        //Parámetros  
        //Se obtiene int crossoverCount que contiene la cantidad de cruces a realizar
        int crossoverCount = crossover();
        List<Solucion> nuevaPoblacion = new ArrayList<Solucion>();
        int restantes = poblacion.size();
         Printer print = new Printer();
        print.printResults("beforeSwap", poblacion, 1);
        
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
       print.printResults("afterSwap", poblacion, 1);
        
        int id = 0;
        if(crossoverCount!=0){
        for (int i = 0; i < poblacion.size()-crossoverCount; i = i + 2) {
            System.out.println("Total de poblacion "+poblacion.size()+" Creando hijos :"+id+" y "+(id+1));
            System.out.println("Id del individuio i:"+i+" id:"+poblacion.get(i).getId());
            nuevaPoblacion.add(cruceSexual(poblacion.get(i), poblacion.get(i + 1), id));
            nuevaPoblacion.add(cruceSexual(poblacion.get(i), poblacion.get(i + 1), id + 1));
            id += 2;
            restantes -= 2;
            }
        }
        System.out.println("Tamaño de la poblacion post cruze "+nuevaPoblacion.size());
        
        //La poblacion restante es generada aleatoriamente de clones de la poblacion vieja
        System.out.println("Restantes son "+restantes);
        
        for (int i = restantes - 1; i < poblacion.size(); ++i) {
            r = new Random();
            int select = r.nextInt(poblacion.size()) + 0;
            Solucion viejo = poblacion.get(select);
            Solucion clon = new Solucion(id, viejo.getValue("cell-density"), viejo.getValue("initial-infected-cell-percentage"),
                     viejo.getValue("viral-reach"), viejo.getValue("infection-rate"), viejo.getValue("mNeptune-effectiveness"),
                     viejo.getValue("initial-probability-of-death"), viejo.getValue("initial-probability-of-chromatin-condensation"),
                     viejo.getValue("marker-detection-threashold"));
            nuevaPoblacion.add(clon);
            ++id;
        }
        
         System.out.println("Tamaño de la poblacion post clon "+nuevaPoblacion.size());
        //cada parametro pasa por una mutacion
        //El chance es un numero aleatorio entre 0 a 100, la posibilidad es dada
        //por el usuario
        for (int i = 0; i < nuevaPoblacion.size(); ++i) {
            r = new Random();
            double rate = r.nextDouble() * (100.0);
            if (rate <= mutationRate) {
                mutate(nuevaPoblacion.get(i));
            }
        }
        //Se verifica que ningun parametro de la solucion este fuera del margen
        
        for(int i =0;i<nuevaPoblacion.size();++i){
            check(nuevaPoblacion.get(i));
        }
        
        //Se retorna la nueva poblacion para hacer un nuevo ciclo
        return nuevaPoblacion;
    }
}