package edu.washington.kb;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import java.io.IOException;


public class KBCreateCorpus {

    public static void main(String[] args) throws IOException {

        System.out.println("KB Create Corpus: ");

        String textInputFileName = "/projects/WebWare9/nhawkins/nsre/text";
        String outputDir = "/projects/WebWare9/nhawkins/nsCorpus/";


        try {                

            BufferedReader br = new BufferedReader(new FileReader(textInputFileName));
            BufferedWriter bw = null;

            String l;
            int lineCount = 0;	    
            while ( lineCount < 1445 && (l = br.readLine()) != null ){

               lineCount = lineCount + 1;
               String articleName = "nsCorpus:" + lineCount;
               System.out.println("l: " + l);
               if(!l.equals("")){
                   String articleText = "<DOC id=\"" + articleName + "\"><TEXT>" + l + "</TEXT></DOC>"; 
                   bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputDir+articleName), "UTF-8"));
                   bw.write(articleText);
                   bw.close();
               }
               System.out.println("Wrote: " + articleName);
            }

	    System.out.println("LineCount: full count 1445: " + lineCount);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }   

}
