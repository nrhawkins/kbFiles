package edu.washington.kb;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import java.io.IOException;


public class KBCreateColdStartDocList {

    public static void main(String[] args) throws IOException {

        System.out.println("KB Create Cold Start Loader File List: ");

        String inputFileName = "/projects/WebWare9/nhawkins/cs2015/csFileList";
        String outputFileName = "/projects/WebWare9/nhawkins/cs2015/csLoaderDocList";

        String allFilesDir = "/projects/WebWare6/KBP_2015/corpus/LDC2015E77_TAC_KBP_2015_English_Cold_Start_Evaluation_Source_Corpus/data/all/";


        try {                

            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF-8"));

            String l;
            int lineCount = 0;	    
//            while ( lineCount < 2 && (l = br.readLine()) != null ){
            while ( lineCount < 49124 && (l = br.readLine()) != null ){

               lineCount = lineCount + 1;
               System.out.println("l: " + l);
               if(!l.equals("")){
                   String docID = l.split("\\.")[0];
                   String docLine = docID + "\t" + allFilesDir + l + "\n"; 
                   bw.write(docLine);
               }
            }

	    System.out.println("LineCount: full count 49124: " + lineCount);

            br.close();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }   

}
