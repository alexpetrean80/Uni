#pragma once
// DO NOT INCLUDE SORTEDBAGITERATOR

#include <memory>

// DO NOT CHANGE THIS PART
typedef int TComp;
typedef TComp TElem;
typedef bool (*Relation)(TComp, TComp);
#define NULL_TCOMP -11111;

class SortedBagIterator;

class SortedBag {
  friend class SortedBagIterator;

private:
  TComp *elems;
  int *freqs;
  int length, capacity;
  int number_of_elements;
  Relation rel;
  int binary_search(int left, int right, TComp e) const;
  void resize();

public:
  // constructor
  explicit SortedBag(Relation r);

  // adds an element to the sorted bag
  void add(TComp e);

  // removes one occurence of an element from a sorted bag
  // returns true if an eleent was removed, false otherwise (if e was not part
  // of the sorted bag)
  bool remove(TComp e);

  // checks if an element appearch is the sorted bag
  bool search(TComp e) const;

  // returns the number of occurrences for an element in the sorted bag
  int nrOccurrences(TComp elem) const;

  // returns the number of elements from the sorted bag
  int size() const;

  // returns an iterator for this sorted bag
  SortedBagIterator iterator() const;

  // checks if the sorted bag is empty
  bool isEmpty() const;

  //returns the difference between the maximum and the minimum element
  //if empty return -1
  int getRange() const;

  // destructor
  ~SortedBag();
};