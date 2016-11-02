package edu.washington.kb;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import java.io.IOException;


public class KBCreatePBSFiles {

    public static void main(String[] args) throws IOException {

        System.out.println("KB Create PBS Files: ");

        String corpusDir = "/projects/WebWare9/nhawkins/afpCorpus/afp_eng_2008/";
        String crawlBatchDir = "/projects/WebWare9/nhawkins/crawlFileLists/afp_eng_2008/100/";
        String outputDir = "/projects/WebWare9/nhawkins/cluster/afp_eng_2008/100/";
        String crawlBatchName = "afp_eng_2008Batch_";
        String batchName = "afp_eng_2008Batch:";

        //String corpusDir = "/projects/WebWare6/KBP_2015/corpus/LDC2015E77_TAC_KBP_2015_English_Cold_Start_Evaluation_Source_Corpus/data/mpdf/";
        //String crawlBatchDir = "/projects/WebWare9/nhawkins/crawlFileLists/mpdf/100/";
        //String outputDir = "/projects/WebWare9/nhawkins/cluster/mpdf/100/";
        //String crawlBatchName = "mpdfBatch_";
        //String batchName = "mpdfBatch:";

        //String corpusDir = "/projects/WebWare9/nhawkins/crawlCorpus/200k/";
        //String crawlBatchDir = "/projects/WebWare9/nhawkins/crawlFileLists/200k/100/";
        //String outputDir = "/projects/WebWare9/nhawkins/cluster/200k/100/";
        //String crawlBatchName = "crawlBatch200k_";
        //String batchName = "crawlBatch:";

        String crawlOutDir = "/projects/WebWare9/nhawkins/crawlOut/";
        String crawlBatchExtension = ".pbs";
        String cdRunDir = "cd /homes/gws/nhawkins/repo/deft";
        String mvnStartCmd = "mvn exec:java -pl washington -Dexec.mainClass=\"edu.washington.WashingtonNewsSpikeExtractorAppKB\" -Dexec.args=\"--input_directory ";
        String mvnMiddleCmd = " -l ";
        String mvnEndCmd = " --output_directory ";
               
        int pbsBatchSize = 20;

        // Number of files in crawlBatchDir 
        int numBatchFileLists = 2260;
        //int numBatchFileLists = 402;
        //int numBatchFileLists = 855;
        //int numBatchFileLists = 919;
        //int numBatchFileLists = 999;
        //int numBatchFileLists = 1204;

        try {                

            int callsWritten = 0;
            int pbsBatch = 0;
            // ------------------------------------------------------------------------------
            // write pbsBatchSize calls per .pbs batch, have numBatches/batchSize pbs files to create
            // ------------------------------------------------------------------------------
            while(callsWritten < numBatchFileLists){

               pbsBatch++;
               String pbsFileName = outputDir + crawlBatchName + pbsBatch + crawlBatchExtension;
               BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pbsFileName), "UTF-8"));

               for(int batchCount=1; batchCount <= pbsBatchSize; batchCount++){
                  int crawlBatchNum = callsWritten + batchCount;
                  if(crawlBatchNum <= numBatchFileLists){
                     if(batchCount==1){
                        bw.write("#!/bin/bash" + "\n");
                        bw.write(cdRunDir + "\n");
                     }
                     String l = mvnStartCmd + corpusDir + mvnMiddleCmd + crawlBatchDir + batchName + crawlBatchNum + mvnEndCmd + crawlOutDir + "\"";
                     bw.write(l + "\n");
                  }
               }
                   
               bw.close();       

	       System.out.println("Wrote Batch File: " + pbsFileName);

               callsWritten = callsWritten + pbsBatchSize;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }   

}
