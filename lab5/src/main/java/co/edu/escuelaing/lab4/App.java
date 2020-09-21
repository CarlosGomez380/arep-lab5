package co.edu.escuelaing.lab4;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class App {

    static int i=0;

    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getPort());
        get("/inputdata", (req, res) ->  {res.redirect("/index.html");return null;});
        get("/results", (req, res) -> resultsPage(req, res));
    }


    private static JSONObject resultsPage(Request req, Response res) throws UnirestException {
        System.out.println(req.queryParams("Cadena"));

        i=(i>2)?0:i;

        System.out.println(i);
        JSONObject jsonObject = new JSONObject(Rounrobin.get(req.queryParams("Cadena"),i));
        i++;
        return jsonObject;
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
