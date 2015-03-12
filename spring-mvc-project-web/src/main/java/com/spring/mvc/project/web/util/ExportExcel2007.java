package com.spring.mvc.project.web.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.View;

import com.spring.mvc.project.domain.util.Constant;
import com.spring.mvc.project.domain.util.ExportField;
import com.spring.mvc.project.domain.util.ExportField.DataType;
import com.spring.mvc.project.web.util.FileUtil.FileType;

/**
 * 
 * @author 
 *
 */
public class ExportExcel2007 implements View {

	private XSSFSheet sheet;

	private int columns;
	private int rows;
	private XSSFCellStyle leftStyle;
	private XSSFCellStyle rightStyle;
	private XSSFCellStyle percentStyle;

	private String DEFAULT_CONTENT_TYPE = "application/octet-stream; charset=utf-8";

	/**
	 * 文件名称
	 */
	private String filename = "temp.zip";
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件类型
	 */
	private FileType fileType;
	/**
	 * 数据
	 */
	private List<?> data;
	/**
	 * 字段列表
	 */
	private List<ExportField> fields;
	/**
	 * excel的工作表名称
	 */
	private String sheetName;

	/**
	 * 构造方法：适用于excel文件，支持DBF文件
	 * @param filename 文件名称
	 * @param fileType 文件类型
	 * @param sheetName excel的工作表名称
	 * @param data 数据
	 * @param fields 字段列表
	 */
	public ExportExcel2007(String filename, FileType fileType, String sheetName, List<?> data, List<ExportField> fields) {
		this.filename = filename;
		this.fileType = fileType;
		this.sheetName = sheetName;
		this.data = data;
		this.fields = fields;
		this.columns = fields.size();
	}

	@Override
	public String getContentType() {
		// the content type String (optionally including a character set), or null if not predetermined.
		return null;
	}

	@Override
	public void render(Map<String, ?> obj, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		buildExcelDocument(obj, workbook, request, response);
	}

	public void buildExcelDocument(Map<String, ?> obj, XSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sheet = workbook.createSheet(sheetName);

		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 11); // 字体大小
		font.setFontName("宋体");

		// 单元格样式
		leftStyle = createCellStyle(workbook, font, HSSFCellStyle.ALIGN_LEFT);
		rightStyle = createCellStyle(workbook, font, HSSFCellStyle.ALIGN_RIGHT);
		percentStyle = createCellStyle(workbook, font, HSSFCellStyle.ALIGN_RIGHT);
		percentStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));

		rows = 0;

		initHeader(workbook);

		for (int i = 0; i < data.size(); i++) {
			appendData(data.get(i), null);
		}

		String agent = request.getHeader("USER-AGENT");
		String headerFilename = null;
		if ((agent != null) && (-1 != agent.indexOf("MSIE") || -1 != agent.indexOf("Trident"))) {
			headerFilename = "attachment;filename=" + URLEncoder.encode(filename, "UTF-8");
		} else {
			headerFilename = "attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", headerFilename + Constant.SEPARATOR_DOT + this.fileType.name());
		response.setContentType(DEFAULT_CONTENT_TYPE);
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	public void initHeader(XSSFWorkbook workbook) {
		XSSFRow row = sheet.createRow(rows++);
		for (int i = 0; i < columns; i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(leftStyle);
			cell.setCellValue(fields.get(i).getShowName());
		}
	}

	public XSSFCellStyle createCellStyle(XSSFWorkbook workbook, XSSFFont font, short align) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(align);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 追加一条数据
	 * @throws Exception 
	 */
	public void appendData(Object data, String message) throws Exception {
		XSSFRow row = sheet.createRow(rows++);
		Object[] objs = parseData(data, message);
		for (int i = 0, size = columns; i < size; i++) {
			DataType dataType = fields.get(i).getType();
			Cell cell = null;
			switch (dataType) {
			case STRING:
				cell = row.createCell(i, Cell.CELL_TYPE_STRING);
				cell.setCellValue(objs[i] == null ? "" : objs[i].toString());
				cell.setCellStyle(leftStyle);
				break;
			case INT:
				cell = row.createCell(i, Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Double.parseDouble(objs[i].toString()));
				cell.setCellStyle(rightStyle);
				break;
			case PERCENT:
				cell = row.createCell(i, Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Double.parseDouble(objs[i].toString()));
				cell.setCellStyle(percentStyle);
				break;
			case DATE:
				cell = row.createCell(i, Cell.CELL_TYPE_STRING);
				cell.setCellValue((Date) objs[i]);
				cell.setCellStyle(rightStyle);
				break;
			case BOOLEAN:
				cell = row.createCell(i, Cell.CELL_TYPE_BOOLEAN);
				cell.setCellValue(Boolean.parseBoolean(objs[i].toString()));
				cell.setCellStyle(rightStyle);
				break;
			default:
				cell = row.createCell(i, Cell.CELL_TYPE_STRING);
				cell.setCellValue(objs[i] == null ? "" : objs[i].toString());
				cell.setCellStyle(leftStyle);
				break;
			}
		}
	}

	/**
	 * 通过反射将数据转换为字符数组
	 * @param data 数据
	 * @param message 消息
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public Object[] parseData(Object data, String message) throws Exception {
		int size = fields.size();
		Object[] values = null;
		values = new Object[size];
		if (data instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) data;
			for (int j = 0; j < size; j++) {
				values[j] = map.get(fields.get(j).getName());
			}
			return values;
		}
		for (int j = 0; j < size; j++) {
			String fieldName = fields.get(j).getName();
			DataType type = fields.get(j).getType();
			Object temp = data;
			if (fieldName.contains(Constant.SEPARATOR_DOT)) {
				String[] fieldNames = fieldName.split(Constant.SEPARATOR_BACKSLASH + Constant.SEPARATOR_DOT);
				for (int k = 0; k < fieldNames.length - 1; k++) {
					temp = getValue(fieldNames[k], false, temp);
					if (temp == null) {
						break;
					}
				}
				fieldName = fieldNames[fieldNames.length - 1];
			}
			values[j] = getValue(fieldName, DataType.BOOLEAN.equals(type), temp);
		}
		return values;
	}

	/**
	 * 根据字段名获取字段值
	 * @param fieldName 字段名称
	 * @param obj 对象
	 * @return
	 * @throws Exception
	 */
	public Object getValue(String fieldName, boolean isBoolean, Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		if (isBoolean) {
			methodName = "is" + methodName;
		} else {
			methodName = "get" + methodName;
		}
		//获得方法名
		Method method = obj.getClass().getMethod(methodName);
		return method.invoke(obj);
	}

}
