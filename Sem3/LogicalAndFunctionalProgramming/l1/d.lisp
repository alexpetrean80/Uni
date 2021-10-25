;; d)Write a function to return the sum of all numerical atoms in a list at superficial level.

(defun sum (l) 
    (cond
        ((null l) 0)
        ((atom (car l)) (+ (car l) (sum (cdr l))))
        (t (sum (cdr l)))))

(write(sum (list (list 2) 2 3 (list 1 3 5) 23)))

