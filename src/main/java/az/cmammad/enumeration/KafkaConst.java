package az.cmammad.enumeration;

public enum KafkaConst {
    TOPIC_SUPERHERO("TOPIC-SUPERHERO");

    private final String field;

    private KafkaConst(String field) {
        this.field = field;
    }
}
