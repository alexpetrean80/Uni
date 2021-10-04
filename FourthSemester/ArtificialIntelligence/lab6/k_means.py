import random
import numpy as np
from math import sqrt
from collections import Counter


def euclidean_distance(x1, x2):
    squared_distance = 0
    for i in range(len(x1)):
        squared_distance += (x1[i] - x2[i]) ** 2
    return sqrt(squared_distance)


class Kmeans:

    def __init__(self, k, max_iterations, labels):
        self.labels = labels
        self.cluster_count = k
        self.iterations = max_iterations
        self.centroids = [[] for _ in range(self.cluster_count)]
        self.clusters = {}
        self.similarity = euclidean_distance
        self.data = []

    def fit(self, data):
        self.data = data
        dist = -1
        for i in range(self.cluster_count):
            self.centroids[i] = random.choice(data)
        for i in range(self.iterations):
            self.clusters = {}
            for j in range(self.cluster_count):
                self.clusters[j] = []
            for each in data:
                distances = [self.similarity(centroid, each) for centroid in self.centroids]
                if self.similarity == euclidean_distance:
                    dist = distances.index(min(distances))
                if dist >= 0:
                    self.clusters[dist].append(each)
            # ACTUALIZAREA CENTROIZILOR
            if self.similarity == euclidean_distance:  # actualizez centroizii cu mediile clusterilor
                for each in self.clusters:
                    self.centroids[each] = np.average(self.clusters[each], axis=0)

    def predict(self, sample):
        centroid = random.choice(self.centroids)
        index = -1
        shortest_distance = 100000
        # for each in self.centroids:
        #     if self.similarity(each, sample) < shortest_distance:
        #         shortest_distance = self.similarity(each, sample)
        #         centroid = each
        for i in range(len(self.centroids)):
            if self.similarity(self.centroids[i], sample) < shortest_distance:
                shortest_distance = self.similarity(self.centroids[i], sample)
                index = i
        label_list = []
        for one in self.clusters[index]:
            i = self.data.index(one)
            label_list.append(self.labels[i])
        return Counter(label_list).most_common()[0]

    def dunn_index(self):
        intercluster_distances = []
        intracluster_distances = []
        for i in range(len(self.clusters)):
            for j in range(i + 1, len(self.clusters)):
                intercluster_distances.append(self.similarity(self.centroids[i], self.centroids[j]))
            for a in self.clusters[i]:
                for b in self.clusters[i]:
                    intracluster_distances.append(self.similarity(a, b))

        return min(intercluster_distances) / max(intracluster_distances)
