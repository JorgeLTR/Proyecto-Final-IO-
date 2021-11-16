/**
 * This class it's initial to Simplex
 */
import java.awt.List;
import java.util.ArrayList;
public Proyecto_Simplex{
    /**
     *@author: Noemi Guadalupe Hernandez Palacios (NoemiGP) 
     *@author: Marco Antonio Santiago Perez (KLM0Marck)
     *@author: Jorge Luis Tovar Rebollo (JorgeLTR)
     *@about: This code in java is about the simplex method which 
     is our final project of the Operations Research subject
     */
public class Problema {
   TablaSimplex proceso;
   boolean  accionMax; //maximize = true, minimize = false
   ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();

   public Problema(boolean max){
       accionMax=max;
   }
}

public class TablaSimplex {
    //Number of variables 
    private int Num_Var;
    //Number of Restrictions
    private int Num_Rest;
    //Table width
    private int Ancho_tab;
    //Table that will have changes
    private double tabla[][];
    //Table Results
    protected double Resul[];

    /*
     * Table Class Builder
     */
    public Tabla(Problema problema) {
        calcularM(problema);
        Num_Rest = problema.restricciones.size();
        casoIgual = new int[Num_Rest];
        Num_Var = maximoNumSubindices(problema);
        Ancho_tab = Num_Var +variablesAdicionales(problema) + 1;
        tabla = new double[Num_Rest][Ancho_tab];
        //Table value at startup: "+NumRestricciones +",ancho: "+ancho);
        int k = Num_Var;
        for (int i = 0; i < Num_Rest; i++) {
            //Incorporate subscripts
            for (int ii = 0; ii < Num_Var; ii++) {
                try {
                    tabla[i][ii] = problema.restricciones.get(i).subindices[ii];
                } catch (Exception ex) {
                    tabla[i][ii] = 0;
                }
            }
            //Incorporate slack variables
            if (problema.restricciones.get(i).valorZ != 1)/*Except in the objective function*/ {
                if (variablePorSigno(problema.restricciones.get(i).desigualdad) == 1)/*In case of <= o =*/ {
                    tabla[i][k] = 1;
                    if (problema.restricciones.get(i).desigualdad == 0)/*In the same way, M is also added*/ {
                        tabla[0][k] = M;
                        casoIgual[i] = 1;
                    }
                    k += 1;
                }
                if (variablePorSigno(problema.restricciones.get(i).desigualdad) == 2)/*In case of >=*/{
                    tabla[i][k] = -1;
                    tabla[i][k + 1] = 1;
                    k += 2;
                }
            }
            //Incorporate result
            tabla[i][Ancho_tab - 2] = problema.restricciones.get(i).solucion;
            //Incorporate Z
            tabla[i][Ancho_tab - 1] = problema.restricciones.get(i).valorZ;
        }
    }

    }
}

