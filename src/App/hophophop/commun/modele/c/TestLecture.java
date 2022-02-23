package hophophop.commun.modele.c;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestLecture {
    public TestLecture() {
    }

    public static void main(String[] args) {
        Path source = Paths.get("tp1.test");
        new ArrayList();

        try {
            TestFonction t = null;
            Map<String, List<TestFonction>> mapTests = new HashMap();
            List<String> lesLignes = Files.readAllLines(source);
            Iterator var6 = lesLignes.iterator();

            String fct;
            while(var6.hasNext()) {
                fct = (String)var6.next();
                if (!fct.startsWith("#")) {
                    t = new TestFonction(fct);
                    if (mapTests.get(t.getFonction()) == null) {
                        mapTests.put(t.getFonction(), new ArrayList());
                    }

                    ((List)mapTests.get(t.getFonction())).add(t);
                }
            }

            var6 = mapTests.keySet().iterator();

            while(var6.hasNext()) {
                fct = (String)var6.next();
                System.out.print("\nTests de la fonction : " + fct);
                System.out.println(", Nombre de tests : " + ((List)mapTests.get(fct)).size());
                Iterator var8 = ((List)mapTests.get(fct)).iterator();

                while(var8.hasNext()) {
                    TestFonction test = (TestFonction)var8.next();
                    System.out.println("\t- " + test);
                }
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }
}
