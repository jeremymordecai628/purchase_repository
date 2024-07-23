#!/usr/bin/python3

import mysql.connector
import json

# Database connection details
servername = "localhost"
username = "root"
password = ""
dbname = "Supplier_Collaboration"

# Create connection
conn = mysql.connector.connect(
    host=servername,
    user=username,
    password=password,
    database=dbname
)

# Check connection
if not conn.is_connected():
    print("Connection failed")
    exit()

# Query to fetch organization names from organization table
sql_organization = "SELECT organization_name FROM organization"
cursor_organization = conn.cursor(dictionary=True)
cursor_organization.execute(sql_organization)
organization_names = [row["organization_name"] for row in cursor_organization.fetchall()]

# Query to fetch company names from vendor table
sql_vendor = "SELECT company_name FROM vendor"
cursor_vendor = conn.cursor(dictionary=True)
cursor_vendor.execute(sql_vendor)
company_names_vendor = [row["company_name"] for row in cursor_vendor.fetchall()]

# Query to fetch device details from organization_assets table
sql_assets = "SELECT device_type, brand, model, warranty FROM organization_assets"
cursor_assets = conn.cursor(dictionary=True)
cursor_assets.execute(sql_assets)
device_details = [
    {
        "device_type": row["device_type"],
        "brand": row["brand"],
        "model": row["model"],
        "warranty": row["warranty"]
    }
    for row in cursor_assets.fetchall()
]

# Close cursor and connection
cursor_organization.close()
cursor_vendor.close()
cursor_assets.close()
conn.close()

# Combine results into a single dictionary
response = {
    "organization_names": organization_names,
    "company_names": company_names_vendor,
    "device_details": device_details
}

# Return JSON response
print("Content-Type: application/json")
print()
print(json.dumps(response))
