<h1>Data Source: API</h1>
To source current and accurate data, an API was used. The criteria of requiring only daily updates allowed for the consideration of free APIs offering a limited number of monthly requests.

<h2>AviationStack</h2>
Initially, AviationStack was selected for its free tier, capable of providing detailed information on either departures or arrivals at a chosen airport, with a limit of 100 requests per month. However, the limit was quickly reached during testing, indicating a need for a more efficient approach in future development, such as using sample requests during code development before making actual API calls.

<h2>AeroDataBox</h2>
Ultimately, AeroDataBox was chosen for the data pipeline due to its affordable minimum tier at $1/month, offering 300 requests sufficient for the project's needs. This API advantageously combines both arrival and departure data in a single request, although it limits data retrieval to a 12-hour span, necessitating two requests per day. This usage pattern allows for approximately 62 requests per airport each month, enabling the inclusion of up to four airports within the limit, in contrast to AviationStack's capacity for just one. This scalability provides potential for expansion. <br>

<h3>Limitations</h3>

- Max 12H span
- Max 300 requests/month