/**
 * Created by allan_chan on 2015/8/31.
 */
class Animal {
  def breathe:this.type = this
}
class Cat extends Animal{
  def eat:this.type  = this
}

object Singleton_Types{
  def main(args: Array[String]) {
    val cat = new Cat
    cat.breathe.eat
  }
}
