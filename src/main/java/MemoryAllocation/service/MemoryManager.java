package MemoryAllocation.service;

import MemoryAllocation.entity.Block;

public class MemoryManager {
    public static final int MAX_BLOCKS = 10;
    public static int initializeBlocks(Block[] blocks) {
        int numBlocks = 0;
        blocks[numBlocks++] = new Block('Z', 3, true);
        blocks[numBlocks++] = new Block('A', 1, true);
        blocks[numBlocks++] = new Block('V', 3, true);
        blocks[numBlocks++] = new Block('L', 1, false);
        blocks[numBlocks++] = new Block('X', 2, true);
        blocks[numBlocks++] = new Block('Y', 5, true);
        blocks[numBlocks++] = new Block('L', 3, false);
        return numBlocks;
    }
    public static void printMemory(Block[] blocks, int numBlocks) {
        System.out.println("Current Memory:");
        for (int i = 0; i < numBlocks; i++) {
            if (blocks[i].isAllocated) {
                System.out.print(blocks[i].id + "-" + blocks[i].size + "k ");
            } else {
                System.out.print("L-" + blocks[i].size + "k ");
            }
        }
        System.out.println();
    }

    public static void deallocateBlock(Block[] blocks, int numBlocks, char id) {
        for (int i = 0; i < numBlocks; i++) {
            if (blocks[i].id == id && blocks[i].isAllocated) {
                blocks[i].isAllocated = false;
                System.out.println("Deallocated: " + id + "-" + blocks[i].size + "k");
                return;
            }
        }
        System.out.println("Block " + id + " not found or already deallocated.");
    }

    public static void combineFreeBlocks(Block[] blocks, int numBlocks) {
        for (int i = 0; i < numBlocks - 1; i++) {
            if (!blocks[i].isAllocated && !blocks[i+1].isAllocated) {
                blocks[i].size += blocks[i+1].size;
                for (int j = i+1; j < numBlocks - 1; j++) {
                    blocks[j] = blocks[j+1];
                }
                numBlocks--;
                i--;
            }
        }
    }

    public static void allocateBlock(Block[] blocks, int numBlocks, char id, int size) {
        combineFreeBlocks(blocks, numBlocks);
        for (int i = 0; i < numBlocks; i++) {
            if (!blocks[i].isAllocated && blocks[i].size >= size) {
                if (blocks[i].size > size) {
                    for (int j = numBlocks - 1; j > i + 1; j--) {
                        blocks[j] = blocks[j - 1];
                    }
                    blocks[i + 1] = new Block('L', blocks[i].size - size, false);
                    blocks[i].size = size;
                    numBlocks++;
                }
                blocks[i].isAllocated = true;
                blocks[i].id = id;
                System.out.println("Allocated: " + id + "-" + size + "k in block " + i);
                return;
            }
        }
        System.out.println("Not enough space to allocate " + id + "-" + size + "k.");
    }


}
