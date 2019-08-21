import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class App {

	public static void main(String[] args) {
		try {
			String databaseAddress = "localhost";
			int databasePort = 27017;
			MongoClient mongoClient = new MongoClient(databaseAddress, databasePort);
			String databaseName = "ServiceTickets";
			DB database = mongoClient.getDB(databaseName);
			DBCollection collection = database.getCollection("Ticket");
			//Perform Operations
			System.out.println("Done");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}
