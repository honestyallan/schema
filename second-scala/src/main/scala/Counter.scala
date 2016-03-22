/**
 * Created by allan_chan on 2015/7/14.
 */
class Counter {

  private  var value= 0
  def increment(): Unit ={
    value+=1
  }

  def current() = value
}

object  Counter{

}
