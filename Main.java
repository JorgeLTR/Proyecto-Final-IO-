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
public class Problema_Simplex {
   TablaSimplex proceso;
   boolean  accionMax; //maximize = true, minimize = false
   ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();

   public Problema_Simplex(boolean max){
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
    public Tabla(Problema_Simplex problema_sim) {
        calcularM(problema_sim);
        Num_Rest = problema_sim.restricciones.size();
        casoIgual = new int[Num_Rest];
        Num_Var = maximoNumSubindices(problema_sim);
        Ancho_tab = Num_Var +variablesAdicionales(problema_sim) + 1;
        tabla = new double[Num_Rest][Ancho_tab];
        //Table value at startup: "+NumRestricciones +",ancho: "+ancho);
        int k = Num_Var;
        for (int i = 0; i < Num_Rest; i++) {
            //Incorporate subscripts
            for (int ii = 0; ii < Num_Var; ii++) {
                try {
                    tabla[i][ii] = problema_sim.restricciones.get(i).subindices[ii];
                } catch (Exception ex) {
                    tabla[i][ii] = 0;
                }
            }
            //Incorporate slack variables
            if (problema_sim.restricciones.get(i).valorZ != 1)/*Except in the objective function*/ {
                if (variablePorSigno(problema_sim.restricciones.get(i).desigualdad) == 1)/*In case of <= o =*/ {
                    tabla[i][k] = 1;
                    if (problema_sim.restricciones.get(i).desigualdad == 0)/*In the same way, M is also added*/ {
                        tabla[0][k] = M;
                        casoIgual[i] = 1;
                    }
                    k += 1;
                }
                if (variablePorSigno(problema_sim.restricciones.get(i).desigualdad) == 2)/*In case of >=*/{
                    tabla[i][k] = -1;
                    tabla[i][k + 1] = 1;
                    k += 2;
                }
            }
            //Incorporate result
            tabla[i][Ancho_tab - 2] = problema_sim.restricciones.get(i).solucion;
            //Incorporate Z
            tabla[i][Ancho_tab - 1] = problema_sim.restricciones.get(i).valorZ;
        }
    }

    /*
     *The Negative_Values verifies that there were no negative values ​​in the objective function
     */
    private boolean Valores_Negativos() {
        boolean j = true;
        for (int i = 0; i < tabla[0].length; i++) {
            if (tabla[0][i] < 0) {
                j = false;
                break;
            }
        }
        return j;
    }

    /*
     *This is to calculate the pivot column from the smallest value
     *Then the variables will be taken into account
     */
    private int Columna_Pivote() {
        int Result = 0;
        double Val_Menor = tabla[0][Result];
        for (int i = 0; i < tabla[0].length - 2; i++) {
            //if there is a value less than the previous one and it is less than zero we will take it into account as the pivot column
            if (tabla[0][i] <= Val_Menor && tabla[0][i] < 0) {
                Val_Menor = tabla[0][i];
                Result = i;
            }
        }
        return Result;
    }

    /*
     * We calculate the pivot row
     */
    private int Fila_Pivote(int PivoteColumn) {
        double pivote[] = new double[Num_Rest - 1];
        int lista[] = new int[Num_Rest - 1];
        int val = 0;
        for (int i = 0; i < Num_Rest - 1; i++) {
            if (tabla[i + 1][PivoteColumn] > 0 && tabla[i + 1][tabla[0].length - 2] != 0) 
            {
                lista[val] = i + 1;
                pivote[val] = Math.abs(tabla[i + 1][tabla[0].length - 2] / tabla[i + 1][PivoteColumn]);
                msm(" " + Double.toString(tabla[i + 1][tabla[0].length - 2]) + " / " 
                + Double.toString(tabla[i + 1][PivoteColumn]) + " = " 
                + Double.toString(pivote[val]));
                val += 1;
            }
        }
        double Val_Menor = pivote[0];
        int PivoteFil = 1;
        for (int i = 0; i < val; i++) {
            if (pivote[i] <= Val_Menor && pivote[i] != 0) {
                Val_Menor = pivote[i];
                PivoteFil = lista[i];
            }
        }
        System.out.println("Fila donde se encuentra el Pivote = " + PivoteFil);
        return PivoteFil;

        }

    /*
     * This is to simplify the pivot row so that the pivot can be made 1
     */
    private void SimpliFilPivote(int PivoteFil, double PivoteNum) {
        for (int i = 0; i < tabla[0].length; i++) {
            tabla[PivoteFil][i] = tabla[PivoteFil][i] / PivoteNum;
        }
    }

    /*
     * This is to simplify the rest of the rows
     */
    private void SimpliTFilPivote(int PivoteFil, int PivoteColumn) {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i][PivoteColumn] != 0 && i != PivoteFil) {
                if (tabla[i][PivoteColumn] > 0) {
                    //When it's Positive
                    double valor = tabla[i][PivoteColumn] * -1;
                    for (int j = 0; j < tabla[0].length; j++) {
                        tabla[i][j] = tabla[PivoteFil][j] * valor + tabla[i][j];
                    }
                } else {
                    //When it's Negative
                    double valor = tabla[i][PivoteColumn] * -1;
                    for (int j = 0; j < tabla[0].length; j++) {
                        tabla[i][j] = tabla[PivoteFil][j] * valor + tabla[i][j];
                    }
                }
            }
        }
    }

    /*
     * Results in Table
     */
    private void ResultadosTabla() {
        System.out.println("Numero de Variables = " + Num_Var);
        result = new double[Num_Var];
        int k=0;
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < Num_Var; j++) {
                if (tabla[i][j] == 1) {
                    result[j] = tabla[i][tabla[0].length - 2];
                    k=j;
                }
            }
        }
        z=tabla[0][tabla[0].length - 2];
        System.out.println("");
        for (int i = 0; i < result.length; i++) {
            System.out.println(" x" + (i + 1) + " = " + result[i]);
        }
    }



    }
}

