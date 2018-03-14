package com.bc.service;

import java.util.ArrayList;

import com.bc.vo.Block;

public interface IBlockChianService {
	
	String calculateHash(Block block);

	void add(Block newBlock);
	
	Block getLastBlockChian();
	
	Block generateBlock(Block oldBlock, int vac);
	
	boolean isBlockValid(Block newBlock, Block oldBlock);
	
	ArrayList<Block> getBlockChain();
}
