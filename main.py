import json
import os
import pandas as pd
import matplotlib.pyplot as plt
import requests
import seaborn as sns
from pprint import pprint
import sys
sns.set()
from sklearn.cluster import KMeans

city = "Windsor_Roads.json"


def get_nodes_from_json(json_path):
    with open(f"./roads/{json_path}", "r") as f:
        road_data = json.load(f)
    node_clusters = [item["nodes"] for item in list(road_data.values())]
    nodes = []
    for node2d in node_clusters: nodes.extend([n for n in node2d])
    return nodes


def convert_string_nodes_to_float_nodes(string_nodes):
    return [list(map(float, i)) for i in string_nodes]


def get_all_nodes():
    nodes = []
    file_names = os.listdir('./roads')
    for name in file_names:
        nodes.extend(get_nodes_from_json(name))
    return nodes


def get_address_from_geocord(coordinates, key):
    coordinates = ",".join(map(str, coordinates.tolist()))
    url = f"https://maps.googleapis.com/maps/api/geocode/json?latlng={coordinates}&key={key}"
    resp = requests.get(url, timeout=10).json()["results"][0]["formatted_address"]

    pprint(resp)
    return resp


def generate_json_result(results: list, key):
    jason = []
    for result in results:
        d = {"name": get_address_from_geocord(result, key), "latitude": result[0], "longitude": result[1]}

        jason.append(d)

    return jason


if __name__ == "__main__":
    # nodes = convert_string_nodes_to_float_nodes(get_nodes_from_json(city))
    nodes = convert_string_nodes_to_float_nodes(get_all_nodes())
    kmeans = KMeans(int(sys.argv[1]))
    x = pd.DataFrame(nodes)
    kmeans.fit(x)
    identified_clusters = kmeans.fit_predict(x)

    # print(list(kmeans.cluster_centers_))
    # from collections import defaultdict
    # count_dic = defaultdict(lambda: 0)
    # for el in list(kmeans.cluster_centers_):
    #     count_dic[tuple(el)] += 1
    # pprint(count_dic)

    x, y = zip(*nodes)
    plt.scatter(x, y, c=identified_clusters, cmap="rainbow")
    centers = kmeans.cluster_centers_
    plt.scatter(centers[:, 0], centers[:, 1], c='black', s=200, alpha=0.5)

    pprint(generate_json_result(kmeans.cluster_centers_, "AIzaSyCZBeAuwbckVWoCaoPnaXXX6oDnwBWpq-g"))
    plt.show()
