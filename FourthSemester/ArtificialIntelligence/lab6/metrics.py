from sklearn.metrics import precision_recall_fscore_support as score


def multi_class_classification(realLabels, computedLabels):
    # computedLabels = []
    # for each in computedOutputs:
    # bigger_prob = each.index(max(each))
    # label = labelNames[bigger_prob]
    # computedLabels.append(label)
    labelNames = list(set(realLabels))
    accuracy = sum([1 if realLabels[i] == computedLabels[i] else 0 for i in range(len(realLabels))]) / len(realLabels)
    precision = {}
    recall = {}
    for each in labelNames:
        TP = FP = FN = 0
        for i in range(len(realLabels)):
            if realLabels[i] == each and computedLabels[i] == each:
                TP = TP + 1
            if realLabels[i] != each and computedLabels[i] == each:
                FP = FP + 1
            if realLabels[i] == each and computedLabels[i] != each:
                FN = FN + 1
        precision[each] = TP / (TP + FP)
        recall[each] = TP / (TP + FN)
    return accuracy, precision, recall

def sklearn_metrics(real, computed):
    precision, recall, fscore, support = score(real, computed)
    return precision, recall, score


