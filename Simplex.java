import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

    /**
     *@author: Noemi Guadalupe Hernandez Palacios (NoemiGP) 
     *@author: Marco Antonio Santiago Perez (KLM0Marck)
     *@author: Jorge Luis Tovar Rebollo (JorgeLTR)
     *@about: This code in java is about the simplex method which 
     is our final project of the Operations Research subject
     **/
public class Simplex {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException{
        BufferedReader scanner = new BufferedReader (new InputStreamReader(System.in));
       int opc=0,back=0,row=0,column=0,i,j,k;
       int model[][];
        do{
        System.out.println("Menu\n1-Resolution Problem\n2-User's Guide\n3-Details\n4-Exit\nOption: ");
        opc=Integer.parseInt(scanner.readLine());
        switch(opc){
            case 1:
                break;
            case 2:
               
               Functions.clear();
               Guide.instructions();
               Functions.pause();
               opc=5;
                break;
            case 3:
               Functions.clear();
               Details.members();
               Functions.pause();
               Functions.clear();
               opc=5;
                break;
            case 4:
                System.out.println("Closing Program....");
                break;
            default:
                
                break;
        }
        }while(opc>4);
    }
}

