# ituneflix

# Core Libraries Use

    * Realm DB - I decided to use this library as my DB Handler because it is easy to implement and queries faster.
    * Butterknife - This library is very important specially in binding views. It will save development time.
    * Retrofit2 - Retrofit is a REST Client for Java and Android. It makes the developer easy to retrieve and upload JSON (or other structured data) via a REST based webservice. In Retrofit you configure which converter is used for the data serialization. like in my side its GSON
    * RxJava - It extends the observer pattern to support sequences of data/events and adds operators that allow you to compose sequences together declaratively while abstracting away concerns about things like low-level threading, synchronization, thread-safety and concurrent data structures.
    * GSON - Fastest and commonly use library for JSON serialization and deserialization.

# App Core functionality

    - The app is compose of 3 pages only (Splash Screen, Search and Detailed View)
    - Last list of search movies will be save in our DB
    - Last screen user enter will be save in our App Session so when the user reopens the app. The user will be redirected to that screen.
    - I follow the MVP (Model, View, Presenter) approach. as it gives reusable and cleaner code.