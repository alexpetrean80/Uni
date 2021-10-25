;;9. Convert a tree of type (1) to type (2).

;;(1) (node no-subtrees list-subtree-1 list-subtree-2 ...)
;;(2) (node (list-subtree-1) (list-subtree-2) ...)

(defun convert (tree)
  (cond
    ((null tree) nil)
    ((= (cadr tree) 0) (list(car tree)))
    ((= (cadr tree) 1) (list (car tree) (convert (cddr tree))))
    ((= (cadr tree) 2) (list (car tree) (convert (cddr tree)) (convert (cddddr tree))))
  ))

  (write(convert (list 'A 2 'B 0 'C 2 'D 0 'E 0)))
  (format t "~%")
  (write(convert (list 'A 2 'B 2 'D 0 'E 1 'H 0 'C 2 'F 1 'I 0 'G 0)))
