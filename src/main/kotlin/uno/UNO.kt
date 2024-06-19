package uno
import util.farben.*
import pub.*
import util.*

/**
 * The Class uno has a Virtual Card Game included.
 * @property deck  init block Creates all 52 Cards to play the Game
 * @property playStack All cards that are played
 * @property skipNextPlayer Boolean for the Block Card Status
 * @property listOfSounds A list containing the Sounds of the Game
 * @property listOfSoundsEnemy A list containing the Sounds for the Enemy of the Game
 *
 * @author Mohamed Remo
 * @version 1.1
 */
class Uno {
    // Deck-Variable mit einer leeren Mutable List.
    private val deck = mutableListOf<Card>()// Mutable List Variable für das Spieldeck
    private var playStack = mutableListOf<Card>() // Mutable List Variable für gespielte Karten
    private var skipNextPlayer = false // Variable, um den nächsten Spieler zu überspringen
    private var listOfSounds = listOf("RANDOM1", "RANDOM2", "RANDOM3", "RANDOM4", "RANDOM5")
    private var listOfSoundsEnemy = listOf("RANDOM1_ENEMY","RANDOM2_ENEMY","RANDOM3_ENEMY", "RANDOM4_ENEMY", "RANDOM5_ENEMY")
    // Deck erstellen mit einem Initialisierungsblock.
    init {
        println(S_GAME_ROUND.center())
        sound("UNO").start()
        stopL()
        Value.entries.forEach { value ->
            Color.entries.forEach { color ->
                deck.add(Card(color, value))}}
        deck.shuffle() // Deck nach dem Erstellen mischen.
        println(S_GAME_MIX.center())
        stopL()
        playStack.add(deck.removeFirst())// Die erste Karte wird vom Deck entfernt, nachdem es gemischt wurde
        println(S_GAME_DISTRI.center())
        println(S_GAME_FIRSTCARD(playStack).center())
        println()
        stopL()

    }
    //Diese Funktionen printed die oberste Karte auf dem Spiel Stapel
    private fun actualCard() {
        println(S_GAME_CARDNOW(playStack).center())
        println()

    }
    // Zieht eine Karte
    private fun draw(): Card {
        if (deck.isEmpty()){
            println(S_GAME_MIXAGAIN.center())
            stopM()
            val firstCard = playStack.removeFirst()
            deck.addAll(playStack)
            deck.shuffle()
            playStack.clear()
            playStack.add(firstCard)
            return deck.removeFirst()
        }
        return deck.removeFirst()
    }
    // Zieht so lange Karten, bis die Hand des jeweiligen Customers 7 erreicht hat.
    private fun drawTillFull(player: Customer) {
        repeat(7) {
            player.hand.add(deck.removeFirst())
        }
    }
    /*Zeigt die aktuellen Karten auf der Hand des Players,
    in dem Fall immer die eigenen da wir die Hand des Gegners nicht sehen wollen oder dürfen.
     */
    private fun showHand(player: Customer) {
        println(S_GAME_ACTUALRDS.center())
        repeat(player.hand.size) {
            val string = ("${it + 1}. " + player.hand[it].value.displayName + " " + player.hand[it].color.displayName)
            println(string.center())
            stopS()
        }
        println()
        stopM()
    }
    /*Hier wird mit einer "read" gefragt,
    welche Karte man setzen möchte. Ist die Eingabe ungültig, sprich passt die Karte nicht wird der else block
    ausgeführt. Gib man jetzt Zahlen außerhalb des Indexes ein oder keine Zahlen wird der catch block ausgeführt.*/
    private fun throwCard(player: Customer){
        println(S_GAME_WHICHCARD(player).center())
        try {
            val choice = readln().toInt() - 1
            if (player.hand[choice].value == playStack.first().value || player.hand[choice].color == playStack.first().color) {
                val card = player.hand.removeAt(choice)

                if (card.value == Value.BLOCK) {
                    skipNextPlayer = true
                }else{
                    skipNextPlayer = false
                }

                playStack.addFirst(card)
                println()
                println(S_GAME_THROWINFO(player,playStack).center())
                println()
                stopL()
            } else {
                println(S_GAME_INVALIDCARD.center())
                println()
                stopL()
                throwCard(player)
            }
        } catch (e: Exception) {
            println(S_GAME_INPUTNUMS(player).center())
            println()
            stopL()
            throwCard(player)
        }
    }
    /* Eine Auswertung über die oberste Karte vom playStack
    d.h. Falls Ziehen, BLOCK oder SPECIAL 4 Liegt wird der jeweilige Block ausgeführt.
     */
    private fun evaluation(player: Customer) {
        val topCard = playStack.first()
        when (topCard.value) {
            Value.DRAWTWO -> {
                sound("DRAW2").start()
                println(S_GAME_DRAW2.center())
                stopS()
                repeat(2){
                    player.hand.add(draw())
                }
                println()
                stopL()
            }
            Value.DRAWFOUR -> {
                sound("DRAW4").start()
                println(S_GAME_DRAW4.center())
                repeat(4){
                    player.hand.add(draw())
                }
                println()
                stopL()
            }

            else -> return
        }
    }
    //Filtert alle karten, die man legen könnte.
    private fun filterValid(player: Customer) {
        val validCards = player.hand.filter { it.value == playStack.first().value || it.color == playStack.first().color }
        //Dieser Zweig checkt, ob man eine Karte legen kann, in dem man schaut ob "validCards" valide Karten beinhaltet.
        if (validCards.isEmpty()) {
            println(S_GAME_DRAWONE(player).center())
            val newCard = draw()
            println(S_GAME_DRAWEDCARDINFO(newCard).center())
            player.hand.add(newCard)
            if (newCard.color == playStack.first().color|| newCard.value == playStack.first().value) {
                showHand(player)
                throwCard(player)
            }else {
                println()
                stopL()
                return
            }
        }else {
            throwCard(player)
            return
        }

    }
    //Gegner(Computer) spielt automatisch.
    private fun enemyPlays(enemy: Customer) {
        if (skipNextPlayer) {
            sound("SKIP").start()
            println(S_GAME_BLOCK(enemy).center())
            skipNextPlayer = false
            println()
            stopL()
            return
        }else{
            evaluation(enemy)
            println(S_GAME_YOURTURN(enemy).center())
            actualCard()
            for (i in enemy.hand.indices) {
                if (enemy.hand[i].value == playStack.first().value || enemy.hand[i].color == playStack.first().color) {
                    val card = enemy.hand.removeAt(i)
                    if (card.value == Value.BLOCK) {
                        skipNextPlayer = true
                    }else{
                        skipNextPlayer = false
                    }
                    playStack.addFirst(card)
                    println(S_GAME_THROWINFO(enemy,playStack).center())
                    println()
                    stopL()
                    sound(listOfSoundsEnemy.random()).start()
                    return
                }
            }
            println(S_GAME_DRAWONE(enemy).center())
            val newCard = draw()
            enemy.hand.add(newCard)
            skipNextPlayer = false
            println()
            stopL()
        }
    }
    // Funktion zur Eingabe eigener Spielzüge, wenn Blocken nicht liegt.
    private fun iPlay(me: Customer) {

        if (skipNextPlayer) {
            sound("SKIP").start()
            skipNextPlayer = false
            println(S_GAME_BLOCK(me).center())
            println()
            stopL()
            return
        } else {
            evaluation(me)
            println(S_GAME_YOURTURN(me).center())
            stopS()
            showHand(me)
            actualCard()
            filterValid(me)
            sound(listOfSounds.random()).start()
        }
    }
    /*Diese Funktion stellt das ganze Spiel zusammen und sorgt dafür, dass:
    - jeder Spieler 7 Karten zieht
    - jeder Spieler abwechselnd am Zug ist.
     */
    fun round(me: Customer, enemy: Customer) {
        S_GAME_VS(me,enemy)
        stopL()
        drawTillFull(me)
        drawTillFull(enemy)
        while (deck.isNotEmpty() || me.hand.isNotEmpty() || enemy.hand.isNotEmpty()) {
            space()
            iPlay(me)
            if (me.hand.isEmpty()) {
                println(S_GAME_THEWINNER(me).center())
                sound("UNO").start()
                stopL()
                me.unoWins++
                stopL()
                break
            }
            space()
            enemyPlays(enemy)
            if (enemy.hand.isEmpty()) {
                println(S_GAME_THEWINNER(enemy).center())
                sound("UNO").start()
                enemy.unoWins++
                stopL()
                break
            }
        }
    }

}


enum class Color(var displayName: String) {
    RED("${util.farben.RED}${BOLD}ROT$RESET"),
    GREEN("${util.farben.GREEN}${BOLD}GRÜN$RESET"),
    YELLOW("${util.farben.YELLOW}${BOLD}GELB$RESET"),
    BLUE("${util.farben.BLUE}${BOLD}BLAU$RESET"),
}
enum class Value(var displayName: String) {
    ONE("EINS".bold()),
    TWO("ZWEI".bold()),
    THREE("DREI".bold()),
    FOUR("VIER".bold()),
    FIVE("FÜNF".bold()),
    SIX("SECHS".bold()),
    SEVEN("SIEBEN".bold()),
    EIGHT("ACHT".bold()),
    NINE("NEUN".bold()),
    TEN("ZEHN".bold()),
    BLOCK("AUSSETZEN!".bold()),
    DRAWFOUR("+4 ZIEHEN".bold()),
    DRAWTWO("+2 ZIEHEN".bold()),
}
