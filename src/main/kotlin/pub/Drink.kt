package pub
/**
 * This Class inherits from Super Class "Product" the only change here is the var "category".
 *
 * @property name The name of the Drink
 * @property price The price of the Drink.
 * @property category The category of the Drink
 * @property DrinkCategory enumClass for the Categories
 *
 * @author Mohamed Remo
 * @version 1.1
 */
class Drink(
    name: String,
    price: Double,
    var category: DrinkCategory,
): Product(name,price)

enum class DrinkCategory{
    NOTALCOHOLIC,
    ALCOHOLIC,
}