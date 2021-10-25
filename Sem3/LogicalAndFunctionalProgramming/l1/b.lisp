;; b) Write a function to reverse a list with its all sublists, on all levels.

(defun rev (l col)
    (cond
        ((null l) col)
        ((listp (car l)) (rev (cdr l) (cons (rev (car l) ())col)))
        (t (rev (cdr l) (cons (car l) col)))))
    
(write (rev (list 1 2 3 (list 4 5) 6 7) ()))