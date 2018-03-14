package com.bc.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bc.service.IBlockChianService;
import com.bc.vo.Block;
import com.bc.vo.JsonResult;
import com.bc.vo.Message;

@RestController
@RequestMapping("/bc")
public class BlockChianControl {

	@Autowired
	private IBlockChianService blockChianService;

	private Logger LOGGER = Logger.getLogger(BlockChianControl.class);

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> init() {
		JsonResult r = new JsonResult();
		Block genesisBlock = new Block();
		genesisBlock.setIndex(0);
		genesisBlock.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		genesisBlock.setVac(0);
		genesisBlock.setPrevHash("");
		genesisBlock.setHash(blockChianService.calculateHash(genesisBlock));
		blockChianService.add(genesisBlock);
		r.setResult(genesisBlock);
		r.setStatus("ok");
		return ResponseEntity.ok(r);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add(@RequestBody Message m) {
		JsonResult r = new JsonResult();
		if (m == null) {
			r.setResult("vac is NULL");
			r.setStatus("error");
			return ResponseEntity.ok(r);
		}
		int vac = m.getVac();
		Block lastBlock = blockChianService.getLastBlockChian();
		Block newBlock = blockChianService.generateBlock(lastBlock, vac);
		if (blockChianService.isBlockValid(newBlock, lastBlock)) {
			blockChianService.add(newBlock);
			ArrayList<Block> blockChain = blockChianService.getBlockChain();
			LOGGER.debug(JSONObject.toJSONString(blockChain));
			r.setResult(blockChain);
			r.setStatus("ok");
		} else {
			r.setResult("HTTP 500: Invalid Block Error");
			r.setStatus("error");
		}
		return ResponseEntity.ok(r);
	}

}
