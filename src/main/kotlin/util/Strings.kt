package util
import util.farben.*
import pub.*
import uno.*
import java.time.Duration
import java.time.LocalTime

//---------------------------------------------------ALL STRINGS--------------------------------------------------------

fun S_WELCOME(customer: Customer) = "🍻Hey ${customer.name} - Willkommen im Syntax Pub!🍻"

var S_LOGIN = """
        ${BOLD}${PURPLE}Herzlich willkommen im Syntax Pub$RESET
        Wir haben einen Social Club mit einem Treuepunkt System
        Jeder Besucher ist ${RED}${UNDERLINE}VERPFLICHTET$RESET an diesem ${RED}${UNDERLINE}Programm teilzunehmen!$RESET
        Bist du schon mal bei uns gewesen und hast deine ${GREEN}LogIn$RESET Daten ? Dann wähle Login im Anmeldefenster aus.
        Ansonsten wähle ${YELLOW}Registrieren$RESET das ist auch kein Problem!
        ${BOLD}${PURPLE}Wir freuen uns auf dich bis gleich 🥳$RESET
    """.trimIndent()

var S_LOGIN_SELECT = """
            ${GREEN}1 ----> LogIn $RESET
            ${YELLOW}2 ----> Register $RESET
        """.trimIndent()

var S_REGISTER_SUCCESS = "${GREEN}Du hast dir erfolreich ein Social Club Konto angelegt!\n".bold() +
        "Drücke ${GREEN}ENTER$RESET um zum Main Menu zu gelangen!$RESET\n".bold()

var S_NO_ACCOUNT = "${YELLOW}Du bist wohl das erste mal hier, bitte Registriere dich für den Social Club!(Drücke Enter)$RESET"

var S_LOGIN_SUCCESS = "${GREEN}${BOLD}Du hast dich erfolgreich Eingeloggt!$RESET"

var S_WRONG_PASSWORD = "$RED${BOLD}Falsches Passwort$RESET"

var S_DOESNT_EXIST = "$RED${BOLD}Dieses Konto existiert nicht...$RESET"

var S_ALREADY_ACC = "${YELLOW}Du hast schon einen Account, bitte Logge dich mit deinen Daten ein!(Drücke Enter)$RESET"

var S_NICKNAME = "$BRIGHTPURPLE---------------------LOGIN---------------------$RESET\n" +
        "Nickname:"

var S_NICKNAME_REG = "$BRIGHTPURPLE---------------------REGISTER---------------------$RESET\n" +
        "Gib deinen Nicknamen ein:"

var S_PASSWORD = "Passwort:"

var S_PASSWORD_REG = "Gib dein neues Passwort ein:"


var S_TYPE_1OR2 = "${RED}Gib nur 1 oder 2 Ein!$RESET"

fun S_WANT_ACCOUNT(customer: Customer) = "${customer.name} du willst dir also ein Social Club Account anlegen."

var S_LINE = "--------------------------------------------------------------------------------------"

var S_DRINK_CARD = "$BOXED$RED--------------GETRÄNKE KARTE--------------$RESET\n" +
        "${BOXED}$RED$BOLD DRINK                              PREIS $RESET"

var S_PRESS_ENTER = "${GREEN_BACKGROUND}DRÜCKE ENTER UM FORTZUFAHREN$RESET"

fun S_WHICH_DRINK (drinkCard:List<Drink>) = "Welchen Drink möchtest du haben ?\n" +
        "AUSWAHL(1-${drinkCard.size}):"

var S_RANDOM_DRINK = """
    
             🎲🎲${BOLD_UNDERLINE}Ein zufälligen Pub-Drink möchtest du also...${RESET}🎲🎲
             ${RED}VORSICHT❗️ 
             Im Syntax-Pub kannst du zwar mit einem Zufälligen Pub-Drink sparen💰
             jedoch wird er per Zufall 🔀 ausgewählt und es konnte sein, dass er dir nicht schmeckt${RESET}🤢
             ${RED}ES KANN AUCH SEIN DAS ES EIN ALKOHOLISCHES GETRÄNK WIRD${RESET}❗
             ${BRIGHTGREEN}Möchtest du einen zufälligen Pub-Drink ?$RESET (j/n)
         """.trimIndent()

var S_RANDOM_DRINK_NO = "Kann ich verstehen, der Zufallsdrink ist nicht für jeden Cool!\n" +
        "Wenn du was Trinken willst schau in die Getränke Karte.🍹🍺🍸\n" +
        "Hast du Hunger ? Kein Ding! schau in unser Menü.🍔🍟🍕"

var S_FOOD_CARD = "$BOXED$RED----------------ESSENSKARTE---------------$RESET\n" +
        "${BOXED}$RED$BOLD GERICHT                            PREIS $RESET"

