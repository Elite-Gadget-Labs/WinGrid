import json
import os

import overpy

LAKESHORE_AREA_CODE = "3607430915"  # includes Belle River
TECUMSEH_AREA_CODE = "3607429829"
WINDSOR_AREA_CODE = "3607025718"
LASALLE_AREA_CODE = "3607429828"
AMHERSTBURG_AREA_CODE = "3607429830"
TOWN_OF_ESSEX_AREA_CODE = "3607433380"  # includes Harrow
KINGSVILLE_AREA_CODE = "3607433486"
LEAMINGTON_AREA_CODE = "3607433781"


def get_roads(area_code, file_name):
    api = overpy.Overpass()
    query = f"""
            [out:json];
            area({area_code})->.searchArea;
            (
            way["highway"="secondary"](area.searchArea);
            );
            (._;>;);
            out;
            """
    result = api.query(query)

    json_dict = {}

    for way in result.ways:
        name = way.tags.get("name", "n/a")
        highway = way.tags.get("highway", "n/a")
        lanes = way.tags.get("lanes", "n/a")
        nodes = []
        for node in way.nodes:
            nodes.append([str(node.lat), str(node.lon)])

        inner_dict = {"highway": highway, "lanes": lanes, "nodes": nodes}
        json_dict[str(name)] = inner_dict

        print("Name: %s" % name)
        print("  Highway: %s" % highway)
        print("  Lanes: %s" % lanes)
        print("  Nodes:")
        for node in way.nodes:
            print("    Lat: %f, Lon: %f" % (node.lat, node.lon))

    if not os.path.isdir("roads"):
        os.mkdir("roads")
    with open(f'./roads/{file_name}.json', 'w') as outfile:
        json.dump(json.dumps(json_dict), outfile)


get_roads(LAKESHORE_AREA_CODE, "Lakeshore_Roads")
get_roads(TECUMSEH_AREA_CODE, "Tecumseh_Roads")
get_roads(WINDSOR_AREA_CODE, "Windsor_Roads")
get_roads(LASALLE_AREA_CODE, "Lasalle_Roads")
get_roads(AMHERSTBURG_AREA_CODE, "Amherstburg_Roads")
get_roads(TOWN_OF_ESSEX_AREA_CODE, "Town_of_Essex_Roads")
get_roads(KINGSVILLE_AREA_CODE, "Kingsville_Roads")
get_roads(LEAMINGTON_AREA_CODE, "Leamington_Roads")
