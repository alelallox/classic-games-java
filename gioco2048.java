import java.util.Scanner;

public class gioco2048{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("puoi scegliere la direzione usando WASD");
        //array with only zeros 
        int[][] matrice = new int[4][4];
        //array of numbers that serves the placement of random numbers
        int[][] matriceNumeri = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        inizioGioco(matrice, matriceNumeri);
        System.out.println();
        stampaMatrice(matrice);
        //while loop that starts the game
        Boolean gioco = false;
        while(gioco==false){
            //first check whether with the previous move there was a victory or a defeat
            Boolean var1 = controllaSconfitta(matrice);
            if(var1==true){
                System.out.println("hai perso");
                gioco = true;
                break;
            }
            Boolean var2 = controllaVittoria(matrice);
            if(var2==true){
                System.out.println("hai vinto");
                gioco = true;
                break;
            }
            System.out.print("scegli la direzione: ");
            String direzione = scanner.nextLine();
            if(direzione.equals("w")){
                spostaSopra(matrice);
            }else if(direzione.equals("s")){
                spostaSotto(matrice);
            }else if(direzione.equals("a")){
                spostaSinistra(matrice);
            }else if(direzione.equals("d")){
                spostaDestra(matrice);
            }
            
            matrice = piazzaCasella(matrice, matriceNumeri);
            stampaMatrice(matrice);
        }
    }
    //method that places the first two numbers in a random position of the game grid
    public static void inizioGioco(int[][] matrice, int[][] matriceNumeri){
        Boolean trovato1 = false;
        while(trovato1 == false){
            int posizione1 = (int)(Math.random()*16)+1;
            for(int i=0; i<matrice.length; i++){
                for(int j=0; j<matrice.length; j++){
                    if(matrice[i][j]==0 && matriceNumeri[i][j]==posizione1){
                        matrice[i][j] = 2; 
                        trovato1=true;
                    }
                }
            }         
        }
        Boolean trovato2 = false;
        while(trovato2 == false){
            int posizione2 = (int)(Math.random()*16)+1;
            for(int i=0; i<matrice.length; i++){
                for(int j=0; j<matrice.length; j++){
                    if(matrice[i][j]==0 && matriceNumeri[i][j]==posizione2){
                        matrice[i][j] = 2; 
                        trovato2=true;
                    }
                }
            }         
        }
        
    }

    //method that prints the matrix
    public static void stampaMatrice(int[][] matrice){
        for(int i = 0; i < matrice.length; i++) {
            for(int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }  
    }

    //method that places a number (2 or 4) in an unoccupied random box
    public static int[][] piazzaCasella(int[][] matrice, int[][] matriceNumeri){
        Boolean trovato3 = false;
        while(trovato3 == false){
            int posizione3 = (int)(Math.random()*16)+1;
            for(int i=0; i<matrice.length; i++){
                for(int j=0; j<matrice.length; j++){
                    if(matrice[i][j]==0 && matriceNumeri[i][j]==posizione3){
                        int caso = (int)(Math.random()*10)+1;
                        if(caso % 2 == 0){
                            matrice[i][j] = 2;
                        }else{
                            matrice[i][j] = 4;
                        } 
                        trovato3=true;
                    }
                }
            }         
        }
        return matrice;  
    }
    
    //method that moves all numbers to the left and adds the same numbers 
    public static int[][] spostaSinistra(int[][] matrice){
        for(int i=0;i<matrice.length;i++){
            for(int j=0;j<matrice.length;j++){
                if(matrice[i][j]!=0){
                    for(int k = j + 1; k < matrice.length; k++) {
                        if(matrice[i][k] != 0) {
                            if(matrice[i][j] == matrice[i][k]) {
                                matrice[i][j] += matrice[i][k];
                                matrice[i][k] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            int[] riga = new int[matrice.length];
            int index = 0;
            for(int j = 0; j < matrice.length; j++) {
                if(matrice[i][j] != 0) {
                    riga[index++] = matrice[i][j];
                }
            }
            
            for(int j = 0; j < matrice.length; j++) {
                matrice[i][j] = riga[j];
            }
        }
        return matrice;
        
    }

    //method that moves all numbers to the right and adds the same numbers 
    public static int[][] spostaDestra(int[][] matrice){
        for(int i = 0; i < matrice.length; i++) {
            for(int j = matrice.length - 1; j > 0; j--) {
                if(matrice[i][j] != 0) {
                    for(int k = j - 1; k >= 0; k--) {
                        if(matrice[i][k] != 0) {
                            if(matrice[i][j] == matrice[i][k]) {
                                matrice[i][j] *= 2;
                                matrice[i][k] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            
            int[] riga = new int[matrice.length];
            int index = matrice.length - 1;
            for(int j = matrice.length - 1; j >= 0; j--) {
                if(matrice[i][j] != 0) {
                    riga[index--] = matrice[i][j];
                }
            }
            
            for(int j = 0; j < matrice.length; j++) {
                matrice[i][j] = riga[j];
            }
        }
        return matrice;
    }
   
    //method that moves all numbers up and adds the same numbers 
    public static int[][] spostaSopra(int[][] matrice) {
        for(int j = 0; j < matrice.length; j++) {
            for(int i = 0; i < matrice.length - 1; i++) {
                if(matrice[i][j] != 0) {
                    for(int k = i + 1; k < matrice.length; k++) {
                        if(matrice[k][j] != 0) {
                            if(matrice[i][j] == matrice[k][j]) {
                                matrice[i][j] *= 2;
                                matrice[k][j] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            
            int[] colonna = new int[matrice.length];
            int index = 0;
            for(int i = 0; i < matrice.length; i++) {
                if(matrice[i][j] != 0) {
                    colonna[index++] = matrice[i][j];
                }
            }
            
            for(int i = 0; i < matrice.length; i++) {
                matrice[i][j] = colonna[i];
            }
        }
        return matrice;
    }

    //method that moves all numbers down and adds the same numbers 
    public static int[][] spostaSotto(int[][] matrice) {
        for(int j = 0; j < matrice.length; j++) {
            for(int i = matrice.length - 1; i > 0; i--) {
                if(matrice[i][j] != 0) {
                    for(int k = i - 1; k >= 0; k--) {
                        if(matrice[k][j] != 0) {
                            if(matrice[i][j] == matrice[k][j]) {
                                matrice[i][j] *= 2;
                                matrice[k][j] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
    
            int[] colonna = new int[matrice.length];
            int index = 0;
            for(int i = matrice.length - 1; i >= 0; i--) {
                if(matrice[i][j] != 0) {
                    colonna[index++] = matrice[i][j];
                }
            }
    
            for(int i = 0; i < matrice.length; i++) {
                matrice[i][j] = colonna[matrice.length - 1 - i];
            }
        }
        return matrice;
    }
 
    //method that checks if there is a win (if you get to 2048)
    public static Boolean controllaVittoria(int[][] matrice){
        Boolean bool = false;
        for(int i=0;i<matrice.length;i++){
            for(int j=0;j<matrice.length;j++){
                if(matrice[i][j]==2048){
                    bool = true;
                }
            }
        }
        return bool;
    }
  
    //method that checks if there is a defeat (if all the boxes are occupied and you can no longer make movements)
    public static Boolean controllaSconfitta(int[][] matrice){
        // Controlla se ci sono ancora celle vuote nella matrice
        for(int i=0; i<matrice.length; i++){
            for(int j=0; j<matrice[i].length; j++){
                if(matrice[i][j] == 0){
                    return false;
                }
            }
        }
        // Se non ci sono più celle vuote, controlla se ci sono ancora mosse valide
        // In questo esempio, controlliamo solo le celle adiacenti per le mosse valide
        for(int i=0; i<matrice.length; i++){
            for(int j=0; j<matrice[i].length; j++){
                if(i > 0 && matrice[i-1][j] == matrice[i][j]){
                    return false;
                }
                if(i < matrice.length-1 && matrice[i+1][j] == matrice[i][j]){
                    return false;
                }
                if(j > 0 && matrice[i][j-1] == matrice[i][j]){
                    return false;
                }
                if(j < matrice[i].length-1 && matrice[i][j+1] == matrice[i][j]){
                    return false;
                }
            }
        }
        // Se non ci sono più mosse valide, il gioco è finito
        return true;
    }
        

}