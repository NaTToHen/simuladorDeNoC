import kotlin.math.sign

fun main(args: Array<String>) {

    println("escolha a topologia de rede:")
    println("[1] Ring")
    println("[2] Mesh-2D")
    print("Resposta: ")
    var escolha = readln().toInt()

    when (escolha) {
        1 -> {
            Ring()
        }
        2 -> {
            Mash2D()
        }
    }
}

fun Ring() {
    val quantNodos = readln().toInt()

    val condicao: Int = 1
    if (quantNodos <= 10) {
        while (condicao == 1) {
            print("Qual é o source: ")
            var source = readln().toInt()
            print("Qual é o target: ")
            var target = readln().toInt()

            if(source <= 0 || target <= 0) {
                println("numeros de 1 á ${quantNodos}")
            } else {
                arbitragem(source, target, quantNodos)
            }
        }
    } else {
        println("O numero maximo de nodos é 10")
    }
}

fun Mash2D() {

    print("Informe o tamanho da NoC para X: ")
    var tamanhoX = readln().toInt()
    print("Informe o tamanho da NoC para Y: ")
    var tamanhoY = readln().toInt()
    println("\nConstrução de pacotes:")

    if(tamanhoX <= 9 && tamanhoY <= 9) {
        acoesMash2D(tamanhoX, tamanhoY)
    } else {
        println("O tamanho de X e Y precisam ser menores que 10")
    }
}

fun arbitragem(source: Int, target: Int, quantNodos: Int) {
    if(source > quantNodos || target > quantNodos) {
        println("esse nodo não existe")
    } else {
        roteamento(source, target, quantNodos)
    }
}
fun roteamento(source: Int, target: Int, quantNodos: Int) {
    var source = source
    var target = target
    if(source == 1 && target == quantNodos || source == quantNodos && target == 1 || source == target) {
        println("Proc[${source}] criou a mensagem")
        println("Proc[${source}] enviou a mensagem para o Proc[${target}]")
        println("Proc[${target}] recebeu a mensagem do Proc[${source}]")
        println("Proc[${target}] é o destino e consumiu a mensagem")

    } else if(source < target) {
        println("Proc[${source}] criou a mensagem")
        while (source < target) {
            println("Proc[${source}] enviou a mensagem para o Proc[${source+1}]")
            source+=1
            println("Proc[${source}] recebeu a mensagem de Proc[${source-1}]")
            if(source < target) {
                println("Proc[${source}] NÃO é o destino")
            }
        }
        println("Proc[${target}] é o destino\n" + "Proc[${target}] consumiu a mensagem")

    } else if(source > target) {
        println("Proc[${source}] criou a mensagem")
        while (source > target) {
            println("Proc[${source}] enviou a mensagem para o Proc[${source-1}]")
            source-=1
            println("Proc[${source}] recebeu a mensagem de Proc[${source+1}]")
            if(source > target) {
                println("Proc[${source}] NÃO é o destino")
            }
        }
        println("Proc[${target}] é o destino\n" + "Proc[${target}] consumiu a mensagem")
        }
}

fun acoesMash2D(tamanhoX: Int, tamanhoY: Int) {
    print("informar origemX: ")
    val origemX = readln().toInt()
    print("informar origemY: ")
    val origemY = readln().toInt()

    print("\ninformar destinoX: ")
    val destinoX = readln().toInt()
    print("informar destinoY: ")
    val destinoY = readln().toInt()

    var matrizNodos = arrayOf<Array<Int>>()

    var origemMatriz = matrizNodos[origemX][origemY]
    var destinoMatriz = matrizNodos[destinoX][destinoY]

    for (i in 0..tamanhoX) {
        var linha = arrayOf<Int>()
        for (j in 0..tamanhoY) {
            linha+=0
            if(matrizNodos[i][j] == matrizNodos[origemX][origemY]) {

            }
        }
        matrizNodos += linha
    }
}