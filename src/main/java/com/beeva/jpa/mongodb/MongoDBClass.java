package com.beeva.jpa.mongodb;

import java.net.UnknownHostException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.MongoClient;

public class MongoDBClass {
	
	private int port;
	private String host;
	
	MongoClient mongo_cliente;
	
	public MongoClient getConexion(){
		
		try {
			mongo_cliente = new MongoClient(host,port);
			
		} catch (UnknownHostException e) {
			System.out.println("Error con conexion en Mongo :(");
		}
		
		return mongo_cliente;
	}
	
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	
	

}
