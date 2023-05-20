import java.util.Scanner;
import java.util.Arrays;

public class forzaQuattro2{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        //creation of the game grid
        String[][] griglia = new String[7][7];
        System.out.println("l'utente è il rosso, il computer è il giallo");

        riempiMatrice(griglia);
        stampaMatrice(griglia);

        String segnoUtente =  "\u001b[31m" + "o" + "\u001b[0m";
        String segnoComputer = "\u001b[33m" + "o" + "\u001b[0m";
        
        //while loop that starts the game
        Boolean gioco = false;
        while(gioco == false){
            //asks the user to insert the column where he wants to place his mark
            System.out.println("inserisci la colonna: ");
            int colonna = scanner.nextInt();
            boolean mossaValida = mossaUtente(griglia, colonna);
            if(mossaValida == false){
                System.out.println("Mossa non valida. Riprova.");
                continue;
            }
            griglia = mossaComputer(griglia);
            stampaMatrice(griglia);
            //check if the user has won
            boolean vittoria = controllaVittoria(griglia, segnoUtente);
            if(vittoria == true){
                System.out.println("hai vinto!");
                break;
            }
            //check if the computer has won
            vittoria = controllaVittoria(griglia, segnoComputer);
            if(vittoria == true){
                System.out.println("hai perso!");
                break;
            }
               
        }
        
        

        
    }


    //method that prints string array
    public static void stampaMatrice(String[][] matrice){
        for(int i = 0; i < matrice.length; i++) {
            for(int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //method that fills the array of "o"
    public static String[][] riempiMatrice(String[][] griglia){
        for(int i=0; i<griglia.length; i++){
            for(int j=0; j<griglia[i].length;j++){
                griglia[i][j] = "o";
            }
        }
        griglia[0][0] = "\u001b[32m" + "0" + "\u001b[0m";
        griglia[0][1] = "\u001b[32m" + "1" + "\u001b[0m";
        griglia[0][2] = "\u001b[32m" + "2" + "\u001b[0m";
        griglia[0][3] = "\u001b[32m" + "3" + "\u001b[0m";
        griglia[0][4] = "\u001b[32m" + "4" + "\u001b[0m";
        griglia[0][5] = "\u001b[32m" + "5" + "\u001b[0m";
        griglia[0][6] = "\u001b[32m" + "6" + "\u001b[0m";
        
        griglia[1][0] = "\u001b[32m" + "1" + "\u001b[0m";
        griglia[2][0] = "\u001b[32m" + "2" + "\u001b[0m";
        griglia[3][0] = "\u001b[32m" + "3" + "\u001b[0m";
        griglia[4][0] = "\u001b[32m" + "4" + "\u001b[0m";
        griglia[5][0] = "\u001b[32m" + "5" + "\u001b[0m";
        griglia[6][0] = "\u001b[32m" + "6" + "\u001b[0m";
        return griglia;

    }

    //method that makes the user’s move 
    public static boolean mossaUtente(String[][] griglia, int colonna) {
        if (colonna < 0 || colonna >= griglia[0].length) {
            return false; // La colonna selezionata non è valida
        }
    
        for (int i = griglia.length - 1; i > 0; i--) {
            if (griglia[i][colonna].equals("o")) {
                griglia[i][colonna] = "\u001b[31m" + "o" + "\u001b[0m";
                return true; // Mossa valida
            }
        }
    
        return false; // La colonna è piena, la mossa non è valida
    }

    //method that makes the computer's move
    public static String[][] mossaComputer(String[][] griglia){
        Boolean trovato = false;
        int rispComputer = 0;
        while(trovato == false){
            rispComputer = (int)(Math.random()*6)+1;
            for (int i = griglia.length - 1; i > 0; i--) {
                if(griglia[i][rispComputer].equals("o")){
                    trovato = true;
                }
            }
        }

        for (int i = griglia.length - 1; i > 0; i--) {
            if (griglia[i][rispComputer].equals("o")) {
                griglia[i][rispComputer] = "\u001b[33m" + "o" + "\u001b[0m";
                return griglia; // Mossa valida
            }
        }
        return griglia;
    } 

    //method that checks if there is a win
    public static boolean controllaVittoria(String[][] griglia, String segno) {
        // Controllo delle vittorie in orizzontale
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length - 3; j++) {
                if (griglia[i][j].equals(segno) && griglia[i][j + 1].equals(segno) &&
                    griglia[i][j + 2].equals(segno) && griglia[i][j + 3].equals(segno)) {
                    return true; // Vittoria trovata in orizzontale
                }
            }
        }
    
        // Controllo delle vittorie in verticale
        for (int i = 0; i < griglia.length - 3; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                if (griglia[i][j].equals(segno) && griglia[i + 1][j].equals(segno) &&
                    griglia[i + 2][j].equals(segno) && griglia[i + 3][j].equals(segno)) {
                    return true; // Vittoria trovata in verticale
                }
            }
        }
    
        // Controllo delle vittorie in diagonale (da sinistra a destra)
        for (int i = 0; i < griglia.length - 3; i++) {
            for (int j = 0; j < griglia[i].length - 3; j++) {
                if (griglia[i][j].equals(segno) && griglia[i + 1][j + 1].equals(segno) &&
                    griglia[i + 2][j + 2].equals(segno) && griglia[i + 3][j + 3].equals(segno)) {
                    return true; // Vittoria trovata in diagonale (da sinistra a destra)
                }
            }
        }
    
        // Controllo delle vittorie in diagonale (da destra a sinistra)
        for (int i = 0; i < griglia.length - 3; i++) {
            for (int j = 3; j < griglia[i].length; j++) {
                if (griglia[i][j].equals(segno) && griglia[i + 1][j - 1].equals(segno) &&
                    griglia[i + 2][j - 2].equals(segno) && griglia[i + 3][j - 3].equals(segno)) {
                    return true; // Vittoria trovata in diagonale (da destra a sinistra)
                }
            }
        }
    
        return false; // Nessuna vittoria trovata
    }
    


}