import json

import requests

import json

file = open('./roads/Windsor_Roads.json')
data = json.load(file)
print(data)
'''for i in data['emp_details']:
    print(i)'''
file.close()

'''YOUR_API_KEY = "AIzaSyCZBeAuwbckVWoCaoPnaXXX6oDnwBWpq-g"
LAT = "42.2875749"
LNG = "-83.0383949"
TYPE = "restaurant"


def get_locations(lat, lng, _type):
    url = f"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat}%2C{lng}&radius=10000" \
          f"&type={_type}&key={YOUR_API_KEY} "
    payload = {}
    headers = {}
    response = requests.request("GET", url, headers=headers, data=payload)
    print(response.text)


get_locations(LAT, LNG, TYPE)'''
