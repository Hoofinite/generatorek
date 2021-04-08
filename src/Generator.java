import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Generator {

    private static final int ILOSC_WIZYT = 10000;
    public static final int ILOSC_PRACOWNIKOW = 100;
    public static final int ILOSC_PACJENTOW = 100;
    public static final int ILOSC_OSOB = ILOSC_PRACOWNIKOW + ILOSC_PACJENTOW;
    private static final int ILOSC_SZPITALI = Szpital.nazwy.length;

    static DecimalFormat df = new DecimalFormat("####,####.##");

    private static Specjalizacje[] specjalizacje = new Specjalizacje[Specjalizacje.specjalizacje.length];
    private static Leki[] leki = new Leki[Leki.leki.length];
    private static Recepta_Leki[] recepta_leki = new Recepta_Leki[ILOSC_WIZYT];
    private static Recepta[] recepta = new Recepta[ILOSC_WIZYT];
    private static Adresy[] adresy = new Adresy[ILOSC_OSOB + ILOSC_SZPITALI];
    private static Osoby[] osoby = new Osoby[ILOSC_OSOB];
    private static Szpital[] szpitale = new Szpital[ILOSC_SZPITALI];
    private static Pacjent[] pacjenci = new Pacjent[ILOSC_PACJENTOW];
    private static Lekarz[] lekarze = new Lekarz[ILOSC_PRACOWNIKOW];
    public static Pracownicy[] pracownicy = new Pracownicy[ILOSC_PRACOWNIKOW];
    private static Wizyta[] wizyty = new Wizyta[ILOSC_WIZYT];

    public static void main(String[] args) {
        initializeArrays();

        try {
            Specjalizacje.saveAllToFile(specjalizacje);
            Leki.saveAllToFile(leki);
            Recepta_Leki.saveAllToFile(recepta_leki);
            Recepta.saveAllToFile(recepta);
            Adresy.saveAllToFile(adresy);
            Osoby.saveAllToFile(osoby);
            Szpital.saveAllToFile(szpitale);
            Pacjent.saveAllToFile(pacjenci);
            Lekarz.saveAllToFile(lekarze);
            Pracownicy.saveAllToFile(pracownicy);
            Wizyta.saveAllToFile(wizyty);
        }catch(FileNotFoundException e){
            System.out.println("File not found: " + e);
        }

    }

    private static void initializeArrays(){

        for(int i = 0; i < specjalizacje.length; i++)
            specjalizacje[i] = new Specjalizacje();

        for(int i = 0; i < leki.length; i++)
            leki[i] = new Leki();

        for(int i = 0; i < ILOSC_WIZYT; i++)
            recepta_leki[i] = new Recepta_Leki();

        for(int i = 0; i < ILOSC_WIZYT; i++)
            recepta[i] = new Recepta();

        for(int i = 0; i < ILOSC_OSOB + ILOSC_SZPITALI; i++)
            adresy[i] = new Adresy();

        for(int i = 0; i < ILOSC_OSOB; i++)
            osoby[i] = new Osoby();

        for(int i = 0; i < ILOSC_SZPITALI; i++)
            szpitale[i] = new Szpital();

        for(int i = 0; i < ILOSC_PACJENTOW; i++)
            pacjenci[i] = new Pacjent();

        for(int i = 0; i < ILOSC_PRACOWNIKOW; i++)
            lekarze[i] = new Lekarz();

        for(int i = 0; i < ILOSC_PRACOWNIKOW; i++)
            pracownicy[i] = new Pracownicy();

        for(int i = 0; i < ILOSC_WIZYT; i++)
            wizyty[i] = new Wizyta();
    }

}

class Specjalizacje{

    private static int maxID = 0;
    private int ID;
    private String nazwa;

    static final String[] specjalizacje = { "Okulistyka", "Neurologia", "Neurochirurgia", "Pediatria",
            "Psychiatria", "Urologia", "Kardiologia", "Chirurgia"};

    Specjalizacje(){
        if(maxID < specjalizacje.length){
            this.nazwa = specjalizacje[maxID];
            this.ID = ++maxID;
        }
    }

    static void saveAllToFile(Specjalizacje[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Specjalizacje.csv");
        for (Specjalizacje tmp : all){
            writer.println(tmp.ID + ";" + tmp.nazwa);
        }
        writer.close();
    }

}

class Leki{

    private static int maxID = 0;
    private int ID;
    private String nazwa;
    private double cena;

    static final String[] leki = {"Lek1", "Lek2", "Lek3", "Lek4", "Lek5", "Lek6", "Lek7",
            "Lek8", "Lek9", "Lek10"};

