/**
 * Created by allan_chan on 2015/7/16.
 */
trait Friendly {
  def greet = "hi"
}

class dog extends  Friendly{
 override  def greet ={"Woof"}
}

class HungryDog extends dog{
  override def greet = super.greet+   "  I'd like to eat my own dog food"
}

trait  ExclamatoryGreeter extends Friendly{
 override def greet = super.greet+ "!"
}

object  FriendlyApp {
  def main(args: Array[String]) {
    var pet : Friendly = new dog
    println(pet.greet)

    pet = new HungryDog
    println(pet.greet)

    pet = new dog with ExclamatoryGreeter
    println(pet.greet)

    pet = new HungryDog with ExclamatoryGreeter
    println(pet.greet)
  }
}
