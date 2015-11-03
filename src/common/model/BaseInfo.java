package common.model;

public class BaseInfo implements IBaseInfo{
	private int objectId = Integer.MIN_VALUE;
	private String name = "";

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
