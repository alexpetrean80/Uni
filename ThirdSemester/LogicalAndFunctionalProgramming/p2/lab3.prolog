% a. Define a predicate to add after every element from a list, the divisors of that number.
% b. For a heterogeneous list, formed from integer numbers 
% and list of numbers, define a predicate to add in every sublist the divisors of every element.

% a

getDivisor(Num, Div, []) :-
    Num = Div, !.

getDivisor(Num, Div, [Div | Res]) :-
    0 is Num mod Div,
    NewDiv is Div + 1,
    getDivisor(Num, NewDiv, Res), !.

getDivisor(Num, Div, Res) :-
    NewDiv is Div + 1,
    getDivisor(Num, NewDiv, Res).

concatList([], List, List).
concatList([Head | Tail], List, [Head | Res]) :-
    concatList(Tail, List, Res).

procList([], []).
procList([Head | Tail], [Head | FinalRes]) :-
    getDivisor(Head, 1, Res),
    procList(Tail, NewRes),
    concatList(Res, NewRes, FinalRes).
    
% b
isList([_ | _]).

procHetList([], []).
procHetList([Head | Tail], [NewRes | FinalRes]) :-
    isList(Head),
    procList(Head, NewRes),
    procHetList(Tail, FinalRes).
procHetList([Head | Tail], [Head | FinalRes]) :-
    \+ isList(Head),
    procHetList(Tail, FinalRes).
