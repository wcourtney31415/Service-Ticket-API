import static spark.Spark.get;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import spark.Spark;

public class App {

	private static DB database;

	public static void main(String[] args) {
		setupAPI();
		
		get("/ticket", (req, res) -> {
			res.type("application/json");
			DBCollection collection = database.getCollection("Ticket");
			String body = req.body();
			BasicDBObject query = (BasicDBObject) JSON.parse(body);
			List<DBObject> tickets = CRUD.read(collection, query);
			String json = tickets.toString();
			return json;
		});
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