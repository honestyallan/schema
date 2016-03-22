/**
 * Created by allan_chan on 2015/7/15.
 */
class Employee extends Person{
  var salary = 0.0
  override def toString = getClass.getName+"[name="

  def arrayMake[T: Manifest](first : T ,second: T )={
    var r =new Array[T](2)
    r(0)= first
    r(1) = second
    r
  }



}

object  Employee{
  def main(args: Array[String]) {
//    (x:Double)=>x*3
//
//      val triple = (x:Double) =>x*3
    var employee = new Employee()
      employee.arrayMake(1,2).foreach(println)
  }
//  def  triple(x:Double) = 3*x
}

