
/**
 * The searching field based on the Book's attributes
 *
 */
public enum SearchingField {
	// Elements
	ISBN("ISBN"),
	TITLE("title"), 
	AUTHOR("lname"),
	DISCRIPTION("discription"),
	GENRE("genre"),
	ALL("all");

	private String name; // the alternative string of the SearchingField
	// A constructor to combine the elements to its the alternative string
	SearchingField(String name){
		this.name = name;
	} // end constructor

	/***
	 * Return an alternative form of the SearchingField,
	 * which can be embedded into the SQL query statement
	 * 
	 * @return the alternative string of the SearchingField
	 */
	public String searchingfield(){
		return name;
	} // end SearchingField
}
