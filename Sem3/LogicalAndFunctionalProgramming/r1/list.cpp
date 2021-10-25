#include "list.h"
#include <iostream>

Node *create_rec() {
    TElem x;
    std::cin >> x;
    if (x == 0) {
        return nullptr;
    }
    return new Node{x, create_rec()};
}

List create() {
    List l;
    l.first = create_rec();
    return l;
}

void print_rec(Node *n) {
    if (n != nullptr) {
        std::cout << n->e << ' ';
        print_rec(n->next);
    }
}

void print(List l) {
    print_rec(l.first);
}

void destroy_rec(Node *n) {
    if (n != nullptr) {
        destroy_rec(n->next);
        delete n;
    }
}

void destroy(List l) {
    destroy_rec(l.first);
}

bool compare_rec(Node *n1, Node *n2) {
    if (n1 == nullptr && n2 != nullptr || (n1 != nullptr && n2 == nullptr)) {
        return false;
    }
    if (n1 == nullptr && n2 == nullptr) {
        return true;
    }

    if (n1->e == n2->e) {
        return true && compare_rec(n1->next, n2->next);
    }
    return false;
}

bool compare(List l1, List l2) {
    return compare_rec(l1.first, l2.first);
}

bool is_elem_in_list_rec(TElem e, Node *first) {
    if (first == nullptr) {
        return false;
    }
    if (e != first->e) {
        return false || is_elem_in_list_rec(e, first->next);
    }
    return true;
}

Node *add_two_lists_rec(Node *n1, Node *n2) {
    if (n1 != nullptr) {
        return new Node{
                n1->e,
                add_two_lists_rec(n1->next, n2)
        };
    }
    if (n2 != nullptr) {
        return new Node{
                n2->e,
                add_two_lists_rec(nullptr, n2->next)
        };
    }
    return nullptr;
}

List add_two_lists(List l1, List l2) {
    List l;
    l.first = add_two_lists_rec(l1.first, l2.first);
    return l;
}

Node *get_set_rec(Node *n) {
    if (n == nullptr) {
        return nullptr;
    }
    if (is_elem_in_list_rec(n->e, n->next)) {
        return get_set_rec(n->next);
    }
    return new Node{n->e, get_set_rec(n->next)};
}

Set get_set(List l) {
    Set s;
    s.first = get_set_rec(l.first);
    return s;
}

Set get_intersection_as_set(List l1, List l2) {
    List l = add_two_lists(l1, l2);
    return get_set(l);
}