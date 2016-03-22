
/**
 * Created by allan_chan on 2015/7/28.
 */
object Demo2 {
  def oncePerSecond(callback:()=>Unit){
    while (true){
      callback()
      Thread sleep 1000
    }
  }

  def timeFiles(): Unit={
    println("time files like an arrow...")
  }

  def cycle(r: Array[Float], cacl: Float => Float) : Map[Float, Float] = {
    var result: Map[Float, Float] = Map()

    r.foreach(b => result += (b -> cacl(b)))
    result
  }

  def cycle2(r: Array[Float] )(cacl: Float => Float) : Map[Float, Float] = {
    var result: Map[Float, Float] = Map()

    r.foreach(b => result += (b -> cacl(b)))
    result
  }

  def main(args: Array[String]) {
      //    oncePerSecond(timeFiles)
      //    oncePerSecond(()=>println("time files like an arrow...."))
      //
      //    println("圆周长：" + cycle(Array(1.0f, 2.3f, 4.5f), e => 2 * 3.14f * e))
      //    println("圆面积：" + cycle(Array(1.0f, 2.3f, 4.5f), e => 3.14f * e * e))
      //变成了一个不全函数
      val c21 = cycle2(Array(1.0f, 2.3f, 4.5f)) _
      println(c21(e =>  2 * 3.14f * e))
      println(c21{e => 3.14f * e * e})

      var high = 2.0f
      val caclCylinderVolume = (e: Float) => println(3.14f * e * e * high)
      val r = Array(1.0f, 2.3f, 4.5f)

      r.map(caclCylinderVolume)

    high = 3.0f
    r.map(caclCylinderVolume)
  }
}
