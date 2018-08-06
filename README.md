# NY Time Api Consumption


Ny Times api cosumption is an android application designed on MVC pattern which consumes NY times JSON API and shows the news results in the form of list.
There is a custom drawer and custom toolbar in the application. 
Drawer has 2 labels at the moment which navigates between the two fragments and toolbar has search button and sub menu button. Search button toogle a search bar on title bar and sub menu buttons open a menu with type of API responses.


The App has following features:

- Parse NY Times API
- Toggle search field on toolbar
- side drawer
- sub menu to view types of news
- If drawer is open -> on back press if closes the drawer
- if search field is visible -> on back pressed it hides the search bar and displays the title
- click on the news item oepn the respestive URL for the respective news in the browser

#### Activities
- Main Activity (Activity handling fragments, drawer and search field)

#### Fragments
- Main Fragment (Fragment which displays the news results as list)

#### Adapters
- NYTimesResultsRecyclerViewAdapter (Adapter that shows the results of news on fragment)

#### Views
- RobotoTextView (Text view with font Roboto Light)
- Toolbar (Custome toolbar with title, drawer button, search button and subMenuButton)\
- BindingViews (Class that initialize the views and binds them with respective screen)

#### Utility Class
- Utility

#### Permissions
- INTERNET

#### Libraries Used
- Retrofit (JSON parsening library)
- Rounded ImageView (Rounded image library)
- Glide (set URL image to image view)

# Screenshot
   [![Loading](https://raw.githubusercontent.com/boygaggoo/NYTimesApiConsumption/master/screenshots/Screenshot_20180806-165926.png)]()
   [![list results with search field](https://raw.githubusercontent.com/boygaggoo/NYTimesApiConsumption/master/screenshots/Screenshot_20180806-165934.png)]()
   [![Search Screen with sub menu](https://github.com/boygaggoo/NYTimesApiConsumption/raw/master/screenshots/Screenshot_20180806-165943.png)]()
   [![Side Drawer](https://github.com/boygaggoo/NYTimesApiConsumption/raw/master/screenshots/Screenshot_20180806-170421.png)]()

# Video
   [![App Video](https://github.com/boygaggoo/NYTimesApiConsumption/blob/master/video/20180806_171358.mp4](https://github.com/boygaggoo/NYTimesApiConsumption/blob/master/video/20180806_171358.mp4)

# Contact
- ##### email (ziagaggoo@gmail.com)