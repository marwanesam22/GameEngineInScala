:- use_module(library(clpfd)).
n_queens(N, Qs) :-
    length(Qs, N),
    Qs ins 1..N,
    safe_queens(Qs).

safe_queens([]).
safe_queens([Q|Qs]):-
    safequeens(Qs,Q,1),
    safe_queens(Qs).

safequeens([],,).
safequeens([Q|Qs],Q0,D0):-
    Q #= Q0,
    abs(Q0 - Q) #= D0,
    D #= D0 + 1,
    safequeens(Qs,Q0,D).



#/* queries */
#
#/*
#intelligent search
#N = 5,
#n_queens(N ,Qs),
#labeling([], Qs).
#*/

/*
early pruninh
N = 8,
n_queens(N, Qs),
maplist(between(1,N), Qs).
*/






