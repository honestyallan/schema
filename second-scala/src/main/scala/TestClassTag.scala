import scala.reflect.ClassTag

/**
 * Created by allan_chan on 2015/9/23.
 */
class TestClassTag {
    def arrayMake[T:ClassTag](first:T,second : T): Unit ={
      val r =new Array[T](2)
      r(0) = first
      r(1) = second
      r
    }

    def arrayMake2[T](first:T,second: T)(implicit m: ClassTag[T]): Unit ={
      val r  = new Array[T](2)
      r(0) = first
      r(1) = second
      r
    }
}
object  TestClassTag{
      var instance = new TestClassTag()
//      instance.arrayMake(1,2).foreach(println)
}


