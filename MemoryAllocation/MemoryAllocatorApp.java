package MemoryAllocation;


import MemoryAllocation.entity.Block;
import MemoryAllocation.service.MemoryManager;
public class MemoryAllocatorApp {

    public static void main(String[] args) {
        Block[] blocks = new Block[MemoryManager.MAX_BLOCKS];
        int numBlocks = MemoryManager.initializeBlocks(blocks);

        MemoryManager.printMemory(blocks, numBlocks);

        MemoryManager.deallocateBlock(blocks, numBlocks, 'V');
        MemoryManager.deallocateBlock(blocks, numBlocks, 'Z');
        MemoryManager.allocateBlock(blocks, numBlocks, 'K', 4);
        MemoryManager.allocateBlock(blocks, numBlocks, 'T', 2);
        MemoryManager.allocateBlock(blocks, numBlocks, 'W', 3);
        MemoryManager.deallocateBlock(blocks, numBlocks, 'Y');
        MemoryManager.allocateBlock(blocks, numBlocks, 'R', 3);

        MemoryManager.printMemory(blocks, numBlocks);
    }
}
