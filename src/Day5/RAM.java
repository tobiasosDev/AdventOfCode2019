package Day5;

public class RAM {
    public int[] memory;

    public RAM (int[] init) {
        memory = init;
    }

    public int getMemoryLoc(int loc) {
        return memory[loc];
    }

    public void setMemoryLoc(int loc, int value) {
        memory[loc] = value;
    }
}
