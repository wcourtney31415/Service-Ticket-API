import com.mongodb.BasicDBObject;

public class JSONObjectSanitizer {

	public static SanitaryObject sanitizeTicket(BasicDBObject unsafeObject, String[] safeKeys) {
		BasicDBObject safeObject = new BasicDBObject();
		for (String key : safeKeys) {
			if (unsafeObject.get(key) != null) {
				safeObject.put(key, unsafeObject.get(key));
			}
		}

		SanitaryObject sanitizedObject = new SanitaryObject(safeObject);

		return sanitizedObject;
	}

	static class SanitaryObject {
		private BasicDBObject sanitaryObject;

		private SanitaryObject(BasicDBObject safeObject) {
			sanitaryObject = safeObject;
		}

		public BasicDBObject retrieve() {
			return sanitaryObject;
		}
		
		public String toString() {
			return sanitaryObject.toString();
		}
		
	}

}