package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*
import java.util.*

class Description internal constructor(doc: Document) {
    var titleInfo = TitleInfo(doc)
        private set
    var srcTitleInfo = SrcTitleInfo(doc)
        private set
    var documentInfo = DocumentInfo(doc)
        private set
    var publishInfo = PublishInfo(doc)
        private set
    var customInfo = ArrayList<CustomInfo>()
        private set
}
