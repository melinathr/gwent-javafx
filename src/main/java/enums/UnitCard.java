package enums;

import java.util.List;

public enum UnitCard {
    // Skellige
    MARDROEME("Mardroeme", "Skellige", CardAbility.MARDROEME, 0, false, 3, List.of(CardPlacement.SPECIAL_SLOT, CardPlacement.SPELL)),
    BERSERKER("Berserker", "Skellige", CardAbility.BERSERKER, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VILDKAARL("Vildkaarl", "Skellige", CardAbility.MORALE_BOOST, 14, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    SVANRIGE("Svanrige", "Skellige", null, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    UDALRYK("Udalryk", "Skellige", null, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    DONAR_AN_HINDAR("Donar an Hindar", "Skellige", null, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    CLAN_AN_CRAITE("Clan An Craite", "Skellige", CardAbility.TIGHT_BOND, 6, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    BLUEBOY_LUGOS("Blueboy", "Skellige", null, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    MADMAN_LUGOS("Madman Lugos", "Skellige", null, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    CERYS("Cerys", "Skellige", CardAbility.MUSTER, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    KAMBI("Kambi", "Skellige", CardAbility.TRANSFORMER, 11, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    BIRNA_BRAN("Birna", "Skellige", CardAbility.MEDIC, 2, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    CLAN_DRUMMOND_SHIELDMAIDEN("Clan Drummond Shieldmaiden", "Skellige", CardAbility.TIGHT_BOND, 4, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    CLAN_TORDARROCH_ARMORSMITH("Clan Tordarroch Armorsmith", "Skellige", null, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", "Skellige", CardAbility.SCORCH, 6, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", "Skellige", null, 6, false, 3, List.of(CardPlacement.RANGED_COMBAT)),
    ERMION("Ermion", "Skellige", CardAbility.MARDROEME, 8, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    HJALMAR("Hjalmar", "Skellige", null, 10, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    YOUNG_BERSERKER("Young Berserker", "Skellige", CardAbility.BERSERKER, 2, false, 3, List.of(CardPlacement.RANGED_COMBAT)),
    YOUNG_VIDKAARL("Young Vidkaarl", "Skellige", CardAbility.TIGHT_BOND, 8, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    LIGHT_LONGSHIP("Light Longship", "Skellige", CardAbility.MUSTER, 4, false, 3, List.of(CardPlacement.RANGED_COMBAT)),
    HOLGER_BLACKHAND("Holger", "Skellige", null, 4, false, 1, List.of(CardPlacement.SIEGE)),
    WAR_LONGSHIP("War Longship", "Skellige", CardAbility.TIGHT_BOND, 6, false, 3, List.of(CardPlacement.SIEGE)),
    DRAIG_BON_DHU("Draig", "Skellige", CardAbility.COMMANDERS_HORN, 2, false, 1, List.of(CardPlacement.SIEGE)),
    OLAF("Olaf", "Skellige", CardAbility.MORALE_BOOST, 12, false, 1, List.of(CardPlacement.AGILE)),

    // Scoia'tael Cards

    ELVEN_SKIRMISHER("Elven Skirmisher", "Scoia'tael", CardAbility.MUSTER, 2, false, 3, List.of(CardPlacement.RANGED_COMBAT)),
    IORVETH("Iorveth", "Scoia'tael", null, 10, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    YAEVINN("Yaevinn", "Scoia'tael", null, 6, false, 1, List.of(CardPlacement.AGILE)),
    CIARAN_AEP("Ciaran", "Scoia'tael", null, 3, false, 1, List.of(CardPlacement.AGILE)),
    DENNIS_CRANMER("Dennis Cranmer", "Scoia'tael", null, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", "Scoia'tael", null, 6, false, 3, List.of(CardPlacement.AGILE)),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", "Scoia'tael", null, 4, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", "Scoia'tael", CardAbility.MUSTER, 3, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    FILAVANDREL("Filavandrel", "Scoia'tael", null, 6, false, 1, List.of(CardPlacement.AGILE)),
    HAVEKAR_HEALER("Havekar Healer", "Scoia'tael", CardAbility.MEDIC, 0, false, 3, List.of(CardPlacement.RANGED_COMBAT)),
    HAVEKAR_SMUGGLER("Havekar Smuggler", "Scoia'tael", CardAbility.MUSTER, 5, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    IDA_EMEAN_AEP("Ida Emean aep", "Scoia'tael", null, 6, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    RIORDAIN("Riordain", "Scoia'tael", null, 1, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    TORUVIEL("Toruviel", "Scoia'tael", null, 2, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", "Scoia'tael", null, 4, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    MAHAKAMAN_DEFENDER("Mahakaman Defender", "Scoia'tael", null, 5, false, 5, List.of(CardPlacement.CLOSE_COMBAT)),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran", "Scoia'tael", null, 5, false, 2, List.of(CardPlacement.AGILE)),
    MILVA("Milva", "Scoia'tael", CardAbility.MORALE_BOOST, 10, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    SEASENTHESSIS("Seasenthessis", "Scoia'tael", null, 10, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    SCHIRRU("Schirru", "Scoia'tael", CardAbility.SCORCH, 8, false, 1, List.of(CardPlacement.SIEGE)),
    BARCLAY_ELS("Barclay Els", "Scoia'tael", null, 6, false, 1, List.of(CardPlacement.AGILE)),
    EITHNE("Eithne", "Scoia'tael", null, 10, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    ISENGRIM_FAOILTIARNA("Isengrim Faoiltiarna", "Scoia'tael", CardAbility.MORALE_BOOST, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),

    // NorthernRealms Cards

    BALLISTA("Ballista", "NorthernRealms", null, 6, false, 2, List.of(CardPlacement.SIEGE)),
    BLUE_STRIPES_COMMANDO("Blue Stripes Commando", "NorthernRealms", CardAbility.TIGHT_BOND, 4, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    CATAPULT("Catapult", "NorthernRealms", CardAbility.TIGHT_BOND, 8, false, 2, List.of(CardPlacement.SIEGE)),
    DRAGON_HUNTER("Dragon Hunter", "NorthernRealms", CardAbility.TIGHT_BOND, 5, false, 3, List.of(CardPlacement.RANGED_COMBAT)),
    DETHMOLD("Dethmold", "NorthernRealms", null, 6, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    DUN_BANNER_MEDIC("Dun Banner Medic", "NorthernRealms", CardAbility.MEDIC, 5, false, 1, List.of(CardPlacement.SIEGE)),
    ESTERAD_THYSSEN("Esterad Thyssen", "NorthernRealms", null, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    JOHN_NATALIS("John Natalis", "NorthernRealms", null, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", "NorthernRealms", CardAbility.MORALE_BOOST, 1, false, 3, List.of(CardPlacement.SIEGE)),
    KEIRA_METZ("Keira Metz", "NorthernRealms", null, 5, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    PHILIPPA_EILHART("Philippa Eilhart", "NorthernRealms", null, 10, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    POOR_FUCKING_INFANTRY("Poor Fucking Infantry", "NorthernRealms", CardAbility.TIGHT_BOND, 1, false, 4, List.of(CardPlacement.CLOSE_COMBAT)),
    PRINCE_STENNIS("Prince Stennis", "NorthernRealms", CardAbility.SPY, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", "NorthernRealms", null, 1, false, 2, List.of(CardPlacement.CLOSE_COMBAT)),
    SABRINA_GLEVISSING("Sabrina Glevissing", "NorthernRealms", null, 4, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    SHELDON_SKAGGS("Sheldon Skaggs", "NorthernRealms", null, 4, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    SIEGE_TOWER("Siege Tower", "NorthernRealms", null, 6, false, 1, List.of(CardPlacement.SIEGE)),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", "NorthernRealms", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", "NorthernRealms", CardAbility.SPY, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    SILE_DE_TANSARVILLE("Sile de Tansarville", "NorthernRealms", null, 5, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    THALER("Thaler", "NorthernRealms", CardAbility.SPY, 1, false, 1, List.of(CardPlacement.SIEGE)),
    TREBUCHET("Trebuchet", "NorthernRealms", null, 6, false, 2, List.of(CardPlacement.SIEGE)),
    VERNON_ROCHE("Vernon Roche", "NorthernRealms", null, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VES("Ves", "NorthernRealms", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    YARPEN_ZIRGRIN("Yarpen Zirgrin", "NorthernRealms", null, 2, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),

    // Nilfgaard Cards
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", "Nilfgaard", CardAbility.TIGHT_BOND, 3, false, 4, List.of(CardPlacement.CLOSE_COMBAT)),
    STEFAN_SKELLEN("Stefan Skellen", "Nilfgaard", CardAbility.SPY, 9, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    SHILARD_FITZ_OESTERLEN("Shilard Fitz Oesterlen", "Nilfgaard", CardAbility.SPY, 7, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    YOUNG_EMISSARY("Young Emissary", "Nilfgaard", CardAbility.TIGHT_BOND, 5, false, 2, List.of(CardPlacement.CLOSE_COMBAT)),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", "Nilfgaard", null, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VATTIER_DE_RIDEAUX_1("Vattier de Rideaux", "Nilfgaard", CardAbility.SPY, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    MENNO_COEHORN_1("Menno Coehorn", "Nilfgaard", CardAbility.MEDIC, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    PUTTKAMMER("Puttkammer", "Nilfgaard", null, 3, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    ASSIRE_VAR_ANAHID("Assire var Anahid", "Nilfgaard", null, 6, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", "Nilfgaard", null, 10, false, 2, List.of(CardPlacement.RANGED_COMBAT)),
    TIBOR_EGGEBRACHT("Tibor Eggebracht", "Nilfgaard", null, 10, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", "Nilfgaard", null, 5, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    FRINGILLA_VIGO("Fringilla Vigo", "Nilfgaard", null, 6, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    ROTTEN_MANGONEL("Rotten Mangonel", "Nilfgaard", null, 3, false, 1, List.of(CardPlacement.SIEGE)),
    HEAVY_ZERRIKANIAN_FIRE_SCORPION("Heavy Zerrikanian Fire Scorpion", "Nilfgaard", null, 10, false, 1, List.of(CardPlacement.SIEGE)),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", "Nilfgaard", null, 5, false, 1, List.of(CardPlacement.SIEGE)),
    SIEGE_ENGINEER("Siege Engineer", "Nilfgaard", null, 6, false, 1, List.of(CardPlacement.SIEGE)),
    MORVRAN_VOORHIS("Morvran Voorhis", "Nilfgaard", null, 10, true, 1, List.of(CardPlacement.SIEGE)),
    ALBRICH("Albrich", "Nilfgaard", null, 2, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    CYNTHIA("Cynthia", "Nilfgaard", null, 4, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archers", "Nilfgaard", CardAbility.MEDIC, 1, false, 2, List.of(CardPlacement.RANGED_COMBAT)),
    LETHO_OF_GULET("Letho of Gulet", "Nilfgaard", null, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    MENNO_COEHORN_2("Menno Coehorn", "Nilfgaard", CardAbility.MEDIC, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    MORTEISEN("Morteisen", "Nilfgaard", null, 3, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    MORVRAN_VOORHIS_2("Morvran Voorhis", "Nilfgaard", null, 10, true, 1, List.of(CardPlacement.SIEGE)),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", "Nilfgaard", CardAbility.TIGHT_BOND, 2, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    RAINFARN("Rainfarn", "Nilfgaard", null, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    SIEGE_TECHNICIAN("Siege Technician", "Nilfgaard", CardAbility.MEDIC, 0, false, 1, List.of(CardPlacement.SIEGE)),
    SWEERS("Sweers", "Nilfgaard", null, 2, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    VANHEMAR("Vanhemar", "Nilfgaard", null, 4, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    VATTIER_DE_RIDEAUX_2("Vattier de Rideaux", "Nilfgaard", CardAbility.SPY, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VREEMDE("Vreemde", "Nilfgaard", null, 2, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),

    // Monster Cards
    DRAUG("Draug", "Monster", null, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    IMLERITH("Imlerith", "Monster", null, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    LESHEN("Leshen", "Monster", null, 10, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    KAYRAN("Kayran", "Monster", CardAbility.MORALE_BOOST, 8, true, 1, List.of(CardPlacement.AGILE)),
    TOAD("Toad", "Monster", CardAbility.SCORCH, 7, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    ARACHAS_BEHEMOTH("Arachas Behemoth", "Monster", CardAbility.MUSTER, 6, false, 1, List.of(CardPlacement.SIEGE)),
    CRONE_BREWESS("Crone Brewess", "Monster", CardAbility.MUSTER, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    CRONE_WEAVESS("Crone Weavess", "Monster", CardAbility.MUSTER, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    CRONE_WHISPESS("Crone Whispess", "Monster", CardAbility.MUSTER, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    EARTH_ELEMENTAL("Earth Elemental", "Monster", null, 6, false, 1, List.of(CardPlacement.SIEGE)),
    FIEND("Fiend", "Monster", null, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    FIRE_ELEMENTAL("Fire Elemental", "Monster", null, 6, false, 1, List.of(CardPlacement.SIEGE)),
    FORKTAIL("Forktail", "Monster", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    FRIGHTENER("Frightener", "Monster", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    GRAVE_HAG("Grave Hag", "Monster", null, 5, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    GRIFFIN("Gryffin", "Monster", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    ICE_GIANT("Ice Giant", "Monster", null, 5, false, 1, List.of(CardPlacement.SIEGE)),
    PLAGUE_MAIDEN("Plague Maiden", "Monster", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VAMPIRE_KATAKAN("Vampire Katakan", "Monster", CardAbility.MUSTER, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    WEREWOLF("Werewolf", "Monster", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    ARACHAS("Arachas", "Monster", CardAbility.MUSTER, 4, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    BOTCHLING("Botchling", "Monster", null, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VAMPIRE_BRUXA("Vampire Bruxa", "Monster", CardAbility.MUSTER, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VAMPIRE_EKIMMARA("Vampire Ekimmara", "Monster", CardAbility.MUSTER, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VAMPIRE_FLEDER("Vampire Fleder", "Monster", CardAbility.MUSTER, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VAMPIRE_GARKAIN("Vampire Garkain", "Monster", CardAbility.MUSTER, 4, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    CELAENO_HARPY("Celaeno Harpy", "Monster", null, 2, false, 1, List.of(CardPlacement.AGILE)),
    COCKATRICE("Cockatrice", "Monster", null, 2, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    ENDREGA("Endrega", "Monster", null, 2, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    FOGLET("Foglet", "Monster", null, 2, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    GARGOYLE("Gargoyle", "Monster", null, 2, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    HARPY("Harpy", "Monster", null, 2, false, 1, List.of(CardPlacement.AGILE)),
    NEKKER("Nekker", "Monster", CardAbility.MUSTER, 2, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),
    WYVERN("Wyvern", "Monster", null, 2, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    GHOUL("Ghoul", "Monster", CardAbility.MUSTER, 1, false, 3, List.of(CardPlacement.CLOSE_COMBAT)),

    // Neutral Cards
    BITING_FROST("Biting Frost", "Neutral", null, 0, true, 3, List.of(CardPlacement.WEATHER)),
    IMPENETRABLE_FOG("Impenetrable fog", "Neutral", null, 0, true, 3, List.of(CardPlacement.WEATHER)),
    TORRENTIAL_RAIN("Torrential Rain", "Neutral", null, 0, true, 3, List.of(CardPlacement.WEATHER)),
    SKELLIGE_STORM("Skellige Storm", "Neutral", null, 0, true, 3, List.of(CardPlacement.WEATHER)),
    CLEAR_WEATHER("Clear Weather", "Neutral", null, 0, true, 3, List.of(CardPlacement.WEATHER)),
    SCORCH("Scorch", "Neutral", CardAbility.SCORCH, 0, true, 3, List.of(CardPlacement.SPELL)),
    COMMANDERS_HORN("Commanders horn", "Neutral", CardAbility.COMMANDERS_HORN, 0, true, 3, List.of(CardPlacement.SPELL)),
    DECOY("Decoy", "Neutral", CardAbility.DECOY, 0, true, 3, List.of(CardPlacement.SPELL)),
    DANDELION("Dandelion", "Neutral", CardAbility.COMMANDERS_HORN, 2, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    COW("Cow", "Neutral", CardAbility.TRANSFORMER, 0, false, 1, List.of(CardPlacement.RANGED_COMBAT)),
    EMIEL_REGIS("Emiel Regis", "Neutral", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    GAUNTER_ODIMM("Gaunter ODimm", "Neutral", CardAbility.MUSTER, 2, false, 1, List.of(CardPlacement.SIEGE)),
    GAUNTER_ODIMM_DARKNESS("Gaunter ODimm Darkness", "Neutral", CardAbility.MUSTER, 4, false, 3, List.of(CardPlacement.RANGED_COMBAT)),
    GERALT_OF_RIVIA("Geralt of Rivia", "Neutral", null, 15, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    MYSTERIOUS_ELF("Mysterious Elf", "Neutral", CardAbility.SPY, 0, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    OLGIERD_VON_EVEREC("Olgierd Von Everc", "Neutral", CardAbility.MORALE_BOOST, 6, false, 1, List.of(CardPlacement.AGILE)),
    TRISS_MERIGOLD("Triss Merigold", "Neutral", null, 7, true, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VESEMIR("Vesemir", "Neutral", null, 6, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    VILLENTRETENMERTH("Villentretenmerth", "Neutral", CardAbility.SCORCH, 7, false, 1, List.of(CardPlacement.CLOSE_COMBAT)),
    YENNEFER_OF_VENGERBERG("Yennefer of Vengerberg", "Neutral", CardAbility.MEDIC, 7, true, 1, List.of(CardPlacement.RANGED_COMBAT)),
    ZOLTAN_CHIVAY("Zoltan Chivay", "Neutral", null, 5, false, 1, List.of(CardPlacement.CLOSE_COMBAT));

    private final String name;
    private final String faction;
    private final CardAbility ability;
    private final int power;
    private final boolean isHero;
    private final int numberOfCardsInGame;
    private final List<CardPlacement> playingRows;

    UnitCard(String name, String faction, CardAbility ability, int power, boolean isHero, int numberOfCardsInGame, List<CardPlacement> playingRows) {
        this.name = name;
        this.faction = faction;
        this.ability = ability;
        this.power = power;
        this.isHero = isHero;
        this.numberOfCardsInGame = numberOfCardsInGame;
        this.playingRows = playingRows;
    }

    public String getName() {
        return name;
    }

    public CardAbility getAbility() {
        return ability;
    }

    public int getPower() {
        return power;
    }

    public boolean isHero() {
        return isHero;
    }

    public int getNumberOfCardsInGame() {
        return numberOfCardsInGame;
    }

    public List<CardPlacement> getPlayingRows() {
        return playingRows;
    }

    public String getImagePath() {
        return "/Image/" + this.name.toLowerCase() + ".jpg";
    }

    public String getFaction() {
        return faction;
    }

}