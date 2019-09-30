import static spark.Spark.get;
import static spark.Spark.post;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import spark.Request;
import spark.Spark;

public class App {

	private static DB database;

	public static void main(String[] args) {
		setupAPI();

		get("/ticket", (request, response) -> {
			response.type("application/json");
			DBCollection collection = database.getCollection("Ticket");
			Map<String, String> queryParams = queryParamsToMap(request);
			BasicDBObject query = new BasicDBObject(queryParams);
			List<String> safeKeys = Arrays.asList("dateIn");
			String myResponse;
			try {
				JSONObjectSanitizer.SanitaryObject safeQuery = JSONObjectSanitizer.sanitizeTicket(query, safeKeys);
				List<DBObject> tickets = CRUD.read(collection, safeQuery);
				myResponse = tickets.toString();
			}catch (Exception e) {
				myResponse = e.getMessage();
			}
			return myResponse;
		});

		get("/client", (request, response) -> {
			response.type("application/json");
			DBCollection collection = database.getCollection("Client");
			Map<String, String> queryParams = queryParamsToMap(request);
			BasicDBObject query = new BasicDBObject(queryParams);
			List<String> safeKeys = Arrays.asList("firstName", "lastName", "phoneNumber");
			String myResponse;
			try {
				JSONObjectSanitizer.SanitaryObject safeQuery = JSONObjectSanitizer.sanitizeTicket(query, safeKeys);
				List<DBObject> tickets = CRUD.read(collection, safeQuery);
				myResponse = tickets.toString();
			} catch (Exception e) {
				myResponse = e.getMessage();
			}
			return myResponse;
		});

		post("/client", (request, response) -> {
			DBCollection collection = database.getCollection("Client");
			String json = request.body();
			ObjectId objectId = CRUD.create(collection, json);
			return objectId;
		});

	}

	public static Map<String, String> queryParamsToMap(Request request) {
		Map<String, String> map = new HashMap<String, String>();
		request.queryParams().forEach(key -> {
			String value = request.queryParamsValues(key)[0];
			map.put(key, value);
		});
		return map;
	}

	private static void setupAPI() {
		setupSpark(80);
		setupMongoClient("localhost", 27017, "ServiceTickets");
	}

	private static void setupSpark(int port) {
		Spark.port(port);
	}

	private static void setupMongoClient(String address, int port, String databaseName) {
		final MongoClient mongoClient;
		try {
			mongoClient = new MongoClient(address, port);
			database = mongoClient.getDB(databaseName);
			System.out.println("DB Connection: Successful.");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

}