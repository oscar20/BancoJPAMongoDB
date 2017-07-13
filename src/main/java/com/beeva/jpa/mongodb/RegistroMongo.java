package com.beeva.jpa.mongodb;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.jpa.models.Cuenta;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RegistroMongo {
	MongoDBClass mongodb;
	
	public RegistroMongo(){
		ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
		mongodb = (MongoDBClass) context.getBean("mongoDBBean");
	}
	
	public void insertMongo(Cuenta c){
		
		MongoClient mclient = mongodb.getConexion();
		DB db = mclient.getDB("BDBanco");
		DBCollection table = db.getCollection("Datos");
		BasicDBObject doc = new BasicDBObject();
		doc.put("Mensaje", "Se registro una cuenta");
		doc.put("Id cuenta" , c.getIdcuenta());
		doc.put("Balance", c.getBalance());
		doc.put("Tipo cuenta", c.getTipocuenta().getNombre());
		doc.put("Fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
;		table.insert(doc);
	}
}
