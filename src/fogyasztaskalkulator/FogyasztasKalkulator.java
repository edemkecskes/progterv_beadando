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
        if(UjArVan == false){
            Arak[0] = 354;   
            Arak[1] = 355;
            Arak[2] = 391;
            Arak[3] = 217; 
        }
        
        Fajtak[0] = "95-os benzin";
        Fajtak[1] = "Dizel";
        Fajtak[2] = "Keverek";
        Fajtak[3] = "LPG";   
        
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
        if(ujar != 0){
            Arak[uzemanyag] = ujar;
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
                    if(newar < 0) throw new NumberFormatException();
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
    
    public static void hetiKalk() throws IOException{
        System.out.println("*******************************");
        System.out.println("******* HETI KALKULATOR *******");
        System.out.println("*******************************");
        String[] Napok = new String[7];
        Double[] NapiAllas = new Double[7];
        
            Napok[0] = "Hétfő";
            Napok[1] = "Kedd";
            Napok[2] = "Szerda";
            Napok[3] = "Csütörtök";
            Napok[4] = "Péntek";
            Napok[5] = "Szombat";
            Napok[6] = "Vasárnap";
            
        Double TankoltUzemanyag = 0.0;
        
        System.out.println("Kerem, adja meg a napi km.allasokat, majd nyomjon enter!");
        
        for(int napiszamlalo = 0; napiszamlalo < Napok.length; napiszamlalo++){
            boolean kilep = false;
            while(kilep != true){
                System.out.print(Napok[napiszamlalo]+":");
                InputStreamReader in = new InputStreamReader(System.in);
                BufferedReader buf = new BufferedReader(in);
                String tempnapi = buf.readLine();
                try {
                    Double napikm = Double.parseDouble(tempnapi);
                    if(napikm < 0) throw new NumberFormatException();
                    NapiAllas[napiszamlalo] = napikm;
                    System.out.println(Napok[napiszamlalo] + ":" + NapiAllas[napiszamlalo] + "km.");
                    kilep = true;
                    
                }catch (NumberFormatException ex) {
                    System.out.println("Ervenytelen km allas! Probalja ujra!");
                    kilep = false;
                }
                if(kilep) break;
            }
        }
        System.out.println("*******************************");
        Double SzumNapiKm = NapiAllas[0]+NapiAllas[1]+NapiAllas[2]+NapiAllas[3]+NapiAllas[4]+NapiAllas[5]+NapiAllas[6];
        System.out.println("Ez összesen:" + SzumNapiKm + " km a heten.");
        Double max = 0.0;
        for(int i = 0; i < NapiAllas.length; i++){
            if(NapiAllas[i] > max){
		max = NapiAllas[i];
            }
        }
        System.out.println("Legnagyobb megtett tavolsag: " + max + " km.");
        System.out.println("*******************************");
        boolean kilep2 = false;
        Double tankolt = null;
        while(kilep2 != true){
            System.out.print("Mennyit tankolt a heten?:");
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(in);
            String temptankolt = buf.readLine();
            try {
                tankolt = Double.parseDouble(temptankolt);                
                if(tankolt < 0) throw new NumberFormatException();
                System.out.println("Tankolt uzemanyag mennyisege:" + tankolt +" l.");
                kilep2 = true;
            }catch (NumberFormatException ex) {
                System.out.println("Ervenytelen ertek! Probalja ujra!");
                kilep2 = false;
            }
            if(kilep2) break;
        }
        System.out.println("A fogyasztás igy: " + (tankolt/SzumNapiKm)*100 + " l, 100km-en.");
        boolean kilep3 = false;
        Double osszeg = 0.0;
        while(kilep3 != true){
            System.out.print("Milyen uzemanyagot tankolt? (1: 95-os benzin - 2: Dizel - 3: Keverek - 4: LPG)");
            InputStreamReader in2 = new InputStreamReader(System.in);
            BufferedReader buf2 = new BufferedReader(in2);
            String tempfajta = buf2.readLine();
            try {
                int fajta = Integer.parseInt(tempfajta);                
                //DEBUG!
                //if((fajta < 0) || (fajta != 1) || (fajta != 2) || (fajta != 3) || (fajta != 4)) throw new NumberFormatException();
                System.out.println("A tankolt uzemanyag:" + fajta +",ami " + Fajtak[fajta-1] + ".");
                osszeg = ((tankolt/SzumNapiKm)*100)*Arak[fajta];
                System.out.println("Ez igy " + osszeg + " HUF.");
                kilep3 = true;   
            }catch (NumberFormatException ex) {
                System.out.println("Ervenytelen ertek! Probalja ujra!");
                kilep3 = false;
            }
        }
        boolean kilep4 = false;
        System.out.println("Visszateres a fomenube: n, majd enter.");
        System.out.println("Uj kalkulaciohoz u, majd enter es kövesse az utasitasokat.");
        String visszamenu = "";
        while((visszamenu != "u") || (visszamenu != "n")){
            System.out.print("Valasztott opcio? ");
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(in);
            visszamenu = buf.readLine();
            boolean kilep = false;
            switch(visszamenu){
                case "u": hetiKalk();
                kilep4 = true;
                break;
                case "n": fomenu(false);
                kilep4 = true;
                break;
                default: System.out.println("Ervenytelen menupont!");
                break;
            }
            if(kilep4) break;
            }
    }        
    
    public static void mennyiLesz() throws IOException{
        System.out.println("*******************************");
        System.out.println("******* MENNYI LESZ.... *******");
        System.out.println("*******************************");
        boolean break1 = false;
        boolean break2 = false;
        boolean break3 = false;
        boolean break4 = false;
        System.out.println("A tervezett fogyasztas kiszamitasahoz, kerem, adja meg a kovetkezo adatokat!");
        Double TervUtHossza = null;
        while(break1 != true){
            System.out.print("Kerem, adja meg a tervezett ut hosszat km-ben! ");
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(in);
            String TempTervUtHossza = buf.readLine();
            try {
                TervUtHossza = Double.parseDouble(TempTervUtHossza);
                if(TervUtHossza < 0) throw new NumberFormatException();
                System.out.println("A tervezett ut hossza: " + TervUtHossza + " km.");
                break1 = true;   
            }catch (NumberFormatException ex) {
                System.out.println("Ervenytelen ertek! Probalja ujra!");
                break1 = false;
            }
            if(break1) break;
        }
        int fajta = 0;
        while(break2 != true){
            System.out.print("Milyen uzemanyagot hasznal? (1: 95-os benzin - 2: Dizel - 3: Keverek - 4: LPG)");
            InputStreamReader in2 = new InputStreamReader(System.in);
            BufferedReader buf2 = new BufferedReader(in2);
            String tempfajta = buf2.readLine();
            try {
                fajta = Integer.parseInt(tempfajta);                
                // DEBUG!
                //if((fajta < 0) || (tempfajta != "1") || (tempfajta != "2") || (tempfajta != "3") || (tempfajta != "4")) throw new NumberFormatException();
                System.out.println("A hasznalt uzemanyag:" + fajta +",ami " + Fajtak[fajta-1] + ".");
                break2 = true;   
            }catch (NumberFormatException ex) {
                System.out.println("Ervenytelen ertek! Probalja ujra!");
                break2 = false;
            }            
            if(break2) break;
        }
        Double fogyi = null;
        while(break3 != true){
            System.out.println("A gepkocsi adott utszakaszon jellemzo atlagfogyasztasa?");
            InputStreamReader in3 = new InputStreamReader(System.in);
            BufferedReader buf3 = new BufferedReader(in3);
            String tempfogyi = buf3.readLine();
            try {
                fogyi = Double.parseDouble(tempfogyi);
                System.out.println("A megadott fogyasztas: " + fogyi + " l/100km.");
                break3 = true;
            }catch (NumberFormatException ex) {
                System.out.println("Ervenytelen ertek! Probalja ujra!");
                break3 = false;
            }
            if(break3) break;
        }
        Double VegEredmeny = (TervUtHossza*fogyi)*(Arak[fajta]/100);
        System.out.println("A megadott adatok alapjan, ez az ut kb. " + VegEredmeny + "HUF lesz.");
        System.out.println("Visszateres a fomenube: n, majd enter.");
        System.out.println("Uj kalkulaciohoz u, majd enter es kövesse az utasitasokat.");
        String visszamenu = "";
        while((visszamenu != "u") || (visszamenu != "n")){
            System.out.print("Valasztott opcio? ");
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(in);
            visszamenu = buf.readLine();
            switch(visszamenu){
                case "u": mennyiLesz();
                break4 = true;
                break;
                case "n": fomenu(false);
                break4 = true;
                break;
                default: System.out.println("Ervenytelen menupont!");
                break;
            }
            if(break4) break;
        }
    }
   
    public static void kilepes(){
        System.exit(0);
    }
}
