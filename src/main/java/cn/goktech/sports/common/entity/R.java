package cn.goktech.sports.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面响应entity
 * @author zcl<yczclcn@163.com>
 */
public class R extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 0);
	}
	
	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}


	public static R errorF(String msg) {
		R r = error(500, "no");
		r.put("msg", "no");
		r.put("code", msg);
		r.put("wng", msg);
		return r;
	}

	public static R okF(Map<String, Object> map) {
		R r = new R();
		r.put("msg", "ok");
		r.put("data", map);
		return r;
	}

	public static R okF(String msg) {
		R r = new R();
		r.put("msg", "ok");
		r.put("code", msg);
		return r;
	}

	public static R okF(Object obj) {
		R r = new R();
		r.put("msg", "ok");
		r.put("data", obj);
		return r;
	}

	public static R success(Object obj) {
		R r = new R();
		r.put("code", 200);
		r.put("msg", "ok");
		r.put("data", obj);
		return r;
	}

}