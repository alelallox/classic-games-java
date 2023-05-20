import java.util.Scanner;
import java.util.Arrays;

public class MasterMindParole{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        //array of word of 5 letters to guess
        String[] parole = {"amore","donna","bella","fiore","festa","cielo","buono","gioco","mondo","notte","padre","poeta","sogno","tempo","terra","pizza","tonno","torre"};
        //game grid
        String[][] griglia = new String[6][5];
        //array with computer choice word
        String[][] grigliaSegreta = new String[6][5];
        griglia = riempiMatrice(griglia);
        stampaMatriceStr(griglia);
        String[] parola = scegliParola(parole);
        
        grigliaSegreta = parolaInMatrice(parola, grigliaSegreta );
        
        //while loop that starts the game
        int riga = 0;
        Boolean gioco = false;
        while(riga <= 5 && gioco == false){
            System.out.println("inserisci una parola di 5 lettere: ");
            String parolaUtente = scanner.nextLine();
            String[] lettereUtente = parolaUtente.split("");
            for (int i = 0; i < griglia[riga].length; i++) {
                griglia[riga][i] = lettereUtente[i];
            }
            griglia = controlloParola(griglia, grigliaSegreta, riga);
            stampaMatriceStr(griglia);
            Boolean vittoria = controllaVittoria(griglia, grigliaSegreta, riga);
            if(vittoria){
                System.out.println("hai vinto");
                gioco = true;
                break;
            }
            riga +=1;
        }
        if(gioco==false){
            System.out.println("hai perso");
        }
    }

    //method that prints string array
    public static void stampaMatriceStr(String[][] matrice){
        for(int i = 0; i < matrice.length; i++) {
            for(int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    //method that fills the array of /
    public static String[][] riempiMatrice(String[][] matrice){
        for(int i = 0; i < matrice.length; i++) {
            for(int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = "/";
            }
        }
        return matrice;
    }

    //method that randomly chooses a word from the word array 
    public static String[] scegliParola(String[] parole){
        String parola = "";
        int caso = (int)(Math.random()*18)+1;
        for(int i=0; i<parole.length; i++){
            if(i == caso){
                parola = parole[i];
            }
        }
        String[] lettere = parola.split("");
        return lettere;
    }
   
    //method that inserts the word in each row of the array
    public static String[][] parolaInMatrice(String[] array, String[][] matrice){
        for(int i=0;i<matrice.length; i++){
            for(int j=0;j<matrice[i].length; j++){
                matrice[i][j] = array[j];
            }
        }
        return matrice;
    }

    //method that defines the colors of the letters 
    public static String[][] controlloParola(String[][] griglia, String[][] grigliaSegreta, int riga){
        for(int j = 0; j<griglia[riga].length; j++){
            if(griglia[riga][j].equals(grigliaSegreta[riga][j])){
                griglia[riga][j] = "\u001b[32m" + griglia[riga][j] + "\u001b[0m"; 
                
            }else if (Arrays.asList(grigliaSegreta[riga]).contains(griglia[riga][j])) {
                griglia[riga][j] = "\u001b[33m" + griglia[riga][j] + "\u001B[0m";
            }

            
        }
        return griglia;
    }
    
    //method that controls whether the user has won
    public static boolean controllaVittoria(String[][] griglia, String[][] grigliaSegreta, int riga) {
        boolean vittoria = true;
        for (int j = 0; j < griglia[riga].length; j++) {
            if (!griglia[riga][j].startsWith("\u001b[32m")) {
                vittoria = false;
                break;
            }
        }
        return vittoria;
    }
}


