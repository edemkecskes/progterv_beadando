/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fogyasztaskalkulator;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author akecskes
 */


public class FogyasztasKalkulator {
    
public static final Integer[] Arak = new Integer[4];
public static final String[] Fajtak = new String[4];
public static boolean UjArVan = false;

    public static void main(String[] args) throws IOException {
        fomenu(true);
    }
     
    public static void fomenu(boolean header) throws IOException{
        if(header != false){
            System.out.println("*******************************");
            System.out.println("***** FOGYASZTAS SZAMLALO *****");
            System.out.println("*******************************");
            System.out.println("Progterv beadando: KEAXAAZ.ZSKF");
        }
        System.out.println("Kerem valasszon a következo menupontok kozul, majd nyomjon entert!");
        System.out.println("1 - Mennyi lesz, ha...");
        System.out.println("2 - Heti kalkulator, napi km. allas alapjan");
        System.out.println("3 - Beallitasok");
        System.out.println("4 - Kilepes");
        int menupont = 0;
        
        while((menupont != 1) || (menupont != 2) || (menupont != 3) || (menupont != 4)){
            System.out.print("Valasztott opcio? ");
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(in);
            boolean kilepesAMenubol = false;
      
            try {
                menupont = Integer.parseInt(buf.readLine());
            }catch (NumberFormatException ex) {
                    System.out.println("Ervenytelen menupont!");
                    fomenu(false);
                    kilepesAMenubol = true;
                    break;
            }
            
            switch(menupont){
                case 1: mennyiLesz();
                kilepesAMenubol = true;
                break;
                case 2: hetiKalk();
                kilepesAMenubol = true;
                break;
                case 3: beallitasok(0, 0, 0);
                kilepesAMenubol = true;
                break;
                case 4: kilepes();
                kilepesAMenubol = true;
                break;
                default: System.out.println("Ervenytelen menupont!");
                break;
            }
            if(kilepesAMenubol) break;
        }
    }
    
    public static void beallitasok(int opcio, int uzemanyag, int ujar) throws IOException{
        Fajtak[0] = "95-os benzin";
        Fajtak[1] = "Dizel";
        Fajtak[2] = "Keverek";
        Fajtak[3] = "LPG";   

        if(ujar != 0){
            Arak[uzemanyag] = ujar;
        } 
        
        if(UjArVan == false){
            Arak[0] = 354;   
            Arak[1] = 355;
            Arak[2] = 391;
            Arak[3] = 217; 
        }
        
        if(opcio == 0){
            System.out.println("*******************************");
            System.out.println("********* BEALLITASOK *********");
            System.out.println("*******************************");
            System.out.println("Az aktualis uzemanyagarak itt erhetoek el:");
            System.out.println("http://nav.gov.hu/nav/szolgaltatasok/uzemanyag/aktualis_uzemanyagarak");
            for(int u = 0; u < Fajtak.length; u++){
                System.out.println(u+1 + " - " +Fajtak[u] + ": " + Arak[u]+ " HUF");
            }
            System.out.println("Visszateres a fomenube: n, majd enter.");
            System.out.println("Uj ar megadasa: e, majd enter es kövesse az utasitasokat.");
            String vmenu = "";
            while((vmenu != "e") || (vmenu != "n")){
                System.out.print("Valasztott opcio? ");
                InputStreamReader in = new InputStreamReader(System.in);
                BufferedReader buf = new BufferedReader(in);
                vmenu = buf.readLine();
                boolean kilep = false;
                switch(vmenu){
                    case "e": beallitasok(1, 0, 0);
                    kilep = true;
                    break;
                    case "n": fomenu(false);
                    kilep = true;
                    break;
                    default: System.out.println("Ervenytelen menupont!");
                    break;
                }
                if(kilep) break;
            }
        } else if(opcio == 1) {
            System.out.println("*******************************");
            System.out.println("******* UJ AR MEGADASA ********");
            System.out.println("*******************************");
            System.out.println("Melyik uzemanyagot szeretne megvaltoztanti?");
            System.out.println("1 - 95-ös benzin, 2 - Dizel, 3 - Keverek, 4 - LPG");
            int emenu = 0;
            while((emenu != 1) || (emenu != 2) || (emenu != 3) || (emenu != 4)){
                System.out.print("Megvaltoztatando uzemanyag? ");
                InputStreamReader in = new InputStreamReader(System.in);
                BufferedReader buf = new BufferedReader(in);
                //NumberFormatException-t elkapni
                boolean kilep = false;
                try {
                    emenu = Integer.parseInt(buf.readLine());
                }catch (NumberFormatException ex) {
                    System.out.println("Ervenytelen üzemanyag!");
                    beallitasok(1, 0, 0);
                    kilep = true;
                    break;
                }
                switch(emenu){
                    case 1: beallitasok(2, 0, 0);
                    kilep = true;
                    break;
                    case 2: beallitasok(2, 1, 0);
                    kilep = true;
                    break;
                    case 3: beallitasok(2, 2, 0);
                    kilep = true;
                    break;
                    case 4: beallitasok(2, 3, 0);
                    kilep = true;
                    break;
                    default: System.out.println("Ervenytelen uzemanyag!");
                    break;
                }
                if(kilep) break;
            }
        } else if (opcio == 2){
            System.out.print(Fajtak[uzemanyag] + " uj ara legyen, HUF: ");
            //String teszt = "";
            //String tipus = teszt.getClass().getName();
            //System.out.println(tipus);
            boolean kilep = false;
            while(kilep != true){
                InputStreamReader in = new InputStreamReader(System.in);
                BufferedReader buf = new BufferedReader(in);
                String tempar = buf.readLine();
                boolean ujarbeallitva = false;
                int newar = 0;
                try {
                    newar = Integer.parseInt(tempar);
                }catch (NumberFormatException ex) {
                    System.out.println("Ervenytelen ar! Probalja ujra!");
                    kilep = false;
                }
                kilep = true;
                ujarbeallitva = true;
                if(kilep){
                    if(ujarbeallitva != true){
                        beallitasok(2, uzemanyag, 0);
                    } else {
                        System.out.println("Az uj ar: " + newar + " HUF.");
                        UjArVan = true;
                        beallitasok(0, uzemanyag, newar);
                    }
                    break;
                }
            }
        } else {
            
    }
    }
    
    public static void hetiKalk(){
        System.out.println("*******************************");
        System.out.println("******* HETI KALKULATOR *******");
        System.out.println("*******************************");
        System.out.println("Kerem, irja be az utolso km allast!");
        System.out.println("Kerem, irja be a jelenlegi km allast!");
        System.out.println("Hany liter uzemanyagot tankolt a heten?");
        System.out.println("A fogyasztas x liter volt.");
        System.out.println("https://www.gyakorikerdesek.hu/kozlekedes__autok-motorok__1367446-hogy-szamitom-ki-a-fogyasztast-100-km-re");
    }        
    
    public static void mennyiLesz(){
        System.out.println("*******************************");
        System.out.println("******* MENNYI LESZ.... *******");
        System.out.println("*******************************");
        System.out.println("https://szamoldki.hu/hu/kalkulator/auto-fogyasztasi-koltseg-kalkulator");
        for(int asd = 0; asd < Arak.length; asd++){
            System.out.println(Arak[asd]);
        }
    }
   
    public static void kilepes(){
        System.out.println("Bye! :)");
        System.exit(0);
    }
}