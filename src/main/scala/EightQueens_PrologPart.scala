import org.jpl7.{Query, Term, Atom}
def constructPrologList(board: Array[Array[String]]): String = {
var array_converted_to_string = "["
  var added = false
  for(i <- 0 until(board.length)){
    added = false
    for(j <-0 until(board(0).length)){
      if(board(j)(i) == "Q"){
        added = true
        array_converted_to_string+=j
        System.out.println(i)
        System.out.println(j)
      }
    }
    if(added == false) {
      array_converted_to_string+="_"
    }
    if(i != (board.length-1) ) array_converted_to_string+=","
  }
  array_converted_to_string += "]"
  array_converted_to_string

}

def extractNumbers(term: Term): List[Int] = term match {
  case atom: Atom => List.empty // Base case: if the term is an Atom, return an empty list
  case _ if term.isInteger => List(term.intValue()) // If the term is an integer, extract its value
  case _ if term.isCompound => extractNumbersFromCompound(term) // If the term is a compound term, delegate to a helper method
  case _ => List.empty // Default case: return an empty list
}

def extractNumbersFromCompound(term: Term): List[Int] = {
  val arity = term.arity()
  val elements = (1 to arity).map(term.arg).toList
  elements.flatMap(extractNumbers) // Recursively process each element in the compound term and flatten the results
}

def convertToBoard(rows: Term): Array[Array[String]] = {
  val list_of_numbers_in_term = extractNumbers(rows)
  var index: Int = 0
  val new_board = Array.ofDim[String](8, 8)
  for (i <- 0 until 8) {
    for (j <- 0 until 8) {
      new_board(i)(j) = list_of_numbers_in_term(index).toString
      index+=1
    }
  }
  new_board
}

def solveEightQueensUsingProlog(board: Array[Array[String]]): (Boolean, Array[Array[String]]) = {
 val consultQuery =new Query("consult('C:/Users/madyelzainy/OneDrive/Documents/New folder (2)/GameEngineInScala/src/main/scala/8queens_solver.pl')")
 consultQuery.hasSolution

  val array_to_be_solved: String = constructPrologList(board)
  System.out.println(array_to_be_solved)
  val prolog_query = "Qs = " + array_to_be_solved + " ,eight_queens(Qs), maplist(between(1,8), Qs)."
  val query = new Query(prolog_query)
  if (query.hasSolution) {
    val solution = query.oneSolution()
    val rows_array = solution.get("Rows")
    val solved_board = convertToBoard(rows_array)
    (true, solved_board)
  } else {
    (false, board)
  }
}
