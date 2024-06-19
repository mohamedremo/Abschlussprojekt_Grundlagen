package pub
import uno.*
import util.*
import kotlin.random.Random
/**
 * The Class "Customer" is to instance Customer of the Pub.
 * @property name The name of the Customer.
 * @property age The age of the Customer.
 * @property money The Money of the Customer (Generated random between 50 - 200)
 * @property bladder The bladder of the Customer
 * @property alcoholLevel The Alcohol Level of the Customer
 * @property hand The Simulated Card hand for UNO
 * @property unoWins The Uno Wins of the Customer
 * @property truePoints The True points of the Customer (Gain from purchases)
 * @property nickname The LogIn name of the Customer
 * @property password The LogIn password of the Customer
 * @property isLoggedIn The Status from LogIn of the Customer
 *
 * @author Mohamed Remo
 * @version 1.1
 */
class Customer(
    var name: String,
    var age: Int,
    var money: Double = Random.nextDouble(50.0,200.0),
    var bladder: Int = 0,
    var alcoholLevel: Int = 0,
    var hand: MutableList<Card> = mutableListOf(),
    var unoWins: Int = 0,
    var truePoints: Int = 0,
    var nickname: String = "",
    var password: String = "",
    var isLoggedIn: Boolean = false,
){
    fun emptyBladder(){
        if (this.bladder > 1) {
            println(S_CUS_BLADDER)
            emojiAnimate("🚽")
            this.bladder = 0
            this.alcoholLevel -= 20
        }else
            println(S_CUS_BLADDER2)
        stopM()
        println(S_PRESS_ENTER)
        readln()
    }

    fun checkPromill(){
        println(S_CUS_PROMILLS2(this))
        if (this.alcoholLevel >= 100){
            println(S_CUS_SLOW_DOWN)
            emojiAnimate("🥴")
        }else
            println(S_CUS_ALL_GOOD)
        println(S_PRESS_ENTER)
        readln()
    }

    fun drink(customer: Customer, drink: Drink) {
        if (drink.price > customer.money){
            println(S_CUS_DRINK)
        }else {
            customer.money -= drink.price
            customer.truePoints += Random.nextInt(20,40)
            customer.bladder += 10
            println(S_CUS_DRINK2(customer,drink))
            drinkAnimate(drink)
            if(drink.category == DrinkCategory.ALCOHOLIC)
                this.alcoholLevel += Random.nextInt(10,20)
        }
    }

    fun eat(customer: Customer, food: Food) {
        if (food.price > customer.money){
            println(S_CUS_EAT)
        }else{
            this.truePoints += Random.nextInt(20,40)
            customer.money -= food.price
            println(S_CUS_EAT2(customer,food))
        }
        eatAnimate(food)
    }

    fun drinkWater () {
        if (this.alcoholLevel >= 10) {
            emojiAnimate("🚰")
            println(S_CUS_PROMILLS)
            this.alcoholLevel -= 10
            println(S_CUS_PROMILLS2(this))
            stopL()
        }else {
            println(S_CUS_PROMILLS3(this))
            emojiAnimate("🚰")
            stopL()
        }
    }
}