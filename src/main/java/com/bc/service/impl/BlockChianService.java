package com.bc.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.bc.service.IBlockChianService;
import com.bc.vo.Block;
import com.twmacinta.util.MD5;

@Service
public class BlockChianService implements IBlockChianService {

	ArrayList<Block> blockChain = new ArrayList<Block>();

	public String calculateHash(Block block) {
		String record = (block.getIndex()) + block.getTimestamp() + (block.getVac()) + block.getPrevHash();
		// return SHA256.crypt(record);
		return MD5.asHex(record.getBytes());

	}

	public Block generateBlock(Block oldBlock, int vac) {
		Block newBlock = new Block();
		newBlock.setIndex(oldBlock.getIndex() + 1);
		newBlock.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		newBlock.setVac(vac);
		newBlock.setPrevHash(oldBlock.getHash());
		newBlock.setHash(calculateHash(newBlock));
		return newBlock;

	}

	public boolean isBlockValid(Block newBlock, Block oldBlock) {
		if (oldBlock.getIndex() + 1 != newBlock.getIndex()) {
			return false;
		}
		if (!oldBlock.getHash().equals(newBlock.getPrevHash())) {
			return false;
		}
		if (!calculateHash(newBlock).equals(newBlock.getHash())) {
			return false;
		}
		return true;
	}

	public void replaceChain(ArrayList<Block> newBlocks) {
		if (newBlocks.size() > blockChain.size()) {
			blockChain = newBlocks;
		}
	}

	@Override
	public void add(Block newBlock) {
		blockChain.add(newBlock);
	}

	@Override
	public Block getLastBlockChian() {
		return blockChain.get(blockChain.size() - 1);
	}

	@Override
	public ArrayList<Block> getBlockChain() {
		return blockChain;
	}
}