/**
 * Created by allan_chan on 2015/8/27.
 */
object Type_con {
  def main(args: Array[String]) {
    def rocky[T](i:T)(implicit ev: T<:<java.io.Serializable) ={
      println("Life is short , you need spark!")
    }
    rocky("spark")
//    rocky(100)
  }
}
