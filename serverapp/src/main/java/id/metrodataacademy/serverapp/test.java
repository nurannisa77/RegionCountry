package id.metrodataacademy.serverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class test {

    public static void main(String[] args) {
    SpringApplication.run(test.class, args);

    try {
      
      System.out.println(" Koneksi ke database berhasil ");
    } catch (Exception e) {
     
      System.out.println("Error + " + e.getMessage());
    }
  }
}
    

