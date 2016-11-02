package edu.washington.kb;
//package adept.uw;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import adept.kbapi.KBOntologyMap;
import adept.kbapi.KBRelation;
import adept.kbapi.KBParameters;



public class KBExample {


	public static void main(String[] args) throws IOException, SQLException, KBConfigurationException {

		System.out.println("KB Example: Running the edu.washington.kb.KBExample class ");

 		KBParameters kbParameters = new KBParameters();

		System.out.println("Triplestore: " + kbParameters.tripleStoreUrl);
		System.out.println("DB: " + kbParameters.metadataUrl);

                KB kb = new KB(kbParameters);
                  
                String sentenceBO = "Barack Obama was born in Honolulu, Hawaii.";               
                String filenameBO = "/homes/gws/nhawkins/kb/deft/adept/adept-kb/src/main/java/adept/uw/documentBO";
 		Entity entityBO = new Entity(1,new Type("per"));
                long entityIdBO = entityBO.getEntityId();
                System.out.println("entityBO: " + entityBO.getEntity());
                System.out.println("entityBO id: " + entityIdBO);
                Corpus corpusBO = new Corpus("testCorpus1","newswire","testCorpus","testCorpusURI");
                HltContentContainer hltcc = new HltContentContainer();
                Document documentBO = DocumentMaker.getInstance().createDefaultDocument(
                                "001",
                                corpusBO,
                                "Text",
                                filenameBO,
                                "English",
                                filenameBO,            // filename
                                hltcc,
                TokenizerType.STANFORD_CORENLP);
//                Document documentBO = new Document("doc1",corpusBO,"docType","docURI","english");
//                documentBO.setValue(sentenceBO);
                System.out.println("document value: " + documentBO.getValue());              
                TokenStream tokenStreamBO = documentBO.getDefaultTokenStream();
                //The call to TokenStream below adds the token stream to the document, and
                //sets the tokenStream textValue to the document value
//                TokenStream tokenStreamBO = new TokenStream(TokenizerType.STANFORD_CORENLP,TranscriptType.SOURCE,"english",
//                   ChannelName.NONE,ContentType.TEXT,documentBO);
                System.out.println("Token Stream: " + tokenStreamBO.getTextValue());
                System.out.println("Token Stream size: " + tokenStreamBO.size());
//                documentBO.setDefaultTokenStream(tokenStreamBO);                
                EntityMention entityMentionBO = new EntityMention(1,new TokenOffset(0,1),tokenStreamBO);
                System.out.println("Created entityMentionBO");
                entityMentionBO.addEntityConfidencePair(entityIdBO, 1);
                System.out.println("entityMentionBO confidence: " + entityMentionBO.getConfidence(entityIdBO));
                List<EntityMention> entityMentionsBO = new ArrayList();
                entityMentionsBO.add(entityMentionBO);
                System.out.println("entityMentionsBO size: " + entityMentionsBO.size());
                entityBO.setCanonicalMentions(entityMentionBO);  

                try{
                        List<KBEntity> entitiesBO = kb.getEntitiesByStringReference("Barack Obama");
	        	List<KBRelation> relationsBorn = kb.getRelationsByStringReference("born");
	        	List<KBRelation> relationsResident = kb.getRelationsByStringReference("grew");
                        
                
        	        if(entitiesBO == null || entitiesBO.size() == 0) {
				System.out.println("No BO entities in the KB.");

                                System.out.println("Creating insertion builder");

                                //insert Barack Obama entity
                                KBEntity.InsertionBuilder insertionBuilder = 
				    KBEntity.entityInsertionBuilder(entityBO, entityMentionsBO, KBOntologyMap.getTACOntologyMap());

                                System.out.println("Inserting BO entity");

                                KBEntity insertedEntity = insertionBuilder.insert(kb);

				System.out.println("Inserted BO entity in the KB.");  
                	}
                	else {
				System.out.println("Number of BO entities: " + entitiesBO.size());
                	}

        	        if(relationsBorn == null || relationsBorn.size() == 0) {
				System.out.println("No born relations in the KB.");
                	}
                	else {
				System.out.println("Number of born relations: " + relationsBorn.size());
                	}                        
        	        if(relationsResident == null || relationsResident.size() == 0) {
				System.out.println("No Resident relations in the KB.");
                	}
                	else {
				System.out.println("Number of Resident relations: " + relationsResident.size());
                	}                        






                }
                catch(Exception e){
                	System.out.println(e);
                }              

        }


}
