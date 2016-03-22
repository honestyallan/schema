/**
 * Created by allan_chan on 2015/7/14.
 */
class Person {
  private var age = 0
  private var name = ""
  def this(name: String){
    this()
    this.name = name
  }

def this(name:String ,age :Int){
  this(name)
  this.name = name
  this.age = age
}

}
