
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by allan_chan on 2015/7/7.
 */
object hello {
  def main(args: Array[String]) {
    println("hello world")
    val pair = ( 100,"scala","spark")
    println(pair._1)
    println("pair = " + pair._2)

    val array = Array(1,2,3,4,5,6)
    for (i<-0 until(array.length)){
      print(array(i))
    }
println()
    for (element<-array){
      print(element)
    }
println()
    val ages= Map("a"-> "scala","b"->"spark")
    for((k,v)<-ages){
        println("key is "+k+" ,value is "+ v)
    }
println()
    for((k,_)<-ages){
      println(" key  is "+k )
    }

    println()

    source("d:\\wifi.txt")

    println("--------------------forMethod---------------")
    forMethod()
    println("--------------------forMethod2---------------")
    forMethod2()
    arrayOperation()

    val pair2 = (90,"tuples")
    println(pair2._1)
    println(pair2._2)

    val (myVar1: Int, myVar2: String) = Pair(40, "Foo")
    println("myVar1 "+ myVar1+" myVar2 "+ myVar2 )
  }

  def source(file:String ): Unit ={
      //      Source.fromFile("D:\\wifi.txt")
      val abc =  Source.fromFile(file)
      for(line<-abc.getLines()){
        print(line)
      }
  }


  def forMethod(): Unit ={
    for(i<-1 to 2; j<-1 to 2){
      print((i*100+j)+" ")
    }
  }
  def forMethod2(): Unit ={
    for(i<-1 to 2; j<-1 to 2 if i!= j){
      print((i*100+j)+" ")
    }
  }

  def arrayOperation(): Unit ={
      val sums  = new Array[Int](10)
      val a = new Array[String](10)
      val s  = Array("Hello","World")
     s(0)= "GoodBye"

    val b = ArrayBuffer[Int](10)
    b+=1
    b+=(1,2,3,4,5,6 )
    b++= Array(8,13,15)
    b.trimEnd(5)

    b.insert(5,6)


  }
  val i =3
  val a = if(i<0)-1 else 1
//不可变
  val  scores = Map("Alice"->10,"Bob"->3,"Cindy"->8)
  //可变
  val scores1 = scala.collection.mutable.Map("Alice"->10,"Bob"->3,"Cindy"->8)
//空的映射
  val scores2 = new mutable.HashMap[String,Int]()
  scores2.getOrElse("Alice",0)
}
