/**
 * Created by allan_chan on 2015/8/27.
 */
class Pair_Ordering[T: Ordering](val first:T,val second : T) {
  def bigger(implicit ordering:Ordering[T])= {
    if(ordering.compare(first,second)>0)first else second
  }

}

object  Context_Bounds{
  def main(args: Array[String]) {
    val pair = new Pair_Ordering[String]("Spark","Hadoop")
    println(pair.bigger)
    val pair2 = new Pair_Ordering[Int](1,3)
    println(pair2.bigger)
  }
}
