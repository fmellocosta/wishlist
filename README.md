# Wishlist project
A tiny API to implement a sort of wishlist to save your preferred itens from a list.
Thanks to the guys from [**jsonplaceholder**](https://jsonplaceholder.typicode.com/) for providing such a nice API for fake data.

## Requirements
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Postman](https://www.getpostman.com/)
- [Google Chrome](https://www.google.com/chrome/browser/desktop/index.html)

## Usage
- Open your terminal and navigate to the directory where you cloned the project
- Enter the code below to run the .jar inside the target folder:
`$ java -jar target/wishlist-1.0.0.jar`
- Wait a little while the application is being loaded, it will not take so long, the last output line should be something similar to:
` [...] Started WishlistApplication in 9.845 seconds (JVM running for 10.547) [...]`
- Open your browser and navigate to the url:
`http://localhost:8080`
- You will see the main page of the API, divided in 3 parts:
1) Menu: a quick way to navigate through
2) Search Form: search for all items from a specific user id
3) Items List: list with all the items available

## Endpoints
##### Main
**GET**
`/`

##### See all items in wishlist
**GET**
`/wishlist`

##### Search an item from wishlist
**GET**
`/wishlist/{id}`

##### Delete from wishlist
**DELETE**
`/wishlist/remove/{id}`

##### Insert in wishlist
**GET**
`/wishlist/populate/{id}`

##### Insert in wishlist
**POST**
`/wishlist`
```json
{ 
userId: 1, 
id: 1, 
title: "SOME TEXT", 
body: "ANOTHER TEXT"
}
```