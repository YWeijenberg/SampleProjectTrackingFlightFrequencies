<h1>Scala Application</h1>

![alt text](images/ScalaCodeOverview.png)

<h2>Scala App Overview</h2>

The scala application that runs on databricks is structured as follows: 

<h3>API Data</h3>

1. Two API calls are made that span the current day and saved in a String
2. The string is parsed into a dataframe using a mirror of the schema in StructType format
3. The DataFrame is flattened and split into arrival and departure data
4. The arrival and departure dataframes are archived to adls gen2 storage in parquet format

<h3>Airport Codes and Names</h3>

1. Airport Codes and Names csv stored in adls is read into dataframe using a mirror of the schema in StructType format

<h3>Processing and Serving</h3>

1. The API data is aggregated and processed
2. The API data is enriched with the full names of the airports as defined in the csv file
3. The gold data is stored in dbfs in delta lake format