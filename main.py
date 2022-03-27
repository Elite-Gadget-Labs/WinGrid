
import geojson
import overpass

api = overpass.API()

# 3607429828 - lasalle
# 3607025718 - windsor
# result = api.get("""
#     area(3607025718)->.searchArea;
#     (
#     way["highway"="secondary"](area.searchArea);
#     );
#     (._;>;);
#     """)

# with open('windsor.geojson', 'w') as outfile:
#     geojson.dump(result, outfile)

result = api.get("""
    area(3607429828)->.searchArea;
    (
    way["highway"="secondary"](area.searchArea);
    );
    (._;>;);
    """)


with open('lasalle.geojson', 'w') as outfile:
    geojson.dump(result, outfile)
