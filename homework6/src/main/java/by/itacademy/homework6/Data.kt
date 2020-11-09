package by.itacademy.homework6

class Data private constructor() {
    private object DataHolder {
        val INSTANCE = Data()
    }

    companion object {
        val dataInstance: Data by lazy { DataHolder.INSTANCE }

    }
    val fileList = mutableListOf<String>()

}