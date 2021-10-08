package com.example.zilab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HelloController {

    @FXML
    private TextField p_inputText;
    @FXML
    private TextField q_inputText;
    @FXML
    private TextField d_Text;
    @FXML
    private TextField n_Text;
    @FXML
    private TextField m_Text;
    @FXML
    private TextField e_Text;
    @FXML
    private Button open_in_file_button;
    @FXML
    private Button open_out_file_button;

    @FXML
    protected void onEncryptButtonClick() throws IOException {
        BigInteger p, q, e, n, m, d;

        p = new BigInteger(p_inputText.getText());////100519  3583
        q = new BigInteger(q_inputText.getText());////77017  2591
        n = p.multiply(q);
        m = p.add(new BigInteger("-1")).multiply(q.add(new BigInteger("-1")));
        e = Find_e(m);
        d = Find_d(e, m);

        String content;
        ArrayList<Character> res = new ArrayList<>();

        content = new String(Files.readAllBytes(Paths.get("in.txt")));

        for (int i = 0; i < content.length(); i++)
        {
            BigInteger index = new BigInteger((Integer.toString(content.charAt(i))));
            index = index.pow(e.intValue()).mod(n);

            res.add((char)index.intValue());
        }


        ClearFile("out.txt");
        FileWriter fw = new FileWriter("out.txt", true);
        for (Character s: res) {
            fw.append(s);
        }

        fw.flush();
        d_Text.setText(d.toString());
        n_Text.setText(n.toString());
        e_Text.setText(e.toString());
        m_Text.setText(m.toString());

    }

    @FXML
    protected void onDecipherButtonClick() throws IOException{
        BigInteger n, d;
        d = new BigInteger(d_Text.getText());
        n = new BigInteger(n_Text.getText());

        String content;
        ArrayList<Character> res = new ArrayList<>();

        content = new String(Files.readAllBytes(Paths.get("out.txt")));

        for (int i = 0; i < content.length(); i++)
        {
            BigInteger index = new BigInteger((Integer.toString(content.charAt(i))));
            index = index.pow(d.intValue()).mod(n);

            res.add((char)index.intValue());
        }

        ClearFile("in.txt");
        FileWriter fw = new FileWriter("in.txt", true);
        for (Character s: res) {
            fw.append(s);
        }

        fw.flush();
    }

    @FXML
    protected void onGenerateButtonClick() throws IOException{
        p_inputText.setText(RandomPrime().toString());
        q_inputText.setText(RandomPrime().toString());
    }

    @FXML
    protected void onOpenInFileButtonClick() throws IOException {
        //File f = new File("in.txt");
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "in.txt");
        pb.start();
    }

    @FXML
    protected void onOpenOutFileButtonClick() throws IOException {
        //File f = new File("in.txt");
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "Out.txt");
        pb.start();
    }

    private void ClearFile (String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.close();
    }


    BigInteger RandomPrime() throws IOException {
        Random rand = new Random();
        int lineNumber = rand.nextInt(2000);
        return new BigInteger(Files.readAllLines(Paths.get("PrimeList.txt")).get(lineNumber));
    }


    private BigInteger Find_e(BigInteger m)
    {
        BigInteger e = m.add(new BigInteger("-1"));

        /*while (!e.gcd(m).equals(new BigInteger("1"))) {
            e = e.add(new BigInteger("-1"));
        }*/

        for (BigInteger i = new BigInteger("2"); i.compareTo(m.add(new BigInteger("1"))) != -1; i = i.add(new BigInteger("1")))
            if ((m.mod(e).equals(BigInteger.ZERO)) && (e.mod(i).equals(BigInteger.ZERO))) //если имеют общие делители
            {
                e = e.add(new BigInteger("-1"));
                i = new BigInteger("1");
            }

        return e;
    }

    private BigInteger Find_d(BigInteger e, BigInteger m)
    {
        BigInteger d = new BigInteger("10");

        while (true)
        {
            if (e.multiply(d).mod(m).equals(new BigInteger("1")))
                break;
            else
                d = d.add(new BigInteger("1"));
        }

        return d;
    }

}