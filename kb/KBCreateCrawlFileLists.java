package edu.washington.kb;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import java.io.IOException;


public class KBCreateCrawlFileLists {

    public static void main(String[] args) throws IOException {

        System.out.println("KB Create Crawl File Lists: ");
 
        // English Gigaword: afp_eng_2008
        String fileNames = "/projects/WebWare9/nhawkins/crawlFileLists/afp_eng_2008Files";
        String outputDir = "/projects/WebWare9/nhawkins/crawlFileLists/afp_eng_2008/100/";

        // Cold Start 2015, 40186 docs
        // /projects/WebWare6/KBP_2015/corpus/LDC2015E77_TAC_KBP_2015_English_Cold_Start_Evaluation_Source_Corpus/data/mpdf/
        //String fileNames = "/projects/WebWare9/nhawkins/crawlFileLists/mpdfColdStart2015Files";
        //String outputDir = "/projects/WebWare9/nhawkins/crawlFileLists/mpdf/100/";

        //String fileNames = "/projects/WebWare9/nhawkins/crawlFileLists/crawlFilesWritten_200k";
        //String outputDir = "/projects/WebWare9/nhawkins/crawlFileLists/200k/100/";
              
        int batchSize = 100;
        int numBatches = 2260;
        //int numBatches = 403;
        //int numBatches = 1000;

        String batchName = "afp_eng_2008Batch:";
        //String batchName = "mpdfBatch:";
        //String batchName = "crawlBatch:";


        try {                

            BufferedReader br = new BufferedReader(new FileReader(fileNames));
            BufferedWriter bw = null;

            String l;
            int lineCount = 0;	 

            for(int batchCount=1; batchCount<numBatches; batchCount++){
                String batchFile = outputDir + batchName + batchCount;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(batchFile), "UTF-8"));
                for(int batchLine=0; batchLine<batchSize; batchLine++){
                    if((l = br.readLine()) != null ){
                        bw.write(l + "\n");
                    }
                }
                bw.close();       

	    System.out.println("Wrote Batch: full count " + numBatches + ": " + batchCount);

            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }   

}
