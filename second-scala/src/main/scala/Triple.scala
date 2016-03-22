/**
 * Created by allan_chan on 2015/7/16.
 */
object Triple extends App {
  override def main(args: Array[String]) {
   /* (1 to 9).map("*"*_).foreach(println _)
    (1 to 9).filter(_%2 ==0)*/
    val triple = mulBy(3)
    val half  = mulBy(0.5)
    println(triple(14)+"  "+half(14) )
  }

  def mulBy(factor:Double) = (x:Double) => factor*x
}
