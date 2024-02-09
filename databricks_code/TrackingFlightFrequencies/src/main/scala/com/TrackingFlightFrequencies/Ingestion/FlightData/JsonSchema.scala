package com.TrackingFlightFrequencies.Ingestion.FlightData

import org.apache.spark.sql.types._

object JsonSchema {
  val jsonSchema: StructType = StructType(
    Array(
      StructField("departures", ArrayType(StructType(
        Array(
          StructField("movement", StructType(
            Array(
              StructField("airport", StructType(
                Array(
                  StructField("icao", StringType),
                  StructField("iata", StringType),
                  StructField("localCode", StringType),
                  StructField("name", StringType),
                  StructField("shortName", StringType),
                  StructField("municipalityName", StringType),
                  StructField("location", StructType(
                    Array(
                      StructField("lat", DoubleType),
                      StructField("lon", DoubleType)
                    )
                  )),
                  StructField("countryCode", StringType)
                )
              )),
              StructField("scheduledTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("revisedTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("runwayTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("terminal", StringType),
              StructField("checkInDesk", StringType),
              StructField("gate", StringType),
              StructField("baggageBelt", StringType),
              StructField("runway", StringType),
              StructField("quality", ArrayType(StringType))
            )
          )),
          StructField("departure", StructType(
            Array(
              StructField("airport", StructType(
                Array(
                  StructField("icao", StringType),
                  StructField("iata", StringType),
                  StructField("localCode", StringType),
                  StructField("name", StringType),
                  StructField("shortName", StringType),
                  StructField("municipalityName", StringType),
                  StructField("location", StructType(
                    Array(
                      StructField("lat", DoubleType),
                      StructField("lon", DoubleType)
                    )
                  )),
                  StructField("countryCode", StringType)
                )
              )),
              StructField("scheduledTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("revisedTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("runwayTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("terminal", StringType),
              StructField("checkInDesk", StringType),
              StructField("gate", StringType),
              StructField("baggageBelt", StringType),
              StructField("runway", StringType),
              StructField("quality", ArrayType(StringType))
            )
          )),
          StructField("arrival", StructType(
            Array(
              StructField("airport", StructType(
                Array(
                  StructField("icao", StringType),
                  StructField("iata", StringType),
                  StructField("localCode", StringType),
                  StructField("name", StringType),
                  StructField("shortName", StringType),
                  StructField("municipalityName", StringType),
                  StructField("location", StructType(
                    Array(
                      StructField("lat", DoubleType),
                      StructField("lon", DoubleType)
                    )
                  )),
                  StructField("countryCode", StringType)
                )
              )),
              StructField("scheduledTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("revisedTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("runwayTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("terminal", StringType),
              StructField("checkInDesk", StringType),
              StructField("gate", StringType),
              StructField("baggageBelt", StringType),
              StructField("runway", StringType),
              StructField("quality", ArrayType(StringType))
            )
          )),
          StructField("number", StringType),
          StructField("callSign", StringType),
          StructField("status", StringType),
          StructField("codeshareStatus", StringType),
          StructField("isCargo", BooleanType),
          StructField("aircraft", StructType(
            Array(
              StructField("reg", StringType),
              StructField("modeS", StringType),
              StructField("model", StringType),
              StructField("image", StructType(
                Array(
                  StructField("url", StringType),
                  StructField("webUrl", StringType),
                  StructField("author", StringType),
                  StructField("title", StringType),
                  StructField("description", StringType),
                  StructField("license", StringType),
                  StructField("htmlAttributions", ArrayType(StringType))
                )
              ))
            )
          )),
          StructField("airline", StructType(
            Array(
              StructField("name", StringType),
              StructField("iata", StringType),
              StructField("icao", StringType)
            )
          )),
          StructField("location", StructType(
            Array(
              StructField("pressureAltitude", StructType(
                Array(
                  StructField("meter", IntegerType),
                  StructField("km", IntegerType),
                  StructField("mile", IntegerType),
                  StructField("nm", IntegerType),
                  StructField("feet", IntegerType)
                )
              )),
              StructField("altitude", StructType(
                Array(
                  StructField("meter", IntegerType),
                  StructField("km", IntegerType),
                  StructField("mile", IntegerType),
                  StructField("nm", IntegerType),
                  StructField("feet", IntegerType)
                )
              )),
              StructField("pressure", StructType(
                Array(
                  StructField("hPa", IntegerType),
                  StructField("inHg", IntegerType),
                  StructField("mmHg", IntegerType)
                )
              )),
              StructField("groundSpeed", StructType(
                Array(
                  StructField("kt", IntegerType),
                  StructField("kmPerHour", IntegerType),
                  StructField("miPerHour", IntegerType),
                  StructField("meterPerSecond", IntegerType)
                )
              )),
              StructField("trueTrack", StructType(
                Array(
                  StructField("deg", DoubleType),
                  StructField("rad", DoubleType)
                )
              )),
              StructField("reportedAtUtc", StringType),
              StructField("lat", DoubleType),
              StructField("lon", DoubleType)
            )
          ))
        )
      )), true),
      StructField("arrivals", ArrayType(StructType(
        Array(
          StructField("movement", StructType(
            Array(
              StructField("airport", StructType(
                Array(
                  StructField("icao", StringType),
                  StructField("iata", StringType),
                  StructField("localCode", StringType),
                  StructField("name", StringType),
                  StructField("shortName", StringType),
                  StructField("municipalityName", StringType),
                  StructField("location", StructType(
                    Array(
                      StructField("lat", DoubleType),
                      StructField("lon", DoubleType)
                    )
                  )),
                  StructField("countryCode", StringType)
                )
              )),
              StructField("scheduledTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("revisedTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("runwayTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("terminal", StringType),
              StructField("checkInDesk", StringType),
              StructField("gate", StringType),
              StructField("baggageBelt", StringType),
              StructField("runway", StringType),
              StructField("quality", ArrayType(StringType))
            )
          )),
          StructField("departure", StructType(
            Array(
              StructField("airport", StructType(
                Array(
                  StructField("icao", StringType),
                  StructField("iata", StringType),
                  StructField("localCode", StringType),
                  StructField("name", StringType),
                  StructField("shortName", StringType),
                  StructField("municipalityName", StringType),
                  StructField("location", StructType(
                    Array(
                      StructField("lat", DoubleType),
                      StructField("lon", DoubleType)
                    )
                  )),
                  StructField("countryCode", StringType)
                )
              )),
              StructField("scheduledTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("revisedTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("runwayTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("terminal", StringType),
              StructField("checkInDesk", StringType),
              StructField("gate", StringType),
              StructField("baggageBelt", StringType),
              StructField("runway", StringType),
              StructField("quality", ArrayType(StringType))
            )
          )),
          StructField("arrival", StructType(
            Array(
              StructField("airport", StructType(
                Array(
                  StructField("icao", StringType),
                  StructField("iata", StringType),
                  StructField("localCode", StringType),
                  StructField("name", StringType),
                  StructField("shortName", StringType),
                  StructField("municipalityName", StringType),
                  StructField("location", StructType(
                    Array(
                      StructField("lat", DoubleType),
                      StructField("lon", DoubleType)
                    )
                  )),
                  StructField("countryCode", StringType)
                )
              )),
              StructField("scheduledTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("revisedTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("runwayTime", StructType(
                Array(
                  StructField("utc", StringType),
                  StructField("local", StringType)
                )
              )),
              StructField("terminal", StringType),
              StructField("checkInDesk", StringType),
              StructField("gate", StringType),
              StructField("baggageBelt", StringType),
              StructField("runway", StringType),
              StructField("quality", ArrayType(StringType))
            )
          )),
          StructField("number", StringType),
          StructField("callSign", StringType),
          StructField("status", StringType),
          StructField("codeshareStatus", StringType),
          StructField("isCargo", BooleanType),
          StructField("aircraft", StructType(
            Array(
              StructField("reg", StringType),
              StructField("modeS", StringType),
              StructField("model", StringType),
              StructField("image", StructType(
                Array(
                  StructField("url", StringType),
                  StructField("webUrl", StringType),
                  StructField("author", StringType),
                  StructField("title", StringType),
                  StructField("description", StringType),
                  StructField("license", StringType),
                  StructField("htmlAttributions", ArrayType(StringType))
                )
              ))
            )
          )),
          StructField("airline", StructType(
            Array(
              StructField("name", StringType),
              StructField("iata", StringType),
              StructField("icao", StringType)
            )
          )),
          StructField("location", StructType(
            Array(
              StructField("pressureAltitude", StructType(
                Array(
                  StructField("meter", IntegerType),
                  StructField("km", IntegerType),
                  StructField("mile", IntegerType),
                  StructField("nm", IntegerType),
                  StructField("feet", IntegerType)
                )
              )),
              StructField("altitude", StructType(
                Array(
                  StructField("meter", IntegerType),
                  StructField("km", IntegerType),
                  StructField("mile", IntegerType),
                  StructField("nm", IntegerType),
                  StructField("feet", IntegerType)
                )
              )),
              StructField("pressure", StructType(
                Array(
                  StructField("hPa", IntegerType),
                  StructField("inHg", IntegerType),
                  StructField("mmHg", IntegerType)
                )
              )),
              StructField("groundSpeed", StructType(
                Array(
                  StructField("kt", IntegerType),
                  StructField("kmPerHour", IntegerType),
                  StructField("miPerHour", IntegerType),
                  StructField("meterPerSecond", IntegerType)
                )
              )),
              StructField("trueTrack", StructType(
                Array(
                  StructField("deg", DoubleType),
                  StructField("rad", DoubleType)
                )
              )),
              StructField("reportedAtUtc", StringType),
              StructField("lat", DoubleType),
              StructField("lon", DoubleType)
            )
          ))
        )
      )), true),
    )
  )
}
