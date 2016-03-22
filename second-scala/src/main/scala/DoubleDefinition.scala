/**
 * Created by allan_chan on 2015/9/10.
 */

class  M_A[T]
class  M_B[T]
object DoubleDefinition {
//类型约束
  def rocky[T](i:T)(implicit ev: T<:<java.io.Serializable): Unit ={
    println("Lift is short, you need spark")
  }


  def main(args: Array[String]) {
    implicit val a  = new M_A[Int]
    implicit val b = new M_B[Int]
    def foo [T: M_A:M_B](i:T) = println("ok")
    foo(2)

    rocky("spark")
  }

}
