package name.oshurkov.books.fb2.parser

import org.w3c.dom.*
import org.xml.sax.*
import java.io.*
import java.util.*
import javax.xml.parsers.*

class FictionBook(file: File) {
    lateinit var xmlns: Array<Xmlns?>
        private set
    var description: Description
        private set
    var bodies = mutableListOf<Body>()
    var binaries = hashMapOf<String, Binary>()
    var encoding = "utf-8"

    init {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        val inputStream: InputStream = FileInputStream(file)
        var br = BufferedReader(FileReader(file))
        var foundIllegalCharacters = false
        try {
            var line = br.readLine().trim { it <= ' ' }
            if (!line.startsWith("<")) {
                foundIllegalCharacters = true
            }
            while (!line.endsWith("?>")) {
                line += """
                    
                    ${br.readLine().trim { it <= ' ' }}
                    """.trimIndent()
            }
            val start = line.indexOf("encoding") + 8
            var substring = line.substring(start)
            substring = substring.substring(substring.indexOf("\"") + 1)
            encoding = substring.substring(0, substring.indexOf("\"")).toLowerCase()
            br.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val doc: Document
        if (foundIllegalCharacters) {
            val text = StringBuilder()
            br = BufferedReader(FileReader(file))
            var line = br.readLine()
            if (line != null && line.contains("<")) {
                line = line.substring(line.indexOf("<"))
            }
            while (line != null) {
                text.append(line)
                line = br.readLine()
            }
            br.close()
            doc = db.parse(InputSource(StringReader(text.toString())))
        } else {
            doc = db.parse(InputSource(InputStreamReader(inputStream, encoding)))
        }
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
