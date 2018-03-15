/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.atividade.neo4j;

import java.io.File;
import org.neo4j.graphdb.Direction;
import static org.neo4j.graphdb.DynamicLabel.label;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;



/**
 *
 * @author Jussara
 *

/**
 *
 * @author IFPB
 */
public class Cprincipal {
    public static void main(String[] args) {
        
        File file = new File("C:\\neo4j\\neo4j-community-3.3.3\\data\\databases\\graph.db");
        GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(file);
       
        try (Transaction tx = graphDB.beginTx()){
            
            
            
            usuario u1 = new usuario("@jacinta","1234","joão@gmail.com");
            
            usuario u2 = new usuario("@lucia","123","marta@gmail.com");
            
            usuario u3 = new usuario("@francisco","321","lucia@gmail.com");
            
            Node n1 = graphDB.createNode(label("usuario"));
            
            Node n2 = graphDB.createNode(label("usuario"));
            
            Node n3 = graphDB.createNode(label("usuario"));
            
            n1.setProperty("user", u1.getUser());
            n1.setProperty("senha", u1.getSenha());
            n1.setProperty("email", u1.getEmail());
            
            n2.setProperty("user", u2.getUser());
            n2.setProperty("senha", u2.getSenha());
            n2.setProperty("email", u2.getEmail());
            
            n3.setProperty("user", u3.getUser());
            n3.setProperty("senha", u3.getSenha());
            n3.setProperty("email", u3.getEmail());
             
            
            Publicacao pp1 = new Publicacao("boa tarde",1);
            Publicacao pp2 = new Publicacao("boa tarde",2);
            Publicacao pp3 = new Publicacao("boa tarde",3);
            Publicacao pp4 = new Publicacao("boa tarde",4);
            Publicacao pp5 = new Publicacao("boa tarde",5);
            Publicacao pp6 = new Publicacao("boa tarde",6);
            
            
            Node p1 = graphDB.createNode(label("publicacao"));
            Node p2 = graphDB.createNode(label("publicacao"));
            Node p3 = graphDB.createNode(label("publicacao"));
            Node p4 = graphDB.createNode(label("publicacao"));
            Node p5 = graphDB.createNode(label("publicacao"));
            Node p6 = graphDB.createNode(label("publicacao"));
            
             
            
           
            p1.setProperty("texto", pp1.getTexto());
            p1.setProperty("id", pp1.getId());
            
            p2.setProperty("texto", pp2.getTexto());
            p2.setProperty("id", pp2.getId());
            
            p3.setProperty("texto", pp3.getTexto());
            p3.setProperty("id", pp3.getId());
            
            p4.setProperty("texto", pp4.getTexto());
            p4.setProperty("id", pp4.getId());
            
            p5.setProperty("texto", pp5.getTexto());
            p5.setProperty("id", pp5.getId());
            
            p6.setProperty("texto", pp6.getTexto());
            p6.setProperty("id", pp6.getId());
            
            
             
            Node user1 = graphDB.findNode(Label.label("usuario"), "user", "@lucia");
            Node user2 = graphDB.findNode(Label.label("usuario"), "user", "@francisco");
            Node user3 = graphDB.findNode(Label.label("usuario"), "user", "@jacinta");
            
            
            user1.createRelationshipTo(user2, segue.seguindo);
            user1.createRelationshipTo(user3, segue.seguindo);
            user2.createRelationshipTo(user1, segue.seguindo);
            user2.createRelationshipTo(user3, segue.seguindo);
            user3.createRelationshipTo(user1, segue.seguindo);
            user3.createRelationshipTo(user2, segue.seguindo);
            
            Node texto1 = graphDB.findNode(Label.label("publicacao"), "id", 1);
            Node texto2 = graphDB.findNode(Label.label("publicacao"), "id", 2);
            Node texto3 = graphDB.findNode(Label.label("publicacao"), "id", 3);
            Node texto4 = graphDB.findNode(Label.label("publicacao"), "id", 4);
            Node texto5 = graphDB.findNode(Label.label("publicacao"), "id", 5);
            Node texto6 = graphDB.findNode(Label.label("publicacao"), "id", 6);
            
           
            user1.createRelationshipTo(texto1, publicar.publica);
            user2.createRelationshipTo(texto2, publicar.publica);
            user3.createRelationshipTo(texto3, publicar.publica);
            user1.createRelationshipTo(texto4, publicar.publica);
            user2.createRelationshipTo(texto5, publicar.publica);
            user3.createRelationshipTo(texto6, publicar.publica);
            
           /*ve suas publicaçoes*/
            for(Relationship rel : user1.getRelationships(publicar.publica)){
                System.out.println("publicaçoes");
                System.out.println(rel.getEndNode().getAllProperties());
                
            }
            System.out.println("-----------------------------");
            
            /*deixa de seguir*/
              for(Relationship rel : user1.getRelationships(segue.seguindo,Direction.OUTGOING)){
                  String s = (String) rel.getEndNode().getProperty("user");
                  String x = (String) user2.getProperty("user");
                    if(s == x){
                        System.out.println("deixar de segui "+rel.getEndNode().getAllProperties());
                        rel.delete();
                    }
                
            }
               System.out.println("-----------------------------"); 
               
               
            /*ve seus seguidores*/
             for(Relationship rel : user1.getRelationships(segue.seguindo,Direction.OUTGOING)){
                System.out.println("seguindo");
                System.out.println(rel.getEndNode().getAllProperties());
            }
             
            
            System.out.println("-----------------------------");
            
            /*exemplo de  retuita uma pubricaçao*/
            user1.createRelationshipTo(texto6, retuita.retuita);
            
            System.out.println("-----------------------------");
           
            /*ver publicaçoes dos seus seguidores*/
            for(Relationship rel : user1.getRelationships(segue.seguindo,Direction.OUTGOING)){
                for(Relationship a: rel.getEndNode().getRelationships(Direction.OUTGOING, publicar.publica)){
                    System.out.println(a.getEndNode().getProperty("texto"));
            }
                System.out.println(" -----");
                System.out.println(" -----");
                System.out.println(" -----");
                
            }
            
            tx.success();
            
        }finally{
            
            graphDB.shutdown();
            
        }
        
    }
  
}

