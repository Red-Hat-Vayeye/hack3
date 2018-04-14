package models;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.*;

public class WordClassifier {

    public static String evalTweets(ArrayList<String> tweetsTxt, int maxQueue) throws Exception{
        Documents documents = new Documents();
        int j = 0;
        while(j<maxQueue && j<tweetsTxt.size()) {
            documents.add(Integer.toString(j+1),tweetsTxt.get(j));
            //System.out.println(documents.documents.get(j).id);
            //System.out.println(documents.documents.get(j).language);
            //System.out.println(documents.documents.get(j).text);
            j++;
        }
        return TextCommon.GetKeyPhrases(documents);
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> demo = new ArrayList<String>();
        demo.add("Me da hambre todos los días.");
        demo.add("Cambiaron de lugar este, no.");
        demo.add("Hector es bueno.");
        demo.add("Raul es el hijo de Zechinelli.");
        demo.add("Oye por que no le pides ayuda a un ingeniero en sistemas.");
        demo.add("Tres tristres tigres tragaban trigo en un trigal.");
        String demoRep = evalTweets(demo,100);
        System.out.println(TextCommon.prettify(demoRep));
    }
}