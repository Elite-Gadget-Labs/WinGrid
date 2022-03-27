import os
from pprint import pprint
import json

city = "Windsor_Roads.json"


def get_nodes_from_json(json_path):
    with open(f"./roads/{json_path}", "r") as f:
        road_data = json.load(f)
    node_clusters = [item["nodes"] for item in list(road_data.values())]
    nodes = [nodes[0] for nodes in node_clusters]
    return nodes


def convert_string_nodes_to_float_nodes(string_nodes):
    return [list(map(float, i)) for i in string_nodes]


def get_all_nodes():
    nodes = []
    file_names = os.listdir('./roads')
    for name in file_names:
        nodes.extend(get_nodes_from_json(name))
    return nodes
