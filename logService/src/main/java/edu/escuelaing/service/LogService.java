package edu.escuelaing.service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class LogService {

    public static JSONObject messageLog(String mensaje){
        System.out.println("Prueba conexión MongoDB");

        MongoClient mongo = crearConexion();
        System.out.println(mongo);
        if (mongo != null) {

            //Si no existe la base de datos la crea
            MongoDatabase db = mongo.getDatabase("laboratorio4");

            //Crea una tabla si no existe y agrega datos
            MongoCollection<Document> table= db.getCollection("Data");

            //Crea los objectos básicos
            Document data= new Document();
            data.append("info", mensaje);
            data.append("fecha", new Date());

            //Insertar tablas
            table.insertOne(data);

            //Listar la tabla "trabajador"
            System.out.println("Listar los registros de la tabla: ");

            return selectTablas(db, "Data");


        } else {
            System.out.println("Error: Conexión no establecida");
            return null;
        }
    }

    /**
     * Clase para crear una conexión a MongoDB.
     * @return MongoClient conexión
     */
    private static MongoClient crearConexion() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("172.17.0.2", 27017);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongo;
    }


    /**
     * Ejemplo que imprime por pantalla todos los trabajadores
     * @param db
     * @param tabla
     */
    private static JSONObject selectTablas(MongoDatabase db, String tabla) {
        ArrayList<String> listaInfo= new ArrayList<>();
        ArrayList<String> listaFecha= new ArrayList<>();
        //Recoge datos de la tabla
        MongoCollection<Document> table= db.getCollection(tabla);
        System.out.println(table.countDocuments());
        //Busca y muestra todos los datos de la tabla
        FindIterable<Document> cur = table.find();
        for (Document document : cur) {
            listaInfo.add(document.get("info").toString());
            listaFecha.add(document.get("fecha").toString());
            System.out.println(document.get("info") + " " + document.get("fecha"));
        }
        if(listaFecha.size()<11){
            return jsonAdd(listaInfo,listaFecha,0,listaFecha.size());
        }else{
            return jsonAdd(listaInfo,listaFecha,listaFecha.size()-10,listaFecha.size());
        }

    }

    public static JSONObject jsonAdd(ArrayList<String> listaInfo,ArrayList<String> listaFecha,int start, int end){
        JSONObject json= new JSONObject();
        for(int i=start; i<end;i++){
            JSONObject data=new JSONObject();
            data.append("info",listaInfo.get(i));
            data.append("fecha",listaFecha.get(i));
            json.append("Data",data);
        }
        return json;
    }
}
