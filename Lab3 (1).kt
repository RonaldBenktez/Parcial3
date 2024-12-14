
// Lab 3 - Implementación en Kotlin
import kotlin.system.measureTimeMillis

fun main() {
    while (true) {
        println("Seleccione una opcion:")
        println("1. Ordenar una lista usando Bubble Sort")
        println("2. Ordenar una lista usando Quick Sort")
        println("3. Calcular el factorial de un numero")
        println("4. Resolver las Torres de Hanoi")
        println("5. Salir")

        when (readLine()?.toIntOrNull()) {
            1 -> bubbleSortMenu()
            2 -> quickSortMenu()
            3 -> factorialMenu()
            4 -> hanoiMenu()
            5 -> {
                println("Saliendo del programa...")
                break
            }
            else -> println("Opcion invalida. Intente de nuevo.")
        }
    }
}

fun bubbleSortMenu() {
    println("Ingrese una lista de numeros separados por comas:")
    val input = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: run {
        println("Entrada invalida. Intente de nuevo.")
        return
    }
    val time = measureTimeMillis {
        val sortedList = bubbleSort(input)
        println("Lista ordenada usando Bubble Sort: $sortedList")
    }
    println("Tiempo de ejecucion: ${time / 1000.0} segundos")
}

fun bubbleSort(list: List<Int>): List<Int> {
    val mutableList = list.toMutableList()
    for (i in mutableList.indices) {
        for (j in 0 until mutableList.size - i - 1) {
            if (mutableList[j] > mutableList[j + 1]) {
                val temp = mutableList[j]
                mutableList[j] = mutableList[j + 1]
                mutableList[j + 1] = temp
            }
        }
    }
    return mutableList
}

fun quickSortMenu() {
    println("Ingrese una lista de numeros separados por comas:")
    val input = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: run {
        println("Entrada invalida. Intente de nuevo.")
        return
    }
    val time = measureTimeMillis {
        val sortedList = quickSort(input)
        println("Lista ordenada usando Quick Sort: $sortedList")
    }
    println("Tiempo de ejecucion: ${time / 1000.0} segundos")
}

fun quickSort(list: List<Int>): List<Int> {
    if (list.size <= 1) return list
    val pivot = list[list.size / 2]
    val less = list.filter { it < pivot }
    val equal = list.filter { it == pivot }
    val greater = list.filter { it > pivot }
    return quickSort(less) + equal + quickSort(greater)
}

fun factorialMenu() {
    println("Ingrese un numero entero positivo:")
    val number = readLine()?.toIntOrNull() ?: run {
        println("Entrada invalida. Intente de nuevo.")
        return
    }
    if (number < 0) {
        println("El numero debe ser positivo.")
        return
    }
    println("El factorial de $number es: ${factorial(number)}")
}

fun factorial(n: Int): Long {
    return if (n == 0) 1 else n * factorial(n - 1)
}

fun hanoiMenu() {
    println("Ingrese el numero de discos:")
    val disks = readLine()?.toIntOrNull() ?: run {
        println("Entrada invalida. Intente de nuevo.")
        return
    }
    if (disks < 1) {
        println("El numero de discos debe ser al menos 1.")
        return
    }
    println("Resolviendo Torres de Hanoi con $disks discos:")
    hanoi(disks, 'A', 'C', 'B')
}

fun hanoi(n: Int, source: Char, target: Char, auxiliary: Char) {
    if (n == 1) {
        println("Mover disco 1 de Torre $source a Torre $target")
    } else {
        hanoi(n - 1, source, auxiliary, target)
        println("Mover disco $n de Torre $source a Torre $target")
        hanoi(n - 1, auxiliary, target, source)
    }
}
