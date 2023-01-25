package name.oshurkov.books.fb2;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.example.books.file.xml.fb2 package.
 * &lt;p&gt;An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StyleTypeStrong_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "strong");
    private final static QName _StyleTypeEmphasis_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "emphasis");
    private final static QName _StyleTypeStyle_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "style");
    private final static QName _StyleTypeA_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "a");
    private final static QName _StyleTypeStrikethrough_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "strikethrough");
    private final static QName _StyleTypeSub_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "sub");
    private final static QName _StyleTypeSup_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "sup");
    private final static QName _StyleTypeCode_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "code");
    private final static QName _StyleTypeImage_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "image");
    private final static QName _TableTypeTrTh_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "th");
    private final static QName _TableTypeTrTd_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "td");
    private final static QName _AnnotationTypeP_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "p");
    private final static QName _AnnotationTypePoem_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "poem");
    private final static QName _AnnotationTypeCite_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "cite");
    private final static QName _AnnotationTypeSubtitle_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "subtitle");
    private final static QName _AnnotationTypeTable_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "table");
    private final static QName _AnnotationTypeEmptyLine_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "empty-line");
    private final static QName _SelectionTypeExtractP_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0/markup", "p");
    private final static QName _SelectionTypeExtractImage_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0/markup", "image");
    private final static QName _SelectionTypeExtractPoem_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0/markup", "poem");
    private final static QName _SelectionTypeExtractSubtitle_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0/markup", "subtitle");
    private final static QName _SelectionTypeExtractCite_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0/markup", "cite");
    private final static QName _SelectionTypeExtractEmptyLine_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0/markup", "empty-line");
    private final static QName _SelectionTypeExtractTable_QNAME = new QName("http://www.gribuser.ru/xml/fictionbook/2.0/markup", "table");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.books.file.xml.fb2
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FictionBook }
     */
    public FictionBook createFictionBook() {
        return new FictionBook();
    }

    /**
     * Create an instance of {@link TitleInfoType }
     */
    public TitleInfoType createTitleInfoType() {
        return new TitleInfoType();
    }

    /**
     * Create an instance of {@link TableType }
     */
    public TableType createTableType() {
        return new TableType();
    }

    /**
     * Create an instance of {@link SectionType }
     */
    public SectionType createSectionType() {
        return new SectionType();
    }

    /**
     * Create an instance of {@link PoemType }
     */
    public PoemType createPoemType() {
        return new PoemType();
    }

    /**
     * Create an instance of {@link FictionBook.Description }
     */
    public FictionBook.Description createFictionBookDescription() {
        return new FictionBook.Description();
    }

    /**
     * Create an instance of {@link SelectionType }
     */
    public SelectionType createSelectionType() {
        return new SelectionType();
    }

    /**
     * Create an instance of {@link FictionBookMarkup }
     */
    public FictionBookMarkup createFictionBookMarkup() {
        return new FictionBookMarkup();
    }

    /**
     * Create an instance of {@link FictionBook.Stylesheet }
     */
    public FictionBook.Stylesheet createFictionBookStylesheet() {
        return new FictionBook.Stylesheet();
    }

    /**
     * Create an instance of {@link BodyType }
     */
    public BodyType createBodyType() {
        return new BodyType();
    }

    /**
     * Create an instance of {@link FictionBook.Binary }
     */
    public FictionBook.Binary createFictionBookBinary() {
        return new FictionBook.Binary();
    }

    /**
     * Create an instance of {@link AuthorType }
     */
    public AuthorType createAuthorType() {
        return new AuthorType();
    }

    /**
     * Create an instance of {@link TextFieldType }
     */
    public TextFieldType createTextFieldType() {
        return new TextFieldType();
    }

    /**
     * Create an instance of {@link DateType }
     */
    public DateType createDateType() {
        return new DateType();
    }

    /**
     * Create an instance of {@link TitleType }
     */
    public TitleType createTitleType() {
        return new TitleType();
    }

    /**
     * Create an instance of {@link ImageType }
     */
    public ImageType createImageType() {
        return new ImageType();
    }

    /**
     * Create an instance of {@link PType }
     */
    public PType createPType() {
        return new PType();
    }

    /**
     * Create an instance of {@link CiteType }
     */
    public CiteType createCiteType() {
        return new CiteType();
    }

    /**
     * Create an instance of {@link EpigraphType }
     */
    public EpigraphType createEpigraphType() {
        return new EpigraphType();
    }

    /**
     * Create an instance of {@link AnnotationType }
     */
    public AnnotationType createAnnotationType() {
        return new AnnotationType();
    }

    /**
     * Create an instance of {@link StyleType }
     */
    public StyleType createStyleType() {
        return new StyleType();
    }

    /**
     * Create an instance of {@link NamedStyleType }
     */
    public NamedStyleType createNamedStyleType() {
        return new NamedStyleType();
    }

    /**
     * Create an instance of {@link LinkType }
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link StyleLinkType }
     */
    public StyleLinkType createStyleLinkType() {
        return new StyleLinkType();
    }

    /**
     * Create an instance of {@link SequenceType }
     */
    public SequenceType createSequenceType() {
        return new SequenceType();
    }

    /**
     * Create an instance of {@link ShareInstructionType }
     */
    public ShareInstructionType createShareInstructionType() {
        return new ShareInstructionType();
    }

    /**
     * Create an instance of {@link PartShareInstructionType }
     */
    public PartShareInstructionType createPartShareInstructionType() {
        return new PartShareInstructionType();
    }

    /**
     * Create an instance of {@link OutPutDocumentType }
     */
    public OutPutDocumentType createOutPutDocumentType() {
        return new OutPutDocumentType();
    }

    /**
     * Create an instance of {@link TdType }
     */
    public TdType createTdType() {
        return new TdType();
    }

    /**
     * Create an instance of {@link InlineImageType }
     */
    public InlineImageType createInlineImageType() {
        return new InlineImageType();
    }

    /**
     * Create an instance of {@link TitleInfoType.Genre }
     */
    public TitleInfoType.Genre createTitleInfoTypeGenre() {
        return new TitleInfoType.Genre();
    }

    /**
     * Create an instance of {@link TitleInfoType.Author }
     */
    public TitleInfoType.Author createTitleInfoTypeAuthor() {
        return new TitleInfoType.Author();
    }

    /**
     * Create an instance of {@link TitleInfoType.Coverpage }
     */
    public TitleInfoType.Coverpage createTitleInfoTypeCoverpage() {
        return new TitleInfoType.Coverpage();
    }

    /**
     * Create an instance of {@link TableType.Tr }
     */
    public TableType.Tr createTableTypeTr() {
        return new TableType.Tr();
    }

    /**
     * Create an instance of {@link SectionType.P }
     */
    public SectionType.P createSectionTypeP() {
        return new SectionType.P();
    }

    /**
     * Create an instance of {@link PoemType.Stanza }
     */
    public PoemType.Stanza createPoemTypeStanza() {
        return new PoemType.Stanza();
    }

    /**
     * Create an instance of {@link FictionBook.Description.DocumentInfo }
     */
    public FictionBook.Description.DocumentInfo createFictionBookDescriptionDocumentInfo() {
        return new FictionBook.Description.DocumentInfo();
    }

    /**
     * Create an instance of {@link FictionBook.Description.PublishInfo }
     */
    public FictionBook.Description.PublishInfo createFictionBookDescriptionPublishInfo() {
        return new FictionBook.Description.PublishInfo();
    }

    /**
     * Create an instance of {@link FictionBook.Description.CustomInfo }
     */
    public FictionBook.Description.CustomInfo createFictionBookDescriptionCustomInfo() {
        return new FictionBook.Description.CustomInfo();
    }

    /**
     * Create an instance of {@link SelectionType.Note }
     */
    public SelectionType.Note createSelectionTypeNote() {
        return new SelectionType.Note();
    }

    /**
     * Create an instance of {@link SelectionType.Extract }
     */
    public SelectionType.Extract createSelectionTypeExtract() {
        return new SelectionType.Extract();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strong", scope = StyleType.class)
    public JAXBElement<StyleType> createStyleTypeStrong(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeStrong_QNAME, StyleType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "emphasis", scope = StyleType.class)
    public JAXBElement<StyleType> createStyleTypeEmphasis(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeEmphasis_QNAME, StyleType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamedStyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link NamedStyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "style", scope = StyleType.class)
    public JAXBElement<NamedStyleType> createStyleTypeStyle(NamedStyleType value) {
        return new JAXBElement<NamedStyleType>(_StyleTypeStyle_QNAME, NamedStyleType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "a", scope = StyleType.class)
    public JAXBElement<LinkType> createStyleTypeA(LinkType value) {
        return new JAXBElement<LinkType>(_StyleTypeA_QNAME, LinkType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strikethrough", scope = StyleType.class)
    public JAXBElement<StyleType> createStyleTypeStrikethrough(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeStrikethrough_QNAME, StyleType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sub", scope = StyleType.class)
    public JAXBElement<StyleType> createStyleTypeSub(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeSub_QNAME, StyleType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sup", scope = StyleType.class)
    public JAXBElement<StyleType> createStyleTypeSup(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeSup_QNAME, StyleType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "code", scope = StyleType.class)
    public JAXBElement<StyleType> createStyleTypeCode(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeCode_QNAME, StyleType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "image", scope = StyleType.class)
    public JAXBElement<InlineImageType> createStyleTypeImage(InlineImageType value) {
        return new JAXBElement<InlineImageType>(_StyleTypeImage_QNAME, InlineImageType.class, StyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TdType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link TdType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "th", scope = TableType.Tr.class)
    public JAXBElement<TdType> createTableTypeTrTh(TdType value) {
        return new JAXBElement<TdType>(_TableTypeTrTh_QNAME, TdType.class, TableType.Tr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TdType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link TdType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "td", scope = TableType.Tr.class)
    public JAXBElement<TdType> createTableTypeTrTd(TdType value) {
        return new JAXBElement<TdType>(_TableTypeTrTd_QNAME, TdType.class, TableType.Tr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strong", scope = StyleLinkType.class)
    public JAXBElement<StyleLinkType> createStyleLinkTypeStrong(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeStrong_QNAME, StyleLinkType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "emphasis", scope = StyleLinkType.class)
    public JAXBElement<StyleLinkType> createStyleLinkTypeEmphasis(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeEmphasis_QNAME, StyleLinkType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "style", scope = StyleLinkType.class)
    public JAXBElement<StyleLinkType> createStyleLinkTypeStyle(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeStyle_QNAME, StyleLinkType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strikethrough", scope = StyleLinkType.class)
    public JAXBElement<StyleLinkType> createStyleLinkTypeStrikethrough(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeStrikethrough_QNAME, StyleLinkType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sub", scope = StyleLinkType.class)
    public JAXBElement<StyleLinkType> createStyleLinkTypeSub(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeSub_QNAME, StyleLinkType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sup", scope = StyleLinkType.class)
    public JAXBElement<StyleLinkType> createStyleLinkTypeSup(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeSup_QNAME, StyleLinkType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "code", scope = StyleLinkType.class)
    public JAXBElement<StyleLinkType> createStyleLinkTypeCode(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeCode_QNAME, StyleLinkType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "image", scope = StyleLinkType.class)
    public JAXBElement<InlineImageType> createStyleLinkTypeImage(InlineImageType value) {
        return new JAXBElement<InlineImageType>(_StyleTypeImage_QNAME, InlineImageType.class, StyleLinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strong", scope = LinkType.class)
    public JAXBElement<StyleLinkType> createLinkTypeStrong(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeStrong_QNAME, StyleLinkType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "emphasis", scope = LinkType.class)
    public JAXBElement<StyleLinkType> createLinkTypeEmphasis(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeEmphasis_QNAME, StyleLinkType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "style", scope = LinkType.class)
    public JAXBElement<StyleLinkType> createLinkTypeStyle(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeStyle_QNAME, StyleLinkType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strikethrough", scope = LinkType.class)
    public JAXBElement<StyleLinkType> createLinkTypeStrikethrough(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeStrikethrough_QNAME, StyleLinkType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sub", scope = LinkType.class)
    public JAXBElement<StyleLinkType> createLinkTypeSub(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeSub_QNAME, StyleLinkType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sup", scope = LinkType.class)
    public JAXBElement<StyleLinkType> createLinkTypeSup(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeSup_QNAME, StyleLinkType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "code", scope = LinkType.class)
    public JAXBElement<StyleLinkType> createLinkTypeCode(StyleLinkType value) {
        return new JAXBElement<StyleLinkType>(_StyleTypeCode_QNAME, StyleLinkType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "image", scope = LinkType.class)
    public JAXBElement<InlineImageType> createLinkTypeImage(InlineImageType value) {
        return new JAXBElement<InlineImageType>(_StyleTypeImage_QNAME, InlineImageType.class, LinkType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strong", scope = NamedStyleType.class)
    public JAXBElement<StyleType> createNamedStyleTypeStrong(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeStrong_QNAME, StyleType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "emphasis", scope = NamedStyleType.class)
    public JAXBElement<StyleType> createNamedStyleTypeEmphasis(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeEmphasis_QNAME, StyleType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamedStyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link NamedStyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "style", scope = NamedStyleType.class)
    public JAXBElement<NamedStyleType> createNamedStyleTypeStyle(NamedStyleType value) {
        return new JAXBElement<NamedStyleType>(_StyleTypeStyle_QNAME, NamedStyleType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "a", scope = NamedStyleType.class)
    public JAXBElement<LinkType> createNamedStyleTypeA(LinkType value) {
        return new JAXBElement<LinkType>(_StyleTypeA_QNAME, LinkType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strikethrough", scope = NamedStyleType.class)
    public JAXBElement<StyleType> createNamedStyleTypeStrikethrough(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeStrikethrough_QNAME, StyleType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sub", scope = NamedStyleType.class)
    public JAXBElement<StyleType> createNamedStyleTypeSub(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeSub_QNAME, StyleType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "sup", scope = NamedStyleType.class)
    public JAXBElement<StyleType> createNamedStyleTypeSup(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeSup_QNAME, StyleType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "code", scope = NamedStyleType.class)
    public JAXBElement<StyleType> createNamedStyleTypeCode(StyleType value) {
        return new JAXBElement<StyleType>(_StyleTypeCode_QNAME, StyleType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "image", scope = NamedStyleType.class)
    public JAXBElement<InlineImageType> createNamedStyleTypeImage(InlineImageType value) {
        return new JAXBElement<InlineImageType>(_StyleTypeImage_QNAME, InlineImageType.class, NamedStyleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "strong", scope = SectionType.P.class)
    public JAXBElement<String> createSectionTypePStrong(String value) {
        return new JAXBElement<String>(_StyleTypeStrong_QNAME, String.class, SectionType.P.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "emphasis", scope = SectionType.P.class)
    public JAXBElement<String> createSectionTypePEmphasis(String value) {
        return new JAXBElement<String>(_StyleTypeEmphasis_QNAME, String.class, SectionType.P.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "a", scope = SectionType.P.class)
    public JAXBElement<LinkType> createSectionTypePA(LinkType value) {
        return new JAXBElement<LinkType>(_StyleTypeA_QNAME, LinkType.class, SectionType.P.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "p", scope = AnnotationType.class)
    public JAXBElement<PType> createAnnotationTypeP(PType value) {
        return new JAXBElement<PType>(_AnnotationTypeP_QNAME, PType.class, AnnotationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "poem", scope = AnnotationType.class)
    public JAXBElement<PoemType> createAnnotationTypePoem(PoemType value) {
        return new JAXBElement<PoemType>(_AnnotationTypePoem_QNAME, PoemType.class, AnnotationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CiteType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link CiteType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "cite", scope = AnnotationType.class)
    public JAXBElement<CiteType> createAnnotationTypeCite(CiteType value) {
        return new JAXBElement<CiteType>(_AnnotationTypeCite_QNAME, CiteType.class, AnnotationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "subtitle", scope = AnnotationType.class)
    public JAXBElement<PType> createAnnotationTypeSubtitle(PType value) {
        return new JAXBElement<PType>(_AnnotationTypeSubtitle_QNAME, PType.class, AnnotationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TableType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link TableType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "table", scope = AnnotationType.class)
    public JAXBElement<TableType> createAnnotationTypeTable(TableType value) {
        return new JAXBElement<TableType>(_AnnotationTypeTable_QNAME, TableType.class, AnnotationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "empty-line", scope = AnnotationType.class)
    public JAXBElement<Object> createAnnotationTypeEmptyLine(Object value) {
        return new JAXBElement<Object>(_AnnotationTypeEmptyLine_QNAME, Object.class, AnnotationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "p", scope = CiteType.class)
    public JAXBElement<PType> createCiteTypeP(PType value) {
        return new JAXBElement<PType>(_AnnotationTypeP_QNAME, PType.class, CiteType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "poem", scope = CiteType.class)
    public JAXBElement<PoemType> createCiteTypePoem(PoemType value) {
        return new JAXBElement<PoemType>(_AnnotationTypePoem_QNAME, PoemType.class, CiteType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "empty-line", scope = CiteType.class)
    public JAXBElement<Object> createCiteTypeEmptyLine(Object value) {
        return new JAXBElement<Object>(_AnnotationTypeEmptyLine_QNAME, Object.class, CiteType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "subtitle", scope = CiteType.class)
    public JAXBElement<PType> createCiteTypeSubtitle(PType value) {
        return new JAXBElement<PType>(_AnnotationTypeSubtitle_QNAME, PType.class, CiteType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TableType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link TableType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", name = "table", scope = CiteType.class)
    public JAXBElement<TableType> createCiteTypeTable(TableType value) {
        return new JAXBElement<TableType>(_AnnotationTypeTable_QNAME, TableType.class, CiteType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", name = "p", scope = SelectionType.Extract.class)
    public JAXBElement<PType> createSelectionTypeExtractP(PType value) {
        return new JAXBElement<PType>(_SelectionTypeExtractP_QNAME, PType.class, SelectionType.Extract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ImageType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", name = "image", scope = SelectionType.Extract.class)
    public JAXBElement<ImageType> createSelectionTypeExtractImage(ImageType value) {
        return new JAXBElement<ImageType>(_SelectionTypeExtractImage_QNAME, ImageType.class, SelectionType.Extract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", name = "poem", scope = SelectionType.Extract.class)
    public JAXBElement<PoemType> createSelectionTypeExtractPoem(PoemType value) {
        return new JAXBElement<PoemType>(_SelectionTypeExtractPoem_QNAME, PoemType.class, SelectionType.Extract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", name = "subtitle", scope = SelectionType.Extract.class)
    public JAXBElement<PType> createSelectionTypeExtractSubtitle(PType value) {
        return new JAXBElement<PType>(_SelectionTypeExtractSubtitle_QNAME, PType.class, SelectionType.Extract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CiteType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link CiteType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", name = "cite", scope = SelectionType.Extract.class)
    public JAXBElement<CiteType> createSelectionTypeExtractCite(CiteType value) {
        return new JAXBElement<CiteType>(_SelectionTypeExtractCite_QNAME, CiteType.class, SelectionType.Extract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", name = "empty-line", scope = SelectionType.Extract.class)
    public JAXBElement<Object> createSelectionTypeExtractEmptyLine(Object value) {
        return new JAXBElement<Object>(_SelectionTypeExtractEmptyLine_QNAME, Object.class, SelectionType.Extract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TableType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link TableType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", name = "table", scope = SelectionType.Extract.class)
    public JAXBElement<TableType> createSelectionTypeExtractTable(TableType value) {
        return new JAXBElement<TableType>(_SelectionTypeExtractTable_QNAME, TableType.class, SelectionType.Extract.class, value);
    }

}
