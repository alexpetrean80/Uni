;; a) Write a function to return the difference of two sets.
(defun doesElemExist (e s)
    (cond
        ((null s) nil)    
        ((equal e (car s)) t)
        (t (doesElemExist e (cdr s)))))

(defun diff (s1 s2)
    (cond
        ((null s1) nil)
        ((null s2) s1)
        ((equal 
            (doesElemExist (car s1) s2) nil) 
         (cons (car s1) (diff (cdr s1) s2)))
        (t (diff (cdr s1) s2))))

(write (diff (list 1 2 3 4 5) (list 3 4 5)))

