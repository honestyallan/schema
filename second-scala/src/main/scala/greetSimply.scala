/**
 * Created by allan_chan on 2015/7/28.
 */
class greetSimply(greeting:String) {

//  val greeting = "Hello world"

  def greet = println(greeting)
}


object greetSimply{
  def main(args: Array[String]) {

    val g = new greetSimply("Salutations,world")
    g.greet
  }
}
