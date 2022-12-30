/**
 * 
 */
package us.muit.fs.a4i.persistence;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;

import us.muit.fs.a4i.config.Context;


import us.muit.fs.a4i.model.entities.IndicatorI;

/**
 * @author Isabel Rom�n
 *
 */
public class ReportFormater implements ReportFormaterI {
	/**
	 * Formato de fuente usado si no se ha definido ninguna otra
	 */
	private Font defaultFont;
	/**
	 * Formato de fuente para las m�tricas
	 */
	private Font metricFont;
	/**
	 * Fomatos de fuente en funci�n del estado del indicador
	 */
	private HashMap<IndicatorI.IndicatorState,Font> indicatorsFont;
	
    ReportFormater(){
    	 	this.indicatorsFont=new HashMap<IndicatorI.IndicatorState,Font>();    	 	
    	 	//S�lo se construye el mapa, conforme se vayan solicitando se ir�n rellenando
    }
	@Override
	public Font getMetricFont() {
		if (metricFont==null) {
			metricFont=Context.getMetricFont();
		}
		return metricFont;
	}

	@Override
	public void setMetricFont(Font font) {
		metricFont=font;

	}

	@Override
	public Font getIndicatorFont(IndicatorI.IndicatorState state) throws IOException {	
		if (!indicatorsFont.containsKey(state)){
			try {
				indicatorsFont.put(state, Context.getIndicatorFont(state));
			} catch (IOException e) {
				indicatorsFont.put(state, Context.getContext().getDefaultFont());
			}
		}	
	
		return indicatorsFont.get(state);
	}

	@Override
	public void setIndicatorFont(IndicatorI.IndicatorState state, Font font) {
		indicatorsFont.put(state, font);

	}

	@Override
	public void setDefaultFont(Font font) {
		defaultFont=font;
	}
	@Override
	public Font getDefaultFont() {	
		return this.defaultFont;
	}
	
}
