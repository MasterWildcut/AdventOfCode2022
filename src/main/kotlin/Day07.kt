import model.Directory
import java.io.File

fun main() {
    println(part13("src/main/resources/Day07_01.txt"))
    println(part14("src/main/resources/Day07_01.txt"))
}

fun part13(fileName: String): Int {
    val fileSystem = getFileSystem(fileName)

    val allDirectories = fileSystem.getAllSubDirectories()
    allDirectories.sortBy { it.getSize() }
    return allDirectories.filter { it.getSize() <= 100000 }.sumOf { it.getSize() }
}


fun part14(fileName: String): Int {
    val fileSystem = getFileSystem(fileName)

    val allDirectories = fileSystem.getAllSubDirectories()
    allDirectories.sortByDescending { it.getSize() }
    val totalSpace = 70000000
    val neededSpace = 30000000
    val unusedSpace = totalSpace - fileSystem.getSize()
    val spaceToBeDeleted = neededSpace - unusedSpace
    val find = allDirectories.findLast { it.getSize() >= spaceToBeDeleted }
    return find!!.getSize()
}

private fun getFileSystem(fileName: String): Directory {
    val file = File(fileName).readLines()
    val fileSystem = Directory("/", null)
    var currentNode = fileSystem
    val lines = file.subList(1, file.size)
    lines.forEach {
        if (isCommand(it)) {
            currentNode = executeCommand(it, currentNode)
        } else {
            addChild(it, currentNode)
        }
    }
    return fileSystem
}

fun isCommand(line: String): Boolean {
    return line.startsWith("$")
}

fun executeCommand(line: String, currentNode: Directory): Directory {
    val split = line.split(" ")
    if (split[1] == "ls") {
        return currentNode
    }
    if (split[2] == "..") {
        return currentNode.upperDirectory ?: currentNode
    }
    return currentNode.changeDirectory(split[2]) ?: currentNode
}

fun addChild(line: String, currentNode: Directory) {
    val split = line.split(" ")
    if (split[0] == "dir") {
        currentNode.addDirectory(split[1])
    } else {
        currentNode.addFile(split[0].toInt(), split[1])
    }

}

