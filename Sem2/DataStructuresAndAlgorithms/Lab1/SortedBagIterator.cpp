#include "SortedBagIterator.h"
#include "SortedBag.h"
#include <exception>
using namespace std;

SortedBagIterator::SortedBagIterator(const SortedBag &b) : bag(b) {
  current_frequency = 0;
  index = 0;
}

TComp SortedBagIterator::getCurrent() {
  if (!valid()) {
    throw std::exception();
  }
  return bag.elems[index];
}

bool SortedBagIterator::valid() {
  return index < bag.length && current_frequency < bag.freqs[index];
}

void SortedBagIterator::next() {
  if (!valid()) {
    throw std::exception();
  }
  current_frequency++;
  if (!valid()) {
    index++;
    current_frequency = 0;
  }
}

void SortedBagIterator::first() {
  current_frequency = 0;
  index = 0;
}
