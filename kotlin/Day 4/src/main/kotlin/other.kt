import kotlin.math.pow

class Card(parse: String){
    var id = 0;
    var winning_numbers: ArrayList<Int> = ArrayList<Int>();
    var chosen_numbers: ArrayList<Int> = ArrayList<Int>();

    init {
        var builder: StringBuilder = java.lang.StringBuilder()
        val colon_pos = parse.indexOf(":")
        val separator = parse.indexOf("|")
        for(char in parse.substring(0, colon_pos).reversed().toCharArray()){
            if(char == ' '){
                break
            }else{
                builder.insert(0, char)
            }
        }

        this.id = builder.toString().toInt()

        val winning_numbers_string = parse.substring(colon_pos+1, separator-1)
        val chosen_numbers_string = parse.substring(separator +1)

        for(numbers in winning_numbers_string.split(" ")){
            if(numbers != " " && numbers != ""){
                winning_numbers.add(numbers.toInt())
            }
        }

        for(numbers in chosen_numbers_string.split(" ")){
            if(numbers != " "&& numbers != "") {
                chosen_numbers.add(numbers.toInt())
            }
        }


    }

    fun winningNumberAmount(): Int{
        var winning_amount = 0
        for(num in chosen_numbers){
            for(winning in winning_numbers){
                if(num == winning){
                    winning_amount++
                }
            }
        }
        return winning_amount
    }

    fun points(): Int{
        var winning_amount = winningNumberAmount()
        if (winning_amount == 0){
            return 0
        }else{
            return 2.toDouble().pow(winning_amount-1).toInt()
        }

    }
}