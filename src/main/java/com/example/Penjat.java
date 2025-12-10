package com.example;

import java.util.Random;
import java.util.Scanner;
public class Penjat {


    
    static Scanner scanner = new Scanner(System.in);

    
    public static String[] paraules = {
        "hola", "cadira", "tisores", "riu", "fotografia", "escala",
        "llibre", "ordinador", "ratoli", "armari", "pati", "programa", "columna"
    };

    public static String[] dibuixos = {
        """
           +---+
               |
               |
               |
              ===
        """,
        """
           +---+
           O   |
               |
               |
              ===
        """,
        """
           +---+
           O   |
           |   |
               |
              ===
        """,
        """
           +---+
           O   |
          /|   |
               |
              ===
        """,
        """
           +---+
           O   |
          /|\\  |
               |
              ===
        """,
        """
           +---+
           O   |
          /|\\  |
          /    |
              ===
        """,
        """
           +---+
           O   |
          /|\\  |
          / \\  |
              ===
        """
    };

    
    public static void main(String[] args) {
        
        String paraulaSecreta = obtenirParaula();
        
        char[] estat = inicialitzarEstat(paraulaSecreta);
        String lletresUtilitzades = "";
        int errors = 0;
        int max_errors = 6;
        boolean jocAcabat = false;

        System.out.println("BENVINGUT AL JOC DEL PENJAT!");

        
        while (!jocAcabat) {
            
            mostrarNinot(errors);
            mostrarEstat(estat);
            System.out.println("Errors: " + errors + "/" + max_errors);
            System.out.println("Lletres utilitzades: " + lletresUtilitzades);

            if (errors >= max_errors) {
                System.out.println("");
                System.out.println("Has perdut! La paraula era: " + paraulaSecreta);
                jocAcabat = true;
            } else if (paraulaCompletada(estat)) {
                System.out.println("");
                System.out.println("Enhorabona! Has guanyat!");
                jocAcabat = true;
            } else {
                
                
                char lletra = demanarLletra();

                if (lletraRepetida(lletresUtilitzades, lletra)) {
                    System.out.println(">> Ja has utilitzat la lletra '" + lletra + "'. Torna a provar.");
                } else {
                    lletresUtilitzades += lletra;
                    boolean encert = actualitzarEstat(estat, paraulaSecreta, lletra);

                    if (encert) {
                        System.out.println(">> Molt bé! La lletra '" + lletra + "' hi és!");
                    } else {
                        System.out.println(">> Fallada! La lletra '" + lletra + "' no hi és.");
                        errors++;
                    }
                }
            }
            System.out.println("--------------------------------------------------");
        }
        
        scanner.close(); 
    }

    public static String obtenirParaula() {
        Random random = new Random();
        int index = random.nextInt(paraules.length);
        return paraules[index];
    }

    public static void mostrarNinot(int errors) {
        if (errors >= 0 && errors < dibuixos.length) {
            System.out.println(dibuixos[errors]);
        }
    }

    public static char demanarLletra() {
        char lletra = ' ';
        boolean valida = false;

        while (!valida) {
            System.out.print("Entra una lletra: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                lletra = input.charAt(0);
                valida = true;
            } else {
                System.out.println("Error: Si us plau, introdueix una única lletra vàlida (a-z).");
            }
        }
        return lletra;
    }

    public static char[] inicialitzarEstat(String paraula) {
        char[] estat = new char[paraula.length()];
        for (int i = 0; i < estat.length; i++) {
            estat[i] = '_';
        }
        return estat;
    }

    public static boolean actualitzarEstat(char[] estat, String paraula, char lletra) {
        boolean trobada = false;
        for (int i = 0; i < paraula.length(); i++) {
            if (paraula.charAt(i) == lletra) {
                estat[i] = lletra; 
                trobada = true;
            }
        }
        return trobada;
    }

    public static void mostrarEstat(char[] estat) {
        System.out.print("PARAULA: ");
        for (int i = 0; i < estat.length; i++) {
            System.out.print(estat[i] + " ");
        }
        System.out.println(); 
    }

    public static boolean paraulaCompletada(char[] estat) {
        for (char c : estat) {
            if (c == '_') {
                return false; 
            }
        }
        return true;
    }
    
    public static boolean lletraRepetida(String lletres, char lletra) {
        for (int i = 0; i < lletres.length(); i++) {
            if (lletres.charAt(i) == lletra) {
                return true;
            }
        }
        return false;
    }
}
