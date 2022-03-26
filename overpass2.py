import overpy

api = overpy.Overpass()


result = api.query("""
    area(3607025718)->.searchArea;
    (
    way["highway"="primary"](area.searchArea);
    way["highway"="secondary"](area.searchArea);
    way["highway"="tertiary"](area.searchArea);
    way["highway"="unclassified"](area.searchArea);
    way["highway"="residential"](area.searchArea);
    );
    out body;
    """)


for way in result.ways:
    print("Name: %s" % way.tags.get("name", "n/a"))
    print("  Highway: %s" % way.tags.get("highway", "n/a"))
    print("  Lanes: %s" % way.tags.get("lanes", "n/a"))
    print("  Nodes:")
    # nodes = way.get_nodes(resolve_missing=True)
    for node in way.nodes:
        print("    Lat: %f, Lon: %f" % (node.lat, node.lon))

