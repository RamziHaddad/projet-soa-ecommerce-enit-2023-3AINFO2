package enit.soa;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

@QuarkusMain  
public class Main {

    public static void main(String ... args) {
       
        try {
            System.out.println("Running main method");
            Quarkus.run(args);
        } catch (Exception e) {
            e.printStackTrace(); // This will print the full stack trace
        }
    }
}
