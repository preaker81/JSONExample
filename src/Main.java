import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println();

//        Skapa ett JSON object
        JSONObject jsonOb = new JSONObject();
        jsonOb.put("namn", "Jimmy");
        jsonOb.put("age", 42);

//        Skriva ut värden
        System.out.println("Mitt namn är: " + jsonOb.get("namn"));
        System.out.println("Jag är " + jsonOb.get("age") + " år gammal.");
    }
}