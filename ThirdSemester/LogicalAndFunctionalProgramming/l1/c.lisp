;; c) Write a function to return the list of the first elements of all list elements of a given list with an odd number of elements at superficial level.

(defun getFirstElement (l)
    (cond
        ((null l) nil)
        (t (car l))))

(defun isListOdd (l)
    (cond
        ((equal (car l) nil) nil)
        ((equal (cdr l) nil) t)
        (t (isListOdd (cddr l)))))
    
(defun getFirstElementsInList (l)
    (cond
        ((null l) 
            nil
        )
        ((and (listp (car l)) (equal (isListOdd (car l)) t)) 
            (cons (getFirstElement (car l)) (getFirstElementsInList (cdr l)))
        )
        (t 
            (getFirstElementsInList (cdr l))
        )
        )
)
    
    
        
(write (getFirstElementsInList (list 1 3 2 (list 3 4 5) (list (list 5 2) 6 3))))

