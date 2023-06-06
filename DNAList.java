package Labs.Lab3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/** Main class */
public class DNAList {
    static int arraysize;
    static String filepath;
    static Sequence[] sequences;

    /** Inserts a new sequence into the sequence array */
    static void insert(String[] command) {
        int pos = Integer.parseInt(command[1]);
        if (pos < 0 || pos >= arraysize) {
            System.out.println("Position is out of bounds");
            return;
        }
        Type t = Type.translate(command[2]);
        if (t == null) {
            System.out.println("Invalid type");
            return;
        }
        if (command.length == 4) {
            String rawSequence = command[3];
            if (!t.checkValidity(rawSequence)) {
                System.out.println("Error occurred while inserting");
                return;
            }
            LinkedList list = new LinkedList(rawSequence);
            sequences[pos] = new Sequence(t, list);
        } else {
            sequences[pos] = new Sequence(t, (LinkedList) null);
        }
    }

    /** Removes a sequence from the sequence array */
    static void remove(String[] command) {
        int pos = Integer.parseInt(command[1]);
        if (sequences[pos] == null || sequences[pos].getType() == Type.EMPTY) {
            System.out.println("No sequence to remove at specified position");
            return;
        } else {
            sequences[pos] = new Sequence(Type.EMPTY, (LinkedList) null);
        }
    }

    /** Prints all sequences in the sequence array */
    static void print(String[] command) {
        if (command.length == 1) {
            for (int i = 0; i < arraysize; i++) {
                Sequence s = sequences[i];
                if (s.getType() != Type.EMPTY) {
                    System.out.printf("%d\t%s\t%s%n", i, s.getType(), s.getList());
                }
            }
        } else {
            int pos = Integer.parseInt(command[1]);
            if (pos < 0 || pos >= arraysize) {
                System.out.println("Position is out of bounds");
                return;
            }
            Sequence s = sequences[pos];
            if (s == null || s.getType() == Type.EMPTY) {
                System.out.println("No sequence to print at specified position");
                return;
            } else {
                System.out.printf("%s\t%s%n", s.getType(), s.getList());
            }
        }
    }

    /** Clips a sequence from start param to end param in sequence array */
    static void clip(String[] command) {
        int pos = Integer.parseInt(command[1]);
        int start = Integer.parseInt(command[2]);
        int end = Integer.parseInt(command[3]);
        if (pos < 0 || pos >= arraysize) {
            System.out.println("Position is out of bounds");
            return;
        }
        if (sequences[pos] == null || sequences[pos].getType() == Type.EMPTY) {
            System.out.println("No sequence to clip at specified position");
            return;
        }
        if (start < 0) {
            System.out.println("Invalid start index");
            return;
        }
        if (start >= sequences[pos].getList().getSize()) {
            System.out.println("Start index is out of bounds");
            return;
        }
        if (end >= sequences[pos].getList().getSize()) {
            System.out.println("End index is out of bounds");
            return;
        }
        if (end <= start) {
            sequences[pos].setList(null);
            return;
        }
        sequences[pos].setList(sequences[pos].getList().clip(start, end));
    }

    /** Copies a sequence from one position in the sequence array to another position */
    static void copy(String[] command) {
        int pos1 = Integer.parseInt(command[1]);
        int pos2 = Integer.parseInt(command[2]);
        if (pos1 < 0 || pos1 >= arraysize || pos2 < 0 || pos2 >= arraysize) {
            System.out.println("Position is out of bounds");
            return;
        }
        if (sequences[pos1] == null || sequences[pos1].getType() == Type.EMPTY) {
            System.out.println("No sequence to copy at specified position");
            return;
        } else {
            sequences[pos2] = new Sequence(sequences[pos1].getType(), sequences[pos1].getList().copy());
        }
    }

    /** Transcribes a sequence from DNA to RNA */
    static void transcribe(String[] command) {
        int pos = Integer.parseInt(command[1]);
        if (pos < 0 || pos >= arraysize) {
            System.out.println("Position is out of bounds");
            return;
        }
        if (sequences[pos] == null || sequences[pos].getType() == Type.EMPTY) {
            System.out.println("No sequence to transcribe at specified position");
            return;
        }
        if (sequences[pos].getType() == Type.RNA) {
            System.out.println("Cannot transcribe RNA");
            return;
        }
        sequences[pos] = new Sequence(Type.RNA, sequences[pos].getList().transcribe());
    }

    /** Main method */
    public static void main(String[] args) throws FileNotFoundException {
        arraysize = Integer.parseInt(args[0]);
        filepath = args[1];
        sequences = new Sequence[arraysize];

        for (int i = 0; i < arraysize; i++) {
            sequences[i] = new Sequence(Type.EMPTY, (LinkedList) null);
        }

        Scanner s = new Scanner(new FileReader(filepath));

        while (s.hasNextLine()) {
            String[] command = s.nextLine().split(" ");
            switch (command[0]) {
                case "insert":
                    insert(command);
                    break;
                case "remove":
                    remove(command);
                    break;
                case "print":
                    print(command);
                    break;
                case "clip":
                    clip(command);
                    break;
                case "copy":
                    copy(command);
                    break;
                case "transcribe":
                    transcribe(command);
                    break;
                default:
                    break;
            }
        }
    }
}