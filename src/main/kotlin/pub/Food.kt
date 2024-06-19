package pub
/**
 * This Class inherits from Super Class "Product" the only change is a new variable "category".
 * @property name The name of the Food.
 * @property price The price of the Food.
 * @property category The Category of the Food.
 * @property EatCategory enumClass for the Categories
 *
 * @author Mohamed Remo
 * @version 1.1
 */
class Food(
    name: String,
    price: Double,
    var category: EatCategory,
): Product(name,price)

enum class EatCategory{
    NOTVEGAN,
    VEGAN
}