package com.example.zilab2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HelloController {
    @FXML
    private Label labelE;
    @FXML
    private Label labelN;
    @FXML
    private Label labelD;
    @FXML
    private TextField inputText;
    @FXML
    private TextField outputText;

    BigInteger p, q, e, n, m, d;

    @FXML
    protected void onInCodeButtonClick() throws IOException {

        p = RandomPrime();
        q = RandomPrime();
        n = p.multiply(q);
        m = p.add(new BigInteger("-1")).multiply(q.add(new BigInteger("-1")));
        e = Calculate_E(p, q);
        //d = Euclid(p, q, e);

        //double k = (int)Math.log10(p*q)/Math.log10(2);
        char[] text = inputText.getText().toCharArray();
        char[] res = new char [text.length];
        int j = 0;
        for (int i =0;i < text.length; i ++){
            //res[j] = text[i];
            double c = (int) text[i];
            //double v = Math.pow(c, e);
            //res[i] = (char)(int)v;
        }

        //outputText.setText();
        labelE.setText(String.valueOf(e));
        labelN.setText(String.valueOf(n));
        labelD.setText(String.valueOf(m));

        //welcomeText.setText(line);

    }


    protected void onDeCodeButtonClick() {
        char[] text = inputText.getText().toCharArray();


    }





    double Euclid(double p, double q, double e){
        double m = (p - 1) * (q - 1);
        double d, y;
        double r = e % m;

        double[][] E = new double[][] {{0, 1}, {0, 1}};

        while (r != 0){
            E = MatrixMultiply(E, q);
            e = m;
            m = r;
            r = e % m;
        }

        return e;
    }

    double[][] MatrixMultiply(double[][] E, double q){
        double[][] M = new double[2][2];

        M[0][0] = E[0][0] * 0 + E[0][1] * 1;
        M[0][1] = E[0][0] * 1 + E[0][1] * q * -1;
        M[1][0] = E[1][0] * 0 + E[1][1] * 1;
        M[1][1] = E[1][0] * 1 + E[1][1] * q * -1;

        return M;
    }

    BigInteger RandomPrime() throws IOException {
        Random rand = new Random();
        int lineNumber = rand.nextInt(2000);
        return new BigInteger(Files.readAllLines(Paths.get("PrimeList.txt")).get(lineNumber));
    }

    BigInteger Calculate_E (BigInteger m, BigInteger n){
        BigInteger e = n.add(new BigInteger("-1"));
        while (e.gcd(m).equals(new BigInteger("1"))){
            e.add(new BigInteger("-1"));
        }
        return  e;
    }


    public double gcd(double a, double b) {
        if (b==0) return a;
        return gcd(b,a%b);
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