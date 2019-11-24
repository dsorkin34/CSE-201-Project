
/**
 * The default sorting order is ascending, The REVERSE suffix will use descending sort order.
 *
 */
public enum SortingCriteria {
	// Elements
	TITLE("title"), 
	TITLE_REVERSE("title DESC"), 
	AUTHOR("lname, fname"), 
	AUTHOR_REVERSE("lname DESC, fname DESC"),
	GENRE("genre"), 
	GENRE_REVERSE("genre DESC"), 
	RATING("avg(score)"), 
	RATING_REVERSE("avg(score) DESC");

	private String name; // the alternative string of the SortingCriteria
	// A constructor to combine the elements to its the alternative string
	SortingCriteria(String name){
		this.name = name;
	} // end constructor

	/***
	 * Return an alternative form of the SortingCriteria,
	 * which can be embedded into the SQL query statement
	 * 
	 * @return the alternative string of the SortingCriteria
	 */
	public String sortingCriteria(){
		return name;
	} // end sortingCriteria
}
