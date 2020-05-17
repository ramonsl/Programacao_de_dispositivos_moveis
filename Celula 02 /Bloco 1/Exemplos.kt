package bmi.calculator
fun main() {

  /// Codigo exemplo Observação
  var a: String? = "Kotlin"
  a = null
  println(a)
  var b: String? = "Android"
  a = null
  println(a)


  /////declaração de variavel
  var cont: Int = 10
  cont = 15
  val nome: String = "Kotlin"
  nome = "Java" /// Erro "Val cannot be reassigned"


  ///inferencia de tipo
  val nome2 = "Kotlin"
  val nomeMaiuscula = nome2.toUpperCase()
  nomeMaiuscula.inc() // Erro ao compilar

  //segurança nula
  val nome3: String = "Ramon"
  val segundoNome: String = null
  val sobreNome: String? = null
  val lenNome3 = nome3.length
  val lenSobre = sobreNome.length
  val lenNome3S = sobreNome?.length ?: -1
/*
////Condicionais/*
  var count =24
  if (count ==1){
    println("Contador igual a 1")
  }else if(count >33){
    println("Contador maior que 33")
  }else{
    println("Contador menor que 33")
  }
*/

  ////Condicionais
  var count =24
  val  resp = if (count ==1){ "Contador igual a 1"
  }else if(count >33){ "Contador maior que 33"
  }else{ "Contador menor que 33"
  }
*/
  var count = 24
  val resp = when {
    count == 1 -> "contatdor igual a 1"
    count > 33 -> "contatdor maior a 33"
    else -> "contador menor que 33"
  }
  println(resp)

  //estrutura de repetição
  var x = 100
  //estrutura while
  while (x > 0) {
    x--
  }
  ///estrutura do ...while
  do {
    val y = funcao()

  } while (y != null)// y é acessivel aqui

  //um laço de 1 a 3
  for (i in 1..3) {
    println(i)
  }
  //um laço de 6 a 0 decrementado em 2
  for (i in 6 downTo 0 step 2) {
    println(i)
  }
  val array = arrayOf("a", "b", "c")
  //percorrendo um array, mostrando valores do array.
  for (i in array.indices) {
    println(array[i])
  }
  //percorrendo um array, mostrando os indices e valores do array
  for ((index, value) in array.withIndex()) {
    println("O elemento na posição $index é $value")
  }
}
  fun main(){
  println(idade(22))

}

fun idade(count : Int): String{
  val resp = when{
    count ==1 -> "idade igual a 1"
    count >33 -> "idade maior a 33"
    else -> "idade menor que 33"
  }
  return resp
}

fun funcao(){

}
