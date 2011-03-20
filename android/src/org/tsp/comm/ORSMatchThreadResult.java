package org.tsp.comm;

import java.util.List;

import org.tsp.model.Painting;

public class ORSMatchThreadResult extends ThreadResult {

	private List<Painting> relevantPaintings = null;
	private Painting matchedPainting = null;
	
	public ORSMatchThreadResult()
	{
		super();
	}
	
	public void setRelevantPaintings(List<Painting> relevantPaintings)
	{
		this.relevantPaintings = relevantPaintings;
	}
	
	public void setMatchedPainting(Painting matchedPainting)
	{
		this.matchedPainting = matchedPainting;
	}

	public List<Painting> getRelevantPaintings()
	{
		return relevantPaintings;
	}
	
	public Painting getMatchedPainting()
	{
		return matchedPainting;
	}
	
	@Override
	protected void subReset() 
	{
		relevantPaintings = null;
		matchedPainting = null;
	}
	
}
