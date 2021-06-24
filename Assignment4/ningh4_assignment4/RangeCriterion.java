class RangeCriterion {
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    void addCriterion(Tag tag) {
        // If the relation is >, the minValue equals to the maximum of itself and the long type of the value of tag
        // If the relation is <, the maxValue equals to the minimum of itself and the long type of the value of tag
        if (tag.getRelation() == Tag.Relation.LARGER) {
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
    }

    // Value is suppose to be in range
    // If value is smaller than maxValue and greater than minValue, returns true, else false
    boolean isInRange(long value) {
        if (value < maxValue && value > minValue)
            return true;
        return false;
    }
}
