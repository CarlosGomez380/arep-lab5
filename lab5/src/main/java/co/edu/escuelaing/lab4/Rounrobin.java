package co.edu.escuelaing.lab4;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Rounrobin {

    public static String get(String data,int port) throws UnirestException {
        int valor= 3 + port;

        HttpResponse<String> response = null;

        response = Unirest.get("http://ec2-34-230-83-190.compute-1.amazonaws.com:3500"+port+"/results?Cadena="+ data)
                .asString();

        return response.getBody();
    }

}
