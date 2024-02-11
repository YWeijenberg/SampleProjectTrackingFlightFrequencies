package com.TrackingFlightFrequencies.Ingestion.FlightData

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, explode}

object DataFrameDenester {
  def flattenDepartures(df: DataFrame): DataFrame = {
    val flattenedDf = df
      .select(
        explode(col("departures")).as("departures")
      ).select(
            col("departures.movement.airport.icao").as("movement_airport_icao"),
            col("departures.movement.airport.iata").as("movement_airport_iata"),
            col("departures.movement.airport.localCode").as("movement_airport_localCode"),
            col("departures.movement.airport.name").as("movement_airport_name"),
            col("departures.movement.airport.shortName").as("movement_airport_shortName"),
            col("departures.movement.airport.municipalityName").as("movement_airport_municipalityName"),
            col("departures.movement.airport.location.lat").as("movement_airport_location_lat"),
            col("departures.movement.airport.location.lon").as("movement_airport_location_lon"),
            col("departures.movement.airport.countryCode").as("movement_airport_countryCode"),
            col("departures.movement.scheduledTime.utc").as("movement_scheduledTime_utc"),
            col("departures.movement.scheduledTime.local").as("movement_scheduledTime_local"),
            col("departures.movement.revisedTime.utc").as("movement_revisedTime_utc"),
            col("departures.movement.revisedTime.local").as("movement_revisedTime_local"),
            col("departures.movement.runwayTime.utc").as("movement_runwayTime_utc"),
            col("departures.movement.runwayTime.local").as("movement_runwayTime_local"),
            col("departures.movement.terminal").as("movement_terminal"),
            col("departures.movement.checkInDesk").as("movement_checkInDesk"),
            col("departures.movement.gate").as("movement_gate"),
            col("departures.movement.baggageBelt").as("movement_baggageBelt"),
            col("departures.movement.runway").as("movement_runway"),
            col("departures.movement.quality").as("movement_quality"),
            col("departures.number").as("departure_number"),
            col("departures.callSign").as("departure_callSign"),
            col("departures.status").as("departure_status"),
            col("departures.codeshareStatus").as("departure_codeshareStatus"),
            col("departures.isCargo").as("departure_isCargo"),
            col("departures.aircraft.reg").as("aircraft_reg"),
            col("departures.aircraft.modeS").as("aircraft_modeS"),
            col("departures.aircraft.model").as("aircraft_model"),
            col("departures.aircraft.image.url").as("aircraft_image_url"),
            col("departures.aircraft.image.webUrl").as("aircraft_image_webUrl"),
            col("departures.aircraft.image.author").as("aircraft_image_author"),
            col("departures.aircraft.image.title").as("aircraft_image_title"),
            col("departures.aircraft.image.description").as("aircraft_image_description"),
            col("departures.aircraft.image.license").as("aircraft_image_license"),
            col("departures.aircraft.image.htmlAttributions").as("aircraft_image_htmlAttributions"),
            col("departures.airline.name").as("airline_name"),
            col("departures.airline.iata").as("airline_iata"),
            col("departures.airline.icao").as("airline_icao")
          )
    flattenedDf
  }
  def flattenArrivals(df: DataFrame): DataFrame = {
    val flattenedDf = df
      .select(
        explode(col("arrivals")).as("arrivals")
      ).select(
        col("arrivals.movement.airport.icao").as("movement_airport_icao"),
        col("arrivals.movement.airport.iata").as("movement_airport_iata"),
        col("arrivals.movement.airport.localCode").as("movement_airport_localCode"),
        col("arrivals.movement.airport.name").as("movement_airport_name"),
        col("arrivals.movement.airport.shortName").as("movement_airport_shortName"),
        col("arrivals.movement.airport.municipalityName").as("movement_airport_municipalityName"),
        col("arrivals.movement.airport.location.lat").as("movement_airport_location_lat"),
        col("arrivals.movement.airport.location.lon").as("movement_airport_location_lon"),
        col("arrivals.movement.airport.countryCode").as("movement_airport_countryCode"),
        col("arrivals.movement.scheduledTime.utc").as("movement_scheduledTime_utc"),
        col("arrivals.movement.scheduledTime.local").as("movement_scheduledTime_local"),
        col("arrivals.movement.revisedTime.utc").as("movement_revisedTime_utc"),
        col("arrivals.movement.revisedTime.local").as("movement_revisedTime_local"),
        col("arrivals.movement.runwayTime.utc").as("movement_runwayTime_utc"),
        col("arrivals.movement.runwayTime.local").as("movement_runwayTime_local"),
        col("arrivals.movement.terminal").as("movement_terminal"),
        col("arrivals.movement.checkInDesk").as("movement_checkInDesk"),
        col("arrivals.movement.gate").as("movement_gate"),
        col("arrivals.movement.baggageBelt").as("movement_baggageBelt"),
        col("arrivals.movement.runway").as("movement_runway"),
        col("arrivals.movement.quality").as("movement_quality"),
        col("arrivals.number").as("arrival_number"),
        col("arrivals.callSign").as("arrival_callSign"),
        col("arrivals.status").as("arrival_status"),
        col("arrivals.codeshareStatus").as("arrival_codeshareStatus"),
        col("arrivals.isCargo").as("arrival_isCargo"),
        col("arrivals.aircraft.reg").as("aircraft_reg"),
        col("arrivals.aircraft.modeS").as("aircraft_modeS"),
        col("arrivals.aircraft.model").as("aircraft_model"),
        col("arrivals.aircraft.image.url").as("aircraft_image_url"),
        col("arrivals.aircraft.image.webUrl").as("aircraft_image_webUrl"),
        col("arrivals.aircraft.image.author").as("aircraft_image_author"),
        col("arrivals.aircraft.image.title").as("aircraft_image_title"),
        col("arrivals.aircraft.image.description").as("aircraft_image_description"),
        col("arrivals.aircraft.image.license").as("aircraft_image_license"),
        col("arrivals.aircraft.image.htmlAttributions").as("aircraft_image_htmlAttributions"),
        col("arrivals.airline.name").as("airline_name"),
        col("arrivals.airline.iata").as("airline_iata"),
        col("arrivals.airline.icao").as("airline_icao")
      )
    flattenedDf
  }
}
