package util
import util.farben.*
import pub.*
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
//------------------------------------------------GENERAL FUNCTIONS-----------------------------------------------------

//BREAKS Thread.sleep als funktionen mit verschiedenen millis:
fun stopS(){
    Thread.sleep(200)
}
fun stopM(){
    Thread.sleep(500)
}
fun stopL(){
    Thread.sleep(1500)
}


//String Funktionen
fun String.center(breite: Int = 80): String {
    val length = this.replace(Regex("\u001B\\[[;\\d]*m"), "").length // entfernt Farbcodes für die Längenberechnung
    if (length >= breite) return this
    val paddingLeft = (breite - length) / 2
    val paddingRight = breite - length - paddingLeft
    return " ".repeat(paddingLeft) + this + " ".repeat(paddingRight)
}
fun String.bold(): String {
    return "${BOLD}$this${ RESET}"
}
fun space(){
    repeat(50){
        println()
    }
}
fun drinkAnimate(drink: Drink){
    println("${BRIGHTBLUE}10 Schlücke und der ${drink.name} ist Geschichte!")
    repeat(10){
        Thread.sleep(300)
        print("$BRIGHTBLUE*Gluck*$RESET...")
    }
    println("${RED}BUUUUUURP$RESET")
    stopM()
}
fun eatAnimate(food: Food){
    println("${BRIGHTPURPLE}Hier deine Delikatesse: ${food.name}!$RESET")
    repeat(10){
        Thread.sleep(300)
        print("$BRIGHTPURPLE*Mampf*$RESET...")
    }
    println("${RED}BUUUUUURP$RESET")
    stopM()
}
fun emojiAnimate(emoji:String){
    repeat(10){
        print("$emoji ")
        stopS()
    }
    println()
}

//Sound Funktionen
fun backMusic(): Thread{
    return Thread{
        val clip = AudioSystem.getClip()
        val stream = AudioSystem.getAudioInputStream(File("src/main/kotlin/util/files/DRAW2.wav"))
        clip.open(stream)
        clip.loop(Clip.LOOP_CONTINUOUSLY)
    }
}
fun sound(file: String): Thread{
    return Thread{
        val clip = AudioSystem.getClip()
        val stream = AudioSystem.getAudioInputStream(File("src/main/kotlin/util/files/$file.wav"))
        clip.open(stream)
        clip.start()
        Thread.sleep(clip.microsecondLength / 1000)
        clip.close()
        stream.close()
    }
}
