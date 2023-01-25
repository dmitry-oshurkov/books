package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for genreType.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="genreType"&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&amp;gt;
 * &amp;lt;enumeration value="accounting"/&amp;gt;
 * &amp;lt;enumeration value="adv_animal"/&amp;gt;
 * &amp;lt;enumeration value="adv_geo"/&amp;gt;
 * &amp;lt;enumeration value="adv_history"/&amp;gt;
 * &amp;lt;enumeration value="adv_maritime"/&amp;gt;
 * &amp;lt;enumeration value="adv_western"/&amp;gt;
 * &amp;lt;enumeration value="adventure"/&amp;gt;
 * &amp;lt;enumeration value="antique"/&amp;gt;
 * &amp;lt;enumeration value="antique_ant"/&amp;gt;
 * &amp;lt;enumeration value="antique_east"/&amp;gt;
 * &amp;lt;enumeration value="antique_european"/&amp;gt;
 * &amp;lt;enumeration value="antique_myths"/&amp;gt;
 * &amp;lt;enumeration value="antique_russian"/&amp;gt;
 * &amp;lt;enumeration value="aphorism_quote"/&amp;gt;
 * &amp;lt;enumeration value="architecture_book"/&amp;gt;
 * &amp;lt;enumeration value="auto_regulations"/&amp;gt;
 * &amp;lt;enumeration value="banking"/&amp;gt;
 * &amp;lt;enumeration value="beginning_authors"/&amp;gt;
 * &amp;lt;enumeration value="child_adv"/&amp;gt;
 * &amp;lt;enumeration value="child_det"/&amp;gt;
 * &amp;lt;enumeration value="child_education"/&amp;gt;
 * &amp;lt;enumeration value="child_prose"/&amp;gt;
 * &amp;lt;enumeration value="child_sf"/&amp;gt;
 * &amp;lt;enumeration value="child_tale"/&amp;gt;
 * &amp;lt;enumeration value="child_verse"/&amp;gt;
 * &amp;lt;enumeration value="children"/&amp;gt;
 * &amp;lt;enumeration value="cinema_theatre"/&amp;gt;
 * &amp;lt;enumeration value="city_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="comp_db"/&amp;gt;
 * &amp;lt;enumeration value="comp_hard"/&amp;gt;
 * &amp;lt;enumeration value="comp_osnet"/&amp;gt;
 * &amp;lt;enumeration value="comp_programming"/&amp;gt;
 * &amp;lt;enumeration value="comp_soft"/&amp;gt;
 * &amp;lt;enumeration value="comp_www"/&amp;gt;
 * &amp;lt;enumeration value="computers"/&amp;gt;
 * &amp;lt;enumeration value="design"/&amp;gt;
 * &amp;lt;enumeration value="det_action"/&amp;gt;
 * &amp;lt;enumeration value="det_classic"/&amp;gt;
 * &amp;lt;enumeration value="det_crime"/&amp;gt;
 * &amp;lt;enumeration value="det_espionage"/&amp;gt;
 * &amp;lt;enumeration value="det_hard"/&amp;gt;
 * &amp;lt;enumeration value="det_history"/&amp;gt;
 * &amp;lt;enumeration value="det_irony"/&amp;gt;
 * &amp;lt;enumeration value="det_police"/&amp;gt;
 * &amp;lt;enumeration value="det_political"/&amp;gt;
 * &amp;lt;enumeration value="detective"/&amp;gt;
 * &amp;lt;enumeration value="dragon_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="dramaturgy"/&amp;gt;
 * &amp;lt;enumeration value="economics"/&amp;gt;
 * &amp;lt;enumeration value="essays"/&amp;gt;
 * &amp;lt;enumeration value="fantasy_fight"/&amp;gt;
 * &amp;lt;enumeration value="foreign_action"/&amp;gt;
 * &amp;lt;enumeration value="foreign_adventure"/&amp;gt;
 * &amp;lt;enumeration value="foreign_antique"/&amp;gt;
 * &amp;lt;enumeration value="foreign_business"/&amp;gt;
 * &amp;lt;enumeration value="foreign_children"/&amp;gt;
 * &amp;lt;enumeration value="foreign_comp"/&amp;gt;
 * &amp;lt;enumeration value="foreign_contemporary"/&amp;gt;
 * &amp;lt;enumeration value="foreign_contemporary_lit"/&amp;gt;
 * &amp;lt;enumeration value="foreign_desc"/&amp;gt;
 * &amp;lt;enumeration value="foreign_detective"/&amp;gt;
 * &amp;lt;enumeration value="foreign_dramaturgy"/&amp;gt;
 * &amp;lt;enumeration value="foreign_edu"/&amp;gt;
 * &amp;lt;enumeration value="foreign_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="foreign_home"/&amp;gt;
 * &amp;lt;enumeration value="foreign_humor"/&amp;gt;
 * &amp;lt;enumeration value="foreign_language"/&amp;gt;
 * &amp;lt;enumeration value="foreign_love"/&amp;gt;
 * &amp;lt;enumeration value="foreign_novel"/&amp;gt;
 * &amp;lt;enumeration value="foreign_other"/&amp;gt;
 * &amp;lt;enumeration value="foreign_poetry"/&amp;gt;
 * &amp;lt;enumeration value="foreign_prose"/&amp;gt;
 * &amp;lt;enumeration value="foreign_psychology"/&amp;gt;
 * &amp;lt;enumeration value="foreign_publicism"/&amp;gt;
 * &amp;lt;enumeration value="foreign_religion"/&amp;gt;
 * &amp;lt;enumeration value="foreign_sf"/&amp;gt;
 * &amp;lt;enumeration value="geo_guides"/&amp;gt;
 * &amp;lt;enumeration value="geography_book"/&amp;gt;
 * &amp;lt;enumeration value="global_economy"/&amp;gt;
 * &amp;lt;enumeration value="historical_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="home"/&amp;gt;
 * &amp;lt;enumeration value="home_cooking"/&amp;gt;
 * &amp;lt;enumeration value="home_crafts"/&amp;gt;
 * &amp;lt;enumeration value="home_diy"/&amp;gt;
 * &amp;lt;enumeration value="home_entertain"/&amp;gt;
 * &amp;lt;enumeration value="home_garden"/&amp;gt;
 * &amp;lt;enumeration value="home_health"/&amp;gt;
 * &amp;lt;enumeration value="home_pets"/&amp;gt;
 * &amp;lt;enumeration value="home_sex"/&amp;gt;
 * &amp;lt;enumeration value="home_sport"/&amp;gt;
 * &amp;lt;enumeration value="humor"/&amp;gt;
 * &amp;lt;enumeration value="humor_anecdote"/&amp;gt;
 * &amp;lt;enumeration value="humor_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="humor_prose"/&amp;gt;
 * &amp;lt;enumeration value="humor_verse"/&amp;gt;
 * &amp;lt;enumeration value="industries"/&amp;gt;
 * &amp;lt;enumeration value="job_hunting"/&amp;gt;
 * &amp;lt;enumeration value="literature_18"/&amp;gt;
 * &amp;lt;enumeration value="literature_19"/&amp;gt;
 * &amp;lt;enumeration value="literature_20"/&amp;gt;
 * &amp;lt;enumeration value="love_contemporary"/&amp;gt;
 * &amp;lt;enumeration value="love_detective"/&amp;gt;
 * &amp;lt;enumeration value="love_erotica"/&amp;gt;
 * &amp;lt;enumeration value="love_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="love_history"/&amp;gt;
 * &amp;lt;enumeration value="love_sf"/&amp;gt;
 * &amp;lt;enumeration value="love_short"/&amp;gt;
 * &amp;lt;enumeration value="magician_book"/&amp;gt;
 * &amp;lt;enumeration value="management"/&amp;gt;
 * &amp;lt;enumeration value="marketing"/&amp;gt;
 * &amp;lt;enumeration value="military_special"/&amp;gt;
 * &amp;lt;enumeration value="music_dancing"/&amp;gt;
 * &amp;lt;enumeration value="narrative"/&amp;gt;
 * &amp;lt;enumeration value="newspapers"/&amp;gt;
 * &amp;lt;enumeration value="nonf_biography"/&amp;gt;
 * &amp;lt;enumeration value="nonf_criticism"/&amp;gt;
 * &amp;lt;enumeration value="nonf_publicism"/&amp;gt;
 * &amp;lt;enumeration value="nonfiction"/&amp;gt;
 * &amp;lt;enumeration value="org_behavior"/&amp;gt;
 * &amp;lt;enumeration value="paper_work"/&amp;gt;
 * &amp;lt;enumeration value="pedagogy_book"/&amp;gt;
 * &amp;lt;enumeration value="periodic"/&amp;gt;
 * &amp;lt;enumeration value="personal_finance"/&amp;gt;
 * &amp;lt;enumeration value="poetry"/&amp;gt;
 * &amp;lt;enumeration value="popadanec"/&amp;gt;
 * &amp;lt;enumeration value="popular_business"/&amp;gt;
 * &amp;lt;enumeration value="prose_classic"/&amp;gt;
 * &amp;lt;enumeration value="prose_counter"/&amp;gt;
 * &amp;lt;enumeration value="prose_history"/&amp;gt;
 * &amp;lt;enumeration value="prose_military"/&amp;gt;
 * &amp;lt;enumeration value="prose_rus_classic"/&amp;gt;
 * &amp;lt;enumeration value="prose_su_classics"/&amp;gt;
 * &amp;lt;enumeration value="psy_alassic"/&amp;gt;
 * &amp;lt;enumeration value="psy_childs"/&amp;gt;
 * &amp;lt;enumeration value="psy_generic"/&amp;gt;
 * &amp;lt;enumeration value="psy_personal"/&amp;gt;
 * &amp;lt;enumeration value="psy_sex_and_family"/&amp;gt;
 * &amp;lt;enumeration value="psy_social"/&amp;gt;
 * &amp;lt;enumeration value="psy_theraphy"/&amp;gt;
 * &amp;lt;enumeration value="real_estate"/&amp;gt;
 * &amp;lt;enumeration value="ref_dict"/&amp;gt;
 * &amp;lt;enumeration value="ref_encyc"/&amp;gt;
 * &amp;lt;enumeration value="ref_guide"/&amp;gt;
 * &amp;lt;enumeration value="ref_ref"/&amp;gt;
 * &amp;lt;enumeration value="reference"/&amp;gt;
 * &amp;lt;enumeration value="religion"/&amp;gt;
 * &amp;lt;enumeration value="religion_esoterics"/&amp;gt;
 * &amp;lt;enumeration value="religion_rel"/&amp;gt;
 * &amp;lt;enumeration value="religion_self"/&amp;gt;
 * &amp;lt;enumeration value="russian_contemporary"/&amp;gt;
 * &amp;lt;enumeration value="russian_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="sci_biology"/&amp;gt;
 * &amp;lt;enumeration value="sci_chem"/&amp;gt;
 * &amp;lt;enumeration value="sci_culture"/&amp;gt;
 * &amp;lt;enumeration value="sci_history"/&amp;gt;
 * &amp;lt;enumeration value="sci_juris"/&amp;gt;
 * &amp;lt;enumeration value="sci_linguistic"/&amp;gt;
 * &amp;lt;enumeration value="sci_math"/&amp;gt;
 * &amp;lt;enumeration value="sci_medicine"/&amp;gt;
 * &amp;lt;enumeration value="sci_philosophy"/&amp;gt;
 * &amp;lt;enumeration value="sci_phys"/&amp;gt;
 * &amp;lt;enumeration value="sci_politics"/&amp;gt;
 * &amp;lt;enumeration value="sci_religion"/&amp;gt;
 * &amp;lt;enumeration value="sci_tech"/&amp;gt;
 * &amp;lt;enumeration value="science"/&amp;gt;
 * &amp;lt;enumeration value="sf"/&amp;gt;
 * &amp;lt;enumeration value="sf_action"/&amp;gt;
 * &amp;lt;enumeration value="sf_cyberpunk"/&amp;gt;
 * &amp;lt;enumeration value="sf_detective"/&amp;gt;
 * &amp;lt;enumeration value="sf_fantasy"/&amp;gt;
 * &amp;lt;enumeration value="sf_heroic"/&amp;gt;
 * &amp;lt;enumeration value="sf_history"/&amp;gt;
 * &amp;lt;enumeration value="sf_horror"/&amp;gt;
 * &amp;lt;enumeration value="sf_humor"/&amp;gt;
 * &amp;lt;enumeration value="sf_social"/&amp;gt;
 * &amp;lt;enumeration value="sf_space"/&amp;gt;
 * &amp;lt;enumeration value="short_story"/&amp;gt;
 * &amp;lt;enumeration value="sketch"/&amp;gt;
 * &amp;lt;enumeration value="small_business"/&amp;gt;
 * &amp;lt;enumeration value="sociology_book"/&amp;gt;
 * &amp;lt;enumeration value="stock"/&amp;gt;
 * &amp;lt;enumeration value="thriller"/&amp;gt;
 * &amp;lt;enumeration value="upbringing_book"/&amp;gt;
 * &amp;lt;enumeration value="vampire_book"/&amp;gt;
 * &amp;lt;enumeration value="visual_arts"/&amp;gt;
 * &amp;lt;enumeration value="unrecognised"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlType(name = "genreType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/genres")
