package com.TrackingFlightFrequencies.Ingestion.FlightData

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfter

class JsonParserTest extends AnyFunSuite with BeforeAndAfter{

  test("parse should correctly convert JSON string to DataFrame") {
    // Define a sample JSON string that matches your schema
    val jsonString = """{"key1": "value1", "key2": "value2"}""" // Modify this to fit your jsonSchema

    // Call the parse method
    val df = JsonParser.parse(jsonString)

    // Assert that the DataFrame is not empty and matches expected schema
    assert(!df.isEmpty)
    df.printSchema() // For visual inspection during test development
    df.show() // Show a few rows for verification
  }

  // Add more tests as needed to cover different scenarios, including invalid JSON strings
}
