package org.example.modell;

import java.util.Objects;

public class Tantargy implements Comparable<Tantargy>{
    private String nev;
    private int kredit;
    private String tanar1;
    private String tanar2;
    private int felev;
    private boolean csak_vizsga;

    public Tantargy(String nev, int kredit, String tanar1, String tanar2, int felev, boolean csak_vizsga) {
        this.nev = nev;
        this.kredit = kredit;
        this.tanar1 = tanar1;
        this.tanar2 = tanar2;
        this.felev = felev;
        this.csak_vizsga = csak_vizsga;
    }

    public Tantargy(String sor) {
        String[] s = sor.split(";");
        String nev = s[0];
        int kredit = Integer.parseInt(s[1]);
        String tanar1 = s[2];
        String tanar2 = s[3];
        int felev = Integer.parseInt(s[4]);
        boolean csak_vizsga = Boolean.parseBoolean(s[5]);
        this.nev = nev;
        this.kredit = kredit;
        this.tanar1 = tanar1;
        this.tanar2 = tanar2;
        this.felev = felev;
        this.csak_vizsga = csak_vizsga;
    }

    public String getNev() {
        return nev;
    }

    public int getKredit() {
        return kredit;
    }

    public String getTanar1() {
        return tanar1;
    }

    public String getTanar2() {
        return tanar2;
    }

    public int getFelev() {
        return felev;
    }

    public boolean isCsak_vizsga() {
        return csak_vizsga;
    }

    @Override
    public String toString() {
        return "Tantargy{" +
                "nev='" + nev + '\'' +
                ", kredit=" + kredit +
                ", tanar1='" + tanar1 + '\'' +
                ", tanar2='" + tanar2 + '\'' +
                ", felev=" + felev +
                ", csak_vizsga=" + csak_vizsga +
                '}';
    }

    @Override
    public int compareTo(Tantargy t){
        return this.nev.compareTo(t.nev);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tantargy tantargy = (Tantargy) o;
        return kredit == tantargy.kredit && felev == tantargy.felev && Objects.equals(nev, tantargy.nev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev, kredit, felev);
    }
}
