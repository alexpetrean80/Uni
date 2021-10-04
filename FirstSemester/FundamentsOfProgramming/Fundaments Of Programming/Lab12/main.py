# The  sequence a= 𝑎1, ..., 𝑎𝑛 with  distinct  integer  elements  is  given.
# Determine  all  subsets  of  at least two elements with the property:
# o The elements in the subset are in increasing order;
# o Any two consecutive elements in the subsequence have at least one common digit.


def isConsistent(subset):
    if len(subset) == 1:
        return True

    for i in range(len(subset)-1):
        if subset[i] > subset[i + 1]:
            return False

    freq1 = [False] * 10
    freq2 = [False] * 10
    for i in range(len(subset)-1):
        aux = subset[i]
        while aux > 0:
            freq1[aux % 10] = True
            aux //= 10
        aux = subset[i + 1]
        while aux > 0:
            freq2[aux % 10] = True
            aux //= 10
        for j in range(10):
            if freq1[j] and freq2[j]:
                return True
    return False


def rec_backtracking(subset, sequence, index=0):
    subset.append(None)
    for i in range(index, len(sequence)):
        subset[-1] = sequence[i]
        if isConsistent(subset):
            if len(subset) > 1:
                print(subset)
            rec_backtracking(subset, sequence, i+1)
    subset.pop()


def iter_backtracking(subset, sequence):
    subset.append(None)
    stack = [0] * (len(sequence) + 1)
    stack[0] = 0
    while len(subset) > 0:
        chosen = False
        for i in range(stack[len(subset) - 1], len(sequence)):
            subset[-1] = sequence[i]
            stack[len(subset)] = i
            if isConsistent(subset):
                chosen = True
                break
        if chosen:
            if len(subset) > 1:
                print(subset)
            subset.append(None)
        else:
            if subset[-1] is None:
                subset.pop()
            subset.pop()
        stack[len(subset) - 1] += 1


sequence = [12, 23, 35, 34, 45]
rec_backtracking([], sequence)
iter_backtracking([], sequence)

