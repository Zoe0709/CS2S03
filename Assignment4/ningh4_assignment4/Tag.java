class Tag {
    public enum Relation {
        SMALLER, LARGER, EQUAL      //Only three kinds of relation
    }
    private Relation relation;
    private String name;
    private String value;

    Tag(String[] tokens) {
        name = tokens[0];

        //If the first character of token[1] is <, it goes to case '<'
        //If is >, it goes to case '>'
        //if is =, it goes to case '='
        //If the character is invalid, it goes to default
        switch (tokens[1].charAt(0)) {
            case '<':
                relation = Relation.SMALLER;
                break;
            case '>':
                relation = Relation.LARGER;
                break;
            case '=':
                relation = Relation.EQUAL;
                break;
            default:
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
        }
        value = tokens[2];
    }

    //Getter of relation
    public Relation getRelation() {
        return relation;
    }

    //Getter of name
    public String getName() {
        return name;
    }

    //Getter of value
    public String getValue() {
        return value;
    }
}
