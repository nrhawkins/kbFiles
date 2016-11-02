package edu.washington.kb;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class KBConglesCorpus {

    public static void main(String[] args) throws IOException {

        System.out.println("KB Congle's Corpus, crawl: ");

//        try {                

//            String inputFileName = args[0];

//            Reader reader = new InputStreamReader(KBConglesCorpus.class.getResourceAsStream(inputFileName), "UTF-8");
//            BufferedReader br = new BufferedReader(reader);


//            String l;
//            int lineCount = 0;	    
//	    while ((l = br.readline()) != null){
//              lineCount = lineCount + 1;

//	       Gson gson = new Gson();
//	       JsonReader jr = new JsonReader(new StringReader(l));
//	       jr.setLenient(true);
//	       CrawlArticle article = gson.fromJson(jr, CrawlArticle.class);
//               System.out.println(article);

//            } 
//	    System.out.println("LineCount: " + lineCount);


            //com.google.gson.stream.JsonReader.setLenient(true);

//            Gson gson = new GsonBuilder().create(); 
//            reader.setLenient(true);
//            CrawlArticle article = gson.fromJson(br, CrawlArticle.class);
//            System.out.println(article);


//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }   

}
