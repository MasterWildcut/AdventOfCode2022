package model

abstract class TreeNode(val name: String) {
    abstract fun getSize(): Int
}

class Directory(name: String, val upperDirectory: Directory?) : TreeNode(name) {
    private val children: MutableSet<TreeNode> = mutableSetOf()

    override fun getSize(): Int {
        return children.stream().map { it.getSize() }.toList().sum()
    }

    fun addDirectory(name: String) {
        children.add(Directory(name, this))
    }

    fun changeDirectory(name: String): Directory? {
        return children.filterIsInstance<Directory>().find { it.name == name }
    }

    fun addFile(size: Int, name: String) {
        children.add(Leaf(size, name))
    }

    fun getAllSubDirectories(): MutableList<Directory> {
        val directories = children.filterIsInstance<Directory>().toList().toMutableList()
        if (directories.isEmpty()) {
            return mutableListOf()
        }
        val childDirectories = mutableListOf<Directory>()
        for (directory in directories) {
            childDirectories.addAll(directory.getAllSubDirectories())
        }
        directories.addAll(childDirectories)
        return directories
    }
}

class Leaf(private val size: Int, name: String) : TreeNode(name) {

    override fun getSize(): Int {
        return this.size
    }
}
