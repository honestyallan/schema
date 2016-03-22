/**
 * Created by allan_chan on 2016/3/22.
 */
object MutiTable2 {
  def main(args: Array[String]): Unit = {
    printMultiTable()
//    multiTable();
//    mutiTable2();
//    multiTable3();
  }

  def printMultiTable(): Unit ={
    var i =1;
    while (i<=9){
      var j = 1;
      while (j<=i){
        val result = (i * j).toString();
        var len = result.length;
        while (len<4) {
          print(" ")
          len+=1;
        }
        print(i+" * "+j+" = "+result)
        j+=1;
      }
      println()
      i+=1;
    }
  }

  def makeRowSeq(row: Int) =
    for (col <- 1 to row) yield {
      val prod = (row * col).toString()
      val padding = " " * (4 - prod.length())
      col + "*" + row + "=" + prod + padding
    }
  def makeRow(row: Int) = makeRowSeq(row).mkString
  def multiTable() = {
    val tableSeq = for (row <- 1 to 9) yield makeRow(row)
    println(tableSeq.mkString("\n"))
  }

    def mutiTable2(): Unit ={
      for (row <- 1 to 9; col <- 1 to row) {
        val prod = (row * col).toString()
        val padding = " " * (4 - prod.length())
        print(col + "*" + row + "=" + prod + padding)
        if (row == col) println()
      }
    }

    def multiTable3() : Unit= {(
      for (
        i <- 1 to 9;
        j <- 1 to i;
        ss = s"$j*$i=${i * j}\t"
      ) yield {
        if (j == i) s"$ss\n" else ss
      }).foreach(print);
    }

}
