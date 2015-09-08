package com.hxgsn.demo;

import org.junit.Test;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuAuth {

	/**
	 * 生成上传token
	 *
	 * @param bucket
	 *            空间名
	 * @param key
	 *            key，可为 null
	 * @param expires
	 *            有效时长，单位秒。默认3600s
	 * @param policy
	 *            上传策略的其它参数，如 new StringMap().put("endUser",
	 *            "uid").putNotEmpty("returnBody", "")。 scope通过
	 *            bucket、key间接设置，deadline 通过 expires 间接设置
	 * @param strict
	 *            是否去除非限定的策略字段，默认true
	 * @return 生成的上传token
	 */

	Auth auth = Auth.create("bFLCOgDjTKo7xLRmtgaqktyKTPoaNpTBSRp2TU06", "JCH35PLpBaFc9W6ZeVb-PINEw-_hwBUZjvlT9ujq");

	// 简单上传，使用默认策略
	private String getUpToken0() {
		return auth.uploadToken("bucket");
	}

	// 覆盖上传
	private String getUpToken1() {
		return auth.uploadToken("bucket", "key");
	}

	// 设置指定上传策略
	private String getUpToken2() {
		return auth.uploadToken("bucket", null, 3600, new StringMap().put("callbackUrl", "call back url")
				.putNotEmpty("callbackHost", "").put("callbackBody", "key=$(key)&hash=$(etag)"));
	}

	// 设置预处理、去除非限定的策略字段
	private String getUpToken3() {
		return auth.uploadToken("bucket", null, 3600, new StringMap().putNotEmpty("persistentOps", "")
				.putNotEmpty("persistentNotifyUrl", "").putNotEmpty("persistentPipeline", ""), true);
	}
	
	UploadManager uploadManager = new UploadManager();


	public MyRet upload() {
		MyRet ret = null;
	    try {
	        Response res = uploadManager.put("/Users/zgc/0.gif", null, getUpToken());
	        ret = res.jsonToObject(MyRet.class);
	        //log.info(res.toString());
	        //log.info(res.bodyString());
	    } catch (QiniuException e) {
	        Response r = e.response;
	    }
		return ret;
	}


	public String getUpToken(){
	    return auth.uploadToken("zhongda", null, 3600, null);
	}
	
	@Test
	public void show(){
		
		System.out.println("http://7xld6c.com1.z0.glb.clouddn.com/"+upload().key);
		
	}

}
