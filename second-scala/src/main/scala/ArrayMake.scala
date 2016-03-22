import scala.reflect.ClassTag

/**
 * Created by allan_chan on 2015/8/27.
 */
class ArrayMake {
  def arrayMake[T: Manifest](first:T,second:T) = {
    val r  = new Array[T](2)
    r(0) = first
    r(1) = second
    r
  }

  def manif[T](x:List[T])(implicit m : Manifest[T])= {
    if(m<:<manifest[String])
      println("List String")
    else
      println("some other type")
  }

  def makeArray[T: ClassTag](elem: T*) = Array[T](elem: _*)

}

object  ArrayMake{
  def main(args: Array[String]) {
  val data = new ArrayMake()
   /* data.arrayMake(1,2).foreach(println)
    data.manif(List("Spark","Hadoop"))
    data.manif(List(1,2))
    data.manif(List("scala",3))*/

    data.makeArray(1,2,3).foreach(println)
    /*class A[T]
      val m  = manifest[A[String]]
      println(m)
      val cm  = classManifest[A[String]]
      println(cm)*/
  }
}
