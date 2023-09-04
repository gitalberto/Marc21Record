import java.util.ArrayList;
import java.util.List;

public class Marc21Record {
    public static class Marc21Field {
        private String tag;
        private char[] indicators;
        private List<Marc21Subfield> subfields;

        public Marc21Field(String tag, char[] indicators) {
            this.tag = tag;
            this.indicators = indicators;
            this.subfields = new ArrayList<>();
        }

        public String getTag() {
            return tag;
        }

        public char[] getIndicators() {
            return indicators;
        }

        public List<Marc21Subfield> getSubfields() {
            return subfields;
        }

        public void addSubfield(char code, String value) {
            Marc21Subfield subfield = new Marc21Subfield(code, value);
            subfields.add(subfield);
        }
    }

    public static class Marc21Subfield {
        private char code;
        private String value;

        public Marc21Subfield(char code, String value) {
            this.code = code;
            this.value = value;
        }

        public char getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String newValue) {
            value = newValue;
        }
    }

    private List<Marc21Field> fields;

    public Marc21Record() {
        fields = new ArrayList<>();
    }

    public List<Marc21Field> getFields() {
        return fields;
    }

    public void addField(String tag, char[] indicators) {
        Marc21Field field = new Marc21Field(tag, indicators);
        fields.add(field);
    }

    public void removeFieldByTag(String tag) {
        fields.removeIf(field -> field.getTag().equals(tag));
    }

    public Marc21Field getFieldByTag(String tag) {
        for (Marc21Field field : fields) {
            if (field.getTag().equals(tag)) {
                return field;
            }
        }
        return null;
    }

    public boolean fieldExists(String tag) {
        return getFieldByTag(tag) != null;
    }

    public int getFieldCount() {
        return fields.size();
    }

    public List<Marc21Subfield> getSubfieldsByTag(String tag) {
        Marc21Field field = getFieldByTag(tag);
        if (field != null) {
            return field.getSubfields();
        }
        return new ArrayList<>();
    }

    public void addField(Marc21Field field) {
        fields.add(field);
    }

    public void clearFields() {
        fields.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Marc21Field field : fields) {
            sb.append("Field Tag: ").append(field.getTag()).append("\n");
            sb.append("Indicators: ").append(field.getIndicators()).append("\n");
            for (Marc21Subfield subfield : field.getSubfields()) {
                sb.append("Subfield Code: ").append(subfield.getCode()).append("\n");
                sb.append("Subfield Value: ").append(subfield.getValue()).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return fields.isEmpty();
    }

    public void replaceSubfieldValue(String fieldTag, char subfieldCode, String newValue) {
        Marc21Field field = getFieldByTag(fieldTag);
        if (field != null) {
            for (Marc21Subfield subfield : field.getSubfields()) {
                if (subfield.getCode() == subfieldCode) {
                    subfield.setValue(newValue);
                }
            }
        }
    }

    public boolean fieldContainsSubfieldWithValue(String fieldTag, char subfieldCode, String value) {
        Marc21Field field = getFieldByTag(fieldTag);
        if (field != null) {
            for (Marc21Subfield subfield : field.getSubfields()) {
                if (subfield.getCode() == subfieldCode && subfield.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Marc21Subfield getSubfield(String fieldTag, char subfieldCode) {
        Marc21Field field = getFieldByTag(fieldTag);
        if (field != null) {
            for (Marc21Subfield subfield : field.getSubfields()) {
                if (subfield.getCode() == subfieldCode) {
                    return subfield;
                }
            }
        }
        return null;
    }

    public void addEmptyField(String tag, char[] indicators) {
        Marc21Field field = new Marc21Field(tag, indicators);
        fields.add(field);
    }

    public void copyFieldToNewIndicators(String sourceTag, char[] newIndicators) {
        Marc21Field sourceField = getFieldByTag(sourceTag);
        if (sourceField != null) {
            Marc21Field newField = new Marc21Field(sourceTag, newIndicators);
            newField.getSubfields().addAll(sourceField.getSubfields());
            fields.add(newField);
        }
    }

    public List<String> getAllFieldTags() {
        List<String> tags = new ArrayList<>();
        for (Marc21Field field : fields) {
            tags.add(field.getTag());
        }
        return tags;
    }

    public boolean hasFieldWithIndicators(String tag, char[] indicators) {
        for (Marc21Field field : fields) {
            if (field.getTag().equals(tag) &&
                field.getIndicators()[0] == indicators[0] &&
                field.getIndicators()[1] == indicators[1]) {
                return true;
            }
        }
        return false;
    }

    public List<Marc21Subfield> getAllSubfieldsOfField(String fieldTag) {
        Marc21Field field = getFieldByTag(fieldTag);
        if (field != null) {
            return field.getSubfields();
        }
        return new ArrayList<>();
    }

    public void addSubfieldToField(String fieldTag, char subfieldCode, String value) {
        Marc21Field field = getFieldByTag(fieldTag);
        if (field != null) {
            field.addSubfield(subfieldCode, value);
        }
    }
}
