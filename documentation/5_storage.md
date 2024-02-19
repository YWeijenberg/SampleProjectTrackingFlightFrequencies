<h1>Output Data</h1>

<h2>Schemas Overview</h2>

<h3>Archive Data</h3>

<b>location:</b> <br> 
`abfss://...@....dfs.core.windows.net/archive/{airportIcao}/{departure/arrival}.parquet`

<b>Schema:</b>

| Column Name                                 | Description                                                                 |
|---------------------------------------|-----------------------------------------------------------------------------|
| `movement_airport_icao`               | The ICAO code for the departure airport.                                    |
| `movement_airport_iata`               | The IATA code for the departure airport.                                    |
| `movement_airport_localCode`          | The local code for the departure airport.                                   |
| `movement_airport_name`               | The full name of the departure airport.                                     |
| `movement_airport_shortName`          | The short name of the departure airport.                                    |
| `movement_airport_municipalityName`   | The municipality in which the airport is located.                           |
| `movement_airport_location_lat`       | Latitude of the departure airport.                                          |
| `movement_airport_location_lon`       | Longitude of the departure airport.                                         |
| `movement_airport_countryCode`        | The country code of the departure airport.                                  |
| `movement_scheduledTime_utc`          | Scheduled departure time in UTC.                                            |
| `movement_scheduledTime_local`        | Scheduled departure time in local timezone.                                 |
| `movement_revisedTime_utc`            | Revised departure time in UTC, if any.                                      |
| `movement_revisedTime_local`          | Revised departure time in local timezone, if any.                           |
| `movement_runwayTime_utc`             | Actual runway departure time in UTC.                                        |
| `movement_runwayTime_local`           | Actual runway departure time in local timezone.                             |
| `movement_terminal`                   | Departure terminal.                                                         |
| `movement_checkInDesk`                | Check-in desk numbers.                                                      |
| `movement_gate`                       | Departure gate.                                                             |
| `movement_baggageBelt`                | Baggage belt for the flight.                                                |
| `movement_runway`                     | Runway used for departure.                                                  |
| `movement_quality`                    | Quality indicator for the movement data.                                    |
| `departure_number`                    | Flight number.                                                              |
| `departure_callSign`                  | Call sign of the flight.                                                    |
| `departure_status`                    | Current status of the departure.                                            |
| `departure_codeshareStatus`           | Codeshare status of the flight.                                             |
| `departure_isCargo`                   | Indicates if the flight is carrying cargo.                                  |
| `aircraft_reg`                        | Registration number of the aircraft.                                        |
| `aircraft_modeS`                      | Mode-S transponder code of the aircraft.                                    |
| `aircraft_model`                      | Model of the aircraft.                                                      |
| `aircraft_image_url`                  | URL to an image of the aircraft.                                            |
| `aircraft_image_webUrl`               | Web URL for the aircraft image.                                             |
| `aircraft_image_author`               | Author of the aircraft image.                                               |
| `aircraft_image_title`                | Title of the aircraft image.                                                |
| `aircraft_image_description`          | Description of the aircraft image.                                          |
| `aircraft_image_license`              | License type for the aircraft image.                                        |
| `aircraft_image_htmlAttributions`     | HTML attributions for the aircraft image.                                   |
| `airline_name`                        | Name of the airline.                                                        |
| `airline_iata`                        | IATA code of the airline.                                                   |
| `airline_icao`                        | ICAO code of the airline.                                                   |


<h3>Gold Table: Daily Departure/Arrival Count</h3>

<b>location:</b> <br> 
`dbfs://{airportIcao}_{departures/arrivals}_count.delta`

<b>Schema:</b>
| Column Name     | Description                                                  |
|-----------------|--------------------------------------------------------------|
| `airport_iata`  | The IATA code identifying the airport.                       |
| `count`         | The number of flights associated with the respective data.   |
| `isCargo`       | Boolean indicating if the flight is carrying cargo.          |
| `flight_date`   | The date of the flight.                                      |
| `airport_name`  | The name of the airport.                                     |

<h2>Design Choices</h2>

<h3>Storage of Raw Data</h3>

The raw data is stored in an Azure Data Lake Storage (ADLS) Gen2 account, organized into separate folders for each airport. Within these folders, data for arrivals and departures are kept in distinct files, facilitating efficient filtering by airport and then by type of flight (arrival/departure). The chosen file format is Parquet, utilizing Snappy compression for its balance of compression chosen for its efficient storage. 

<h3>Storage of "Gold" Data</h3>

Aggregated data tables are stored in Databricks File System (DBFS), making them easily accessible for querying and visualization within Databricks. This data is stored in the Delta Lake format, which provides ACID transaction support, adding an extra layer of data integrity and reliability to the Parquet storage format.