#include "SMMIterator.h"
#include "SortedMultiMap.h"

SMMIterator::SMMIterator(const SortedMultiMap &d) : map(d) {
    current_list = d.first;
    if (current_list != nullptr)
        current_node = current_list->first_node;
    else
        current_node = nullptr;
}

void SMMIterator::first() {
    current_list = map.first;
    if (current_list != nullptr)
        current_node = current_list->first_node;
    else
        current_node = nullptr;
}

void SMMIterator::next() {
    if (!valid()) {
        throw exception();
    }
    if (current_node != nullptr) {
        if (current_node->next_node == nullptr) {
            current_list = current_list->next_list;
            if (current_list == nullptr) {
                return;
            }
            current_node = current_list->first_node;
        } else {
            current_node = current_node->next_node;
        }
    }
}

bool SMMIterator::valid() const {

    return current_list != nullptr;
}

TElem SMMIterator::getCurrent() const {
    if (!valid()) {
        throw exception();
    }
    return make_pair(current_list->key, current_node->value);
}
