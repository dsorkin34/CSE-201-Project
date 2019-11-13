public class User {
	private String userName;
	private String password;
	public User(String name, String pass) {
		userName = name;
		password = pass;
	}
	public String getName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
}