fun S_WHICH_FOOD (foodCard:List<Food>) = "Welche Speise möchtest du haben ?\n" +
        "AUSWAHL(1-${foodCard.size}):"

var S_MENU_CARD = "$BOXED$RED----------------ESSENSKARTE---------------$RESET" + " " +"$BOXED$RED--------------GETRÄNKE KARTE--------------$RESET\n" +
        "${BOXED}$RED$BOLD GERICHT                            PREIS $RESET" +" " + "${BOXED}$RED$BOLD DRINK                              PREIS $RESET"

var S_MENU = "1️⃣ ➡️ ${GREEN}${BOLD}Ich möchte etwas bestellen!${RESET}🍻\n"+
        "2️⃣ ➡️ ${PURPLE}${BOLD}Wer möchte eine Partie Uno Spielen?${RESET}🃏\n"+
        "3️⃣ ➡️ ${BRIGHTCYAN}${BOLD}Zufälliger Drink!${RESET}🍺🍸\n"+
        "4️⃣ ➡️ ${BLUE}${BOLD}Wasser trinken!(Promille runterschrauben)$RESET\n"+
        "5️⃣ ➡️ ${YELLOW}${BOLD}Die Blase entleeren!${RESET}😵‍💫\n"+
        "6️⃣ ➡️ $BRIGHTBLUE${BOLD}Promille Checken!$RESET 🌡️\n" +
        "7️⃣ ➡️ ${RED}Kneipe ${BOLD}Verlassen!(LogOut)$RESET\n"+
        "${BLACK_BACKGROUND}AUSWAHL:$RESET"

var S_ORDERMENU = "1️⃣ ➡️ ${GREEN}${BOLD}Einen Drink bitte!(GETRÄNKEKARTE)${RESET}🍻\n"+
        "2️⃣ ➡️ ${GREEN}${BOLD}Mein Magen knurrt!(SPEISEKARTE)${RESET}🤤\n"+
        "3️⃣ ➡️ ${PURPLE}${BOLD}Beide ansehen${RESET}🍻🍻🍻\n"+
        "${BLACK_BACKGROUND}AUSWAHL:$RESET"

var S_DRINKORDERMENU = "1️⃣ ➡️ ${GREEN}${BOLD}ALKOHOLISCH${RESET}🍻\n"+
        "2️⃣ ➡️ ${GREEN}${BOLD}NICHT ALKOHOLISCH${RESET}🍻🚫\n"+
        "3️⃣ ➡️ ${PURPLE}${BOLD}GANZE KARTE ANSEHEN${RESET}🍻🍻🍻\n"+
        "${BLACK_BACKGROUND}AUSWAHL:$RESET"

var S_EATORDERMENU = "1️⃣ ➡️ ${GREEN}${BOLD}VEGAN${RESET}\n"+
        "2️⃣ ➡️ ${GREEN}${BOLD}NICHT VEGAN${RESET}🍻🚫\n"+
        "3️⃣ ➡️ ${PURPLE}${BOLD}GANZE KARTE ANSEHEN${RESET}🍻🍻🍻\n"+
        "${BLACK_BACKGROUND}AUSWAHL:$RESET"

var S_DRUNK_MENU =  "1️⃣ ➡️ ${GREEN}${BOLD}Ich möchte etwas bestellen!${RESET}🍻\n"+
        "2️⃣ ➡️ ${PURPLE}${BOLD}Wer möchte eine Partie Uno Spielen?${RESET}🃏\n"+
        "3️⃣ ➡️ ${BRIGHTCYAN}${BOLD}Zufälliger Drink!${RESET}🍺🍸\n"+
        "4️⃣ ➡️ ${BLUE}${BOLD}Wasser trinken!(Promille runterschrauben)$RESET\n"+
        "5️⃣ ➡️ ${YELLOW}${BOLD}Die Blase entleeren!${RESET}😵‍💫\n"+
        "6️⃣ ➡️ $BRIGHTBLUE${BOLD}Promille Checken!$RESET 🌡️\n"

var S_DRUNK_MENU2 = "7️⃣ ➡️ ${RED}Kneipe ${BOLD}Verlassen!(LogOut)$RESET\n"+
        "${BLACK_BACKGROUND}AUSWAHL:$RESET"

var S_SCREEN_SAVER = ("""
            $BRIGHTPURPLE              ______   ___   _ _____  _    __  __       ____  _   _ ____  
                         / ___\ \ / / \ | |_   _|/ \   \ \/ /      |  _ \| | | | __ ) 
            -------------\___ \\ V /|  \| | | | / _ \   \  /       | |_) | | | |  _ \------------- 
                          ___) || | | |\  | | |/ ___ \  /  \       |  __/| |_| | |_) |
                         |____/ |_| |_| \_| |_/_/   \_\/_/\_\      |_|    \___/|____/ $RESET
            1️⃣ ➡️ ${"${GREEN}LogIn$RESET".bold()} 🚪
            2️⃣ ➡️ ${"${YELLOW}Registrieren$RESET".bold()} 🧑🏽‍💻
            3️⃣ ➡️ ${"${RED}Pub Verlassen$RESET".bold()} ⛔️
            ${BLACK_BACKGROUND}AUSWAHL:$RESET
        """.trimIndent())

