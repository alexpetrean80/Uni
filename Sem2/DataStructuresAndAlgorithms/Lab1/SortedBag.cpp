#include "SortedBag.h"
#include "SortedBagIterator.h"
#include <algorithm>

SortedBag::SortedBag(Relation r) {
  length = 0;
  capacity = 10;
  number_of_elements = 0;
  elems = new TComp[capacity];
  freqs = new int[capacity];
  rel = r;
}

/*
 * BC: theta(1)
 * WC: theta(length)
 * AC: O(length)
 */
void SortedBag::add(TComp e) {
  int pos = binary_search(0, length - 1, e);
  if (pos != -1) {
    freqs[pos]++;
    number_of_elements++;
    return;
  }
  if (length == capacity) {
    resize();
  }
  for (auto i = length - 1; i >= -1; i--) {
    if (rel(e, elems[i]) && i != -1) {
      elems[i + 1] = elems[i];
      freqs[i + 1] = freqs[i];
    } else {
      elems[i + 1] = e;
      freqs[i + 1] = 1;
      length++;
      number_of_elements++;
      return;
    }
  }
}

/*
 * BC = WC = AC = theta(length)
 */
void SortedBag::resize() {
  capacity *= 2;
  auto *new_elements = new TComp[capacity];
  auto *new_frequences = new int[capacity];
  for (auto i = 0; i < length; i++) {
    new_elements[i] = elems[i];
    new_frequences[i] = freqs[i];
  }
  delete[] elems;
  delete[] freqs;
  elems = new_elements;
  freqs = new_frequences;
}

/*
 * BC: theta(1)
 * WC: theta(length)
 * AC: O(length)
 */
bool SortedBag::remove(TComp e) {
  auto position = binary_search(0, length - 1, e);
  if (position == -1) {
    return false;
  }
  freqs[position]--;
  if (freqs[position] == 0) {
    for (auto i = position; i < length - 1; i++) {
      elems[i] = elems[i + 1];
      freqs[i] = freqs[i + 1];
    }
    length--;
  }
  number_of_elements--;
  return true;
}

/*
 * BC: theta(1)
 * WC: theta(log length)
 * AC: O(log length)
 */
bool SortedBag::search(TComp elem) const {
  auto position = binary_search(0, length - 1, elem);
  return position != -1;
}

/*
 * BC: theta(1)
 * WC: theta(log length)
 * AC: O(log length)
 */

int SortedBag::nrOccurrences(TComp elem) const {
  auto position = binary_search(0, length - 1, elem);
  if (position > length - 1 || position < 0) {
    return 0;
  }
  return freqs[position];
}

/*
    BC = WC = AC = theta(1)
*/
int SortedBag::size() const { return number_of_elements; }

/*
    BC = WC = AC = theta(1)
*/
bool SortedBag::isEmpty() const { return length == 0; }

SortedBagIterator SortedBag::iterator() const {
  return SortedBagIterator{*this};
}

SortedBag::~SortedBag() {
  delete[] elems;
  delete[] freqs;
}
/*
 * BC: theta(1)
 * WC: theta(log length)
 * AC: O(log length)
 */
int SortedBag::binary_search(int left, int right, TComp e) const {
  if (left > right) {
    return -1;
  }
  int mid = (left + right) / 2;
  if (elems[mid] == e) {
    return mid;
  } else if (rel(e, elems[mid])) {
    return binary_search(left, mid - 1, e);
  } else {
    return binary_search(mid + 1, right, e);
  }
}

/*
 * bc = wc = ac = theta(length)
 */
int SortedBag::getRange() const {
  if (isEmpty()) return -1;
  TComp max = NULL_TCOMP;
  TComp min = INT32_MAX;
  for (auto i = 0; i < length; i++) {
    if (elems[i] < min) min = elems[i];
    if (elems[i] > max) max = elems[i];
  }
  return max - min;
}
