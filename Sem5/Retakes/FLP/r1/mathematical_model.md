### a

does_list_have_even_number_of_elems(l1..ln)

- true, if n = 0,
- false, if n = 1
- does_list_have_even_number_of_elems(l3..ln), otherwise

### b

delete_elem_from_list(el, l1..ln)

- [], if n == 0
- l1 U delete_elem_from_list(l2..ln), if l1 != el
- delete_elem_from_list(l2..ln), otherwise
