# autofleet

The purpose of this project is to enable the fleet manager to see the vehicle locations, 
and to be able to choose vehicles within an area, this is achieved by drawing a polygon on the map (and see their ids).
 
the server have one endpoint to query all of the vehicle locations which given by json file, 
and one to get vehicles ids that are inside of the specific polygon. 

The client show a map and list of selected vehicles ids (if the user created a polygon).

I used google maps API to show the maps, draw polygon etc.
