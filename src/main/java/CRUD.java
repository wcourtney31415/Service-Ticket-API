import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CRUD {
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

}
