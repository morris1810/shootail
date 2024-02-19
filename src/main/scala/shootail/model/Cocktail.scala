package shootail.model

import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer


class Cocktail(_name: String, val ingredients: List[Ingredient], val recipe: List[String]) {
  var name = new StringProperty(_name)

  override def toString: String =  name.value
}

object Cocktail {

  val Mojito: Cocktail = new Cocktail(
    "Mojito",
    List(Liquor.Rum, Fruit.Lime, Syrup.Vanilla, SoftDrink.TonicWater),
    List(
      "Combine mint and simple syrup in the bottom of a cocktail shaker, and muddle gently.",
      "Add rum, lime juice, and ice, and shake briefly.",
      "Strain into a Collins glass with ice, and top with soda water.",
      "Garnish with mint sprig"
    )
  )

  val Margarita: Cocktail = new Cocktail(
    "Margarita",
    List(Liquor.Tequila, Liquor.Cointreau, Fruit.Lime),
    List(
      "If using a salt rim, rub a lime wedge around the rim of a double Old Fashioned glass, then roll the outside in sea salt.",
      "Combine tequila, Cointreau, and lime juice in a cocktail shaker with ice, and shake vigorously.",
      "Strain into the glass over fresh ice.",
      "Garnish with a lime wedge."
    )
  )

  val WhiskeySour: Cocktail = new Cocktail(
    "Whiskey Sour",
    List(Liquor.Whiskey, Fruit.Lemon, Syrup.Vanilla),
    List(
      "Combine whiskey, lemon, simple syrup, and egg white (if using) in a cocktail shaker with ice, and shake vigorously.",
      "Strain into a double Old Fashioned glass over fresh ice.",
      "Garnish with a cherry."
    )
  )

  val GinAndTonic: Cocktail = new Cocktail(
    "Gin and Tonic",
    List(Liquor.Gin, SoftDrink.TonicWater, Fruit.Lime),
    List(
      "Combine gin and tonic in a Collins glass filled with ice.",
      "Garnish with a lime wedge."
    )
  )

  val Daiquiri: Cocktail = new Cocktail(
    "Daiquiri",
    List(Liquor.Rum, Fruit.Lime, Syrup.Vanilla),
    List(
      "Combine rum, lime juice, and simple syrup in a shaker with ice.",
      "Shake well, and strain into a cocktail glass.",
      "Garnish with a lime wedge."
    )
  )

  val DarkNStormy: Cocktail = new Cocktail(
    "Dark ‘n Stormy",
    List(Liquor.Rum, SoftDrink.GingerAle, Fruit.Lime),
    List(
      "In a Collins glass filled with ice, combine rum, ginger ale, and lime juice.",
      "Garnish with a lime wedge."
    )
  )

  val Sidecar: Cocktail = new Cocktail(
    "Sidecar",
    List(Liquor.Brandy, Liquor.Cointreau, Fruit.Lemon),
    List(
      "Wet rim of a chilled cocktail glass with a lemon wedge and roll in sugar.",
      "Combine all ingredients in a cocktail shaker with ice, and shake vigorously.",
      "Strain into sugared glass."
    )
  )

  val cocktails: List[Cocktail] = List(Mojito, Margarita, WhiskeySour, GinAndTonic, Daiquiri, DarkNStormy, Sidecar)

}





