package edu.washington.kb;

import java.io.IOException;
import java.lang.Boolean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.google.common.collect.ImmutableSet;

import adept.common.ChannelName;
import adept.common.ContentType;
import adept.common.Corpus;
import adept.common.Document;
import adept.common.Entity;
import adept.common.EntityMention;
import adept.common.HltContentContainer;
import adept.common.TokenizerType;
import adept.common.TokenOffset;
import adept.common.TokenStream;
import adept.common.TranscriptType;
import adept.common.Type;
import adept.utilities.DocumentMaker;

import adept.kbapi.KB;
import adept.kbapi.KBConfigurationException;
import adept.kbapi.KBEntity;
import adept.kbapi.KBEvent;
import adept.kbapi.KBOntologyMap;
import adept.kbapi.KBOntologyModel;
import adept.kbapi.KBProvenance;
import adept.kbapi.KBRelation;
import adept.kbapi.KBRelationArgument;
import adept.kbapi.KBParameters;
import adept.kbapi.KBTextProvenance;


import com.hp.hpl.jena.query.ResultSet;



public class KBStats {


	public static void main(String[] args) throws IOException, SQLException, KBConfigurationException {

		System.out.println("KB Stats: ");

                //System.out.println("args0: " + args[0]);

                boolean examine = Boolean.parseBoolean(args[0]);
                //boolean examine = false;

 		KBParameters kbParameters = new KBParameters();

		//System.out.println("Triplestore: " + kbParameters.tripleStoreUrl);
		//System.out.println("DB: " + kbParameters.metadataUrl);

                KB kb = new KB(kbParameters);

                                  
                try {

                // ---------------------------------------------------
                // Get All Entities, and Report Total
                // ---------------------------------------------------
                List<KBEntity> kbEntities = new ArrayList<KBEntity>();
		String regexString = ".";
                kbEntities = kb.getEntitiesByRegexMatch(regexString, false);
                System.out.println("Number of kbEntities: " + kbEntities.size());

                System.exit(0);                 


               // ---------------------------------------------------
                // Get All Relations, and Report Total
                // ---------------------------------------------------
                List<KBRelation> kbRelations = new ArrayList<KBRelation>();
		regexString = ".";
                kbRelations = kb.getRelationsByRegexMatch(regexString, false);
                System.out.println("Number of kbRelations: " + kbRelations.size());

                // ---------------------------------------------------
                // Get All Events, and Report Total
                // ---------------------------------------------------
                List<KBEvent> kbEvents= new ArrayList<KBEvent>();
		regexString = ".";
                kbEvents = kb.getEventsByRegexMatch(regexString, false);
                System.out.println("Number of kbEvents: " + kbEvents.size());


           if(examine){
                                
                // ---------------------------------------------------
                // Examine the Entities
                // ---------------------------------------------------
                for(KBEntity kbe : kbEntities){
                    System.out.println("entity: " + kbe.getKBID().getObjectID() +
                    " " + kbe.getKBID().getKBNamespace() +
                    //" " + kbe.getTypes().
                    " " + kbe.getCanonicalString()); 
                }


                // ---------------------------------------------------
                // Examine the Relations
                // ---------------------------------------------------
                for(KBRelation kbr : kbRelations){
                    System.out.println("rel: " + kbr.getKBID().getObjectID() +
                    " " + kbr.getKBID().getKBNamespace() +
                    " " + kbr.getType().getType()); 

                    ImmutableSet<KBRelationArgument> relArgs = kbr.getArguments();
                    for(KBRelationArgument arg : relArgs){
                        System.out.println("role: " + arg.getRole().getType());
                        //Set<KBProvenance> provenances = arg.getProvenances();
                        //System.out.println("prov size: " + provenances.size());
                        //for(KBProvenance kbp : provenances){
                        //    System.out.println("prov: " + kbp.getKBID().getObjectID());
                        //}
                    }
                }

     
                // ---------------------------------------------------
                // Examine the Events
                // ---------------------------------------------------
                for(KBEvent kbe : kbEvents){
                    System.out.println("event: " + kbe.getKBID().getObjectID() +
                    " " + kbe.getKBID().getKBNamespace() +
                    " " + kbe.getType().getType()); 

                    ImmutableSet<KBRelationArgument> eventArgs = kbe.getArguments();
                    for(KBRelationArgument arg : eventArgs){
                        System.out.println("role: " + arg.getRole().getType());
                    }
                }

          }

     
		// ---------------------------------------------------
                // Sparql Query
                // ---------------------------------------------------
//	        ResultSet rs = kb.executeSelectQuery("SELECT ?Entity");  
//                int count = 0;
//                while (rs.hasNext()){              
//                    rs.next();
//                    count++;
//                }
//                System.out.println("Sparql entities: " + count);


		// ---------------------------------------------------
                // Count Events By Event Name
                // ---------------------------------------------------
                KBOntologyModel model = KBOntologyModel.instance();
                Map<String, Map<String, String>> relations = model.getRelationArgumentTypes();                
                for (String relationName : relations.keySet()){
                   //List<KBEvent> events = kb.getEventsByType(new OntType(model, ontology, ontClass));
                   //System.out.println("Events size: " + relationName + ": " + events.size());
                   System.out.println("Events size: " + relationName + ": ");
                }

                
                } catch (Exception e) {
                    e.printStackTrace();
                }



        }   


}
