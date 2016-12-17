# BookListing
Books information retriever. Get books information according to user-input-keywords from the Google Books website through its API. 

Remove the space if user input space between keywords. 

Open the app to get a list of books summaries according to user-input-keywords. 

Use TaskLoader together with activity to trigger the same task, when different activity instances are created, e.g., rotating the screen. 

Implement an AsyncTaskLoader to asynchronously load data from the API. 

A progress bar is displayed while the app sending the request and waiting for the response. 

The JSON response is parsed to get the expected data. 

Exceptions like no network connection, no expected data, retrieving books information which has no authors information are handled. 

StringBuilder method are covered because the retrieved authors information from JSON is in an array. 

# App ScreenShots
<img src="screenshots/EmptyStateNoKeyWord.png" width="25%" alt="EmptyStateNoKeyWord"/> <img src="screenshots/BookSearchResultJane.png" width="25%" alt="BookSearchResultJane"/> <img src="screenshots/BookSearchResultMy.png" width="25%" alt="BookSearchResultMy"/> <img src="screenshots/BookSearchResultMay.png" width="25%" alt="BookSearchResultMay"/> <img src="screenshots/EmptyStateNoBooksFound.png" width="25%" alt="EmptyStateNoBooksFound"/> <img src="screenshots/EmptyStateNoInternetConnectionBeforeSearch.png" width="25%" alt="EmptyStateNoInternetConnectionBeforeSearch"/> <img src="screenshots/EmptyStateNoInternetConnectionAfterSearch.png" width="25%" alt="EmptyStateNoInternetConnectionAfterSearch"/> <img src="screenshots/BookSearchResultHarryPotter.png" width="25%" alt="BookSearchResultHarryPotter"/> 

<img src="screenshots/LandscapeView.png" width="50%" alt="Landscape view"/>
