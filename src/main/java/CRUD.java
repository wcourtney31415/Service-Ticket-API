import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CRUD {
	public static ObjectId create(DBCollection collection, String json) {
		BasicDBObject document = (BasicDBObject) JSON.parse(json);
		collection.insert(document);
		ObjectId id = (ObjectId)document.get( "_id" );
		return id;
	}

	// Began adding a Property santizer for this, but noticed that if i provide a
	// JSON object like:
	// {"sdfsd":"sdaga"} into the body of the request, that my read method spits out
	// all of the tickets
	// This needs addressed.

	public static List<DBObject> read(DBCollection collection, JSONObjectSanitizer.SanitaryObject query) {
		BasicDBObject safeQuery = query.retrieve();

		List<DBObject> dbObjects = find(collection, safeQuery);
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

	private static List<DBObject> find(DBCollection collection, BasicDBObject query) {
		DBCursor cursor = collection.find(query);
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		while (cursor.hasNext()) {
			dbObjects.add(cursor.next());
		}
		return dbObjects;
	}

}
