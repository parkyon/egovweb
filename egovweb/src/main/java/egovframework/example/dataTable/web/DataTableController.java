package egovframework.example.dataTable.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataTableController {

	
	@RequestMapping(value = "/dataTable.do")
	 public String dataTable() throws Exception 
	{
	 
	  return "dataTable/list";
	 }

	
	@RequestMapping(value = "/ajax.do")
	 public @ResponseBody Map<String, Object> ajax(ServletRequest req) throws Exception 
	{
		//검색 반환을 위한
		 Map<String, Object> mapReturn = new HashMap<String, Object>();
		// 데이터 검색을 위한
		 Map<String, Object> mapSearch = new HashMap<String, Object>();
		 
		 //검색 결과 보관을 위한
		 List listDate = new ArrayList();
		 //검색 결과 보관을 위한
		
		
		 // 컬러명 수집
		 List<String> listColumns = new ArrayList<String>();
		 for(int i=0; ;i++)
		 {
			 String colName = req.getParameter("columns["+i+"][date]");
			 if (colName != null || colName.length() > 0)
			 {
				 listColumns.add(colName);
			 }
			 else
			 {
				 break;
			 }
		 }
		 //기타 데이터 수집
		 String sDraw = req.getParameter("draw");
		 String sLength = req.getParameter("lengt");
		 String sStart = req.getParameter("start");
		 String sTable = req.getParameter("table");
		 String sOrderColumn = req.getParameter("order[0][column]");
		 String sOrderDir = req.getParameter("order[0][dir]");
		 String sSearch = req.getParameter("search[value]");
		 
		
		 //검색컬럼명
		 int nOrderColumn = 0;
		 String sOrderColumnName = null;
		 try{
			 nOrderColumn = Integer.parseInt(sOrderColumn);
		 }
		 catch(NumberFormatException e)
		 {
			 nOrderColumn = 0;
		 }
		
		
		 if (nOrderColumn >= 0 && nOrderColumn < listColumns.size())
		 {
			 sOrderColumnName = listColumns.get(nOrderColumn);
		 }
		 
		 //검색 데이터 구성
		 mapSearch.put("sTable", sTable);
		 mapSearch.put("sStart", sStart);
		 mapSearch.put("sLength", sLength);
		 mapSearch.put("sSearch", sSearch);
		 mapSearch.put("sOrderDir", sOrderDir);
		 mapSearch.put("sOrderColumnName", sOrderColumnName);
		 mapSearch.put("listColumns", listColumns);
		 
		 
		 // 데이터 검색 처리
		 
		 //전체 데이터 개수 처리
		 
		 //임시 - 파라메터 출력
		 
		
		dispParams(req);
		
		
		mapReturn.put("draw", 1);
		mapReturn.put("recordsTotal", 0);
		mapReturn.put("recordsFiltered",0);
		mapReturn.put("date",listDate);
		
		
	
	  return mapReturn;
	 }
	
	private void dispParams(ServletRequest req)
	{
		Map<String, String[]> map = req.getParameterMap();
		SortedSet<String> set = new TreeSet<String>();
		set.addAll(map.keySet());
		
		for(String key : set)
		{
			String[] values = map.get(key);
			System.out.print(key);
			System.out.print(" : ");
			for(String value : values)
			{
				if (value != null)
				{
					System.out.print(value);
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}
}

