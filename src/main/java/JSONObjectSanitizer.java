import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;

public class JSONObjectSanitizer {

	public static SanitaryObject sanitizeTicket(BasicDBObject unsafeObject, List<String> safeKeys) throws Exception {
		Set<String> fields = unsafeObject.keySet();
		for (String field : fields) {
			if (!safeKeys.contains(field)) {
				throw new Exception(field + " is an invalid field.");
			}
		}
		return new SanitaryObject(unsafeObject);
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