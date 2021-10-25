#ifndef LIST_H
#define LIST_H

typedef int TElem;

struct Node {
    TElem e;
    Node *next;
};

struct List {
    Node *first;
};

typedef List Set;

List create();

void print(List l);

void destroy(List l);

bool compare(List l1, List l2);

List get_intersection_as_set(List l1, List l2);

#endif