/**
 * This class it's initial to Simplex
 */
public Main{

    /**
     *@author:- Noemi Guadalupe Hernandez Palacios (NoemiGP) 
     *@author: Marco Antonio Santiago Perez 
     *@author: Jorge Luis Tovar Rebollo (JorgeLTR)
     *@about: This code in java is about the simplex method which 
     is our final project of the Operations Research subject
     */
public class TablaSimplex {
    //Number of variables 
    private int NumVariables;
    //Number of Restrictions
    private int NumRestricciones;
    //Table width
    private int ancho;
    //Table that will have changes
    private double tabla[][];
    //Table Results
    protected double resultado[];
    

    /*
     * Table Class Builder
     */
    public Tabla(Problema problema) {
        calcularM(problema);
        NumRestricciones = problema.restricciones.size();
        casoIgual = new int[NumRestricciones];
        NumVariables = maximoNumSubindices(problema);
        ancho = NumVariables +variablesAdicionales(problema) + 1;
        tabla = new double[NumRestricciones][ancho];
        //Table value at startup: "+NumRestricciones +",ancho: "+ancho);
        int k = NumVariables;
        for (int i = 0; i < NumRestricciones; i++) {
            //Incorporate subscripts
            for (int ii = 0; ii < NumVariables; ii++) {
                try {
                    tabla[i][ii] = problema.restricciones.get(i).subindices[ii];
                } catch (Exception ex) {
                    tabla[i][ii] = 0;
                }
            }
    }
}