;; 8.Write a function to determine the number of nodes on the level k from a n-tree represented as follows:
;; (rootlist_nodes_subtree1 ... list_nodes_subtreen) Eg: tree is(a (b (c)) (d) (e (f))) and k=1 => 3 nodes

(defun getKGen (node level)
  (cond
    ((= 0 level) 1)
    ((atom node) nil)
    (t (apply #'+
     (maplist #'(lambda (n) (getKGen n (- level 1))) (cdr node))
     )
    )
  ))


(write (getKGen (list 'a (list 'b (list 'c)) (list 'd) (list 'e (list 'f))) 1))
