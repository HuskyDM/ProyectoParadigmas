/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jay
 */
public class Terminal {
    
    
    public String naming(){
        String filename;
        System.out.println("Introduzca el nombre del archivo");
        System.out.println("----No añada extension----");
        Scanner scan = new Scanner(System.in);
        filename=scan.nextLine();
        return filename;
    }
    
    public double crossover(){
        double cross;
        System.out.println("Introduzca el crossover rate");
        System.out.println("Usa la funcion floor((crossover-rate*population-size)/100/2)");
        Scanner scan = new Scanner(System.in);
        cross=scan.nextDouble();
        return cross;
    }
    
    public void setUp(){
        List<Solucion> iniciales = new ArrayList<Solucion>();
       Solucion sol1 = new Solucion(0, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol2 = new Solucion(1, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol3 = new Solucion(2, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol4 = new Solucion(3, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol5 = new Solucion(4, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       
       iniciales.add(sol1);
       iniciales.add(sol2);
       iniciales.add(sol3);
       iniciales.add(sol4);
       iniciales.add(sol5);
             
        String name = naming();
        double cross = crossover();
    
        ThreadCall tru = new ThreadCall(name, cross,iniciales);
        tru.runProject();
    }
    
}
