#include <iostream>
#include "list.h"

int main() {
    // create lists
    auto l1 = create();
    auto l2 = create();

    // print the lists
    std::cout << "First list: ";
    print(l1);
    std::cout << "\nSecond list: ";
    print(l2);

    // check for list equality
    std::cout << "Are the lists equal? " << compare(l1, l2);

    // print the intersection of the lists as a set
    std::cout << "\nIntersection of the lists as sets: ";
    Set intersection_set = get_intersection_as_set(l1, l2);
    print(intersection_set);
    destroy(intersection_set);

    // check that the original lists are not modified
    // print the lists
    std::cout << "\nFirst list after intersection: ";
    print(l1);
    std::cout << "\nSecond list after intersection: ";
    print(l2);

    // destroy lists
    destroy(l1);
    destroy(l2);

    return 0;
}
