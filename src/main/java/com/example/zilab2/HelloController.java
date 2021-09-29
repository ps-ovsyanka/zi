package com.example.zilab2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        Long p, q, n;
        //welcomeText.setText(RandomPrime().toString());

        try(FileWriter writer = new FileWriter("PrimeList.txt", true))
        {
            for (BigInteger i = new BigInteger("100000000000"); i.compareTo(new BigInteger("200000000000")) == -1; i = i.add(new BigInteger("1"))){
                String res = isPrime(i) ? i.toString() : "";
                if (res != ""){
                    System.out.println(res);
                    writer.write(res);
                    writer.append('\n');
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    /*BigInteger RandomPrime(){
        Random rand = new Random();
        Long n = rand.nextLong() + 100000000000L;
        if (n < 0)
            n *= -1;
        while(!isPrime(n)){
            n += 1;
        }
        return BigInteger.valueOf(n);
    }*/

    public static boolean isPrime(BigInteger x){
        for (BigInteger i = new BigInteger("2"); i.compareTo(x.sqrt().add(new BigInteger("1"))) == -1; i = i.add(new BigInteger("1"))){
            if (x.mod(i) == BigInteger.ZERO) {
                return false;
            }
        }
        return true;
    }

}