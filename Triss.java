
import java.util.Scanner; 

public class Triss{
    public static void main(String[]args){
        //array with numbers that the user needs to place the marks 
        int[][] tabella = {{1,2,3},{4,5,6},{7,8,9}};
        stampaTabella1(tabella);
        
        //User sign selection and value assignment (1 or -1) for victory calculation
        int[] simboli = scegliSimboloGiocatore();
        int utente = simboli[0];
        int computer = simboli[1];
        
        //array with only zero where 1 or -1 values are inserted
        int[][] tabellaN = new int[3][3];

        //while loop that starts the game
        Boolean gioco = false;
        while(gioco == false){
            
            int rispostaUtente = scegliCasellaGiocatore(tabella, tabellaN, utente);
            if( rispostaUtente == -1){
                System.out.println("casella non disponibile, riprova");
                rispostaUtente = scegliCasellaGiocatore(tabella, tabellaN, utente);
            }
            int rispostaComputer = scegliCasellaComputer(tabella, tabellaN, computer);
            if(rispostaComputer == -1){
                System.out.println("pareggio");        
            }
            stampaTabella1(tabella);
            stampaTabella(tabellaN);

            //Check if there were a tris
            int vittoria = controllaTris(tabellaN);
            if(vittoria==1){
                System.out.println("vince o");
                gioco = true;
            }else if(vittoria == -1){
                System.out.println("vince x");
                gioco = true;
            }else if(vittoria == 0){
                gioco = false;
            }
                
        }   
       
        
    }
    
    public static char converti(int c) {
        if(c == 0) return '_';
        if(c == 1) return 'X';
        return 'O';
        
        
    }
    
    //method that prints the table of numbers
    public static void stampaTabella1(int[][] tabella){
        for(int i = 0; i < tabella.length; i++) {
            for(int j = 0; j < tabella[i].length; j++) {
                System.out.print(tabella[i][j] + " ");
            }
            System.out.println();
        }
    }
   
    //method that prints the table with X or O
    public static void stampaTabella(int[][] tabella){
        for(int i = 0; i < tabella.length; i++) {
            for(int j = 0; j < tabella.length; j++) {
                System.out.print(converti(tabella[i][j]) + " ");
            }
            System.out.println();
        }
    }
    
    //method that makes the user choose the sign
    public static int[] scegliSimboloGiocatore() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("scegli X o O: ");
        String risposta1 = scanner1.nextLine();
        int utente = 0;
        int computer = 0;
        if (risposta1.equals("x")){
            utente = -1; //x
            computer = 1; //o
        }else{
            utente = 1; //o
            computer = -1; //x
        }
        return new int[] { utente, computer };
    }

    //method that places the user’s sign in the chosen location
    public static int scegliCasellaGiocatore(int[][] tabella, int[][] tabellaN, int utente) {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("scegli la casella scrivedo il suo numero: ");
        int risposta2 = scanner2.nextInt();
        for(int i=0; i<tabella.length; i++){
            for(int j=0; j<tabella.length; j++){
                if(tabella[i][j] == risposta2 && tabellaN[i][j]==0){
                    tabellaN[i][j] = utente;
                    return risposta2;
                }
            }
        }
        return -1;
    }
    
    //method that randomly places the computer mark in an unoccupied box
    public static int scegliCasellaComputer(int[][] tabella, int[][] tabellaN, int computer) {
        Boolean trovato = false;
        int rispComputer = 0;
        while(trovato == false){
            rispComputer = (int)(Math.random()*9)+1;
            for(int i=0; i<tabella.length; i++){
                for(int j=0; j<tabella.length; j++){
                    if(tabella[i][j]==rispComputer && tabellaN[i][j]==0){
                        trovato=true;
                    }
                }
            }         
        }
        
        for(int i=0; i<tabella.length; i++){
           for(int j=0; j<tabella.length; j++){
               if(tabella[i][j] == rispComputer){
                    tabellaN[i][j] = computer;
                    return rispComputer;
               }
           }
        }
        return -1;
    }
    
    //method that checks whether the user or computer has made tris
    public static int controllaTris(int[][] tabellaN) {
        int vittoria = 0;
        // Controllo righe
        for (int i = 0; i < 3; i++) {
            if (tabellaN[i][0] + tabellaN[i][1] + tabellaN[i][2] == 3) {
                return 1; //o
            }
            else if(tabellaN[i][0] + tabellaN[i][1] + tabellaN[i][2] == -3){
                return  -1; //x
            }
        }
        
    
        // Controllo colonne
        for (int i = 0; i < 3; i++) {
            if (tabellaN[0][i] + tabellaN[1][i] + tabellaN[2][i] == 3 ) {
                return 1; //o
            }
            else if(tabellaN[0][i] + tabellaN[1][i] + tabellaN[2][i] == -3){
                return -1; //x
            }
        }
    
        // Controllo diagonali
        if (tabellaN[0][0] + tabellaN[1][1] + tabellaN[2][2] == 3 ) {
            return  1; //o
        }
        else if (tabellaN[0][0] + tabellaN[1][1] + tabellaN[2][2] == -3){
            return -1; //x
        }
        if (tabellaN[0][2] + tabellaN[1][1] + tabellaN[2][0] == 3 ) {
            return 1; //o
        }
        else if(tabellaN[0][2] + tabellaN[1][1] + tabellaN[2][0] == -3 ){
            return -1; //x
        }
        
        return 0;
    }
    
    
}