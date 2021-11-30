import java.io.IOException;

    /**
     *@author: Noemi Guadalupe Hernandez Palacios (NoemiGP) 
     *@author: Marco Antonio Santiago Perez (KLM0Marck)
     *@author: Jorge Luis Tovar Rebollo (JorgeLTR)
     *@about: This code in java is about the functions we use in the simplex method which 
     is our final project of the Operations Research subject
     **/
public class Functions {
    public static void clear(){
    try {
    if (System.getProperty("os.name").contains("Windows"))
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    else
    Runtime.getRuntime().exec("clear");
    } catch (IOException | InterruptedException ex) {}
    }
    
    
    public static void pause(){
    try{
        Thread.sleep(5000);
    }catch(InterruptedException e ){
        System.out.println("Interrupted Pause");
    }
}
}

