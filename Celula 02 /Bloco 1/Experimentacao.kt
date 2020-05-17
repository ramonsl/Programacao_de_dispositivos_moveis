fun main(){
    println(par(5))
}

fun par(valor: Int):String{
    val resp = if(valor %2==0){" Is par"}
                else{"is impar"}
    return resp
}