package kr.co.ethree.dev.common.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ParamMap extends HashMap {

	private static final long serialVersionUID = -2422921337596913081L;

	private final static int MODE_HASHMAP = 0;
	private final static int MODE_REQUEST = 1;

	private final int MAX_QUERY_BUFFER = 256;

	private int initMode = -1;

	private HttpServletRequest _request = null;
	private Enumeration<?> _enum = null;

	public ParamMap() {
		super();

		initMode = MODE_HASHMAP;
	}

	public ParamMap(final Object o) {
		this((HashMap<?, ?>) o);

		initMode = MODE_HASHMAP;
	}

	public ParamMap(final HashMap<?, ?> map) {
		super();

		if (map != null)
			this.putAll(map);
		initMode = MODE_HASHMAP;
	}

	public ParamMap(final String key, final Object value) {
		super();

		super.put(key, value);
	}

	public ParamMap(final Map<?, ?> map) {
		super();
		if (map != null)
			this.putAll(map);
		initMode = MODE_HASHMAP;
	}

	public ParamMap(final HttpServletRequest request) {
		super();

		setRequest(request);
		initMode = MODE_REQUEST;
	}

	public int length() {
		return super.entrySet().size();
	}

	public String getQueryString(final String name) {
		String query = _request.getQueryString();
		if (query == null)
			return null;

		if (query.startsWith(name + "=")) {
			int pos = query.indexOf("&");

			if (pos > -1)
				return query.substring(name.length() + 1, pos);
			else
				return query.substring(name.length() + 1);
		} else {
			int pos1 = query.indexOf("&" + name + "=");

			if (pos1 > -1) {
				int pos2 = query.indexOf("&", pos1 + 1);

				if (pos2 > -1)
					return query.substring(pos1 + name.length() + 2, pos2);
				else
					return query.substring(pos1 + name.length() + 2);
			}
		}
		return null;
	}

	public int getQueryStringCount(final String name) {
		String query = _request.getQueryString();
		if (query == null)
			return 0;

		int cnt = 0;
		int last = 0;
		while (true) {
			int start = query.indexOf(name + "=", last);
			if (start > -1) {
				cnt++;
				last = start + name.length() + 1;
			} else
				break;
		}
		return cnt;
	}

	private boolean hasQueryString(final String name) {
		String query = _request.getQueryString();
		if (query == null)
			return false;

		if (query.startsWith(name + "="))
			return true;
		else {
			if (query.indexOf("&" + name + "=") > 0) {
				return true;
			}
		}
		return false;
	}

	public ParamMap copy() {
		return (ParamMap) super.clone();
	}

	public void put(final String column, final Clob value) {
		Reader reader = null;
		BufferedReader buffer = null;
		StringBuilder text = new StringBuilder();

		try {
			String line;
			reader = value.getCharacterStream();
			buffer = new BufferedReader(reader);

			while (null != (line = buffer.readLine())) {
				if ("".equals(line))
					continue;

				text.append(line).append("\n");
			}
		} catch (Exception e) {
			set(column, "");
		} finally {
			try {
				if (null != buffer)
					buffer.close();
				if (null != reader)
					reader.close();
			} catch (Exception ce) {
			}
		}

		set(column, text.toString());
	}

	public void set(final String column, final Clob value) {
		Reader reader = null;
		BufferedReader buffer = null;
		StringBuilder text = new StringBuilder();

		try {
			String line;
			reader = value.getCharacterStream();
			buffer = new BufferedReader(reader);

			while (null != (line = buffer.readLine())) {
				if ("".equals(line))
					continue;

				text.append(line).append("\n");
			}
		} catch (Exception e) {
			set(column, "");
		} finally {
			try {
				if (null != buffer)
					buffer.close();
				if (null != reader)
					reader.close();
			} catch (Exception ce) {
			}
		}

		set(column, text.toString());
	}

	public void set(final String column, final String value) {
		super.put(column, value);
	}

	public void set(final String column, final Object value) {
		super.put(column, value);
	}

	public void set(final String column, final int value) {
		super.put(column, Integer.toString(value));
	}

	public void set(final String column, final long value) {
		super.put(column, Long.toString(value));
	}

	public void set(final String column, final float value) {
		super.put(column, Float.toString(value));
	}

	public void set(final String column, final double value) {
		super.put(column, Double.toString(value));
	}

	public void set(final String column, final String[] value) {
		super.put(column, value);
	}

	public void set(final String column, final InputStream value) {
		super.put(column, value);
	}

	public int getInt(final String column) {
		return getInt(column, 0);
	}

	public int getInt(final String column, final int v_default) {
		int val = v_default;
		try {
			if (super.get(column) != null) {
				if (super.get(column) instanceof Integer) {
					return ((Integer) super.get(column)).intValue();
				} else if (super.get(column) instanceof BigDecimal) {
					return Integer.parseInt(((BigDecimal) super.get(column)).toString());
				} else if (super.get(column) instanceof Short) {
					return Integer.parseInt(((Short) super.get(column)).toString());
				} else if (super.get(column) instanceof Long) {
					return Integer.parseInt(String.valueOf(super.get(column)));
				} else {
					return Integer.parseInt((String) super.get(column));
				}
			} else {
				return val;
			}
		} catch (NumberFormatException e) {
		}

		return val;
	}

	public long getLong(final String column) {
		return getLong(column.replaceAll(",", ""), 0);
	}

	public long getLong(final String column, final long v_default) {
		long val = v_default;
		try {
			if (super.get(column) instanceof BigDecimal) {
				val = Long.parseLong(((BigDecimal) super.get(column)).toString());
			} else {
				val = Long.parseLong((String) super.get(column));
			}
		} catch (NumberFormatException e) {
		}

		return val;
	}

	public Object get(Object key) {

		Object obj = super.get(key);

		if (obj instanceof Clob || obj instanceof oracle.sql.CLOB) {

			Reader reader = null;
			BufferedReader buffer = null;
			StringBuffer text = new StringBuffer();

			try {
				String line;
				reader = ((Clob) super.get(key)).getCharacterStream();
				buffer = new BufferedReader(reader);

				while (null != (line = buffer.readLine())) {
					if ("".equals(line)) {
						continue;
					}

					text.append(line).append("\n");
				}
			} catch (Exception e) {
				return "";

			} finally {
				try {
					if (null != buffer) {
						buffer.close();
					}
					if (null != reader) {
						reader.close();
					}
				} catch (Exception ce) {

				}
			}

			return text.toString();

		} else {
			return obj;
		}
	}

	public List getList(final String key) {
		return getList(key, new ArrayList());
	}

	public List getList(final String key, final List value) {
		if (null == super.get(key)) {
			return value;
		}
		return (List) super.get(key);
	}

	public String getStr(final String key) {

		if (super.get(key) == null || nvl(super.get(key)).equals("")) {
			return "";
		} else if (super.get(key) instanceof Clob || super.get(key) instanceof oracle.sql.CLOB) {
			Reader reader = null;
			BufferedReader buffer = null;
			StringBuilder text = new StringBuilder();

			try {
				String line;
				reader = ((Clob) super.get(key)).getCharacterStream();
				buffer = new BufferedReader(reader);

				while (null != (line = buffer.readLine())) {
					if ("".equals(line))
						continue;

					text.append(line).append("\n");
				}
			} catch (Exception e) {
				return "";
			} finally {
				try {
					if (null != buffer)
						buffer.close();
					if (null != reader)
						reader.close();
				} catch (Exception ce) {
				}
			}

			return text.toString();
		} else if (super.get(key) instanceof String[]) {
			String[] val = (String[]) super.get(key);

			if (0 < val.length) {
				return val[0];
			} else {
				return "";
			}

		} else if (super.get(key) instanceof BigDecimal) {
			return String.valueOf((BigDecimal) super.get(key));

		} else {
			return nvl(super.get(key));
		}
	}

	public String getStr(final String key, final int value) {
		if (super.get(key) == null || nvl(super.get(key)).equals(""))
			return Integer.toString(value);
		else
			return nvl(super.get(key));
	}

	public String getStr(final String key, final String value) {
		if (super.get(key) == null || nvl(super.get(key)).equals(""))
			return value;
		else
			return nvl(super.get(key));
	}

	public String getSubString(String key, int index) {
		try {
			return getStr(key).substring(0, index);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

	public byte[] getByte(final String key) {
		if (null != super.get(key)) {
			try {
				return (byte[]) super.get(key);
			} catch (Exception e) {
				return new byte[0];
			}
		}

		return new byte[0];
	}

	public String[] getValues(final String key) {
		if (super.get(key) != null) {
			try {
				Object obj = super.get(key);
				if (obj instanceof ArrayList) {
					ArrayList<?> list = (ArrayList<?>) obj;
					return (String[]) list.toArray(new String[0]);
				} else if (obj instanceof String) {
					String[] rv = { (String) obj };
					return rv;
				} else {
					return (String[]) obj;
				}
			} catch (java.lang.ClassCastException e) {
				String[] rv = { (String) super.get(key) };
				return rv;
			} catch (Exception ee) {
				String[] rv = { (String) super.get(key) };
				return rv;
			}
		} else {
			return new String[0];
		}
	}

	public float getFloat(String column) {
		return getFloat(column, 0);
	}

	public float getFloat(final String column, final float v_default) {
		float val = v_default;
		try {
			if (null != super.get(column)) {
				if (super.get(column) instanceof Float)
					return ((Float) super.get(column)).floatValue();
				else if (super.get(column) instanceof BigDecimal)
					return Float.parseFloat(((BigDecimal) super.get(column)).toString());
				else
					return Float.parseFloat((String) super.get(column));
			}
			val = Float.parseFloat((String) super.get(column));
		} catch (NullPointerException e) {
		}

		return val;
	}

	public double getDouble(final String column) {
		return getDouble(column, 0);
	}

	public double getDouble(final String column, final double v_default) {
		double val = v_default;
		try {
			if (null != super.get(column)) {
				if (super.get(column) instanceof Double) {
					return ((Double) super.get(column)).doubleValue();
				} else if (super.get(column) instanceof BigDecimal) {
					return Double.parseDouble(((BigDecimal) super.get(column)).toString());
				} else if (super.get(column) instanceof Short || super.get(column) instanceof Long || super.get(column) instanceof Float) {
					return (Double) super.get(column);
				} else {
					return Double.parseDouble((String) super.get(column));
				}
			} else {
				return val;
			}
		} catch (NullPointerException e) {
		}

		return val;
	}

	public boolean getBoolean(final String column) {
		if (super.get(column) instanceof Boolean) {
			return (Boolean) super.get(column);
		} else {
			return !isNull(column);
		}
	}

	public ParamMap getParam(final String column) {
		if (super.get(column) instanceof ParamMap) {
			return (ParamMap) super.get(column);
		} else {
			ParamMap result = new ParamMap();
			result.set(column, super.get(column));

			return result;
		}
	}

	public void remove(final String key) {
		super.remove(key);
	}

	public String toQueryString() {
		StringBuffer sb = new StringBuffer();

		Set<?> set = this.entrySet();
		Iterator<?> keys = set.iterator();

		boolean isFirst = true;
		while (keys.hasNext()) {
			String sKey = keys.next().toString();
			if (!isFirst)
				sb.append("&");
			sb.append(sKey);
			isFirst = false;
		}

		return sb.toString();
	}

	public String toQueryString(final String url) {
		if (toQueryString().equals("")) {
			return url;
		} else {
			if (url.indexOf("?") > -1)
				return url + "&" + toQueryString();
			else
				return url + "?" + toQueryString();
		}
	}

	private static String nvl(final Object v) {

		if (v == null)
			return "";
		else
			return String.valueOf(v);
	}

	@Override
	public String toString() {
		String s = "";
		Set<?> set = this.entrySet();
		for (Iterator<?> it = set.iterator(); it.hasNext();) {
			String key = it.next().toString();

			s += "[" + key + "]";
		}
		return s;
	}

	private String antiBufferOverFlow(String org) {
		if (org.length() > MAX_QUERY_BUFFER) {
			return org.substring(0, MAX_QUERY_BUFFER);
		} else
			return org;
	}

	public void setRequest(final HttpServletRequest request) {
		this._request = request;
		try {
			_enum = _request.getParameterNames();
			while (_enum.hasMoreElements()) {

				String key = (String) _enum.nextElement();

				if (hasQueryString(key)) {
					String sAnti = nvl(_request.getParameter(key));
					sAnti = antiBufferOverFlow(sAnti);

					this.put(key, sAnti);
				} else {
					this.put(key, nvl(_request.getParameter(key)));
				}

				String[] pv = null;
				if (null != _request.getParameterValues(key)) {
					pv = _request.getParameterValues(key);
					int nQueryString = getQueryStringCount(key);

					for (int i = 0; i < pv.length; i++) {
						if (i < nQueryString) {
							String sAnti = nvl(pv[i]);
							sAnti = antiBufferOverFlow(sAnti);
							// sAnti = Utils.safeHTML(sAnti);
							// sAnti = sAnti;

							pv[i] = sAnti;
						}
					}

					if (pv.length > 1)
						this.put(key, pv);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception :" + e);
		}
	}

	public boolean isNull(final String key) {
		switch (initMode) {
		case MODE_HASHMAP:
			return (null == super.get(key));
		case MODE_REQUEST:
			return (null == _request.getParameter(key));
		}

		return false;
	}

	public boolean isList(final String key) {
		return super.get(key) instanceof List;
	}

	public boolean isBoolean(final String key) {
		return super.get(key) instanceof Boolean;
	}

	public boolean isParam(final String key) {
		return super.get(key) instanceof ParamMap;
	}

	public Object getObject(final String key) {
		return super.get(key);
	}

	public String toHiddenForm() {
		StringBuffer sb = new StringBuffer();

		Iterator<?> keys = this.entrySet().iterator();
		while (keys.hasNext()) {
			String sKey = keys.next().toString();
			String[] tmp = sKey.split("=");
			sb.append("<input type=\"hidden\" name=\"" + tmp[0] + "\" value=\"" + (1 > tmp.length ? tmp[1] : "") + "\" />\n");
		}

		return sb.toString();
	}

}