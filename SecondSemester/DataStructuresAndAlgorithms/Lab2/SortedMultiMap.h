#pragma once
//DO NOT INCLUDE SMMITERATOR

//DO NOT CHANGE THIS PART
#include <utility>
#include <vector>
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -11111
#define NULL_TELEM pair<TKey, TValue>(-11111, -11111);
using namespace std;
class SMMIterator;
typedef bool (*Relation)(TKey, TKey);

typedef struct Node {
    TValue value;
    Node *next_node;
    Node *prev_node;
} Node;

typedef struct List {
    TKey key;
    Node *first_node;
    List *next_list;
    List *prev_list;
} List;


class SortedMultiMap {
    friend class SMMIterator;

private:
    Relation rel;
    List *first;
    List *last;
    int dim;

public:
    // constructor
    SortedMultiMap(Relation r);

    //adds a new key value pair to the sorted multi map
    void add(TKey c, TValue v);

    //returns the values belonging to a given key
    vector<TValue> search(TKey c) const;

    //removes a key value pair from the sorted multimap
    //returns true if the pair was removed (it was part of the multimap), false if nothing is removed
    bool remove(TKey c, TValue v);

    //returns the number of key-value pairs from the sorted multimap
    int size() const;

    //verifies if the sorted multi map is empty
    bool isEmpty() const;

    // returns an iterator for the sorted multimap. The iterator will returns the pairs as required by the relation (given to the constructor)
    SMMIterator iterator() const;

    //removes all elements from the SortedMultiMap
    void empty();
    // destructor
    ~SortedMultiMap();
};
