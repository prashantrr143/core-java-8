package in.explore.core.java8.enums;

public enum NameEnum {

	AN("Ankit"), PR("Prashant"), UT("Utkarsh");

	private String name;
	

	@Override
	public String toString() {
		System.out.println("To String called on " + this.name());
		final NameEnum nameEnum =  NameEnum.valueOf(this.name());
		return nameEnum.getName();
	}

	private NameEnum(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
