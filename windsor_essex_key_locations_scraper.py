import requests
from main import *
import pandas as pd

YOUR_API_KEY = "AIzaSyCZBeAuwbckVWoCaoPnaXXX6oDnwBWpq-g"
LAT = "42.2875749"
LNG = "-83.0383949"
TYPE = "restaurant"

nodes = get_all_nodes()

location_types = ["airport", "bank", "church", "city_hall", "clothing_store",
                  "doctor", "drugstore", "hindu_temple", "home_goods_store",
                  "hospital", "library", "mosque", "pharmacy", "school",
                  "secondary_school", "supermarket", "synagogue", "shopping_mall",
                  "university", "courthouse"]


def get_key_locations(lat, lng, location_type):
    key_locations = []
    url = f"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat}%2C{lng}&radius=500" \
          f"&type={location_type}&key={YOUR_API_KEY} "
    payload = {}
    headers = {}
    response = requests.request("GET", url, headers=headers, data=payload).json()
    for result in response['results']:
        print(result['name'])
        location = result['geometry']['location']
        key_locations.extend([location['lat'], location['lng']])
    return list(set(key_locations))


def get_all_key_locations():
    key_locations = []
    for node in nodes:
        for location_type in location_types:
            key_locations.extend(get_key_locations(node[0], node[1], location_type))
    return list(set(key_locations))


# all_key_locations = get_all_key_locations()
pprint(get_all_key_locations())
