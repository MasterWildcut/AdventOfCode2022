import java.io.File

fun main() {
    println(part23("src/main/resources/Day12_01.txt")) // 464 too high 462 is the right answer....
    println(part24("src/main/resources/Day12_01.txt")) // same problem here two too much
}

fun part23(fileName: String): Int {
    val map = File(fileName).readLines().map { it.toCharArray() }
    lateinit var start: Pair<Int, Int>
    lateinit var end: Pair<Int, Int>
    val graph =
        mutableMapOf<Pair<Int, Int>, MutableSet<Pair<Int, Int>>>()//mutableMapOf<Pair<Int, Int>, Set<Pair<Int, Int>>>( emptyMap<Pair<Int, Int>, Set<Pair<Int, Int>>>())
    map.forEachIndexed { y, line ->
        line.forEachIndexed { x, it ->
            val node = Pair(y, x)
            graph[node] = mutableSetOf()
            if (it == 'S') {
                start = node
            }
            if (it == 'E') {
                end = node
            }
            // add left node
            if (x > 0 && isConnected(it, map[y][x - 1])) {
                val edges = graph[node]
                edges!!.add(Pair(y, x - 1))
                graph[node] = edges
            }
            // add right node
            if (x < line.size - 1 && isConnected(it, map[y][x + 1])) {
                val edges = graph[node]
                edges!!.add(Pair(y, x + 1))
                graph[node] = edges
            }
            // add lower node
            if (y < map.size - 1 && isConnected(it, map[y + 1][x])) {
                val edges = graph[node]
                edges!!.add(Pair(y + 1, x))
                graph[node] = edges
            }
            // add upper node
            if (y > 0 && isConnected(it, map[y - 1][x])) {
                val edges = graph[node]
                edges!!.add(Pair(y - 1, x))
                graph[node] = edges
            }


        }
    }
    return bfs(graph, start, end)

}

fun part24(fileName: String): Int {
    val map = File(fileName).readLines().map { it.toCharArray() }
    lateinit var end: Pair<Int, Int>
    val graph =
        mutableMapOf<Pair<Int, Int>, MutableSet<Pair<Int, Int>>>()//mutableMapOf<Pair<Int, Int>, Set<Pair<Int, Int>>>( emptyMap<Pair<Int, Int>, Set<Pair<Int, Int>>>())
    map.forEachIndexed { y, line ->
        line.forEachIndexed { x, it ->
            val node = Pair(y, x)
            graph[node] = mutableSetOf()
            if (it == 'E') {
                end = node
            }
            // add left node
            if (x > 0 && isConnected(it, map[y][x - 1])) {
                val edges = graph[node]
                edges!!.add(Pair(y, x - 1))
                graph[node] = edges
            }
            // add right node
            if (x < line.size - 1 && isConnected(it, map[y][x + 1])) {
                val edges = graph[node]
                edges!!.add(Pair(y, x + 1))
                graph[node] = edges
            }
            // add lower node
            if (y < map.size - 1 && isConnected(it, map[y + 1][x])) {
                val edges = graph[node]
                edges!!.add(Pair(y + 1, x))
                graph[node] = edges
            }
            // add upper node
            if (y > 0 && isConnected(it, map[y - 1][x])) {
                val edges = graph[node]
                edges!!.add(Pair(y - 1, x))
                graph[node] = edges
            }


        }
    }
    return graph.filter { map[it.key.first][it.key.second] == 'S' || map[it.key.first][it.key.second] == 'a' }
        .map { bfs(graph, it.key, end) }.filter { it > 0 }.min()
}

fun bfs(
    graph: MutableMap<Pair<Int, Int>, MutableSet<Pair<Int, Int>>>,
    start: Pair<Int, Int>,
    end: Pair<Int, Int>
): Int {
    val visited = Array(graph.keys.size) { BooleanArray(graph.keys.size) { false } }
    val queue: MutableList<Pair<Pair<Int, Int>, Int>> = mutableListOf(Pair(start, 0))
    while (queue.isNotEmpty()) {
        val (node, distance) = queue.removeAt(0)
        if (!visited[node.first][node.second]) {
            if (node == end) {
                return distance
            }
            graph[node]!!.forEach {
                queue.add(it to distance + 1)
            }
            // Mark the dequeued node as visited
            visited[node.first][node.second] = true
        }
    }

    return -1
}

fun depthFirstSearch(
    graph: MutableMap<Pair<Int, Int>, MutableSet<Pair<Int, Int>>>,
    start: Pair<Int, Int>,
    end: Pair<Int, Int>,
    steps: Int,
    shortestDistances: Array<IntArray>
): Array<IntArray> {
    val connectedNodes = graph[start]!!
    val newSteps = steps + 1
    if (connectedNodes.isEmpty()) {
        return shortestDistances
    }
    for (node in connectedNodes) {
        if (node == end) {
            shortestDistances[end.first][end.second] = newSteps
            return shortestDistances
        } else {
            if (shortestDistances[node.first][node.second] > newSteps) {
                shortestDistances[node.first][node.second] = newSteps
                depthFirstSearch(graph, node, end, newSteps, shortestDistances)
            }
        }
    }
    return shortestDistances
}

fun isConnected(me: Char, neighbor: Char): Boolean {
    return ((neighbor - me) <= 1 && neighbor != 'E') || neighbor == 'S' || ((me == 'z') && (neighbor == 'E')) || (me == 'S' && neighbor == 'a')
}
