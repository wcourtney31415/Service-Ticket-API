import static spark.Spark.get;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import spark.Spark;

public class App {

	private static DB database;

	public static void main(String[] args) {
		setupServer();
		
		get("/ticket", (req, res) -> {
			res.type("application/json");
			DBCollection collection = database.getCollection("Ticket");
			String json = CRUD.read(collection).toString();
			return json;
		});
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

	private static void setupServer() {
		setupSpark(80);
		setupMongoClient("localhost", 27017, "ServiceTickets");
	}
}