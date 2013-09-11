package edu.hfmp3;

import android.content.SearchRecentSuggestionsProvider;

public class SearchProvider extends SearchRecentSuggestionsProvider{
	public static final String AUTHEN = "authen";
	public static final int MODE = DATABASE_MODE_QUERIES;

	public SearchProvider(){
		setupSuggestions(AUTHEN, MODE);
	}
}
