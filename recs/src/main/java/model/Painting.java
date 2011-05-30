package model;

import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.emul.org.bson.types.ObjectId;

import cv.descriptor.FeatureVector;

@Entity
public class Painting {

	@Id private ObjectId id = new ObjectId();
	private String title;
	private String artist;
	private String paintingId;
	private int descriptorType = 1;
	private int[] scaleIndices = null;
	@Embedded private List<String> tags;
	@Embedded private List<FeatureVector> featureVectors;
	
	public Painting setDescriptorType(int type)
	{
		this.descriptorType = type;
		return this;
	}
	
	public int getDescriptorType()
	{
		return this.descriptorType;
	}
	
	public Painting setPaintingId(String pID)
	{
		this.paintingId = pID;
		return this;
	}
	
	public Painting setTitle(String title)
	{
		this.title = title;
		return this;
	}
	
	public Painting setArtist(String artist)
	{
		this.artist = artist;
		return this;
	}
	
	public Painting setFeatureVectors(List<FeatureVector> featureVectors)
	{
		this.featureVectors = featureVectors;
		return this;
	}
	
	public Painting setScaleIndices(int[] indexes)
	{
		this.scaleIndices = indexes;
		return this;
	}
	
	public Painting setTags(List<String> tagList)
	{
		this.tags = tagList;
		return this;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getArtist()
	{
		return this.artist;
	}
	
	public List<FeatureVector> getFeatureVectors()
	{
		return this.featureVectors;
	}
	
	public ObjectId getId()
	{
		return this.id;
	}
	
	public String getPaintingId()
	{
		return this.paintingId;
	}
	
	public int[] getScaleIndices()
	{
		return this.scaleIndices;
	}
	
	public List<String> getTags()
	{
		return this.tags;
	}
	
	public float getMatchScore(Painting painting, float threshold)
	{
		int matched = 0;
		double d1 = Double.MAX_VALUE;
		double d2 = Double.MAX_VALUE;
		List<FeatureVector> otherFeatureVectors = painting.getFeatureVectors();
		for (FeatureVector anfv : otherFeatureVectors) {
			for (int i = 0; i < this.featureVectors.size(); ++i) {
				double vectorDistance = this.featureVectors.get(i).getVectorDistance(anfv);
				if (Double.isNaN(vectorDistance))
					continue;
				if (d1 < Double.MAX_VALUE && d2 < Double.MAX_VALUE) {
					if (vectorDistance <= d1) {
						d1 = vectorDistance;
					} else if (vectorDistance <= d2) {
						d2 = vectorDistance;
					}
				} else if (d1 == Double.MAX_VALUE) {
					d1 = vectorDistance;
				} else if (d2 == Double.MAX_VALUE) {
					d2 = vectorDistance;
				}
			}
			if (d1 < threshold) matched++;
			d1 = d2 = Double.MAX_VALUE;
		}
		return (float) matched / featureVectors.size();
	}
}
