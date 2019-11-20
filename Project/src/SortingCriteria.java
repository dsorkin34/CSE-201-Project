
/**
 * The default sorting order is ascending, The REVERSE suffix will use descending sort order.
 *
 */
public enum SortingCriteria {
	TITLE("title"), 
	TITLE_REVERSE("title DESC"), 
	AUTHOR("lname, fname"), 
	AUTHOR_REVERSE("lname, fname DESC"),
	GENRE("genre"), 
	GENRE_REVERSE("genre DESC"), 
	RATING("avg(score)"), 
	RATING_REVERSE("avg(score) DESC");

	private String name;
	SortingCriteria(String name){
		this.name = name;
	}

	public String sortingCriteria(){
		return name;
	}
}
