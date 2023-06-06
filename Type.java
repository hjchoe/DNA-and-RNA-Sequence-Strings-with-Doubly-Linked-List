package Labs.Lab3;

/** Enumerated Type for sequence type */
public enum Type {
    /** possible enum types of Type (with a char array value of possible bases for each type) */
    DNA(new char[] { 'A', 'C', 'G', 'T' }), RNA(new char[] { 'A', 'C', 'G', 'U' }), EMPTY(null);

    /** init for value of Type */
    private char[] valid;

    /** Constructor */
    Type(char[] valid) {
        this.valid = valid;
    }

    /** checks whether the given string sequence contains only valid bases based on which type it is */
    public Boolean checkValidity(String raw) {
        char[] rawchar = raw.toCharArray();
        for (int i = 0; i < rawchar.length; i++) {
            Boolean temp = false;
            for (int j = 0; j < 4; j++) {
                if (this.valid[j] == rawchar[i]) {
                    temp = true;
                    break;
                }
            }
            if (!temp)
                return false;
        }
        return true;
    }

    /** return type based on a String input */
    public static Type translate(String s) {
        if (s.equals("DNA"))
            return DNA;
        else if (s.equals("RNA"))
            return RNA;
        else {
            return null;
        }
    }
}