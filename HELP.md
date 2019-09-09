### Instruction
There is a website - https://www.abodo.com
You need to write a scraper to get the list of property data with the following information (in JSON)
1. Name of the property
2. Location
3. Lat/Lng
4. Bedroom cnt
5. Bathroom cnt
6. Size of the property
7. Property Details
8. Amenities

Look only for properties in 60603 and 60607 postal codes of chicago area.
Also, limit number of properties in each postcode to 200.
Subsequently write an api to fetch these properties based on bedroom and bathroom.
You are free to use any language/modules/server framework of your choice.

### Features
1. Parser
2. Search

### API
1. properties/parse -> To save properties into local database(H2)
2. properties/search/bedroom/{bedRoom} -> To fetch properties by bedroom
3. properties/search/bedroom/{bathRoom} -> To fetch properties by bathroom

### How to access H2 Console
1. Run application.
2. Open browser.
3. Go to http://localhost:8080/h2-console
4. Rewrite JDBC Url as "jdbc:h2:mem:testdb"
5. Connect.