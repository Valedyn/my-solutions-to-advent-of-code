import java.io.File

fun read(path: String): List<String> = File(path).bufferedReader().readLines()


    fun main(){
        var points = 0
        var cards = HashMap<Int, Int>()
        var lines = read("input.txt")


        for(line in lines){
            val card: Card = Card(line)

            cards.set(card.id, 1)

            val point = card.points()
            points += point
        }
        for(line in lines){
            val card: Card = Card(line)
            val id = card.id
            val winning_numbers = card.winningNumberAmount()
            val copy_amount = cards.get(id)!!

            for(winning in 1..winning_numbers){
                if(cards.containsKey(id+ winning)){
                    cards.set(id + winning, cards.get(id + winning)!! + copy_amount)
                }
            }
        }
        println("points: " + points)
        var scratchcard_amount = 0
        for(card in cards.keys){
            scratchcard_amount += cards.get(card)!!
        }
        println("amount of scratchcards: " + scratchcard_amount)
    }
