#include "SortedMultiMap.h"
#include "SMMIterator.h"
#include <exception>
#include <vector>
using namespace std;

SortedMultiMap::SortedMultiMap(Relation r) {
    //TODO - Implementation
    rel = r;
    last = first = nullptr;
    dim = 0;
}

/*
 * BC: theta(1)
 * WC: theta(lists)
 * AC: O(lists)
 */
void SortedMultiMap::add(TKey c, TValue v) {
    if (first == nullptr) {
        first = new List;
        first->key = c;
        first->next_list = nullptr;
        first->prev_list = nullptr;
        first->first_node = new Node;
        first->first_node->prev_node = nullptr;
        first->first_node->value = v;
        first->first_node->next_node = nullptr;
        last = first;
        dim++;
        return;
    }
    if (rel(c, first->key) && c != first->key) {
        auto new_list = new List;
        new_list->prev_list = nullptr;
        new_list->next_list = first;
        first->prev_list = new_list;
        new_list->key = c;
        new_list->first_node = new Node;
        new_list->first_node->next_node = nullptr;
        new_list->first_node->prev_node = nullptr;
        new_list->first_node->value = v;
        first = new_list;
        dim++;
        return;
    }
    if (!rel(c, last->key) && c != last->key) {
        auto new_list = new List;
        new_list->key = c;
        new_list->prev_list = last;
        new_list->next_list = nullptr;
        new_list->first_node = new Node;
        new_list->first_node->next_node = nullptr;
        new_list->first_node->prev_node = nullptr;
        new_list->first_node->value = v;
        last->next_list = new_list;
        last = new_list;
        dim++;
        return;
    }
    List *previous = nullptr;
    auto current = first;

    while (current != nullptr) {
        if (current->key == c) {
            auto new_node = new Node;
            new_node->prev_node = nullptr;
            new_node->next_node = current->first_node;
            current->first_node->prev_node = new_node;
            new_node->value = v;
            current->first_node = new_node;
            dim++;
            return;
        }

        if (rel(c, current->key) && previous != nullptr && !rel(c, previous->key)) {
            auto new_list = new List;
            new_list->next_list = current;
            new_list->prev_list = previous;
            new_list->key = c;
            new_list->first_node = new Node;
            new_list->first_node->prev_node = nullptr;
            new_list->first_node->next_node = nullptr;
            new_list->first_node->value = v;
            previous->next_list = new_list;
            current->prev_list = new_list;
            dim++;
            return;
        }
        previous = current;
        current = current->next_list;
    }
}

/*
 * BC:  theta(1)
 * WC: theta(dim)
 * AC: O(dim)
 */
vector<TValue> SortedMultiMap::search(TKey c) const {
    if (dim == 0) {
        return vector<TValue>{};
    }
    vector<TValue> values;
    List *current_list_node = first;
    while (current_list_node->next_list != nullptr) {
        if (current_list_node->key == c) {
            break;
        } else {
            current_list_node = current_list_node->next_list;
        }
    }
    if (current_list_node->key == c) {
        auto current_node = current_list_node->first_node;
        while (current_node != nullptr) {
            values.push_back(current_node->value);
            current_node = current_node->next_node;
        }
    }
    return values;
}

/*
 * BC: theta(1)
 * WC: theta(nodes + lists)
 * AC: O(nodes + lists)
 */
bool SortedMultiMap::remove(TKey c, TValue v) {
    if (dim == 0) return false;
    auto current_list = first;
    while (current_list != nullptr) {
        if (current_list->key == c) break;
        current_list = current_list->next_list;
    }
    if (current_list == nullptr) return false;
    auto current_node = current_list->first_node;
    while (current_node != nullptr) {
        if (current_node->value == v) break;
        current_node = current_node->next_node;
    }
    if (current_node == nullptr) return false;
    //deleting first node
    if (current_node == current_list->first_node) {
        // if it is the only node, the list is also deleted
        if (current_node->next_node == nullptr) {
            delete current_node;
            if (current_list == first) {
                if (current_list->next_list != nullptr) {
                    first = current_list->next_list;
                    first->prev_list = nullptr;
                } else {
                    first = last = nullptr;
                }
                delete current_list;

                dim--;
                return true;
            }
            if (current_list == last) {
                last = current_list->prev_list;
                last->next_list = nullptr;
                delete current_list;
                dim--;
                return true;
            }
            current_list->next_list->prev_list = current_list->prev_list;
            current_list->prev_list->next_list = current_list->next_list;
            delete current_list;
            dim--;
            return true;
        } else {
            current_list->first_node = current_node->next_node;
            delete current_node;
            dim--;
            return true;
        }
    }
    if (current_node->next_node == nullptr) {
        current_node->prev_node->next_node = nullptr;
        delete current_node;
        dim--;
        return true;
    }
    current_node->next_node->prev_node = current_node->prev_node;
    current_node->prev_node->next_node = current_node->next_node;
    delete current_node;
    dim--;
    return true;
}


/*
 * BC = WC = AC = theta(1)
 */
int SortedMultiMap::size() const {
    //TODO - Implementation
    return dim;
}

/*
 * BC = WC = AC = theta(1)
 */
bool SortedMultiMap::isEmpty() const {
    return dim == 0;
}

SMMIterator SortedMultiMap::iterator() const {
    return SMMIterator(*this);
}

SortedMultiMap::~SortedMultiMap() {
    empty();
}

/*
 * BC = WC = AC = theta(lists * nodes)
 */
void SortedMultiMap::empty() {
    if (dim == 0) {
        return;
    }
    auto current_list = first;
    Node *current_node = nullptr;
    while (current_list != nullptr) {
        current_node = current_list->first_node;
        while (current_node != nullptr) {
            auto aux = current_node;
            current_node = current_node->next_node;
            delete aux;
        }
        auto aux = current_list;
        current_list = current_list->next_list;
        delete aux;
    }
    dim = 0;
}