var S_JUST_NUMBERS = "$GREEN${BOLD}Bitte gib nur ${BOLD_UNDERLINE}Zahlen$RESET $GREEN ein!$RESET"

var S_INVALID_INPUT = "${RED}Ungültige Eingabe. Möchtest du es nochmal Versuchen ? (j/n)$RESET"

var S_INVALID_INPUT2 = "${RED}Ungültige Eingabe$RESET"

var S_SEE_YOU = "Danke für deinen Besuch!\n" +
        "Bis zum nächsten mal! <3"

var S_SELECTION = "AUSWAHL:"

var S_ALREADY_EXIST = "${RED}Es existiert bereits ein Nutzer mit diesem Nicknamen...$RESET".bold()

var S_INVALID_NICKNAME = "${RED}Dieser Nickname existiert nicht!$RESET"

var S_WHICH_NICKNAME = "${BRIGHTPURPLE}Mit welchem Nickname hast du dich bei uns Registriert ?$RESET :".bold()

var S_FIRST_TIME = "Warst du schon mal in unserem Pub?"

var S_NEWACC = "${BRIGHTPURPLE}Du meldest dich jetzt neu beim Syntax Pub an!$RESET"

var S_YOUR_NAME = "Wie ist dein Name ?".bold()

var S_YOUR_AGE = "Wie alt bist du ?".bold()

var S_INVALID_OPTION = "Diese Option gibt es nicht.. \n" +
        "Versuchs nochmal..."

fun S_STATS(customer: Customer) = "Name: ${customer.name} - Alter: ${customer.age} - Promille: ${customer.alcoholLevel/60}"

var S_NO_ONE_THERE = "Zurzeit ist leider keiner im Pub, warte noch einen Moment es kommt bestimmt gleich jemand"

var S_NOT_18 = "🔞Du bist leider nicht alt genug! Komm wieder wenn du 18 bist!🔞"

var S_FULL = "Wir sind leider zu voll komm in einer Stunde wieder!⏳"

var S_DISGRACED = "${RED}Du hast dir voll in die Hose gemacht und wirst aus dem Pub geschmissen..$RESET\n" +
        "${YELLOW}Du zahlst 10 € Reinigungsgebühren und hast jeden neben dir den Abend versaut...😂$RESET\n" +
        "CIAOOOO!"

fun S_WHATS_NEXT(customer:Customer) = "$BOLD$BRIGHTPURPLE---------------------- M E N Ü ----------------------$RESET\n" +
        "${BOLD}${GREEN}Was möchtest du als nächstes tun $UNDERLINE${customer.name}$RESET ?\n" +
        "Geld: $UNDERLINE${String.format("%.2f",customer.money)}€$RESET - Treuepunkte: $UNDERLINE${customer.truePoints}$RESET - Uno Wins: $UNDERLINE${customer.unoWins}$RESET"

fun S_GET_DRINK(product: Drink) = "${BRIGHTGREEN}So soll es sein, dein zufälliger Drink ist: $PURPLE${product.name}$BRIGHTGREEN Alkoholisch?: $RED$UNDERLINE${product.category == DrinkCategory.ALCOHOLIC}$RESET\n".bold()

fun S_TIME_IS(time: LocalTime) = "Es ist gerade ${time}Uhr."

fun S_TIME_CLOSING(time: LocalTime) = "Wir machen heute um ${time}Uhr zu."

fun S_TIME_LEFT(time: Duration) = "Es verbleiben ${time.toHours()} Stunden und ${time.toMinutes() % 60} min."

//GAME (UNO) STRINGS
var S_GAME_ROUND = "$BOXED${BOLD}Eine Runde UNO im Syntax Pub$RESET"

var S_GAME_MIX = "${BOLD}*Das Deck wird gemischt!*$RESET"

var S_GAME_DISTRI = "${BOLD}*Karten werden verteilt*$RESET"

fun S_GAME_FIRSTCARD(playStack: MutableList<Card>) = "${BOLD}${GREEN}${BLACK_BACKGROUND}Die erste Karte, die aufgedeckt wurde," +
        " ist ➡️${playStack.first().value.displayName}$BLACK_BACKGROUND in der Farbe ${playStack.first().color.displayName}$RESET"

