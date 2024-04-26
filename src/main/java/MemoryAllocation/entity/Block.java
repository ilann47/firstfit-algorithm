package MemoryAllocation.entity;

public class Block {
    public char id;
    public int size;
    public boolean isAllocated;

    public Block(char id, int size, boolean isAllocated) {
        this.id = id;
        this.size = size;
        this.isAllocated = isAllocated;
    }
}
