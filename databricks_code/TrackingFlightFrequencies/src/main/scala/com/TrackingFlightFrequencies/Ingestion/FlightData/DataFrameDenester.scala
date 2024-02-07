package com.TrackingFlightFrequencies.Ingestion.FlightData

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, explode}

object DataFrameDenester {
  def flattenDataFrame(df: DataFrame): DataFrame = {
    df.select(
      col("pagination.limit"),
      col("pagination.offset"),
      col("pagination.count"),
      col("pagination.total"),
      explode(col("data")).alias("data")
    ).select(
      col("data.flight_date").alias("flight_date"),
      col("data.flight_status").alias("flight_status"),
      col("data.departure.airport").alias("departure_airport"),
      col("data.departure.timezone").alias("departure_timezone"),
      col("data.departure.iata").alias("departure_iata"),
      col("data.departure.icao").alias("departure_icao"),
      col("data.departure.terminal").alias("departure_terminal"),
      col("data.departure.gate").alias("departure_gate"),
      col("data.departure.delay").alias("departure_delay"),
      col("data.departure.scheduled").alias("departure_scheduled"),
      col("data.departure.estimated").alias("departure_estimated"),
      col("data.departure.actual").alias("departure_actual"),
      col("data.departure.estimated_runway").alias("departure_estimated_runway"),
      col("data.departure.actual_runway").alias("departure_actual_runway"),
      col("data.arrival.airport").alias("arrival_airport"),
      col("data.arrival.timezone").alias("arrival_timezone"),
      col("data.arrival.iata").alias("arrival_iata"),
      col("data.arrival.icao").alias("arrival_icao"),
      col("data.arrival.terminal").alias("arrival_terminal"),
      col("data.arrival.gate").alias("arrival_gate"),
      col("data.arrival.delay").alias("arrival_delay"),
      col("data.arrival.scheduled").alias("arrival_scheduled"),
      col("data.arrival.estimated").alias("arrival_estimated"),
      col("data.arrival.actual").alias("arrival_actual"),
      col("data.arrival.estimated_runway").alias("arrival_estimated_runway"),
      col("data.arrival.actual_runway").alias("arrival_actual_runway"),
      col("data.airline.name").alias("airline_name"),
      col("data.airline.iata").alias("airline_iata"),
      col("data.airline.icao").alias("airline_icao"),
      col("data.flight.number").alias("flight_number"),
      col("data.flight.iata").alias("flight_iata"),
      col("data.flight.icao").alias("flight_icao"),
      col("data.flight.codeshared.airline_name").alias("codeshared_airline_name"),
      col("data.flight.codeshared.airline_iata").alias("codeshared_airline_iata"),
      col("data.flight.codeshared.airline_icao").alias("codeshared_airline_icao"),
      col("data.flight.codeshared.flight_number").alias("codeshared_flight_number"),
      col("data.flight.codeshared.flight_iata").alias("codeshared_flight_iata"),
      col("data.flight.codeshared.flight_icao").alias("codeshared_flight_icao"),
      col("data.aircraft").alias("aircraft"),
      col("data.live").alias("live")
    )
  }
}
