import csv

from k_means import Kmeans
from matplotlib import pyplot as plt

from metrics import multi_class_classification, sklearn_metrics


def plot(inputs, outputs, output_names, centroids):
    labels = set(outputs)
    data_count = len(inputs)
    for label in labels:
        x = [inputs[i][0] for i in range(data_count) if outputs[i] == label]
        y = [inputs[i][1] for i in range(data_count) if outputs[i] == label]
        plt.scatter(x, y, label=output_names[label])
    for centroid in centroids:
        plt.scatter(centroid[0], centroid[1], s=130, marker='*')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.legend()
    plt.show()


def plot_clusters(inputs, outputs, output_names, km):
    labels = set(outputs)
    data_count = len(inputs)
    for each in km.centroids:
        x = [inputs[i][0] for i in range(data_count) if km.predict(inputs[i]) == each]
        y = [inputs[i][1] for i in range(data_count) if km.predict(inputs[i]) == each]
        plt.scatter(x, y, label=output_names[label])
    for centroid in km.centroids:
        plt.scatter(centroid[0], centroid[1], s=130, marker='*')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.legend()
    plt.show()


def read_dataset():
    c = 0
    v = []
    l = []
    with open("dataset.csv") as ds:
        reader = csv.reader(ds, delimiter=',')
        for line in reader:
            if c != 0:
                l.append(line[0])
                v.append([float(line[1]), float(line[2])])
            c += 1
    return v, l


if __name__ == '__main__':
    values, labels = read_dataset()
    km = Kmeans(4, 50, labels)
    km.fit(values)
    plot(values, labels, {'A': 'A', 'B': 'B', 'C': 'C', 'D': 'D'}, km.centroids)
    computed = []
    for i in range(len(values)):
        computed.append(km.predict(values[i]))
        # print("real : " + str(labels[i]) + "   computed : " + str(computed[i]))

    accuracy, precision, recall = sklearn_metrics(labels, [computed[i][0] for i in range(len(computed))])
    print('accuracy : {}'.format(accuracy))
    print('precision : {}'.format(precision))
    print('recall: {}'.format(recall))
