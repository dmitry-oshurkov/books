package name.oshurkov.books.fb2.parser

import org.w3c.dom.*
import org.xml.sax.*
import java.io.*
import java.util.*
import javax.xml.parsers.*

class FictionBook(file: File?, bytes: ByteArray?) {
    lateinit var xmlns: Array<Xmlns?>
        private set
    var description: Description
        private set
    var bodies = mutableListOf<Body>()
    var binaries = hashMapOf<String, Binary>()
    var encoding = "utf-8"

    init {

        var foundIllegalCharacters = false

        try {

            val reader = if (file != null)
                FileReader(file)
            else
                InputStreamReader(ByteArrayInputStream(bytes))

            BufferedReader(reader).use { br1 ->

                var line = br1.readLine().trim { it <= ' ' }
                if (!line.startsWith("<")) {
                    foundIllegalCharacters = true
                }
                while (!line.endsWith("?>")) {
                    line += """
                    
                    ${br1.readLine().trim { it <= ' ' }}
                    """.trimIndent()
                }
                val start = line.indexOf("encoding") + 8
                var substring = line.substring(start)
                substring = substring.substring(substring.indexOf("\"") + 1)
                encoding = substring.substring(0, substring.indexOf("\"")).toLowerCase()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val reader = if (foundIllegalCharacters) {

            val reader = if (file != null)
                FileReader(file)
            else
                InputStreamReader(ByteArrayInputStream(bytes))

            BufferedReader(reader).use { br2 ->

                var line = br2.readLine()
                if (line != null && line.contains("<")) {
                    line = line.substring(line.indexOf("<"))
                }

                val text = StringBuilder()
                while (line != null) {
                    text.append(line)
                    line = br2.readLine()
                }

                StringReader(text.toString())
            }
        } else {
            val inputStream = if (file != null)
                FileInputStream(file)
            else
                ByteArrayInputStream(bytes)
            InputStreamReader(inputStream, encoding)
        }

        val db = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = db.parse(InputSource(reader))

        initXmlns(doc)
        description = Description(doc)
        val bodyNodes = doc.getElementsByTagName("body")

        for (item in 0 until bodyNodes.length) {
            bodies.add(Body(bodyNodes.item(item)))
        }

        val binary = doc.getElementsByTagName("binary")

        for (item in 0 until binary.length) {
            val binary1 = Binary(binary.item(item))
            binaries[binary1.id!!.replace("#", "")] = binary1
        }
    }

    private fun setXmlns(nodeList: ArrayList<Node>) {
        xmlns = arrayOfNulls(nodeList.size)
        for (index in nodeList.indices) {
            val node = nodeList[index]
            xmlns[index] = Xmlns(node)
        }
    }

    private fun initXmlns(doc: Document) {
        val fictionBook = doc.getElementsByTagName("FictionBook")
        val xmlns = ArrayList<Node>()
        for (item in 0 until fictionBook.length) {
            val map = fictionBook.item(item).attributes
            for (index in 0 until map.length) {
                val node = map.item(index)
                xmlns.add(node)
            }
        }
        setXmlns(xmlns)
    }

    val authors get() = description.documentInfo.authors
    val body get() = getBody(null)
    val notes get() = getBody("notes")
    val comments get() = getBody("comments")

    private fun getBody(name: String?): Body {
        for (body in bodies) {
            if (name + "" == body.name + "") {
                return body
            }
        }
        return bodies[0]
    }

    val title get() = description.titleInfo.bookTitle!!
    val lang get() = description.titleInfo.lang!!
    val annotation get() = description.titleInfo.annotation!!
}
