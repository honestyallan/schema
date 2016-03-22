/**
 * Created by allan_chan on 2016/3/22.
 */
object StrCount {
  def main(args: Array[String]) {
    var longString = "aaaaabbbbcccccaaerr";
    var shortString = "a";
    var count  = 0;
    var tempString = longString;
    while(longString != null && shortString != null && shortString.length <= longString.length && tempString.contains(shortString)){
      count += 1
      tempString = tempString.substring(tempString.indexOf(shortString) + shortString.length())
      // println(tempString)
    }
    println(count)
  }
}
