package com.TrackingFlightFrequencies.Ingestion.FlightData

object ApiRequest {

  def request(url: String, apiKey: String, apiHost: String): String = {
    val r = requests.get(
      url = url,
      headers = Map(
        "X-RapidAPI-Key" -> apiKey,
        "X-RapidAPI-Host" -> apiHost
      )
    )
    if (r.statusCode == 200) r.text else s"Error: ${r.statusCode}"
  }
}