    Leki(){
        if(maxID < leki.length){
            Random rand = new Random();

            this.nazwa = leki[maxID];
            this.cena = 10 + (100-10) * rand.nextDouble();
            this.ID = ++maxID;
        }
    }

    static void saveAllToFile(Leki[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Leki.csv");
        for (Leki tmp : all){
            writer.println(tmp.ID + ";" + tmp.nazwa + ";" + Generator.df.format(tmp.cena));
        }
        writer.close();
    }

}

class Recepta_Leki{

    private static int maxID = 0;
    private int ID;
    private int id_lek;
    private int ilosc;

    Recepta_Leki(){
        Random rand = new Random();
        this.id_lek = rand.nextInt(Leki.leki.length);
        this.ilosc = rand.nextInt(10) + 1;
        this.ID = ++maxID;
    }

    static void saveAllToFile(Recepta_Leki[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Recepta_Leki.csv");
        for (Recepta_Leki tmp : all){
            writer.println(tmp.ID + ";" + tmp.id_lek + ";" + tmp.ilosc);
        }
        writer.close();
    }

}

class Recepta{

    private static int maxID = 0;
    private int ID;
    private int id_wizyta;

    Recepta(){
        this.ID = ++maxID;
        this.id_wizyta = this.ID;
    }

    static void saveAllToFile(Recepta[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Recepta.csv");
        for (Recepta tmp : all){
            writer.println(tmp.ID + ";" + tmp.id_wizyta);
        }
        writer.close();
    }

}

class Adresy{

    private static int maxID = 0;
    private int ID;
    private String ulica;
    private int nr_domu;
    private int nr_lokalu;
    private String kod_pocztowy;
    private String poczta;

    static final String[] ulice = { "Polna", "Lesna", "Sloneczna", "Krotka", "Szkolna",
            "Ogrodowa", "Lipowa", "Brzozowa" };

    static final String[] poczty = { "Warszawa", "Krakow", "Lodz", "Wroclaw", "Poznan", "Kielce", "Gdynia",
            "Czestochowa", "Radom", "Sosnowiec", "Torun" };

    static final String[] kody_pocztowe = { "11-111", "22-222", "33-333", "44-444", "55-555", "66-666", "77-777",
            "88-888", "99-999", "12-123", "13-166" };

    Adresy(){
        Random rand = new Random();

        ulica = ulice[rand.nextInt(ulice.length)];
        int randPoczta = rand.nextInt(poczty.length);
        poczta = poczty[randPoczta];
        kod_pocztowy = kody_pocztowe[randPoczta];

        int dom_lokal = rand.nextInt(2);
        if(dom_lokal == 0){
            nr_domu = rand.nextInt(100) + 1;
            nr_lokalu = 0;
        }else{
            nr_lokalu = rand.nextInt(100) + 1;
            nr_domu = 0;
        }

        this.ID = ++maxID;
    }

    static void saveAllToFile(Adresy[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("K_adres.csv");
        for (Adresy tmp : all){
            writer.println(tmp.ID + ";" + tmp.ulica + ";" + tmp.nr_domu + ";" + tmp.nr_lokalu
                    + ";" + tmp.poczta + ";" + tmp.kod_pocztowy);
        }
        writer.close();
    }
}

class Osoby{

    private static int maxID = 0;
    private int ID;
    private String nazwisko;
    private String imie;
    private int id_adres;

    static final String[] nazwiska = { "Nowak", "Kowalski", "Wisniewski", "Wojcik", "Kowalczyk",
            "Kaminski", "Lewandowski", "Zielinski", "Szymanski", "Wozniak" };

    static final String[] imiona = { "Antoni", "Jan", "Jakub", "Aleksander", "Szymon", "Franciszek",
            "Filip", "Mikolaj", "Wojciech", "Adam", "Kamil", "Michal",
            "Karol", "Dawid", "Konrad", "Maciej"};

    Osoby(){

        Random rand = new Random();

        int id_nazwiska = rand.nextInt(nazwiska.length);
        int id_imienia = rand.nextInt(imiona.length);

        nazwisko = nazwiska[id_nazwiska];
        imie = imiona[id_imienia];

        this.ID = ++maxID;

        id_adres = ID;
    }

    static void saveAllToFile(Osoby[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Osoby.csv");
        for (Osoby tmp : all){
            writer.println(tmp.ID + ";" + tmp.imie + ";" + tmp.nazwisko + ";" + tmp.id_adres);
        }
        writer.close();
    }
}

class Szpital{

    private static int maxID = 0;
    private int ID;
    private String nazwa;
    private int id_adres;

