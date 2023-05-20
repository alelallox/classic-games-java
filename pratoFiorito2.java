import java.util.Scanner;

public class pratoFiorito2{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        //choice of difficulty of the game
         int dimensione = difficoltàGioco();
        int numFiori = 0;
        if(dimensione == 5){
            numFiori = 7;
        }else if(dimensione == 7){
            numFiori = 12;
        }else{
            numFiori = 19;
        }
        //boolean array with bomb position
        boolean[][] campo = new boolean[dimensione][dimensione];
        piazzaFiori(campo, numFiori);

        //int array with nearby bomb numbers
        int[][] fioriVicini = new int[dimensione][dimensione];
        for(int i = 0; i < campo.length; i++) {
            for(int j = 0; j < campo[i].length; j++) {
                int numFioriVicini = contaFioriVicini(campo, i, j);
                fioriVicini[i][j] = numFioriVicini;
            }
        }
        //transform int values into string and create string array
        String[][] fioriViciniStr = new String[dimensione][dimensione];
        for (int i = 0; i < fioriVicini.length; i++) {
            for (int j = 0; j < fioriVicini.length; j++) {
                fioriViciniStr[i][j] = Integer.toString(fioriVicini[i][j]);
            }
        }
        
        //basic array with only X
        String[][] matrix = new String[dimensione][dimensione];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = "x";
            }
        }
        stampaMatriceStr(matrix);
        //first move out of while unlocking a box and the 8 boxs around and all zeros
        System.out.print("Inserisci la riga da scoprire: ");
        int riga = scanner.nextInt();
        System.out.print("Inserisci la colonna da scoprire: ");
        int colonna = scanner.nextInt();
        matrix = primaMossa(riga, colonna, fioriViciniStr, matrix);
        stampaMatriceStr(matrix);
        //while loop starting the game
        boolean ciclo = true;
        while (ciclo == true) {
            //asks the location of the box to be discovered
            System.out.print("Inserisci la riga da scoprire: ");
            int riga2 = scanner.nextInt();
            System.out.print("Inserisci la colonna da scoprire: ");
            int colonna2 = scanner.nextInt();
            scanner.nextLine();
            //asks whether you want to place a flag or dig 
            System.out.print("digita S per scavare o digita B per piazzare la bandiera: ");
            String azioneUtente = scanner.nextLine();

            if(azioneUtente.equals("b")){
                matrix[riga2][colonna2] = "B";
            }else{
                matrix = scambio(riga2, colonna2, fioriViciniStr, matrix);
            }
            //check if the user’s choice move is correct, if it is wrong the user has lost and the loop is interrupted
            Boolean controllo = controlloMossa(matrix, fioriViciniStr, riga2, colonna2, azioneUtente);
            if(controllo == false){
                ciclo = false;
                break;
            }
            //check if there is a victory 
            boolean vittoria = controlloVittoria(matrix);
            if(vittoria == true){
                stampaMatriceStr(matrix);
                System.out.println("vittoria");
                ciclo = false;
                break;
            }
            stampaMatriceStr(matrix);
               
        }
    }

    //method that randomly places bombs in the array of boolean values, where the boxes with true have a bomb 
    public static void piazzaFiori(boolean[][] campo, int numFiori) {

        for (int i = 0; i < numFiori; i++) {
            int x = (int)(Math.random() * campo.length);
            int y = (int)(Math.random() * campo[0].length);
            campo[x][y] = true;
        }
    }

    //method that counts the number of bombs present in the 8 boxes around each box and inserts the value in the array of integers
    public static int contaFioriVicini(boolean[][] campo, int riga, int colonna) {
        int fioriVicini = 0;
        
        if(campo[riga][colonna] == true){
            fioriVicini = 10;
            return fioriVicini;
        }
        // Controllo della casella a sinistra
        if (colonna > 0 && campo[riga][colonna - 1]) {
            fioriVicini++;
        }
        
        // Controllo della casella a destra
        if (colonna < campo[0].length - 1 && campo[riga][colonna + 1]) {
            fioriVicini++;
        }
        
        // Controllo della casella sopra
        if (riga > 0 && campo[riga - 1][colonna]) {
            fioriVicini++;
        }
        
        // Controllo della casella sotto
        if (riga < campo.length - 1 && campo[riga + 1][colonna]) {
            fioriVicini++;
        }
        
        // Controllo della casella in alto a sinistra
        if (riga > 0 && colonna > 0 && campo[riga - 1][colonna - 1]) {
            fioriVicini++;
        }
        
        // Controllo della casella in alto a destra
        if (riga > 0 && colonna < campo[0].length - 1 && campo[riga - 1][colonna + 1]) {
            fioriVicini++;
        }
        
        // Controllo della casella in basso a sinistra
        if (riga < campo.length - 1 && colonna > 0 && campo[riga + 1][colonna - 1]) {
            fioriVicini++;
        }
        
        // Controllo della casella in basso a destra
        if (riga < campo.length - 1 && colonna < campo[0].length - 1 && campo[riga + 1][colonna + 1]) {
            fioriVicini++;
        }
        
        return fioriVicini;
    }

    //method which puts the string array box with numbers in the X array
    public static String[][] scambio(int riga, int colonna, String[][] fioriViciniStr, String[][] matrix){
        matrix[riga][colonna] = fioriViciniStr[riga][colonna];
        return matrix;
    }

    //Method that manages the first Move that unlocks one box and the 8 boxs around and all zeros
    public static String[][] primaMossa(int riga,int colonna, String[][] fioriViciniStr, String[][] matrix){
        for(int i = 0; i < fioriViciniStr.length; i++){
            for(int j = 0; j < fioriViciniStr.length; j++){
                if(fioriViciniStr[i][j].equals("0")){
                    matrix = scambio(i, j, fioriViciniStr, matrix);
                }
            }
        }
        if (!fioriViciniStr[riga][colonna].equals("10")) {
            matrix = scambio(riga, colonna, fioriViciniStr, matrix);
        } 
        
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 && !fioriViciniStr[riga-1][colonna-1].equals("10")){
            matrix[riga-1][colonna-1] = fioriViciniStr[riga-1][colonna-1];
        }
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 && !fioriViciniStr[riga-1][colonna].equals("10")){
            matrix[riga-1][colonna] = fioriViciniStr[riga-1][colonna];
        }
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 && !fioriViciniStr[riga-1][colonna+1].equals("10")){
            matrix[riga-1][colonna+1] = fioriViciniStr[riga-1][colonna+1]; 
        }
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 && !fioriViciniStr[riga][colonna-1].equals("10")){
            matrix[riga][colonna-1] = fioriViciniStr[riga][colonna-1];
        }
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 && !fioriViciniStr[riga][colonna+1].equals("10")){
            matrix[riga][colonna+1] = fioriViciniStr[riga][colonna+1];
        }
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 && !fioriViciniStr[riga+1][colonna-1].equals("10")){
            matrix[riga+1][colonna-1] = fioriViciniStr[riga+1][colonna-1];
        }
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 &&!fioriViciniStr[riga+1][colonna].equals("10")){
            matrix[riga+1][colonna] = fioriViciniStr[riga+1][colonna];
        }
        if(riga!=0 && riga!=9 && colonna!=0 && colonna!=9 && !fioriViciniStr[riga+1][colonna+1].equals("10")){
            matrix[riga+1][colonna+1] = fioriViciniStr[riga+1][colonna+1];
        } 
        return matrix;
    }

    //method that prints integer array 
    public static void stampaMatriceInt(int[][] matrice){
        for(int i = 0; i < matrice.length; i++) {
            for(int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
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

    //method that checks whether the user’s move is correct
    public static boolean controlloMossa(String[][] matrix, String[][] fioriViciniStr, int riga, int colonna, String azioneUtente){
        boolean controllo = true;
        String cellaSelezionata = matrix[riga][colonna];
        String valoreFioriVicini = fioriViciniStr[riga][colonna];
        
        if (cellaSelezionata.equals("B")) { 
            if (!valoreFioriVicini.equals("10")) {
                stampaMatriceStr(matrix); 
                System.out.println("Errore: non hai individuato la giusta posizione della mina");
                return false;
            }
        } else if (azioneUtente.equals("s")) { 
            if (valoreFioriVicini.equals("10")) { 
                stampaMatriceStr(matrix);
                System.out.println("Errore: hai selezionato una mina");

                return false;
            }
        } 
        return controllo;

    }

    //method that checks if the user won, unlocking all the boxes correctly
    public static boolean controlloVittoria(String[][] matrix){
        boolean vittoria = false;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j].equals("x")){
                    return false;
                }
            }
        }
        return true;
    }

    //method that asks for the difficulty with which the user wants to play
    public static int difficoltàGioco(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("sceglie la difficolta da 1 a 3:");
        int livello = scanner.nextInt();
        int dimensione = 0;
        if(livello == 1){
            dimensione = 5;
        }else if(livello == 2){
            dimensione = 7; 
        }else{
            dimensione = 10;
        }
        return dimensione;
    }
}       

