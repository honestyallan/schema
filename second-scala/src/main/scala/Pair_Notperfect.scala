/**
 * Created by allan_chan on 2015/8/27.
 */
/*class Pair_Notperfect[T <:Comparable[T]](val first: T,val second : T) {
    def bigger = if(first.compareTo(second)>0)first else second
}*/
class Pair_Notperfect[T <% Comparable[T]](val first: T,val second : T) {
  def bigger = if(first.compareTo(second)>0)first else second
}
class Pair_Better[T <% Ordered[T]](val first: T, val second : T){
  def bigger = if(first>second)first else second
}

//视图界定
object Pair_Notperfect{
  def main(args: Array[String]) {
    val pair  = new Pair_Notperfect("spark","hadoop")
    println(pair.bigger)

    val pair1 = new Pair_Notperfect(3,5)
    println(pair1.bigger)

    val pair2 = new Pair_Better[String]("Java","scala")
    println(pair2.bigger)

    val pair3 = new Pair_Better[Int](45,40)
    println(pair3.bigger)
  }
}
