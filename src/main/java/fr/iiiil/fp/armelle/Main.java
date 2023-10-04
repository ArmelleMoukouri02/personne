package fr.iiiil.fp.armelle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        /*System.out.printf("Hello and welcome!");

        // Press Maj+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Maj+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);*/

        Path path = Paths.get ("src/main/resources/personnes.csv");

        try {
            Stream<String> lines = Files.lines(path).skip(1);
            Comparator <Personne> comparator = Comparator.comparing(Personne::getAge);
            Comparator <Personne> comparator1 = Comparator.comparing(Personne::getPrenom);

            List<Personne> personnes = lines.map(l-> {
                String[] tableline = l.split(",");
                String prenom = tableline[0];
                int age = Integer.valueOf(tableline[1].trim());
                return new Personne(prenom, age);

            })
                    //.sorted((Personne p1, Personne p2) -> p1.getAge() > p2.getAge() ? 1: p1.getAge() ==  p2.getAge() ? 0: -1 )

                    .sorted(comparator.thenComparing(comparator.reversed()))
                    .sorted(comparator1.thenComparing(comparator1.reversed()))
                    .toList();
            
            System.out.println(personnes);
        }
        catch (IOException e){

        }
    }
}