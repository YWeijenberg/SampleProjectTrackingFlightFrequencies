package com.TrackingFlightFrequencies.Ingestion.FlightData

object ApiRequest {

  def request(url: String,
              apiKey: String,
              apiHost: String,
              fromLocal: String = "2024-02-07T12:00",
              toLocal: String = "2024-02-07T23:59"): String = {
    val r = requests.get(
      url = url,
      headers = Map(
        "X-RapidAPI-Key" -> apiKey,
        "X-RapidAPI-Host" -> apiHost
      ),
      params = Map(
        "fromLocal" -> fromLocal,
        "toLocal" -> toLocal
      )
    )
    if (r.statusCode == 200) r.text else s"Error: ${r.statusCode}"
  }
}