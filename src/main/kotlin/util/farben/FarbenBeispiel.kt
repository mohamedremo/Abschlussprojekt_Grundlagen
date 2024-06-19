package util.farben

fun main(){

    val flugzeugArt: String = """
            __!__
        _____(_)_____
           !  !  !    
    """.trimIndent()

    println("$BOXED\u001B[31m$flugzeugArt$reset")

    val beispiel = """
        Das hier ist ${rot}rot$reset mit $rotBack${gelb}rotem Hintergrund$reset. 
        Jetzt ist wieder alles normal.
        
        $gruen WOW ein grüner Text!$reset
        
        Hier eine unterstrichtene Zahl $UNDERLINE 500,99€ $reset
        
        $gelb$blauBack Gelb-blaue Schrift?! wie cool!
    """.trimIndent()
    println(beispiel)
    println() // leere Zeile
    println("Ooops! \u001B[0m ich hatte wohl das Reset vergessen :O !")


    println("\u001B[33mHallo Welt!$reset")


    println("""
    @@          @@
    @@@        @@@
    @@@@      @@@@   @@@@  @@ @@@  @@ @@@ @@      @@
    @@ @@    @@ @@  @@  @@ @@@  @@ @@@  @@ @@    @@
    @@  @@  @@  @@ @@@@@@  @@      @@       @@  @@
    @@   @@@@   @@  @@     @@      @@        @@@@
    @@    @@    @@   @@@@  @@      @@         @@
                                             @@
                                        @@  @@
                                         @@@@
   @@@   @@              @@          @@
 @@   @@ @@                          @@
@@       @@ @@@  @@ @@@  @@   @@@@ @@@@@@  @@ @@  @@      @@@    @@@@
@@       @@@  @@ @@@  @@ @@  @@      @@    @@@  @@  @@  @@  @@  @@
@@       @@   @@ @@      @@   @@@    @@    @@   @@  @@ @@   @@   @@@
 @@   @@ @@   @@ @@      @@     @@   @@ @@ @@       @@ @@   @@     @@
   @@@   @@   @@ @@       @@ @@@@     @@   @@       @@  @@@@ @@ @@@@
    """.trimIndent())
}