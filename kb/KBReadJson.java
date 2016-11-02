package edu.washington.kb;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.BufferedWriter;
//import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
//import java.net.URL;
//import java.net.URLConnection;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class KBReadJson {

    public static void main(String[] args) throws IOException {

        System.out.println("KB ReadJson: ");

        //Class cls = new KBReadJson().getClass();
        //String testOutputDir = "/" + cls.getPackage().getName().replace(".","/") + "/crawlFiles/";
        //System.out.println("testOutputDir: " + testOutputDir);
        //System.out.println("File Separator: " + File.separator);

        //String outputDir = "/homes/gws/nhawkins/repo/deft/washington/src/main/resources/edu/washington/kb/crawlFiles/";
        //String outputDir = "/homes/gws/nhawkins/crawlFiles/";
        String outputDir = "/projects/WebWare9/nhawkins/crawlCorpus/500k/";
        String filesWritten = "/projects/WebWare9/nhawkins/crawlFileLists/crawlFilesWritten_500k";
        //String outputDir = "/projects/WebWare9/nhawkins/crawlCorpus/test/";
        //String filesWritten = "/projects/WebWare9/nhawkins/crawlFileLists/crawlFilesWritten_test";

        try {                

            String inputFileName = args[0];
            System.out.println("inputFileName: " + inputFileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(KBReadJson.class.getResourceAsStream(inputFileName), "UTF-8"));
            BufferedWriter bw = null;
            BufferedWriter bwFiles = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filesWritten), "UTF-8"));


            String l;
            int lineCount = 0;	    
//            while ( lineCount < 2 && (l = br.readLine()) != null ){
//            while ( lineCount < 28360 && (l = br.readLine()) != null ){

            while ( lineCount < 546713 && (l = br.readLine()) != null ){
//            while ( lineCount < 420001 && (l = br.readLine()) != null ){
//            while ( lineCount < 315001 && (l = br.readLine()) != null ){
//            while ( lineCount < 205001 && (l = br.readLine()) != null ){
//            while ( lineCount < 115501 && (l = br.readLine()) != null ){
//            while ( lineCount < 105001 && (l = br.readLine()) != null ){
//            while ( lineCount < 94501 && (l = br.readLine()) != null ){
//            while ( lineCount < 84001 && (l = br.readLine()) != null ){
//            while ( lineCount < 73501 && (l = br.readLine()) != null ){
//            while ( lineCount < 63001 && (l = br.readLine()) != null ){
//            while ( lineCount < 52501 && (l = br.readLine()) != null ){
//            while ( lineCount < 42001 && (l = br.readLine()) != null ){
//            while ( lineCount < 31501 && (l = br.readLine()) != null ){
//            while ( lineCount < 21001 && (l = br.readLine()) != null ){
//            while ( lineCount < 10501 && (l = br.readLine()) != null ){
//            while ( lineCount < 546713 && (l = br.readLine()) != null ){
//	      while ((l = br.readLine()) != null){
              lineCount = lineCount + 1;
              
//              if(lineCount > 28340){
              if(lineCount > 420001){

	       Gson gson = new Gson();
	       JsonReader jr = new JsonReader(new StringReader(l));
	       //jr.setLenient(true);
	       CrawlArticle article = gson.fromJson(jr, CrawlArticle.class);

               String articleName = outputDir + article.getName();
               //String articleName = testOutputDir + article.getName() + "test";

               if(articleName != null){
                   String articleText = article.getText();
                   //System.out.println("article: " + articleName + " text length: " + articleText.length());
                   boolean binaryFile = false;
                   //if(articleText.contains("\uFFFD")) System.out.println("article contains uFFFD"); 
                   //if(articleText.contains("\u00ef")) System.out.println("article contains u00ef"); 
                   //if(articleText.contains("\u00bf")) System.out.println("article contains u00bf"); 
                   //if(articleText.contains("\u00bd")) System.out.println("article contains u00bd"); 
                   //System.out.println("text: " + articleText);
                   if(articleText != null && articleText.length()!=0){
                      if(articleText.contains("\uFFFD") && articleText.contains("\u00ef") &&  
                         articleText.contains("\u00bf") && articleText.contains("\u00bd")) binaryFile = true;
                      if(!binaryFile){
                         //articleText = Pattern.compile("[\\s]{2,}").matcher(articleText).replaceAll(" ");
                         //articleText = articleText.replace("\n", " ");
                         //articleText = articleText.replace("\r", " ");
                         articleText = "<DOC id=\"" + article.getName() + "\"><TEXT>" + articleText + "</TEXT></DOC>"; 
                         bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(articleName), "UTF-8"));
                         bw.write(articleText);
                         bwFiles.write(article.getName() + "\n");
                         System.out.println("Wrote: " + articleName);
                         bw.close(); 
                      }                    
                   }
               }
               jr.close();

           } 

           } // if

	    System.out.println("LineCount: full count 546713: " + lineCount);

            br.close();
            bwFiles.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        KBReadJson c = new KBReadJson();
//        Class cls = c.getClass();

//        URL url = cls.getResource("urlCrawlTest.txt");
        // this only creates the URL, but doesn't check if it really exists
        //and then try open it for writing
//        URLConnection connection = url.openConnection();
//        connection.setDoOutput(true);
//        OutputStream outputStream = connection.getOutputStream();

//        BufferedWriter bw2 = null;
//        bw2 = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//        bw2.write("Testing URL Crawl");
//        bw2.close();

        //System.out.println("URL url: " + url);
        //System.out.println("URL path: " + url.getPath());
        //System.out.println("URL file: " + url.getFile());

    }   

}
