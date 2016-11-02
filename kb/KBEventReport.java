package edu.washington.kb;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;


public class KBEventReport {

    public static int reportCountsByType(String fileName){

        HashMap<String,Integer> typeCounts = new HashMap<String,Integer>();
        int typeCountsSize = 0;

        typeCounts.put("Attack", new Integer(0));
        typeCounts.put("Broadcast", new Integer(0));
        typeCounts.put("Business", new Integer(0));
        typeCounts.put("Correspondence", new Integer(0));
        typeCounts.put("Die", new Integer(0));
        typeCounts.put("Elect", new Integer(0));
        typeCounts.put("EmploymentMembership", new Integer(0));
        typeCounts.put("EndPosition", new Integer(0));
        typeCounts.put("Leadership", new Integer(0));
        typeCounts.put("Marry", new Integer(0));
        typeCounts.put("Meet", new Integer(0));
        typeCounts.put("MergeOrganization", new Integer(0));
        typeCounts.put("Nominate", new Integer(0));
        typeCounts.put("PersonAge", new Integer(0));
        typeCounts.put("Resident", new Integer(0));
        typeCounts.put("Sentiment", new Integer(0));
        typeCounts.put("SpousalRelationship", new Integer(0));
        typeCounts.put("StartPosition", new Integer(0));
        typeCounts.put("StudentAlum", new Integer(0));
        typeCounts.put("Sue", new Integer(0));
        typeCounts.put("TransferOwnership", new Integer(0));
        typeCounts.put("TransportPerson", new Integer(0));
        typeCounts.put("TransferMoney", new Integer(0));

        System.out.println("typeCounts size: " + typeCounts.size());
 
        try{

            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String l;
            int lineCount = 0;	    
            while ( (l = br.readLine()) != null ){
 
               lineCount++;
               //String[] lineFields = l.split(",");
               //System.out.println("line, first field: " + lineFields[0]);
               //System.out.println("line size: " + lineFields.length);
               String eventType = l.split(",")[0];                
               if(typeCounts.containsKey(eventType)){
                   //typeCounts.put(eventType, new Integer(1));
                   typeCounts.put(eventType, typeCounts.get(eventType)+1);
               } 
            }
            System.out.println("lineCount: " + lineCount); 
            System.out.println("typeCounts size: " + typeCountsSize);                     
            for(String key: typeCounts.keySet()){
                System.out.println(key + " " + typeCounts.get(key));
                typeCountsSize = typeCountsSize + typeCounts.get(key);
            }
                       


            br.close();

        }catch(Exception e){      
            e.printStackTrace();
        }

        return typeCountsSize;

    }

    public static void main(String[] args) throws IOException {

        System.out.println("KB EventReport: ");

        String inputDir = "/homes/gws/nhawkins/repo/deft/washington/src/main/resources/kb/kbreports/washington/";
        String relationsFileName = inputDir + "washington.relations.csv";
        String eventsFileName = inputDir + "washington.events.csv";

        int count = reportCountsByType(relationsFileName);
        System.out.println("Total Number of Relations: " + count);
        count = reportCountsByType(eventsFileName);
        System.out.println("Total Number of Events: " + count);
    }      


}