    final static String[] nazwy = { "SP ZOZ Szpital Uniwersytecki w Krakowie",
            "Centrum Onkologii - Instytut im. M. Skłodowskiej-Curie w Warszawie",
            "Wielkopolskie Centrum Onkologii im. Marii Skłodowskiej-Curie w Poznaniu",
            "Samodzielny Publiczny Centralny Szpital Kliniczny",
            "Wojewódzki Szpital Zespolony im. L. Rydygiera w Toruniu",
            "Szpital Powiatowy w Chrzanowie",
            "EMC Regionalne Centrum Zdrowia" };

    Szpital() {
        ID = ++maxID;
        id_adres = Generator.ILOSC_OSOB + ID;
        nazwa = nazwy[ID-1];
    }

    static void saveAllToFile(Szpital[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Szpital.csv");
        for (Szpital tmp : all){
            writer.println(tmp.ID + ";" + tmp.nazwa + ";" + tmp.id_adres);
        }
        writer.close();
    }
}

class Pacjent{

    private static int maxID = 0;
    private int ID;
    private int id_osoba;

    Pacjent(){
        this.ID = ++maxID;
        id_osoba = this.ID;
    }

    static void saveAllToFile(Pacjent[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Pacjent.csv");
        for (Pacjent tmp : all){
            writer.println(tmp.ID + ";" + tmp.id_osoba);
        }
        writer.close();
    }
}

class Lekarz{

    private static int maxID = 0;
    private int ID;
    private int id_osoba;
    private int id_specjalizacja;
    private double wynagrodzenie;

    Lekarz(){
        Random rand = new Random();

        this.ID = ++maxID;
        this.id_osoba = Generator.ILOSC_PACJENTOW + ID;
        this.id_specjalizacja = rand.nextInt(Specjalizacje.specjalizacje.length) + 1;
        wynagrodzenie = 3000.0 + (7000.0 - 2000.0) * rand.nextDouble();
    }

    static void saveAllToFile(Lekarz[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Lekarz.csv");
        for (Lekarz tmp : all){
            writer.println(tmp.ID + ";" + tmp.id_osoba + ";" +
                    tmp.id_specjalizacja + ";" + Generator.df.format(tmp.wynagrodzenie));
        }
        writer.close();
    }
}

class Pracownicy{

    private static int maxID = 0;
    public int id_lekarz;
    public int id_szpital;
    private GregorianCalendar data_zatrudnienia;

    Pracownicy(){

        Random rand = new Random();

        id_lekarz = ++maxID;

        id_szpital = rand.nextInt(Szpital.nazwy.length) + 1;

        data_zatrudnienia = new GregorianCalendar();
        data_zatrudnienia.set(Calendar.YEAR, rand.nextInt(2019 - 2010) + 2010);
        data_zatrudnienia.set(Calendar.DAY_OF_YEAR, rand.nextInt(data_zatrudnienia.getActualMaximum(Calendar.DAY_OF_YEAR)));
    }

    static void saveAllToFile(Pracownicy[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Pracownicy.csv");
        for (Pracownicy tmp : all){
            writer.println(tmp.id_lekarz + ";" + tmp.id_szpital + ";" +
                    tmp.data_zatrudnienia.get(Calendar.DAY_OF_MONTH) + "-" +
                    (tmp.data_zatrudnienia.get(Calendar.MONTH)+1)
                    + "-" + tmp.data_zatrudnienia.get(Calendar.YEAR));
        }
        writer.close();
    }
}

class Wizyta{

    private static int maxID = 0;
    private int ID;
    private GregorianCalendar data;
    private int id_pacjent;
    private int id_lekarz;
    private int id_szpital;

    Wizyta(){
        Random rand = new Random();

        id_pacjent = rand.nextInt(Generator.ILOSC_PACJENTOW) + 1;
        id_lekarz = rand.nextInt(Generator.ILOSC_PRACOWNIKOW) + 1;

        for(Pracownicy p : Generator.pracownicy){
            if(id_lekarz == p.id_lekarz) {
                id_szpital = p.id_szpital;
                break;
            }
        }

        this.ID = ++maxID;
        data = new GregorianCalendar();
        data.set(Calendar.YEAR, rand.nextInt(2019 - 2010) + 2010);
        data.set(Calendar.DAY_OF_YEAR, rand.nextInt(data.getActualMaximum(Calendar.DAY_OF_YEAR)));
    }

    static void saveAllToFile(Wizyta[] all) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("Wizyta.csv");
        for (Wizyta tmp : all){
            writer.println(tmp.ID + ";" + tmp.id_pacjent + ";" +
                    + tmp.id_lekarz + ";" + tmp.id_szpital + ";" +
                    tmp.data.get(Calendar.DAY_OF_MONTH) + "-" +
                    (tmp.data.get(Calendar.MONTH)+1)
                    + "-" + tmp.data.get(Calendar.YEAR));
        }
        writer.close();
    }

}