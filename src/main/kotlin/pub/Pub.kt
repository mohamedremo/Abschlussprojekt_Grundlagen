package pub
/**
 * The Class Pub simulates the Pub.
 * In the pub, you can order drinks and food, play Uno an view + adjust your blood alcohol level,
 * for example, by drinking water or emptying your bladder.
 * A higher alcohol level causes the strings on the console to be displayed incorrectly and jumbled, simulating the
 * effects of drunkenness.
 *
 * @property visitors All Visitors of the Pub
 * @property userData Saves the LogIn Data of each Visitor
 * @property menu Complete Menu includes all Drinks and PUB.Food/Snacks
 * @property drinkCard Filters the Menu - Datatype "DRINK"
 * @property foodCard Filters the Menu - Datatype "FOOD"

 * @author Mohamed Remo
 * @version 1.1
 */
import util.farben.*
import kotlin.random.Random
import uno.*
import util.*
import java.time.*
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

open class Pub (
    var visitors: MutableList<Customer> = mutableListOf(
        Customer("Konsti", 20, alcoholLevel = 25, password = "Konsti007", nickname = "Konsti"),
        Customer("Karl", 65, alcoholLevel = 20, password = "Karl007", nickname = "Karl"),
        Customer("Mark Egal", 20, alcoholLevel = 75, password = "Mark007", nickname = "Mark"),
        Customer("Max Mustermann", 18, alcoholLevel = 30,password = "Maxi007", nickname = "Maxi"),

        ),
    var userData: MutableMap<String, String> = mutableMapOf(),
    var menu: MutableList<Product> = mutableListOf(

// Alkoholische Drinks
        Drink("Martini", 2.99, DrinkCategory.ALCOHOLIC),
        Drink("Mojito", 3.50, DrinkCategory.ALCOHOLIC),
        Drink("Pina Colada", 5.00, DrinkCategory.ALCOHOLIC),
        Drink("Cuba Libre", 4.50, DrinkCategory.ALCOHOLIC),
        Drink("Daiquiri", 4.00, DrinkCategory.ALCOHOLIC),
        Drink("Old Fashioned", 5.50, DrinkCategory.ALCOHOLIC),
        Drink("Margarita", 3.75, DrinkCategory.ALCOHOLIC),
        Drink("Bloody Mary", 4.25, DrinkCategory.ALCOHOLIC),
        Drink("Gin Tonic", 3.80, DrinkCategory.ALCOHOLIC),
        Drink("Whiskey Sour", 4.60, DrinkCategory.ALCOHOLIC),
        Drink("Tequila Sunrise", 4.20, DrinkCategory.ALCOHOLIC),
        Drink("Mai Tai", 5.75, DrinkCategory.ALCOHOLIC),
        Drink("Cosmopolitan", 4.90, DrinkCategory.ALCOHOLIC),
        Drink("Long Island Iced Tea", 6.00, DrinkCategory.ALCOHOLIC),
        Drink("Bellini", 3.70, DrinkCategory.ALCOHOLIC),
        Drink("Caipirinha", 4.50, DrinkCategory.ALCOHOLIC),

// Nicht-alkoholische Drinks
        Drink("Mint Lemonade", 3.20, DrinkCategory.NOTALCOHOLIC),
        Drink("Cucumber Cooler", 4.70, DrinkCategory.NOTALCOHOLIC),
        Drink("Lavender Lemonade", 3.50, DrinkCategory.NOTALCOHOLIC),
        Drink("Ginger Beer", 3.80, DrinkCategory.NOTALCOHOLIC),
        Drink("Coconut Cooler", 4.50, DrinkCategory.NOTALCOHOLIC),
        Drink("Peach Bellini", 3.90, DrinkCategory.NOTALCOHOLIC),
        Drink("Raspberry Mojito", 4.30, DrinkCategory.NOTALCOHOLIC),
        Drink("Strawberry Daiquiri", 4.70, DrinkCategory.NOTALCOHOLIC),
        Drink("Tropical Punch", 4.20, DrinkCategory.NOTALCOHOLIC),
        Drink("Watermelon Margarita", 5.00, DrinkCategory.NOTALCOHOLIC),

// Vegane Gerichte
        Food("Nuss-Mix", 3.99, EatCategory.VEGAN),
        Food("Vegetable Stir Fry", 6.00, EatCategory.VEGAN),
        Food("Pasta Primavera", 8.25, EatCategory.VEGAN),
        Food("Quinoa Salad", 6.75, EatCategory.VEGAN),
        Food("Vegan Burger", 8.50, EatCategory.VEGAN),
        Food("Stuffed Bell Peppers", 7.99, EatCategory.VEGAN),
        Food("Lentil Soup", 5.50, EatCategory.VEGAN),
        Food("Vegetable Curry", 9.50, EatCategory.VEGAN),
        Food("Falafel Wrap", 6.25, EatCategory.VEGAN),
        Food("Grilled Veggie Sandwich", 7.25, EatCategory.VEGAN),
        Food("Vegan Sushi", 8.75, EatCategory.VEGAN),

// Nicht-vegane Gerichte
        Food("Thunfisch Pizza", 5.99, EatCategory.NOTVEGAN),
        Food("Caesar Salad", 6.50, EatCategory.NOTVEGAN),
        Food("Spaghetti Carbonara", 7.99, EatCategory.NOTVEGAN),
        Food("Margherita Pizza", 5.50, EatCategory.NOTVEGAN),
        Food("Chicken Wings", 6.75, EatCategory.NOTVEGAN),
        Food("Cheeseburger", 8.50, EatCategory.NOTVEGAN),
        Food("Grilled Salmon", 9.99, EatCategory.NOTVEGAN),
        Food("Beef Tacos", 7.50, EatCategory.NOTVEGAN),
        Food("BBQ Ribs", 9.99, EatCategory.NOTVEGAN),
        Food("Greek Salad", 5.99, EatCategory.NOTVEGAN),
        Food("Clam Chowder", 7.25, EatCategory.NOTVEGAN),
        Food("Beef Stroganoff", 9.50, EatCategory.NOTVEGAN),
        Food("Chicken Alfredo", 9.75, EatCategory.NOTVEGAN),
        Food("Fish and Chips", 8.50, EatCategory.NOTVEGAN)),
    var drinkCard:List<Drink> = menu.filterIsInstance<Drink>().sortedBy { it.price },
    var foodCard:List<Food> = menu.filterIsInstance<Food>().sortedBy { it.price },
)
{
    init {
        repeat(visitors.size){
            userData.put(visitors[it].nickname,visitors[it].password)
        }
    }
    // if else Zweig um zu schauen welches Menü aufgerufen werden soll (NORMAL o. DRUNK)
    fun menu(customer: Customer){
        if (customer.alcoholLevel >= 30 && customer.isLoggedIn)
            drunkMenu(customer)
        else if (customer.isLoggedIn)
            mainMenu(customer)
        else if (!customer.isLoggedIn)
            loginScreen(customer)
    }
    //Standard Main Menu
    fun mainMenu(customer: Customer){
        space()
        println(S_WHATS_NEXT(customer).center())
        if (customer.bladder >= 40){
            println(S_DISGRACED)
            customer.money -= 10
            startPub()
        }
        println()
        print(S_MENU)
        try {
            var choice = readln().toInt()
            when (choice) {
                1 -> {
                    space()
                    print(S_ORDERMENU)
                    var choice1 = readln().toInt()
                    when (choice1){
                        1 -> {
                            space()
                            print(S_DRINKORDERMENU)
                            var choice2 = readln().toInt()
                            when (choice2){
                                1 -> {
                                    space()
                                    buyDrinks(customer, drinkCard.filter { it.category == DrinkCategory.ALCOHOLIC })
                                    menu(customer)
                                }
                                2 -> {
                                    space()
                                    buyDrinks(customer, drinkCard.filter { it.category == DrinkCategory.NOTALCOHOLIC })
                                    menu(customer)
                                }
                                3 -> {
                                    space()
                                    buyDrinks(customer, drinkCard)
                                    menu(customer)
                                }
                                else -> {
                                    space()
                                    println(S_INVALID_OPTION)
                                    menu(customer)
                                }
                            }
                        }
                        2 -> {
                            space()
                            print(S_EATORDERMENU)
                            var choice3 = readln().toInt()
                            when (choice3){
                                1 -> {
                                    space()
                                    buyFoods(customer,foodCard.filter { it.category == EatCategory.VEGAN })
                                    menu(customer)
                                }
                                2 -> {
                                    space()
                                    buyFoods(customer,foodCard.filter { it.category == EatCategory.NOTVEGAN })
                                    menu(customer)
                                }
                                3 -> {
                                    space()
                                    buyFoods(customer,foodCard)
                                    menu(customer)}
                                else -> {
                                    space()
                                    println(S_INVALID_OPTION)
                                    menu(customer)
                                }
                            }
                        }
                        3 -> {
                            space()
                            printMenu()
                            println(S_PRESS_ENTER)
                            readln()
                            menu(customer)
                        }
                        else -> {
                            space()
                            println(S_INVALID_OPTION)
                            menu(customer)
                        }
                    }
                    buyDrinks(customer,drinkCard.filter { it.category == DrinkCategory.ALCOHOLIC })
                    menu(customer)
                } // Zeigt die Karte der Drinks und man kann sich einen auswählen um zu kaufen
                2 -> {
                    space()
                    if (visitors.isEmpty())
                        println(S_NO_ONE_THERE)
                    else
                        Uno().round(customer,visitors.filter { it != customer }.random())
                        menu(customer)
                } // Zeigt die Karte der Speisen und man kann sich einen auswählen um zu kaufen
                3 -> {
                    space()
                    randomDrink(customer)
                    menu(customer)
                }
                4 -> {
                    space()
                    customer.drinkWater()
                    menu(customer)
                }
                5 -> {
                    space()
                    customer.emptyBladder()
                    menu(customer)
                }
                6 -> {
                    space()
                    customer.checkPromill()
                    menu(customer)
                }
                7 -> {
                    space()
                    logout(customer)
                }
                else -> {
                    space()
                    println(S_INVALID_INPUT2)
                    menu(customer)
                }
            }
        } catch (e: NumberFormatException) {
            space()
            println(S_JUST_NUMBERS)
            menu(customer)
        }

    }
    // Gleiches Menü wie main nur mit drunkReading Funktion
    fun drunkMenu(customer: Customer){
        space()
        drunkReading(customer, S_WHATS_NEXT(customer) +  "\n" + (S_DRUNK_MENU))
        println(S_DRUNK_MENU2)
        try {
            var choice = readln().toInt()
            when (choice) {
                1 -> {
                    space()
                    drunkReading(customer,S_ORDERMENU)
                    var choice1 = readln().toInt()
                    when (choice1){
                        1 -> {
                            space()
                            drunkReading(customer, S_DRINKORDERMENU)
                            var choice2 = readln().toInt()
                            when (choice2){
                                1 -> {
                                    space()
                                    buyDrinks(customer, drinkCard.filter { it.category == DrinkCategory.ALCOHOLIC })
                                    menu(customer)
                                }
                                2 -> {
                                    space()
                                    buyDrinks(customer, drinkCard.filter { it.category == DrinkCategory.NOTALCOHOLIC })
                                    menu(customer)
                                }
                                3 -> {
                                    space()
                                    buyDrinks(customer, drinkCard)
                                    menu(customer)
                                }
                                else -> {
                                    space()
                                    println(S_INVALID_OPTION)
                                    menu(customer)
                                }
                            }
                        }
                        2 -> {
                            space()
                            drunkReading(customer,S_EATORDERMENU)
                            var choice3 = readln().toInt()
                            when (choice3){
                                1 -> {
                                    space()
                                    buyFoods(customer,foodCard.filter { it.category == EatCategory.VEGAN })
                                    menu(customer)
                                }
                                2 -> {
                                    space()
                                    buyFoods(customer,foodCard.filter { it.category == EatCategory.NOTVEGAN })
                                    menu(customer)
                                }
                                3 -> {
                                    space()
                                    buyFoods(customer,foodCard)
                                    menu(customer)
                                }
                                else -> {
                                    space()
                                    println(S_INVALID_OPTION)
                                    menu(customer)
                                }
                            }
                        }
                    }
                    space()
                    buyDrinks(customer,drinkCard.filter { it.category == DrinkCategory.ALCOHOLIC })
                    menu(customer)
                } // Zeigt die Karte der Drinks und man kann sich einen zum Kaufen auswählen
                2 -> {
                    space()
                    if (visitors.isEmpty())
                    drunkReading(customer,S_NO_ONE_THERE)
                else
                    Uno().round(customer,visitors.filter { it != customer }.random())
                    menu(customer)

                } // Zeigt die Karte der Speisen und man kann sich eine zum Kaufen auswählen
                3 -> {
                    space()
                    randomDrink(customer)
                    menu(customer)
                }
                4 -> {
                    space()
                    customer.drinkWater()
                    menu(customer)
                }
                5 -> {
                    space()
                    customer.emptyBladder()
                    menu(customer)
                }
                6 -> {
                    space()
                    customer.checkPromill()
                    menu(customer)
                }
                7 -> {
                    space()
                    logout(customer)
                }
                else -> {
                    space()
                    println(S_INVALID_OPTION)
                    menu(customer)
                }
            }
        } catch (e: NumberFormatException) {
            println(S_JUST_NUMBERS)
            menu(customer)
        }

    }
    // printet die Stats und Eigenschaften aller Besucher
    fun allVisitors(){
        for (visitor in visitors){
            println(S_STATS(visitor))
        }
    }
    /*Fügt einen neuen Besucher/in zu dem Pub hinzu.
    vorher wird gecheckt, ob er/sie 18 Jahre alt ist und das Pub
    schon die Maximale-Kapazität von 15 Personen erreicht hat.
     */
    fun addCustomer(customer: Customer) {
        if (customer.age >= 18 && visitors.size < 15) {
            println(S_WELCOME(customer))
            visitors.add(customer)
        }else if(visitors.size > 15){
            println(S_FULL)
        }
        else
            println(S_NOT_18)
    }
    /*Gibt dem Besucher/Kunden einen zufälligen Drink mit 20%nachlass
    */
    fun randomDrink(customer: Customer) {
        if (customer in visitors){
            println(S_RANDOM_DRINK)
            stopS()
            var choice = readln()
                    if ('j' in choice){
                        var random = menu.filterIsInstance<Drink>().random()
                        println(S_GET_DRINK(random))
                        stopM()
                        customer.drink(customer,random)
                        println(S_PRESS_ENTER)
                    }else if ('n' in choice){
                        println(S_RANDOM_DRINK_NO)
                        println()
                        mainMenu(customer)
                    }else
                        println(S_INVALID_INPUT)
                        choice = readln()
                        if ('j' in choice){
                            randomDrink(customer)
                        }else
                            mainMenu(customer)
        }else {
            println("Sorry")
        }

    }
    //printet die komplette Getränke Karte
    fun buyDrinks(customer: Customer, drinkCard: List<Drink>) {
        println(S_DRINK_CARD)
        repeat(9){
            println("$BOXED ${it+1}. ${drinkCard[it].name.padEnd(32,' ')} " +
                    "${String.format("%.2f",drinkCard[it].price)}€$RESET")
            stopS()
        }
        repeat(drinkCard.size-9){
            println("$BOXED ${it+10}. ${drinkCard[it+9].name.padEnd(31,' ')} " +
                    "${String.format("%.2f",drinkCard[it+9].price)}€$RESET")
            stopS()
        }
        print(S_WHICH_DRINK(drinkCard))
        try {
            var selectedDrink = readln().toInt()-1
            println()
            customer.drink(customer,drinkCard[selectedDrink])
        }catch (e: Exception){
            print(e)
        }
    }
    //printet die komplette Essens Karte
    fun buyFoods(customer: Customer, foodCard: List<Food>) {
        println(S_FOOD_CARD)
        repeat(9){
            println("$BOXED ${it+1}. ${foodCard[it].name.padEnd(33,' ')}" +
                    "${String.format("%.2f",foodCard[it].price)}€$RESET")
            stopS()
        }
        repeat(foodCard.size-9){
            println("$BOXED ${it+10}. ${foodCard[it+9].name.padEnd(31,' ')} " +
                    "${String.format("%.2f",foodCard[it+9].price)}€$RESET")
            stopS()
        }
        print(S_WHICH_FOOD(foodCard))
        try {
            var selectedFood = readln().toInt()-1
            println()
            customer.eat(customer,foodCard[selectedFood])
        }catch (e: Exception){
            print(e)
        }
    }
    //printet beide Karten (Getränke & Speisekarte) nebeneinander
    fun printMenu(){
        println(S_MENU_CARD)
        repeat(9){
            println("$BOXED ${it+1}. ${foodCard[it].name.padEnd(32,' ')}" +
                    "${String.format("%.2f",foodCard[it].price)}€ $RESET" + " " +
                    "$BOXED ${it+1}. ${drinkCard[it].name.padEnd(31,' ')} " +
                    "${String.format("%.2f",drinkCard[it].price)}€ $RESET")
        }
        repeat(foodCard.size-9){
            println("$BOXED ${it+10}. ${foodCard[it+9].name.padEnd(30,' ')} " +
                    "${String.format("%.2f",foodCard[it+9].price)}€ $RESET"+ " " +
                    "$BOXED ${it+10}. ${drinkCard[it+9].name.padEnd(30,' ')} " +
                    "${String.format("%.2f",drinkCard[it+9].price)}€ $RESET")
        }
    }
    //20 Prozent Nachlass für zufällige Drinks
    open fun discount (drink: Drink): Double{
        return 0.8 * drink.price
    }
    /*Eine Funktion um Strings durcheinander auszugeben und
    somit, bei einem zu hohen Alkoholverzehr das Betrunken sein simuliert
     */
    fun drunkReading(customer: Customer, s:String){
        s.forEach{
            val randomValue = Random.nextInt(0,100)
            if(randomValue  < customer.alcoholLevel - 35  && it != ' '){
                print(('a'..'z').random())
            }
            else print(it)
        }
    }
    //Printet die aktuelle Zeit
    fun actualTime(){
        var time = LocalTime.now()
        var closing = LocalTime.of(23,59,59)
        var howLong = Duration.between(time,closing)
        println(S_TIME_IS(time))
        println(S_TIME_CLOSING(closing))
        println(S_TIME_LEFT(howLong))
    }
    //LogIn Funktion
    fun logIn(customer: Customer){
        space()
        if (customer.nickname.isEmpty() && customer.age >= 18){
            println(S_NO_ACCOUNT)
            readln()
            register(customer)
        }else if (customer.age >= 18){
            print(S_NICKNAME)
            var name = readln()
            print(S_PASSWORD)
            var password = readln()
            if (name == customer.nickname && password == customer.password){
                println(S_LOGIN_SUCCESS)
                emojiAnimate("✅")
                customer.truePoints += Random.nextInt(20,40)
                customer.isLoggedIn = true
                println(S_LINE)
                menu(customer)
            }else if ( name == customer.nickname && password != customer.password){
                println(S_WRONG_PASSWORD)
                logIn(customer)
            } else {
                println(S_DOESNT_EXIST)
                stopL()
                logIn(customer)
            }
        }else if (customer.age <= 18){
            println("${RED}Mindestalter 18! Versuchs nochmal...$RESET".bold())
            stopL()
            logIn(newNew())
        }
    }
    //Register Funktion
    fun register(customer: Customer){
        space()
        if (customer.nickname.isNotEmpty()){
            println(S_ALREADY_ACC)
            readln()
            logIn(customer)
        }else if (customer.age >= 18){
            println(S_WANT_ACCOUNT(customer))
            print(S_NICKNAME_REG)
            var nickname = readln()
            print(S_PASSWORD_REG)
            var passwort = readln()
            if (nickname !in userData.keys ) {
                emojiAnimate("✅")
                println(S_REGISTER_SUCCESS)
                readln()
                customer.truePoints += Random.nextInt(20,40)
                customer.nickname = nickname
                customer.password = passwort
                customer.isLoggedIn = true
                userData.put(nickname, passwort)
                menu(customer)
            }else if (customer.age <= 18){
                println("Mindestalter 18! Versuchs nochmal...")
                logIn(newNew())
            }
            else {
                println(S_ALREADY_EXIST)
                stopL()
                register(customer)
            }
        }
    }
    //LogIn Screen mit zweig um zu checken, ob man schon Registriert ist.
    fun loginScreen(customer: Customer){
        println(S_LOGIN)
        println(S_LOGIN_SELECT)
        print(S_SELECTION)
        try {
            var regOrLogin = readln().toInt()
            when (regOrLogin) {
                1 -> logIn(customer)
                2 -> register(customer)
                else -> {
                    println(S_TYPE_1OR2)
                    stopL()
                    println(S_LINE)
                    loginScreen(customer)
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }
    //logOut funktion die zum startPub Bildschirm verweist
    fun logout(customer: Customer){
        println(S_SEE_YOU)
        customer.isLoggedIn = false
        startPub()
    }
    //Legt ein neuen Customer an
    fun newNew(): Customer {
        space()
        print(S_FIRST_TIME)
        println()
        var choice = readln().lowercase()
        if ('n' in choice){
            println(S_NEWACC)
            stopM()
            println(S_YOUR_NAME)
            var name = readln()
            println(S_YOUR_AGE)
            var alter = readln().toInt()
            var customer = Customer(name, alter)
            visitors.add(customer)
            return customer
        }else if ('j' in choice) {
            print(S_WHICH_NICKNAME)
            var nickName = readln()
            var customer2 = visitors.filter { it.nickname == nickName }
            return customer2.first()
        }
        return newNew()
    }
    //Start des Pub Programms
    fun startPub(){
        space()
        print(S_SCREEN_SAVER)
        try {
            var choice = readln().toInt()
            when (choice){
                1 -> logIn(newNew())
                2 -> register(newNew())
                3 -> {
                    println(S_SEE_YOU)
                    exitProcess(0)
                }
                else -> {
                    println(S_JUST_NUMBERS)
                    startPub()
                }
            }
        }catch (e: Exception){
            println(S_INVALID_NICKNAME)
            stopM()
            startPub()
        }

    }
}

