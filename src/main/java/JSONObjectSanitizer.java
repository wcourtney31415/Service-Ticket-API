import com.mongodb.BasicDBObject;

public class JSONObjectSanitizer {
	public static BasicDBObject sanitizeTicket(BasicDBObject unsafeObject) {
		BasicDBObject safeObject = new BasicDBObject();
		KeyValueCopier copier = new KeyValueCopier(safeObject, unsafeObject);
		copier.copy("firstName");
		copier.copy("lastName");
		copier.copy("dateIn");
		copier.copy("passwordBox");
		copier.copy("description");
		copier.copy("inventoryItems");
		copier.copy("phoneNumber");
		return safeObject;
	}
}

class KeyValueCopier {
	BasicDBObject safeObject, unsafeObject;

	KeyValueCopier(BasicDBObject safeObject, BasicDBObject unsafeObject) {
		this.safeObject = safeObject;
		this.unsafeObject = unsafeObject;
	}

	public void copy(String field) {
		if (unsafeObject.get(field) != null) {
			safeObject.put(field, unsafeObject.get(field));
		}
	}
}