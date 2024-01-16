# Data Ingestion
To Ingest the data from the OpenSky API azure has several options such as Azure Data Factory, Azure Logic Apps, or Azure Functions. Since we only have to ingest a relatively small amount of data once every 24 hours, the free tier of Azure Functions might suffice. This would result in the most cost effective option.

## Azure Functions Free Tier
![Alt text](https://file%252B.vscode-resource.vscode-cdn.net/var/folders/xv/vv2_hgfx0znbs20fy51pps_m0000gn/T/TemporaryItems/NSIRD_screencaptureui_VqJ8Z8/Screenshot%25202024-01-15%2520at%252010.43.57.png?version%253D1705311846078)

According to the documentation on the functions free tier it would be free to ingest data every 24 hours into azure.

## Blob Storage
The API data will be ingested into blob storage, which will be the raw data (read: bronze layer).

## Doing Bronze, Silver, and Gold layer all in DataBricks
Instead of using Azure Functions, an easier way to ingest the data could be using databricks. For the purpose of this project and to learn databricks I will do the project in databricks notebook using scala. 