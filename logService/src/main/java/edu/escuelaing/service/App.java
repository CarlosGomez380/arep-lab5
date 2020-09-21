package edu.escuelaing.service;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(getPort());
        get("/results", (req, res) -> resultsPage(req, res));
    }


    private static JSONObject resultsPage(Request req, Response res) {
        System.out.println(req.queryParams("Cadena"));
        LogService logService= new LogService();
        return logService.messageLog(req.queryParams("Cadena"));
    }


    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}

