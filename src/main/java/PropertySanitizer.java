import com.mongodb.BasicDBObject;

public class PropertySanitizer {
	public static BasicDBObject sterilizeTicket(BasicDBObject unsafeObject) {
		BasicDBObject safeObject = new BasicDBObject();
		Copier copier = new Copier(safeObject, unsafeObject);
		copier.copyField("firstName");
		copier.copyField("lastName");
		copier.copyField("dateIn");
		copier.copyField("passwordBox");
		copier.copyField("description");
		copier.copyField("inventoryItems");
		copier.copyField("phoneNumber");
		return safeObject;
	}
}

class Copier {
	BasicDBObject safeObject, unsafeObject;

	Copier(BasicDBObject safeObject, BasicDBObject unsafeObject) {
		this.safeObject = safeObject;
		this.unsafeObject = unsafeObject;
	}

	public void copyField(String field) {
		if (unsafeObject.get(field) != null) {
			safeObject.put(field, unsafeObject.get(field));
		}
	}
}