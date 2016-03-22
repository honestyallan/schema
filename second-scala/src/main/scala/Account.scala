/**
 * Created by allan_chan on 2015/7/14.
 */
object Account {
  private var lastNumber = 0

  def newUniqueNumber() = {
    lastNumber+=1
    lastNumber
  }
}
class  Account{
   var id = Account.newUniqueNumber()
  private var balance = 0.0
  def deposit(amount:Double): Unit ={
    balance+= amount
  }
}
