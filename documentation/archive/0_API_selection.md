# API Selection and Info

## OpenSky Intro
OpenSky is an free public API providing a variety of flight information data. The two API requests that are interesting for this project is 'Arrivals by Airport' and 'Departures by Airport'. Using these endpoints we can select a few airports and compare the frequency of arrivals and departures for these airports. The data of these endpoints is updated over night. 

## Limitations
- A time interval can be selected from the API but it can not be longer than 7 days
- Data is updated every night

## Testing API get request for Arrivals by Airport
After testing the request for Arrivals by Airport in postman, it seems that there is a problem with more recent data of the last few months resulting in an error. Probably data for this time period is missing. Since we want to update the data daily to get an updated view of flight frequency per airport using the OpenSky API will not work. 

## Replacement API:  Aviationstack
To replace the use of OpenSky, I found aviationstack. This is a paid API but it has a free tier of 1000 free requests per month. This should be more than enough for our usecase of 1 request per day.

## Testing Aviationstack
After testing aviationstack it is clear that only current flight data can be retrieved with the free plan. The objective of the project could be changed to accomodate for the limitations by assessing the current number of flights in a radius around an airport. 

Since 1000 requests can be made per month, around 30 requests can be made each day. This is roughly one request each hour. If we look at a radius around the airport, and count the unique flights each hour, we can still get a sense of how busy the airport was that day. 

A downside: airplanes could go 'under the radar' of the hourly request leading to misleading results 
A benefit: How busy the airport is can be assessed over time during the day instead of only daily. 

It looks like the API returns all the departures or arrivals of the current date on the specified airport instead of returning only the in-flight airplanes. Thus it is after all possible to achieve our first goal of flight frequency. 

## Aviationstack limitations
We have a limitation of 1000 requests per month. Since we want to compare 4 airports and include departure and arrival data, we will do 8 requests per day, which results in around 250 requests per month which is sufficient. 

## API get url's

- Schiphol Departures: http://api.aviationstack.com/v1/flights?dep_icao=EHAM
- Schiphol Arrivals: http://api.aviationstack.com/v1/flights?arr_icao=EHAM
- Eindhoven Departures: http://api.aviationstack.com/v1/flights?dep_icao=EHEH
- Eindhoven Arrivals: http://api.aviationstack.com/v1/flights?arr_icao=EHEH
- Heathrow Departures: http://api.aviationstack.com/v1/flights?dep_icao=EGLL
- Heathrow Arrivals: http://api.aviationstack.com/v1/flights?arr_icao=EGLL
- Aachen-Merzbrük Departures: http://api.aviationstack.com/v1/flights?dep_icao=EDKA
- Aachen-Merzbrük Arrivals: http://api.aviationstack.com/v1/flights?arr_icao=EDKA

## Format
Example Response: 
```
{
    "pagination": {
        "limit": 100,
        "offset": 0,
        "count": 100,
        "total": 1669022
    },
    "data": [
        {
            "flight_date": "2019-12-12",
            "flight_status": "active",
            "departure": {
                "airport": "San Francisco International",
                "timezone": "America/Los_Angeles",
                "iata": "SFO",
                "icao": "KSFO",
                "terminal": "2",
                "gate": "D11",
                "delay": 13,
                "scheduled": "2019-12-12T04:20:00+00:00",
                "estimated": "2019-12-12T04:20:00+00:00",
                "actual": "2019-12-12T04:20:13+00:00",
                "estimated_runway": "2019-12-12T04:20:13+00:00",
                "actual_runway": "2019-12-12T04:20:13+00:00"
            },
            "arrival": {
                "airport": "Dallas/Fort Worth International",
                "timezone": "America/Chicago",
                "iata": "DFW",
                "icao": "KDFW",
                "terminal": "A",
                "gate": "A22",
                "baggage": "A17",
                "delay": 0,
                "scheduled": "2019-12-12T04:20:00+00:00",
                "estimated": "2019-12-12T04:20:00+00:00",
                "actual": null,
                "estimated_runway": null,
                "actual_runway": null
            },
            "airline": {
                "name": "American Airlines",
                "iata": "AA",
                "icao": "AAL"
            },
            "flight": {
                "number": "1004",
                "iata": "AA1004",
                "icao": "AAL1004",
                "codeshared": null
            },
            "aircraft": {
               "registration": "N160AN",
               "iata": "A321",
               "icao": "A321",
               "icao24": "A0F1BB"
            },
            "live": {
                "updated": "2019-12-12T10:00:00+00:00",
                "latitude": 36.28560000,
                "longitude": -106.80700000,
                "altitude": 8846.820,
                "direction": 114.340,
                "speed_horizontal": 894.348,
                "speed_vertical": 1.188,
                "is_ground": false
            }
        }, 
        [...]
    ]
}
```