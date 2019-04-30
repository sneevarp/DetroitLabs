# DetroitLabs

Requirements:
The App Should...
Create a “Current Temperature” page that fetches and displays the current temperature at the user’s current location.
Add a “5 Day Temperatures” page to your application that fetches and displays the 5 day forecast for the user’s current location. Display all of the 3-hourly forecasts within this 5 day period. 
The user should be able to access this "5 Day Forecast" page from the "Current Temperature" page. You may choose the navigation pattern used. For example, both pages could be top-level tabs within your application. 

APP STRUCTURE
Implemented Structured Architecture
Fetched the user’s current location from the device.
Realtime permission request using Dexter Library
Fetched data from Open Weather API using APP ID (RetroFit)
Tab Navigation with fragments, one for the current weather and the other for the 5 day forecast.
Fetched weather icons using Picasso and displayed in recycler view. 

