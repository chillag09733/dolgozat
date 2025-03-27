package java.org.example;

import org.example.modell.Szak;
import org.example.modell.Tantargy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


class MainTest {
    Szak szak;
    @BeforeEach
    void ini() throws IOException {
        szak = new Szak("egy szak neve");
    }

    @Test
    void testSzakTargyNevek(){
        for (Tantargy tantargy : szak.tantargyak) {
            Assertions.assertTrue(tantargy.getNev().length() > 3);
        }
    }

    @Test
    void testGetTargyak(){
        List<Tantargy> targyak = szak.getTargyak();
        int eredeti = targyak.size();
        targyak.add(new Tantargy());
        Assertions.assertTrue(eredeti == szak.getTargyak().size());
    }

    @Test
    void testAzonosTanarok(){
        List<Tantargy> targyak = szak.getTargyak();

    }

    @Test
    void testKreditErteke(){
        List<Tantargy> targyak = szak.getTargyak();
        for (Tantargy i : targyak) {
            int kredit = i.getKredit();
            if(kredit < 1 || kredit > 5) {
                try {
                    throw new ClassNotFoundException();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}