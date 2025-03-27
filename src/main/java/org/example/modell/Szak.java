package org.example.modell;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Szak {
    public List<Tantargy> tantargyak;

    private String nev;
    private transient UUID id;
    private static final String TARGYAK = "targyak.dat";

    public Szak(String nev) throws IOException {
        this.nev = nev;
        ujId();

        szakBeolvasasa();
        szakKiirasa();
        getTargyakKreditSzerint();
        getTargyakNevSzerint();
        statisztika();

        Szak prg = new Szak("nev");
        List<String> sorok = prg.szakBeolvasasa();
        prg.hozzaad(sorok);
    }

    public void ujId(){
        id = UUID.randomUUID();
    }

    public String getNev() {
        return nev;
    }

    private List<String> szakBeolvasasa() throws IOException {
        String fajl = "tantargyak.txt";
        Path hely = Path.of(fajl);
        List<String> sorok = Files.readAllLines(hely);
        System.out.println("beolvasott fájl sorai:");
        for (String sor : sorok) {
            System.out.println(sor);
        }
        return sorok;
    }

    private void hozzaad(List<String> sorok) {
        tantargyak = new ArrayList<>();
        for (String sor : sorok) {
            Tantargy tantargy = new Tantargy(sor);
            tantargyak.add(tantargy);
        }
    }

    public List<Tantargy> getTargyak(){
        return tantargyak;
    }

    private void szakKiirasa() throws IOException {
        Szak szak1= new Szak("gépészet");
        try(ObjectOutputStream objKi = new ObjectOutputStream(new FileOutputStream(TARGYAK))){
            objKi.writeObject(szak1);
            objKi.writeObject(tantargyak);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tantargy> getTargyakNevSzerint(){
        List<Tantargy> rendezett = new ArrayList<>(tantargyak);
        Collections.sort(rendezett);
        return rendezett;
    }

    public List<Tantargy> getTargyakKreditSzerint(){
        List<Tantargy> rendezett = new ArrayList<>(tantargyak);
        rendezett.sort(Comparator.comparingDouble(Tantargy::getKredit));
        return rendezett;
    }

    private int kulonbozoTargyak(){
        Set<Tantargy> kulonbozo = new HashSet<>();
        for (Tantargy tantargy : tantargyak) {
            kulonbozo.add(tantargy);
        }
        return kulonbozo.size();
    }

    private double osszKredit() {
        int osszes = 0;
        for (Tantargy tantargy : tantargyak) {
            osszes += tantargy.getKredit();
        }
        return osszes;
    }

    private void statisztika() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("különböz tárgy: ");
        stringBuilder.append(kulonbozoTargyak());
        stringBuilder.append('\'');
        stringBuilder.append(", összes kredit: ");
        stringBuilder.append(osszKredit());
        stringBuilder.append('\'');
        String adatok = stringBuilder.toString();
        String fajl = "statisztika.txt";
        Path cel = Path.of(fajl);
        Files.write(cel, adatok.getBytes());
    }
}