fun S_GAME_CARDNOW(playStack: MutableList<Card>)= "$GREEN_BACKGROUND$BLACK ES LIEGT ➡️${playStack.first().value.displayName}$GREEN_BACKGROUND$BLACK " +
        "$RESET ${playStack.first().color.displayName}"

fun S_GAME_DRAWONE(player: Customer) = "${player.name} konnte ${RED_BACKGROUND}keine Karte$RESET legen und muss eine ziehen!"

fun S_GAME_DRAWEDCARDINFO(card: Card)= "Du hast die Karte: " + card.color.displayName +" "+ card.value.displayName + " gezogen!"

var S_GAME_MIXAGAIN = "Das Deck ist leer, der Spielstapel wird wieder ins Deck gemischt\n" +
        "Die erste Karte bleibt natürlich liegen :)".center()

var S_GAME_DRAW4 = "Oh, es liegt eine ${BOLD}${PURPLE}+4$RESET. Du musst 4 Karten ziehen 👻"

var S_GAME_DRAW2 = "OH, es liegt eine ${BOLD}${YELLOW}+2$RESET. Du musst 2 Karten ziehen."

var S_GAME_INVALIDCARD = "${BOLD}${RED}Ungültige Karte gewählt.$RESET"


var S_GAME_ACTUALRDS = "Du hast folgende Karten auf der Hand:"

fun S_GAME_WHICHCARD(player: Customer) = "${GREEN_BACKGROUND}${BLACK}Welche Karte möchtest du spielen? (Eingabe 1-${player.hand.size})➡️$RESET"

fun S_GAME_BLOCK(player: Customer) = "${BOLD}${RED}🚫🚫🚫${player.name} muss aussetzen.🚫🚫🚫$RESET"

fun S_GAME_YOURTURN(player: Customer) = "${BOLD}${GREEN_BACKGROUND}$BLACK${player.name.uppercase()}${GREEN_BACKGROUND}$BLACK IST DRAN!$RESET"

fun S_GAME_INPUTNUMS(player: Customer)= "${BOLD}${RED}Bitte gib nur Zahlen von 1 bis ${player.hand.size} ein!$RESET"

fun S_GAME_THROWINFO (player: Customer, playStack: MutableList<Card>) = "$BRIGHTGREEN${player.name} hat die Karte $UNDERLINE" +
        "${playStack.first().value.displayName}$BRIGHTGREEN$UNDERLINE ${playStack.first().color.displayName}" +
        "${RESET}$BRIGHTGREEN gelegt.$RESET"

fun S_GAME_THEWINNER(player: Customer) = "${BOLD}${GREEN}🥳🥳UNO! ${player.name} GEWINNT!🥳🥳🥳$RESET"

fun S_GAME_VS (me: Customer, enemy: Customer) = println("${RED}Heute spielt ${enemy.name} gegen ${me.name}!!".center())

var S_CUS_DRINK = "Du hast leider nicht genug Geld für den Drink..."

fun S_CUS_DRINK2(customer: Customer,drink: Drink) = "${BRIGHTGREEN}Das macht dann einmal $RED_BACKGROUND${drink.price}$RESET$BRIGHTGREEN für: $BRIGHTPURPLE${drink.name}$RESET\n" +
        "${BRIGHTGREEN}Du hast jetzt noch $UNDERLINE${String.format("%.2f",customer.money)} €$RESET"

var S_CUS_SLOW_DOWN = "Du solltest es etwas Ruhiger angehen..."

var S_CUS_ALL_GOOD = "${GREEN}${UNDERLINE}Alles im grünen Bereich!$RESET"

var S_CUS_BLADDER = """Du warst Pinkeln und die Blase ist jetzt Leer!
                |Du hast deine Promille 0,2 runtergeschraubt!
            """.trimMargin()

var S_CUS_BLADDER2 = "Deine Blase ist schon Leer!"

var S_CUS_EAT = "Du hast leider nicht genug Geld für die Speise..."

fun S_CUS_EAT2(customer: Customer,food: Food) = "Das macht dann einmal $RED_BACKGROUND${food.price}$RESET für: ${food.name}\n"+
        "Du hast jetzt noch ${String.format("%.2f",customer.money)} €\n"

var S_CUS_PROMILLS = "${BRIGHTBLUE}Du hast Wasser getrunken, Wasser bewirkt wunder und dein Promillegehalt ist um 0,1 gesunken$RESET"

fun S_CUS_PROMILLS2(customer: Customer) = "$BRIGHTBLUE${customer.name} deine Promille betragen: ${customer.alcoholLevel.toDouble()/100}$RESET"

fun S_CUS_PROMILLS3 (customer: Customer) = "$GREEN${customer.name} deine Promille betragen: ${customer.alcoholLevel.toDouble() / 100}\n" +
        "Aber hier das Glas Wasser, kann nie schaden.$RESET"