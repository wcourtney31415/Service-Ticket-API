import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class App {

	public static void create(DBCollection collection) {
		BasicDBObject document = new BasicDBObject();
		document.put("firstName", "Bob");
		document.put("lastName", "Smith");
		document.put("dateIn", "2019-04-05");
		document.put("passwordBox", "#BestPasswordEver");
		document.put("description", "won't boot");
		document.put("phoneNumber", "5551235542");
		collection.insert(document);
	}

	public static List<DBObject> read(DBCollection collection, BasicDBObject query) {
		DBCursor cursor = collection.find(query);
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		while (cursor.hasNext()) {
			dbObjects.add(cursor.next());
		}
		return dbObjects;
	}

	public static void update(DBCollection collection, BasicDBObject query, BasicDBObject updates) {
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", updates);
		collection.updateMulti(query, updateObj);
	}

	public static void delete(DBCollection collection, BasicDBObject query) {
		collection.remove(query);
	}

	public static void main(String[] args) {
		try {
			String databaseAddress = "localhost";
			int databasePort = 27017;
			MongoClient mongoClient = new MongoClient(databaseAddress, databasePort);
			String databaseName = "ServiceTickets";
			DB database = mongoClient.getDB(databaseName);
			DBCollection collection = database.getCollection("Ticket");

			System.out.println("Done");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}
