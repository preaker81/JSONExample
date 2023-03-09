import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println();

//      Skapa ett JSON object

        JSONObject jsonOb = new JSONObject();
        jsonOb.put("namn", "Jimmy");
        jsonOb.put("age", 42);

//      Skriva ut värden

        System.out.println("Mitt namn är: " + jsonOb.get("namn"));
        System.out.println("Jag är " + jsonOb.get("age") + " år gammal.");
        System.out.println("------------------------------------------------------");

//      Hämta data från en JSON fil

        Object o = new JSONParser().parse(new FileReader("C:\\git\\Test\\intelliJProjekt\\JSONExample\\src\\data.json"));
        JSONObject jsonData = (JSONObject) o;

        JSONObject person = (JSONObject) jsonData.get("p1");
        String name= (String) person.get("name");

        System.out.println("P1 Name :" + name);

        fetchJsonFromFile();

        fetchJsonFromAPI();
    }

//    Funktion som gör motsvarande som ovan
    static void fetchJsonFromFile() throws IOException, ParseException{
        String filepath = "C:\\git\\Test\\intelliJProjekt\\JSONExample\\src\\data.json";

//        Hämta data från JSON fil
        JSONObject fetchData = (JSONObject) new JSONParser().parse(new FileReader(filepath));

//        Konvertera data till ett JSONObject
        JSONObject p1 = (JSONObject) fetchData.get("p1");
        JSONObject p2 = (JSONObject) fetchData.get("p2");

//       Hämta och skriva ut data
        String nameP1 = p1.get("name").toString();
        String nameP2 = p2.get("name").toString();
        int ageP1 = Integer.parseInt(p1.get("age").toString());
        int ageP2 = Integer.parseInt(p2.get("age").toString());

        System.out.println("Mitt namn är " + nameP1 + " och jag är " + ageP1 + " år gammal.");
        System.out.println("Mitt namn är " + nameP2 + " och jag är " + ageP2 + " år gammal.");
    }

    static  void fetchJsonFromAPI() throws IOException, ParseException {
//Spara URL till API
        URL url = new URL("https://api.wheretheiss.at/v1/satellites/25544");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect(); // Utför connection
        if (conn.getResponseCode() == 200) System.out.println("Connection successful");
        else System.out.println("Connection failed");

        StringBuilder strData = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            strData.append(scanner.nextLine());
        }
        scanner.close();

        JSONObject dataObject = (JSONObject) new JSONParser().parse(String.valueOf(strData));

        String speed = (String) dataObject.get("velocity").toString();
        String speedUnit = (String) dataObject.get("units");
        System.out.println("The speed is " + speed + " " + speedUnit + " an hour");
    }
}