@XmlEnum
public enum GenreType {

    @XmlEnumValue("accounting")
    ACCOUNTING("accounting"),
    @XmlEnumValue("adv_animal")
    ADV_ANIMAL("adv_animal"),
    @XmlEnumValue("adv_geo")
    ADV_GEO("adv_geo"),
    @XmlEnumValue("adv_history")
    ADV_HISTORY("adv_history"),
    @XmlEnumValue("adv_maritime")
    ADV_MARITIME("adv_maritime"),
    @XmlEnumValue("adv_western")
    ADV_WESTERN("adv_western"),
    @XmlEnumValue("adventure")
    ADVENTURE("adventure"),
    @XmlEnumValue("antique")
    ANTIQUE("antique"),
    @XmlEnumValue("antique_ant")
    ANTIQUE_ANT("antique_ant"),
    @XmlEnumValue("antique_east")
    ANTIQUE_EAST("antique_east"),
    @XmlEnumValue("antique_european")
    ANTIQUE_EUROPEAN("antique_european"),
    @XmlEnumValue("antique_myths")
    ANTIQUE_MYTHS("antique_myths"),
    @XmlEnumValue("antique_russian")
    ANTIQUE_RUSSIAN("antique_russian"),
    @XmlEnumValue("aphorism_quote")
    APHORISM_QUOTE("aphorism_quote"),
    @XmlEnumValue("architecture_book")
    ARCHITECTURE_BOOK("architecture_book"),
    @XmlEnumValue("auto_regulations")
    AUTO_REGULATIONS("auto_regulations"),
    @XmlEnumValue("banking")
    BANKING("banking"),
    @XmlEnumValue("beginning_authors")
    BEGINNING_AUTHORS("beginning_authors"),
    @XmlEnumValue("child_adv")
    CHILD_ADV("child_adv"),
    @XmlEnumValue("child_det")
    CHILD_DET("child_det"),
    @XmlEnumValue("child_education")
    CHILD_EDUCATION("child_education"),
    @XmlEnumValue("child_prose")
    CHILD_PROSE("child_prose"),
    @XmlEnumValue("child_sf")
    CHILD_SF("child_sf"),
    @XmlEnumValue("child_tale")
    CHILD_TALE("child_tale"),
    @XmlEnumValue("child_verse")
    CHILD_VERSE("child_verse"),
    @XmlEnumValue("children")
    CHILDREN("children"),
    @XmlEnumValue("cinema_theatre")
    CINEMA_THEATRE("cinema_theatre"),
    @XmlEnumValue("city_fantasy")
    CITY_FANTASY("city_fantasy"),
    @XmlEnumValue("comp_db")
    COMP_DB("comp_db"),
    @XmlEnumValue("comp_hard")
    COMP_HARD("comp_hard"),
    @XmlEnumValue("comp_osnet")
    COMP_OSNET("comp_osnet"),
    @XmlEnumValue("comp_programming")
    COMP_PROGRAMMING("comp_programming"),
    @XmlEnumValue("comp_soft")
    COMP_SOFT("comp_soft"),
    @XmlEnumValue("comp_www")
    COMP_WWW("comp_www"),
    @XmlEnumValue("computers")
    COMPUTERS("computers"),
    @XmlEnumValue("design")
    DESIGN("design"),
    @XmlEnumValue("det_action")
    DET_ACTION("det_action"),
    @XmlEnumValue("det_classic")
    DET_CLASSIC("det_classic"),
    @XmlEnumValue("det_crime")
    DET_CRIME("det_crime"),
    @XmlEnumValue("det_espionage")
    DET_ESPIONAGE("det_espionage"),
    @XmlEnumValue("det_hard")
    DET_HARD("det_hard"),
    @XmlEnumValue("det_history")
    DET_HISTORY("det_history"),
    @XmlEnumValue("det_irony")
    DET_IRONY("det_irony"),
    @XmlEnumValue("det_police")
    DET_POLICE("det_police"),
    @XmlEnumValue("det_political")
    DET_POLITICAL("det_political"),
    @XmlEnumValue("detective")
    DETECTIVE("detective"),
    @XmlEnumValue("dragon_fantasy")
    DRAGON_FANTASY("dragon_fantasy"),
    @XmlEnumValue("dramaturgy")
    DRAMATURGY("dramaturgy"),
    @XmlEnumValue("economics")
    ECONOMICS("economics"),
    @XmlEnumValue("essays")
    ESSAYS("essays"),
    @XmlEnumValue("fantasy_fight")
    FANTASY_FIGHT("fantasy_fight"),
    @XmlEnumValue("foreign_action")
    FOREIGN_ACTION("foreign_action"),
    @XmlEnumValue("foreign_adventure")
    FOREIGN_ADVENTURE("foreign_adventure"),
    @XmlEnumValue("foreign_antique")
    FOREIGN_ANTIQUE("foreign_antique"),
    @XmlEnumValue("foreign_business")
    FOREIGN_BUSINESS("foreign_business"),
    @XmlEnumValue("foreign_children")
    FOREIGN_CHILDREN("foreign_children"),
    @XmlEnumValue("foreign_comp")
    FOREIGN_COMP("foreign_comp"),
    @XmlEnumValue("foreign_contemporary")
    FOREIGN_CONTEMPORARY("foreign_contemporary"),
    @XmlEnumValue("foreign_contemporary_lit")
    FOREIGN_CONTEMPORARY_LIT("foreign_contemporary_lit"),
    @XmlEnumValue("foreign_desc")
    FOREIGN_DESC("foreign_desc"),
    @XmlEnumValue("foreign_detective")
    FOREIGN_DETECTIVE("foreign_detective"),
    @XmlEnumValue("foreign_dramaturgy")
    FOREIGN_DRAMATURGY("foreign_dramaturgy"),
    @XmlEnumValue("foreign_edu")
    FOREIGN_EDU("foreign_edu"),
    @XmlEnumValue("foreign_fantasy")
    FOREIGN_FANTASY("foreign_fantasy"),
    @XmlEnumValue("foreign_home")
    FOREIGN_HOME("foreign_home"),
    @XmlEnumValue("foreign_humor")
    FOREIGN_HUMOR("foreign_humor"),
    @XmlEnumValue("foreign_language")
    FOREIGN_LANGUAGE("foreign_language"),
    @XmlEnumValue("foreign_love")
    FOREIGN_LOVE("foreign_love"),
    @XmlEnumValue("foreign_novel")
    FOREIGN_NOVEL("foreign_novel"),
    @XmlEnumValue("foreign_other")
    FOREIGN_OTHER("foreign_other"),
    @XmlEnumValue("foreign_poetry")
    FOREIGN_POETRY("foreign_poetry"),
    @XmlEnumValue("foreign_prose")
    FOREIGN_PROSE("foreign_prose"),
    @XmlEnumValue("foreign_psychology")
    FOREIGN_PSYCHOLOGY("foreign_psychology"),
    @XmlEnumValue("foreign_publicism")
    FOREIGN_PUBLICISM("foreign_publicism"),
    @XmlEnumValue("foreign_religion")
    FOREIGN_RELIGION("foreign_religion"),
    @XmlEnumValue("foreign_sf")
    FOREIGN_SF("foreign_sf"),
    @XmlEnumValue("geo_guides")
    GEO_GUIDES("geo_guides"),
    @XmlEnumValue("geography_book")
    GEOGRAPHY_BOOK("geography_book"),
    @XmlEnumValue("global_economy")
    GLOBAL_ECONOMY("global_economy"),
    @XmlEnumValue("historical_fantasy")
    HISTORICAL_FANTASY("historical_fantasy"),
    @XmlEnumValue("home")
    HOME("home"),
    @XmlEnumValue("home_cooking")
    HOME_COOKING("home_cooking"),
    @XmlEnumValue("home_crafts")
    HOME_CRAFTS("home_crafts"),
    @XmlEnumValue("home_diy")
    HOME_DIY("home_diy"),
    @XmlEnumValue("home_entertain")
    HOME_ENTERTAIN("home_entertain"),
    @XmlEnumValue("home_garden")
    HOME_GARDEN("home_garden"),
    @XmlEnumValue("home_health")
    HOME_HEALTH("home_health"),
    @XmlEnumValue("home_pets")
    HOME_PETS("home_pets"),
    @XmlEnumValue("home_sex")
    HOME_SEX("home_sex"),
    @XmlEnumValue("home_sport")
    HOME_SPORT("home_sport"),
    @XmlEnumValue("humor")
    HUMOR("humor"),
    @XmlEnumValue("humor_anecdote")
    HUMOR_ANECDOTE("humor_anecdote"),
    @XmlEnumValue("humor_fantasy")
    HUMOR_FANTASY("humor_fantasy"),
    @XmlEnumValue("humor_prose")
    HUMOR_PROSE("humor_prose"),
    @XmlEnumValue("humor_verse")
    HUMOR_VERSE("humor_verse"),
    @XmlEnumValue("industries")
    INDUSTRIES("industries"),
    @XmlEnumValue("job_hunting")
    JOB_HUNTING("job_hunting"),
    @XmlEnumValue("literature_18")
    LITERATURE_18("literature_18"),
    @XmlEnumValue("literature_19")
    LITERATURE_19("literature_19"),
    @XmlEnumValue("literature_20")
    LITERATURE_20("literature_20"),
    @XmlEnumValue("love_contemporary")
    LOVE_CONTEMPORARY("love_contemporary"),
    @XmlEnumValue("love_detective")
    LOVE_DETECTIVE("love_detective"),
    @XmlEnumValue("love_erotica")
    LOVE_EROTICA("love_erotica"),
    @XmlEnumValue("love_fantasy")
    LOVE_FANTASY("love_fantasy"),
    @XmlEnumValue("love_history")
    LOVE_HISTORY("love_history"),
    @XmlEnumValue("love_sf")
    LOVE_SF("love_sf"),
    @XmlEnumValue("love_short")
    LOVE_SHORT("love_short"),
    @XmlEnumValue("magician_book")
    MAGICIAN_BOOK("magician_book"),
    @XmlEnumValue("management")
    MANAGEMENT("management"),
    @XmlEnumValue("marketing")
    MARKETING("marketing"),
    @XmlEnumValue("military_special")
    MILITARY_SPECIAL("military_special"),
    @XmlEnumValue("music_dancing")
    MUSIC_DANCING("music_dancing"),
    @XmlEnumValue("narrative")
    NARRATIVE("narrative"),
    @XmlEnumValue("newspapers")
    NEWSPAPERS("newspapers"),
    @XmlEnumValue("nonf_biography")
    NONF_BIOGRAPHY("nonf_biography"),
    @XmlEnumValue("nonf_criticism")
    NONF_CRITICISM("nonf_criticism"),
    @XmlEnumValue("nonf_publicism")
    NONF_PUBLICISM("nonf_publicism"),
    @XmlEnumValue("nonfiction")
    NONFICTION("nonfiction"),
    @XmlEnumValue("org_behavior")
    ORG_BEHAVIOR("org_behavior"),
    @XmlEnumValue("paper_work")
    PAPER_WORK("paper_work"),
    @XmlEnumValue("pedagogy_book")
    PEDAGOGY_BOOK("pedagogy_book"),
    @XmlEnumValue("periodic")
    PERIODIC("periodic"),
    @XmlEnumValue("personal_finance")
    PERSONAL_FINANCE("personal_finance"),
    @XmlEnumValue("poetry")
    POETRY("poetry"),
    @XmlEnumValue("popadanec")
    POPADANEC("popadanec"),
    @XmlEnumValue("popular_business")
    POPULAR_BUSINESS("popular_business"),
    @XmlEnumValue("prose_classic")
    PROSE_CLASSIC("prose_classic"),
    @XmlEnumValue("prose_counter")
    PROSE_COUNTER("prose_counter"),
    @XmlEnumValue("prose_history")
    PROSE_HISTORY("prose_history"),
    @XmlEnumValue("prose_military")
    PROSE_MILITARY("prose_military"),
    @XmlEnumValue("prose_rus_classic")
    PROSE_RUS_CLASSIC("prose_rus_classic"),
    @XmlEnumValue("prose_su_classics")
    PROSE_SU_CLASSICS("prose_su_classics"),
    @XmlEnumValue("psy_alassic")
    PSY_ALASSIC("psy_alassic"),
    @XmlEnumValue("psy_childs")
    PSY_CHILDS("psy_childs"),
    @XmlEnumValue("psy_generic")
    PSY_GENERIC("psy_generic"),
    @XmlEnumValue("psy_personal")
    PSY_PERSONAL("psy_personal"),
    @XmlEnumValue("psy_sex_and_family")
    PSY_SEX_AND_FAMILY("psy_sex_and_family"),
    @XmlEnumValue("psy_social")
    PSY_SOCIAL("psy_social"),
    @XmlEnumValue("psy_theraphy")
    PSY_THERAPHY("psy_theraphy"),
    @XmlEnumValue("real_estate")
    REAL_ESTATE("real_estate"),
    @XmlEnumValue("ref_dict")
    REF_DICT("ref_dict"),
    @XmlEnumValue("ref_encyc")
    REF_ENCYC("ref_encyc"),
    @XmlEnumValue("ref_guide")
    REF_GUIDE("ref_guide"),
    @XmlEnumValue("ref_ref")
    REF_REF("ref_ref"),
    @XmlEnumValue("reference")
    REFERENCE("reference"),
    @XmlEnumValue("religion")
    RELIGION("religion"),
    @XmlEnumValue("religion_esoterics")
    RELIGION_ESOTERICS("religion_esoterics"),
    @XmlEnumValue("religion_rel")
    RELIGION_REL("religion_rel"),
    @XmlEnumValue("religion_self")
    RELIGION_SELF("religion_self"),
    @XmlEnumValue("russian_contemporary")
    RUSSIAN_CONTEMPORARY("russian_contemporary"),
    @XmlEnumValue("russian_fantasy")
    RUSSIAN_FANTASY("russian_fantasy"),
    @XmlEnumValue("sci_biology")
    SCI_BIOLOGY("sci_biology"),
    @XmlEnumValue("sci_chem")
    SCI_CHEM("sci_chem"),
    @XmlEnumValue("sci_culture")
    SCI_CULTURE("sci_culture"),
    @XmlEnumValue("sci_history")
    SCI_HISTORY("sci_history"),
    @XmlEnumValue("sci_juris")
    SCI_JURIS("sci_juris"),
    @XmlEnumValue("sci_linguistic")
    SCI_LINGUISTIC("sci_linguistic"),
    @XmlEnumValue("sci_math")
    SCI_MATH("sci_math"),
    @XmlEnumValue("sci_medicine")
    SCI_MEDICINE("sci_medicine"),
    @XmlEnumValue("sci_philosophy")
    SCI_PHILOSOPHY("sci_philosophy"),
    @XmlEnumValue("sci_phys")
    SCI_PHYS("sci_phys"),
    @XmlEnumValue("sci_politics")
    SCI_POLITICS("sci_politics"),
    @XmlEnumValue("sci_religion")
    SCI_RELIGION("sci_religion"),
    @XmlEnumValue("sci_tech")
    SCI_TECH("sci_tech"),
    @XmlEnumValue("science")
    SCIENCE("science"),
    @XmlEnumValue("sf")
    SF("sf"),
    @XmlEnumValue("sf_action")
    SF_ACTION("sf_action"),
    @XmlEnumValue("sf_cyberpunk")
    SF_CYBERPUNK("sf_cyberpunk"),
    @XmlEnumValue("sf_detective")
    SF_DETECTIVE("sf_detective"),
    @XmlEnumValue("sf_fantasy")
    SF_FANTASY("sf_fantasy"),
    @XmlEnumValue("sf_heroic")
    SF_HEROIC("sf_heroic"),
    @XmlEnumValue("sf_history")
    SF_HISTORY("sf_history"),
    @XmlEnumValue("sf_horror")
    SF_HORROR("sf_horror"),
    @XmlEnumValue("sf_humor")
    SF_HUMOR("sf_humor"),
    @XmlEnumValue("sf_social")
    SF_SOCIAL("sf_social"),
    @XmlEnumValue("sf_space")
    SF_SPACE("sf_space"),
    @XmlEnumValue("short_story")
    SHORT_STORY("short_story"),
    @XmlEnumValue("sketch")
    SKETCH("sketch"),
    @XmlEnumValue("small_business")
    SMALL_BUSINESS("small_business"),
    @XmlEnumValue("sociology_book")
    SOCIOLOGY_BOOK("sociology_book"),
    @XmlEnumValue("stock")
    STOCK("stock"),
    @XmlEnumValue("thriller")
    THRILLER("thriller"),
    @XmlEnumValue("upbringing_book")
    UPBRINGING_BOOK("upbringing_book"),
    @XmlEnumValue("vampire_book")
    VAMPIRE_BOOK("vampire_book"),
    @XmlEnumValue("visual_arts")
    VISUAL_ARTS("visual_arts"),
    @XmlEnumValue("unrecognised")
    UNRECOGNISED("unrecognised");
    private final String value;

    GenreType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GenreType fromValue(String v) {
        for (GenreType c : GenreType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
