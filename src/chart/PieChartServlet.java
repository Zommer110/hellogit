package chart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//1. �����͸� �����Ѵ�.
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Son-OGong", 63.2);
		dataset.setValue("Jer-PalGae", 37.9);
		dataset.setValue("Sa-OJung", 29.5);
		//2. ��Ʈ�� �����.
		JFreeChart chart = ChartFactory.createPieChart("Energy Power", dataset, true, true, false);
		//3. ��Ʈ�� �����ش�.
		ServletOutputStream out = response.getOutputStream();
		ChartUtils.writeChartAsPNG(out, chart, 400, 400);
	}
}
