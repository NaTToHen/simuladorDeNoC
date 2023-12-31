fun main(args: Array<String>) {

    println("escolha a topologia de rede:")
    println("[1] Ring")
    println("[2] Mesh-2D")
    println("[3] MPSoC")
    print("Resposta: ")
    var escolha = readln().toInt()

    when (escolha) {
        1 -> Ring()
        2 -> Mash2D()
        3 -> MPSoC()
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
            source += 1
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
            source -= 1
            println("Proc[${source}] recebeu a mensagem de Proc[${source+1}]")
            if(source > target) {
                println("Proc[${source}] NÃO é o destino")
            }
        }
        println("Proc[${target}] é o destino\n" + "Proc[${target}] consumiu a mensagem")
        }
}

fun Mash2D() {
    print("Informe o tamanho da NoC para X: ")
    var tamanhoX = readln().toInt()
    print("Informe o tamanho da NoC para Y: ")
    var tamanhoY = readln().toInt()

    if(tamanhoX <= 9 && tamanhoY <= 9) {
        acoesMesh2D(tamanhoX, tamanhoY)
    } else {
        println("O tamanho de X e Y precisam ser menores que 10")
    }
}

fun acoesMesh2D(tamanhoX: Int, tamanhoY: Int) {
    val matrizNodos = Array(tamanhoY) { Array(tamanhoX) { "" } }

    for (i in 0 until tamanhoY) {
        for (j in 0 until tamanhoX) {
            matrizNodos[i][j] = "${i}${j}"
        }
    }

    print("informar origemX: ")
    val origemX = readln().toInt()
    print("informar origemY: ")
    val origemY = readln().toInt()

    print("\ninformar destinoX: ")
    val destinoX = readln().toInt()
    print("informar destinoY: ")
    val destinoY = readln().toInt()

    if (origemX < tamanhoX && origemY < tamanhoY && destinoX < tamanhoX && destinoY < tamanhoY) {
        roteamentoMesh2D(origemX, origemY, destinoX, destinoY)
    } else {
        println("Origem e destino devem estar dentro dos limites")
    }
}

fun roteamentoMesh2D(origemX: Int, origemY: Int, destinoX: Int, destinoY: Int) {
    println("Iniciando roteamento da origem (${origemX}, ${origemY}) para o destino (${destinoX}, ${destinoY}):")

    var posicaoX = origemX
    var posicaoY = origemY

    while (posicaoX != destinoX || posicaoY != destinoY) {
        if (posicaoX < destinoX) {
            posicaoX++
        } else if (posicaoX > destinoX) {
            posicaoX--
        }

        if (posicaoY < destinoY) {
            posicaoY++
        } else if (posicaoY > destinoY) {
            posicaoY--
        }

        println("Encaminhando para o nó (${posicaoX}, ${posicaoY})")
        println("Chegou ao destino (${posicaoX}, ${posicaoY})")
    }
    println("Chegou ao destino final (${posicaoX}, ${posicaoY})")
}

fun MPSoC() {
    val noc_x = 4
    val noc_y = 4

    val matrizNodos = Array(noc_x) { Array(noc_y) { "" } }

    for (i in 0 until matrizNodos.size) {
        for (j in 0 until matrizNodos[i].size) {
            matrizNodos[i][j] = "${i}${j}"
            print(matrizNodos[i][j] + " ")
        }
        println("")
    }

    println("Escolha a aplicação: 1, 2 ou 3")
    print("Resposta: ")
    val aplicacao = readLine()?.toIntOrNull()

    if (aplicacao !in 1..3) {
        println("Escolha de aplicação inválida.")
        return
    }

    when (aplicacao) {
        1 -> app1(matrizNodos)
        2 -> app2(matrizNodos)
        3 -> app3(matrizNodos)
    }
}

fun roteamentoNPSoC(origemX: Int, origemY: Int, destinoX: Int, destinoY: Int, matrizNodos: Array<Array<String>>) {
    println("[P] [INICIANDO COMUNICAÇÃO] [${matrizNodos[origemX][origemY]}] [${matrizNodos[destinoX][destinoY]}]")

    var posicaoX = origemX
    var posicaoY = origemY

    while (posicaoX != destinoX || posicaoY != destinoY) {
        println("[R] [ARBITRAGEM] [${matrizNodos[posicaoX][posicaoY]}] [${matrizNodos[destinoX][destinoY]}]")
        if(posicaoX != destinoX) {
            if (posicaoX < destinoX) {
                posicaoX++
            } else if (posicaoX > destinoX) {
                posicaoX--
            }
        } else {
            if (posicaoY < destinoY) {
                posicaoY++
            } else if (posicaoY > destinoY) {
                posicaoY--
            }
        }
        println("[R] [ENVIANDO] [${matrizNodos[posicaoX][posicaoY]}] [${matrizNodos[destinoX][destinoY]}]")
    }
    println("[R] [ARBITRAGEM] [${matrizNodos[posicaoX][posicaoY]}] [${matrizNodos[destinoX][destinoY]}]")
    println("[P] [ENCERRANDO COMUNICAÇÃO] [${matrizNodos[posicaoX][posicaoY]}] [${matrizNodos[destinoX][destinoY]}]")
}

fun app1(matrizNodos: Array<Array<String>>) {
    var mapaNodos = mapOf(
        "A" to intArrayOf(0,2),
        "C" to intArrayOf(1,1),
        "B" to intArrayOf(2,1),
        "D" to intArrayOf(3,0)
    )

    var inicioPos = mapaNodos["A"]
    var destinoPos = mapaNodos["C"]

    roteamentoNPSoC(inicioPos!![0], inicioPos[1], destinoPos!![0], destinoPos[1], matrizNodos)
}

fun app2(matrizNodos: Array<Array<String>>) {

    var mapaNodos = mapOf(
        "T" to intArrayOf(0,3),
        "X" to intArrayOf(1,1),
        "W" to intArrayOf(3,2)
    )

    var inicioPos = mapaNodos["T"]
    var destinoPos = mapaNodos["X"]

    roteamentoNPSoC(inicioPos!![0], inicioPos[1], destinoPos!![0], destinoPos[1], matrizNodos)
}

fun app3(matrizNodos: Array<Array<String>>) {

    var mapaNodos = mapOf(
        "G" to intArrayOf(0,3),
        "N" to intArrayOf(0,2),
        "O" to intArrayOf(1,2),
        "P" to intArrayOf(2,2),
        "U" to intArrayOf(1,1),
        "H" to intArrayOf(2,1),
        "Z" to intArrayOf(3,1),
        "V" to intArrayOf(3,0)
    )

    var inicioPos = mapaNodos["G"]
    var destinoPos = mapaNodos["O"]

    roteamentoNPSoC(inicioPos!![0], inicioPos[1], destinoPos!![0], destinoPos[1], matrizNodos)
}