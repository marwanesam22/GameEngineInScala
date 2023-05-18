import org.jpl7.{Atom, Query, Term}

def construct_prolog_array(board: Array[Array[String]]): String = {
  var array_converted_to_string = "["
  for(i <- 0 until(board.length)){
    array_converted_to_string += "["
    for (j <- 0 until(board(0).length)){
      if(board(i)(j) == "-1"){
        array_converted_to_string += "_"
      }else if(board(i)(j).charAt(board(i)(j).length-1) == 'N') {
        array_converted_to_string += board(i)(j).init
      }else{
        array_converted_to_string += board(i)(j)
      }
      if(j != board(0).length-1){
        array_converted_to_string += ","
      }else{
        if(i == board.length-1){
          array_converted_to_string +=  "]"
        }else{
          array_converted_to_string +=  "],"
        }
      }
    }
  }
  array_converted_to_string += "],"
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
def convert_to_board(rows: Term): Array[Array[String]] = {
  val list_of_numbers_in_term = extractNumbers(rows)
  var index: Int = 0
  var new_board = Array.ofDim[String](9, 9)
  for(i <- 0 until(9)){
    for(j <- 0 until(9)){
      new_board(i)(j) = list_of_numbers_in_term(index).toString
      index += 1
    }
  }
  new_board
}
def solve_using_prolog(board: Array[Array[String]]): (Boolean, Array[Array[String]]) = {
  val consultQuery = new Query("consult('E:/Semester 4/Paradigms/GameEngineInScala/src/main/scala/Sudoku_Solver.pl')")
  consultQuery.hasSolution

  val array_to_be_solved: String = construct_prolog_array(board)
  val prolog_query = "Rows = " + array_to_be_solved + "sudoku(Rows), maplist(label, Rows)."
  val query = new Query(prolog_query);
  if(query.hasSolution){
    val solution = query.oneSolution();
    val rows_array = solution.get("Rows")
    val solved_board = convert_to_board(rows_array);
    (true, solved_board)
  }else{
    (false, board)
  }
}