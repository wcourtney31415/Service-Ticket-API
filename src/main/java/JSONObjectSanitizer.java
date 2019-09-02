import com.mongodb.BasicDBObject;

public class JSONObjectSanitizer {

	public static BasicDBObject sanitizeTicket(BasicDBObject unsafeObject, String[] safeKeys) {
		BasicDBObject safeObject = new BasicDBObject();
		for (String key : safeKeys) {
			if (unsafeObject.get(key) != null) {
				safeObject.put(key, unsafeObject.get(key));
			}
		}
		return safeObject;
	}

}