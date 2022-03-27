from pprint import pprint
import json

city = "Windsor_Roads.json"

with open(f"./roads/{city}", "r") as f:
    road_data = json.load(f)

node_clusters = [item["nodes"] for item in list(road_data.values())]
nodes = [nodes[0] for nodes in node_clusters]

pprint(nodes)

import numpy as np
from sklearn.cluster import KMeans
from scipy.spatial.distance import cdist
import math
import matplotlib.pyplot as plt

# print(np.array(nodes))