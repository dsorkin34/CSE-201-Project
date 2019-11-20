
/**
 * The searching field based on the Book's attributes
 *
 */
public enum SearchingField {
	ISBN("ISBN"),
	TITLE("title"), 
	AUTHOR("author"),
	DISCRIPTION("discription"),
	GENRE("genre"),
	ALL("all");

	private String name;
	SearchingField(String name){
		this.name = name;
	}

	public String searchingfield(){
		return name;
	}
}
