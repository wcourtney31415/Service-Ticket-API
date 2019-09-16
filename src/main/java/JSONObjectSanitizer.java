import com.mongodb.BasicDBObject;

public class JSONObjectSanitizer {

	public static SanitaryObject sanitizeTicket(BasicDBObject unsafeObject, String[] safeKeys) {
		BasicDBObject safeObject = new BasicDBObject();
		for (String key : safeKeys) {
			if (unsafeObject.get(key) != null) {
				safeObject.put(key, unsafeObject.get(key));
			}
		}

		SanitaryObject so = new SanitaryObject(safeObject);

		return so;
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