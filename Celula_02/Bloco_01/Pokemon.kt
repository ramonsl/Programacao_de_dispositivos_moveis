
class Pokemon(val nome:String, val peso: Int, val familia:String){
    //bloco de inicialização

    init{
        require(nome.trim().length > 0){
            "informe um nome"
        }
        require (familia.trim().length > 0){
            "informe uma familia"
        }
    }

    //construtor secundario
    constructor (nome: String, familia: String): this(nome,10,familia)
    //funçao toString
    override fun toString(): String{
        return "Pokemon(nome= $nome, peso=$peso, familia= $familia)"
    } 
    //funcao ataque
    fun ataque(): String{
        val atk = if (this.familia.equals("Eletrico")){
            "raio do trovão"
        }else{
            "Outro ataque"
        }
        return atk
    }
}//fim da classe

fun main(){
    //instancia com constructor primario
    var pokemon = Pokemon("Picachu",1,"Elétrico")
    //instancia com constructo secundario
    val pokemon2 = Pokemon("Bulbasau", "Planta/Venenoso")
    println(pokemon.toString())
    println(pokemon2.toString())
    println(pokemon.ataque())
}